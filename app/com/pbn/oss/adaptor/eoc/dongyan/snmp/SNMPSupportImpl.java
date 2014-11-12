package com.pbn.oss.adaptor.eoc.dongyan.snmp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Counter64;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.SMIConstants;
import org.snmp4j.smi.UnsignedInteger32;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

public class SNMPSupportImpl implements SNMPSupport {
	private Snmp snmp;
	private SNMPConfig config;
	private PDU reqPdu;
	private PDU respPdu;
	private CommunityTarget getTarget;
	private CommunityTarget setTarget;
	
	public void setParameter(String oid, int value,boolean flag) throws Exception {
		if(oid == null || oid.length() == 0) {
//			logger.error("oid or value is null!");
			throw new Exception("oid or value is null!");
		}
		PDU pdu = new PDU();
		pdu.setType(PDU.SET);

		//request.add(new VariableBinding(new OID(strOid), new OctetString(value))); 
		if(flag){
			pdu.add(new VariableBinding(new OID(oid), new Integer32(value)));
		}else{
			pdu.add(new VariableBinding(new OID(oid), new UnsignedInteger32(value)));
		}
		
		
		ResponseEvent responseEvent = snmp.send(pdu, setTarget);
		PDU response = responseEvent.getResponse();
//		logger.info(response);
		if(response == null) {
			throw new Exception("setParameter() : snmp connect time out!");
		}
		else if(response.getErrorStatus() == PDU.noError) {
//			logger.info("setParameter : snmp set message has been set successfully");
			System.out.println("setParameter :" + oid + " snmp set message has been set successfully");
		}
		else {
			
//			logger.error("setParameter() set [" + oid + "] value [" + value + "] failure!");
			throw new Exception("setParameter() set [" + oid + "] value [" + value + "] failure!");
		}
 
	}
	
	public void setParameter(String oid, String value, boolean stringFlag) throws Exception {

		if(oid == null || oid.length() == 0) {
			throw new Exception("oid or value is null!");
		}
		PDU pdu = new PDU();
		pdu.setType(PDU.SET);
		if(stringFlag==true){
			pdu.add(new VariableBinding(new OID(oid),new OctetString(value)));
		}else{
			pdu.add(new VariableBinding(new OID(oid),new IpAddress(value)));
		}
		
//		logger.info("target.getRetries()"+target.getRetries());
//		logger.info("target.getCommunity()"+target.getCommunity());
		ResponseEvent responseEvent = snmp.send(pdu, setTarget);
		PDU response = responseEvent.getResponse();
//		logger.info(response);
		if(response == null) {
			throw new Exception("setParameter() : snmp connect time out!");
		}
		else if(response.getErrorStatus() == PDU.noError) {
//			logger.info("setParameter : snmp set message has been set successfully");
 
		}
		else {
//			logger.error("setParameter() set [" + oid + "] value [" + value + "] failure!");
			throw new Exception("setParameter() set [" + oid + "] value [" + value + "] failure!");
		}
 
	
		
	}

