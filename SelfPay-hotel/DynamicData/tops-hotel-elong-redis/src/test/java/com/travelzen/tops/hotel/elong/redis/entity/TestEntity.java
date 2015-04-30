package com.travelzen.tops.hotel.elong.redis.entity;

import java.io.Serializable;

public class TestEntity implements Serializable {
	/**
	 * @author muyuansun
	 * @date 2014-9-19 下午1:49:29
	 */
	private static final long serialVersionUID = -2977467664978776953L;
	
	public String hotelListCacheResult = null;

	public String getHotelListCacheResult() {
		return hotelListCacheResult;
	}

	public void setHotelListCacheResult(String hotelListCacheResult) {
		this.hotelListCacheResult = hotelListCacheResult;
	}
	
}
