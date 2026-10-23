/** Query 29*/
public class GetFeeders_JavaCompute extends MobiReadJdbcOperation {
    public GetFeeders_JavaCompute() {
        super("getFeeders",
            "SELECT "
            + "0 as lovcabin_id, "
            + "l.numbering_line as id_cabin_billing, "
            + "i.town as zona, "
            + "'' as nenzona, "
            + "'' as parent, "
            + "'' as parent_serial, "
            + "l.ref_substation as id_node_substation, "
            + "l.instcode as id_instance, "
            + "l.name_line as node_name, "
            + "l.nr_electricitymeter as serial_number, "
            + "'' as node_code, "
            + "'' as description, "
            + "l.validity as status, "
            + "l.coefficient as koeficienti_matesit, "
            + "0 as parent_id, "
            + "1 as active "
            + "FROM MR.LOVLINE_FATURA l "
            + "INNER JOIN MR.LOVINSTANCES i ON l.INSTCODE = i.INSTCODE "
            + "WHERE l.validity = 1 AND l.type = 1",
            "{OSHEEPolicies}:MobiReadDatabase", "array",
            new String[] {},
            new String[][] {
        	{"LOVCABIN_ID", "lovcabinId", "integer"}, 
        	{"ID_CABIN_BILLING", "cabinBillingId", "integer"}, 
        	{"ZONA", "zone", "string"}, 
        	{"NENZONA", "subZone", "string"}, 
        	{"PARENT", "parent", "string"}, 
        	{"PARENT_SERIAL", "parentSerial", "string"}, 
        	{"ID_NODE_SUBSTATION", "nodeSubstationId", "string"}, 
        	{"ID_INSTANCE", "instanceId", "string"}, 
        	{"NODE_NAME", "nodeName", "string"}, 
        	{"SERIAL_NUMBER", "serialNumber", "string"}, 
        	{"NODE_CODE", "nodeCode", "string"}, 
        	{"DESCRIPTION", "description", "string"}, 
        	{"STATUS", "status", "number"}, 
        	{"KOEFICIENTI_MATESIT", "meterCoefficient", "number"}, 
        	{"PARENT_ID", "parentId", "integer"}, 
        	{"ACTIVE", "active", "integer"}});
    }
}
