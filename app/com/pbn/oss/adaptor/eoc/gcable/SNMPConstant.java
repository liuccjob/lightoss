package com.pbn.oss.adaptor.eoc.gcable;
/**
 * @author seven
 * @date 2014-07-20 15:37:15
 */
public interface SNMPConstant {
	
	public static String snmpTrapOID = "1.3.6.1.6.3.1.1.4.1";
	
	//CNU
	public static final String eocCNUMACAddress = "1.3.6.1.4.1.42342.2.4.5.2.4.1.3";
	public static final String eocCNURFAttenuation = "1.3.6.1.4.1.42342.2.4.5.2.4.1.10";
	public static final String eocCNURFDownstreamQuality = "1.3.6.1.4.1.42342.2.4.5.2.4.1.11";
	public static final String eocCNURFUpstreamQuality = "1.3.6.1.4.1.42342.2.4.5.2.4.1.12";
	public static final String eocCNUPhyDownstreamRate = "1.3.6.1.4.1.42342.2.4.5.2.4.1.13";
	public static final String eocCNUPhyUpstreamRate = "1.3.6.1.4.1.42342.2.4.5.2.4.1.14";
	
	public static String eocCbatHighTemperatureAlm = "1.3.6.1.4.1.42342.2.2.1.1.1";//eocCbatHighTemperatureAlm { eocCbatEnvTemperature, eocCbatEnvTempHighThreshold }
	public static String eocCbatHighTemperatureClr = "1.3.6.1.4.1.42342.2.2.1.1.2";//eocCbatHighTemperatureClr { eocCbatEnvTemperature, eocCbatEnvTempHighThreshold }
	
	public static String eocCbatLowTemperatureAlm = "1.3.6.1.4.1.42342.2.2.1.1.3";//eocCbatLowTemperatureAlm { eocCbatEnvTemperature, eocCbatEnvTempLowThreshold }
	public static String eocCbatLowTemperatureClr = "1.3.6.1.4.1.42342.2.2.1.1.4";//eocCbatLowTemperatureClr { eocCbatEnvTemperature, eocCbatEnvTempLowThreshold }
	
	public static String eocCbatCardCpuOverloadAlm="1.3.6.1.4.1.42342.2.2.1.1.5";//eocCbatCardCpuOverloadAlm { eocCbatCardCpuLoad, eocCbatCardCpuLoadThreshold }
	public static String eocCbatCardCpuOverloadClr="1.3.6.1.4.1.42342.2.2.1.1.6";//eocCbatCardCpuOverloadClr { eocCbatCardCpuLoad, eocCbatCardCpuLoadThreshold }
	
	public static String eocCbatCardMemOverloadAlm="1.3.6.1.4.1.42342.2.2.1.1.7";//eocCbatCardMemOverloadAlm { eocCbatCardMemUsage, eocCbatCardMemUsageThreshold }
	public static String eocCbatCardMemOverloadClr="1.3.6.1.4.1.42342.2.2.1.1.8";//eocCbatCardMemOverloadClr { eocCbatCardMemUsage, eocCbatCardMemUsageThreshold }
	
