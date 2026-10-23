/** Query 23*/
public class GetZones_JavaCompute extends MobiReadJdbcOperation {
    public GetZones_JavaCompute() {
        super("getZones",
            "SELECT zona, "
            + "pershkrimi || ' Zone' pershkrimi, "
            + "ZONE_CHILD\nFROM ( "
            + "SELECT mz.BILLING, "
            + "mz.MYAVIS, "
            + "z.TOWN zona, "
            + "z.TOWN pershkrimi, "
            + "z.SPORTELI, "
            + "0 ZONE_CHILD "
            + "FROM MYAVIS_ZONA mz "
            + "LEFT OUTER JOIN LOVZONA z ON mz.BILLING = (z.MYAVIS_DB_ID || z.NENZONA || ' ')) "
            + "GROUP BY zona, pershkrimi, ZONE_CHILD "
            + "ORDER BY zona, pershkrimi, ZONE_CHILD",
            "{OSHEEPolicies}:MobiReadDatabase", "array",
            new String[] {},
            new String[][] {
        	{"ZONA", "zone", "string"}, 
        	{"PERSHKRIMI", "description", "string"}, 
        	{"ZONE_CHILD", "zoneChild", "integer"}});
    }
}
