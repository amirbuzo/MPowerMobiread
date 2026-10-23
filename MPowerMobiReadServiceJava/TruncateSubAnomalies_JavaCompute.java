/** Query 27*/
public class TruncateSubAnomalies_JavaCompute extends MobiReadJdbcOperation {
    public TruncateSubAnomalies_JavaCompute() {
        super("truncateSubAnomalies",
            "TRUNCATE TABLE OSSH.LISTE_ANOMALI_SUBANOMALI",
            "{OSHEEPolicies}:MobiReadOSSHDatabase", "truncate",
            new String[] {},
            new String[][] {});
    }
}
