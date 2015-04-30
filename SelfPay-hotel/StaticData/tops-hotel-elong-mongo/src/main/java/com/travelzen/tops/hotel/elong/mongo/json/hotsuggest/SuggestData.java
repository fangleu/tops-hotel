package com.travelzen.tops.hotel.elong.mongo.json.hotsuggest;

import com.alibaba.fastjson.annotation.JSONField;

public class SuggestData {
	@JSONField(name = "SuggestType")
	protected String suggestType;
	@JSONField(name = "BaiduLat")
	protected String baiduLat;
	@JSONField(name = "BaiduLng")
	protected String baiduLng;
	@JSONField(name = "DataName")
	private String dataName;
	@JSONField(name = "GoogleLat")
	private String googleLat;
	@JSONField(name = "GoogleLng")
	private String googleLng;
	@JSONField(name = "QueryWeight")
	private int queryWeight;
	@JSONField(name = "PropertiesId")
	private String propertiesId;
	@JSONField(name = "HotCount")
	private int hotCount;
	
	public String getSuggestType() {
		return suggestType;
	}
	public void setSuggestType(String suggestType) {
		this.suggestType = suggestType;
	}
	public String getBaiduLat() {
		return baiduLat;
	}
	public void setBaiduLat(String baiduLat) {
		this.baiduLat = baiduLat;
	}
	public String getBaiduLng() {
		return baiduLng;
	}
	public void setBaiduLng(String baiduLng) {
		this.baiduLng = baiduLng;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getGoogleLat() {
		return googleLat;
	}
	public void setGoogleLat(String googleLat) {
		this.googleLat = googleLat;
	}
	public String getGoogleLng() {
		return googleLng;
	}
	public void setGoogleLng(String googleLng) {
		this.googleLng = googleLng;
	}
	public int getQueryWeight() {
		return queryWeight;
	}
	public void setQueryWeight(int queryWeight) {
		this.queryWeight = queryWeight;
	}
	public String getPropertiesId() {
		return propertiesId;
	}
	public void setPropertiesId(String propertiesId) {
		this.propertiesId = propertiesId;
	}
	public int getHotCount() {
		return hotCount;
	}
	public void setHotCount(int hotCount) {
		this.hotCount = hotCount;
	}
	
	
	    
}
