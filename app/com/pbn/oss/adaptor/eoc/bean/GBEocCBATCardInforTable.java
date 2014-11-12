package com.pbn.oss.adaptor.eoc.bean;

 

public class GBEocCBATCardInforTable{ 
	
	private java.lang.String modelType;
	private java.lang.String fwVersion;
	private java.lang.String hwVersion;
	private java.lang.String mac;
	private java.lang.Integer onlineStatus;
	private java.lang.String serialNumber;
	private java.lang.String vendor;
	private java.lang.Long upTime;
	private java.lang.Long uFrameInterval1;
	private java.lang.Long uFrameInterval2;
	private java.lang.String uplinkMac;
	private java.lang.Integer uplinkPort;
	private java.lang.Integer currentTemperature;
 
	private java.lang.Integer index;
	private java.lang.Integer rfLinkMax;
    
	private java.lang.Integer rfOutputLevel;
	private java.lang.Long rfDsStartFrq;
	private java.lang.Long rfDsStopFrq;
	private java.lang.Long rfUsStartFrq;
	private java.lang.Long rfUsStopFrq;
 
     
    private java.lang.String id;

    public GBEocCBATCardInforTable() {
	}

	public GBEocCBATCardInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String hwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.String serialNumber,
    		java.lang.String vendor,
    		java.lang.Long upTime,
    		java.lang.Long uFrameInterval1,
    		java.lang.Long uFrameInterval2,
    		java.lang.String uplinkMac,
    		java.lang.Integer uplinkPort,
    		java.lang.Integer currentTemperature,
    		java.lang.Integer rfLinkMax,
    	    java.lang.Integer rfOutputLevel,
    		java.lang.Long rfDsStartFrq,
    		java.lang.Long rfDsStopFrq,
    		java.lang.Long rfUsStartFrq,
    		java.lang.Long rfUsStopFrq,
    		java.lang.String id ) {
    	
        this.modelType = modelType;
        this.fwVersion = fwVersion;
        this.hwVersion = hwVersion;
        this.mac = mac;
        
        this.onlineStatus = onlineStatus;
        this.serialNumber = serialNumber;
        this.vendor = vendor;
        this.upTime = upTime;
        
        this.uFrameInterval1 = uFrameInterval1;
        this.uFrameInterval2 = uFrameInterval2;
        this.uplinkMac = uplinkMac;
        this.uplinkPort = uplinkPort;
        
        
        this.currentTemperature = currentTemperature;
        this.rfLinkMax = rfLinkMax;
 
        this.rfOutputLevel = rfOutputLevel;
        
        this.rfDsStartFrq = rfDsStartFrq;
        this.rfDsStopFrq = rfDsStopFrq;
        this.rfUsStartFrq = rfUsStartFrq;
        this.rfUsStopFrq = rfUsStopFrq;
       
        this.id = id;
 
    }
	
	//for DongYan
    public GBEocCBATCardInforTable(String modelType,
    		String fwVersion,
			String hwVersion, 
			String mac,
			Integer onlineStatus,
			String serialNumber,
			String vendor, 
			Long upTime,
			Long uFrameInterval1,
			Long uFrameInterval2,
			String uplinkMac,
			Integer uplinkPort,
			Integer currentTemperature, 
			Integer index,
			Integer rfLinkMax,
			Integer rfOutputLevel, 
			Long rfDsStartFrq,
			Long rfDsStopFrq,
			Long rfUsStartFrq, 
			Long rfUsStopFrq,
			String id) {
		super();
		this.modelType = modelType;
		this.fwVersion = fwVersion;
		this.hwVersion = hwVersion;
		this.mac = mac;
		this.onlineStatus = onlineStatus;
		this.serialNumber = serialNumber;
		this.vendor = vendor;
		this.upTime = upTime;
		this.uFrameInterval1 = uFrameInterval1;
		this.uFrameInterval2 = uFrameInterval2;
		this.uplinkMac = uplinkMac;
		this.uplinkPort = uplinkPort;
		this.currentTemperature = currentTemperature;
		this.index = index;
		this.rfLinkMax = rfLinkMax;
		this.rfOutputLevel = rfOutputLevel;
		this.rfDsStartFrq = rfDsStartFrq;
		this.rfDsStopFrq = rfDsStopFrq;
		this.rfUsStartFrq = rfUsStartFrq;
		this.rfUsStopFrq = rfUsStopFrq;
		this.id = id;
	}

	public GBEocCBATCardInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.Long uFrameInterval1,
    		java.lang.Long uFrameInterval2,
    		java.lang.String uplinkMac,
    		java.lang.Integer uplinkPort,
    		java.lang.Integer rfLinkMax,

    		java.lang.Long rfDsStartFrq,
    		java.lang.Long rfDsStopFrq,
    		java.lang.Long rfUsStartFrq,
    		java.lang.Long rfUsStopFrq,
    		java.lang.String id ) {
    	
        this.modelType = modelType;
        this.fwVersion = fwVersion;
        this.mac = mac;
        
        this.onlineStatus = onlineStatus;
        
        this.uFrameInterval1 = uFrameInterval1;
        this.uFrameInterval2 = uFrameInterval2;
        this.uplinkMac = uplinkMac;
        this.uplinkPort = uplinkPort;
        
        this.rfLinkMax = rfLinkMax;
 
       
        
        this.rfDsStartFrq = rfDsStartFrq;
        this.rfDsStopFrq = rfDsStopFrq;
        this.rfUsStartFrq = rfUsStartFrq;
        this.rfUsStopFrq = rfUsStopFrq;
       
        this.id = id;
 
    }
    
    public GBEocCBATCardInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.Long uFrameInterval1,
    		java.lang.Long uFrameInterval2,
    		java.lang.String uplinkMac,
    		java.lang.Integer uplinkPort,
    		java.lang.Integer rfLinkMax,
    	    java.lang.Integer rfOutputLevel,
    		java.lang.Long rfDsStartFrq,
    		java.lang.Long rfDsStopFrq,
    		java.lang.Long rfUsStartFrq,
    		java.lang.Long rfUsStopFrq,
    		java.lang.String id ) {
    	
        this.modelType = modelType;
        this.fwVersion = fwVersion;
        this.mac = mac;
        
        this.onlineStatus = onlineStatus;
        
        this.uFrameInterval1 = uFrameInterval1;
        this.uFrameInterval2 = uFrameInterval2;
        this.uplinkMac = uplinkMac;
        this.uplinkPort = uplinkPort;
        
        
        this.rfLinkMax = rfLinkMax;
 
        this.rfOutputLevel = rfOutputLevel;
        
        this.rfDsStartFrq = rfDsStartFrq;
        this.rfDsStopFrq = rfDsStopFrq;
        this.rfUsStartFrq = rfUsStartFrq;
        this.rfUsStopFrq = rfUsStopFrq;
       
        this.id = id;
 
    }
    
    public GBEocCBATCardInforTable(java.lang.String modelType,
            java.lang.String fwVersion,
            java.lang.String mac,
            java.lang.Integer onlineStatus,
            String id){

		this.modelType = modelType;
		this.fwVersion = fwVersion;
		this.mac = mac;
		this.onlineStatus = onlineStatus;
		this.id = id;
	
	}
    
    //for orientview
    public GBEocCBATCardInforTable(String fwVersion, String hwVersion,
			String mac, Integer onlineStatus, Long upTime, String id) {
		super();
		this.fwVersion = fwVersion;
		this.hwVersion = hwVersion;
		this.mac = mac;
		this.onlineStatus = onlineStatus;
		this.upTime = upTime;
		this.id = id;
	}
    
    //for B-Star
	public GBEocCBATCardInforTable(
			String modelType,
			String fwVersion,
			String hwVersion, 
			String mac, 
			Integer onlineStatus,
			Long uFrameInterval1,
			Long uFrameInterval2,
			String uplinkMac,
			Integer index,
			Integer rfLinkMax,
			Integer rfOutputLevel,
			Long rfDsStartFrq, 
			Long rfDsStopFrq,
			Long rfUsStartFrq,
			Long rfUsStopFrq, 
			String id) {
		super();
		this.modelType = modelType;
		this.fwVersion = fwVersion;
		this.hwVersion = hwVersion;
		this.mac = mac;
		this.onlineStatus = onlineStatus;
		this.uFrameInterval1 = uFrameInterval1;
		this.uFrameInterval2 = uFrameInterval2;
		this.uplinkMac = uplinkMac;
		this.index = index;
		this.rfLinkMax = rfLinkMax;
		this.rfOutputLevel = rfOutputLevel;
		this.rfDsStartFrq = rfDsStartFrq;
		this.rfDsStopFrq = rfDsStopFrq;
		this.rfUsStartFrq = rfUsStartFrq;
		this.rfUsStopFrq = rfUsStopFrq;
		this.id = id;
	}

	public java.lang.String getId () {
        return id;
    }

	public void setId ( java.lang.String id ) {
        this.id = id;
    }
	public java.lang.Integer getCurrentTemperature() {
		return currentTemperature;
	}
	public void setCurrentTemperature(java.lang.Integer currentTemperature) {
		this.currentTemperature = currentTemperature;
	}
	public java.lang.String getFwVersion() {
		return fwVersion;
	}
	public void setFwVersion(java.lang.String fwVersion) {
		this.fwVersion = fwVersion;
	}
	public java.lang.String getHwVersion() {
		return hwVersion;
	}
	public void setHwVersion(java.lang.String hwVersion) {
		this.hwVersion = hwVersion;
	}
	public java.lang.Integer getIndex() {
		return index;
	}
	public void setIndex(java.lang.Integer index) {
		this.index = index;
	}
	public java.lang.String getMac() {
		return mac;
	}
	public void setMac(java.lang.String mac) {
		this.mac = mac;
	}
	public java.lang.String getModelType() {
		return modelType;
	}
	public void setModelType(java.lang.String modelType) {
		this.modelType = modelType;
	}
	public java.lang.Integer getOnlineStatus() {
		return onlineStatus;
	}
	public void setOnlineStatus(java.lang.Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	public java.lang.Long getRfDsStartFrq() {
		return rfDsStartFrq;
	}
	public void setRfDsStartFrq(java.lang.Long rfDsStartFrq) {
		this.rfDsStartFrq = rfDsStartFrq;
	}
	public java.lang.Long getRfDsStopFrq() {
		return rfDsStopFrq;
	}
	public void setRfDsStopFrq(java.lang.Long rfDsStopFrq) {
		this.rfDsStopFrq = rfDsStopFrq;
	}
	public java.lang.Integer getRfLinkMax() {
		return rfLinkMax;
	}
	public void setRfLinkMax(java.lang.Integer rfLinkMax) {
		this.rfLinkMax = rfLinkMax;
	}
	public java.lang.Integer getRfOutputLevel() {
		return rfOutputLevel;
	}
	public void setRfOutputLevel(java.lang.Integer rfOutputLevel) {
		this.rfOutputLevel = rfOutputLevel;
	}
	public java.lang.Long getRfUsStartFrq() {
		return rfUsStartFrq;
	}
	public void setRfUsStartFrq(java.lang.Long rfUsStartFrq) {
		this.rfUsStartFrq = rfUsStartFrq;
	}
	public java.lang.Long getRfUsStopFrq() {
		return rfUsStopFrq;
	}
	public void setRfUsStopFrq(java.lang.Long rfUsStopFrq) {
		this.rfUsStopFrq = rfUsStopFrq;
	}
	public java.lang.String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public java.lang.Long getUFrameInterval1() {
		return uFrameInterval1;
	}
	public void setUFrameInterval1(java.lang.Long frameInterval1) {
		uFrameInterval1 = frameInterval1;
	}
	public java.lang.Long getUFrameInterval2() {
		return uFrameInterval2;
	}
	public void setUFrameInterval2(java.lang.Long frameInterval2) {
		uFrameInterval2 = frameInterval2;
	}
	public java.lang.String getUplinkMac() {
		return uplinkMac;
	}
	public void setUplinkMac(java.lang.String uplinkMac) {
		this.uplinkMac = uplinkMac;
	}
	public java.lang.Integer getUplinkPort() {
		return uplinkPort;
	}
	public void setUplinkPort(java.lang.Integer uplinkPort) {
		this.uplinkPort = uplinkPort;
	}
	public java.lang.Long getUpTime() {
		return upTime;
	}
	public void setUpTime(java.lang.Long upTime) {
		this.upTime = upTime;
	}
	public java.lang.String getVendor() {
		return vendor;
	}
	public void setVendor(java.lang.String vendor) {
		this.vendor = vendor;
	}
 
}
