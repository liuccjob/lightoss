package com.pbn.oss.adaptor.eoc.bean;

public class EocModemVersionTable { 
    private java.lang.String modemModelType;
    private java.lang.String modemBoardVersion;
    private java.lang.String modemBoardSerialNumber;
    private java.lang.String modemCurrentFwVersion;
    private String id;
    private String Mac;
    public EocModemVersionTable (
            java.lang.String modemModelType,
            java.lang.String modemBoardVersion,
            java.lang.String modemBoardSerialNumber,
            java.lang.String modemCurrentFwVersion,
            String id,
            String Mac) {
        this.modemModelType = modemModelType;
        this.modemBoardVersion = modemBoardVersion;
        this.modemBoardSerialNumber = modemBoardSerialNumber;
        this.modemCurrentFwVersion = modemCurrentFwVersion;
        this.id = id;
        this.Mac = Mac;
    }
    public java.lang.String getmodemModelType () {
        return modemModelType;
    }
    public void setmodemModelType ( java.lang.String modemModelType ) {
        this.modemModelType = modemModelType;
    }
    public java.lang.String getmodemBoardVersion () {
        return modemBoardVersion;
    }
    public void setmodemBoardVersion ( java.lang.String modemBoardVersion ) {
        this.modemBoardVersion = modemBoardVersion;
    }
    public java.lang.String getmodemBoardSerialNumber () {
        return modemBoardSerialNumber;
    }
    public void setmodemBoardSerialNumber ( java.lang.String modemBoardSerialNumber ) {
        this.modemBoardSerialNumber = modemBoardSerialNumber;
    }
    public java.lang.String getmodemCurrentFwVersion () {
        return modemCurrentFwVersion;
    }
    public void setmodemCurrentFwVersion ( java.lang.String modemCurrentFwVersion ) {
        this.modemCurrentFwVersion = modemCurrentFwVersion;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
    public String getMac () {
        return Mac;
    }
    public void setMac ( String Mac ) {
        this.Mac = Mac;
    }
} 
