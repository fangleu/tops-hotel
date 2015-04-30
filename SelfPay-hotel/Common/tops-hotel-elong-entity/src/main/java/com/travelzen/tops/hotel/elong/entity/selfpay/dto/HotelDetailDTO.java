package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.util.List;

public class HotelDetailDTO{
	/**
	 * 酒店ID
	 */
	private String hotelId;
	/**
	 * 酒店名字
	 */
	private String hotelName;
	/**
	 * 默认图片
	 */
	private String thumbNailUrl;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 *  酒店简介
	 */
	private String introEditor;
	/**
	 *  周围交通
	 */
	private List<String> traffic;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 开业时间
	 */
	private String openDate;
	/**
	 * 装修时间
	 */
	private String renovationDate;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 星级
	 */
	private int starRate;
	/**
	 * travelzen推荐星级
	 */
	private int tzRate;
	/**
	 * 信用卡
	 */
	private List<String> creditCards;
	/**
	 * 酒店设施
	 */
	private String[] facilityType;
	/**
	 * 酒店设施名
	 */
	private String[] facilityName;
	/**
	 * 挂牌星级描述
	 */
	private String starDetail;
	/**
	 * 无线
	 */
	private String freeWifi;
	/**
	 * 宽带
	 */
	private String broadband;
	/**
	 * 停车场
	 */
	private String park;
	/**
	 *一般设施
	 */
	private List<String> generalAmenities;
	/**
	 * 会议设施
	 */
	private List<String> conferenceAmenities;
	/**
	 * 餐饮设施
	 */
	private List<String> diningAmenities;
	/**
	 * 娱乐设施
	 */
	private List<String> recreationAmenities;
	
	/**
	 * 图片列表
	 */
	private List<String> thumbNailUrlList;
	/**
	 * 图片描述列表
	 */
	private List<String> thumbNailInfoList;
	/**
	 * 房型
	 */
	private List<RoomInfo> roomList;
	/**
	 * 最低价格
	 */
	private double lowRate;
	/**
	 * 图片数量
	 */
	private int numberOfPicture;
	
	private String HelpfulTips;
	/**
	 * 酒店所在的城市
	 */
	private String hotelCity;
	
	public String getHotelCity() {
		return hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getIntroEditor() {
		return introEditor;
	}

	public void setIntroEditor(String introEditor) {
		this.introEditor = introEditor;
	}

	public List<String> getTraffic() {
		return traffic;
	}

	public void setTraffic(List<String> traffic) {
		this.traffic = traffic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStarRate() {
		return starRate;
	}

	public void setStarRate(int starRate) {
		this.starRate = starRate;
	}

	public List<String> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<String> creditCards) {
		this.creditCards = creditCards;
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

	public String getStarDetail() {
		return starDetail;
	}

	public void setStarDetail(String starDetail) {
		this.starDetail = starDetail;
	}

	public String getFreeWifi() {
		return freeWifi;
	}

	public void setFreeWifi(String freeWifi) {
		this.freeWifi = freeWifi;
	}

	public String getBroadband() {
		return broadband;
	}

	public void setBroadband(String broadband) {
		this.broadband = broadband;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public List<String> getGeneralAmenities() {
		return generalAmenities;
	}

	public void setGeneralAmenities(List<String> generalAmenities) {
		this.generalAmenities = generalAmenities;
	}

	public List<String> getConferenceAmenities() {
		return conferenceAmenities;
	}

	public void setConferenceAmenities(List<String> conferenceAmenities) {
		this.conferenceAmenities = conferenceAmenities;
	}

	public List<String> getDiningAmenities() {
		return diningAmenities;
	}

	public void setDiningAmenities(List<String> diningAmenities) {
		this.diningAmenities = diningAmenities;
	}

	public List<String> getRecreationAmenities() {
		return recreationAmenities;
	}

	public void setRecreationAmenities(List<String> recreationAmenities) {
		this.recreationAmenities = recreationAmenities;
	}

	public List<RoomInfo> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<RoomInfo> roomList) {
		this.roomList = roomList;
	}

	public double getLowRate() {
		return lowRate;
	}

	public void setLowRate(double lowRate) {
		this.lowRate = lowRate;
	}

	public int getNumberOfPicture() {
		return numberOfPicture;
	}

	public void setNumberOfPicture(int numberOfPicture) {
		this.numberOfPicture = numberOfPicture;
	}

	public List<String> getThumbNailUrlList() {
		return thumbNailUrlList;
	}

	public void setThumbNailUrlList(List<String> thumbNailUrlList) {
		this.thumbNailUrlList = thumbNailUrlList;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
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

	public List<String> getThumbNailInfoList() {
		return thumbNailInfoList;
	}

	public void setThumbNailInfoList(List<String> thumbNailInfoList) {
		this.thumbNailInfoList = thumbNailInfoList;
	}

	public String getThumbNailUrl() {
		return thumbNailUrl;
	}

	public void setThumbNailUrl(String thumbNailUrl) {
		this.thumbNailUrl = thumbNailUrl;
	}

	public String getRenovationDate() {
		return renovationDate;
	}

	public void setRenovationDate(String renovationDate) {
		this.renovationDate = renovationDate;
	}

	public String getHelpfulTips() {
		return HelpfulTips;
	}

	public void setHelpfulTips(String helpfulTips) {
		HelpfulTips = helpfulTips;
	}

	
}