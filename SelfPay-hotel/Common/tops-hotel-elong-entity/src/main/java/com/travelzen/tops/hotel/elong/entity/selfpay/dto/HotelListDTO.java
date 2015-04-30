package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.util.List;

public class HotelListDTO {
	/**
	 * 酒店列表
	 */
	private List<HotelItemDTO> hotelItemList;
	/**
	 * 酒店结果数
	 */
	private int counts;

	public List<HotelItemDTO> getHotelItemList() {
		return hotelItemList;
	}

	public void setHotelItemList(List<HotelItemDTO> hotelItemList) {
		this.hotelItemList = hotelItemList;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}
}
