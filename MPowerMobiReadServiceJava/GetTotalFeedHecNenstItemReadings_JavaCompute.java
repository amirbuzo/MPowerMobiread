/** Query 12*/
public class GetTotalFeedHecNenstItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetTotalFeedHecNenstItemReadings_JavaCompute() {
        super("getTotalFeedHecNenstItemReadings",
            "SELECT COUNT(MODOWNHEADER_ID) as TOTAL "
            + "FROM MR.MODOWNHEADER_F_H_N_NEW "
            + "WHERE zzimp = 1 AND ZZIMP1 = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "total",
            new String[] {},
            new String[][] {{"TOTAL", "total", "integer"}});
    }
}
