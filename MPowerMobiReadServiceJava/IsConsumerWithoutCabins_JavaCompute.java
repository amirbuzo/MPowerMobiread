/** Query 43*/
public class IsConsumerWithoutCabins_JavaCompute extends MobiReadJdbcOperation {
    public IsConsumerWithoutCabins_JavaCompute() {
        super("isConsumerWithoutCabins",
            "SELECT COUNT(modownheader_id) as CNT "
            + "FROM MR.${tableName} H "
            + "WHERE h.zzimp = 0 AND h.zzimp1 = 0 "
            + "AND SUBSTR(h.ableinh, 3, LENGTH(h.ableinh)) NOT IN ( "
            + "SELECT numbering_cabin FROM MR.LOVCABIN_FATURA c "
            + "WHERE c.validity = 1 AND c.type = ? )",
            "{OSHEEPolicies}:MobiReadDatabase", "count",
            new String[] {"typeNr"},
            new String[][] {{"CNT", "count", "integer"}});
    }
}
