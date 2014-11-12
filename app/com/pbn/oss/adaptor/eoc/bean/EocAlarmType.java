package com.pbn.oss.adaptor.eoc.bean;

public enum EocAlarmType {

	coldStart(1),
	warmStart(2),
	linkStatusChange(3),
	authenticationFailure(4),
	
	apTemperatureAlarmTrap(5),
	apIpAddressChangeTrap(6),
	apCableAttenuationAlarmTrap(7),
	apBridgeFdbFullTrap(8),
	modemOfflineTrap(9),
	
	GBCbatCardCpuOverloadTrap(10),
	GBCbatCardMemoryOverloadTrap(11),
	GBEocCnuUnauthCnuFirstOnlineTrap(12),
	GBEocCnuRfAttenuationTrap(13),
	GBEocCnuLineStatusChangeTrap(14),
	GBEocCnuEtherPortLoopbackTrap(15),
	UnknowTypeTrap(16),
	GBEocCnuBackCnuFirstOnlineTrap(17),
	eocExtCbat12voltOverload(18),
	eocExtCbat60voltOverload(19),
	eocExtCbatCoaxialTrap(20),
	eocExtCbatGEUpDonwnTrap(21),
	GBCBATResetTrap(21),
 
	PM_Master_TxSpeed(411),
	PM_Master_RxSpeed(412),
	PM_Master_CNUOnlineCount(413),
	PM_Modem_RFAttenuation(433),
	PM_Modem_RFDownstreamQuality(434),
	PM_Modem_RFUpstreamQuality(435),
	PM_Modem_PhyDownstreamRate(436),
	PM_Modem_PhyUpstreamRate(437),
	PM_ONU_DownTraffic(438),
	PM_OLT_DownTraffic(439)
	;
	private int value;
	
	EocAlarmType(int value){
		this.value = value;
	}
 	public int getValue(){
		return value;
	}

}
