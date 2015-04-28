package com.travelzen.tops.front.hotel.chinaonline.restriction;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AvailUpdateSegments")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailUpdateSegments {
	
	@XmlElement(name = "AvailUpdateSegment")
	 private List<AvailUpdateSegment> availUpdateSegment;

	public List<AvailUpdateSegment> getAvailUpdateSegment() {
		return availUpdateSegment;
	}

	public void setAvailUpdateSegment(List<AvailUpdateSegment> availUpdateSegment) {
		this.availUpdateSegment = availUpdateSegment;
	}


}
