package com.travelzen.tops.front.hotel.chinaonline.restriction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Property")
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {
	
	@XmlAttribute(name = "chainCode")
	private String chainCode;
	
	@XmlAttribute(name = "hotelCode")
	private String hotelCode;
	
	@XmlElement(name = "AvailUpdateSegments")
	 private AvailUpdateSegments availUpdateSegments;

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public AvailUpdateSegments getAvailUpdateSegments() {
		return availUpdateSegments;
	}

	public void setAvailUpdateSegments(AvailUpdateSegments availUpdateSegments) {
		this.availUpdateSegments = availUpdateSegments;
	} 
	

}
