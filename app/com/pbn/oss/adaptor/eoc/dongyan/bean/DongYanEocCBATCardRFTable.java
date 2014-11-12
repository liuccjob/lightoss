package com.pbn.oss.adaptor.eoc.dongyan.bean;

public class DongYanEocCBATCardRFTable {
	private java.lang.Integer rfLinkMax;
    
	private java.lang.Integer rfOutputLevel;
	private java.lang.Long rfDsStartFrq;
	private java.lang.Long rfDsStopFrq;
	private java.lang.Long rfUsStartFrq;
	private java.lang.Long rfUsStopFrq;
 
     
    private java.lang.String id;


	public DongYanEocCBATCardRFTable(Integer rfLinkMax,
			Integer rfOutputLevel, Long rfDsStartFrq, Long rfDsStopFrq,
			Long rfUsStartFrq, Long rfUsStopFrq, String id) {
		super();
		this.rfLinkMax = rfLinkMax;
		this.rfOutputLevel = rfOutputLevel;
		this.rfDsStartFrq = rfDsStartFrq;
		this.rfDsStopFrq = rfDsStopFrq;
		this.rfUsStartFrq = rfUsStartFrq;
		this.rfUsStopFrq = rfUsStopFrq;
		this.id = id;
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


	public java.lang.String getId() {
		return id;
	}


	public void setId(java.lang.String id) {
		this.id = id;
	}
    
    

}
