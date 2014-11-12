package com.pbn.oss.adaptor.eoc.bean;

public class EocModemFdbTable { 
    private java.lang.Integer modemFdbEntryIndex;
    private java.lang.String modemFdbEntryMac;
    private java.lang.Integer modemFdbEntrySourcePort;
    private String id;
    public EocModemFdbTable (
            java.lang.Integer modemFdbEntryIndex,
            java.lang.String modemFdbEntryMac,
            java.lang.Integer modemFdbEntrySourcePort,
            String id) {
        this.modemFdbEntryIndex = modemFdbEntryIndex;
        this.modemFdbEntryMac = modemFdbEntryMac;
        this.modemFdbEntrySourcePort = modemFdbEntrySourcePort;
        this.id = id;
    }
    public java.lang.Integer getmodemFdbEntryIndex () {
        return modemFdbEntryIndex;
    }
    public void setmodemFdbEntryIndex ( java.lang.Integer modemFdbEntryIndex ) {
        this.modemFdbEntryIndex = modemFdbEntryIndex;
    }
    public java.lang.String getmodemFdbEntryMac () {
        return modemFdbEntryMac;
    }
    public void setmodemFdbEntryMac ( java.lang.String modemFdbEntryMac ) {
        this.modemFdbEntryMac = modemFdbEntryMac;
    }
    public java.lang.Integer getmodemFdbEntrySourcePort () {
        return modemFdbEntrySourcePort;
    }
    public void setmodemFdbEntrySourcePort ( java.lang.Integer modemFdbEntrySourcePort ) {
        this.modemFdbEntrySourcePort = modemFdbEntrySourcePort;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
} 
