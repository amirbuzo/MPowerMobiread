/** Query 15*/
public class GetMinIDFeedHecNenstItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetMinIDFeedHecNenstItemReadings_JavaCompute() {
        super("getMinIDFeedHecNenstItemReadings",
            "SELECT MODOWNITEM_ID "
            + "FROM MODOWNITEM_F_H_N_FATURA "	
            + "WHERE ZZIMP = 0 "
            + "ORDER BY modownitem_id "
            + "FETCH FIRST 1 ROW ONLY",
            "{OSHEEPolicies}:MobiReadDatabase", "min",
            new String[] {},
            new String[][] {{"MODOWNITEM_ID", "minItemId", "integer"}});
    }
}
