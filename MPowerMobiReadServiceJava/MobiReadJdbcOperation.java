import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ibm.broker.javacompute.MbJavaComputeNode;
import com.ibm.broker.plugin.MbElement;
import com.ibm.broker.plugin.MbException;
import com.ibm.broker.plugin.MbJSON;
import com.ibm.broker.plugin.MbMessage;
import com.ibm.broker.plugin.MbMessageAssembly;
import com.ibm.broker.plugin.MbPolicy;
import com.oshee.util.ACELogger;

public abstract class MobiReadJdbcOperation extends MbJavaComputeNode {
	private static final String[] TYPES = { "fatura", "converge", "feed-hec-nenst", "special" };
	private static final String[] TABLES = { "MODOWNHEADER_FATURA_NEW", "MODOWNHEADER_CONVERGE_NEW",
			"MODOWNHEADER_F_H_N_NEW", "MODOWNHEADER_FIDERI16_NEW" };
	private final String operation;
	private final String sqlTemplate;
	private final String policy;
	private final String shape;
	private final String[] parameters;
	private final String[][] fields;

	protected MobiReadJdbcOperation(String operation, String sql, String policy, String shape, String[] parameters,
			String[][] fields) {
		this.operation = operation;
		this.sqlTemplate = sql;
		this.policy = policy;
		this.shape = shape;
		this.parameters = parameters;
		this.fields = fields;
	}

	@Override
	public void evaluate(MbMessageAssembly input) throws MbException {
		MbMessage response = new MbMessage(input.getMessage());
		try {
			String correlationId = resolveCorrelationId(input);
			String sql = sqlTemplate;
			int limit = 14000;
			long offset = 0;
			List<Long> bindings = new ArrayList<Long>();
			try {
				int type = 0;
				if (sql.contains("${tableName}") || hasParameter("typeNr")) {
					String sourceType = parameter(input, "type");
					for (int i = 0; i < TYPES.length; i++) {
						if (TYPES[i].equals(sourceType))
							type = i + 1;
					}
					if (type == 0)
						throw new IllegalArgumentException("Invalid or missing type.");
					sql = sql.replace("${tableName}", TABLES[type - 1]);
				}
				if (hasParameter("limit")) {
					limit = (int) integerParameter(input, "limit", 14000, 1, 14000);
					offset = integerParameter(input, "offset", 0, 0, Long.MAX_VALUE);
				}
				for (String parameter : parameters) {
					if ("typeNr".equals(parameter))
						bindings.add((long) type);
					else if ("offset".equals(parameter))
						bindings.add(offset);
					else if ("limit".equals(parameter))
						bindings.add((long) limit);
					else
						throw new IllegalStateException("Unknown SQL binding.");
				}
			} catch (IllegalArgumentException invalid) {
				error(response, 400, "INVALID_REQUEST", invalid.getMessage());
				propagate(input, response, correlationId);
				return;
			}
			try {
				sql = applySchemaPolicy(sql);
				Connection connection = getJDBCType4Connection(policy, JDBC_TransactionType.MB_TRANSACTION_AUTO);
				ACELogger.info(correlationId, operation, "Executing SQL: " + renderSqlForLog(sql, bindings));
				try (PreparedStatement statement = connection.prepareStatement(sql)) {
					for (int i = 0; i < bindings.size(); i++)
						statement.setLong(i + 1, bindings.get(i));
					if ("truncate".equals(shape)) {
						statement.execute();
						reset(response, 204, false);
					} else {
						MbElement data = reset(response, 200, true);
						try (ResultSet rows = statement.executeQuery()) {
							if ("total".equals(shape) || "count".equals(shape)) {
								if (!rows.next())
									throw new SQLException("Count query returned no row");
								field(data, fields[0][1], read(rows, fields[0]));
							} else if ("min".equals(shape)) {
								field(data, "minItemId", rows.next() ? read(rows, fields[0]) : null);
							} else {
								MbElement array;
								if ("page".equals(shape)) {
									array = data.createElementAsLastChild(MbJSON.ARRAY, "items", null);
								} else {
									data.delete();
									data = response.getRootElement().getFirstElementByPath("JSON")
											.createElementAsLastChild(MbJSON.ARRAY, MbJSON.DATA_ELEMENT_NAME, null);
									array = data;
								}
								int returned = 0;
								while (rows.next()) {
									MbElement item = array.createElementAsLastChild(MbElement.TYPE_NAME,
											MbJSON.ARRAY_ITEM_NAME, null);
									for (String[] definition : fields)
										field(item, definition[1], read(rows, definition));
									returned++;
								}
								if ("page".equals(shape)) {
									MbElement pagination = data.createElementAsLastChild(MbElement.TYPE_NAME,
											"pagination", null);
									field(pagination, "limit", limit);
									field(pagination, "offset", offset);
									field(pagination, "returned", returned);
								}
							}
						}
					}
				}
			} catch (SQLException | MbException failure) {
				logFailure(correlationId, failure);
				error(response, 500, "DATABASE_ERROR", "Unable to execute the database operation.");
			}
			propagate(input, response, correlationId);
		} finally {
			response.clearMessage();
		}
	}

