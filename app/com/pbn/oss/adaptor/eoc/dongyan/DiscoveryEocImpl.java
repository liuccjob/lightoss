package com.pbn.oss.adaptor.eoc.dongyan;

 

import java.util.ArrayList;
import java.util.HashMap;
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
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCBATCardInforTable;
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCBATCardRFTable;
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUInforTable;
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUPortInfoTable;
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUServiceTable;
import com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocIGMPSnoopingTable;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPConfig;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPSupport;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPSupportImpl;
 

public class DiscoveryEocImpl extends AbstractEocAdaptor{
//public class DiscoveryEocImpl{
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(DiscoveryEocImpl.class);
	//Used as KangType system OID
	public static final String eocCBATAdminModelNumber = "1.3.6.1.4.1.17409.2.4.1.1.4.0";
	public static final String sysUpTime = "1.3.6.1.2.1.1.3.0";
	public static final String serviceStatus = "1.3.6.1.4.1.17409.2.4.5.3.2.1.10";
	
	@Override
	public int getDomain() {
		return 4;
	}
	public void setHostIp(String hostIp) {
		
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
				logger.info("adaptor_eoc_daya getSystemsByNetworkRange hostIp="+hostIp+",read="+readCommunity+",snmpVer="+snmpVersion);
				String modelNumber = null;
				if(hostIp.equals("192.168.16.106")){
					modelNumber = support.getOneStringValue("1.3.6.1.4.1.17409.2.4.1.1.3.1", PDU.GET);
				}else{
					modelNumber = support.getOneStringValue(eocCBATAdminModelNumber, PDU.GET);
				}
//				String modelNumber1 = support.getOneStringValue( "1.3.6.1.4.1.17409.2.4.1.1.4.1", PDU.GET);
				if (modelNumber != null) {
					logger.info("adaptor_eoc_dongyan connected. hostIp="+hostIp+", systemOID="+eocCBATAdminModelNumber);
//					map.put(eocCBATAdminModelNumber+"_"+i,hostIp+"_"+snmpVersion+"_"+readCommunity);
					map.put(eocCBATAdminModelNumber+"_"+i,hostIp);
				}else{
					logger.error("*****ERROR adaptor_eoc_dongyan connect failed. hostIp="+hostIp
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
		logger.info("discoveryModule in dongyan DiscoveryEocImpl: ip="+hostIp+",snmpVersion="+snmpVersion+",read="+readComm+",write="+writeComm);
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
				List<Object> cnuIgmpList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocIGMPSnoopingTable", support);
				map.put("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocIGMPSnoopingTable", cnuIgmpList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CNU_PORT_LIST.getValue()){
				List<Object> portList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable",portList);
				List<Object> cnuPortList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUPortInfoTable", support);
				map.put("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUPortInfoTable",cnuPortList);
				List<Object> cnuServiceList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUServiceTable", support);
				map.put("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCNUServiceTable",cnuServiceList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CNU_CONFIG.getValue() || discoveryType == DiscoveryType.CNU_BASIC.getValue()){
				List<Object> cnuConfigList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable", cnuConfigList);
				List<Object> cnuIgmpList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocIGMPSnoopingTable", support);
				map.put("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocIGMPSnoopingTable", cnuIgmpList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CBAT_CARD.getValue()){
				List<Object> cbatCardList = null;
				if(hostIp.equals("192.168.24.233")){
					cbatCardList= comAdapter.getValues("com.pbn.oss.adaptor.eoc.dongyan.bean.DongYanEocCBATCardInforTable", support);
				}else{
					cbatCardList= comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCBATCardInforTable", support);
				}
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCBATCardInforTable", cbatCardList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CBAT_BASIC.getValue()){
				List<Object> cbatList = comAdapter.getValues("com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable", support);
				map.put("com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable",cbatList);
				wrap(map, hostIp, support, snmpVersion, readComm, writeComm);
			}else if(discoveryType == DiscoveryType.CBAT_ALL.getValue()){
				DiscoveryEocImpl impl = new DiscoveryEocImpl();
				xmlPath = impl.getClass().getClassLoader().getResource("META-INF/eoc_gb_dongyan.xml").getFile();
				map = (HashMap<String, List<Object>>) impl.discovery(hostIp, xmlPath, snmpVersion, readComm, writeComm);
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
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			return "failed";
		}
		return "successful";
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
		} finally {
			if (support != null) {
				support.closeSnmp();
			}
		}
//		Reflection reflect = new Reflection();
//		try {
//			Map<String,List<Object>> map = reflect.getAllDataValue(xmlpath,hostIp);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
				cbat.setModelType("VISTA-E01/B05-212/224-NSCRTV");
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
		List<Object> gbCardList = new ArrayList<Object>();
		List<Object> cardList = map.get(GBEocCBATCardInforTable.class.getName());
		List<Object> cardList1 = map.get(DongYanEocCBATCardInforTable.class.getName());
		if(cardList == null){
			if(cardList1!= null && cardList1.size()>0){
				for(Object o : cardList1){
					DongYanEocCBATCardInforTable card = (DongYanEocCBATCardInforTable)o;
					GBEocCBATCardInforTable gbCard = new GBEocCBATCardInforTable();		
					gbCard.setId(card.getId());
					gbCard.setIndex(card.getIndex());
					gbCard.setOnlineStatus(card.getOnlineStatus());//TODO:get card online status
					gbCard.setUpTime(card.getUpTime()/100);
					gbCard.setModelType(parseHexString(card.getModelType()));
					gbCard.setFwVersion(parseHexString(card.getFwVersion()));
					gbCard.setHwVersion("VISTA-E01/B05");
					gbCard.setSerialNumber("");
					gbCard.setVendor("donyan");
					gbCard.setCurrentTemperature(card.getCurrentTemperature());
					gbCard.setMac(card.getMac().toUpperCase());
					gbCard.setUFrameInterval1(card.getuFrameInterval1());
					gbCard.setUFrameInterval2(card.getuFrameInterval2());
					gbCard.setUplinkMac(card.getUplinkMac().toUpperCase());
					gbCard.setUplinkPort(card.getUplinkPort());
					gbCard.setRfLinkMax(card.getRfLinkMax());
					gbCard.setRfOutputLevel(card.getRfOutputLevel());
					gbCard.setRfDsStartFrq(card.getRfDsStartFrq());
					gbCard.setRfDsStopFrq(card.getRfDsStopFrq());
					gbCard.setRfUsStartFrq(card.getRfUsStartFrq());
					gbCard.setRfUsStopFrq(card.getRfUsStopFrq());
					gbCardList.add(gbCard);
				}
				logger.info("Construct GB CBAT Card finished. count="+gbCardList.size());
				map.put(ResourceClassName.GBEocCBATCardInforTable.getValue(), gbCardList);
			}
		}else{
			logger.info("Construct GB CBAT Card finished. count="+cardList.size());
			GBEocCBATCardInforTable o = (GBEocCBATCardInforTable) cardList.get(0);
			o.setUpTime(o.getUpTime()/100);
			map.put(ResourceClassName.GBEocCBATCardInforTable.getValue(), cardList);
		}
		
		//construct GB CNU info
		List<Object> gbCnuList = new ArrayList<Object>();
		List<Object> cnuList = map.get(GBEocCNUInforTable.class.getName());
		List<Object> igmpList = map.get(DongYanEocIGMPSnoopingTable.class.getName());
		if(cnuList != null && cnuList.size() > 0){
			for(Object o : cnuList){
				GBEocCNUInforTable cnu = (GBEocCNUInforTable)o;
				GBEocCNUInforTable gbCnu = new GBEocCNUInforTable();
				gbCnu.setId(cnu.getId());
				gbCnu.setMac(cnu.getMac());
				gbCnu.setAuthorizationStatus(cnu.getAuthorizationStatus());
				gbCnu.setIndex(cnu.getIndex());
				gbCnu.setRfOutputLevel(cnu.getRfOutputLevel());
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
				gbCnuList.add(gbCnu);
			}
		}
		if(igmpList !=null && igmpList.size()>0){
			for(Object o : igmpList){
				DongYanEocIGMPSnoopingTable igmp = (DongYanEocIGMPSnoopingTable)o;
				for(Object c : gbCnuList){
					GBEocCNUInforTable gbCnu = (GBEocCNUInforTable)c;
					if(igmp.getIgmpCNUIndex().equals(gbCnu.getIndex())){
						gbCnu.setIgmpEn(igmp.getIgmpEN());
					}
				}
			}
		}
		logger.info("Construct GB CNU finished. CNU count="+gbCnuList.size());
		map.put(ResourceClassName.GBEocCNUInforTable.getValue(), gbCnuList);
		
		
		//construct GB CNU port info
		List<Object> gbCnuPortList = new ArrayList<Object>();
		List<Object> cnuPortList = map.get(DongYanEocCNUPortInfoTable.class.getName());
		List<Object> cnuServiceList = map.get(DongYanEocCNUServiceTable.class.getName());
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
		}
		if(cnuPortList != null && cnuPortList.size() > 0){
			for(Object o:cnuPortList){
				DongYanEocCNUPortInfoTable port = (DongYanEocCNUPortInfoTable)o;
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
					DongYanEocCNUServiceTable cnuService = (DongYanEocCNUServiceTable)o;
					if(cnuService.getServiceIndex().equals(gbPort.getPortService())){
						gbPort.setPortDSSpeed(cnuService.getServiceDownCIR().longValue());
						gbPort.setPortUSSpeed(cnuService.getServiceUpCIR().longValue());
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
	
//	@Override
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
		List<VariableBinding> list = support.getGroupValues(new OID[]{new OID(SNMPConstant.avSysStatUpSpeed), new OID(SNMPConstant.avSysStatDownSpeed)}, PDU.GET);
		JSONObject cbat = new JSONObject();
		int upSpeed = list.get(0).getVariable().toInt();
		int downSpeed = list.get(1).getVariable().toInt();
		cbat.element("RxSpeed", upSpeed);
		cbat.element("TxSpeed", downSpeed);
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
		String xmlPath = impl.getClass().getClassLoader().getResource("META-INF/eoc_gb_dongyan.xml").getFile();
		
//		impl.discovery("192.168.21.103", xmlPath, 1, "public");
//		System.out.print(impl.discovery("192.168.21.103", xmlPath,1,"public"));
//		Map<String,List<Object>> map = (Map<String, List<Object>>) impl.discovery("192.168.16.106", xmlPath,1,"public","private");
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
//		snmpConfig.setAgentIp("192.168.16.100");
//		snmpConfig.setReadCommunity("public");
//		snmpConfig.setWriteCommunity("private");
//		SNMPSupportImpl support = new SNMPSupportImpl();
//		support.initSnmp(snmpConfig);
		
		//test for CNU template set
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType","setCnuTemplate");
//		map.put("readComm","public");
//		map.put("snmpVersion","1");
//		map.put("writeComm","private");
//		map.put("templateIndex","0");
//		map.put("templateStatus","0");
//		map.put("index","[\"1.1\",\"1.2\"]");
//		map.put("tempName","exampl1");
//		map.put("duplexMode_1",1);
//		map.put("duplexMode_2",3);
//		impl.applyTemplate("192.168.16.100", 4, 1, 1, 1, map);
		
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
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType", "setCardInfo");
//		map.put("writeComm", "private");
//		map.put("rfOutputPower", 100);
//		map.put("index", "1");
//		map.put("rfDsBeginFrequency", 7500001);
//		map.put("rfDsEndFrequency", 30000001);
//		map.put("rfUsBeginFrequency", 7500001 );
//		map.put("rfUsEndFrequency", 30000001);
//		impl.applyTemplate("172.18.201.10", 1, 1,1, 1, map);
		
		//test setCnuConfig 
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType","setCnuConfig");
//		map.put("readComm","public");
//		map.put("writeComm","private");
//		map.put("snmpVersion","1");
//		map.put("index","1.1");
//		map.put("downstreamPIR",256);
//		map.put("upstreamPIR",256);
//		impl.applyTemplate("172.18.201.10", 1, 1,1, 1, map);
		
//		test for setSysInfo
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("applyType", "setSysInfo");
//		map.put("setCommunity", "private");
//		map.put("alarmStatus", 1);
//		map.put("cnuCountMax", 32);
		
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
	}
	@Override
	public HashMap<?, ?> discovery(String hostIp, String xmlpath) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getVendor() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getChassis() {
		// TODO Auto-generated method stub
		return 0;
	}

}
