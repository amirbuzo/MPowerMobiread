/** Query 39*/
public class ValidCabins_JavaCompute extends MobiReadJdbcOperation {
    public ValidCabins_JavaCompute() {
        super("validCabins",
            "SELECT COUNT(DISTINCT c.numbering_cabin) CNT "
            + "FROM MR.LOVCABIN_FATURA c "
            + "INNER JOIN MR.LOVINSTANCES i ON c.INSTCODE = i.INSTCODE "
            + "INNER JOIN MR.LOVLINE_FATURA l ON c.REF_LINE = l.NUMBERING_LINE "
            + "INNER JOIN MR.MYAVIS_ZONA mv ON mv.BILLING = (i.MYAVIS_DB_ID || c.REF_ZONE || ' ') "
            + "WHERE c.validity = 1 AND c.type = ? AND l.type = ? AND l.validity = 1",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {"typeNr", "typeNr"},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
