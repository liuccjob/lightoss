package com.pbn.oss.adaptor.eoc.bean;
/**
 * Used for define the resource type in RES_OBJECT
 * 
 * @author seven
 * @date 2014-01-07 19:57:26
 */
public enum ResType {
	CBAT(1),
	CBAT_CARD(2),
	CNU(3),
	CNU_PORT(4),
	;
	private int value;
	private ResType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
