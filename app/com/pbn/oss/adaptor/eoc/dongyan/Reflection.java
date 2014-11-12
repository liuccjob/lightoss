package com.pbn.oss.adaptor.eoc.dongyan;

 

/**
* Copyright (c) 2011,  All rights reserved
* <p>Reflection
* <p> 
*   
* </p>
* @author   Zhijun Yu
* @version  1.00 2011/05/15    
*/

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.snmp4j.PDU;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable;
import com.pbn.oss.adaptor.eoc.bean.ResourceClassName;
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCBATCardInforTable;
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUServiceTable;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPConfig;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPSupport;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPSupportImpl;

 

 

public class Reflection {
	public static Map<String,SetParameter> setMap = new HashMap<String,SetParameter>();
	
	
	public Object invokeMethod(String className, String methodName,
			Object[] args) throws Exception {

		Class ownerClass = Class.forName(className);

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Method method = ownerClass.getMethod(methodName, argsClass);

		return method.invoke(null, args);
	}

	public Object newInstance(String className, Object[] args) throws Exception {
		Class newoneClass = Class.forName(className);

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Constructor cons = newoneClass.getConstructor(argsClass);

		return cons.newInstance(args);

	}


	
	public List<List<VariableBinding>> getValue(OID[] objs,
			SNMPSupportImpl support, String operation) throws Exception {
		if(operation.equalsIgnoreCase("getTable")) {
			return getTableValue(objs, support);
		}else{
			List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>();
			vbs.add(getGroupValue(objs, support));
			return vbs;
		}
	}
	
	
	public Map<String ,List<VariableBinding>> getValue(OID[] objs,
			SNMPSupportImpl support, String operation,String depoids) throws Exception {
		if(operation.equalsIgnoreCase("getSubTable")) {
			return getSubTableValue(objs, support,depoids);
		}
		else if(operation.equalsIgnoreCase("getSubPortTable")) {
			return getSubPortTableValue(objs, support,depoids);
		}
		return null;
	}
	
	public Map<String ,Integer> ModemsStatus(String ipAddress) throws Exception{
		SNMPSupport support = new SNMPSupportImpl();
		Map<String,Integer> modemsStatusMap = new HashMap<String,Integer>();
		try{
			//TODO: Check all CNU status
		}catch(Exception e){
			System.out.println("getModemsStatus throw an exception");
		}
		return null;
	}
	
	public Map<String ,List<VariableBinding>> getSubTableValue(OID[]objs,SNMPSupportImpl support,String depoids)throws Exception{
		 
		try{

			
			//Map<Integer,Map<String,String>> depOidValueMap = new HashMap<Integer,Map<String,String>>();
//			SNMPConfig cfg = new SNMPConfig();
//			cfg.setAgentIp(support);
//			support.initSnmp(cfg);
			
			Map<String,String> depOidValueMap = support.getBulk(new OID(depoids),"getSubTable");
			Set<String> oidIndexSet = depOidValueMap.keySet();
			
			Iterator<String> oidIndexIter = oidIndexSet.iterator();
			
			Map<String ,List<VariableBinding>> resultMap = new HashMap<String,List<VariableBinding>>();
			
			if(oidIndexIter==null){
				return null;
			}
			while(oidIndexIter.hasNext()){
				
				String key = (String) oidIndexIter.next();
				
				

				OID[] newOids = new OID[objs.length];
				for(int i =0;i<objs.length;i++){
					 
					OID newOid = support.getOIDIndex(objs[i], key);
					newOids[i]=newOid;
					 
				}
				resultMap.put(key, support.getGroupValues(newOids, PDU.GET));
				
			}
			return resultMap;
		}catch(Exception e){
			System.out.println("getSubTableValue throw an exception");
			e.printStackTrace();
		}
		return null;
		
		 
		
	}
	
