package com.travelzen.tops.front.hotel.chinaonline.rateupdate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RateAmount {
	
	@XmlAttribute
	private String rateAmountType;
	
	@XmlValue
	private String amountType;
	
	public String getRateAmountType() {
		return rateAmountType;
	}

	public void setRateAmountType(String rateAmountType) {
		this.rateAmountType = rateAmountType;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}



}
