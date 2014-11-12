package com.pbn.oss.adaptor.eoc.dongyan.bean;

public class DongYanEocCNUPortInfoTable {
	
	private java.lang.Integer cnuIndex;
	private java.lang.Integer portIndex;
	private java.lang.Integer portPVID;
	private java.lang.Integer portTPID;
	private java.lang.Integer portPRIO;
 
	private java.lang.String portVIDList;
	private java.lang.String portUntaggedVIDList;
 
	private java.lang.Integer portMode;
 
	private java.lang.String id;

	public DongYanEocCNUPortInfoTable() {
		super();
	}
	
	public DongYanEocCNUPortInfoTable(Integer cnuIndex, Integer portIndex,
			Integer portPVID, Integer portTPID, Integer portPRIO,
			String portVIDList, String portUntaggedVIDList, Integer portMode,
			String id) {
		super();
		this.cnuIndex = cnuIndex;
		this.portIndex = portIndex;
		this.portPVID = portPVID;
		this.portTPID = portTPID;
		this.portPRIO = portPRIO;
		this.portVIDList = portVIDList;
		this.portUntaggedVIDList = portUntaggedVIDList;
		this.portMode = portMode;
		this.id = id;
	}


	public java.lang.Integer getCnuIndex() {
		return cnuIndex;
	}

	public void setCnuIndex(java.lang.Integer cnuIndex) {
		this.cnuIndex = cnuIndex;
	}

	public java.lang.Integer getPortIndex() {
		return portIndex;
	}

	public void setPortIndex(java.lang.Integer portIndex) {
		this.portIndex = portIndex;
	}

	public java.lang.Integer getPortPVID() {
		return portPVID;
	}

	public void setPortPVID(java.lang.Integer portPVID) {
		this.portPVID = portPVID;
	}

	public java.lang.Integer getPortTPID() {
		return portTPID;
	}

	public void setPortTPID(java.lang.Integer portTPID) {
		this.portTPID = portTPID;
	}

	public java.lang.Integer getPortPRIO() {
		return portPRIO;
	}

	public void setPortPRIO(java.lang.Integer portPRIO) {
		this.portPRIO = portPRIO;
	}

	public java.lang.String getPortVIDList() {
		return portVIDList;
	}

	public void setPortVIDList(java.lang.String portVIDList) {
		this.portVIDList = portVIDList;
	}

	public java.lang.String getPortUntaggedVIDList() {
		return portUntaggedVIDList;
	}

	public void setPortUntaggedVIDList(java.lang.String portUntaggedVIDList) {
		this.portUntaggedVIDList = portUntaggedVIDList;
	}

	public java.lang.Integer getPortMode() {
		return portMode;
	}

	public void setPortMode(java.lang.Integer portMode) {
		this.portMode = portMode;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

}
