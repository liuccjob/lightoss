package com.pbn.oss.adaptor.eoc.gcable.bean;

public class GCableEocBroadcastStormTable {
	private Integer stormCBATIndex;
	private Integer stormCNUIndex;
	private Integer stormPortIndex;
	private Integer stormEN;
	private Integer stormThreshold;
	private Integer multiStormEN;
	private Integer multiStormThreshold;
	private Integer unStormEN;
	private Integer unStormThreshold;
	private String id;
	
	public GCableEocBroadcastStormTable() {
		super();
	}

	public GCableEocBroadcastStormTable( 
			Integer stormEN,
			Integer stormThreshold,
			Integer multiStormEN,
			Integer multiStormThreshold,
			Integer unStormEN,
			Integer unStormThreshold, 
			String id) {
		super();
		this.stormEN = stormEN;
		this.stormThreshold = stormThreshold;
		this.multiStormEN = multiStormEN;
		this.multiStormThreshold = multiStormThreshold;
		this.unStormEN = unStormEN;
		this.unStormThreshold = unStormThreshold;
		this.id = id;
	}

	public Integer getStormCBATIndex() {
		return stormCBATIndex;
	}

	public void setStormCBATIndex(Integer stormCBATIndex) {
		this.stormCBATIndex = stormCBATIndex;
	}

	public Integer getStormCNUIndex() {
		return stormCNUIndex;
	}

	public void setStormCNUIndex(Integer stormCNUIndex) {
		this.stormCNUIndex = stormCNUIndex;
	}

	public Integer getStormPortIndex() {
		return stormPortIndex;
	}

	public void setStormPortIndex(Integer stormPortIndex) {
		this.stormPortIndex = stormPortIndex;
	}

	public Integer getStormEN() {
		return stormEN;
	}

	public void setStormEN(Integer stormEN) {
		this.stormEN = stormEN;
	}

	public Integer getStormThreshold() {
		return stormThreshold;
	}

	public void setStormThreshold(Integer stormThreshold) {
		this.stormThreshold = stormThreshold;
	}

	public Integer getMultiStormEN() {
		return multiStormEN;
	}

	public void setMultiStormEN(Integer multiStormEN) {
		this.multiStormEN = multiStormEN;
	}

	public Integer getMultiStormThreshold() {
		return multiStormThreshold;
	}

	public void setMultiStormThreshold(Integer multiStormThreshold) {
		this.multiStormThreshold = multiStormThreshold;
	}

	public Integer getUnStormEN() {
		return unStormEN;
	}

	public void setUnStormEN(Integer unStormEN) {
		this.unStormEN = unStormEN;
	}

	public Integer getUnStormThreshold() {
		return unStormThreshold;
	}

	public void setUnStormThreshold(Integer unStormThreshold) {
		this.unStormThreshold = unStormThreshold;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
