package com.pbn.oss.adaptor.eoc.bean;

public class EocModemPortConfigTable { 

    private java.lang.Integer modemPortVlanId;
    private java.lang.Integer modemPortDefaultPriority;
    private java.lang.Integer modemPortUplinkBandwidth;
    private java.lang.Integer modemPortDownlinkBandwidth;
    private java.lang.Integer modemPortAllowMacNumber;
    private java.lang.Integer modemPortAdminStatus;
    private String id;
    private String Mac;
    private String Port;
    public EocModemPortConfigTable (

            java.lang.Integer modemPortVlanId,
            java.lang.Integer modemPortDefaultPriority,
            java.lang.Integer modemPortUplinkBandwidth,
            java.lang.Integer modemPortDownlinkBandwidth,
            java.lang.Integer modemPortAllowMacNumber,
            java.lang.Integer modemPortAdminStatus,
            String id,
            String Mac,
            String Port) {

        this.modemPortVlanId = modemPortVlanId;
        this.modemPortDefaultPriority = modemPortDefaultPriority;
        this.modemPortUplinkBandwidth = modemPortUplinkBandwidth;
        this.modemPortDownlinkBandwidth = modemPortDownlinkBandwidth;
        this.modemPortAllowMacNumber = modemPortAllowMacNumber;
        this.modemPortAdminStatus = modemPortAdminStatus;
        this.id = id;
        this.Mac = Mac;
        this.Port = Port;
    }

    public java.lang.Integer getmodemPortVlanId () {
        return modemPortVlanId;
    }
    public void setmodemPortVlanId ( java.lang.Integer modemPortVlanId ) {
        this.modemPortVlanId = modemPortVlanId;
    }
    public java.lang.Integer getmodemPortDefaultPriority () {
        return modemPortDefaultPriority;
    }
    public void setmodemPortDefaultPriority ( java.lang.Integer modemPortDefaultPriority ) {
        this.modemPortDefaultPriority = modemPortDefaultPriority;
    }
    public java.lang.Integer getmodemPortUplinkBandwidth () {
        return modemPortUplinkBandwidth;
    }
    public void setmodemPortUplinkBandwidth ( java.lang.Integer modemPortUplinkBandwidth ) {
        this.modemPortUplinkBandwidth = modemPortUplinkBandwidth;
    }
    public java.lang.Integer getmodemPortDownlinkBandwidth () {
        return modemPortDownlinkBandwidth;
    }
    public void setmodemPortDownlinkBandwidth ( java.lang.Integer modemPortDownlinkBandwidth ) {
        this.modemPortDownlinkBandwidth = modemPortDownlinkBandwidth;
    }
    public java.lang.Integer getmodemPortAllowMacNumber () {
        return modemPortAllowMacNumber;
    }
    public void setmodemPortAllowMacNumber ( java.lang.Integer modemPortAllowMacNumber ) {
        this.modemPortAllowMacNumber = modemPortAllowMacNumber;
    }
    public java.lang.Integer getmodemPortAdminStatus () {
        return modemPortAdminStatus;
    }
    public void setmodemPortAdminStatus ( java.lang.Integer modemPortAdminStatus ) {
        this.modemPortAdminStatus = modemPortAdminStatus;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
    public String getMac() {
        return Mac;
    }
    public void setMac(String Mac) {
        this.Mac = Mac;
    }
    public String getPort() {
        return Port;
    }
    public void setPort(String Port) {
        this.Port = Port;
    }
} 
