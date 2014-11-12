package com.pbn.oss.adaptor.eoc.bean;

public class GBEocCNUPortInforTable {
	
	
	
	private java.lang.Integer portEn;
	private java.lang.Integer portService;
	private java.lang.Integer portAutoNeg;
	private java.lang.Integer portSpeed;
	private java.lang.Integer portDuplex;
	private java.lang.Integer portPVID;
	private java.lang.Integer portTPID;
	private java.lang.Integer portPRIO;
 
	private java.lang.String portVIDList;
	private java.lang.String portUntaggedVIDList;
 
	private java.lang.Integer portMode;
 
	private java.lang.Long portUSSpeed;
    
	private java.lang.Long portDSSpeed;
	private java.lang.String id;
	private java.lang.Integer index;
	private java.lang.Integer cardIndex;
	private java.lang.Integer cnuIndex;
	
	private  java.lang.Integer  broadCastStormProtectionEnable;
	private  java.lang.Integer  multicastStormProtectionEnable;
	private  java.lang.Integer  unknownUnicastStormProtectionEnable;
	private  java.lang.Integer  broadCastStormThreshold;
	private  java.lang.Integer  multicastStormThreshold;
	private  java.lang.Integer  unknownUnicastStormThreshold;
	
	
	
	public GBEocCNUPortInforTable() {
		super();
	}

	//for adaptor_eoc_tongwei
	public GBEocCNUPortInforTable(
			java.lang.Integer portEn,
			java.lang.Integer portService,
			java.lang.Integer portPVID,
			java.lang.Integer portTPID,
			java.lang.Integer portPRIO,
		 
			java.lang.String portVIDList,
			java.lang.String portUntaggedVIDList,
		 
			java.lang.Integer portMode,
			java.lang.String id
			
			){
		 this.portEn = portEn;
		 this.portService = portService;
		 
		 this.portPVID = portPVID;
		 this.portTPID = portTPID;
		 
		 this.portPRIO = portPRIO;
		 this.portVIDList = portVIDList;
		 this.portUntaggedVIDList = portUntaggedVIDList;
		 this.portMode = portMode;
		 this.id = id;

		 
	}
	
	// for adaptor_eoc_wanlong
	public GBEocCNUPortInforTable(
			
			java.lang.Integer portPVID,
			java.lang.Integer portPRIO,
			java.lang.Long portDSSpeed,
			java.lang.Long portUSSpeed,		    			
			java.lang.Integer portEn,
			java.lang.String id){
				 		 		 
		 this.portPVID = portPVID;		 
		 this.portPRIO = portPRIO;		 		 
		 this.portUSSpeed = portUSSpeed;
		 this.portDSSpeed = portDSSpeed;
		 this.portEn = portEn;
		 
		 this.id = id;

		 
	}
	
	public GBEocCNUPortInforTable(
			java.lang.Integer portEn,
			java.lang.Integer portService,
			java.lang.Integer portAutoNeg,
			java.lang.Integer portSpeed,
			java.lang.Integer portDuplex,
			java.lang.Integer portPVID,
			java.lang.Integer portTPID,
			java.lang.Integer portPRIO,
		 
			java.lang.String portVIDList,
			java.lang.String portUntaggedVIDList,
		 
			java.lang.Integer portMode,
			java.lang.String id
			
			){
		 this.portEn = portEn;
		 this.portService = portService;
		 this.portAutoNeg = portAutoNeg;
		 this.portSpeed = portSpeed;
		 this.portDuplex = portDuplex;
		 
		 this.portPVID = portPVID;
		 this.portTPID = portTPID;
		 
		 this.portPRIO = portPRIO;
		 this.portVIDList = portVIDList;
		 this.portUntaggedVIDList = portUntaggedVIDList;
		 this.portMode = portMode;
		 this.id = id;

		 
	}
	
