package com.travelzen.tops.hotel.chinaonline.bean;

import java.util.List;

import org.joda.time.DateTime;

import com.travelzen.tops.hotel.chinaonline.bean.base.GuestInformation;
import com.travelzen.tops.hotel.chinaonline.bean.base.Rates;

public class CreateOrderRequest {
	
	private String orderId;
	
	private String hotelCode;

	private String roomCode;
	
	private String rateCode;
	
	private DateTime startDate;
	
	private DateTime endDate;
	
	private List<Rates> rate;   // 每日房费
	
	private int roomNumber;   // 预订间数
	
	private List<GuestInformation> guestList;
	
	private String comments;   //备注   选填
	
	private String EarliestCheckInTime;     // 最早到店时间14:00
	
	private String LastestCheckInTime;       //最晚到店时间14:00

	public String getHotelCode() {
		return hotelCode;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}


	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	

	public List<Rates> getRate() {
		return rate;
	}

	public void setRate(List<Rates> rate) {
		this.rate = rate;
	}


	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getEarliestCheckInTime() {
		return EarliestCheckInTime;
	}

	public void setEarliestCheckInTime(String earliestCheckInTime) {
		EarliestCheckInTime = earliestCheckInTime;
	}

	public String getLastestCheckInTime() {
		return LastestCheckInTime;
	}

	public void setLastestCheckInTime(String lastestCheckInTime) {
		LastestCheckInTime = lastestCheckInTime;
	}

	public List<GuestInformation> getGuestList() {
		return guestList;
	}

	public void setGuestList(List<GuestInformation> guestList) {
		this.guestList = guestList;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
