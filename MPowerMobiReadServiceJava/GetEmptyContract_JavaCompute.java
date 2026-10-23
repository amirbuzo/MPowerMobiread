/** Query 48*/
public class GetEmptyContract_JavaCompute extends MobiReadJdbcOperation {
    public GetEmptyContract_JavaCompute() {
        super("getEmptyContract",
            "SELECT COUNT(*) CNT "
            + "FROM MR.${tableName} H "
            + "WHERE h.zzimp = 0 AND zzimp1 = 0 "
            + "AND (h.CODE_CONTRACT IS NULL OR h.CODE_CONTRACT = '')",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
