package com.travelzen.tops.hotel.elong.mongo.json.hotsuggest;

import com.alibaba.fastjson.annotation.JSONField;

public class SubCityNode {
	@JSONField(name = "CityCode")
	private String cityCode;
	@JSONField(name = "CityId")
	private String cityId;
	@JSONField(name = "CityNameCn")
	private String cityNameCn;
	@JSONField(name = "CityNameEn")
	private String cityNameEn;
	@JSONField(name = "CityThreeSign")
	private String cityThreeSign;
	@JSONField(name = "CityType")
	private String cityType;
	@JSONField(name = "OldEnglishName")
	private String oldEnglishName;
	@JSONField(name = "ProvinceId")
	private String provinceId;
	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityNameCn() {
		return cityNameCn;
	}
	public void setCityNameCn(String cityNameCn) {
		this.cityNameCn = cityNameCn;
	}
	public String getCityNameEn() {
		return cityNameEn;
	}
	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
	}
	public String getCityThreeSign() {
		return cityThreeSign;
	}
	public void setCityThreeSign(String cityThreeSign) {
		this.cityThreeSign = cityThreeSign;
	}
	public String getCityType() {
		return cityType;
	}
	public void setCityType(String cityType) {
		this.cityType = cityType;
	}
	public String getOldEnglishName() {
		return oldEnglishName;
	}
	public void setOldEnglishName(String oldEnglishName) {
		this.oldEnglishName = oldEnglishName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

}
