package com.pbn.oss.adaptor.eoc.bean;

public class EocMasterModemListTable { 
    private java.lang.String modemMacAddress;
    private java.lang.String modemHostName;
    private java.lang.Integer modemCurrentState;
    private java.lang.Integer modemAdminStatus;
    private String id;
    public EocMasterModemListTable (
            java.lang.String modemMacAddress,
            java.lang.String modemHostName,
            java.lang.Integer modemCurrentState,
            java.lang.Integer modemAdminStatus,
            String id) {
        this.modemMacAddress = modemMacAddress;
        this.modemHostName = modemHostName;
        this.modemCurrentState = modemCurrentState;
        this.modemAdminStatus = modemAdminStatus;
        this.id = id;
    }
    public java.lang.String getmodemMacAddress () {
        return modemMacAddress;
    }
    public void setmodemMacAddress ( java.lang.String modemMacAddress ) {
        this.modemMacAddress = modemMacAddress;
    }
    public java.lang.String getmodemHostName () {
        return modemHostName;
    }
    public void setmodemHostName ( java.lang.String modemHostName ) {
        this.modemHostName = modemHostName;
    }
    public java.lang.Integer getmodemCurrentState () {
        return modemCurrentState;
    }
    public void setmodemCurrentState ( java.lang.Integer modemCurrentState ) {
        this.modemCurrentState = modemCurrentState;
    }
    public java.lang.Integer getmodemAdminStatus () {
        return modemAdminStatus;
    }
    public void setmodemAdminStatus ( java.lang.Integer modemAdminStatus ) {
        this.modemAdminStatus = modemAdminStatus;
    }
    public String getId () {
        return id;
    }
    public void setId ( String id ) {
        this.id = id;
    }
} 
