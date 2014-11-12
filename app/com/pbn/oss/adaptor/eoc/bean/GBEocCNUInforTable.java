package com.pbn.oss.adaptor.eoc.bean;

public class GBEocCNUInforTable {
	
	private java.lang.String modelType;
	private java.lang.String fwVersion;
	private java.lang.String mac;
	private java.lang.Integer onlineStatus = 0;
	private java.lang.Integer authorizationStatus = 0;
	private java.lang.Integer adminStatus = 0;
	private java.lang.Integer accessStatus = 0;
	private java.lang.Integer rfAttenuation = 0;
	private java.lang.Integer rfDSQuality = 0;
	private java.lang.Integer rfUSQuality = 0;
 
	private java.lang.Long rfPhyDSRate = 0L;
	private java.lang.Long rfPhyUSRate = 0L;

	private java.lang.Long dsPIR = 0L;
	private java.lang.Long usPIR = 0L;
 
	private java.lang.Integer broadCastStormProtectionEnable = 0;
    private java.lang.Integer multicastStormProtectionEnable = 0;
    private java.lang.Integer unknownUnicastStormProtectionEnable = 0;
    private java.lang.Integer broadCastStormThreshold = 0;
    private java.lang.Integer multicastStormThreshold = 0;
    private java.lang.Integer unknownUnicastStormThreshold = 0;
    private java.lang.Integer igmpEn = 0;
    private java.lang.Integer index;
    
    private java.lang.Integer rfOutputLevel = 0;
    private java.lang.Integer upSNR = 0;
    private java.lang.Integer downSNR = 0;
    
    public java.lang.Integer getRfOutputLevel() {
		return rfOutputLevel;
	}

	public void setRfOutputLevel(java.lang.Integer rfOutputLevel) {
		this.rfOutputLevel = rfOutputLevel;
	}

	private java.lang.String id;
    
    public GBEocCNUInforTable() {
		super();
	}

