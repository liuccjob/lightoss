package com.pbn.oss.adaptor.eoc.bean;

public class EocMasterModemConfigTable { 

    private java.lang.Integer apMaxModemNumber;
    private java.lang.Integer apCurrentChannel;
    private java.lang.Integer apTxPower;
    private java.lang.Integer apRtsThreshold;
    private java.lang.Integer apReceivedNoiseLevel;
    private String id;
    public EocMasterModemConfigTable (
            java.lang.Integer apMaxModemNumber,
            java.lang.Integer apCurrentChannel,
            java.lang.Integer apTxPower,
            java.lang.Integer apRtsThreshold,
            java.lang.Integer apReceivedNoiseLevel,
            String id) {
        this.apMaxModemNumber = apMaxModemNumber;
        this.apCurrentChannel = apCurrentChannel;
        this.apTxPower = apTxPower;
        this.apRtsThreshold = apRtsThreshold;
        this.apReceivedNoiseLevel = apReceivedNoiseLevel;
    
        this.id = id;
    }
    public java.lang.Integer getapMaxModemNumber () {
        return apMaxModemNumber;
    }
    public void setapMaxModemNumber ( java.lang.Integer apMaxModemNumber ) {
        this.apMaxModemNumber = apMaxModemNumber;
    }
    public java.lang.Integer getapCurrentChannel () {
        return apCurrentChannel;
    }
    public void setapCurrentChannel ( java.lang.Integer apCurrentChannel ) {
        this.apCurrentChannel = apCurrentChannel;
    }
    public java.lang.Integer getapTxPower () {
        return apTxPower;
    }
    public void setapTxPower ( java.lang.Integer apTxPower ) {
        this.apTxPower = apTxPower;
    }
    public java.lang.Integer getapRtsThreshold () {
        return apRtsThreshold;
    }
    public void setapRtsThreshold ( java.lang.Integer apRtsThreshold ) {
        this.apRtsThreshold = apRtsThreshold;
    }
    public java.lang.Integer getapReceivedNoiseLevel () {
        return apReceivedNoiseLevel;
    }
    public void setapReceivedNoiseLevel ( java.lang.Integer apReceivedNoiseLevel ) {
        this.apReceivedNoiseLevel = apReceivedNoiseLevel;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
} 
