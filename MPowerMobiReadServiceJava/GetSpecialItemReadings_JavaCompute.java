/** Query 20*/
public class GetSpecialItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetSpecialItemReadings_JavaCompute() {
        super("getSpecialItemReadings",
            "SELECT "
            + "code_contract, "
            + "MODOWNHEADER_ID id_customer, "
            + "SERGE meter_code, "
            + "HAS_INDEX_AKTIV, "
            + "HAS_INDEX_REAKTIV, "
            + "HAS_INDEX_AMBJ_PERBASHKET, "
            + "NVL(INDEX_AKTIV_LOWER, 0) INDEX_AKTIV_LOWER, "
            + "NVL(INDEX_AKTIV_UPPER, 0) INDEX_AKTIV_UPPER, "
            + "NVL(INDEX_AKTIV_PREV, 0) INDEX_AKTIV_PREV, "
            + "NVL(INDEX_REAKTIV_LOWER, 0) INDEX_REAKTIV_LOWER, "
            + "NVL(INDEX_REAKTIV_UPPER, 0) INDEX_REAKTIV_UPPER, "
            + "NVL(INDEX_REAKTIV_PREV, 0) INDEX_REAKTIV_PREV, "
            + "NVL(INDEX_AMBJ_PERBASHKET_LOWER, 0) INDEX_AMBJ_PERBASHKET_LOWER, "
            + "NVL(INDEX_AMBJ_PERBASHKET_UPPER, 0) INDEX_AMBJ_PERBASHKET_UPPER, "
            + "NVL(INDEX_AMBJ_PERBASHKET_PREV, 0) INDEX_AMBJ_PERBASHKET_PREV, "
            + "NVL(HAS_INDEX_PIK, 0) HAS_INDEX_PIK, "
            + "NVL(INDEX_PIK_LOWER, 0) INDEX_PIK_LOWER, "
            + "NVL(INDEX_PIK_UPPER, 0) INDEX_PIK_UPPER, "
            + "NVL(INDEX_PIK_PREV, 0) INDEX_PIK_PREV, "
            + "MODOWNHEADER_ID ID_ITEMDATAENTRY_BILLING, "
            + "NVL(INCORRECT_CHARGING, 0) INCORRECT_CHARGING "
            + "FROM MR.MODOWNHEADER_FIDERI16_NEW "
            + "WHERE zzimp = 1 AND ZZIMP1 = 0 "
            + "ORDER BY MODOWNHEADER_ID "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
            "{OSHEEPolicies}:MobiReadDatabase", "page",
            new String[] {"offset", "limit"},
            new String[][] {
        	{"CODE_CONTRACT", "contractCode", "string"}, 
        	{"ID_CUSTOMER", "customerId", "integer"}, 
        	{"METER_CODE", "meterCode", "string"}, 
        	{"HAS_INDEX_AKTIV", "hasIndexAktiv", "number"}, 
        	{"HAS_INDEX_REAKTIV", "hasIndexReaktiv", "number"}, 
        	{"HAS_INDEX_AMBJ_PERBASHKET", "hasIndexAmbjPerbashket", "number"}, 
        	{"INDEX_AKTIV_LOWER", "indexAktivLower", "number"}, 
        	{"INDEX_AKTIV_UPPER", "indexAktivUpper", "number"}, 
        	{"INDEX_AKTIV_PREV", "indexAktivPrev", "number"},
        	{"INDEX_REAKTIV_LOWER", "indexReaktivLower", "number"}, 
        	{"INDEX_REAKTIV_UPPER", "indexReaktivUpper", "number"}, 
        	{"INDEX_REAKTIV_PREV", "indexReaktivPrev", "number"}, 
        	{"INDEX_AMBJ_PERBASHKET_LOWER", "indexAmbjPerbashketLower", "number"}, 
        	{"INDEX_AMBJ_PERBASHKET_UPPER", "indexAmbjPerbashketUpper", "number"}, 
        	{"INDEX_AMBJ_PERBASHKET_PREV", "indexAmbjPerbashketPrev", "number"}, 
        	{"HAS_INDEX_PIK", "hasIndexPik", "number"}, 
        	{"INDEX_PIK_LOWER", "indexPikLower", "number"}, 
        	{"INDEX_PIK_UPPER", "indexPikUpper", "number"}, 
        	{"INDEX_PIK_PREV", "indexPikPrev", "number"}, 
        	{"ID_ITEMDATAENTRY_BILLING", "itemDataEntryBillingId", "integer"}, 
        	{"INCORRECT_CHARGING", "incorrectCharging", "number"}});
    }
}
