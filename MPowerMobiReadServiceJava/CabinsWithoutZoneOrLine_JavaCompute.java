/** Query 42*/
public class CabinsWithoutZoneOrLine_JavaCompute extends MobiReadJdbcOperation {
    public CabinsWithoutZoneOrLine_JavaCompute() {
        super("cabinsWithoutZoneOrLine",
            "SELECT COUNT(c.NUMBERING_CABIN) as CNT FROM ${schemaName}.LOVCABIN_FATURA c "
            + "INNER JOIN ${schemaName}.LOVINSTANCES i ON c.INSTCODE = i.INSTCODE "
            + "LEFT JOIN ${schemaName}.LOVLINE_FATURA l ON c.REF_LINE = l.NUMBERING_LINE AND l.type = ? "
            + "LEFT JOIN ${schemaName}.MYAVIS_ZONA mv ON mv.BILLING = (i.MYAVIS_DB_ID || c.REF_ZONE || ' ') "
            + "WHERE c.zzimp = 0 AND c.validity = 1 AND c.type = ? "
            + "AND (i.town IS NULL OR mv.MYAVIS IS NULL OR c.ref_line IS NULL)",
            "{MPowerMobiReadPolicies}:MobiReadDatabase", "count",
            new String[] {"typeNr", "typeNr"},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
