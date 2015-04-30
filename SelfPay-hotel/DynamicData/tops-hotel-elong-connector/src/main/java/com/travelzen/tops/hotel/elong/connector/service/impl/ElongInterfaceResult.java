package com.travelzen.tops.hotel.elong.connector.service.impl;

public class ElongInterfaceResult {
	
	private byte[] result = null;

	private boolean success;

	public byte[] getResult() {
		return result;
	}
	
	public void setResult(byte[] result) {
		this.result = result;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
