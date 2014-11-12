package com.pbn.oss.adaptor.eoc.bean;

 

public class GBEocCNUWhiteListTable{ 
	
	private java.lang.Integer eocCNUWhiteListIndex;
	private java.lang.String eocCNUWhiteListIPAddress;
	private java.lang.String eocCNUWhiteListMACAddress;
	private java.lang.Integer eocCNUWhiteListAuthorization;
	private java.lang.Integer eocCNUWhiteListRFOutputLevel;
	private java.lang.Integer eocCNUWhiteListAutoUpgradeEN;
	private java.lang.Integer eocCNUWhiteListRegisterOnlineStatus;
	private java.lang.Integer eocCNUWhiteListCBATCardIndex;
	private java.lang.Integer eocCNUWhiteListCNUIndex;
 
    private java.lang.String id;

	public GBEocCNUWhiteListTable() {
		super();
	}

	public GBEocCNUWhiteListTable(Integer eocCNUWhiteListIndex, String eocCNUWhiteListIPAddress,
			String eocCNUWhiteListMACAddress, Integer eocCNUWhiteListAuthorization,
			Integer eocCNUWhiteListRFOutputLevel, Integer eocCNUWhiteListAutoUpgradeEN,
			Integer eocCNUWhiteListRegisterOnlineStatus, Integer eocCNUWhiteListCBATCardIndex,
			Integer eocCNUWhiteListCNUIndex, String id) {
		super();
		this.eocCNUWhiteListIndex = eocCNUWhiteListIndex;
		this.eocCNUWhiteListIPAddress = eocCNUWhiteListIPAddress;
		this.eocCNUWhiteListMACAddress = eocCNUWhiteListMACAddress;
		this.eocCNUWhiteListAuthorization = eocCNUWhiteListAuthorization;
		this.eocCNUWhiteListRFOutputLevel = eocCNUWhiteListRFOutputLevel;
		this.eocCNUWhiteListAutoUpgradeEN = eocCNUWhiteListAutoUpgradeEN;
		this.eocCNUWhiteListRegisterOnlineStatus = eocCNUWhiteListRegisterOnlineStatus;
		this.eocCNUWhiteListCBATCardIndex = eocCNUWhiteListCBATCardIndex;
		this.eocCNUWhiteListCNUIndex = eocCNUWhiteListCNUIndex;
		this.id = id;
	}
	
	public GBEocCNUWhiteListTable(Integer eocCNUWhiteListAuthorization,
			Integer eocCNUWhiteListRegisterOnlineStatus, String id) {
		super();
		this.eocCNUWhiteListAuthorization = eocCNUWhiteListAuthorization;
		this.eocCNUWhiteListRegisterOnlineStatus = eocCNUWhiteListRegisterOnlineStatus;
		this.id = id;
	}
	
	//for DongYan
	public GBEocCNUWhiteListTable(Integer eocCNUWhiteListIndex,
			String eocCNUWhiteListIPAddress, String eocCNUWhiteListMACAddress,
			Integer eocCNUWhiteListAuthorization,
			Integer eocCNUWhiteListRFOutputLevel,
			Integer eocCNUWhiteListAutoUpgradeEN,
			Integer eocCNUWhiteListRegisterOnlineStatus, String id) {
		super();
		this.eocCNUWhiteListIndex = eocCNUWhiteListIndex;
		this.eocCNUWhiteListIPAddress = eocCNUWhiteListIPAddress;
		this.eocCNUWhiteListMACAddress = eocCNUWhiteListMACAddress;
		this.eocCNUWhiteListAuthorization = eocCNUWhiteListAuthorization;
		this.eocCNUWhiteListRFOutputLevel = eocCNUWhiteListRFOutputLevel;
		this.eocCNUWhiteListAutoUpgradeEN = eocCNUWhiteListAutoUpgradeEN;
		this.eocCNUWhiteListRegisterOnlineStatus = eocCNUWhiteListRegisterOnlineStatus;
		this.id = id;
	}
	
