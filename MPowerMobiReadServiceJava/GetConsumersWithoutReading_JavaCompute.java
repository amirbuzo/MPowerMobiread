/** Query 46*/
public class GetConsumersWithoutReading_JavaCompute extends MobiReadJdbcOperation {
    public GetConsumersWithoutReading_JavaCompute() {
        super("getConsumersWithoutReading",
            "SELECT COUNT(1) CNT "
            + "FROM MR.${tableName} h "
            + "WHERE h.ZZIMP = 0 "
            + "AND h.CODE_CONTRACT NOT IN ( "
            + "SELECT CODE_CONTRACT FROM MR.${tableName} WHERE zzimp1 = 0)",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
