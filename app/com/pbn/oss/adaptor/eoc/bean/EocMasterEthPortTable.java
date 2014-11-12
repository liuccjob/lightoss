package com.pbn.oss.adaptor.eoc.bean;

public class EocMasterEthPortTable { 
    private java.lang.Long inOctets;
    private java.lang.Long inUnicastPackets;
    private java.lang.Long inNonUnicastPackets;
    private java.lang.Long inErrors;
    private java.lang.Long inDiscards;
    private java.lang.Long outOctets;
    private java.lang.Long outUnicastPackets;
    private java.lang.Long outNonUnicastPackets;
    private java.lang.Long outErrors;
    private java.lang.Long outDiscards;
//    private java.lang.Long collectionTime;
//    private java.lang.Long isLatestRecord;
    private String id;
    public EocMasterEthPortTable (
            java.lang.Long inOctets,
            java.lang.Long inUnicastPackets,
            java.lang.Long inNonUnicastPackets,
            java.lang.Long inErrors,
            java.lang.Long inDiscards,
            java.lang.Long outOctets,
            java.lang.Long outUnicastPackets,
            java.lang.Long outNonUnicastPackets,
            java.lang.Long outErrors,
            java.lang.Long outDiscards,
//            java.lang.Long isLatestRecord,
//            java.lang.Long collectionTime,
            String id) {
        this.inOctets = inOctets;
        this.inUnicastPackets = inUnicastPackets;
        this.inNonUnicastPackets = inNonUnicastPackets;
        this.inErrors = inErrors;
        this.inDiscards = inDiscards;
        this.outOctets = outOctets;
        this.outUnicastPackets = outUnicastPackets;
        this.outNonUnicastPackets = outNonUnicastPackets;
        this.outErrors = outErrors;
        this.outDiscards = outDiscards;
//        this.isLatestRecord = isLatestRecord;
//        this.collectionTime = collectionTime;
        this.id = id;
    }
    public java.lang.Long getinOctets () {
        return inOctets;
    }
    public void setinOctets ( java.lang.Long inOctets ) {
        this.inOctets = inOctets;
    }
    public java.lang.Long getinUnicastPackets () {
        return inUnicastPackets;
    }
    public void setinUnicastPackets ( java.lang.Long inUnicastPackets ) {
        this.inUnicastPackets = inUnicastPackets;
    }
    public java.lang.Long getinNonUnicastPackets () {
        return inNonUnicastPackets;
    }
    public void setinNonUnicastPackets ( java.lang.Long inNonUnicastPackets ) {
        this.inNonUnicastPackets = inNonUnicastPackets;
    }
    public java.lang.Long getinErrors () {
        return inErrors;
    }
    public void setinErrors ( java.lang.Long inErrors ) {
        this.inErrors = inErrors;
    }
    public java.lang.Long getinDiscards () {
        return inDiscards;
    }
    public void setinDiscards ( java.lang.Long inDiscards ) {
        this.inDiscards = inDiscards;
    }
    public java.lang.Long getoutOctets () {
        return outOctets;
    }
    public void setoutOctets ( java.lang.Long outOctets ) {
        this.outOctets = outOctets;
    }
    public java.lang.Long getoutUnicastPackets () {
        return outUnicastPackets;
    }
    public void setoutUnicastPackets ( java.lang.Long outUnicastPackets ) {
        this.outUnicastPackets = outUnicastPackets;
    }
    public java.lang.Long getoutNonUnicastPackets () {
        return outNonUnicastPackets;
    }
    public void setoutNonUnicastPackets ( java.lang.Long outNonUnicastPackets ) {
        this.outNonUnicastPackets = outNonUnicastPackets;
    }
    public java.lang.Long getoutErrors () {
        return outErrors;
    }
    public void setoutErrors ( java.lang.Long outErrors ) {
        this.outErrors = outErrors;
    }
    public java.lang.Long getoutDiscards () {
        return outDiscards;
    }
    public void setoutDiscards ( java.lang.Long outDiscards ) {
        this.outDiscards = outDiscards;
    }
//    public java.lang.Long getisLatestRecord(){
//        return isLatestRecord;
//    }
//    public void setisLatestRecord( java.lang.Long isLatestRecord ) {
//    	this.isLatestRecord = isLatestRecord;
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
    public void setId ( String id ) {
        this.id = id;
    }
} 
