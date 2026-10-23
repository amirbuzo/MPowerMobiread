/** Query 42*/
public class CabinsWithoutZoneOrLine_JavaCompute extends MobiReadJdbcOperation {
    public CabinsWithoutZoneOrLine_JavaCompute() {
        super("cabinsWithoutZoneOrLine",
            "SELECT COUNT(c.NUMBERING_CABIN) as CNT FROM MR.LOVCABIN_FATURA c "
            + "INNER JOIN MR.LOVINSTANCES i ON c.INSTCODE = i.INSTCODE "
            + "LEFT JOIN MR.LOVLINE_FATURA l ON c.REF_LINE = l.NUMBERING_LINE AND l.type = ? "
            + "LEFT JOIN MR.MYAVIS_ZONA mv ON mv.BILLING = (i.MYAVIS_DB_ID || c.REF_ZONE || ' ') "
            + "WHERE c.zzimp = 0 AND c.validity = 1 AND c.type = ? "
            + "AND (i.town IS NULL OR mv.MYAVIS IS NULL OR c.ref_line IS NULL)",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {"typeNr", "typeNr"},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
