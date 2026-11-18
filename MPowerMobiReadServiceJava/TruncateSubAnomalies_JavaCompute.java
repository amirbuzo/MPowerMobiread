/** Query 27*/
public class TruncateSubAnomalies_JavaCompute extends MobiReadJdbcOperation {
    public TruncateSubAnomalies_JavaCompute() {
        super("truncateSubAnomalies",
            "TRUNCATE TABLE ${schemaName}.LISTE_ANOMALI_SUBANOMALI",
            "{MPowerMobiReadPolicies}:MobiReadOSSHDatabase", "truncate",
            new String[] {},
            new String[][] {});
    }
}
