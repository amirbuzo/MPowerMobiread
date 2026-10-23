/** Query 13*/
public class GetTotalSpecialItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalSpecialItemReadings_JavaCompute() {
        super("getTotalSpecialItemReadings",
            "SELECT COUNT(MODOWNHEADER_ID) as TOTAL "
            + "FROM MR.MODOWNHEADER_FIDERI16_NEW "
            + "WHERE zzimp = 1 AND ZZIMP1 = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
