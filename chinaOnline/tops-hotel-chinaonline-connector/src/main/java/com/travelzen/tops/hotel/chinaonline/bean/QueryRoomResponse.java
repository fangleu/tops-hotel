package com.travelzen.tops.hotel.chinaonline.bean;

import java.util.Map;

import org.joda.time.DateTime;


public class QueryRoomResponse {

	private Map<DateTime,Double> priceList;

	public Map<DateTime, Double> getPriceList() {
		return priceList;
	}

	public void setPriceList(Map<DateTime, Double> priceList) {
		this.priceList = priceList;
	}

	

	
}
