/** Query 24*/
public class GetSubZones_JavaCompute extends MobiReadJdbcOperation {
    public GetSubZones_JavaCompute() {
        super("getSubZones",
            "SELECT ZONA, "
            + "ZONE_CHILD, "
            + "PERSHKRIMI\nFROM ( "
            + "SELECT mz.BILLING, "
            + "mz.MYAVIS ZONE_CHILD, "
            + "z.TOWN ZONA, "
            + "mz.NAME_ZONE PERSHKRIMI "
            + "FROM MYAVIS_ZONA mz "
            + "LEFT OUTER JOIN LOVZONA z ON mz.BILLING = (z.MYAVIS_DB_ID || z.NENZONA || ' ')) "
            + "GROUP BY ZONA, ZONE_CHILD, PERSHKRIMI\nORDER BY ZONA, ZONE_CHILD, PERSHKRIMI",
            "{OSHEEPolicies}:MobiReadDatabase", "array",
            new String[] {},
            new String[][] {
        	{"ZONA", "zone", "string"}, 
        	{"ZONE_CHILD", "zoneChild", "string"}, 
        	{"PERSHKRIMI", "description", "string"}});
    }
}
