package com.pbn.oss.adaptor.eoc.bean;

public class EocMasterSysVersionTable { 
    private java.lang.String apModelType;
    private java.lang.String apMac;
    private java.lang.String apCurrentFwVersion;
    private java.lang.String apUpTime;
    private java.lang.String apName;
    private String id;
    public EocMasterSysVersionTable (
    		java.lang.String apMac,
            java.lang.String apModelType,
            java.lang.String apCurrentFwVersion,
            java.lang.String apUpTime,
            java.lang.String apName,
            String id) {
        this.apModelType = apModelType;
        this.apMac = apMac;
        this.apCurrentFwVersion = apCurrentFwVersion;
        this.id = id;
        this.apUpTime = apUpTime;
        this.apName = apName;
    }
    
    public java.lang.String getapUpTime() {
        return apUpTime;
    }
    public void setapUpTime ( java.lang.String apUpTime ) {
        this.apUpTime = apUpTime;
    }
    public java.lang.String getapName() {
        return apName;
    }
    public void setapName( java.lang.String apName ) {
        this.apName = apName;
    }
    public java.lang.String getapModelType () {
        return apModelType;
    }
    public void setapModelType ( java.lang.String apModelType ) {
        this.apModelType = apModelType;
    }
    public java.lang.String getapCurrentFwVersion () {
        return apCurrentFwVersion;
    }
    public void setapCurrentFwVersion ( java.lang.String apCurrentFwVersion ) {
        this.apCurrentFwVersion = apCurrentFwVersion;
    }
    public java.lang.String getapMac() {
        return apMac;
    }
    public void setapMac ( java.lang.String apMac ) {
        this.apMac = apMac;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
} 