	public static String eocCnuUnauthCnuFirstOnline="1.3.6.1.4.1.42342.2.2.1.3.1";//eocCnuUnauthCnuFirstOnline { eocCNUUnauthListCltNo, eocCNUUnauthListCnuNo }
	public static String eocCnuEtherPortLoopbackAlm="1.3.6.1.4.1.42342.2.2.1.3.2";//eocCnuEtherPortLoopbackAlm{  eocCnuEtherPortActualSpeed  }
	public static String eocCnuEtherPortLoopbackClr="1.3.6.1.4.1.42342.2.2.1.3.3";//eocCnuEtherPortLoopbackAlm{  eocCnuEtherPortActualSpeed  }
	public static String eocCnuRfHighAttenuationAlm="1.3.6.1.4.1.42342.2.2.1.3.4";//eocCnuRfHighAttenuationAlm{ eocCNURFAttenuation, eocCnuRfHighAtnThreshold }
	public static String eocCnuRfHighAttenuationClr="1.3.6.1.4.1.42342.2.2.1.3.5";//eocCnuRfHighAttenuationClr{ eocCNURFAttenuation, eocCnuRfHighAtnThreshold }
	public static String eocCnuRfLowAttenuationAlm="1.3.6.1.4.1.42342.2.2.1.3.6";//eocCnuRfLowAttenuationAlm{ eocCNURFAttenuation, eocCnuRfLowAtnThreshold }
	public static String eocCnuRfLowAttenuationClr="1.3.6.1.4.1.42342.2.2.1.3.7";//eocCnuRfLowAttenuationClr{ eocCNURFAttenuation, eocCnuRfLowAtnThreshold }
	public static String eocCnuLineStatusChange="1.3.6.1.4.1.42342.2.2.1.3.9";//eocCnuLineStatusChange{ eocCNUMACAddress, eocCNUOnlineStatus}
 
	
	public static String eocCbatCardMemUsage = "1.3.6.1.4.1.42342.2.1.1.1.1.6";
	public static String eocCbatCardCpuLoad = "1.3.6.1.4.1.42342.2.1.1.1.1.4";
	public static String eocCbatEnvTemperature = "1.3.6.1.4.1.42342.2.1.1.1.1.1";
	public static String eocCNUUnauthListCltNo = "1.3.6.1.4.1.42342.2.4.5.2.4.1.1";
	public static String eocCNUUnauthListCnuNo = "1.3.6.1.4.1.42342.2.4.5.2.4.1.2";
	public static String eocCnuEtherPortActualSpeed = "1.3.6.1.4.1.42342.2.4.20.1.2.1.1.1.6";
	public static String eocCbatCardMACAddress = "1.3.6.1.4.1.42342.2.4.3.2.1.2";
	public static String eocCNUOnlineStatus="1.3.6.1.4.1.42342.2.4.5.2.4.1.4";
 
	public static String eocCardAlarm="1.3.6.1.4.1.42342.2.2.1.101.1.1";//???
	
	//CNU template
	public static String tempRowStatus = "1.3.6.1.4.1.42342.1.4.1.4.2.1.39";
	public static String numOfTemplates = "1.3.6.1.4.1.42342.1.4.1.4.1.0";
	public static String templateApply = "1.3.6.1.4.1.42342.1.4.1.3.1.1.1.5";
	
	//CNU portService OID
	public static String cnuServiceStatus = "1.3.6.1.4.1.42342.2.4.5.3.2.1.10";
	public static String portServiceOid = "1.3.6.1.4.1.42342.2.4.5.4.3.1.5";
	public static String serviceDownPIR = "1.3.6.1.4.1.42342.2.4.5.3.2.1.5";
	public static String serviceUpPIR = "1.3.6.1.4.1.42342.2.4.5.3.2.1.6";
	public static String serviceName= "1.3.6.1.4.1.42342.2.4.5.3.2.1.2";
	public static final int RowStatus_active = 1;
	public static final int RowStatus_notInService = 2;
	public static final int RowStatus_notReady = 3;
	public static final int RowStatus_createAndGo = 4;
	public static final int RowStatus_createAndWait = 5;
	public static final int RowStatus_destroy = 6;
	
	//CNU Duplex and Speed
	public static Object autoNegValueD = 2;
	public static Object autoNegValueE = 1;
	public static String portConfAutoNeg = "1.3.6.1.4.1.42342.2.4.20.1.2.1.1.4";
	public static String portConfSpeed = "1.3.6.1.4.1.42342.2.4.20.1.2.1.1.5";
	public static String portConfDuplex = "1.3.6.1.4.1.42342.2.4.20.1.2.1.1.7";
}
