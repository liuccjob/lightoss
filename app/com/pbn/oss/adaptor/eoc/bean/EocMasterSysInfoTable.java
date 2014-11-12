package com.pbn.oss.adaptor.eoc.bean;

public class EocMasterSysInfoTable { 
    private java.lang.Integer apCpuLoad;
    private java.lang.Integer apMemoryUsed;
    private java.lang.Integer apTemperatureThresholdMax;
    private java.lang.Integer apCurrentTemperature;
    private String id;
    public EocMasterSysInfoTable (
            java.lang.Integer apCpuLoad,
            java.lang.Integer apMemoryUsed,
            java.lang.Integer apTemperatureThresholdMax,
            java.lang.Integer apCurrentTemperature,
            String id) {
        this.apCpuLoad = apCpuLoad;
        this.apMemoryUsed = apMemoryUsed;
        this.apTemperatureThresholdMax = apTemperatureThresholdMax;
        this.apCurrentTemperature = apCurrentTemperature;
        this.id = id;
    }
    public java.lang.Integer getapCpuLoad () {
        return apCpuLoad;
    }
    public void setapCpuLoad ( java.lang.Integer apCpuLoad ) {
        this.apCpuLoad = apCpuLoad;
    }
    public java.lang.Integer getapMemoryUsed () {
        return apMemoryUsed;
    }
    public void setapMemoryUsed ( java.lang.Integer apMemoryUsed ) {
        this.apMemoryUsed = apMemoryUsed;
    }
    public java.lang.Integer getapTemperatureThresholdMax () {
        return apTemperatureThresholdMax;
    }
    public void setapTemperatureThresholdMax ( java.lang.Integer apTemperatureThresholdMax ) {
        this.apTemperatureThresholdMax = apTemperatureThresholdMax;
    }
    public java.lang.Integer getapCurrentTemperature () {
        return apCurrentTemperature;
    }
    public void setapCurrentTemperature ( java.lang.Integer apCurrentTemperature ) {
        this.apCurrentTemperature = apCurrentTemperature;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
} 
