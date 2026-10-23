/** Query 14*/
public class GetMinIDItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetMinIDItemReadings_JavaCompute() {
        super("getMinIDItemReadings",
            "SELECT MODOWNITEM_ID "
            + "FROM MODOWNITEM_FATURA "
            + "WHERE ZZIMP = 0 "
            + "ORDER BY modownitem_id "
            + "FETCH FIRST 1 ROW ONLY",
            "{OSHEEPolicies}:MobiReadDatabase", "min",
            new String[] {},
            new String[][] {{"MODOWNITEM_ID", "minItemId", "integer"}});
    }
}
