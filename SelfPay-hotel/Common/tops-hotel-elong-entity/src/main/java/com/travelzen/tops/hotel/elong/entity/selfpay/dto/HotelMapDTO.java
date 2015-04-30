package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

public class HotelMapDTO {
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 经度
	 */
	private String longitude;
	
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getTzRate() {
		return tzRate;
	}
	public void setTzRate(int tzRate) {
		this.tzRate = tzRate;
	}
}
