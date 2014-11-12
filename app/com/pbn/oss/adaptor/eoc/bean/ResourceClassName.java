package com.pbn.oss.adaptor.eoc.bean;

public enum ResourceClassName {
	//Used for Tomson EOC device
	EocMasterEthPort ("com.pbn.oss.adaptor.eoc.bean.EocMasterEthPortTable"),
	EocMasterFdb("com.pbn.oss.adaptor.eoc.bean.EocMasterFdbTable"),
	EocMasterModemConfig("com.pbn.oss.adaptor.eoc.bean.EocMasterModemConfigTable"),
	EocMasterModemList("com.pbn.oss.adaptor.eoc.bean.EocMasterModemListTable"),
	EocMasterModemSignalCondition("com.pbn.oss.adaptor.eoc.bean.EocMasterModemSignalConditionTable"),

	EocMasterSysInfo("com.pbn.oss.adaptor.eoc.bean.EocMasterSysInfoTable"),
	EocMasterSysVersion("com.pbn.oss.adaptor.eoc.bean.EocMasterSysVersionTable"),
	
	EocModemEthPort("com.pbn.oss.adaptor.eoc.bean.EocModemEthPortTable"),
	EocModemConfig("com.pbn.oss.adaptor.eoc.bean.EocModemConfigTable"),
	EocModemPerfor("com.pbn.oss.adaptor.eoc.bean.EocModemPerforTable"),
	EocModemPortConfig("com.pbn.oss.adaptor.eoc.bean.EocModemPortConfigTable"),
	EocModemVersion("com.pbn.oss.adaptor.eoc.bean.EocModemVersionTable"),
	
	
		//Used for GB EOC device
	GBEocCBATCardInforTable("com.pbn.oss.adaptor.eoc.bean.GBEocCBATCardInforTable"),
	GBEocCBATInforTable("com.pbn.oss.adaptor.eoc.bean.GBEocCBATInforTable"),
	GBEocCNUWhiteListTable("com.pbn.oss.adaptor.eoc.bean.GBEocCNUWhiteListTable"),
	GBEocCNUInforTable("com.pbn.oss.adaptor.eoc.bean.GBEocCNUInforTable"),
	GBEocCNUPortInforTable("com.pbn.oss.adaptor.eoc.bean.GBEocCNUPortInforTable");
	

	
	
	ResourceClassName (String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	public  static String getBeanName(String fullName){
		if(fullName==null||fullName.equals(""))return null;
		for(ResourceClassName resourceClassName:values()){
			if(resourceClassName.value.equals(fullName)) return resourceClassName.name();
		}
		return null;
	}
}
