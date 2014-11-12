package com.pbn.oss.adaptor.eoc.bean;

public enum VendorType {

	VENDOR_THOMSON(1),
	VENDOR_YITONG(2);
	
	private int value;
	
	VendorType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
