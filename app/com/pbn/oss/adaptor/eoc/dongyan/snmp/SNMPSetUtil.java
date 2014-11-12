package com.pbn.oss.adaptor.eoc.dongyan.snmp;

import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Counter64;
import org.snmp4j.smi.Gauge32;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

/**
 * @author seven
 * @date 2014-04-17 22:21:24
 */
public enum SNMPSetUtil {
	OctetString,
	Integer32,
	IpAddress,
	Gauge32,
	Counter32,
	Counter64,
	OID,
	;
	public static Variable getVariable(SNMPSetUtil type, String value){
		Variable v = null;
		if(type == OctetString){
			v = new OctetString(value);
		}else if(type == Integer32){
			v = new Integer32(Integer.parseInt(value));
		}else if(type == IpAddress){
			v = new IpAddress(value);
		}else if(type == Gauge32){
			v = new Gauge32(Long.parseLong(value));
		}else if(type == Counter32){
			v = new Counter32(Long.parseLong(value));
		}else if(type == Counter64){
			v = new Counter64(Long.parseLong(value));
		}else if(type == OID){
			v = new OID(value);
		}
		return v;
	}
}
