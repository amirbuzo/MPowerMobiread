/** Query 17 from MOBIREAD Technical Analysis. SQL predicates/projections are preserved. */
public class GetItemReadings_JavaCompute extends MobiReadJdbcOperation {
    public GetItemReadings_JavaCompute() {
        super("getItemReadings",
            "SELECT    "
            + "code_contract,    "
            + "MODOWNHEADER_ID id_customer,    "
            + "SERGE meter_code,    "
            + "HAS_INDEX_AKTIV,    "
            + "HAS_INDEX_REAKTIV,    "
            + "HAS_INDEX_AMBJ_PERBASHKET,    "
            + "NVL(INDEX_AKTIV_LOWER, 0) INDEX_AKTIV_LOWER,    "
            + "NVL(INDEX_AKTIV_UPPER, 0) INDEX_AKTIV_UPPER,    "
            + "NVL(INDEX_AKTIV_PREV, 0) INDEX_AKTIV_PREV,    "
            + "NVL(INDEX_REAKTIV_LOWER, 0) INDEX_REAKTIV_LOWER,    "
            + "NVL(INDEX_REAKTIV_UPPER, 0) INDEX_REAKTIV_UPPER,    "
            + "NVL(INDEX_REAKTIV_PREV, 0) INDEX_REAKTIV_PREV,    "
            + "NVL(INDEX_AMBJ_PERBASHKET_LOWER, 0) INDEX_AMBJ_PERBASHKET_LOWER,    "
            + "NVL(INDEX_AMBJ_PERBASHKET_UPPER, 0) INDEX_AMBJ_PERBASHKET_UPPER,    "
            + "NVL(INDEX_AMBJ_PERBASHKET_PREV, 0) INDEX_AMBJ_PERBASHKET_PREV,    "
            + "NVL(HAS_INDEX_PIK, 0) HAS_INDEX_PIK,    "
            + "NVL(INDEX_PIK_LOWER, 0) INDEX_PIK_LOWER,    "
            + "NVL(INDEX_PIK_UPPER, 0) INDEX_PIK_UPPER,    "
            + "NVL(INDEX_PIK_PREV, 0) INDEX_PIK_PREV,    "
            + "MODOWNHEADER_ID ID_ITEMDATAENTRY_BILLING,    "
            + "NVL(HAS_INDEX_SPROD, 0) HAS_INDEX_SPROD,    "
            + "NVL(INDEX_SPROD_PREV, 0) INDEX_SPROD_PREV,    "
            + "NVL(INDEX_SPROD_UPPER, 0) INDEX_SPROD_UPPER,    "
            + "NVL(INDEX_SPROD_LOWER, 0) INDEX_SPROD_LOWER,    "
            + "NVL(INCORRECT_CHARGING, 0) INCORRECT_CHARGING "
            + "FROM MR.MODOWNHEADER_FATURA_NEW "
            + "WHERE zzimp = 1 AND ZZIMP1 = 0 "
            + "ORDER BY CODE_CONTRACT, TIPI_LEXIMIT "
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
        	{"HAS_INDEX_SPROD", "hasIndexSprod", "number"}, 
        	{"INDEX_SPROD_PREV", "indexSprodPrev", "number"}, 
        	{"INDEX_SPROD_UPPER", "indexSprodUpper", "number"}, 
        	{"INDEX_SPROD_LOWER", "indexSprodLower", "number"}, 
        	{"INCORRECT_CHARGING", "incorrectCharging", "number"}});
    }
}
