package com.pbn.oss.adaptor.eoc.bean;
/**
 * @author seven
 */
public enum DiscoveryType {
	CBAT_ALL(1),
	CBAT_CARD(2),
	CNU_LIST(3),
	CNU_BASIC(4),
	CNU_CONFIG(5),
	CNU_PORT_LIST(6),
	CBAT_BASIC(7),
	CNU_ALL(8),
	
	PM_Master(410),
	PM_Master_TxSpeed(411),
	PM_Master_RxSpeed(412),
	PM_Master_CNUOnlineCount(413),
	PM_Modem(430),
	PM_Modem_TxSpeed(431),
	PM_Modem_RxSpeed(432),
	PM_Modem_RFAttenuation(433),
	PM_Modem_RFDownstreamQuality(434),
	PM_Modem_RFUpstreamQuality(435),
	PM_Modem_PhyDownstreamRate(436),
	PM_Modem_PhyUpstreamRate(437)

	

	;
	
	private int value;
	
	DiscoveryType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static DiscoveryType valueOf(int value){
		for(DiscoveryType type : values()){
			if(type.getValue() == value)
				return type;
		}
		return CBAT_ALL;
	}
}
