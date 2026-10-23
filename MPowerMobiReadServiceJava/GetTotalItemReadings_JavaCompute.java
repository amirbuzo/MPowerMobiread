/** Query 10*/
public class GetTotalItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalItemReadings_JavaCompute() {
        super("getTotalItemReadings",
            "SELECT COUNT(MODOWNHEADER_ID) as TOTAL "
            + "FROM MR.MODOWNHEADER_FATURA_NEW "
            + "WHERE zzimp = 1 AND ZZIMP1 = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
