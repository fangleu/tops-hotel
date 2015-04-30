package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.util.List;
public class HotelPictureDTO {

	/**
	 * 酒店名称
	 */
	private String hotelName;
	/**
	 * 挂牌星级
	 */
	private int starRate;
	/**
	 * travelzen推荐星级
	 */
	private int tzRate;
	/**
	 * 挂牌星级描述
	 */
	private String starDetail;
	
	/**
	 * 地址
	 */
	private List<String> thumbNailUrl;
	private String address;
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public int getStarRate() {
		return starRate;
	}
	public void setStarRate(int starRate) {
		this.starRate = starRate;
	}
	public String getStarDetail() {
		return starDetail;
	}
	public void setStarDetail(String starDetail) {
		this.starDetail = starDetail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getThumbNailUrl() {
		return thumbNailUrl;
	}
	public void setThumbNailUrl(List<String> thumbNailUrl) {
		this.thumbNailUrl = thumbNailUrl;
	}
	public int getTzRate() {
		return tzRate;
	}
	public void setTzRate(int tzRate) {
		this.tzRate = tzRate;
	}
	
}