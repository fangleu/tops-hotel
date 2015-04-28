package com.travelzen.tops.hotel.chinaonline.bean;

public class CancelOrderRequest {
	
	private String hotelConfirmCode;
	
	private String hotelCode;
	
	private String guestName;
	
	//备注
	private String comments;


	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getHotelConfirmCode() {
		return hotelConfirmCode;
	}

	public void setHotelConfirmCode(String hotelConfirmCode) {
		this.hotelConfirmCode = hotelConfirmCode;
	}
	
	

}
