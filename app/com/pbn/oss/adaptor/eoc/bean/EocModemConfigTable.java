package com.pbn.oss.adaptor.eoc.bean;

public class EocModemConfigTable { 

    private java.lang.Integer apToModemTxDataRateCurrent;
    private java.lang.Integer modemToApTxDataRateCurrent;
    private java.lang.Integer modemRtsThreshold;
    private java.lang.Integer modemTxPower;
    private String id;
    private String Mac;
    public EocModemConfigTable (

            java.lang.Integer apToModemTxDataRateCurrent,
            java.lang.Integer modemToApTxDataRateCurrent,
            java.lang.Integer modemRtsThreshold,
            java.lang.Integer modemTxPower,
            String id,String Mac) {

        this.apToModemTxDataRateCurrent = apToModemTxDataRateCurrent;
        this.modemToApTxDataRateCurrent = modemToApTxDataRateCurrent;
        this.modemRtsThreshold = modemRtsThreshold;
        this.modemTxPower = modemTxPower;
        this.id = id;
        this.Mac = Mac;
    }

    public java.lang.Integer getapToModemTxDataRateCurrent () {
        return apToModemTxDataRateCurrent;
    }
    public void setapToModemTxDataRateCurrent ( java.lang.Integer apToModemTxDataRateCurrent ) {
        this.apToModemTxDataRateCurrent = apToModemTxDataRateCurrent;
    }
    public java.lang.Integer getmodemRtsThreshold () {
        return modemRtsThreshold;
    }
    public void setmodemRtsThreshold ( java.lang.Integer modemRtsThreshold ) {
        this.modemRtsThreshold = modemRtsThreshold;
    }
    public java.lang.Integer getmodemTxPower() {
        return modemTxPower;
    }
    public void setmodemTxPower( java.lang.Integer modemTxPower ) {
        this.modemTxPower = modemTxPower;
    }
    public java.lang.Integer getmodemToApTxDataRateCurrent () {
        return modemToApTxDataRateCurrent;
    }
    public void setmodemToApTxDataRateCurrent ( java.lang.Integer modemToApTxDataRateCurrent ) {
        this.modemToApTxDataRateCurrent = modemToApTxDataRateCurrent;
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
