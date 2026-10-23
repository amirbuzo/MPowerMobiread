/** Query 38*/
public class ValidFeeders_JavaCompute extends MobiReadJdbcOperation {
    public ValidFeeders_JavaCompute() {
        super("validFeeders",
            "SELECT COUNT(1) CNT "
            + "FROM MR.LOVLINE_FATURA l "
            + "INNER JOIN MR.LOVINSTANCES i ON l.INSTCODE = i.INSTCODE "
            + "WHERE l.validity = 1 AND l.type = ?",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {"typeNr"},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