	@Override
	public void initSnmp(SNMPConfig config) {
		this.config = config;
		try {
			TransportMapping transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			snmp.listen();
			Address targetAddress = GenericAddress.parse("udp:" + config.getAgentIp() + "/" + config.getSnmpPort());
			getTarget = buidTarget(config.getReadCommunity(), targetAddress);
			setTarget = buidTarget(config.getWriteCommunity(), targetAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private CommunityTarget buidTarget(String community, Address targetAddress){
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(community));
		target.setAddress(targetAddress);
		target.setRetries(config.getRetries());
		target.setTimeout(config.getTimeout());
		target.setVersion(config.getSnmpVer());
		return target;
	}
	
	@Override
	public void closeSnmp() {
		try {
			if (snmp != null) {
				snmp.close();
				snmp = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getOneIntValue(String oid, int pduType) throws Exception{
		return Integer.valueOf(getOneStringValue(oid, pduType));
	}
	
	@Override
	public long getOneLongValue(String oid, int pduType) throws Exception {
		return Long.valueOf(getOneStringValue(oid, pduType));
	}
	
	
	public OID getOIDIndex(OID oid, String index)
	{
		OID oidIndex = new OID(oid.toString().concat(index));
		return oidIndex;
	}
	
	@Override
	public String getOneStringValue(String oid, int pduType) throws Exception{
		if(oid == null || oid.length() == 0) {
			throw new Exception("oid is null!");
		}
//		System.out.println("********Enter getOneStringValue: oid=["+oid+"],pduType="+pduType);
		reqPdu = new PDU();
		reqPdu.add(new VariableBinding(new OID(oid)));
		reqPdu.setType(pduType);
		ResponseEvent response = snmp.send(reqPdu, getTarget);
		if (response != null && response.getResponse() != null) {
			respPdu = response.getResponse();
//			System.out.println("***respPdu:"+respPdu);
			VariableBinding varBinding = respPdu.get(0);
			String value = getValueString(varBinding.getVariable());
//			System.out.println("********value="+value+"("+getValueType(varBinding.getVariable())+")");
			return value;
//			if(!"noSuchInstance".equalsIgnoreCase(value)){
//				System.out.println("***return value="+value);
//				return value;
//			}else
//				throw new Exception("noSuchInstance! oid:"+oid);
		}
		return null;
	}
	

	@Override
	public List<List<VariableBinding>> getTableValues(OID[] oids) throws Exception {
		if(oids == null || oids.length == 0) {
			throw new Exception("oid is null!");
		}
		PDUFactory pf = new DefaultPDUFactory(PDU.GET);
		TableUtils tu = new TableUtils(snmp, pf);
		@SuppressWarnings("unchecked")
		List<TableEvent> list = tu.getTable(getTarget, oids, null, null);
		List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>(list.size());
		for(int i = 0; i < list.size(); i++){
			TableEvent te = list.get(i);
			VariableBinding[] vb = te.getColumns();
			List<VariableBinding> temp = new ArrayList<VariableBinding>(oids.length);
			for(int j = 0; j < vb.length; j++){
				temp.add(vb[j]);
			}
//			System.out.println("List<VariableBinding>:"+temp);
			vbs.add(temp);
		}
		return vbs;
	}

	@Override
	public List<VariableBinding> getGroupValues(OID[] oids,int pduType) throws Exception {
		if(oids == null || oids.length == 0) {
			throw new Exception("oids is null!");
		}
		reqPdu = new PDU();
		reqPdu.setType(pduType);
		for(OID o : oids){
			reqPdu.add(new VariableBinding(new OID(o)));
		}
		List<VariableBinding> list = new ArrayList<VariableBinding>();
		try {
			ResponseEvent response = snmp.send(reqPdu, getTarget);
			if (response != null && (respPdu = response.getResponse()) != null) {
				for (int i = 0; i < respPdu.size(); i++) {
					list.add(respPdu.get(i));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Map<String,String> getBulk(OID oid,String operation) throws Exception{
		Map<String,String> values = new HashMap<String,String>();
		OID temp = new OID(oid);
		int index=0;
		if(operation.equals("getSubPortTable")){
			index=8;
		}else if(operation.equals("getSubTable")){
			index=2;
		}
		
			while(temp.startsWith(oid)&&index>0){
				VariableBinding varBinding = getVariableBinding(temp.toString(), PDU.GETNEXT);
				temp = varBinding.getOid();
				if(temp.startsWith(oid)){
					String key = varBinding.getOid().toString().replace(oid.toString(), "").trim();
					String value = varBinding.getVariable().toString();
					values.put(key, value);
					System.out.print("<<<<<<<<<<<<:::key"+key+":::>>>>>>>>>>>>\n");
					System.out.print("<<<<<<<<<<<<:::value"+value+":::>>>>>>>>>>>>\n");
					key=null;
					value = null;
				}
				index--;
			}
		
		
		return values;
	}
	
	@Override
	public Map<String,String> getBulk(OID oid) throws Exception{
		Map<String,String> values = new TreeMap<String,String>();
		OID temp = new OID(oid);
		while(temp.startsWith(oid)){
			VariableBinding varBinding = getVariableBinding(temp.toString(), PDU.GETNEXT);
			temp = varBinding.getOid();
			if(temp.startsWith(oid)){
				values.put(varBinding.getOid().toString(), varBinding.getVariable().toString());
			}
		}
		return values;
	}
	
	public VariableBinding getVariableBinding(String oid, int pduType) throws Exception{
		if(oid == null || oid.length() == 0) {
			throw new Exception("oid is null!");
		}
		reqPdu = new PDU();
		reqPdu.add(new VariableBinding(new OID(oid)));
		reqPdu.setType(pduType);
		try {
			ResponseEvent response = snmp.send(reqPdu, getTarget);
			if (response != null && response.getResponse() != null) {
				respPdu = response.getResponse();
//				System.out.println("respPdu:"+respPdu.getType());
				VariableBinding varBinding = respPdu.get(0);
//				System.out.println(varBinding.toString()+" --syntax: " + varBinding.getSyntax());
//				String value = varBinding.getVariable().toString();
				int smi = varBinding.getSyntax();
				if(smi != SMIConstants.EXCEPTION_END_OF_MIB_VIEW && smi != SMIConstants.EXCEPTION_NO_SUCH_INSTANCE && smi != SMIConstants.EXCEPTION_NO_SUCH_OBJECT)
//				if(!"noSuchInstance".equalsIgnoreCase(value))
					return varBinding;
				else
					throw new Exception("noSuchInstance! oid="+oid);
			}else{
				throw new Exception("No Response! oid="+oid);
			}
		} catch (IOException e) {
			e.printStackTrace();
//			throw new Exception("getStringParameter : perhaps snmp connect time out!");
		}
		return null;
	}
	
	public static String getValueType(Variable v){
		int syntax = v.getSyntax();
		switch(syntax){
			case SMIConstants.SYNTAX_COUNTER32:
				return "Counter32";
			case SMIConstants.SYNTAX_COUNTER64:
				return "Counter64";
			case SMIConstants.SYNTAX_INTEGER32:
				return "Integer";
//			case SMIConstants.SYNTAX_UNSIGNED_INTEGER32:
//				return "UnsignedInteger";
			case SMIConstants.SYNTAX_GAUGE32:
				return "Gauge";
			case SMIConstants.SYNTAX_TIMETICKS:
				return "TimeTicks";
			case SMIConstants.SYNTAX_OBJECT_IDENTIFIER:
				return "OID";
			case SMIConstants.SYNTAX_IPADDRESS:
				return "IpAddress";
			case SMIConstants.SYNTAX_OCTET_STRING:
				return "OctetString";
			default:
				return "OctetString";
		}
	}
	
	public static String getValueString(Variable v){
		int syntax = v.getSyntax();
		switch(syntax){
			case SMIConstants.SYNTAX_INTEGER32:
				return v.toInt()+"";
			case SMIConstants.SYNTAX_GAUGE32:
			case SMIConstants.SYNTAX_TIMETICKS:
			case SMIConstants.SYNTAX_COUNTER32:
			case SMIConstants.SYNTAX_COUNTER64:
				return v.toLong()+"";
			case SMIConstants.SYNTAX_OBJECT_IDENTIFIER:
			case SMIConstants.SYNTAX_IPADDRESS:
			case SMIConstants.SYNTAX_OCTET_STRING:
				return v.toString();
			default:
				return v.toString();
		}
	}

	@Override
	public void set(String oid, Variable v) {
		try {
			PDU pdu = new PDU();
			pdu.add(new VariableBinding(new OID(oid), v));
			pdu.setType(PDU.SET);
			ResponseEvent response = snmp.send(pdu, setTarget);
			if (response != null) {
				PDU respPdu = response.getResponse();
				if(respPdu == null){
					System.out.println("set value failed.");
					return;
				}
				for (int i = 0; i < respPdu.size(); i++) {
					VariableBinding varBinding = respPdu.get(i);
					System.out.println(varBinding);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getReadCommunity() {
		return config.getReadCommunity();
	}

	@Override
	public int getSnmpVersion() {
		return config.getSnmpVer();
	}

	@Override
	public String getWriteCommunity() {
		return config.getWriteCommunity();
	}
	
}
