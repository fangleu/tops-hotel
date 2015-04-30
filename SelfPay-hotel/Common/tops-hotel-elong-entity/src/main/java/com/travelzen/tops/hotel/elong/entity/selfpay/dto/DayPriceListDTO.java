package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.util.List;

public class DayPriceListDTO {
	/**
	 * 价格列表
	 */
	private List<DayPrice> dayPriceList;
	/**
	 * 价格列表规则描述
	 */
	private String priceRuleDesc;
	public List<DayPrice> getDayPriceList() {
		return dayPriceList;
	}
	public void setDayPriceList(List<DayPrice> dayPriceList) {
		this.dayPriceList = dayPriceList;
	}
	public String getPriceRuleDesc() {
		return priceRuleDesc;
	}
	public void setPriceRuleDesc(String priceRuleDesc) {
		this.priceRuleDesc = priceRuleDesc;
	}
}
