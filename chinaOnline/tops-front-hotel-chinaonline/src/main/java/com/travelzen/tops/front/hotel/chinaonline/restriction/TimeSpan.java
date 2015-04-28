package com.travelzen.tops.front.hotel.chinaonline.restriction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TimeSpan")
@XmlAccessorType(XmlAccessType.FIELD)
public class TimeSpan {
	
	@XmlAttribute(name = "start")
	private String start;
	
	@XmlAttribute(name = "end")
	private String end;
	
	@XmlAttribute(name = "duration")
	private String duration;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
