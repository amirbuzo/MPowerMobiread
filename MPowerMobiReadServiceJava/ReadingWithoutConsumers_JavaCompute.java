/** Query 44*/
public class ReadingWithoutConsumers_JavaCompute extends MobiReadJdbcOperation {
    public ReadingWithoutConsumers_JavaCompute() {
        super("readingWithoutConsumers",
            "SELECT COUNT(MODOWNHEADER_ID) as CNT "
            + "FROM ${schemaName}.${tableName} "
            + "WHERE zzimp1 = 0 AND zzimp != 0",
            "{MPowerMobiReadPolicies}:MobiReadDatabase", "count",
            new String[] {},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
