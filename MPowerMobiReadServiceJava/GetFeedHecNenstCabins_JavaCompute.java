/** Query 35*/
public class GetFeedHecNenstCabins_JavaCompute extends MobiReadJdbcOperation {
    public GetFeedHecNenstCabins_JavaCompute() {
        super("getFeedHecNenstCabins",
            "SELECT "
            + "DISTINCT    0 as lovcabin_id, "
            + "c.numbering_cabin as id_cabin_billing, "
            + "i.town as zona, "
            + "mv.MYAVIS as nenzona, "
            + "l.name_line as parent,  "
            + "l.nr_electricitymeter as parent_serial, "
            + "l.NUMBERING_LINE as parent_id, "
            + "c.ref_line as id_node_line, "
            + "c.instcode as id_instance, "
            + "c.cabin_name as node_name, "
            + "c.nr_electricitymeter as serial_number, "
            + "c.code_cabin as node_code, "
            + "'' as description, "
            + "c.validity as active, "
            + "c.coefficient as koeficienti_matesit "
            + "FROM MR.LOVCABIN_FATURA c "
            + "INNER JOIN MR.LOVINSTANCES i ON c.INSTCODE = i.INSTCODE "
            + "INNER JOIN MR.LOVLINE_FATURA l ON c.REF_LINE = l.NUMBERING_LINE "
            + "INNER JOIN MR.MYAVIS_ZONA mv ON mv.BILLING = (i.MYAVIS_DB_ID || c.REF_ZONE || ' ') "
            + "WHERE c.validity = 1 AND c.type = 3 AND l.type = 3 AND l.validity = 1 AND c.zzimp = 0 AND l.zzimp = 0",
            "{OSHEEPolicies}:MobiReadDatabase", "array",
            new String[] {},
            new String[][] {
        	{"LOVCABIN_ID", "lovcabinId", "integer"}, 
        	{"ID_CABIN_BILLING", "cabinBillingId", "integer"}, 
        	{"ZONA", "zone", "string"}, 
        	{"NENZONA", "subZone", "string"}, 
        	{"PARENT", "parent", "string"}, 
        	{"PARENT_SERIAL", "parentSerial", "string"}, 
        	{"PARENT_ID", "parentId", "integer"}, 
        	{"ID_NODE_LINE", "nodeLineId", "integer"}, 
        	{"ID_INSTANCE", "instanceId", "string"}, 
        	{"NODE_NAME", "nodeName", "string"}, 
        	{"SERIAL_NUMBER", "serialNumber", "string"}, 
        	{"NODE_CODE", "nodeCode", "string"}, 
        	{"DESCRIPTION", "description", "string"}, 
        	{"ACTIVE", "active", "number"}, 
        	{"KOEFICIENTI_MATESIT", "meterCoefficient", "number"}});
    }
}
