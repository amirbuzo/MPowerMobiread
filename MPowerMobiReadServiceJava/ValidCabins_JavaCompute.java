/** Query 39*/
public class ValidCabins_JavaCompute extends MobiReadJdbcOperation {
    public ValidCabins_JavaCompute() {
        super("validCabins",
            "SELECT COUNT(DISTINCT c.numbering_cabin) CNT "
            + "FROM ${schemaName}.LOVCABIN_FATURA c "
            + "INNER JOIN ${schemaName}.LOVINSTANCES i ON c.INSTCODE = i.INSTCODE "
            + "INNER JOIN ${schemaName}.LOVLINE_FATURA l ON c.REF_LINE = l.NUMBERING_LINE "
            + "INNER JOIN ${schemaName}.MYAVIS_ZONA mv ON mv.BILLING = (i.MYAVIS_DB_ID || c.REF_ZONE || ' ') "
            + "WHERE c.validity = 1 AND c.type = ? AND l.type = ? AND l.validity = 1",
            "{MPowerMobiReadPolicies}:MobiReadDatabase", "count",
            new String[] {"typeNr", "typeNr"},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
