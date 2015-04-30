package com.travelzen.tops.hotel.elong.entity.selfpay.dto;


public class CityCode {
	/**
	 * 城市id
	 */
	private String cityId;
	/**
	 * 城市码
	 */
	private String cityCode;

	/**
	 * 城市名称
	 */
	private String cityNameCN;
	
	/**
	 * 城市英名
	 */
	private String cityNameEN;
	
	/**
	 * 国内国际
	 */
	private boolean isDomestic;
	
	/**
	 * 热点
	 */
	private Integer hotTag;
	
	/**
	 * 国家名称
	 */
	private String regionName;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityNameCN() {
		return cityNameCN;
	}

	public void setCityNameCN(String cityNameCN) {
		this.cityNameCN = cityNameCN;
	}

	public String getCityNameEN() {
		return cityNameEN;
	}

	public void setCityNameEN(String cityNameEN) {
		this.cityNameEN = cityNameEN;
	}

	public boolean isDomestic() {
		return isDomestic;
	}

	public void setDomestic(boolean isDomestic) {
		this.isDomestic = isDomestic;
	}

	public Integer getHotTag() {
		return hotTag;
	}

	public void setHotTag(Integer hotTag) {
		this.hotTag = hotTag;
	}


	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}
