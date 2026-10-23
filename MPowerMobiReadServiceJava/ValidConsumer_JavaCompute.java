/** Query 40*/
public class ValidConsumer_JavaCompute extends MobiReadJdbcOperation {
    public ValidConsumer_JavaCompute() {
        super("validConsumer",
            "SELECT COUNT(DISTINCT h.modownheader_ID) CNT "
            + "FROM MR.${tableName} H "
            + "WHERE h.zzimp = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
