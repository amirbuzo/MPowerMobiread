/** Query 2*/
public class GetTotalConvergeCustomers_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalConvergeCustomers_JavaCompute() {
        super("getTotalConvergeCustomers",
            "SELECT "
            + "count(h.MODOWNHEADER_ID) AS TOTAL "
            + "FROM ${schemaName}.MODOWNHEADER_CONVERGE_NEW H "
            + "WHERE h.zzimp = 0",
            "{MPowerMobiReadPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
