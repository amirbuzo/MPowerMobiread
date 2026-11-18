/** Query 3*/
public class GetTotalFeedHecNenstCustomers_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalFeedHecNenstCustomers_JavaCompute() {
        super("getTotalFeedHecNenstCustomers",
            "SELECT "
            + "count(h.MODOWNHEADER_ID) AS TOTAL "
            + "FROM ${schemaName}.MODOWNHEADER_F_H_N_NEW H "
            + "WHERE h.zzimp = 0",
            "{MPowerMobiReadPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