	//for adaptor_eoc_wanlong
    public GBEocCNUInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.Integer authorizationStatus,    		
    		java.lang.Integer rfAttenuation,
    		java.lang.String id ) {
    	    	
    	 this.modelType = modelType;
    	 this.fwVersion = fwVersion;
    	 this.mac = mac;
    	 this.onlineStatus = onlineStatus;
    	 this.authorizationStatus = authorizationStatus;    	       	 
    	 this.rfAttenuation = rfAttenuation;

    	 this.id = id;
    	 
    }
    
    public GBEocCNUInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.Integer authorizationStatus,
    		java.lang.Integer adminStatus,
    		java.lang.Integer rfAttenuation,
    		java.lang.Integer rfDSQuality,
    		java.lang.Integer rfUSQuality,
    		java.lang.Long rfPhyDSRate,
    		java.lang.Long rfPhyUSRate,
    		java.lang.Long dsPIR,
    		java.lang.Long usPIR,
    		java.lang.Integer broadCastStormProtectionEnable,
    	    java.lang.Integer multicastStormProtectionEnable,
    	    java.lang.Integer unknownUnicastStormProtectionEnable,
    	    java.lang.Integer broadCastStormThreshold,
    	    java.lang.Integer multicastStormThreshold,
    	    java.lang.Integer unknownUnicastStormThreshold,
    	    java.lang.Integer igmpEn,

    		java.lang.String id ) {
    	
    	
    	 this.modelType = modelType;
    	 this.fwVersion = fwVersion;
    	 this.mac = mac;
    	 this.onlineStatus = onlineStatus;
    	 this.authorizationStatus = authorizationStatus;
    	 
       	 this.adminStatus = adminStatus;
    	 this.rfAttenuation = rfAttenuation;
    	 this.rfDSQuality = rfDSQuality;
    	 this.rfUSQuality = rfUSQuality;
       	 this.rfPhyDSRate = rfPhyDSRate;
    	 this.rfPhyUSRate = rfPhyUSRate;
    	 this.dsPIR = dsPIR;
    	 this.usPIR = usPIR;
    	 this.broadCastStormProtectionEnable = broadCastStormProtectionEnable;
    	 this.multicastStormProtectionEnable = multicastStormProtectionEnable;
    	 this.unknownUnicastStormProtectionEnable = unknownUnicastStormProtectionEnable;
    	 this.broadCastStormThreshold = broadCastStormThreshold;
    	 this.multicastStormThreshold = multicastStormThreshold;
    	 
    	 this.unknownUnicastStormThreshold = unknownUnicastStormThreshold;
    	 this.igmpEn = igmpEn;
    	 

    	 this.id = id;
    	 
    }
    //for gospell and kangte
    public GBEocCNUInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.Integer authorizationStatus,
    		java.lang.Integer rfAttenuation,
    		java.lang.Integer rfDSQuality,
    		java.lang.Integer rfUSQuality,
    		java.lang.Long rfPhyDSRate,
    		java.lang.Long rfPhyUSRate,
    		java.lang.Long dsPIR,
    		java.lang.Long usPIR,
    		java.lang.Integer index,
    		java.lang.String id ) {
    	
    	
    	 this.modelType = modelType;
    	 this.fwVersion = fwVersion;
    	 this.mac = mac;
    	 this.onlineStatus = onlineStatus;
    	 this.authorizationStatus = authorizationStatus;
    	 
 
    	 this.rfAttenuation = rfAttenuation;
    	 this.rfDSQuality = rfDSQuality;
    	 this.rfUSQuality = rfUSQuality;
       	 this.rfPhyDSRate = rfPhyDSRate;
    	 this.rfPhyUSRate = rfPhyUSRate;
    	 this.dsPIR = dsPIR;
    	 this.usPIR = usPIR;
    	 this.index = index;

    	 this.id = id;
    	 
    }
    
    //for some kangte(not have dsPIR and usPIR)
    public GBEocCNUInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.Integer authorizationStatus,
    		java.lang.Integer rfAttenuation,
    		java.lang.Integer rfDSQuality,
    		java.lang.Integer rfUSQuality,
    		java.lang.Long rfPhyDSRate,
    		java.lang.Long rfPhyUSRate,
    		java.lang.Integer igmpEn,
    		java.lang.String id ) {
    	
    	
    	 this.modelType = modelType;
    	 this.fwVersion = fwVersion;
    	 this.mac = mac;
    	 this.onlineStatus = onlineStatus;
    	 this.authorizationStatus = authorizationStatus;
    	 
 
    	 this.rfAttenuation = rfAttenuation;
    	 this.rfDSQuality = rfDSQuality;
    	 this.rfUSQuality = rfUSQuality;
       	 this.rfPhyDSRate = rfPhyDSRate;
    	 this.rfPhyUSRate = rfPhyUSRate;
    	 this.igmpEn = igmpEn;

    	 this.id = id;
    	 
    }
    
    //for adaptor_eoc_tongwei
    public GBEocCNUInforTable(
    		java.lang.String modelType,
    		java.lang.String fwVersion,
    		java.lang.String mac,
    		java.lang.Integer onlineStatus,
    		java.lang.Integer authorizationStatus,
    		java.lang.Integer rfAttenuation,
    		java.lang.Integer rfDSQuality,
    		java.lang.Integer rfUSQuality,
    		java.lang.Long rfPhyDSRate,
    		java.lang.Long rfPhyUSRate,
    		java.lang.Integer broadCastStormProtectionEnable,
    	    java.lang.Integer broadCastStormThreshold,
    	    java.lang.Integer igmpEn,

    		java.lang.String id ) {
    	
    	
    	 this.modelType = modelType;
    	 this.fwVersion = fwVersion;
    	 this.mac = mac;
    	 this.onlineStatus = onlineStatus;
    	 this.authorizationStatus = authorizationStatus;
    	 
    	 this.rfAttenuation = rfAttenuation;
    	 this.rfDSQuality = rfDSQuality;
    	 this.rfUSQuality = rfUSQuality;
       	 this.rfPhyDSRate = rfPhyDSRate;
    	 this.rfPhyUSRate = rfPhyUSRate;
    	 this.broadCastStormProtectionEnable = broadCastStormProtectionEnable;
    	 this.broadCastStormThreshold = broadCastStormThreshold;
    	 
    	 this.igmpEn = igmpEn;
    	 

    	 this.id = id;
    	 
    }
	
	//for Gospell
	public GBEocCNUInforTable(
			String modelType, 
			String fwVersion, 
			String mac,
			Integer rfAttenuation,
			Integer rfDSQuality,
			Integer rfUSQuality,
			Long rfPhyDSRate,
			Long rfPhyUSRate,
			String id) {
		super();
		this.modelType = modelType;
		this.fwVersion = fwVersion;
		this.mac = mac;
		this.rfAttenuation = rfAttenuation;
		this.rfDSQuality = rfDSQuality;
		this.rfUSQuality = rfUSQuality;
		this.rfPhyDSRate = rfPhyDSRate;
		this.rfPhyUSRate = rfPhyUSRate;
		this.id = id;
	}
    

	//for DongYan
	public GBEocCNUInforTable(String modelType, 
			String fwVersion, 
			String mac,
			Integer onlineStatus, 
			Integer authorizationStatus,
			Integer adminStatus,
			Integer rfAttenuation,
			Integer rfDSQuality,
			Integer rfUSQuality,
			Long rfPhyDSRate,
			Long rfPhyUSRate,
			Long dsPIR, 
			Long usPIR,
			Integer index,
			String id) {
		super();
		this.modelType = modelType;
		this.fwVersion = fwVersion;
		this.mac = mac;
		this.onlineStatus = onlineStatus;
		this.authorizationStatus = authorizationStatus;
		this.adminStatus = adminStatus;
		this.rfAttenuation = rfAttenuation;
		this.rfDSQuality = rfDSQuality;
		this.rfUSQuality = rfUSQuality;
		this.rfPhyDSRate = rfPhyDSRate;
		this.rfPhyUSRate = rfPhyUSRate;
		this.dsPIR = dsPIR;
		this.usPIR = usPIR;
		this.index = index;
		this.id = id;
	}
	//for gcable
	public GBEocCNUInforTable(String modelType, 
			String fwVersion, 
			String mac,
			Integer onlineStatus, 
			Integer authorizationStatus,
			Integer adminStatus,
			Integer rfAttenuation,
			Integer rfDSQuality,
			Integer rfUSQuality,
			Long rfPhyDSRate,
			Long rfPhyUSRate,
			Long dsPIR, 
			Long usPIR,
			Integer index,
			Integer upSNR,
			Integer downSNR,
			String id) {
		super();
		this.modelType = modelType;
		this.fwVersion = fwVersion;
		this.mac = mac;
		this.onlineStatus = onlineStatus;
		this.authorizationStatus = authorizationStatus;
		this.adminStatus = adminStatus;
		this.rfAttenuation = rfAttenuation;
		this.rfDSQuality = rfDSQuality;
		this.rfUSQuality = rfUSQuality;
		this.rfPhyDSRate = rfPhyDSRate;
		this.rfPhyUSRate = rfPhyUSRate;
		this.dsPIR = dsPIR;
		this.usPIR = usPIR;
		this.index = index;
		this.upSNR = upSNR;
		this.downSNR = downSNR;
		this.id = id;
	}
	
	//for orientview
	public GBEocCNUInforTable(String fwVersion, String mac,
			Integer onlineStatus, Long dsPIR, Long usPIR, String id) {
		super();
		this.fwVersion = fwVersion;
		this.mac = mac;
		this.onlineStatus = onlineStatus;
		this.dsPIR = dsPIR;
		this.usPIR = usPIR;
		this.id = id;
	}
    
	public java.lang.Integer getAccessStatus() {
		return accessStatus;
	}

	public void setAccessStatus(java.lang.Integer accessStatus) {
		this.accessStatus = accessStatus;
	}

	public java.lang.Integer getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(java.lang.Integer adminStatus) {
		this.adminStatus = adminStatus;
	}

	public java.lang.Integer getAuthorizationStatus() {
		return authorizationStatus;
	}

	public void setAuthorizationStatus(java.lang.Integer authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}

	public java.lang.Integer getBroadCastStormProtectionEnable() {
		return broadCastStormProtectionEnable;
	}

	public void setBroadCastStormProtectionEnable(
			java.lang.Integer broadCastStormProtectionEnable) {
		this.broadCastStormProtectionEnable = broadCastStormProtectionEnable;
	}

	public java.lang.Integer getBroadCastStormThreshold() {
		return broadCastStormThreshold;
	}

	public void setBroadCastStormThreshold(java.lang.Integer broadCastStormThreshold) {
		this.broadCastStormThreshold = broadCastStormThreshold;
	}

	public java.lang.Long getDsPIR() {
		return dsPIR;
	}

	public void setDsPIR(java.lang.Long dsPIR) {
		this.dsPIR = dsPIR;
	}

	public java.lang.String getFwVersion() {
		return fwVersion;
	}

	public void setFwVersion(java.lang.String fwVersion) {
		this.fwVersion = fwVersion;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.Integer getIgmpEn() {
		return igmpEn;
	}

	public void setIgmpEn(java.lang.Integer igmpEn) {
		this.igmpEn = igmpEn;
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

	public java.lang.Integer getMulticastStormProtectionEnable() {
		return multicastStormProtectionEnable;
	}

	public void setMulticastStormProtectionEnable(
			java.lang.Integer multicastStormProtectionEnable) {
		this.multicastStormProtectionEnable = multicastStormProtectionEnable;
	}

	public java.lang.Integer getMulticastStormThreshold() {
		return multicastStormThreshold;
	}

	public void setMulticastStormThreshold(java.lang.Integer multicastStormThreshold) {
		this.multicastStormThreshold = multicastStormThreshold;
	}

	public java.lang.Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(java.lang.Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public java.lang.Integer getRfAttenuation() {
		return rfAttenuation;
	}

	public void setRfAttenuation(java.lang.Integer rfAttenuation) {
		this.rfAttenuation = rfAttenuation;
	}

	public java.lang.Integer getRfDSQuality() {
		return rfDSQuality;
	}

	public void setRfDSQuality(java.lang.Integer rfDSQuality) {
		this.rfDSQuality = rfDSQuality;
	}

	public java.lang.Long getRfPhyDSRate() {
		return rfPhyDSRate;
	}

	public void setRfPhyDSRate(java.lang.Long rfPhyDSRate) {
		this.rfPhyDSRate = rfPhyDSRate;
	}

	public java.lang.Long getRfPhyUSRate() {
		return rfPhyUSRate;
	}

	public void setRfPhyUSRate(java.lang.Long rfPhyUSRate) {
		this.rfPhyUSRate = rfPhyUSRate;
	}

	public java.lang.Integer getRfUSQuality() {
		return rfUSQuality;
	}

	public void setRfUSQuality(java.lang.Integer rfUSQuality) {
		this.rfUSQuality = rfUSQuality;
	}

	public java.lang.Integer getUnknownUnicastStormProtectionEnable() {
		return unknownUnicastStormProtectionEnable;
	}

	public void setUnknownUnicastStormProtectionEnable(
			java.lang.Integer unknownUnicastStormProtectionEnable) {
		this.unknownUnicastStormProtectionEnable = unknownUnicastStormProtectionEnable;
	}

	public java.lang.Integer getUnknownUnicastStormThreshold() {
		return unknownUnicastStormThreshold;
	}

	public void setUnknownUnicastStormThreshold(
			java.lang.Integer unknownUnicastStormThreshold) {
		this.unknownUnicastStormThreshold = unknownUnicastStormThreshold;
	}

	public java.lang.Long getUsPIR() {
		return usPIR;
	}

	public void setUsPIR(java.lang.Long usPIR) {
		this.usPIR = usPIR;
	}

	public java.lang.Integer getUpSNR() {
		return upSNR;
	}

	public void setUpSNR(java.lang.Integer upSNR) {
		this.upSNR = upSNR;
	}

	public java.lang.Integer getDownSNR() {
		return downSNR;
	}

	public void setDownSNR(java.lang.Integer downSNR) {
		this.downSNR = downSNR;
	}
 
 
 

}
