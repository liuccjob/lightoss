package com.pbn.oss.adaptor.eoc.gcable;

 

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

import org.slf4j.Logger;
import org.snmp4j.PDU;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUServiceTable;
import com.pbn.oss.adaptor.eoc.gcable.snmp.SNMPConfig;
import com.pbn.oss.adaptor.eoc.gcable.snmp.SNMPSupport;
import com.pbn.oss.adaptor.eoc.gcable.snmp.SNMPSupportImpl;

 

 

public class Reflection {
	public static Map<String,SetParameter> setMap = new HashMap<String,SetParameter>();
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(Reflection.class);
	
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
			if(vars != null && vars.size()>0){
				for (int j = 0; j < properties.size(); j++) {
					VariableBinding var = vars.get(j);
					if(var == null){
						continue;
					}
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
//								System.out.println(i + " : " + j + " : oid = "
//										+ oid.toString());
//								System.out.println(i + " : " + j + " : name = "
//										+ oid2property.getpropertyName());
//								System.out.println(i + " : " + j + " : class = "
//										+ nv.getValue().getClass().toString());
//								System.out.println(i + " : " + j + " : value = "
//										+ nv.getValue().toString());
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
		cfg.setTimeout(15000);
		cfg.setRetries(1);
		SNMPSupportImpl snmpSupport = new SNMPSupportImpl();
		snmpSupport.initSnmp(cfg);
		DiscoveryEocImpl impl = new DiscoveryEocImpl();
		String xmlPath = impl.getClass().getClassLoader().getResource("META-INF/eoc_gb_gcable.xml").getFile();
		try {
			String setMethod = map.get("applyType").toString();
			SetParameter setParameter = setMap.get(setMethod);
			String cnuIndex  = "";
			String cardIndex = "";
			String cnuIndexArray = "";
			String cnuConfigIndex = "";
			CommonAdapter comAdapter = new CommonAdapter(xmlPath);
			List<Object> serviceList = new ArrayList<Object>();
			String portIndex = "";
			Map<String, String> parameters = setParameter.getParameters();
			//init batch oids and variables
			List<String> oids = new ArrayList<String>();
			List<Variable> variables = new ArrayList<Variable>();
			if(setMethod.equalsIgnoreCase("resetBatchOnlineCnu")){
				cnuIndexArray = (String) map.get("index");
				JSONArray ja = new JSONArray();
				ja = JSONArray.fromObject(cnuIndexArray);
				for(Object cnuIndex1 : ja){
					String cnuIndex2 = cnuIndex1.toString();
					Object resetValue = 1;
					for(String parameterName : parameters.keySet()){
						String cnuOid = parameters.get(parameterName);
//						oids.add(cnuOid+"."+cnuIndex2);
//						variables.add(new Integer32(resetValue));
						String[] oidList = new String[]{cnuOid+"."+cnuIndex2};
						setDataValue(hostIp, snmpSupport, resetValue, oidList);
					}
				}
//				snmpSupport.set(oids, variables);
				System.out.println("set:" + setMethod + " value:" + ja.toString() + " success!");
				return true;
			}else if(setMethod.equalsIgnoreCase("setCnuPortList")){//applyType：setCnuPortList
				portIndex = (String) map.get("index");
				if(portIndex != null && !portIndex.equals("")){
					//CNU portInfo-set
					JSONArray ja = new JSONArray();
					ja = JSONArray.fromObject(portIndex);
					for(Object cnuPortIndex : ja){
						String tempPortIndex = cnuPortIndex.toString();
						int t = 0;
						String portObj = (String) map.get(cnuPortIndex);
						serviceList = null;
						serviceList = comAdapter.getValues(GCableEocCNUServiceTable.class.getName(), snmpSupport);
						String upSpeedLimit = "";
						String downSpeedLimit = "";
						int[] upAndDownSpeed = new int[2];
						if(portObj != null){
							JSONObject jsb = JSONObject.fromObject(portObj);
							for(String parameterName : parameters.keySet()){
								if(!jsb.containsKey(parameterName)){
									continue;
								}
								Object obj = jsb.get(parameterName);
//								Variable v = null;
								if(obj == null){
									continue;
								}
								String oid = parameters.get(parameterName);
								if(obj.equals("Transparent")){
									obj = 0;
								}else if(obj.equals("Access")){
									obj = 1;
								}else if(obj.equals("Trunk") || obj.equals("Hybrid")){
									obj = 2;
								}
								if(parameterName.equals("portVIDList") || parameterName.equals("portUntaggedVIDList")){
									obj = setTaggedValanList(obj);
								}
								if(parameterName.contains("SpeedLimit") || parameterName.contains("castStorm")){//gcable 的广播风暴抑制使能是统一设置的
									continue;
								}
								if(parameterName.equals("duplexMode") || parameterName.equals("speedMode")){
									String[] autoNegOids = new String[]{SNMPConstant.portConfAutoNeg+"."+tempPortIndex};
									setDataValue(hostIp, snmpSupport, SNMPConstant.autoNegValueD, autoNegOids);
								}
								String[] oidList = new String[]{oid+"."+tempPortIndex};
								setDataValue(hostIp, snmpSupport, obj, oidList);
							}
							for(String parameterName : parameters.keySet()){
								if(!jsb.containsKey(parameterName)){
									continue;
								}
								Object oidValue = jsb.get(parameterName);
								if(oidValue == null){
									continue; 
								}
								if(parameterName.contains("SpeedLimit")){
									upSpeedLimit = "upSpeedLimit";
									downSpeedLimit = "downSpeedLimit";
									installUpAndDownArray(parameterName,upAndDownSpeed,oidValue);
									t++;
									if(t<2){
										continue;
									}
									setSpeedLimitValue(parameterName,serviceList,upAndDownSpeed,tempPortIndex,hostIp,
											snmpSupport,map,setMethod,jsb,upSpeedLimit,downSpeedLimit);	
								}
							}
							for(String parameterName : parameters.keySet()){
								if(parameterName.contains("castStorm")){
									if(!jsb.containsKey(parameterName)){
										continue;
									}
									Object oidValue = jsb.get(parameterName);
									String objOid = parameters.get(parameterName);
									if(oidValue == null){
										continue; 
									}
									if(parameterName.contains("StormProtection") || parameterName.equals("broadcastStormThreshold")){
										for(Object portTempIndex : ja){
											String[] oidList = new String[]{objOid+"."+portTempIndex};
											setDataValue(hostIp, snmpSupport, oidValue, oidList);
										}
									}
								}
							}
						}
					}
				}
			}else if(setMethod.equalsIgnoreCase("setCnuTemplate")){//applyType：setCnuTemplate
				portIndex = (String) map.get("index");
				cnuIndex = (String)map.get("cnuIndex");
				if((portIndex != null && !portIndex.equals(""))&&(cnuIndex != null && !cnuIndex.equals(""))){
					//CNU portInfo-set
					JSONArray ja = new JSONArray();
					JSONArray js = new JSONArray();
					ja = JSONArray.fromObject(portIndex);
					js = JSONArray.fromObject(cnuIndex);
					for(String parameterName : parameters.keySet()){
						String oid = parameters.get(parameterName);
						if(!map.containsKey(parameterName)){
							continue;
						}
						Object oidValue = map.get(parameterName);
						if(oidValue == null){
							continue;
						}
						if(parameterName.equals("igmp") || parameterName.equals("lookbackTest") || parameterName.equals("macLimit")){//这里过滤了cableDownLimit与cableUpLimit,设备不支持
							for(Object cnuTempIndex : js){
								String[] oidList = new String[]{oid+"."+cnuTempIndex.toString()};
								setDataValue(hostIp, snmpSupport, oidValue, oidList);
							}
						}
						if(parameterName.contains("castStorm")){//gcable 的广播风暴抑制使能是统一设置的
							if(!map.containsKey(parameterName)){
								continue;
							}
							if(parameterName.contains("StormProtection_1") || parameterName.equals("broadcastStormThreshold_1")){
								for(Object portTempIndex : ja){
									String tempPortIndex = portTempIndex.toString();
									String[] oidList = new String[]{oid+"."+tempPortIndex};
									setDataValue(hostIp, snmpSupport, oidValue, oidList);
								}
							}
							
						}
					}
					for(Object portTempIndex : ja){
						String tempPortIndex = portTempIndex.toString();
						JSONObject jsb = null;
						serviceList = null;
						serviceList = comAdapter.getValues(GCableEocCNUServiceTable.class.getName(), snmpSupport);
						String upSpeedLimit = "";
						String downSpeedLimit = "";
						int[] upAndDownSpeed = new int[2];
						int k =0;
						for(String parameterName : parameters.keySet()){
							String oid = parameters.get(parameterName);
							if(!map.containsKey(parameterName)){
								continue;
							}
							if(parameterName.contains("castStorm") || parameterName.contains("priority_") ){
								continue;
							}
							Object oidValue = map.get(parameterName);
							if(oidValue == null){
								continue; 
							}
							if(oidValue.equals("Transparent")){//disable
								oidValue = 0;
							}else if(oidValue.equals("Access")){//enable
								oidValue = 1;
							}else if(oidValue.equals("Trunk") || oidValue.equals("Hybrid")){//enable
								oidValue = 2;
							}
							if(parameterName.contains("_")){
								String[] nameStrs = parameterName.split("_");
								String[] indexStrs = tempPortIndex.split("\\.");
								if(indexStrs[2].equals(nameStrs[1])){
									if(parameterName.contains("SpeedLimit")){
										upSpeedLimit = "upSpeedLimit_"+indexStrs[2];
										downSpeedLimit = "downSpeedLimit_"+indexStrs[2];
										installUpAndDownArray(parameterName,upAndDownSpeed,oidValue);
										k++;
										if(k<2){
											continue;
										}
										setSpeedLimitValue(parameterName,serviceList,upAndDownSpeed,tempPortIndex,hostIp,
												snmpSupport,map,setMethod,jsb,upSpeedLimit,downSpeedLimit);
										k=0;
									}else if(parameterName.contains("duplexMode_")){
										setDuplexMode(hostIp,snmpSupport,tempPortIndex,oidValue);
									}else if(parameterName.contains("portTaggedVLAN_") || parameterName.contains("portUnTaggedVLAN_")){
										oidValue = setTaggedValanList(oidValue);
									}else{
										String[] oidList = new String[]{oid+"."+tempPortIndex};
										setDataValue(hostIp, snmpSupport, oidValue, oidList);
									}
								}
							}
						}
						for(String parameterName : parameters.keySet()){//set the priority
							String oid = parameters.get(parameterName);
							if(!map.containsKey(parameterName)){
								continue;
							}
							if(!parameterName.contains("priority_")){
								continue;
							}
							Object oidValue = map.get(parameterName);
							if(oidValue == null){
								continue; 
							}
							String[] nameStrs = parameterName.split("_");
							String[] indexStrs = tempPortIndex.split("\\.");
							if(indexStrs[2].equals(nameStrs[1])){
								String[] oidList = new String[]{oid+"."+tempPortIndex};
								setDataValue(hostIp, snmpSupport, oidValue, oidList);
							}
						}
					}
				}

			}else{
				if(setMethod.equalsIgnoreCase("setCnuBasic")){//applyType：setCnuBasic
					cnuIndex = (String)map.get("index");
				}else if(setMethod.equalsIgnoreCase("setCardInfo")){//applyType：setCardInfo
					cardIndex = (String)map.get("index");
				}else if(setMethod.equalsIgnoreCase("setCnuConfig")){//applyType：setCnuConfig
					cnuConfigIndex = (String)map.get("index");
				}
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
					}else{
						//In addition to the structure set CNU Port Table
						String oid = parameters.get(parameterName);
						String[] oidArray = getOidByIndex(cardIndex,cnuIndex,cnuConfigIndex,oid);
						setDataValue(hostIp, snmpSupport, value, oidArray);
						oids.add(oidArray[0]);
						value = map.get(parameterName);
						Variable vs = null;
						if(value instanceof Integer){
							vs = new Integer32(Integer.valueOf(value.toString()));
						}else{//default is string
							vs = new OctetString(value.toString());
						}
//						String[] oidArray = getOidByIndex(cardIndex,cnuIndex,cnuConfigIndex,oid);
//						setDataValue(hostIp, snmpSupport, value, oidArray);
					}
					System.out.println("set:" + parameterName + " value:" + value + " success!");
				}
				snmpSupport.set(oids,variables);
				oids = null;
				variables = null;
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
	//将tagVlan值转换成“80.20.10......”形式
	public String setTaggedValanList(Object portVIDs){
		String backStr = "";
		String portVIDList = portVIDs.toString();
		if(portVIDList.length()>0){
			String[] strArray = null;
			if(portVIDList.contains(",")){
				strArray = portVIDList.split(",");
			}else{
				strArray = new String[]{portVIDList};
			}
			String[] strs = new String[512];
			for(int i=0;i<strs.length;i++){
				strs[i] = "00";
				if(i==strs.length-1){
					strs[i] = "08";//最后的4093是预留给wifi管理模块的vlan
				}
				int count = 0;
				int count2 = 0;
				if(i==0){
					count = 1;
				}else{
					count = i*8+1;
				}
				count2 =count+7;
				String[] strList = new String[]{"80","40","20","10","08","04","02","01"};
				Map<String,String> map = new HashMap<String,String>();
				int k = 0;
				for(int j= count;j<=count2;j++){
					map.put(String.valueOf(j),strList[k]);
					k++;
				}
				List<String> list = new ArrayList<String>();
				for(int n=0;n<strArray.length;n++){
					int current = Integer.parseInt(strArray[n]);
					if((current-count)>=0 && (count2-current)>=0){
						list.add(map.get(strArray[n]));
					}
				}
				int charInt0 = 0;
				int charInt1 = 0;
				if(list!=null && list.size()>0){
					for(int l =0;l<list.size();l++){
						int oxVaue0 =Integer.parseInt(String.valueOf(list.get(l).charAt(0)),10);
						int oxVaue1 =Integer.parseInt(String.valueOf(list.get(l).charAt(1)),10);
						charInt0 = charInt0+oxVaue0;
						charInt1 = charInt1+oxVaue1;
					}
					String str1 = "0";
					String str2 = "0";
					//convert value first
					if(charInt0 == 0){
						str1= "0";
					}else{
						if(charInt0<10){
							str1 = String.valueOf(charInt0);
						}else{
							str1 = Integer.toHexString(charInt0).toUpperCase();
						}
					}
					//convert value second
					if(charInt1 == 0){
						str2 = "0";
					}else{
						if(charInt1<10){
							str2 = String.valueOf(charInt1);
						}else{
							str2 = Integer.toHexString(charInt1).toUpperCase();
						}
					}
					strs[i] = str1+str2;
					if(i==0){
						backStr = strs[i];
					}else{
						backStr = backStr+"."+strs[i];
					}
				}else{
					backStr = backStr+"."+strs[i];
				}
			}
			if(backStr.startsWith(".")){ 
				backStr = backStr.substring(1);
			}
		}
		return backStr;
	}
	
	public void setDuplexMode(String hostIp, SNMPSupportImpl snmpSupport,String tempPortIndex,Object oidValue) throws Exception{
		Object speedValue = 0;
		Object duplexValue = 0;
		Object autoNegValue = 0;
		if(oidValue.equals(0)){
			autoNegValue = 1;
		}else if(oidValue.equals(1)){
			autoNegValue = 2;
			speedValue = 1;
			duplexValue = 1;
		}else if(oidValue.equals(2)){
			autoNegValue = 2;
			speedValue = 1;
			duplexValue = 2;
		}else if(oidValue.equals(3)){
			autoNegValue = 2;
			speedValue = 2;
			duplexValue = 1;
		}else if(oidValue.equals(4)){
			autoNegValue = 2;
			speedValue = 2;
			duplexValue = 2;
		}
		String[] autoNegOids = new String[]{SNMPConstant.portConfAutoNeg+"."+tempPortIndex};
		String[] duplexOids = new String[]{SNMPConstant.portConfDuplex+"."+tempPortIndex};
		String[] speedOids = new String[]{SNMPConstant.portConfSpeed+"."+tempPortIndex};
		if(autoNegValue.equals(1)){
			setDataValue(hostIp, snmpSupport, autoNegValue, autoNegOids);
		}else{
			setDataValue(hostIp, snmpSupport, autoNegValue, autoNegOids);
			setDataValue(hostIp, snmpSupport, duplexValue, duplexOids);
			setDataValue(hostIp, snmpSupport, speedValue, speedOids);	
		}
	}
	
	public void installUpAndDownArray(String parameterName, int[] upAndDownSpeed ,Object oidValue){
		if(parameterName.startsWith("upSpeedLimit")){
			upAndDownSpeed[0] = Integer.parseInt(oidValue.toString());
		}else{
			upAndDownSpeed[1] = Integer.parseInt(oidValue.toString());
		}
	}
	
	private void setSpeedLimitValue(String parameterName, List<Object> serviceList, int[] upAndDownSpeed,
			String tempPortIndex, String hostIp, SNMPSupportImpl snmpSupport,
			Map<?, ?> map, String setMethod, JSONObject jsb, String upSpeedLimit, String downSpeedLimit) throws Exception {
		if(parameterName.equals(upSpeedLimit) || parameterName.equals(downSpeedLimit)){
			int k = 0;
			Object serviceIndex = null;
			if(serviceList != null && serviceList.size()>0){
				for(Object o :serviceList){
					GCableEocCNUServiceTable gcs = (GCableEocCNUServiceTable)o;
					serviceIndex = gcs.getServiceIndex();
					if(gcs.getServiceDownPIR().equals(upAndDownSpeed[1]) && gcs.getServiceUpPIR().equals(upAndDownSpeed[0])){
						k++;
						break;
					}
				}
			}
			if(k==1){//bundling the CNUService for CNU port
				String[] oidList = new String[]{SNMPConstant.portServiceOid+"."+tempPortIndex};
				setDataValue(hostIp, snmpSupport, serviceIndex, oidList);
			}else{//create the new CNUService for CNU port
				int nextNo2 = 0;
				if(serviceList!=null && serviceList.size() > 0){
					nextNo2 = serviceList.size()+1;
				}else{
					nextNo2 = 1;
				}
				String cnuServiceOid = SNMPConstant.cnuServiceStatus+"."+String.valueOf(nextNo2);
				//create a template and wait for set values
				snmpSupport.set(cnuServiceOid, new Integer32(SNMPConstant.RowStatus_createAndWait));
				snmpSupport.set(cnuServiceOid, new Integer32(SNMPConstant.RowStatus_active));
				List<String> serviceOidList = new ArrayList<String>();
				serviceOidList.add(SNMPConstant.serviceName+"."+nextNo2); 
				serviceOidList.add(SNMPConstant.serviceDownPIR+"."+nextNo2);
				serviceOidList.add(SNMPConstant.serviceUpPIR+"."+nextNo2);
				List<Variable> serviceVarList = new ArrayList<Variable>();
				serviceVarList.add(new OctetString("service_"+nextNo2));
				if(setMethod.equals("setCnuTemplate")){
					serviceVarList.add(new Integer32(Integer.valueOf(map.get(downSpeedLimit).toString())));
					serviceVarList.add(new Integer32(Integer.valueOf(map.get(upSpeedLimit).toString())));
				}else{
					serviceVarList.add(new Integer32(Integer.valueOf(jsb.get(downSpeedLimit).toString())));
					serviceVarList.add(new Integer32(Integer.valueOf(jsb.get(upSpeedLimit).toString())));
				}
				snmpSupport.set(serviceOidList,serviceVarList);
				String[] oidList = new String[]{SNMPConstant.portServiceOid+"."+tempPortIndex};
				Object obValue = nextNo2;
				setDataValue(hostIp, snmpSupport, obValue, oidList);
			}
		}
		
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
		parameters1.put("rfOutputPower","1.3.6.1.4.1.42342.2.4.2.1.1.3");
		parameters1.put("rfDsBeginFrequency","1.3.6.1.4.1.42342.2.4.2.1.1.4");
		parameters1.put("rfDsEndFrequency","1.3.6.1.4.1.42342.2.4.2.1.1.5");
		parameters1.put("rfUsBeginFrequency","1.3.6.1.4.1.42342.2.4.2.1.1.6");
		parameters1.put("rfUsEndFrequency","1.3.6.1.4.1.42342.2.4.2.1.1.7");
		
		SetParameter setCardInfo = new SetParameter(index,parameters1);
		setMap.put("setCardInfo", setCardInfo);
		//init setSysInfo
		index = "";
		Map<String,String> parameters2 = new HashMap<String,String>();
		parameters2.put("alarmStatus","1.3.6.1.4.1.42342.2.4.1.3.1.0");
		parameters2.put("cnuCountMax","1.3.6.1.4.1.42342.2.4.5.2.3.0");
		
		SetParameter setSysInfo = new SetParameter(index,parameters2);
		setMap.put("setSysInfo", setSysInfo);
		//		init resetEoc
		index = "";
		Map<String,String> parameters3 = new HashMap<String,String>();
		parameters3.put("resetEoc","1.3.6.1.4.1.42342.2.4.1.3.6.0");
		
		SetParameter resetEoc = new SetParameter(index,parameters3);
		setMap.put("resetEoc", resetEoc);
		
		//init resetBatchOnlineCnu
		index = "";
		Map<String,String> parameters10 = new HashMap<String,String>();
		parameters10.put("resetBatchOnlineCnu","1.3.6.1.4.1.42342.2.4.5.2.4.1.9");
		 
		SetParameter resetBatchOnlineCnu = new SetParameter(index,parameters10);
		setMap.put("resetBatchOnlineCnu", resetBatchOnlineCnu);
		
		//init resetAllOnlineCnu
		index = "";
		Map<String,String> parameters4 = new HashMap<String,String>();
		parameters4.put("resetAllOnlineCnu","1.3.6.1.4.1.42342.2.4.5.2.1.0");
		
		SetParameter resetAllOnlineCnu = new SetParameter(index,parameters4);
		setMap.put("resetAllOnlineCnu", resetAllOnlineCnu);
		//		init setCnuBasic
		index = "";
		Map<String,String> parameters5 = new HashMap<String,String>();
		parameters5.put("eocCNUAdminStatus","1.3.6.1.4.1.42342.2.4.5.2.4.1.18");
		
		SetParameter setCnuBasic = new SetParameter(index,parameters5);
		setMap.put("setCnuBasic", setCnuBasic);
		//		init setCnuConfig
		index = "";
		Map<String,String> parameters6 = new HashMap<String,String>();
//		parameters6.put("downstreamPIR","1.3.6.1.4.1.42342.2.4.5.2.4.1.19");//gcable的终端不支持这俩节点的设置
//		parameters6.put("upstreamPIR","1.3.6.1.4.1.42342.2.4.5.2.4.1.20");
		parameters6.put("igmpSnoopingEN","1.3.6.1.4.1.42342.2.4.7.1.1.4");
		
		SetParameter setCnuConfig = new SetParameter(index,parameters6);
		setMap.put("setCnuConfig", setCnuConfig);
//		init resetCnu
		index = "";
		Map<String,String> parameters7 = new HashMap<String,String>();
		parameters7.put("resetCnu","1.3.6.1.4.1.42342.2.4.5.2.4.1.9");
		
		SetParameter resetCnu = new SetParameter(index,parameters7);
		setMap.put("resetCnu", resetCnu);
		
		//init setCnuPortList
		index = "";
		Map<String,String> parameters8 = new HashMap<String,String>();
		parameters8.put("priority","1.3.6.1.4.1.42342.2.4.9.8.1.6");
		parameters8.put("portMode", "1.3.6.1.4.1.42342.2.4.9.8.1.9");
		parameters8.put("pvid", "1.3.6.1.4.1.42342.2.4.9.8.1.4");
		parameters8.put("portVIDList", "1.3.6.1.4.1.42342.2.4.9.8.1.7");
		parameters8.put("portUntaggedVIDList", "1.3.6.1.4.1.42342.2.4.9.8.1.8");
		parameters8.put("speedMode", "1.3.6.1.4.1.42342.2.4.20.1.2.1.1.5");
		parameters8.put("duplexMode", "1.3.6.1.4.1.42342.2.4.20.1.2.1.1.7");
		parameters8.put("upSpeedLimit","1.3.6.1.4.1.42342.2.4.5.3.2.1.6");
		parameters8.put("downSpeedLimit","1.3.6.1.4.1.42342.2.4.5.3.2.1.5");
		parameters8.put("broadcastStormProtectionEN","1.3.6.1.4.1.42342.2.4.10.1.1.4");
		parameters8.put("broadcastStormThreshold","1.3.6.1.4.1.42342.2.4.10.1.1.5");
		parameters8.put("multicastStormProtectionEN","1.3.6.1.4.1.42342.2.4.10.1.1.6");
		parameters8.put("multicastStormThreshold","1.3.6.1.4.1.42342.2.4.10.1.1.7");
		parameters8.put("unknownUnicastStormProtectionEN","1.3.6.1.4.1.42342.2.4.10.1.1.8");
		parameters8.put("unknownUnicastStormThreshold","1.3.6.1.4.1.42342.2.4.10.1.1.9");
		SetParameter setCnuPortList = new SetParameter(index,parameters8);
		setMap.put("setCnuPortList", setCnuPortList);
		
		//init setCnuTemplate
		index = "";
		Map<String,String> parameters9 = new HashMap<String,String>();
		parameters9.put("igmp","1.3.6.1.4.1.42342.2.4.7.1.1.4");
		parameters9.put("lookbackTest","1.3.6.1.4.1.42342.2.4.111.2.1.12");
		parameters9.put("cableDownLimit","1.3.6.1.4.1.42342.2.4.5.2.4.1.19");
		parameters9.put("cableUpLimit","1.3.6.1.4.1.42342.2.4.5.2.4.1.20");
		parameters9.put("macLimit","1.3.6.1.4.1.42342.2.4.20.1.1.1.1.3");
		
		parameters9.put("upSpeedLimit_1","1.3.6.1.4.1.42342.2.4.5.3.2.1.6");
		parameters9.put("downSpeedLimit_1","1.3.6.1.4.1.42342.2.4.5.3.2.1.5");
		parameters9.put("status_1","1.3.6.1.4.1.42342.2.4.5.4.3.1.4");
		parameters9.put("pvid_1","1.3.6.1.4.1.42342.2.4.9.8.1.4");
		parameters9.put("portTaggedVLAN_1","1.3.6.1.4.1.42342.2.4.9.8.1.7");
		parameters9.put("broadcastStormProtection_1","1.3.6.1.4.1.42342.2.4.10.1.1.4");
		parameters9.put("broadcastStormThreshold_1","1.3.6.1.4.1.42342.2.4.10.1.1.5");
		parameters9.put("multicastStormProtection_1","1.3.6.1.4.1.42342.2.4.10.1.1.6");
		parameters9.put("multicastStormThreshold_1","1.3.6.1.4.1.42342.2.4.10.1.1.7");
		parameters9.put("unknownUnicastStormProtection_1","1.3.6.1.4.1.42342.2.4.10.1.1.8");
		parameters9.put("unknownUnicastStormThreshold_1","1.3.6.1.4.1.42342.2.4.10.1.1.9");
		parameters9.put("duplexMode_1","1.3.6.1.4.1.42342.2.4.20.1.2.1.1.7");
		parameters9.put("priority_1","1.3.6.1.4.1.42342.2.4.9.8.1.6");
		parameters9.put("portVLANMode_1","1.3.6.1.4.1.42342.2.4.9.8.1.9");

		parameters9.put("upSpeedLimit_2","1.3.6.1.4.1.42342.2.4.5.3.2.1.6");
		parameters9.put("downSpeedLimit_2","1.3.6.1.4.1.42342.2.4.5.3.2.1.5");
		parameters9.put("status_2","1.3.6.1.4.1.42342.2.4.5.4.3.1.4");
		parameters9.put("pvid_2","1.3.6.1.4.1.42342.2.4.9.8.1.4");
		parameters9.put("portTaggedVLAN_2","1.3.6.1.4.1.42342.2.4.9.8.1.7");
		parameters9.put("broadcastStormProtection_2","1.3.6.1.4.1.42342.2.4.10.1.1.4");
		parameters9.put("broadcastStormThreshold_2","1.3.6.1.4.1.42342.2.4.10.1.1.5");
		parameters9.put("multicastStormProtection_2","1.3.6.1.4.1.42342.2.4.10.1.1.6");
		parameters9.put("multicastStormThreshold_2","1.3.6.1.4.1.42342.2.4.10.1.1.7");
		parameters9.put("unknownUnicastStormProtection_2","1.3.6.1.4.1.42342.2.4.10.1.1.8");
		parameters9.put("unknownUnicastStormThreshold_2","1.3.6.1.4.1.42342.2.4.10.1.1.9");
		parameters9.put("duplexMode_2","1.3.6.1.4.1.42342.2.4.20.1.2.1.1.7");
		parameters9.put("priority_2","1.3.6.1.4.1.42342.2.4.9.8.1.6");
		parameters9.put("portVLANMode_2","1.3.6.1.4.1.42342.2.4.9.8.1.9");

		parameters9.put("upSpeedLimit_3","1.3.6.1.4.1.42342.2.4.5.3.2.1.6");
		parameters9.put("downSpeedLimit_3","1.3.6.1.4.1.42342.2.4.5.3.2.1.5");
		parameters9.put("status_3","1.3.6.1.4.1.42342.2.4.5.4.3.1.4");
		parameters9.put("pvid_3","1.3.6.1.4.1.42342.2.4.9.8.1.4");
		parameters9.put("portTaggedVLAN_3","1.3.6.1.4.1.42342.2.4.9.8.1.7");
		parameters9.put("broadcastStormProtection_3","1.3.6.1.4.1.42342.2.4.10.1.1.4");
		parameters9.put("broadcastStormThreshold_3","1.3.6.1.4.1.42342.2.4.10.1.1.5");
		parameters9.put("multicastStormProtection_3","1.3.6.1.4.1.42342.2.4.10.1.1.6");
		parameters9.put("multicastStormThreshold_3","1.3.6.1.4.1.42342.2.4.10.1.1.7");
		parameters9.put("unknownUnicastStormProtection_3","1.3.6.1.4.1.42342.2.4.10.1.1.8");
		parameters9.put("unknownUnicastStormThreshold_3","1.3.6.1.4.1.42342.2.4.10.1.1.9");
		parameters9.put("duplexMode_3","1.3.6.1.4.1.42342.2.4.20.1.2.1.1.7");
		parameters9.put("priority_3","1.3.6.1.4.1.42342.2.4.9.8.1.6");
		parameters9.put("portVLANMode_3","1.3.6.1.4.1.42342.2.4.9.8.1.9");

		parameters9.put("upSpeedLimit_4","1.3.6.1.4.1.42342.2.4.5.3.2.1.6");
		parameters9.put("downSpeedLimit_4","1.3.6.1.4.1.42342.2.4.5.3.2.1.5");
		parameters9.put("status_4","1.3.6.1.4.1.42342.2.4.5.4.3.1.4");
		parameters9.put("pvid_4","1.3.6.1.4.1.42342.2.4.9.8.1.4");
		parameters9.put("portTaggedVLAN_4","1.3.6.1.4.1.42342.2.4.9.8.1.7");
		parameters9.put("broadcastStormProtection_4","1.3.6.1.4.1.42342.2.4.10.1.1.4");
		parameters9.put("broadcastStormThreshold_4","1.3.6.1.4.1.42342.2.4.10.1.1.5");
		parameters9.put("multicastStormProtection_4","1.3.6.1.4.1.42342.2.4.10.1.1.6");
		parameters9.put("multicastStormThreshold_4","1.3.6.1.4.1.42342.2.4.10.1.1.7");
		parameters9.put("unknownUnicastStormProtection_4","1.3.6.1.4.1.42342.2.4.10.1.1.8");
		parameters9.put("unknownUnicastStormThreshold_4","1.3.6.1.4.1.42342.2.4.10.1.1.9");
		parameters9.put("duplexMode_4","1.3.6.1.4.1.42342.2.4.20.1.2.1.1.7");
		parameters9.put("priority_4","1.3.6.1.4.1.42342.2.4.9.8.1.6");
		parameters9.put("portVLANMode_4","1.3.6.1.4.1.42342.2.4.9.8.1.9");


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
			System.out.print("@@@@@@@@@@@@@@@@@@@@@@@varValues.size-----------"+varValues.size()+"\n");
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
			List<OID2Property> properties = entry.getOid2propertyList();
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
		String str = rf.setTaggedValanList("3,5,12,18,24");
//		String str = rf.setTaggedValanList("12");
		System.out.println(str);
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