	public GBEocCNUPortInforTable(

			java.lang.Integer portPVID,
			java.lang.Integer portTPID,
			java.lang.Integer portPRIO,
		 

		 
			java.lang.Integer portMode,
			java.lang.Integer  broadCastStormProtectionEnable,
			java.lang.Integer  multicastStormProtectionEnable,
			java.lang.Integer  unknownUnicastStormProtectionEnable,
			java.lang.Integer  broadCastStormThreshold,
			java.lang.Integer  multicastStormThreshold,
			java.lang.Integer  unknownUnicastStormThreshold,
			java.lang.String id
			
			){

		 
		 this.portPVID = portPVID;
		 this.portTPID = portTPID;
		 
		 this.portPRIO = portPRIO;
		 this.portMode = portMode;
		 this.broadCastStormProtectionEnable = broadCastStormProtectionEnable;
		 this.multicastStormProtectionEnable = multicastStormProtectionEnable;
		 this.unknownUnicastStormProtectionEnable = unknownUnicastStormProtectionEnable;
		 this.broadCastStormThreshold = broadCastStormThreshold;
		 this.multicastStormThreshold = multicastStormThreshold;
		 this.unknownUnicastStormThreshold = unknownUnicastStormThreshold;
		 this.id = id;

		 
	}
	
	//for adaptor_eoc_hs4504_dg
	public GBEocCNUPortInforTable(
			java.lang.Integer portPVID, 
			java.lang.Integer portTPID, 
			java.lang.Integer portPRIO,
			java.lang.String portVIDList,
			java.lang.String portUntaggedVIDList,
			java.lang.Integer portMode, 
			java.lang.Integer index, 
			java.lang.Integer cardIndex, 
			java.lang.Integer cnuIndex, 
			java.lang.String id) {
		super();
		this.portPVID = portPVID;
		this.portTPID = portTPID;
		this.portPRIO = portPRIO;
		this.portVIDList = portVIDList;
		this.portUntaggedVIDList = portUntaggedVIDList;
		this.portMode = portMode;
		this.index = index;
		this.cardIndex = cardIndex;
		this.cnuIndex = cnuIndex;
		this.id = id;
	}
	
	//for DongYan
	public GBEocCNUPortInforTable(Integer portEn, 
			Integer portService,
			Integer index,
			Integer cnuIndex,
			String id) {
		super();
		this.portEn = portEn;
		this.portService = portService;
		this.index = index;
		this.cnuIndex = cnuIndex;
		this.id = id;
	}
	
	//for orientview
	public GBEocCNUPortInforTable(Integer portEn, Integer portAutoNeg,
			Integer portTPID, Integer portPRIO, Long portUSSpeed,
			Long portDSSpeed, String id) {
		super();
		this.portEn = portEn;
		this.portAutoNeg = portAutoNeg;
		this.portTPID = portTPID;
		this.portPRIO = portPRIO;
		this.portUSSpeed = portUSSpeed;
		this.portDSSpeed = portDSSpeed;
		this.id = id;
	}
	
	public java.lang.Integer getCnuIndex() {
		return cnuIndex;
	}

	public void setCnuIndex(java.lang.Integer cnuIndex) {
		this.cnuIndex = cnuIndex;
	}

	public java.lang.Integer getBroadCastStormProtectionEnable() {
		return broadCastStormProtectionEnable;
	}

	public void setBroadCastStormProtectionEnable(java.lang.Integer broadCastStormProtectionEnable) {
		this.broadCastStormProtectionEnable = broadCastStormProtectionEnable;
	}

	public java.lang.Integer getMulticastStormProtectionEnable() {
		return multicastStormProtectionEnable;
	}

	public void setMulticastStormProtectionEnable(java.lang.Integer multicastStormProtectionEnable) {
		this.multicastStormProtectionEnable = multicastStormProtectionEnable;
	}

	public java.lang.Integer getUnknownUnicastStormProtectionEnable() {
		return unknownUnicastStormProtectionEnable;
	}

	public void setUnknownUnicastStormProtectionEnable(java.lang.Integer unknownUnicastStormProtectionEnable) {
		this.unknownUnicastStormProtectionEnable = unknownUnicastStormProtectionEnable;
	}