	public Map<String ,List<VariableBinding>> getSubPortTableValue(OID[]objs,SNMPSupportImpl support,String depoids)throws Exception{
		 
		try{


			
			Map<String,String> depOidValueMap = support.getBulk(new OID(depoids),"getSubPortTable");

			Set<String> oidIndexSet = depOidValueMap.keySet();
			
			Iterator<String> oidIndexIter = oidIndexSet.iterator();
			
			Map<String ,List<VariableBinding>> resultMap = new HashMap<String,List<VariableBinding>>();
			
			if(oidIndexIter==null){
				return null;
			}
			
			
			while(oidIndexIter.hasNext()){
				
				String key = (String) oidIndexIter.next();
				
				

				OID[] newOids = new OID[objs.length];
				for(int i =0;i<objs.length;i++){
					System.out.print("<<<<<<<<<<<<:::objs[i]"+objs[i]+":::>>>>>>>>>>>>\n");
					System.out.print("<<<<<<<<<<<<:::key"+key+":::>>>>>>>>>>>>\n");
					if(key.trim().equals(".1.0.0")&&objs[i].toString().startsWith("1.3.6.1.4.1.17409.2.4.10.1.1.")){
						OID newOid = support.getOIDIndex(objs[i], ".1.0.1");
						newOids[i]=newOid;
					}else{
						OID newOid = support.getOIDIndex(objs[i], key);
						newOids[i]=newOid;
					}
					
					 
				}
				resultMap.put(key, support.getGroupValues(newOids, PDU.GET));
				
			}
			return resultMap;
		}catch(Exception e){
			System.out.println("getSubTableValue throw an exception");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<VariableBinding> getGroupValue(OID[] objs,
			SNMPSupportImpl support) throws Exception {
		//List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>();

		try {
			return support.getGroupValues(objs, PDU.GET);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getGroupValue throw an exception");
		}

		return null;
	}

	public List<List<VariableBinding>> getTableValue(OID[] objs,
			SNMPSupportImpl support) throws Exception {
		List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>();

		try {
			vbs = support.getTableValues(objs);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getTableValue throw an exception");
		}
		return vbs;
	}

	public ClassData ConvertValues2Entity(String className,
			List<List<VariableBinding>> varValues, List<OID2Property> properties)
			throws Exception {
		List<List<NameValue>> values = new ArrayList<List<NameValue>>();
		for (int i = 0; i < varValues.size(); i++) {
			List<NameValue> rowValues = new ArrayList<NameValue>();

			boolean getId = false;
			String id = "";
			OID idTmp = new OID();
			
			List<VariableBinding> vars = varValues.get(i);
			System.out.println("className=="+className+"<<>>"+"vars=="+vars);
			if(vars != null && vars.size()>0){
				for (int j = 0; j < properties.size(); j++) {
					VariableBinding var = vars.get(j);
					OID oid = var.getOid();
					
					for (int z = 0; z < properties.size(); z++) {
						OID2Property oid2property = properties.get(z);
						OID oidindex = oid2property.getOid();
						if (oid.startsWith(oidindex)) {
							String value = SNMPSupportImpl.getValueString(var.getVariable());
							try {
								
								Object valuereal;
								if (oid2property.getType().endsWith("java.lang.String")) {
									valuereal = value;
								} else if(oid2property.getType().equals("java.lang.Integer")){
									valuereal = Integer.valueOf(value);
								} else if(oid2property.getType().equals("java.lang.Long")){
									valuereal = Long.valueOf(value);
								} else if(oid2property.getType().equals("java.lang.Double")){
									valuereal = Double.valueOf(value);
								}else {
									Object[] args = { value };
									if( value.indexOf("noSuchObject") >= 0){
										valuereal = 0;
									}else
									valuereal = invokeMethod(oid2property.getType(),
											"valueOf", args);
								}

								NameValue nv = new NameValue(oid2property
										.getpropertyName(), valuereal);
								rowValues.add(nv);
								if(!getId) {
									getId = true;
									for(int it = oidindex.size(); it < oid.size();it++) {
										idTmp.append(oid.get(it));
									}
									id = idTmp.toString();
								}
							}
							catch(Exception ex) {
								System.out.println("Error is Found when transfer value for the item : " + oid.toString());
								throw ex;
							}
							break;

						}
					}
				}	

				rowValues.add(new NameValue("id", id));
	//
//				System.out.println(i + " : name = id");
//				System.out.println(i + " : class = "
//						+ id.getClass().toString());
//				System.out.println(i + " : value = "
//						+ id.toString());
				
				values.add(rowValues);
			}

		}

		ClassData data = new ClassData(className, values);
		return data;
	}
	
	public boolean setDataValues(String hostIp,Map<?,?> map){
		initSetMap();
		String setCommunity = map.get("writeComm").toString();
		SNMPConfig cfg = new SNMPConfig();
		cfg.setAgentIp(hostIp);
		cfg.setWriteCommunity(setCommunity);
		SNMPSupportImpl snmpSupport = new SNMPSupportImpl();
		snmpSupport.initSnmp(cfg);
		try {
			String setMethod = map.get("applyType").toString();
			SetParameter setParameter = setMap.get(setMethod);
			String indexStr = setParameter.getIndex();
			String cnuIndex  = "";
			String cardIndex = "";
			String cnuConfigIndex = "";
			int nextNo = 0;
			List<Object> serviceList = new ArrayList<Object>();
			CommonAdapter comAdapter = new CommonAdapter();
			String portIndex = "";
			if(setMethod.equalsIgnoreCase("setCnuBasic")){//applyType：setCnuBasic
				cnuIndex = (String)map.get("index");
			}else if(setMethod.equalsIgnoreCase("setCardInfo")){//applyType：setCardInfo
				cardIndex = (String)map.get("index");
			}else if(setMethod.equalsIgnoreCase("setCnuConfig")){//applyType：setCnuConfig
				cnuConfigIndex = (String)map.get("index");
			}else if(setMethod.equalsIgnoreCase("setCnuPortList")){//applyType：setCnuPortList
				if(indexStr.equalsIgnoreCase("")){
					portIndex = null;
				}
				portIndex = (String) map.get("index");
			}else if(setMethod.equalsIgnoreCase("setCnuTemplate")){
				serviceList = comAdapter.getValues(DongYanEocCNUServiceTable.class.getName(), snmpSupport);
				if(serviceList.size()>0){
					
				}
			}
			
			Map<String, String> parameters = setParameter.getParameters();
			List<String> oids = new ArrayList<String>();
			List<Variable> variables = new ArrayList<Variable>();
			for (String parameterName : parameters.keySet()) {
				Object value = null;
				if(setMethod.equalsIgnoreCase("setSysInfo")){//applyType：setSysInfo
					value = map.get(parameterName);
					String[] oidArray = new String[1];
					oidArray[0] = parameters.get(parameterName);
					setDataValue(hostIp, snmpSupport, value, oidArray);
				}else if(parameterName.equalsIgnoreCase("resetEoc")
						||parameterName.equalsIgnoreCase("resetAllOnlineCnu")
						||parameterName.equalsIgnoreCase("resetCnu")){
					value = 1;
					String[] oidArray = new String[1];
					oidArray[0] = parameters.get(parameterName);
					setDataValue(hostIp, snmpSupport, value, oidArray);
				}else if(setMethod.equalsIgnoreCase("setCnuTemplate")){
					//CNU template set
					String oid = parameters.get(parameterName);
					Object oidValue = map.get(parameterName);
					if(oidValue == null){//do not set this param to device
						continue;
					}else{
						OID[] versionOids = new OID[]{new OID("1.3.6.1.4.1.17409.2.4.3.2.1.18")};
						List<List<VariableBinding>> list = snmpSupport.getTableValues(versionOids);
						List<Object> cbatCardList = null;
						if(list.size()>0 && list.get(0).get(0).getVariable().toString().equals("VISTA-E01/B05")){
							List<Object> portList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable", snmpSupport);
							for(Object o : portList){
								
							}
						}else{
							cbatCardList= comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCBATCardInforTable", snmpSupport);
						}
						if(parameterName.equals("上行")){
							
						}
						if(oidValue == null){
							
						}else{
							String[] oidArray = new String[]{oid+"."+String.valueOf(nextNo)};
							setDataValue(hostIp, snmpSupport, oidValue, oidArray);
						}	
					}
					

				}else{
					if(portIndex != null && !portIndex.equals("")){
						//CNU portInfo-set
						JSONArray ja = new JSONArray();
						ja = JSONArray.fromObject(portIndex);
						for(Object cnuPortIndex : ja){
							String oid = parameters.get(parameterName);
							String portObj = (String) map.get(cnuPortIndex);
							if(portObj != null){
								JSONObject jsb = JSONObject.fromObject(portObj);
								Object obj = jsb.get(parameterName);
								String[] oidArray = new String[]{oid+"."+cnuPortIndex};
								setDataValue(hostIp, snmpSupport, obj, oidArray);	
							}
						}
					}else{
						//In addition to the structure set CNU Port Table
						String oid = parameters.get(parameterName);
						String[] oidArray = getOidByIndex(cardIndex,cnuIndex,cnuConfigIndex,oid);
						value = map.get(parameterName);
						setDataValue(hostIp, snmpSupport, value, oidArray);
					}
				}
				System.out.println("set:" + parameterName + " value:" + value + " success!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			if (snmpSupport != null) {
				snmpSupport.closeSnmp();
			}
		}
		return true;
	}
	
	private String[] getOidByIndex(String cardIndex, String cnuIndex, String cnuConfigIndex,String oid) {
		// TODO Auto-generated method stub
		String[] oidArray = new String[1];
		if(cardIndex.equals("")){
			
		}else{
			oidArray[0] = oid + "." + cardIndex;
		}
		if(cnuIndex.equals("")){
			
		}else{
			oidArray[0] = oid + "." + cnuIndex;
		}
		if(cnuConfigIndex.equals("")){
			
		}else{
			oidArray[0] = oid + "." + cnuConfigIndex;
		}
		return oidArray;
	}

	private void setDataValue(String hostIp, SNMPSupportImpl snmpSupport, Object object,String[] oidArray) throws Exception {
		try {
			for(String oid : oidArray){
				if (object instanceof Integer) {
					int value = Integer.parseInt(object.toString());
//					snmpSupport.setParameter(oid, value, true);
					snmpSupport.set(oid, new Integer32(value));
				}else{
					String value = object.toString();
//					snmpSupport.setParameter(oid, value, true);
					snmpSupport.set(oid, new OctetString(value));
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("setDataValue error!");
			throw e;
		}
	}

	public void initSetMap(){
		if(setMap.size() > 0){
			return;
		}
		//init setCardInfo
		String index = "index";
		Map<String,String> parameters1 = new HashMap<String,String>();
		parameters1.put("rfOutputPower","1.3.6.1.4.1.17409.2.4.2.1.1.3");
		parameters1.put("rfDsBeginFrequency","1.3.6.1.4.1.17409.2.4.2.1.1.4");
		parameters1.put("rfDsEndFrequency","1.3.6.1.4.1.17409.2.4.2.1.1.5");
		parameters1.put("rfUsBeginFrequency","1.3.6.1.4.1.17409.2.4.2.1.1.6");
		parameters1.put("rfUsEndFrequency","1.3.6.1.4.1.17409.2.4.2.1.1.7");
		
		SetParameter setCardInfo = new SetParameter(index,parameters1);
		setMap.put("setCardInfo", setCardInfo);
		//init setSysInfo
		index = "";
		Map<String,String> parameters2 = new HashMap<String,String>();
		parameters2.put("alarmStatus","1.3.6.1.4.1.17409.2.4.1.3.1.0");
		parameters2.put("cnuCountMax","1.3.6.1.4.1.17409.2.4.5.2.3.0");
		
		SetParameter setSysInfo = new SetParameter(index,parameters2);
		setMap.put("setSysInfo", setSysInfo);
		//		init resetEoc
		index = "";
		Map<String,String> parameters3 = new HashMap<String,String>();
		parameters3.put("resetEoc","1.3.6.1.4.1.17409.2.4.1.3.6.0");
		
		SetParameter resetEoc = new SetParameter(index,parameters3);
		setMap.put("resetEoc", resetEoc);
		//init resetAllOnlineCnu
		index = "";
		Map<String,String> parameters4 = new HashMap<String,String>();
		parameters4.put("resetAllOnlineCnu","1.3.6.1.4.1.17409.2.4.5.2.1.0");
		
		SetParameter resetAllOnlineCnu = new SetParameter(index,parameters4);
		setMap.put("resetAllOnlineCnu", resetAllOnlineCnu);
		//		init setCnuBasic
		index = "";
		Map<String,String> parameters5 = new HashMap<String,String>();
		parameters5.put("eocCNUAdminStatus","1.3.6.1.4.1.17409.2.4.5.2.4.1.18");
		
		SetParameter setCnuBasic = new SetParameter(index,parameters5);
		setMap.put("setCnuBasic", setCnuBasic);
		//		init setCnuConfig
		index = "";
		Map<String,String> parameters6 = new HashMap<String,String>();
		parameters6.put("downstreamPIR","1.3.6.1.4.1.17409.2.4.5.2.4.1.19");
		parameters6.put("upstreamPIR","1.3.6.1.4.1.17409.2.4.5.2.4.1.20");
		parameters6.put("igmpSnoopingEN","1.3.6.1.4.1.17409.2.4.7.1.1.4");
		
		SetParameter setCnuConfig = new SetParameter(index,parameters6);
		setMap.put("setCnuConfig", setCnuConfig);
//		init resetCnu
		index = "";
		Map<String,String> parameters7 = new HashMap<String,String>();
		parameters7.put("resetCnu","1.3.6.1.4.1.17409.2.4.5.2.4.1.9");
		
		SetParameter resetCnu = new SetParameter(index,parameters7);
		setMap.put("resetCnu", resetCnu);
		
		//init setCnuPortList
		index = "";
		Map<String,String> parameters8 = new HashMap<String,String>();
		parameters8.put("priority","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.4");
		parameters8.put("portMode", "1.3.6.1.4.1.34592.1.4.1.3.1.2.1.5");
		parameters8.put("pvid", "1.3.6.1.4.1.34592.1.4.1.3.1.2.1.6");
//		parameters8.put("upSpeedLimit","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.8");
//		parameters8.put("downSpeedLimit","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.7");
		parameters8.put("portVIDList", "1.3.6.1.4.1.17409.2.4.9.8.1.7");
		parameters8.put("portUntaggedVIDList", "1.3.6.1.4.1.17409.2.4.9.8.1.8");
		SetParameter setCnuPortList = new SetParameter(index,parameters8);
		setMap.put("setCnuPortList", setCnuPortList);
		
		//init setCnuTemplate
		index = "";
		Map<String,String> parameters9 = new HashMap<String,String>();
		parameters9.put("priority_1","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.4");
		parameters9.put("PortVLANMode_1","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.5");
		parameters9.put("pvid_1","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.6");
		parameters9.put("portTaggedVLAN_1","1.3.6.1.4.1.17409.2.4.9.8.1.7");
		parameters9.put("upSpeedLimit_1","1.3.6.1.4.1.34592.1.4.1.4.2.1.13");
		parameters9.put("downSpeedLimit_1","1.3.6.1.4.1.34592.1.4.1.4.2.1.14");
//		parameters9.put("duplexMode_2","1.3.6.1.4.1.34592.1.4.1.4.2.1.15");
		parameters9.put("priority_2","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.4");
		parameters9.put("PortVLANMode_2","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.5");
		parameters9.put("pvid_2","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.6");
		parameters9.put("portTaggedVLAN_2","1.3.6.1.4.1.17409.2.4.9.8.1.7");
		parameters9.put("upSpeedLimit_2","1.3.6.1.4.1.34592.1.4.1.4.2.1.21");
		parameters9.put("downSpeedLimit_2","1.3.6.1.4.1.34592.1.4.1.4.2.1.22");
//		parameters9.put("duplexMode_3","1.3.6.1.4.1.34592.1.4.1.4.2.1.23");
		parameters9.put("priority_3","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.4");
		parameters9.put("PortVLANMode_3","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.5");
		parameters9.put("pvid_3","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.6");
		parameters9.put("portTaggedVLAN_3","1.3.6.1.4.1.17409.2.4.9.8.1.7");
		parameters9.put("upSpeedLimit_3","1.3.6.1.4.1.34592.1.4.1.4.2.1.29");
		parameters9.put("downSpeedLimit_3","1.3.6.1.4.1.34592.1.4.1.4.2.1.30");
//		parameters9.put("duplexMode_4","1.3.6.1.4.1.34592.1.4.1.4.2.1.31");
		parameters9.put("priority_4","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.4");
		parameters9.put("PortVLANMode_4","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.5");
		parameters9.put("pvid_4","1.3.6.1.4.1.34592.1.4.1.3.1.2.1.6");
		parameters9.put("portTaggedVLAN_4","1.3.6.1.4.1.17409.2.4.9.8.1.7");
		parameters9.put("upSpeedLimit_4","1.3.6.1.4.1.34592.1.4.1.4.2.1.37");
		parameters9.put("downSpeedLimit_4","1.3.6.1.4.1.34592.1.4.1.4.2.1.38");
		SetParameter setCnuTemplate = new SetParameter(index,parameters9);
		setMap.put("setCnuTemplate", setCnuTemplate);
		
//		//init set template main info
//		index = "";
//		Map<String,String> parameters10 = new HashMap<String,String>();
//		parameters10.put("", "");
	}
	
	public List<Object> getDataValue(String className, String xmlFile, SNMPSupportImpl support) throws Exception {
		Xml2Entity xml2entity = new Xml2Entity();
		List<ClassDefine> tables = xml2entity.getEntities(xmlFile);
		for(Iterator<ClassDefine> iterTable = tables.iterator(); iterTable.hasNext();) {

			ClassDefine entry = iterTable.next();
			
			if(entry.getName().equals(className)) {
				return getDataValue(entry, support);
			}
		}
		System.out.println("the calss Name Can't be found when invoke getDataValue.");
		return new ArrayList<Object>();
	}

	public List<Object> getDataValue(String className, String xmlFile, String id, SNMPSupportImpl support) throws Exception {
		Xml2Entity xml2entity = new Xml2Entity();
		List<ClassDefine> tables = xml2entity
				.getEntities(xmlFile);

		for(Iterator<ClassDefine> iterTable = tables.iterator(); iterTable.hasNext();) {
			ClassDefine entry = iterTable.next();
			if(entry.getName().equals(className)) {
				return getDataValue(entry, id, support);
			}
		}
		System.out.println("the calss Name Can't be found when invoke getDataValue.");
		return new ArrayList<Object>();
	}
	
	public List<Object> getDataValue(ClassDefine entry, String id, SNMPSupportImpl support) throws Exception {
		ClassDefine currententry = new ClassDefine(entry);
		List<OID2Property> pty = currententry.getOid2propertyList();
		for(Iterator<OID2Property> iter = pty.iterator(); iter.hasNext();) {
			iter.next().getOid().append(id);
		}
		currententry.setOperation("getGroup");
		return getDataValue(currententry, support);
	}

	public List<Object> getDataValue(ClassDefine entry, SNMPSupportImpl support) throws Exception {
		String className = entry.getName();
		List<OID2Property> properties = entry.getOid2propertyList();;
		String operation = entry.getOperation();
		String depoids = entry.getdepOid();

		if(properties == null) {
			System.out.println("The class can't be fined.");
			throw new IllegalArgumentException();
		}
		
		OID[] oids = new OID[properties.size()];

		int i = 0;
		for (Iterator<OID2Property> iter = properties.iterator(); iter
				.hasNext();) {
			oids[i++] = iter.next().getOid();
		}
		if((operation.equals("getGroup")||operation.equals("getTable"))){
			List<List<VariableBinding>> varValues = getValue(oids, support, operation);
			
			if(varValues==null||varValues.size()==0) return null;
			System.out.print("@@@@@@@@@@@@@@@@@@@@@@@varValues.size@@@@@@@@@@@@@@"+varValues.size());
			ClassData data = ConvertValues2Entity(className, varValues, properties);
			List<Object> classes = new ArrayList<Object> ();
			List<List<NameValue>> values = data.getData();
			for(Iterator<List<NameValue>> iterRow = values.iterator(); iterRow.hasNext();) {
				List<NameValue> rowValue = iterRow.next();
				Object[] paras = new Object[rowValue.size()];
				int j = 0;
				for(Iterator<NameValue> iter = rowValue.iterator(); iter.hasNext();) {
					paras[j++] = iter.next().getValue();
				}
				
				Object classval = newInstance(className, paras);
				
				classes.add(classval);
			}
			return classes;
		}
		else if(operation.equals("getSubTable")||operation.equals("getSubPortTable")){
			Map<String ,List<VariableBinding>> varValuesMap = getValue(oids,support,operation,depoids);
			if(varValuesMap==null||varValuesMap.size()==0) return null;
			System.out.print("@@@@@@@@@@@@@@@@@@@@@@@varValuesMap.size()@@@@@@@@@@@@@@"+varValuesMap.size());
			Set varValueMapSet = varValuesMap.keySet();
			Iterator<String> varValueMapIter = varValueMapSet.iterator();
			List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>();
			String[] identifierArray = new String[varValueMapSet.size()];
			int index = 0;
			while(varValueMapIter.hasNext()){
				String Identifier = varValueMapIter.next();
				List<VariableBinding> varValues = varValuesMap.get(Identifier);
				identifierArray[index++]=Identifier;
				vbs.add(varValues);
			}	
			ClassData data = ConvertValues2Entity(className, vbs, properties);	
			List<Object> classes = new ArrayList<Object> ();
			List<List<NameValue>> values = data.getData();
			index = 0;
			for(Iterator<List<NameValue>> iterRow = values.iterator(); iterRow.hasNext();) {
				List<NameValue> rowValue = iterRow.next();
				Object[] paras = new Object[rowValue.size()];
				int j = 0;
				String Identifier = identifierArray[index++];
				for(Iterator<NameValue> iter = rowValue.iterator(); iter.hasNext();) {
					paras[j++] = iter.next().getValue();
				}
				//paras[j] = Identifier;
				Object classval = newInstance(className, paras);
				classes.add(classval);
			}
			return classes;
		}
//		else if(operation.equals("getSubPortTable")){
//
//			Map<String ,List<VariableBinding>>varValuesMap = getValue(oids,support,operation,depoids); 
//			if(varValuesMap==null||varValuesMap.size()==0) return null;
//			Set<String> varValueMapSet = varValuesMap.keySet();
//			Iterator<String> varValueMapIter = varValueMapSet.iterator();
//			if(varValueMapIter==null)return null;
//			List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>();
//			String[] identifierArray = new String[varValueMapSet.size()];
//			int index = 0;
//			while(varValueMapIter.hasNext()){
//				String Identifier = varValueMapIter.next();
//				List<VariableBinding> varValues = varValuesMap.get(Identifier);
//				identifierArray[index++]=Identifier;
//				vbs.add(varValues);
//			}
//			ClassData data = ConvertValues2Entity(className, vbs, properties);
//			List<Object> classes = new ArrayList<Object> ();
//			List<List<NameValue>> values = data.getData();
//			index = 0;
//			for(Iterator<List<NameValue>> iterRow = values.iterator(); iterRow.hasNext();) {
//				List<NameValue> rowValue = iterRow.next();
//				Object[] paras = new Object[rowValue.size()+2];
//				int j = 0;
//				for(Iterator<NameValue> iter = rowValue.iterator(); iter.hasNext();) {
//					paras[j++] = iter.next().getValue();
//				}
//				String Identifier = identifierArray[index++];
//				paras[j] = Identifier.substring(0, Identifier.length()-2);
//				paras[++j]=Identifier.substring(Identifier.length()-1,Identifier.length());
//				Object classval = newInstance(className, paras);
//				classes.add(classval);
//			}
//			return classes;
//		}
		return null;
	}

	public Map<String, List<Object>> getAllDataValue(String xmlFile, SNMPSupportImpl support) throws Exception {

		Xml2Entity xml2entity = new Xml2Entity();
		List<ClassDefine> tables = xml2entity.getEntities(xmlFile);

		Map<String, List<Object>> returnVal = new HashMap<String, List<Object>>();

		for(Iterator<ClassDefine> iterTable = tables.iterator(); iterTable.hasNext();) {
		
			ClassDefine entry = iterTable.next();
			String className = entry.getName();
			String operation = entry.getOperation();
			List<OID2Property> properties = entry.getOid2propertyList();

			OID[] oids = new OID[properties.size()];

			int i = 0;
			for (Iterator<OID2Property> iter = properties.iterator(); iter
					.hasNext();) {
				oids[i++] = iter.next().getOid();
			}
			
			List<List<VariableBinding>> varValues = getValue(oids, support, operation);

			ClassData data = ConvertValues2Entity(className, varValues, properties);
			
			List<Object> classes = new ArrayList<Object> ();
			
			List<List<NameValue>> values = data.getData();
			
			for(Iterator<List<NameValue>> iterRow = values.iterator(); iterRow.hasNext();) {
				List<NameValue> rowValue = iterRow.next();
				Object[] paras = new Object[rowValue.size()];
				int j = 0;
				for(Iterator<NameValue> iter = rowValue.iterator(); iter.hasNext();) {
					paras[j++] = iter.next().getValue();
				}
			
				Object classval = newInstance(className, paras);
				
				classes.add(classval);
			}
			
			returnVal.put(className, classes);
			
		}
		return returnVal;
	}

	public Map<String, List<Object>> getAllDataValue(List<ClassDefine> tables, SNMPSupportImpl support) throws Exception {

		Map<String, List<Object>> returnVal = new HashMap<String, List<Object>>();

		for(Iterator<ClassDefine> iterTable = tables.iterator(); iterTable.hasNext();) {
			
			ClassDefine entry = iterTable.next();
			String className = entry.getName();
			String operation = entry.getOperation();
			if(operation==null){
				continue;
			}
			if(className.equals(ResourceClassName.GBEocCBATCardInforTable.getValue())){
				List<Object> cbatList = returnVal.get(ResourceClassName.GBEocCBATInforTable.getValue());
				if(cbatList!=null && cbatList.size()>0){
					GBEocCBATInforTable cbat  =  (GBEocCBATInforTable)cbatList.get(0);;
					if(cbat.getHwVersion().equals("VISTA-E01/B05")){
						continue;
					}
				}
			}
			if(className.equals(DongYanEocCBATCardInforTable.class.getName())){
				List<Object> cbatList = returnVal.get(ResourceClassName.GBEocCBATInforTable.getValue());
				if(cbatList!=null && cbatList.size()>0){
					GBEocCBATInforTable cbat  =  (GBEocCBATInforTable)cbatList.get(0);;
					if(!cbat.getHwVersion().equals("VISTA-E01/B05")){
						continue;
					}
				}
			}
			List<OID2Property> properties = entry.getOid2propertyList();
			System.out.println("entry=="+entry.getName());
			System.out.println("properties=="+entry.getOid2propertyList());
			String depoids=entry.getdepOid();
			OID[] oids = new OID[properties.size()];
			int i = 0;
			
			for (Iterator<OID2Property> iter = properties.iterator(); iter.hasNext();) {
				oids[i++] = iter.next().getOid();
			}
			
			if((operation.equals("getGroup")||operation.equals("getTable"))){
				List<List<VariableBinding>> varValues = getValue(oids, support, operation);
				if(varValues==null||varValues.size()==0){
					if(operation.equals("getTable")&&className.equals("com.pbn.oss.adaptor.eoc.bean.EocCBATCardRFTable")){
						return returnVal;
					}
					continue;
				}
				ClassData data  = ConvertValues2Entity(className, varValues, properties);
				
				List<Object> classes = new ArrayList<Object> ();
				
				List<List<NameValue>> values = data.getData();
				
				for(Iterator<List<NameValue>> iterRow = values.iterator(); iterRow.hasNext();) {
					List<NameValue> rowValue = iterRow.next();
					Object[] paras = new Object[rowValue.size()];
					int j = 0;
					for(Iterator<NameValue> iter = rowValue.iterator(); iter.hasNext();) {
					    Object obj = iter.next();
						paras[j++] = ((NameValue) obj).getValue();
					}
					
					Object classval = newInstance(className, paras);
					
					classes.add(classval);
				}
				
				returnVal.put(className, classes);
			}
			else if(operation.equals("getSubTable")||operation.equals("getSubPortTable")){
				Map<String ,List<VariableBinding>> varValuesMap = getValue(oids,support,operation,depoids); 
				if(varValuesMap==null||varValuesMap.size()==0) continue;
				Set varValueMapSet = varValuesMap.keySet();
				Iterator<String> varValueMapIter = varValueMapSet.iterator();
				List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>();
				String[] identifierArray = new String[varValueMapSet.size()];
				int index = 0;
				while(varValueMapIter.hasNext()){
					String Identifier = varValueMapIter.next();
					List<VariableBinding> varValues = varValuesMap.get(Identifier);
					identifierArray[index++]=Identifier;
					vbs.add(varValues);
				}	
				ClassData data = ConvertValues2Entity(className, vbs, properties);	
				List<Object> classes = new ArrayList<Object> ();
				List<List<NameValue>> values = data.getData();
				index = 0;
				for(Iterator<List<NameValue>> iterRow = values.iterator(); iterRow.hasNext();) {
					List<NameValue> rowValue = iterRow.next();
					//Object[] paras = new Object[rowValue.size()+1];
					Object[] paras = new Object[rowValue.size()];
					int j = 0;
					String Identifier = identifierArray[index++];
					for(Iterator<NameValue> iter = rowValue.iterator(); iter.hasNext();) {
						paras[j++] = iter.next().getValue();
					}
					//paras[j] = Identifier;
					Object classval = newInstance(className, paras);
					classes.add(classval);
				}
					
				returnVal.put(className, classes);
				
				
			}
//			else if(operation.equals("getSubPortTable")){
//				
//				Map<String ,List<VariableBinding>>varValuesMap = getValue(oids,support,operation,depoids); 
//				if(varValuesMap==null||varValuesMap.size()==0) continue;
//				Set<String> varValueMapSet = varValuesMap.keySet();
//				Iterator<String> varValueMapIter = varValueMapSet.iterator();
//				if(varValueMapIter==null)continue;
//				List<List<VariableBinding>> vbs = new ArrayList<List<VariableBinding>>();
//				String[] identifierArray = new String[varValueMapSet.size()];
//				int index = 0;
//				while(varValueMapIter.hasNext()){
//					String Identifier = varValueMapIter.next();
//					List<VariableBinding> varValues = varValuesMap.get(Identifier);
//					identifierArray[index++]=Identifier;
//					vbs.add(varValues);
//				}
//				ClassData data = ConvertValues2Entity(className, vbs, properties);
//				List<Object> classes = new ArrayList<Object> ();
//				List<List<NameValue>> values = data.getData();
//				index = 0;
//				for(Iterator<List<NameValue>> iterRow = values.iterator(); iterRow.hasNext();) {
//					List<NameValue> rowValue = iterRow.next();
//					Object[] paras = new Object[rowValue.size()+2];
//					int j = 0;
//					for(Iterator<NameValue> iter = rowValue.iterator(); iter.hasNext();) {
//						paras[j++] = iter.next().getValue();
//					}
//					String Identifier = identifierArray[index++];
//					paras[j] = Identifier.substring(0, Identifier.length()-2);
//					paras[++j]=Identifier.substring(Identifier.length()-1,Identifier.length());
//					Object classval = newInstance(className, paras);
//					classes.add(classval);
//				}
//				returnVal.put(className, classes);
//			}
			
		}
		return returnVal;
	}
	
	
	// *******************************************************************
	public static void main(String[] args) throws Exception {

		Reflection rf = new Reflection();
		
//		rf.getAllDataValue("g:\\test\\test.xml", "192.168.27.225");
//		rf.getAllDataValue("e:\\thomson.xml", "192.168.16.81");
//		List<VariableBinding> vbs = rf.getGroupValue(new OID[]{new OID("1.3.6.1.4.1.2863.1.1.1.8.5.0")}, "192.168.21.101");
//		System.out.println(vbs);

	}

}

class OID2Property {
	private OID oid;
	private String propertyName;
	private String type;
	private String processClass;

	public OID2Property(OID oid, String property, String type,
			String processClass) {
		super();
		this.oid = oid;
		this.propertyName = property;
		this.type = type;
		this.processClass = processClass;
	}

	public OID getOid() {
		return oid;
	}

	public void setOid(OID oid) {
		this.oid = oid;
	}

	public String getpropertyName() {
		return propertyName;
	}

	public void setpropertyName(String property) {
		this.propertyName = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProcessClass() {
		return processClass;
	}

	public void setProcessClass(String processClass) {
		this.processClass = processClass;
	}
}

class ClassDefine
{
	private String name;
	private String operation;
	List<OID2Property> oid2propertyList;
	private String tableProperty;
	private String depOid;
	public ClassDefine(String name, String operation,
			List<OID2Property> oid2propertyList) {
		super();
		this.name = name;
		this.operation = operation;
		this.oid2propertyList = new ArrayList<OID2Property>();
		for(Iterator<OID2Property> iter = oid2propertyList.iterator(); iter.hasNext();) {
			this.oid2propertyList.add(iter.next());
		}
	}
	public ClassDefine(ClassDefine origin) {
		this(origin.name, origin.operation, origin.oid2propertyList);
	}
	public ClassDefine(String name, String operation,
			List<OID2Property> oid2propertyList,String tableProperty,String oid) {
		super();
		this.name = name;
		this.operation = operation;
		this.oid2propertyList = new ArrayList<OID2Property>();
		this.tableProperty = tableProperty;
		this.depOid = oid;
		for(Iterator<OID2Property> iter = oid2propertyList.iterator(); iter.hasNext();) {
			this.oid2propertyList.add(iter.next());
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public List<OID2Property> getOid2propertyList() {
		return oid2propertyList;
	}
	public void setOid2propertyList(List<OID2Property> oid2propertyList) {
		this.oid2propertyList = oid2propertyList;
	}
	public String getTableProperty(){
		return tableProperty;
	}
	public void setTableProperty(String tableProperty){
		this.tableProperty = tableProperty;
	}
	public String getdepOid(){
		return depOid;
	}
	public  void setdepOid(String depOid){
		this.depOid = depOid;
	}
}

class NameValue {
	private String name;
	private Object value;

	public NameValue(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

class SetParameter{
	private String cardIndex = "";
	private String cnuIndex = "";
	private String portIndex = "";
	private String index = "";
	private Map<String,String> parameters;
	public SetParameter(String index,Map<String,String> parameters, String cardIndex, String cnuIndex, String portIndex) {
		this.cardIndex = cardIndex;
		this.cnuIndex = cnuIndex;
		this.portIndex = portIndex;
		this.index = index;
		this.parameters = parameters;
	}
	
	public SetParameter(String index, Map<String, String> parameters) {
		this.index = index;
		this.parameters = parameters;
	}

	public SetParameter(){
		
	}
	public String getCardIndex() {
		return cardIndex;
	}
	public void setCardIndex(String index) {
		this.cardIndex = index;
	}
	public Map<String,String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String,String> parameters) {
		this.parameters = parameters;
	}
	public String getCnuIndex() {
		return cnuIndex;
	}
	public void setCnuIndex(String cnuIndex) {
		this.cnuIndex = cnuIndex;
	}
	public String getPortIndex() {
		return portIndex;
	}
	public void setPortIndex(String portIndex) {
		this.portIndex = portIndex;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
}

class ClassData {
	private String className;
	List<List<NameValue>> data;

	public ClassData(String className, List<List<NameValue>> data) {
		super();
		this.className = className;
		this.data = data;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<List<NameValue>> getData() {
		return data;
	}

	public void setData(List<List<NameValue>> data) {
		this.data = data;
	}
}