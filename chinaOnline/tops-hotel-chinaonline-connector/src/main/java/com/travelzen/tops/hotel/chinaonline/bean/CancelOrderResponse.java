package com.travelzen.tops.hotel.chinaonline.bean;

public class CancelOrderResponse {

	private boolean result;
	
	private String errorText;

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
