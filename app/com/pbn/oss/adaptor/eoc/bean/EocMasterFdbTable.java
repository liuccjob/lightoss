package com.pbn.oss.adaptor.eoc.bean;

public class EocMasterFdbTable { 
    private java.lang.Integer apFdbMacIndex;
    private java.lang.String apFdbMacAddress;
    private java.lang.Integer apFdbVlanId;
    private java.lang.String apFdbPeerMac;

    private String id;
    public EocMasterFdbTable (
            java.lang.Integer apFdbMacIndex,
            java.lang.String apFdbMacAddress,
            java.lang.Integer apFdbVlanId,
            java.lang.String apFdbPeerMac,
            String id) {
        this.apFdbMacIndex = apFdbMacIndex;
        this.apFdbMacAddress = apFdbMacAddress;
        this.apFdbVlanId = apFdbVlanId;
        this.apFdbPeerMac = apFdbPeerMac;
        this.id = id;
    }
    public java.lang.Integer getapFdbMacIndex () {
        return apFdbMacIndex;
    }
    public void setapFdbMacIndex ( java.lang.Integer apFdbMacIndex ) {
        this.apFdbMacIndex = apFdbMacIndex;
    }
    public java.lang.String getapFdbMacAddress () {
        return apFdbMacAddress;
    }
    public void setapFdbMacAddress ( java.lang.String apFdbMacAddress ) {
        this.apFdbMacAddress = apFdbMacAddress;
    }
    public java.lang.Integer getapFdbVlanId () {
        return apFdbVlanId;
    }
    public void setapFdbVlanId ( java.lang.Integer apFdbVlanId ) {
        this.apFdbVlanId = apFdbVlanId;
    }
    public java.lang.String getapFdbPeerMac () {
        return apFdbPeerMac;
    }
    public void setapFdbPeerMac ( java.lang.String apFdbPeerMac ) {
        this.apFdbPeerMac = apFdbPeerMac;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
} 
