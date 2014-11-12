package com.pbn.oss.adaptor.eoc.gcable;

 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.text.StyledEditorKit.BoldAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.snmp4j.PDU;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.pbn.oss.adaptor.eoc.AbstractEocAdaptor;
import com.pbn.oss.adaptor.eoc.bean.DiscoveryType;
import com.pbn.oss.adaptor.eoc.bean.EocAlarmType;
import com.pbn.oss.adaptor.eoc.bean.GBEocCBATCardInforTable;
import com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable;
import com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable;
import com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable;
import com.pbn.oss.adaptor.eoc.bean.GBEocCNUWhiteListTable;
import com.pbn.oss.adaptor.eoc.bean.ResourceClassName;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocBroadcastStormTable;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCBATCardInforTable;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCBATCardRFTable;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUInforTable;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUPortInfoTable;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUServiceTable;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocExtCnuEtherPortTable;
import com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocIGMPSnoopingTable;
import com.pbn.oss.adaptor.eoc.gcable.snmp.SNMPConfig;
import com.pbn.oss.adaptor.eoc.gcable.snmp.SNMPSupport;
import com.pbn.oss.adaptor.eoc.gcable.snmp.SNMPSupportImpl;
//import com.pbn.oss.fault.ActiveAlarm;
//import com.pbn.oss.fault.RawAlarm;
 


 

public class DiscoveryEocImpl extends AbstractEocAdaptor{
//public class DiscoveryEocImpl{
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(DiscoveryEocImpl.class);
	//Used as KangType system OID
	public static final String eocCBATAdminModelNumber = "1.3.6.1.4.1.42342.2.4.1.1.4.0";
	public static final String systemOID = "1.3.6.1.2.1.1.2.0";
	public static final String sysUpTime = "1.3.6.1.2.1.1.3.0";
	public static final String serviceStatus = "1.3.6.1.4.1.42342.2.4.5.3.2.1.10";
	
