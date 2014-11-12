package com.pbn.oss.adaptor.eoc.dongyan.snmp;

import org.snmp4j.mp.SnmpConstants;
 

public class SNMPConfig {
	private String agentIp;
	private int snmpPort = 161;
	private int snmpVer = SnmpConstants.version2c;
	private String readCommunity = "public";
	private String writeCommunity = "private";
	private int retries = 3;
	private int timeout = 5000;
	
	public String getAgentIp() {
		return agentIp;
	}

	public void setAgentIp(String agentIp) {
		this.agentIp = agentIp;
	}

	public int getSnmpPort() {
		return snmpPort;
	}

	public void setSnmpPort(int snmpPort) {
		this.snmpPort = snmpPort;
	}

	public int getSnmpVer() {
		return snmpVer;
	}

	public void setSnmpVer(int snmpVer) {
		this.snmpVer = snmpVer;
	}

	public String getReadCommunity() {
		return readCommunity;
	}

	public void setReadCommunity(String readCommunity) {
		this.readCommunity = readCommunity;
	}

	public String getWriteCommunity() {
		return writeCommunity;
	}

	public void setWriteCommunity(String writeCommunity) {
		this.writeCommunity = writeCommunity;
	}

	public int getRetries() {
		return retries;
	}

	public void setRetries(int retries) {
		this.retries = retries;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
