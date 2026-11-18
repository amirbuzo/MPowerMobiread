/** Query 16*/
public class GetMinIDSpecialItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetMinIDSpecialItemReadings_JavaCompute() {
        super("getMinIDSpecialItemReadings",
            "SELECT MODOWNITEM_ID "
            + "FROM ${schemaName}.MODOWNITEM_FIDERI16 "
            + "WHERE ZZIMP = 0 "
            + "ORDER BY modownitem_id "
            + "FETCH FIRST 1 ROW ONLY",
            "{MPowerMobiReadPolicies}:MobiReadDatabase", "min",
            new String[] {},
            new String[][] {{"MODOWNITEM_ID", "minItemId", "integer"}});
    }
}
