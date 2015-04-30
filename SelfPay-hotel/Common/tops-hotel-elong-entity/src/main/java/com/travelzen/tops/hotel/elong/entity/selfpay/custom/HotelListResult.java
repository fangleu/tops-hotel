package com.travelzen.tops.hotel.elong.entity.selfpay.custom;

import com.alibaba.fastjson.annotation.JSONField;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelList;

public class HotelListResult {

	@JSONField(name = "Code")
	private String code;
	
	@JSONField(name = "Result")
	private HotelList result;

	public HotelList getResult() {
		return result;
	}

	public void setResult(HotelList result) {
		this.result = result;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
