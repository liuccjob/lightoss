package com.pbn.oss.adaptor.eoc.bean;

public class EocModemPerforTable { 
    private java.lang.String modemSysUptime;
    private java.lang.Integer modemCpuLoad;
    private java.lang.Integer modemMemoryUsed;
    private String id;
    private String Mac;
    public EocModemPerforTable (
            java.lang.String modemSysUptime,
            java.lang.Integer modemCpuLoad,
            java.lang.Integer modemMemoryUsed,
            String id,
            String Mac) {
        this.modemSysUptime = modemSysUptime;
        this.modemCpuLoad = modemCpuLoad;
        this.modemMemoryUsed = modemMemoryUsed;
        this.id = id;
        this.Mac = Mac;
    }
    public java.lang.String getmodemSysUptime () {
        return modemSysUptime;
    }
    public void setmodemSysUptime ( java.lang.String modemSysUptime ) {
        this.modemSysUptime = modemSysUptime;
    }
    public java.lang.Integer getmodemCpuLoad () {
        return modemCpuLoad;
    }
    public void setmodemCpuLoad ( java.lang.Integer modemCpuLoad ) {
        this.modemCpuLoad = modemCpuLoad;
    }
    public java.lang.Integer getmodemMemoryUsed () {
        return modemMemoryUsed;
    }
    public void setmodemMemoryUsed ( java.lang.Integer modemMemoryUsed ) {
        this.modemMemoryUsed = modemMemoryUsed;
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
