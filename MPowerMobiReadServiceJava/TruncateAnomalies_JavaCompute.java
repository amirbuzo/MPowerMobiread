/** Query 25*/
public class TruncateAnomalies_JavaCompute extends MobiReadJdbcOperation {
    public TruncateAnomalies_JavaCompute() {
        super("truncateAnomalies",
            "TRUNCATE TABLE OSSH.MYAVIS_ANOMAL",
            "{OSHEEPolicies}:MobiReadOSSHDatabase", "truncate",
            new String[] {},
            new String[][] {});
    }
}
