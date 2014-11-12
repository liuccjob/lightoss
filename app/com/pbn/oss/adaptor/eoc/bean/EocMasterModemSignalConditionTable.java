package com.pbn.oss.adaptor.eoc.bean;

public class EocMasterModemSignalConditionTable{
	
    private java.lang.Integer modemSideReceivedNoiseLevel;
    private java.lang.Integer modemSideReceivedSignalRssi;
    private java.lang.Integer apSideReceivedSignalRssi;
    private java.lang.Integer modemSignalPathAttenuation;
    private String id;
    private String Mac;
    public EocMasterModemSignalConditionTable (
    		
            java.lang.Integer modemSideReceivedNoiseLevel,
            java.lang.Integer modemSideReceivedSignalRssi,
            java.lang.Integer apSideReceivedSignalRssi,
            java.lang.Integer modemSignalPathAttenuation,
            String id,
            String Mac) {
    	
        this.modemSideReceivedNoiseLevel = modemSideReceivedNoiseLevel;
        this.modemSideReceivedSignalRssi = modemSideReceivedSignalRssi;
        this.apSideReceivedSignalRssi = apSideReceivedSignalRssi;
        this.modemSignalPathAttenuation = modemSignalPathAttenuation;
        this.id = id;
        this.Mac = Mac;
    }
    public java.lang.Integer getmodemSideReceivedNoiseLevel () {
        return modemSideReceivedNoiseLevel;
    }
    public void setmodemSideReceivedNoiseLevel ( java.lang.Integer modemSideReceivedNoiseLevel ) {
        this.modemSideReceivedNoiseLevel = modemSideReceivedNoiseLevel;
    }
    public java.lang.Integer getmodemSideReceivedSignalRssi () {
        return modemSideReceivedSignalRssi;
    }
    public void setmodemSideReceivedSignalRssi ( java.lang.Integer modemSideReceivedSignalRssi ) {
        this.modemSideReceivedSignalRssi = modemSideReceivedSignalRssi;
    }
    public java.lang.Integer getapSideReceivedSignalRssi () {
        return apSideReceivedSignalRssi;
    }
    public void setapSideReceivedSignalRssi ( java.lang.Integer apSideReceivedSignalRssi ) {
        this.apSideReceivedSignalRssi = apSideReceivedSignalRssi;
    }
    public java.lang.Integer getmodemSignalPathAttenuation () {
        return modemSignalPathAttenuation;
    }
    public void setmodemSignalPathAttenuation ( java.lang.Integer modemSignalPathAttenuation ) {
        this.modemSignalPathAttenuation = modemSignalPathAttenuation;
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