	public java.lang.Integer getBroadCastStormThreshold() {
		return broadCastStormThreshold;
	}

	public void setBroadCastStormThreshold(java.lang.Integer broadCastStormThreshold) {
		this.broadCastStormThreshold = broadCastStormThreshold;
	}



	public java.lang.Integer getMulticastStormThreshold() {
		return multicastStormThreshold;
	}



	public void setMulticastStormThreshold(java.lang.Integer multicastStormThreshold) {
		this.multicastStormThreshold = multicastStormThreshold;
	}



	public java.lang.Integer getUnknownUnicastStormThreshold() {
		return unknownUnicastStormThreshold;
	}



	public void setUnknownUnicastStormThreshold(java.lang.Integer unknownUnicastStormThreshold) {
		this.unknownUnicastStormThreshold = unknownUnicastStormThreshold;
	}



	public java.lang.String getId() {
		return id;
	}


	public void setId(java.lang.String id) {
		this.id = id;
	}


	public java.lang.Integer getIndex() {
		return index;
	}


	public void setIndex(java.lang.Integer index) {
		this.index = index;
	}


	public java.lang.Integer getPortAutoNeg() {
		return portAutoNeg;
	}


	public void setPortAutoNeg(java.lang.Integer portAutoNeg) {
		this.portAutoNeg = portAutoNeg;
	}


	public java.lang.Long getPortDSSpeed() {
		return portDSSpeed;
	}


	public void setPortDSSpeed(java.lang.Long portDSSpeed) {
		this.portDSSpeed = portDSSpeed;
	}


	public java.lang.Integer getPortDuplex() {
		return portDuplex;
	}


	public void setPortDuplex(java.lang.Integer portDuplex) {
		this.portDuplex = portDuplex;
	}


	public java.lang.Integer getPortEn() {
		return portEn;
	}


	public void setPortEn(java.lang.Integer portEn) {
		this.portEn = portEn;
	}


	public java.lang.Integer getPortMode() {
		return portMode;
	}


	public void setPortMode(java.lang.Integer portMode) {
		this.portMode = portMode;
	}


	public java.lang.Integer getPortPRIO() {
		return portPRIO;
	}


	public void setPortPRIO(java.lang.Integer portPRIO) {
		this.portPRIO = portPRIO;
	}

	public java.lang.Integer getPortPVID() {
		return portPVID;
	}

	public void setPortPVID(java.lang.Integer portPVID) {
		this.portPVID = portPVID;
	}

	public java.lang.Integer getPortService() {
		return portService;
	}

	public void setPortService(java.lang.Integer portService) {
		this.portService = portService;
	}

	public java.lang.Integer getPortSpeed() {
		return portSpeed;
	}

	public void setPortSpeed(java.lang.Integer portSpeed) {
		this.portSpeed = portSpeed;
	}

	public java.lang.Integer getPortTPID() {
		return portTPID;
	}

	public void setPortTPID(java.lang.Integer portTPID) {
		this.portTPID = portTPID;
	}

	public java.lang.String getPortUntaggedVIDList() {
		return portUntaggedVIDList;
	}

	public void setPortUntaggedVIDList(java.lang.String portUntaggedVIDList) {
		this.portUntaggedVIDList = portUntaggedVIDList;
	}

	public java.lang.Long getPortUSSpeed() {
		return portUSSpeed;
	}

	public void setPortUSSpeed(java.lang.Long portUSSpeed) {
		this.portUSSpeed = portUSSpeed;
	}

	public java.lang.String getPortVIDList() {
		return portVIDList;
	}

	public void setPortVIDList(java.lang.String portVIDList) {
		this.portVIDList = portVIDList;
	}

	public java.lang.Integer getCardIndex() {
		return cardIndex;
	}

	public void setCardIndex(java.lang.Integer cardIndex) {
		this.cardIndex = cardIndex;
	}

}
