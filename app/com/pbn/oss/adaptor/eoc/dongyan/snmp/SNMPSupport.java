package com.pbn.oss.adaptor.eoc.dongyan.snmp;

import java.util.List;
import java.util.Map;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

public interface SNMPSupport{
	void initSnmp(SNMPConfig config) throws Exception;
	int getSnmpVersion();
	String getReadCommunity();
	String getWriteCommunity();
	void closeSnmp();
	
	VariableBinding getVariableBinding(String oid, int pduType) throws Exception;
	
	String getOneStringValue(String oid, int pduType) throws Exception;
	int getOneIntValue(String oid, int pduType) throws Exception;
	long getOneLongValue(String oid, int pduType) throws Exception;
	
	List<List<VariableBinding>> getTableValues(OID[] oids) throws Exception;
	List<VariableBinding> getGroupValues(OID[] oids, int pduType) throws Exception;
	Map<String,String> getBulk(OID oid) throws Exception;
	
	void set(String oid, Variable v);
	Map<String,String> getBulk(OID oid,String operation) throws Exception;
	OID getOIDIndex(OID oid, String index);
}
