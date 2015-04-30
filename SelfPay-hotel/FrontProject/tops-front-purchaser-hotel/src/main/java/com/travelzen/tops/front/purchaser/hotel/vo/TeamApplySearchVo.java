package com.travelzen.tops.front.purchaser.hotel.vo;

/**
 * Created with IntelliJ IDEA.
 * User: lyy
 * Date: 13-9-16
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public class TeamApplySearchVo {
 	private String cityName;
	private String cityCode;
	private String fromDate;
	private String leaveDate;
	private String roomNumber1;
	private String roomNumber2;
	private String hotelLevel;
	private String hotelBrand;
	private String fromPrice;
	private String toPrice;
	private String price;
	private String remark;
	private String clientName;
	private String clientPhone;

	public String getCityName() {
		return cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public String getRoomNumber1() {
		return roomNumber1;
	}

	public String getRoomNumber2() {
		return roomNumber2;
	}

	public String getHotelLevel() {
		return hotelLevel;
	}

	public String getHotelBrand() {
		return hotelBrand;
	}

	public String getFromPrice() {
		return fromPrice;
	}

	public String getToPrice() {
		return toPrice;
	}

	public String getPrice() {
		return price;
	}

	public String getRemark() {
		return remark;
	}

	public String getClientName() {
		return clientName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public void setRoomNumber1(String roomNumber1) {
		this.roomNumber1 = roomNumber1;
	}

	public void setRoomNumber2(String roomNumber2) {
		this.roomNumber2 = roomNumber2;
	}

	public void setHotelLevel(String hotelLevel) {
		this.hotelLevel = hotelLevel;
	}

	public void setHotelBrand(String hotelBrand) {
		this.hotelBrand = hotelBrand;
	}

	public void setFromPrice(String fromPrice) {
		this.fromPrice = fromPrice;
	}

	public void setToPrice(String toPrice) {
		this.toPrice = toPrice;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	@Override
	public String toString() {
		return "TeamApplySearchVo{" +
				"cityName='" + cityName + '\'' +
				", cityCode='" + cityCode + '\'' +
				", fromDate='" + fromDate + '\'' +
				", leaveDate='" + leaveDate + '\'' +
				", roomNumber1='" + roomNumber1 + '\'' +
				", roomNumber2='" + roomNumber2 + '\'' +
				", hotelLevel='" + hotelLevel + '\'' +
				", hotelBrand='" + hotelBrand + '\'' +
				", fromPrice='" + fromPrice + '\'' +
				", toPrice='" + toPrice + '\'' +
				", price='" + price + '\'' +
				", remark='" + remark + '\'' +
				", clientName='" + clientName + '\'' +
				", clientPhone='" + clientPhone + '\'' +
				'}';
	}
}
