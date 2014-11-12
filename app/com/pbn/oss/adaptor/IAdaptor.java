package com.pbn.oss.adaptor;

import java.util.HashMap;

public interface IAdaptor {

	/**
	 * The vendor's authoritative identification of the
	 * network management subsystem contained in the
	 * entity.  This value is allocated within the SMI
	 * enterprises subtree (1.3.6.1.4.1) and provides an
	 * easy and unambiguous means for determining `what
	 * kind of box' is being managed.  For example, if
	 * vendor `Flintstones, Inc.' was assigned the
	 * subtree 1.3.6.1.4.1.4242, it could assign the
	 * identifier 1.3.6.1.4.1.4242.1.1 to its `Fred Router'.
	 */
	public static final String sysObjectID = "1.3.6.1.2.1.1.2.0";
	/**
	 * Bundle Adaptor Life Cycle Management
	 * Should be implemented in the Platform side
	 * 
	 * @return
	 */
 	public boolean register();
 	
 	public int getDomain();
 	
	public int getVendor();
	
	public int getChassis();

 	public HashMap<String, String> getSystemsByNetworkRange(String startIp, String endIp, int snmpVersion, String readCommunity);
}
