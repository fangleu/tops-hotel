package com.travelzen.tops.hotel.elong.mongo.json.hotsuggest;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class SuggestNode {
	@JSONField(name = "CityId")
	private String cityId;
	@JSONField(name = "TypeId")
	private int typeId;
	@JSONField(name = "TypeName")
	private String typeName;
	@JSONField(name = "SuggestTagDataNodes")
	private List<SuggestTagDataNode> suggestTagDataNodes;
	@JSONField(name = "ChildCount")
	private int childCount;
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public List<SuggestTagDataNode> getSuggestTagDataNodes() {
		return suggestTagDataNodes;
	}
	public void setSuggestTagDataNodes(List<SuggestTagDataNode> suggestTagDataNodes) {
		this.suggestTagDataNodes = suggestTagDataNodes;
	}
	public int getChildCount() {
		return childCount;
	}
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	
}
