package com.travelzen.tops.hotel.elong.mongo.json.hotsuggest;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class HotCityNode {
	@JSONField(name = "Name")
	private String name;
	@JSONField(name = "NameEn")
	private String nameEn;
	@JSONField(name = "TabId")
	private String tabId;
	@JSONField(name = "CityList")
	private List<SubCityNode> cityList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public List<SubCityNode> getCityList() {
		return cityList;
	}
	public void setCityList(List<SubCityNode> cityList) {
		this.cityList = cityList;
	}
}
