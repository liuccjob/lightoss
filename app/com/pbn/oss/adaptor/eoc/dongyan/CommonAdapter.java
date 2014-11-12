package com.pbn.oss.adaptor.eoc.dongyan;

 
import java.util.List;
import java.util.Map;

import com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPConfig;
import com.pbn.oss.adaptor.eoc.dongyan.snmp.SNMPSupportImpl;

public class CommonAdapter { 
	private String xmlFile;
	private List<ClassDefine> classDefines = null;
	
    public CommonAdapter(String xmlFile) throws Exception {
		super();
		this.xmlFile = xmlFile;
		Xml2Entity xml2entity = new Xml2Entity();
		classDefines = xml2entity.getEntities(xmlFile);
	}
    
    public CommonAdapter() throws Exception {
		super();
	}
    
    public boolean setValues(String hostIp,Map<?,?> map){
    	Reflection rf = new Reflection();
        return rf.setDataValues(hostIp, map);
  	 

    }
    
	public List<Object> getValues(String className, SNMPSupportImpl support) throws Exception{
        Reflection rf = new Reflection();
        return rf.getDataValue(className, xmlFile, support);
    }
    public List<Object> getValues(ClassDefine entry, SNMPSupportImpl support) throws Exception{
        Reflection rf = new Reflection();
        return rf.getDataValue(entry, support);
    }

	public List<Object> getValues(String className, String id, SNMPSupportImpl support) throws Exception{
        Reflection rf = new Reflection();
        return rf.getDataValue(className, xmlFile, id, support);
    }
    public List<Object> getValues(ClassDefine entry, String id, SNMPSupportImpl support) throws Exception{
        Reflection rf = new Reflection();
        return rf.getDataValue(entry, id, support);
    }

    public Map<String, List<Object>> getAllValues(SNMPSupportImpl support) throws Exception{
        Reflection rf = new Reflection();
        return rf.getAllDataValue(classDefines, support);
    }
    
    public  static void main(String[] args) throws Exception {
    	SNMPSupportImpl support = new SNMPSupportImpl();
    	SNMPConfig cfg = new SNMPConfig();
    	cfg.setAgentIp("192.168.2.2");
    	support.initSnmp(cfg);
    	String xmlPath = CommonAdapter.class.getClassLoader().getResource("META-INF/eoc_gb_daya.xml").getFile();
    	CommonAdapter comAdapter = new CommonAdapter(xmlPath);
    	Map<String, List<Object>> map = comAdapter.getAllValues(support);
    	
//    	for(String key : map.keySet()){
//			System.out.println("key is:" + key);
//			List<Object> list = map.get(key);
//			for(Object object : list){
//				if(object instanceof EocCBATCardRFTable){
//					EocCBATCardRFTable gbTable = (EocCBATCardRFTable) object;
//					System.out.println(gbTable.getId()+";"+gbTable.getCbatCardRFDownstreamStartFreq()+";"+gbTable.getEocCBATCardRFOutputLevel());
//					System.out.println("GBEocCBATInforTable");
//				} 
//			}
//    	}
    	
//    	for(String key : map.keySet()){
//			System.out.println("key is:" + key);
//			List<Object> list = map.get(key);
//			for(Object object : list){
//				if(object instanceof GBEocCBATInforTable){
//					GBEocCBATInforTable gbTable = (GBEocCBATInforTable) object;
//					System.out.println(gbTable.getId()+";"+gbTable.getFwVersion()+";"+gbTable.getHwVersion());
//					System.out.println("GBEocCBATInforTable");
//				} 
//			}
//    	}
    }
} 
