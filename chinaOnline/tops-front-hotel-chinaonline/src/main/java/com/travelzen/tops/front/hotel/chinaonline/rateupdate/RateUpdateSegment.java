package com.travelzen.tops.front.hotel.chinaonline.rateupdate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RateUpdateSegment")
@XmlAccessorType(XmlAccessType.FIELD)
public class RateUpdateSegment {
	
	@XmlAttribute(name = "currencyCode")
	private String currencyCode;
	
	@XmlAttribute(name = "rateCode")
	private String rateCode;
	
	@XmlAttribute(name = "roomTypeCode")
	private String roomTypeCode;
	
	@XmlElement(name = "TargetGDS")
	private String targetGDS;
	
	@XmlElement(name = "RateAmounts")
	private RateAmounts rateAmounts;
	
	@XmlElement(name = "DOW")
	private Dow dow;
	
	@XmlElement(name = "TimeSpan")
	private TimeSpan timeSpan;


	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public Dow getDow() {
		return dow;
	}

	public void setDow(Dow dow) {
		this.dow = dow;
	}

	public RateAmounts getRateAmounts() {
		return rateAmounts;
	}

	public void setRateAmounts(RateAmounts rateAmounts) {
		this.rateAmounts = rateAmounts;
	}

	public TimeSpan getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(TimeSpan timeSpan) {
		this.timeSpan = timeSpan;
	}

	public String getTargetGDS() {
		return targetGDS;
	}

	public void setTargetGDS(String targetGDS) {
		this.targetGDS = targetGDS;
	}

}
