/** Query 7*/
public class GetFeedHecNenstCustomers_JavaCompute extends MobiReadJdbcOperation {
    public GetFeedHecNenstCustomers_JavaCompute() {
        super("getFeedHecNenstCustomers",
            "SELECT  "
            + "name1_text customer_full_name, "
            + "MODOWNHEADER_ID customer_id, "
            + "city1 city, "
            + "street street, "
            + "house_num1 apartment,  "
            + "entrance entrance,  "
            + "tariftyp tariff_type, "
            + "SUBSTR(ableinh, 3, LENGTH(ableinh)) ref_cabin, "
            + "termtext node_name,    "
            + "adatsoll previous_reading_date,    "
            + "code_contract contract_code,    "
            + "faze meter_type,    "
            + "serge meter_code,    "
            + "NVL(konstanta_meridla, 0) customer_rate,    "
            + "NVL(fotomand, 0) fotomand,    "
            + "ZZGPS_LATITUDE,    "
            + "ZZGPS_LONGITUDE,    "
            + "KATEGORIA,    "
            + "NENKATEGORIA "
            + "FROM MR.MODOWNHEADER_F_H_N_NEW H "
            + "WHERE zzimp = 0 "
            + "ORDER BY code_contract "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
            "{OSHEEPolicies}:MobiReadDatabase", "page",
            new String[] {"offset", "limit"},
            new String[][] {
        	{"CUSTOMER_FULL_NAME", "customerFullName", "string"}, 
        	{"CUSTOMER_ID", "customerId", "integer"}, 
        	{"CITY", "city", "string"}, 
        	{"STREET", "street", "string"}, 
        	{"APARTMENT", "apartment", "string"}, 
        	{"ENTRANCE", "entrance", "string"}, 
        	{"TARIFF_TYPE", "tariffType", "string"}, 
        	{"REF_CABIN", "refCabin", "string"}, 
        	{"NODE_NAME", "nodeName", "string"}, 
        	{"PREVIOUS_READING_DATE", "previousReadingDate", "string"}, 
        	{"CONTRACT_CODE", "contractCode", "string"}, 
        	{"METER_TYPE", "meterType", "string"}, 
        	{"METER_CODE", "meterCode", "string"}, 
        	{"CUSTOMER_RATE", "customerRate", "number"}, 
        	{"FOTOMAND", "fotomand", "number"}, 
        	{"ZZGPS_LATITUDE", "gpsLatitude", "string"}, 
        	{"ZZGPS_LONGITUDE", "gpsLongitude", "string"}, 
        	{"KATEGORIA", "category", "string"}, 
        	{"NENKATEGORIA", "subcategory", "string"}});
    }
}
