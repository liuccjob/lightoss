package com.pbn.oss.adaptor.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.slf4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;


import com.pbn.oss.adaptor.IAdaptor;

public abstract class AbstractAdaptor implements IAdaptor{
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(AbstractAdaptor.class);
	//TODO: implements
	public boolean register() {
		// TODO refer to bundle context ?
		
		//record
		//interface
		
		return true;
	    	
		
	}

	public HashMap<String, String> getSystemsByNetworkRange(String startIp, String endIp, int snmpVersion, String readCommunity)
	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		int start = Integer.parseInt(startIp.substring(startIp.lastIndexOf(".")+1, startIp.length()));
//		int end = Integer.parseInt(endIp.substring(endIp.lastIndexOf(".")+1, endIp.length()));
//		Snmp snmp = null;
//		try {
//			TransportMapping transport = new DefaultUdpTransportMapping();
//			snmp = new Snmp(transport);
//			snmp.listen();
//			Vector<DiscoveryChassisSystem> vec = new Vector<DiscoveryChassisSystem>();
//			for(int i=start;i<=end;i++)
//			{
//				String hostip = startIp.substring(0, startIp.lastIndexOf(".")) + "." + i;
//				DiscoveryChassisSystem task = new DiscoveryChassisSystem(snmp, map, hostip, snmpVersion, readCommunity, i);
//				vec.add(task);
//				InitSimpleTaskExcutor.exec.execute(task);
//				
////				new Thread(task).start();
//			}
//			while(true)
//    	    {
//    		   int m = 0;
//    		   for(int j = 0;j < vec.size();j++){
//    			   logger.info("hostIp="+vec.get(j).getHostIp()+"="+vec.get(j).isFinished());
//	    		   if(!vec.get(j).isFinished()){
//	    			   m = 0;
//	    			   break;
//	    		   }else{
//	    			   m++;
//	    		   }
//	    	   }
//    		   
//    		   if(m == vec.size()){
//    			   logger.info("return "+m+" of "+vec.size()+" discovery jobs ");
//    			   return map;
//    		   }
//    		   try{
//    			   Thread.sleep(3000);
//    		   }catch(Exception e){
//    			   logger.info(e+"");
//    		   }
//    	   }
//		} 
//		catch (Exception e) {
//			e.printStackTrace();
//		} 
//		finally
//		{
//			try {
//				if(snmp != null)
//				{
//					snmp.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return null;
	}
	
}
