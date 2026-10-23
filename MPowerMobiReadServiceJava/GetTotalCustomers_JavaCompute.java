/** Query 1*/
public class GetTotalCustomers_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalCustomers_JavaCompute() {
        super("getTotalCustomers",
            "SELECT count(h.MODOWNHEADER_ID) AS TOTAL FROM MR.MODOWNHEADER_FATURA_NEW h WHERE h.zzimp = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
