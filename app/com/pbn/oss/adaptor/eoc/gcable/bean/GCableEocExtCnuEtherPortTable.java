package com.pbn.oss.adaptor.eoc.gcable.bean;

public class GCableEocExtCnuEtherPortTable {
	private java.lang.Integer confAutoNeg;
	private java.lang.Integer confSpeed;
	private java.lang.Integer confDuplex;
	private java.lang.String id;
	
	public GCableEocExtCnuEtherPortTable(Integer confAutoNeg,
			Integer confSpeed, Integer confDuplex, String id) {
		super();
		this.confAutoNeg = confAutoNeg;
		this.confSpeed = confSpeed;
		this.confDuplex = confDuplex;
		this.id = id;
	}
	
	public GCableEocExtCnuEtherPortTable() {
		super();
	}
	public java.lang.Integer getConfAutoNeg() {
		return confAutoNeg;
	}
	public void setConfAutoNeg(java.lang.Integer confAutoNeg) {
		this.confAutoNeg = confAutoNeg;
	}
	public java.lang.Integer getConfSpeed() {
		return confSpeed;
	}
	public void setConfSpeed(java.lang.Integer confSpeed) {
		this.confSpeed = confSpeed;
	}
	public java.lang.Integer getConfDuplex() {
		return confDuplex;
	}
	public void setConfDuplex(java.lang.Integer confDuplex) {
		this.confDuplex = confDuplex;
	}
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
}
