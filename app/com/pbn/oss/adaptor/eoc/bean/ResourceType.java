package com.pbn.oss.adaptor.eoc.bean;

public enum ResourceType {
	

	
	EocMasterSysInfoTable(2),
	EocMasterSysVersionTable(3),
	EocMasterFdbTable(4),
	EocMasterEthPortTable(5),
	
	EocMasterModemConfigTable(6),
	EocMasterModemListTable(7),
	EocMasterModemSignalConditionTable(8),
	EocModemVersionTable(9),
	
	EocModemConfigTable(10),
	//EocModemFdbTable(11),
	EocModemEthPortTable(11),
	EocModemPerforTable(12),
	
	EocModemPortConfigTable(13),
	
	GBEocCBATCardInforTable(14),
	GBEocCBATInforTable(15),
	GBEocCNUInforTable(16),
	GBEocCNUPortInforTable(17);
	
	
	private int value;
	
	ResourceType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	
}
