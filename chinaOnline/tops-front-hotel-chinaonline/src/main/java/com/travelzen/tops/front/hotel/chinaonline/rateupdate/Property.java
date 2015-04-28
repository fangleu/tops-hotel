package com.travelzen.tops.front.hotel.chinaonline.rateupdate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Property")
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

	@XmlAttribute(name = "chainCode")
	private String chinaCode;
	
	@XmlAttribute(name = "hotelCode")
	private String hotelCode;
	
	@XmlElement(name = "RateUpdateSegments")
	 public RateUpdateSegments rateUpdateSegments;

	public String getChinaCode() {
		return chinaCode;
	}

	public void setChinaCode(String chinaCode) {
		this.chinaCode = chinaCode;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public RateUpdateSegments getRateUpdateSegments() {
		return rateUpdateSegments;
	}

	public void setRateUpdateSegments(RateUpdateSegments rateUpdateSegments) {
		this.rateUpdateSegments = rateUpdateSegments;
	}
	
}
