package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import java.util.List;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;

@Entity("elong.HotelGeo")
public class HotelGeo extends AbstractMorphiaEntity {
	/**
	 * 国家，不可为空
	 */
	private String country;
	
	/**
	 * 省份编号，不可为空
	 */
	private String provinceId;
	/**
	 * 城市名称，不可为空
	 */
	private String provinceName;
	
	/**
	 * 城市名称，不可为空
	 */
	private String cityName;
	/**
	 * 城市编码，不可为空
	 */
	@Indexed
	private String cityCode;
	
	/**
	 * 行政区
	 */
	private List<GeoLocation> districts;
	
	/**
	 * 商业区
	 */
	private List<GeoLocation> commericalLocations;
	/**
	 * 标志物
	 */
	private List<GeoLocation> landmarkLocations;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public List<GeoLocation> getDistricts() {
		return districts;
	}
	public void setDistricts(List<GeoLocation> districts) {
		this.districts = districts;
	}
	public List<GeoLocation> getCommericalLocations() {
		return commericalLocations;
	}
	public void setCommericalLocations(List<GeoLocation> commericalLocations) {
		this.commericalLocations = commericalLocations;
	}
	public List<GeoLocation> getLandmarkLocations() {
		return landmarkLocations;
	}
	public void setLandmarkLocations(List<GeoLocation> landmarkLocations) {
		this.landmarkLocations = landmarkLocations;
	}
	
	
}