	private String applySchemaPolicy(String sql) throws MbException, SQLException {
        if (!sql.contains("${schemaName}")) {
            return sql;
        }
        MbPolicy schemaPolicy = MbPolicy.getPolicy("JDBCProviders", policy);
        if (schemaPolicy == null) {
            throw new SQLException("JDBC policy is not deployed: " + policy);
        }
        String schemaName = schemaPolicy.getPropertyValueAsString("databaseSchemaNames");
        if (schemaName == null || schemaName.trim().isEmpty()) {
            throw new SQLException("JDBC policy databaseSchemaNames is missing: " + policy);
        }
        schemaName = schemaName.trim();
        if (!schemaName.matches("[A-Za-z][A-Za-z0-9_$#]{0,127}")) {
            throw new SQLException("JDBC policy databaseSchemaNames must contain one valid unquoted schema name: " + policy);
        }
        return sql.replace("${schemaName}", schemaName);
    }

	private static String resolveCorrelationId(MbMessageAssembly input) throws MbException {
        MbElement headers = input.getMessage().getRootElement().getFirstElementByPath("HTTPInputHeader");
        if (headers == null) {
            headers = input.getMessage().getRootElement().getFirstElementByPath("HTTPRequestHeader");
        }
        if (headers != null) {
            for (MbElement header = headers.getFirstChild(); header != null; header = header.getNextSibling()) {
                if ("X-Correlation-Id".equalsIgnoreCase(header.getName()) && header.getValue() != null) {
                    String value = header.getValueAsString().replace('\r', ' ').replace('\n', ' ').trim();
                    return value.isEmpty() ? UUID.randomUUID().toString() : value;
                }
            }
        }
        return UUID.randomUUID().toString();
    }

	private static String renderSqlForLog(String sql, List<Long> bindings) {
		for (Long value : bindings) {
			sql = sql.replaceFirst("\\?", value == null ? "NULL" : value.toString());
		}
		return sql;
	}
	
	private boolean hasParameter(String name) {
		for (String value : parameters)
			if (name.equals(value))
				return true;
		return false;
	}

	private static String parameter(MbMessageAssembly input, String name) throws MbException {
		MbElement element = input.getLocalEnvironment().getRootElement()
				.getFirstElementByPath("REST/Input/Parameters/" + name);
		return element == null || element.getValue() == null ? null : element.getValueAsString();
	}

	private static long integerParameter(MbMessageAssembly input, String name, long defaultValue, long minimum,
			long maximum) throws MbException {
		String text = parameter(input, name);
		if (text == null)
			return defaultValue;
		try {
			if (!text.matches("[0-9]+"))
				throw new NumberFormatException();
			long value = Long.parseLong(text);
			if (value < minimum || value > maximum)
				throw new NumberFormatException();
			return value;
		} catch (NumberFormatException invalid) {
			throw new IllegalArgumentException(
					"Invalid " + name + "; expected integer from " + minimum + " to " + maximum + ".");
		}
	}

	private static Object read(ResultSet rows, String[] definition) throws SQLException {
		if ("string".equals(definition[2]))
			return rows.getString(definition[0]);
		BigDecimal value = rows.getBigDecimal(definition[0]);
		if (value == null)
			return null;
		if ("integer".equals(definition[2])) {
			try {
				return value.longValueExact();
			} catch (ArithmeticException invalid) {
				throw new SQLException("Database integer exceeds the API int64 representation", invalid);
			}
		}
		return value;
	}

	private static void field(MbElement parent, String name, Object value) throws MbException {
		parent.createElementAsLastChild(MbElement.TYPE_NAME_VALUE, name, value);
	}

	private static MbElement reset(MbMessage response, int status, boolean json) throws MbException {
		MbElement root = response.getRootElement();
		MbElement child = root.getFirstChild();
		while (child != null) {
			MbElement next = child.getNextSibling();
			if (!"Properties".equals(child.getName()))
				child.delete();
			child = next;
		}
		MbElement headers = root.createElementAsLastChild("HTTPReplyHeader");
		field(headers, "X-Original-HTTP-Status-Code", status);
		if (!json)
			return null;
		field(headers, "Content-Type", "application/json");
		return root.createElementAsLastChild(MbJSON.PARSER_NAME).createElementAsLastChild(MbElement.TYPE_NAME,
				MbJSON.DATA_ELEMENT_NAME, null);
	}

	private static void error(MbMessage response, int status, String code, String message) throws MbException {
		MbElement data = reset(response, status, true);
		field(data, "code", code);
		field(data, "message", message);
	}

	private void logFailure(String correlationId, Exception failure) {
		String detail = failure instanceof SQLException
				? "SQLState=" + ((SQLException) failure).getSQLState() + ", errorCode="
						+ ((SQLException) failure).getErrorCode()
				: failure.getClass().getSimpleName();
		ACELogger.error(correlationId, operation, "Database operation failed (" + detail + ")");
	}

	private void propagate(MbMessageAssembly input, MbMessage response, String correlationId) throws MbException {
        MbElement headers = response.getRootElement().getFirstElementByPath("HTTPReplyHeader");
        field(headers, "X-Correlation-ID", correlationId);
        getOutputTerminal("out").propagate(new MbMessageAssembly(input, response));
    }
}
