/** Query 47*/
public class GetWrongRead_JavaCompute extends MobiReadJdbcOperation {
    public GetWrongRead_JavaCompute() {
        super("getWrongRead",
            "SELECT COUNT(*) CNT "
            + "FROM ${schemaName}.${tableName} H "
            + "WHERE h.zzimp = 0 AND zzimp1 = 0 "
            + "AND (h.ADATSOLL < '19990101' OR h.ADATSOLL > TO_CHAR(SYSDATE, 'YYYYMMDD'))",
            "{MPowerMobiReadPolicies}:MobiReadDatabase", "count",
            new String[] {},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
