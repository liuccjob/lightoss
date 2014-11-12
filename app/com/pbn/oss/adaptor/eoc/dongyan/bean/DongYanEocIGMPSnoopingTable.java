package com.pbn.oss.adaptor.eoc.dongyan.bean;

public class DongYanEocIGMPSnoopingTable {
	private Integer igmpCardIndex;	
	private Integer igmpCNUIndex;
	private String igmpMac;
	private Integer igmpEN;
	private String id;
	
	public DongYanEocIGMPSnoopingTable() {
		super();
	}

	public DongYanEocIGMPSnoopingTable(Integer igmpCNUIndex,Integer igmpEN, String id) {
		super();
		this.igmpCNUIndex = igmpCNUIndex;
		this.igmpEN = igmpEN;
		this.id = id;
	}

	public Integer getIgmpCardIndex() {
		return igmpCardIndex;
	}

	public void setIgmpCardIndex(Integer igmpCardIndex) {
		this.igmpCardIndex = igmpCardIndex;
	}

	public Integer getIgmpCNUIndex() {
		return igmpCNUIndex;
	}

	public void setIgmpCNUIndex(Integer igmpCNUIndex) {
		this.igmpCNUIndex = igmpCNUIndex;
	}

	public String getIgmpMac() {
		return igmpMac;
	}

	public void setIgmpMac(String igmpMac) {
		this.igmpMac = igmpMac;
	}

	public Integer getIgmpEN() {
		return igmpEN;
	}

	public void setIgmpEN(Integer igmpEN) {
		this.igmpEN = igmpEN;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
