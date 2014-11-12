package com.pbn.oss.adaptor.eoc.gcable.bean;

public class GCableEocCNUInforTable {
	
	private java.lang.String modelType;
	private java.lang.String fwVersion;
	private java.lang.String mac;
	private java.lang.Integer rfAttenuation;
	private java.lang.Integer rfDSQuality;
	private java.lang.Integer rfUSQuality;
 
	private java.lang.Long rfPhyDSRate;
	private java.lang.Long rfPhyUSRate;

    private java.lang.Integer index;
    private java.lang.String id;


	public GCableEocCNUInforTable(String fwVersion, String mac, Integer rfAttenuation,
			Integer rfDSQuality, Integer rfUSQuality, Long rfPhyDSRate, Long rfPhyUSRate, Integer index, String id) {
		super();
		this.fwVersion = fwVersion;
		this.mac = mac;
		this.rfAttenuation = rfAttenuation;
		this.rfDSQuality = rfDSQuality;
		this.rfUSQuality = rfUSQuality;
		this.rfPhyDSRate = rfPhyDSRate;
		this.rfPhyUSRate = rfPhyUSRate;
		this.index = index;
		this.id = id;
	}


	public GCableEocCNUInforTable() {
		super();
	}


	public java.lang.String getModelType() {
		return modelType;
	}


	public void setModelType(java.lang.String modelType) {
		this.modelType = modelType;
	}


	public java.lang.String getFwVersion() {
		return fwVersion;
	}


	public void setFwVersion(java.lang.String fwVersion) {
		this.fwVersion = fwVersion;
	}


	public java.lang.String getMac() {
		return mac;
	}


	public void setMac(java.lang.String mac) {
		this.mac = mac;
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


	public java.lang.Integer getRfUSQuality() {
		return rfUSQuality;
	}


	public void setRfUSQuality(java.lang.Integer rfUSQuality) {
		this.rfUSQuality = rfUSQuality;
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


	public java.lang.Integer getIndex() {
		return index;
	}


	public void setIndex(java.lang.Integer index) {
		this.index = index;
	}


	public java.lang.String getId() {
		return id;
	}


	public void setId(java.lang.String id) {
		this.id = id;
	}
    
}
