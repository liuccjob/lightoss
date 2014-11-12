package com.pbn.oss.adaptor.eoc;

import org.slf4j.Logger;

//import com.pbn.oss.adaptor.Alarm;
//import com.pbn.oss.adaptor.CRawAlarm;
import com.pbn.oss.adaptor.impl.AbstractAdaptor;

public abstract class AbstractEocAdaptor  extends AbstractAdaptor implements IEocAdaptor {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(AbstractEocAdaptor.class);

	public abstract void setHostIp(String hostIp);

	// public abstract DiskArraySystem getChassis(String hostip);

//	public abstract Alarm parseTrap(CRawAlarm ra);

	// public abstract List discoveryPhysicalInfo();
	//	
	// public abstract List discoveryLogicalInfo();
	//	
	// public abstract List discoveryPhysicalStatus();
	//	
	// public abstract List discoveryLogicalStatus();

//	public HashMap<?, ?> discovery(String hostIp, String xmlpath) {
//		// TODO: get xml location dynamically by relationship of domain and
//		// location
//
////		String xmlFile = "f://test/test.xml";
//		// String xmlFile = "test.xml";
//		CommonAdapter comAdapter = null;
//		HashMap<?, ?> map = null;
//		try {
//			comAdapter = new CommonAdapter(xmlpath);
//			// comAdapter.getValues("com.pbn.oss.adaptor.diskarray.DiskarrayLogicTable",
//			// ipAddress);
//			map = (HashMap<?, ?>) comAdapter.getAllValues(hostIp);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return map;
//	}


}
