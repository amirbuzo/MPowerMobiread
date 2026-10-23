/** Query 45*/
public class ConsumerWithoutCabins_JavaCompute extends MobiReadJdbcOperation {
    public ConsumerWithoutCabins_JavaCompute() {
        super("consumerWithoutCabins",
            "SELECT COUNT(1) CNT "
            + "FROM MR.LOVCABIN_FATURA c "
            + "WHERE c.NUMBERING_CABIN NOT IN ( "
            + "SELECT SUBSTR(h.ableinh, 3, LENGTH(h.ableinh)) "
            + "FROM MR.${tableName} h "
            + "WHERE h.ZZIMP = 0) "
            + "AND c.ZZIMP = 0 AND c.validity = 1 AND c.type = ?",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {"typeNr"},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
