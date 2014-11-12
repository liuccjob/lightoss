package com.pbn.oss.adaptor.eoc.bean;

public enum EocModemConfigPM {
	
	apToModemTxDataRateCurrent("{\"label\":\"apToModemTxDataRateCurrent\", \"unit\":\"Mbps\"}"),
	modemToApTxDataRateCurrent("{\"label\":\"modemToApTxDataRateCurrent\", \"unit\":\"Mbps\"}");

	private String value;
	
	EocModemConfigPM(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
 
}
