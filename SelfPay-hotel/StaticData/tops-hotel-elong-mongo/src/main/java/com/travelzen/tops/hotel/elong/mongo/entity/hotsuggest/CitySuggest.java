package com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;

@Entity
public class CitySuggest extends AbstractMorphiaEntity {
	/**
	 * 城市编号
	 */
	private String cityId;
	
	/**
	 * 保存的elong提供的城市关键字的json数据
	 */
	private String keywords;
	
	/**
	 * 保存的elong提供的城市交通信息的json数据
	 */
	private String result;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
