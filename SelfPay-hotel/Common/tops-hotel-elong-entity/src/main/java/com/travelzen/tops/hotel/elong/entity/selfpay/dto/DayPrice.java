package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

public class DayPrice {
	/**
	 * 星期第几天
	 */
	private String weekDay;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 早餐类别
	 */
	private String breakfast;
	/**
	 * 是否已满员
	 */
	private boolean isFull;
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(String breakfast) {
		this.breakfast = breakfast;
	}
	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	

}
