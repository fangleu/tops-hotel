package com.travelzen.tops.front.hotel.chinaonline.restriction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AvailUpdateSegment")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailUpdateSegment {

	@XmlAttribute(name = "roomTypeCode")
	private String roomTypeCode;
	
	@XmlAttribute(name = "rateCode")
	private String rateCode;
	
	@XmlAttribute(name = "actionCode")
	private String actionCode;
	
	@XmlElement(name = "TargetGDS")
	private String targetGDS;
	
	@XmlElement(name = "TimeSpan")
	 private TimeSpan timeSpan;

	public String getRoomTypeCode() {
		return roomTypeCode;
	}

	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getTargetGDS() {
		return targetGDS;
	}

	public void setTargetGDS(String targetGDS) {
		this.targetGDS = targetGDS;
	}

	public TimeSpan getTimeSpan() {
		return timeSpan;
	}

	public void setTimeSpan(TimeSpan timeSpan) {
		this.timeSpan = timeSpan;
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	
	
	
}
