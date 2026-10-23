/** Query 11*/
public class GetTotalConvergeItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalConvergeItemReadings_JavaCompute() {
        super("getTotalConvergeItemReadings",
            "SELECT COUNT(MODOWNHEADER_ID) as TOTAL "
            + "FROM MR.MODOWNHEADER_CONVERGE_NEW "
            + "WHERE zzimp = 1 AND ZZIMP1 = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
