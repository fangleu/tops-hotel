package com.travelzen.tops.elong.request.model;

import java.util.List;

public class SelfPaySearchCriteria {
	
	private int pageNo = 1; //
	private int pageSize; //
	private String cityIsoCode; //
	private String cityName; //
	private List<String> districtCodes; //
	private List<String> districtNames; //
	private List<String> commercialCodes; //
	private List<String> commercialNames; //
	private String checkInDate = "1"; //
	private String checkOutDate = "1"; //
	private String hotelName; //
	private List<String> hotelRatings; //
	private List<String> hotelCategoryIds; //
	private List<String> chainBrandIds; //
	private List<String> facilitys;
	private Double minPrice; //
	private Double maxPrice; //
	private String sortItem; //
	private String sortOrder; //
	private String customerKey;
	private String keywords;   //关键字
	private String hotelId;
	private List<String> hotelIds;
	// 订单参数
	private String roomNo;
	private String roomId;//不用于建单，用于关联价格计划
	private String roomCatId;
	private String bookingClassId;
	private List<String> conditions;// 查询条件（前台展示用）
	private List<String> validDates;
	private String roomType;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getCityIsoCode() {
		return cityIsoCode;
	}
	public void setCityIsoCode(String cityIsoCode) {
		this.cityIsoCode = cityIsoCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public List<String> getDistrictCodes() {
		return districtCodes;
	}
	public void setDistrictCodes(List<String> districtCodes) {
		this.districtCodes = districtCodes;
	}
	public List<String> getDistrictNames() {
		return districtNames;
	}
	public void setDistrictNames(List<String> districtNames) {
		this.districtNames = districtNames;
	}
	public List<String> getCommercialCodes() {
		return commercialCodes;
	}
	public void setCommercialCodes(List<String> commercialCodes) {
		this.commercialCodes = commercialCodes;
	}
	public List<String> getCommercialNames() {
		return commercialNames;
	}
	public void setCommercialNames(List<String> commercialNames) {
		this.commercialNames = commercialNames;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public List<String> getHotelRatings() {
		return hotelRatings;
	}
	public void setHotelRatings(List<String> hotelRatings) {
		this.hotelRatings = hotelRatings;
	}
	public List<String> getHotelCategoryIds() {
		return hotelCategoryIds;
	}
	public void setHotelCategoryIds(List<String> hotelCategoryIds) {
		this.hotelCategoryIds = hotelCategoryIds;
	}
	public List<String> getChainBrandIds() {
		return chainBrandIds;
	}
	public void setChainBrandIds(List<String> chainBrandIds) {
		this.chainBrandIds = chainBrandIds;
	}
	public List<String> getFacilitys() {
		return facilitys;
	}
	public void setFacilitys(List<String> facilitys) {
		this.facilitys = facilitys;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getSortItem() {
		return sortItem;
	}
	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getCustomerKey() {
		return customerKey;
	}
	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public List<String> getHotelIds() {
		return hotelIds;
	}
	public void setHotelIds(List<String> hotelIds) {
		this.hotelIds = hotelIds;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomCatId() {
		return roomCatId;
	}
	public void setRoomCatId(String roomCatId) {
		this.roomCatId = roomCatId;
	}
	public String getBookingClassId() {
		return bookingClassId;
	}
	public void setBookingClassId(String bookingClassId) {
		this.bookingClassId = bookingClassId;
	}
	public List<String> getConditions() {
		return conditions;
	}
	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}
	public List<String> getValidDates() {
		return validDates;
	}
	public void setValidDates(List<String> validDates) {
		this.validDates = validDates;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
}
