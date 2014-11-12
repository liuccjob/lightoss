package com.pbn.oss.adaptor.eoc.gcable.bean;

/**
 * @author think
 *
 */
public class GCableEocCNUServiceTable {
	private java.lang.Integer serviceIndex;
	private java.lang.String serviceName;
	private java.lang.Integer serviceType;
	private java.lang.Integer serviceQosPrio;
	private java.lang.Integer serviceDownCIR;
	private java.lang.Integer serviceUpCIR;
	private java.lang.Integer serviceDownPIR;
	private java.lang.Integer serviceUpPIR;
	private java.lang.String id;
	
	public GCableEocCNUServiceTable() {
		super();
	}

	public GCableEocCNUServiceTable(Integer serviceIndex, String serviceName,
			Integer serviceType, Integer serviceQosPrio,
			Integer serviceDownPIR, Integer serviceUpPIR, String id) {
		super();
		this.serviceIndex = serviceIndex;
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.serviceQosPrio = serviceQosPrio;
		this.serviceDownPIR = serviceDownPIR;
		this.serviceUpPIR = serviceUpPIR;
		this.id = id;
	}

	public java.lang.Integer getServiceIndex() {
		return serviceIndex;
	}

	public void setServiceIndex(java.lang.Integer serviceIndex) {
		this.serviceIndex = serviceIndex;
	}

	public java.lang.String getServiceName() {
		return serviceName;
	}

	public void setServiceName(java.lang.String serviceName) {
		this.serviceName = serviceName;
	}

	public java.lang.Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(java.lang.Integer serviceType) {
		this.serviceType = serviceType;
	}

	public java.lang.Integer getServiceQosPrio() {
		return serviceQosPrio;
	}

	public void setServiceQosPrio(java.lang.Integer serviceQosPrio) {
		this.serviceQosPrio = serviceQosPrio;
	}

	public java.lang.Integer getServiceDownCIR() {
		return serviceDownCIR;
	}

	public void setServiceDownCIR(java.lang.Integer serviceDownCIR) {
		this.serviceDownCIR = serviceDownCIR;
	}

	public java.lang.Integer getServiceUpCIR() {
		return serviceUpCIR;
	}

	public void setServiceUpCIR(java.lang.Integer serviceUpCIR) {
		this.serviceUpCIR = serviceUpCIR;
	}

	public java.lang.Integer getServiceDownPIR() {
		return serviceDownPIR;
	}

	public void setServiceDownPIR(java.lang.Integer serviceDownPIR) {
		this.serviceDownPIR = serviceDownPIR;
	}

	public java.lang.Integer getServiceUpPIR() {
		return serviceUpPIR;
	}

	public void setServiceUpPIR(java.lang.Integer serviceUpPIR) {
		this.serviceUpPIR = serviceUpPIR;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

}