	//for B-Star
	public GBEocCNUWhiteListTable(String eocCNUWhiteListIPAddress,
			String eocCNUWhiteListMACAddress,
			Integer eocCNUWhiteListAuthorization,
			Integer eocCNUWhiteListRFOutputLevel,
			Integer eocCNUWhiteListRegisterOnlineStatus,
			Integer eocCNUWhiteListCBATCardIndex,
			Integer eocCNUWhiteListCNUIndex, 
			String id) {
		super();
		this.eocCNUWhiteListIPAddress = eocCNUWhiteListIPAddress;
		this.eocCNUWhiteListMACAddress = eocCNUWhiteListMACAddress;
		this.eocCNUWhiteListAuthorization = eocCNUWhiteListAuthorization;
		this.eocCNUWhiteListRFOutputLevel = eocCNUWhiteListRFOutputLevel;
		this.eocCNUWhiteListRegisterOnlineStatus = eocCNUWhiteListRegisterOnlineStatus;
		this.eocCNUWhiteListCBATCardIndex = eocCNUWhiteListCBATCardIndex;
		this.eocCNUWhiteListCNUIndex = eocCNUWhiteListCNUIndex;
		this.id = id;
	}

	public java.lang.Integer getEocCNUWhiteListIndex() {
		return eocCNUWhiteListIndex;
	}

	public void setEocCNUWhiteListIndex(java.lang.Integer eocCNUWhiteListIndex) {
		this.eocCNUWhiteListIndex = eocCNUWhiteListIndex;
	}

	public java.lang.String getEocCNUWhiteListIPAddress() {
		return eocCNUWhiteListIPAddress;
	}

	public void setEocCNUWhiteListIPAddress(java.lang.String eocCNUWhiteListIPAddress) {
		this.eocCNUWhiteListIPAddress = eocCNUWhiteListIPAddress;
	}

	public java.lang.String getEocCNUWhiteListMACAddress() {
		return eocCNUWhiteListMACAddress;
	}

	public void setEocCNUWhiteListMACAddress(java.lang.String eocCNUWhiteListMACAddress) {
		this.eocCNUWhiteListMACAddress = eocCNUWhiteListMACAddress;
	}


	public java.lang.Integer getEocCNUWhiteListAuthorization() {
		return eocCNUWhiteListAuthorization;
	}


	public void setEocCNUWhiteListAuthorization(java.lang.Integer eocCNUWhiteListAuthorization) {
		this.eocCNUWhiteListAuthorization = eocCNUWhiteListAuthorization;
	}


	public java.lang.Integer getEocCNUWhiteListRFOutputLevel() {
		return eocCNUWhiteListRFOutputLevel;
	}


	public void setEocCNUWhiteListRFOutputLevel(java.lang.Integer eocCNUWhiteListRFOutputLevel) {
		this.eocCNUWhiteListRFOutputLevel = eocCNUWhiteListRFOutputLevel;
	}


	public java.lang.Integer getEocCNUWhiteListAutoUpgradeEN() {
		return eocCNUWhiteListAutoUpgradeEN;
	}

	public void setEocCNUWhiteListAutoUpgradeEN(java.lang.Integer eocCNUWhiteListAutoUpgradeEN) {
		this.eocCNUWhiteListAutoUpgradeEN = eocCNUWhiteListAutoUpgradeEN;
	}

	public java.lang.Integer getEocCNUWhiteListRegisterOnlineStatus() {
		return eocCNUWhiteListRegisterOnlineStatus;
	}

	public void setEocCNUWhiteListRegisterOnlineStatus(java.lang.Integer eocCNUWhiteListRegisterOnlineStatus) {
		this.eocCNUWhiteListRegisterOnlineStatus = eocCNUWhiteListRegisterOnlineStatus;
	}

	public java.lang.Integer getEocCNUWhiteListCBATCardIndex() {
		return eocCNUWhiteListCBATCardIndex;
	}

	public void setEocCNUWhiteListCBATCardIndex(java.lang.Integer eocCNUWhiteListCBATCardIndex) {
		this.eocCNUWhiteListCBATCardIndex = eocCNUWhiteListCBATCardIndex;
	}

	public java.lang.Integer getEocCNUWhiteListCNUIndex() {
		return eocCNUWhiteListCNUIndex;
	}

	public void setEocCNUWhiteListCNUIndex(java.lang.Integer eocCNUWhiteListCNUIndex) {
		this.eocCNUWhiteListCNUIndex = eocCNUWhiteListCNUIndex;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}
 
}
