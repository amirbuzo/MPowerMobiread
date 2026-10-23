/** Query 4*/
public class GetTotalSpecialCustomers_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalSpecialCustomers_JavaCompute() {
        super("getTotalSpecialCustomers",
            "SELECT "
            + "count(h.MODOWNHEADER_ID) AS TOTAL "
            + "FROM MR.MODOWNHEADER_F_H_N_NEW H "
            + "WHERE h.zzimp = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
