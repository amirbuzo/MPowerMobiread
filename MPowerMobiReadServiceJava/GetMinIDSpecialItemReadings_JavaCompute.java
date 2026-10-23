/** Query 16*/
public class GetMinIDSpecialItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetMinIDSpecialItemReadings_JavaCompute() {
        super("getMinIDSpecialItemReadings",
            "SELECT MODOWNITEM_ID "
            + "FROM MODOWNITEM_FIDERI16 "
            + "WHERE ZZIMP = 0 "
            + "ORDER BY modownitem_id "
            + "FETCH FIRST 1 ROW ONLY",
            "{OSHEEPolicies}:MobiReadDatabase", "min",
            new String[] {},
            new String[][] {{"MODOWNITEM_ID", "minItemId", "integer"}});
    }
}
