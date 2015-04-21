package com.travelzen.tops.front.hotel.chinaonline.rateupdate;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RateUpdateSegments")
@XmlAccessorType(XmlAccessType.FIELD)
public class RateUpdateSegments {

	@XmlElement(name = "RateUpdateSegment")
	 public List<RateUpdateSegment> rateUpdateSegmentList;

	public List<RateUpdateSegment> getRateUpdateSegmentList() {
		return rateUpdateSegmentList;
	}

	public void setRateUpdateSegmentList(
			List<RateUpdateSegment> rateUpdateSegmentList) {
		this.rateUpdateSegmentList = rateUpdateSegmentList;
	}
	
}
