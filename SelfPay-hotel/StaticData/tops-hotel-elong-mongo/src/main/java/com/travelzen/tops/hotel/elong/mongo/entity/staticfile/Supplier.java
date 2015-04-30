package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Embedded;

@Embedded
public class Supplier {
	/**
	 * 星期开始设置
	 * 
	 * 用于房价的周末价计算。为0表示周末设置从周一开始
	 */
	private int weekendStart;
	
	/**
	 * 星期结束设置
	 * 
	 * 为0表示到周日结束，但是两个都为0表示无周末设置； 
	 * 如果开始为3，结束为1，表示从周三到下周1都是周末设置
	 * 1代表周一，7代表周日
	 */
	private int weekendEnd;
	/**
	 * 即时确认的销售房型
	 * 
	 * 多个房型以逗号分隔
	 * 订单是否即时还受订单使用库存的影响
	 */
	private String instantRoomTypes;
	/**
	 * 有效状态
	 */
	private boolean status;
	/**
	 * 特殊政策
	 */
	private String availPolicy;
	/**
	 * AvailPolicy开始日期
	 */
	private String availStartDate;
	/**
	 * AvailPolicy结束日期
	 */
	private String availEndDate;
	/**
	 * 温馨提示
	 */
	private String helpfulTips;
	/**
	 * HelpfulTips开始日期
	 */
	private String tipsStartDate;
	/**
	 * HelpfulTips结束日期
	 */
	private String tipsEndDate;
	
	public int getWeekendStart() {
		return weekendStart;
	}
	public void setWeekendStart(int weekendStart) {
		this.weekendStart = weekendStart;
	}
	public int getWeekendEnd() {
		return weekendEnd;
	}
	public void setWeekendEnd(int weekendEnd) {
		this.weekendEnd = weekendEnd;
	}
	public String getInstantRoomTypes() {
		return instantRoomTypes;
	}
	public void setInstantRoomTypes(String instantRoomTypes) {
		this.instantRoomTypes = instantRoomTypes;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getAvailPolicy() {
		return availPolicy;
	}
	public void setAvailPolicy(String availPolicy) {
		this.availPolicy = availPolicy;
	}
	public String getHelpfulTips() {
		return helpfulTips;
	}
	public void setHelpfulTips(String helpfulTips) {
		this.helpfulTips = helpfulTips;
	}
	public String getAvailStartDate() {
		return availStartDate;
	}
	public void setAvailStartDate(String availStartDate) {
		this.availStartDate = availStartDate;
	}
	public String getAvailEndDate() {
		return availEndDate;
	}
	public void setAvailEndDate(String availEndDate) {
		this.availEndDate = availEndDate;
	}
	public String getTipsStartDate() {
		return tipsStartDate;
	}
	public void setTipsStartDate(String tipsStartDate) {
		this.tipsStartDate = tipsStartDate;
	}
	public String getTipsEndDate() {
		return tipsEndDate;
	}
	public void setTipsEndDate(String tipsEndDate) {
		this.tipsEndDate = tipsEndDate;
	}
}
