/** Query 3*/
public class GetTotalFeedHecNenstCustomers_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalFeedHecNenstCustomers_JavaCompute() {
        super("getTotalFeedHecNenstCustomers",
            "SELECT "
            + "count(h.MODOWNHEADER_ID) AS TOTAL "
            + "FROM MR.MODOWNHEADER_F_H_N_NEW H "
            + "WHERE h.zzimp = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
