package com.pbn.oss.adaptor;

import java.util.HashMap;
import java.util.Map;

//import com.pbn.oss.fault.RawAlarm;
//import com.pbn.oss.fault.ActiveAlarm;


public interface IResourceAdaptor extends IAdaptor {

	public HashMap<?, ?> discovery(String hostIp, String xmlpath, int snmpVersion, String readComm, String writeComm);
	public HashMap<?, ?> discoveryModule(String hostIp, String xmlPath, int resType, HashMap<?, ?> map);
	
	public HashMap<?, ?> discoveryPMData(String hostIp, String xmlPath, int discoveryType, HashMap<?, ?> paramMap);
	
//	public ActiveAlarm parseAlarm(RawAlarm ra);
	
    public String applyTemplate(String hostIp, int domain, int vendor, int chassis, int resType, Map<?, ?> map);
    
	public HashMap<?, ?> resourceTest(HashMap<?, ?> map);

}
