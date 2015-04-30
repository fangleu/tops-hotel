package com.travelzen.tops.hotel.elong.mongo.json.hotsuggest;

import com.alibaba.fastjson.annotation.JSONField;

public class TypeNameNode {
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "PropertiesId")
	private String propertiesId;
	@JSONField(name = "Type")
	private int type;
	@JSONField(name = "Lat")
	private String lat;
	@JSONField(name = "Lng")
	private String lng;
	@JSONField(name = "Accept")
	private boolean accept;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPropertiesId() {
		return propertiesId;
	}
	public void setPropertiesId(String propertiesId) {
		this.propertiesId = propertiesId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public boolean isAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
}
