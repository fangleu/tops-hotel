package com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;

@Entity
public class HotCity extends AbstractMorphiaEntity {
	/**
	 * 标签页id
	 */
	private String tabId;
	
	/**
	 * 保存的elong提供的城市id的json数据
	 */
	private String result;

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
