package com.travelzen.tops.hotel.elong.mongo.entity.statistic;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;

@Entity("elong.UpdateHotel")
public class UpdateHotel extends AbstractMorphiaEntity {
	/**
	 * 酒店ID，不可为空
	 */
	private String hotelId;
	/**
	 * 更新类型
	 */
	private String updateType;
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
}
