package com.travelzen.tops.hotel.chinaonline.bean;


public class CreateOrderResponse {
	
	private String hotelConfirmCode;//
	
	private boolean result;
	
	private String errorText;


	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getHotelConfirmCode() {
		return hotelConfirmCode;
	}

	public void setHotelConfirmCode(String hotelConfirmCode) {
		this.hotelConfirmCode = hotelConfirmCode;
	}


}
