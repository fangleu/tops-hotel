package com.travelzen.tops.front.hotel.chinaonline.rateupdate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DOW")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dow {
	
	@XmlAttribute(name = "Fri")
	private String fri;
	
	@XmlAttribute(name = "Mon")
	private String mon;
	
	@XmlAttribute(name = "Sat")
	private String sat;
	
	@XmlAttribute(name = "Sun")
	private String sun;
	
	@XmlAttribute(name = "Thu")
	private String thu;
	
	@XmlAttribute(name = "Tue")
	private String tue;
	
	@XmlAttribute(name = "Wed")
	private String wed;

	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}

	public String getWed() {
		return wed;
	}

	public void setWed(String wed) {
		this.wed = wed;
	}
	

}
