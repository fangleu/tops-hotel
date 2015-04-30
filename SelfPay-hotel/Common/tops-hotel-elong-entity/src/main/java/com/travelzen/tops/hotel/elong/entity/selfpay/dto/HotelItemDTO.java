package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.io.Serializable;
import java.util.List;

public class HotelItemDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 855176086215574454L;
	/**
	 * 酒店名称
	 */
	private String hotelId;
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
	 * 最低价格
	 */
	private double lowRate;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 酒店介绍
	 */
	private String hotelDesc;
	/**
	 * 封面图片
	 */
	private String thumbNailUrl;
	
	/**
	 * 酒店设施
	 */
	private String[] facilityType;
	/**
	 * 酒店设施名
	 */
	private String[] facilityName;
	/**
	 * 图片数目
	 */
//	private int imageCount;
	
	private List<RoomInfo> roomList;
	
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
	public double getLowRate() {
		return lowRate;
	}
	public void setLowRate(double lowRate) {
		this.lowRate = lowRate;
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
	public String getThumbNailUrl() {
		return thumbNailUrl;
	}
	public void setThumbNailUrl(String thumbNailUrl) {
		this.thumbNailUrl = thumbNailUrl;
	}
	
	public List<RoomInfo> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomInfo> roomList) {
		this.roomList = roomList;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getStarDetail() {
		return starDetail;
	}
	public void setStarDetail(String starDetail) {
		this.starDetail = starDetail;
	}
	
	public String[] getFacilityType() {
		return facilityType;
	}
	public void setFacilityType(String[] facilityType) {
		this.facilityType = facilityType;
	}
	public String[] getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String[] facilityName) {
		this.facilityName = facilityName;
	}
//	public int getImageCount() {
//		return imageCount;
//	}
//	public void setImageCount(int imageCount) {
//		this.imageCount = imageCount;
//	}
	public int getTzRate() {
		return tzRate;
	}
	public void setTzRate(int tzRate) {
		this.tzRate = tzRate;
	}
	public String getHotelDesc() {
		return hotelDesc;
	}
	public void setHotelDesc(String hotelDesc) {
		this.hotelDesc = hotelDesc;
	}
	
}
