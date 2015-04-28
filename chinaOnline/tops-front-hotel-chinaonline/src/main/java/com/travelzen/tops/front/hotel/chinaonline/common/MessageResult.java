package com.travelzen.tops.front.hotel.chinaonline.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageResult {

	String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
