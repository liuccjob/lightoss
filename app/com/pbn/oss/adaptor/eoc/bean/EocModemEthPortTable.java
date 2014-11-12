package com.pbn.oss.adaptor.eoc.bean;

public class EocModemEthPortTable { 

    private java.lang.Long modemEthInOctets;
    private java.lang.Long modemEthInUnicastPackets;
    private java.lang.Long modemEthInNonUnicastPackets;
    private java.lang.Long modemEthInErrors;
    private java.lang.Long modemEthInDiscards;
    private java.lang.Long modemEthOutOctets;
    private java.lang.Long modemEthOutUnicastPackets;
    private java.lang.Long modemEthOutNonUnicastPackets;
    private java.lang.Long modemEthOutErrors;
    private java.lang.Long modemEthOutDiscards;
    
//    private java.lang.Long collectionTime;
//    private java.lang.Long isLatestRecord;
    private String id;
    private String Mac;
    private String Port;
    public EocModemEthPortTable (
  
            java.lang.Long modemEthInOctets,
            java.lang.Long modemEthInUnicastPackets,
            java.lang.Long modemEthInNonUnicastPackets,
            java.lang.Long modemEthInErrors,
            java.lang.Long modemEthInDiscards,
            java.lang.Long modemEthOutOctets,
            java.lang.Long modemEthOutUnicastPackets,
            java.lang.Long modemEthOutNonUnicastPackets,
            java.lang.Long modemEthOutErrors,
            java.lang.Long modemEthOutDiscards,
//            java.lang.Long isLatestRecord,
//            java.lang.Long collectionTime,
            String id,
            String Mac,
            String Port) {

        this.modemEthInOctets = modemEthInOctets;
        this.modemEthInUnicastPackets = modemEthInUnicastPackets;
        this.modemEthInNonUnicastPackets = modemEthInNonUnicastPackets;
        this.modemEthInErrors = modemEthInErrors;
        this.modemEthInDiscards = modemEthInDiscards;
        this.modemEthOutOctets = modemEthOutOctets;
        this.modemEthOutUnicastPackets = modemEthOutUnicastPackets;
        this.modemEthOutNonUnicastPackets = modemEthOutNonUnicastPackets;
        this.modemEthOutErrors = modemEthOutErrors;
        this.modemEthOutDiscards = modemEthOutDiscards;
//        this.isLatestRecord = isLatestRecord;
//        this.collectionTime = collectionTime;
        this.id = id;
        this.Mac = Mac;
        this.Port = Port;
    }
    public java.lang.Long getmodemEthInOctets () {
        return modemEthInOctets;
    }
    public void setmodemEthInOctets ( java.lang.Long modemEthInOctets ) {
        this.modemEthInOctets = modemEthInOctets;
    }
    public java.lang.Long getmodemEthInUnicastPackets () {
        return modemEthInUnicastPackets;
    }
    public void setmodemEthInUnicastPackets ( java.lang.Long modemEthInUnicastPackets ) {
        this.modemEthInUnicastPackets = modemEthInUnicastPackets;
    }
    public java.lang.Long getmodemEthInNonUnicastPackets () {
        return modemEthInNonUnicastPackets;
    }
    public void setmodemEthInNonUnicastPackets ( java.lang.Long modemEthInNonUnicastPackets ) {
        this.modemEthInNonUnicastPackets = modemEthInNonUnicastPackets;
    }
    public java.lang.Long getmodemEthInErrors () {
        return modemEthInErrors;
    }
    public void setmodemEthInErrors ( java.lang.Long modemEthInErrors ) {
        this.modemEthInErrors = modemEthInErrors;
    }
    public java.lang.Long getmodemEthInDiscards () {
        return modemEthInDiscards;
    }
    public void setmodemEthInDiscards ( java.lang.Long modemEthInDiscards ) {
        this.modemEthInDiscards = modemEthInDiscards;
    }
    public java.lang.Long getmodemEthOutOctets () {
        return modemEthOutOctets;
    }
    public void setmodemEthOutOctets ( java.lang.Long modemEthOutOctets ) {
        this.modemEthOutOctets = modemEthOutOctets;
    }
    public java.lang.Long getmodemEthOutUnicastPackets () {
        return modemEthOutUnicastPackets;
    }
    public void setmodemEthOutUnicastPackets ( java.lang.Long modemEthOutUnicastPackets ) {
        this.modemEthOutUnicastPackets = modemEthOutUnicastPackets;
    }
    public java.lang.Long getmodemEthOutNonUnicastPackets () {
        return modemEthOutNonUnicastPackets;
    }
    public void setmodemEthOutNonUnicastPackets ( java.lang.Long modemEthOutNonUnicastPackets ) {
        this.modemEthOutNonUnicastPackets = modemEthOutNonUnicastPackets;
    }
    public java.lang.Long getmodemEthOutErrors () {
        return modemEthOutErrors;
    }
    public void setmodemEthOutErrors ( java.lang.Long modemEthOutErrors ) {
        this.modemEthOutErrors = modemEthOutErrors;
    }
    public java.lang.Long getmodemEthOutDiscards () {
        return modemEthOutDiscards;
    }
    public void setmodemEthOutDiscards ( java.lang.Long modemEthOutDiscards ) {
        this.modemEthOutDiscards = modemEthOutDiscards;
    }
//    public java.lang.Long getisLatestRecord(){
//        return isLatestRecord;
//    }
//    public void setisLatestRecord( java.lang.Long isLatestRecord ) {
//        this.isLatestRecord = isLatestRecord;
//    }
//    
//    public java.lang.Long getcollectionTime(){
//        return collectionTime;
//    }
//    public void setcollectionTime( java.lang.Long collectionTime ) {
//        this.collectionTime = collectionTime;
//    }

    public String getId () {
        return id;
    }
    public void setId (String id) {
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
