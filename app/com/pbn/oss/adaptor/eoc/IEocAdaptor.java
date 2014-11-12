package com.pbn.oss.adaptor.eoc;

import java.util.HashMap;

import com.pbn.oss.adaptor.IResourceAdaptor;

public interface IEocAdaptor extends IResourceAdaptor {

//	public DiskArraySystem getChassis(String hostip);
//	
//	public List discoveryPhysicalInfo();
//	
//	public List discoveryLogicalInfo();
//	
//	public List discoveryPhysicalStatus();
//	
//	public List discoveryLogicalStatus();
	
	public HashMap<?, ?> discovery(String hostIp, String xmlpath);
}
