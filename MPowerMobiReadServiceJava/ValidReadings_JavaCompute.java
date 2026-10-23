/** Query 41*/
public class ValidReadings_JavaCompute extends MobiReadJdbcOperation {
    public ValidReadings_JavaCompute() {
        super("validReadings",
            "SELECT COUNT(DISTINCT h.modownheader_ID) CNT "
            + "FROM MR.${tableName} H "
            + "WHERE h.zzimp1 = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
