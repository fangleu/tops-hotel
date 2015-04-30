package com.travelzen.tops.hotel.elong.mongo.json.hotsuggest;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class KeywordsNode {
	@JSONField(name = "CityId")
	private String cityId;
	@JSONField(name = "Type")
	private int type;
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "TypeNameList")
	private List<TypeNameNode> typeNameList;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<TypeNameNode> getTypeNameList() {
		return typeNameList;
	}
	public void setTypeNameList(List<TypeNameNode> typeNameList) {
		this.typeNameList = typeNameList;
	}
	
	
}