	@Override
	public int getDomain() {
		return 4;
	}
	public void setHostIp(String hostIp) {
		
	}
	@Override
	public int getChassis() {
		return 1;
	}
	@Override
	public int getVendor() {
		return 100;
	}
	@Override
	public HashMap<String, String> getSystemsByNetworkRange(String startIp, String endIp, int snmpVersion,
			String readCommunity) {
		HashMap<String, String> map = new HashMap<String, String>();
		int start = Integer.parseInt(startIp.substring(startIp.lastIndexOf(".")+1, startIp.length()));
		int end = Integer.parseInt(endIp.substring(endIp.lastIndexOf(".")+1, endIp.length()));

		SNMPSupportImpl support = new SNMPSupportImpl();
		try {
			for(int i=start;i<=end;i++){
				String hostIp = startIp.substring(0, startIp.lastIndexOf(".")) + "." + i;
				SNMPConfig snmpConfig = new SNMPConfig();
				snmpConfig.setAgentIp(hostIp);
				snmpConfig.setSnmpVer(snmpVersion);
				snmpConfig.setReadCommunity(readCommunity);
				support.initSnmp(snmpConfig);
				logger.info("adaptor_eoc_gcable getSystemsByNetworkRange hostIp="+hostIp+",read="+readCommunity+",snmpVer="+snmpVersion);
				System.out.println("adaptor_eoc_gcable getSystemsByNetworkRange hostIp="+hostIp+",read="+readCommunity+",snmpVer="+snmpVersion);
				String modelNumber = null;
				modelNumber = support.getOneStringValue(eocCBATAdminModelNumber, PDU.GET);
				System.out.println("modelNumber====="+modelNumber);
				if (modelNumber != null) {
					logger.info("adaptor_eoc_gcable connected. hostIp="+hostIp+", systemOID="+eocCBATAdminModelNumber);
					map.put(eocCBATAdminModelNumber+"_"+i,hostIp);
				}else{
					logger.error("*****ERROR adaptor_eoc_gcable connect failed. hostIp="+hostIp
							+", systemOID="+eocCBATAdminModelNumber);
					map.put("disconnected_"+i, hostIp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			support.closeSnmp();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<?, ?> discoveryModule(String hostIp, String xmlPath, int discoveryType, HashMap<?, ?> rmap){
		HashMap<String, List<Object>> map = new HashMap<String, List<Object>>();
		int snmpVersion = Integer.parseInt(rmap.get("snmpVersion")+"");
		String readComm = rmap.get("readComm")+"";
		String writeComm = rmap.get("writeComm")+"";
		logger.info("discoveryModule in gcable DiscoveryEocImpl: ip="+hostIp+",snmpVersion="+snmpVersion+",read="+readComm+",write="+writeComm);
		SNMPConfig snmpConfig = new SNMPConfig();
		snmpConfig.setAgentIp(hostIp);
		snmpConfig.setSnmpVer(snmpVersion);
		snmpConfig.setReadCommunity(readComm);
		SNMPSupportImpl support = new SNMPSupportImpl();
		try {
			support.initSnmp(snmpConfig);
			CommonAdapter comAdapter = new CommonAdapter(xmlPath);
			if(discoveryType == DiscoveryType.CNU_LIST.getValue()){
				List<Object> cnuConfigList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", cnuConfigList);
				List<Object> cnuWhiteList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUWhiteListTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUWhiteListTable", cnuWhiteList);
				List<Object> cnuIgmpList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocIGMPSnoopingTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocIGMPSnoopingTable", cnuIgmpList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CNU_PORT_LIST.getValue()){
				List<Object> portList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable",portList);
				List<Object> cnuPortList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUPortInfoTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUPortInfoTable",cnuPortList);
				List<Object> cnuServiceList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUServiceTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUServiceTable",cnuServiceList);
				List<Object> broadCastList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocBroadcastStormTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocBroadcastStormTable",broadCastList);
				List<Object> cnuEtherList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocExtCnuEtherPortTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocExtCnuEtherPortTable",cnuEtherList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CNU_CONFIG.getValue() || discoveryType == DiscoveryType.CNU_BASIC.getValue()){
				String cnuIndex = (String) rmap.get("index");
				List<Object> cnuConfigList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", cnuConfigList);
				List<Object> cnuIgmpList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocIGMPSnoopingTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocIGMPSnoopingTable", cnuIgmpList);
				uptdateCnuConfig(map,cnuIndex);
			}else if(discoveryType == DiscoveryType.CBAT_CARD.getValue()){
				List<Object> cbatCardList = null;
				cbatCardList= comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCBATCardInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCBATCardInforTable", cbatCardList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CBAT_BASIC.getValue()){
				List<Object> cbatList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable",cbatList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CBAT_ALL.getValue()){
				DiscoveryEocImpl impl = new DiscoveryEocImpl();
				xmlPath = impl.getClass().getClassLoader().getResource("META-INF/eoc_gb_gcable.xml").getFile();
				map = (HashMap<String, List<Object>>) impl.discovery(hostIp, xmlPath, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CNU_ALL.getValue()){
				List<Object> portList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable",portList);
				List<Object> cnuPortList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUPortInfoTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.DongYanEocCNUPortInfoTable",cnuPortList);
				List<Object> cnuServiceList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUServiceTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocCNUServiceTable",cnuServiceList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
				List<Object> cnuConfigList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", cnuConfigList);
				List<Object> cnuIgmpList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocIGMPSnoopingTable", support);
				map.put("com.pbn.oss.adaptor.eoc.gcable.bean.GCableEocIGMPSnoopingTable", cnuIgmpList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}
			//TODO: other modules
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (support != null) {
				support.closeSnmp();
			}
		}
		return map;
	}
	

    private void uptdateCnuConfig(HashMap<String, List<Object>> map, String cnuIndex) {
    	//construct GB CNU info
		List<Object> cnuList = map.get(GBEocCNUInforTable.class.getName());
		List<Object> igmpList = map.get(GCableEocIGMPSnoopingTable.class.getName());
		if(cnuList != null && cnuList.size() > 0){
			for(Object c :cnuList){
				GBEocCNUInforTable gbCnu = (GBEocCNUInforTable)c;
				if(gbCnu.getId() != cnuIndex){
					cnuList.remove(c);
				}
			}
			if(igmpList !=null && igmpList.size()>0){
				for(Object o : igmpList){
					GCableEocIGMPSnoopingTable igmp = (GCableEocIGMPSnoopingTable)o;
					for(Object c : cnuList){
						GBEocCNUInforTable gbCnu = (GBEocCNUInforTable)c;
						if(igmp.getId().equals(gbCnu.getId())){
							gbCnu.setIgmpEn(igmp.getIgmpEN());
							break;
						}
					}
				}
			}
		}
		logger.info("Construct GB CNU finished. CNU count="+cnuList.size());
	}
//	@Override
	public HashMap<?, ?> resourceTest(HashMap<?, ?> map){
		String identifier = (String)map.get("resourcetest");
		int snmpVer = Integer.parseInt(map.get("snmpVersion")+"");
		String readCommunity = map.get("readCommunity")+"";
		HashMap<String, Object> testMap = new HashMap<String, Object>();
		return (HashMap<?, ?>) testMap;
 	}
	
	@Override
	public String applyTemplate(String hostIp, int domain, int vendor, int chassis, int resType, Map<?, ?> map) {
		boolean flag = false;
		try {
			CommonAdapter comAdapter = new CommonAdapter();
			logger.info("applyTemplate in gcable DiscoveryEocImpl: map="+map);
			String applyType = map.get("applyType").toString();
			if(applyType.equals("setCnuTemplate")){
				
				Reflection rf = new Reflection();
				boolean backStatus = rf.setDataValues(hostIp, map);
				if(backStatus){
					return "successful";
				}
				return "faield";
				
			}else{
				flag = comAdapter.setValues(hostIp, map);
				if(flag){
					return "successfull";
				}
				return "faield";
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			return "failed";
		}
	}
	
	public void boundTemplateToCNU(JSONArray ja,SNMPSupportImpl support,String templateIndex){
		for(int i=0;i< ja.size();i++){
			String[] cnuIndex = ja.getString(i).split("\\.");
			String applyOid = SNMPConstant.templateApply+"."+cnuIndex[1];
			//The template is bound to the corresponding CNU index
			support.set(applyOid, new Integer32(Integer.parseInt(templateIndex)));
		}
	}

	@Override
	public HashMap<?, ?> discovery(String hostIp, String xmlpath, int snmpVersion, String readComm, String writeComm) {
		logger.info("discovery executed in DiscoveryEocImpl: ip="+hostIp+",snmpVer="+snmpVersion+",read="+readComm+",write="+writeComm);
		SNMPConfig snmpConfig = new SNMPConfig();
		snmpConfig.setAgentIp(hostIp);
		snmpConfig.setTimeout(15000);
		snmpConfig.setRetries(1);
		snmpConfig.setReadCommunity(readComm);
		SNMPSupportImpl support = new SNMPSupportImpl();
		try {
			support.initSnmp(snmpConfig);
			CommonAdapter comAdapter = new CommonAdapter(xmlpath);
			HashMap<String, List<Object>> map = (HashMap<String, List<Object>>) comAdapter.getAllValues(support);
			wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		} finally {
			if (support != null) {
				support.closeSnmp();
			}
		}
		return null;
	}
	
	public static String parseHexString(String hexStr){
		if(hexStr == null)
			return null;
		if(!hexStr.contains(":"))
			return hexStr;
		hexStr = hexStr.endsWith(":00") ? hexStr.substring(0, hexStr.length()-3) : hexStr;
		String[] arr = hexStr.split(":");
		byte[] b = new byte[arr.length];
		for(int i = 0; i < arr.length; i++){
			b[i] = Byte.parseByte(arr[i], 16);
		}
		return new String(b);
	}

	private void wrap(HashMap<String, List<Object>> map, String hostIp, SNMPSupportImpl support, int snmpVersion, String readComm, String writeComm) throws Exception{
		long upTime = support.getOneLongValue(sysUpTime, PDU.GET) / 100;
		//contruct GB CBAT info
		List<Object> cbatList = map.get(ResourceClassName.GBEocCBATInforTable.getValue());
		if(cbatList != null && cbatList.size() > 0){
			GBEocCBATInforTable cbat = (GBEocCBATInforTable)cbatList.get(0);
			cbat.setMac(cbat.getMac().toUpperCase());
			cbat.setCurrentTemperature(cbat.getCurrentTemperature());
			if(cbat.getModelType().equals("00:00:00") || cbat.getModelType().equals("00.00.00")){
				cbat.setModelType("");
			}else{
				cbat.setModelType(parseHexString(cbat.getModelType()));
			}
			cbat.setFwVersion(parseHexString(cbat.getFwVersion()));
			cbat.setHwVersion(cbat.getHwVersion());
			cbat.setVendor(cbat.getVendor());
			cbat.setIp(cbat.getIp());
			cbat.setAlarmDetection(cbat.getAlarmDetection());
			cbat.setOnlineMacLimit(cbat.getOnlineMacLimit());
			cbat.setOnlineAmount(cbat.getOnlineAmount());
			cbat.setUpTime(upTime);
			cbat.setSnmpVersion(snmpVersion);
			cbat.setReadCommunity(readComm);
			cbat.setWriteCommunity(writeComm);
		}
		
		//construct GB card info
		List<Object> cardList = map.get(GBEocCBATCardInforTable.class.getName());
		if(cardList != null && cardList.size()>0){
			logger.info("Construct GB CBAT Card finished. count="+cardList.size());
			GBEocCBATCardInforTable gc = (GBEocCBATCardInforTable) cardList.get(0);
			gc.setUpTime(gc.getUpTime()/100);
		}
		map.put(ResourceClassName.GBEocCBATCardInforTable.getValue(), cardList);
		
		//construct GB CNU info 
		List<Object> gbCnuList = new ArrayList<Object>();
		List<Object> gbWhiteList = map.get(GBEocCNUWhiteListTable.class.getName());
		List<Object> cnuList = map.get(GBEocCNUInforTable.class.getName());
		List<Object> igmpList = map.get(GCableEocIGMPSnoopingTable.class.getName());
		if(gbWhiteList != null && gbWhiteList.size()>0){
			for(Object w : gbWhiteList){
				GBEocCNUWhiteListTable gbw = (GBEocCNUWhiteListTable)w;
				if(gbw.getEocCNUWhiteListRegisterOnlineStatus() ==0){
					GBEocCNUInforTable gbCnu = new GBEocCNUInforTable();
					gbCnu.setId(gbw.getEocCNUWhiteListCBATCardIndex()+"."+gbw.getId());
					gbCnu.setIndex(gbw.getEocCNUWhiteListCNUIndex());
					gbCnu.setMac(gbw.getEocCNUWhiteListMACAddress());
					gbCnu.setAuthorizationStatus(gbw.getEocCNUWhiteListAuthorization());
					gbCnu.setRfOutputLevel(gbw.getEocCNUWhiteListRFOutputLevel());
					gbCnu.setOnlineStatus(gbw.getEocCNUWhiteListRegisterOnlineStatus());
					gbCnu.setModelType("");
					gbCnu.setFwVersion("");
					gbCnuList.add(gbCnu);
				}

			}
		}
		if(cnuList != null && cnuList.size() > 0){
			for(Object o : cnuList){
				GBEocCNUInforTable cnu = (GBEocCNUInforTable)o;
				GBEocCNUInforTable gbCnu = new GBEocCNUInforTable();
				gbCnu.setId(cnu.getId());
				gbCnu.setMac(cnu.getMac());
				gbCnu.setAuthorizationStatus(cnu.getAuthorizationStatus());
				gbCnu.setIndex(cnu.getIndex());
				gbCnu.setOnlineStatus(cnu.getOnlineStatus());
				gbCnu.setModelType(parseHexString(cnu.getModelType()));
				gbCnu.setFwVersion(parseHexString(cnu.getFwVersion()));
				gbCnu.setRfAttenuation(cnu.getRfAttenuation());
				gbCnu.setRfDSQuality(cnu.getRfDSQuality());
				gbCnu.setRfUSQuality(cnu.getRfUSQuality());
				gbCnu.setRfPhyDSRate(cnu.getRfPhyDSRate());
				gbCnu.setRfPhyUSRate(cnu.getRfPhyUSRate());
				gbCnu.setUsPIR(cnu.getUsPIR());
				gbCnu.setDsPIR(cnu.getDsPIR());
				if(cnu.getDownSNR() == 0 || cnu.getDownSNR() == null){
					gbCnu.setDownSNR(0);
				}else{
					gbCnu.setDownSNR(cnu.getDownSNR());
				}
				if(cnu.getUpSNR() == 0 || cnu.getUpSNR() == null){
					gbCnu.setUpSNR(0);
				}else{
					gbCnu.setUpSNR(cnu.getUpSNR());
				}
				gbCnu.setAdminStatus(cnu.getAdminStatus());
				if(gbWhiteList != null && gbWhiteList.size()>0){
					for(Object r : gbWhiteList){
						GBEocCNUWhiteListTable gbw = (GBEocCNUWhiteListTable)r;
						if(gbw.getEocCNUWhiteListCNUIndex().equals(cnu.getIndex())){
							gbCnu.setRfOutputLevel(gbw.getEocCNUWhiteListRFOutputLevel());
							break;
						}
					}
				}
				gbCnuList.add(gbCnu);
			}
			logger.info("Construct GB CNU finished. CNU count="+gbCnuList.size());
		}
		if(igmpList !=null && igmpList.size()>0){
			for(Object o : igmpList){
				GCableEocIGMPSnoopingTable igmp = (GCableEocIGMPSnoopingTable)o;
				for(Object c : gbCnuList){
					GBEocCNUInforTable gbCnu = (GBEocCNUInforTable)c;
					if(igmp.getId().equals(gbCnu.getId())){
						gbCnu.setIgmpEn(igmp.getIgmpEN());
						break;
					}
				}
			}
		}
		map.put(ResourceClassName.GBEocCNUInforTable.getValue(), gbCnuList);
		
		
		//construct GB CNU port info
		List<Object> gbCnuPortList = new ArrayList<Object>();
		List<Object> cnuPortList = map.get(GCableEocCNUPortInfoTable.class.getName());
		List<Object> cnuServiceList = map.get(GCableEocCNUServiceTable.class.getName());
		List<Object> extCnuEtherList = map.get(GCableEocExtCnuEtherPortTable.class.getName());
		List<Object> broadcastStormList = map.get(GCableEocBroadcastStormTable.class.getName());
		List<Object> portList = map.get(GBEocCNUPortInforTable.class.getName());
		if(portList != null && portList.size() > 0){
			for(Object o : portList){
				// port id(index) not contains cbat card index 
				GBEocCNUPortInforTable port = (GBEocCNUPortInforTable) o;
				GBEocCNUPortInforTable gbPort = new GBEocCNUPortInforTable();
				gbPort.setId(port.getId());
				gbPort.setPortEn(port.getPortEn());
				gbPort.setPortService(port.getPortService());
				gbPort.setIndex(port.getIndex());
				gbPort.setCnuIndex(port.getCnuIndex());
				gbCnuPortList.add(gbPort);
			}
			logger.info("Construct GB CNU_PORT finished. CNU_PORT count="+portList.size());
		}
		if(cnuPortList != null && cnuPortList.size() > 0){
			for(Object o:cnuPortList){
				GCableEocCNUPortInfoTable port = (GCableEocCNUPortInfoTable)o;
				if(port.getPortIndex().toString().startsWith("1.0") || port.getPortIndex().toString().startsWith("0.0")){
					continue;
				}
				for(Object c : gbCnuPortList){
					GBEocCNUPortInforTable gbPort = (GBEocCNUPortInforTable)c;
					if(port.getCnuIndex().equals(gbPort.getCnuIndex()) && port.getPortIndex().equals(gbPort.getIndex())){
						gbPort.setPortPRIO(port.getPortPRIO());
						gbPort.setPortMode(port.getPortMode());
						gbPort.setPortPVID(port.getPortPVID());
						gbPort.setPortTPID(port.getPortTPID());
						if(gbPort.getPortMode()==0){
							gbPort.setPortVIDList("");
							gbPort.setPortUntaggedVIDList("");
						}else{
							gbPort.setPortVIDList(ConvertHexVIDListToStringOct(port.getPortVIDList()));
							gbPort.setPortUntaggedVIDList(ConvertHexVIDListToStringOct(port.getPortUntaggedVIDList()));
						}
					}
				}
			}
		}
		if(cnuServiceList != null && cnuServiceList.size()>0){
			for(Object c : gbCnuPortList){
				GBEocCNUPortInforTable gbPort = (GBEocCNUPortInforTable)c;
				for(Object o : cnuServiceList){
					GCableEocCNUServiceTable cnuService = (GCableEocCNUServiceTable)o;
					if(cnuService.getServiceIndex().equals(gbPort.getPortService())){
						gbPort.setPortDSSpeed(cnuService.getServiceDownPIR().longValue());
						gbPort.setPortUSSpeed(cnuService.getServiceUpPIR().longValue());
						break;
					}
					if(gbPort.getPortService()==0){
						long upSpeed = 0;
						long downSpeed = 0;
						gbPort.setPortUSSpeed(upSpeed);
						gbPort.setPortDSSpeed(downSpeed);
					}
				}
			}
		}
		if(extCnuEtherList != null && extCnuEtherList.size()>0){
			for(Object c : gbCnuPortList){
				GBEocCNUPortInforTable gbPort = (GBEocCNUPortInforTable)c;
				for(Object o : extCnuEtherList){
					GCableEocExtCnuEtherPortTable gcExt = (GCableEocExtCnuEtherPortTable)o;
					if(gbPort.getId().equals(gcExt.getId())){
						if(gcExt.getConfAutoNeg().equals(1)){
							gbPort.setPortSpeed(0);
							gbPort.setPortDuplex(0);
						}else{
							gbPort.setPortSpeed(gcExt.getConfSpeed());
							gbPort.setPortDuplex(gcExt.getConfDuplex());
						}
						gbPort.setPortAutoNeg(gcExt.getConfAutoNeg());
						break;
					}
				}
			}
		}
		if(broadcastStormList != null && broadcastStormList.size()>0){
			for(Object c : gbCnuPortList){
				GBEocCNUPortInforTable gbPort = (GBEocCNUPortInforTable)c;
				for(Object o : broadcastStormList){
					GCableEocBroadcastStormTable gcBST = (GCableEocBroadcastStormTable)o;
					if(gbPort.getId().equals(gcBST.getId())){
						gbPort.setBroadCastStormProtectionEnable(gcBST.getStormEN());
						gbPort.setBroadCastStormThreshold(gcBST.getStormThreshold());
						gbPort.setMulticastStormProtectionEnable(gcBST.getMultiStormEN());
						gbPort.setMulticastStormThreshold(gcBST.getMultiStormThreshold());
						gbPort.setUnknownUnicastStormProtectionEnable(gcBST.getUnStormEN());
						gbPort.setUnknownUnicastStormThreshold(gcBST.getUnStormThreshold());
						break;
					}
				}
			}
		}

		map.put(ResourceClassName.GBEocCNUPortInforTable.getValue(), gbCnuPortList);
	}
	
	public String ConvertHexVIDListToStringOct(String portVIDList){
		String backStr = "";
		String[] strArray = portVIDList.split(":");
		for(int i=0;i<strArray.length;i++){
			String str = strArray[i];
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
				map.put(strList[k],String.valueOf(j));
				k++;
			}
			String charStr0 = String.valueOf(str.charAt(0));
			String charStr1 =String.valueOf(str.charAt(1));
			int intStr0 = Integer.parseInt(charStr0.toUpperCase(), 16);
			int intStr1 = Integer.parseInt(charStr1.toUpperCase(), 16);
			//construct first parameter
			if(intStr0 == 0){
				
			}else{
				if(intStr0<10){
					int value0 = intStr0%2;
					if(value0 ==0){
						backStr = backStr+ map.get(intStr0+"0")+",";
					}
				}else {
					if(intStr0>=10){
						int ll = intStr0-8;
						backStr = backStr+ map.get(8+"0")+",";
						if(ll>=4){
							backStr = backStr+ map.get(4+"0")+",";
							if(ll-4>2){
								backStr = backStr+ map.get(2+"0")+",";
								backStr = backStr+ map.get(1+"0")+",";
							}else if(ll-4 == 2){
								backStr = backStr+ map.get(2+"0")+",";
							}else if(ll-4 < 2){
								backStr = backStr+ map.get(1+"0")+",";
							}
						}else{
							if(ll-2>0){
								backStr = backStr+ map.get(2+"0")+",";
								backStr = backStr+ map.get(1+"0")+",";
							}else{
								backStr = backStr+map.get(2+"0");
							}
						}
					}
				}
			}
			//construct second parameter
			if(intStr1 == 0){
				
			}else{
				if(intStr1<10){
					int value1 = intStr1%2;
					if(value1 ==0){
						backStr = backStr+ map.get("0"+intStr1)+",";
					}
				}else {
					if(intStr1>=10){
						int kk = intStr1-8;
						backStr = backStr+ map.get("0"+8)+",";
						if(kk>=4){
							backStr = backStr+ map.get("0"+4)+",";
							if(kk-4>2){
								backStr = backStr+ map.get("0"+2)+",";
								backStr = backStr+ map.get("0"+1)+",";
							}else if(kk-4 == 2){
								backStr = backStr+ map.get("0"+2)+",";
							}else if(kk-4 < 2){
								backStr = backStr+ map.get("0"+1)+",";
							}
						}else{
							if(kk-2>0){
								backStr = backStr+ map.get("0"+2)+",";
								backStr = backStr+ map.get("0"+1)+",";
							}else{
								backStr = backStr+map.get("0"+2)+",";
							}
						}
					}
				}
			}

		}
		if(backStr.length()>1){
			backStr = backStr.substring(0, backStr.length()-1);
		}
		return backStr;
	}
	
	@Override
	public HashMap<?, ?> discoveryPMData(String hostIp, String xmlPath, int discoveryType, HashMap<?, ?> paramMap) {
		// Discovery all PM param by discoveryType
		logger.info("**************************enter the EOC discoveryPMData. hostIp="+hostIp+",discoveryType="+discoveryType);
		HashMap<String, List<Object>> mapReturn = new HashMap<String, List<Object>>();
		SNMPSupport support = new SNMPSupportImpl();
		try {
			SNMPConfig cfg = new SNMPConfig();
			cfg.setAgentIp(hostIp);
			cfg.setSnmpVer(Integer.parseInt(paramMap.get("snmpVersion")+""));
			cfg.setReadCommunity(paramMap.get("readComm")+"");
			cfg.setWriteCommunity(paramMap.get("writeComm")+"");
			logger.info("*****discoveryPMData hostIp="+hostIp+",SnmpVer="+cfg.getSnmpVer()+",ReadCommunity="+cfg.getReadCommunity()+",WriteCommunity="+cfg.getWriteCommunity());
			support.initSnmp(cfg);
			DiscoveryType type = DiscoveryType.valueOf(discoveryType);
			switch (type) {
				case PM_Master:
					return discoveryCbatPmIntegration(support, paramMap);
				case PM_Modem:
					return discoveryCnuPmIntegration(support, paramMap);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (support != null)
				support.closeSnmp();

		}
		return mapReturn;
	}
	
	private HashMap<Integer, JSONObject> discoveryCbatPmIntegration(SNMPSupport support, Map<?, ?> map) throws Exception{
//		Set<Integer> discoveryTypeList = (Set<Integer>)map.get("settings");
		HashMap<Integer, JSONObject> values = new HashMap<Integer, JSONObject>();
		//Get CBAT RxSpeed and TxSpeed ignore whether need.
//		List<VariableBinding> list = support.getGroupValues(new OID[]{new OID(SNMPConstant.eocRealTimeStatisticTxByte), new OID(SNMPConstant.avSysStatDownSpeed)}, PDU.GET);
//		JSONObject cbat = new JSONObject();
//		int upSpeed = list.get(0).getVariable().toInt();
//		int downSpeed = list.get(1).getVariable().toInt();
//		cbat.element("RxSpeed", upSpeed);
//		cbat.element("TxSpeed", downSpeed);
//		values.put(0, cbat);
//		return values;
		
		//无法直接取到CBAT的上下行速率，通过所有在线的CNU速率累加计算而来
		List<List<VariableBinding>> list = support.getTableValues(new OID[]{new OID(SNMPConstant.eocCNUPhyUpstreamRate), new OID(SNMPConstant.eocCNUPhyDownstreamRate)});
		int upSpeed = 0;
		int downSpeed = 0;
		int onlineCNUCount = 0;
		for(List<VariableBinding> vbList : list){
			int cnuUpSpeed = vbList.get(0).getVariable().toInt();
			int cnuDownSpeed = vbList.get(1).getVariable().toInt();
			upSpeed += cnuUpSpeed;
			downSpeed += cnuDownSpeed;
			onlineCNUCount++;
		}
		JSONObject cbat = new JSONObject();
		cbat.element("RxSpeed", upSpeed);// for CBAT RxSpeed is upstream speed, but for CNU RxSpeed is downstream speed
		cbat.element("TxSpeed", downSpeed);
		cbat.element("onlineCNUCount", onlineCNUCount);
		values.put(0, cbat);
		return values;
	}
	
	private HashMap<String, JSONObject> discoveryCnuPmIntegration(SNMPSupport support, Map<?, ?> map) throws Exception{
//		Set<Integer> discoveryTypeList = (Set<Integer>)map.get("settings");
		HashMap<String, JSONObject> values = new HashMap<String, JSONObject>();
		OID[] columns = new OID[]{new OID(SNMPConstant.eocCNUMACAddress), 
									new OID(SNMPConstant.eocCNURFAttenuation),
									new OID(SNMPConstant.eocCNURFDownstreamQuality),
									new OID(SNMPConstant.eocCNURFUpstreamQuality),
									new OID(SNMPConstant.eocCNUPhyDownstreamRate),
									new OID(SNMPConstant.eocCNUPhyUpstreamRate),
									};
		List<List<VariableBinding>> list = support.getTableValues(columns);
//		System.out.println("list="+list);
		for(List<VariableBinding> vbList : list){
			JSONObject cnu = new JSONObject();
			String mac = vbList.get(0).getVariable().toString().toUpperCase();
			cnu.element("RFAttenuation", vbList.get(1).getVariable().toInt());
			cnu.element("RFDownstreamQuality", vbList.get(2).getVariable().toInt());
			cnu.element("RFUpstreamQuality", vbList.get(3).getVariable().toInt());
			cnu.element("PhyDownstreamRate", vbList.get(4).getVariable().toInt());
			cnu.element("PhyUpstreamRate", vbList.get(5).getVariable().toInt());
			values.put(mac, cnu);
		}
		return values;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		DiscoveryEocImpl impl = new DiscoveryEocImpl();
		HashMap<String, Object> rmap = new HashMap<String, Object>();
		String xmlPath = impl.getClass().getClassLoader().getResource("META-INF/eoc_gb_gcable.xml").getFile();
		
//		HashMap<String, String> getrange= new HashMap<String, String>();
//		getrange = impl.getSystemsByNetworkRange("192.168.16.101", "192.168.16.101", 1, "private");
//		System.out.println("jhfkdsjkfjdskl++++"+getrange); 
//		logger.info("gcable.getrange============="+getrange);
//		impl.discovery("192.168.21.103", xmlPath, 1, "public");
//		System.out.print(impl.discovery("192.168.21.103", xmlPath,1,"public"));
//		rmap.put("snmpVersion", 1);
//		rmap.put("readComm","public");
//		rmap.put("writeComm", "private");
//		rmap.put("index", "1.2");
//		Map<String,List<Object>> map = (Map<String, List<Object>>) impl.discoveryModule("192.168.16.101", xmlPath, 6, rmap);
//		logger.info("**************************enter the EOC MAP="+map);
		Map<String,List<Object>> map = (Map<String, List<Object>>) impl.discovery("172.18.129.17", xmlPath,1,"public","private");
//		List<Object> list = map.get(GBEocCBATCardInforTable.class.getName());
//		System.out.println("map==="+map.values());
//		for(Object o :list){
//			GBEocCBATCardInforTable card = (GBEocCBATCardInforTable)o;
//			System.out.println("card==="+card.getVendor());
//			System.out.println("card==="+card.getSerialNumber());
//		}
//		System.out.println("map======"+map.get(GBEocCNUPortInforTable.class.getName()));
//		for(String key : map.keySet()){
//			System.out.println("key is:" + key);
//			List<Object> list = map.get(key);
//			for(Object object : list){
//				System.out.println(object);
//				if(object instanceof GBEocCBATInforTable){
//					GBEocCBATInforTable gbTable = (GBEocCBATInforTable) object;
//					System.out.println("GBEocCBATInforTable");
//				}else if(object instanceof GBEocCBATCardInforTable){
//					GBEocCBATCardInforTable gbTable = (GBEocCBATCardInforTable) object;
//					System.out.println("GBEocCBATCardInforTable="+gbTable);
//				}else if(object instanceof GBEocCNUInforTable){
//					GBEocCNUInforTable gbTable = (GBEocCNUInforTable) object;
//					System.out.println("GBEocCNUInforTable id="+gbTable.getId()+",index="+gbTable.getIndex()+",FwVersion="+gbTable.getFwVersion()+"\n\t\t"
//							+gbTable.getModelType()+","+gbTable.getOnlineStatus()+","+gbTable.getAuthorizationStatus()+","+gbTable.getMac());
//				}else if(object instanceof GBEocCNUPortInforTable){
//					GBEocCNUPortInforTable gbTable = (GBEocCNUPortInforTable) object;
//					System.out.println("GBEocCNUPortInforTable gbTable: "+gbTable.getId());
//				}
//			}
//		}
		
//		SNMPConfig snmpConfig = new SNMPConfig();
//		snmpConfig.setAgentIp("192.168.16.101");
//		snmpConfig.setReadCommunity("public");
//		snmpConfig.setWriteCommunity("private");
//		SNMPSupportImpl support = new SNMPSupportImpl();
//		support.initSnmp(snmpConfig);
//		support.set("1.3.6.1.4.1.42342.2.4.9.8.1.6.1.1.1", new Integer32(3));
//		support.set("1.3.6.1.4.1.42342.2.4.9.8.1.6.1.1.2", new Integer32(6));
//		support.set("1.3.6.1.4.1.42342.2.4.9.8.1.4.1.1.1", new Integer32(12));
//		support.set("1.3.6.1.4.1.42342.2.4.9.8.1.4.1.1.2", new Integer32(13));
		
		//test for CNU template set
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType","setCnuTemplate");
//		map.put("readComm","public");
//		map.put("snmpVersion","1");
//		map.put("multicastStormThreshold_2", 40);
//		map.put("writeComm","private");
//		map.put("templateStatus","1");
//		map.put("cnuIndex","[\"1.114\"]");
//		map.put("index","[\"1.114.1\",\"1.114.2\"]");
//		map.put("tempName","exampl1");
//		map.put("lookbackTest", 0);
//		map.put("timeout", 10000);
//		map.put("duplexMode_1",2);
//		map.put("templateIndex", 0);
//		map.put("duplexMode_2",3);
//		map.put("retry", 3);
//		map.put("templateId",16589);
//		map.put("macLimit", 12);
//		map.put("status_1",1);
//		map.put("status_2",1);
//		map.put("portVLANMode_1","Access");
//		map.put("portVLANMode_2","Access");
//		map.put("downSpeedLimit_1",98);
//		map.put("downSpeedLimit_2",23);
//		map.put("upSpeedLimit_1",435);
//		map.put("upSpeedLimit_2",234);
//		map.put("pvid_1",12);//存在值范围
//		map.put("pvid_2",5);
//		map.put("cableDownLimit",456);//不允许设置
//		map.put("cableUpLimit",345);//不允许设置
////		map.put("portTaggedVLAN_1",123);
////		map.put("portUnTaggedVLAN_1",3);//不存在设置
//		map.put("priority_1",3);
//		map.put("priority_2",2);
//		map.put("portInsulated",0); 
//		map.put("broadcastStormProtection_1",0);
//		map.put("broadcastStormProtection_2",0);
//		map.put("broadcastStormThreshold_1",160);
//		map.put("broadcastStormThreshold_2",160);
//		map.put("multicastStormProtection_1",1);
//		map.put("multicastStormThreshold_1",80);
//		map.put("multicastStormProtection_2",1);
//		map.put("multicastStormProtection_2",80);
//		map.put("unknownUnicastStormProtection_1",1);
//		map.put("unknownUnicastStormThreshold_1",160);
//		map.put("unknownUnicastStormProtection_2",0);
//		map.put("unknownUnicastStormThreshold_2",640);
//		map.put("igmp", 1);
////		map.put("writeComm", "0");
////		
//		impl.applyTemplate("172.18.201.22", 4, 1, 1, 1, map);
		
		//test for daya
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType","setCnuConfig");
//		map.put("readComm","public");
//		map.put("writeComm","private");
//		map.put("snmpVersion","1");
//		map.put("index","[\"1.1\"]");
		
//		long upTime = support.getOneLongValue(sysUpTime, PDU.GET) / 100;
//		System.out.println(upTime);
		
		//test for system oid
//		String str = support.getOneStringValue(eocCBATAdminModelNumber, PDU.GET);
//		System.out.println(str);
		
		//test for discoveryCbatPmIntegration
//		Map<String,Object> map = new HashMap<String,Object>();
//		HashMap<Integer, JSONObject> result = impl.discoveryCbatPmIntegration(support, map);
//		System.out.println(result);
		
		//test for discoveryCnuPmIntegration
//		Map<String,Object> map = new HashMap<String,Object>();
//		HashMap<String, JSONObject> result = impl.discoveryCnuPmIntegration(support, map);
//		System.out.println(result);
		
//		//test for setCardInfo
//		Map<String,Object> map = new LinkedHashMap<String,Object>();
//		map.put("applyType", "setCnuPortList");
//		map.put("writeComm", "private");
//		map.put("snmpVersion","1"); 
//		map.put("index", "[\"1.1.2\",\"1.1.1\"]");
//		map.put("1.1.2", "{\"priority\":3,\"status\":1,\"portNumber\":\"2\",\"broadcastStormThreshold\":120,\"multicastStormProtectionEN\":1,\"broadcastStormProtectionEN\":0,\"multicastStormThreshold\":160,\"downSpeedLimit\":2048,\"unknownUnicastStormProtectionEN\":1,\"duplexMode\":1,\"unknownUnicastStormThreshold\":160,\"upSpeedLimit\":1024,\"speedMode\":1,\"portIndex\":\"1.1.2\",\"portMode\":\"Trunk\",\"pvid\":15,\"portVIDList\":\"2,5,9\",\"index\":\"1.1.2\"}");
//		map.put("1.1.1", "{\"priority\":2,\"status\":1,\"portNumber\":\"1\",\"broadcastStormThreshold\":120,\"multicastStormProtectionEN\":1,\"broadcastStormProtectionEN\":0,\"multicastStormThreshold\":160,\"downSpeedLimit\":2048,\"unknownUnicastStormProtectionEN\":1,\"duplexMode\":1,\"unknownUnicastStormThreshold\":160,\"upSpeedLimit\":1024,\"speedMode\":2,\"portIndex\":\"1.1.1\",\"portMode\":\"Access\",\"pvid\":12,\"index\":\"1.1.1\"}");
//		impl.applyTemplate("192.168.16.101", 1, 1,1, 1, map);
		
		//test setCnuConfig -faield 
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType","setCnuConfig");
//		map.put("readComm","public");
//		map.put("writeComm","private");
//		map.put("snmpVersion","1"); 
//		map.put("index","1.1");
//		map.put("downstreamPIR",512);
//		map.put("upstreamPIR",512);
//		map.put("igmpSnoopingEN", 0);
//		impl.applyTemplate("192.168.16.101", 1, 1,1, 1, map);
		
//		test for setSysInfo
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType", "resetBatchOnlineCnu");
//		map.put("writeComm", "private");
//		map.put("alarmStatus", 0);
//		map.put("cnuCountMax", 32);
//		map.put("index", "[\"1.1\",\"1.2\"]");
//		impl.applyTemplate("192.168.16.101", 1, 1,1, 1, map);
		
//		test for resetEoc
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType", "resetEoc");
//		map.put("setCommunity", "private");
		
		//test for setCnuConfig
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType", "setCnuConfig");
//		map.put("setCommunity", "private");
//		map.put("cardIndex", 1);
//		map.put("cnuIndex", 3);
//		map.put("rfAttenuation", 31);
//		map.put("downstreamPIR", 100);
//		map.put("upstreamPIR", 100);
//		map.put("broadcastStormProtectionEN", 30000001);
//		map.put("broadcastStormThreshold", 7500001 );
//		map.put("multicastStormProtectionEN", 30000001);
//		map.put("multicastStormThreshold", 30000001);
//		map.put("unknownUnicastStormProtectionEN", 7500001 );
//		map.put("unknownUnicastStormThreshold", 30000001);
//		map.put("igmpSnoopingEN", 30000001);
		
//		boolean flag = impl.applyTemplate("192.168.21.103", 1, 1,1, 1, map);

		
		//System.out.println(result);
		
//		SNMPConfig snmpConfig = new SNMPConfig();
//		snmpConfig.setAgentIp("172.18.129.10");
//		snmpConfig.setTimeout(15000);
//		snmpConfig.setRetries(1);
//		snmpConfig.setReadCommunity("public");
//		SNMPSupportImpl support = new SNMPSupportImpl();
//		support.initSnmp(snmpConfig);
//		long upTime = support.getOneLongValue(sysUpTime, PDU.GET) / 100;
//		System.out.println("uptime======"+upTime);
	}
	@Override
	public HashMap<?, ?> discovery(String hostIp, String xmlpath) {
		// TODO Auto-generated method stub
		return null;
	}


}
