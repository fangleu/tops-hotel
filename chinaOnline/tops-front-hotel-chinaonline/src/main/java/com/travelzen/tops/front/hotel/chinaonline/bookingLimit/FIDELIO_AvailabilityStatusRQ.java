package com.travelzen.tops.front.hotel.chinaonline.bookingLimit;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FIDELIO_AvailabilityStatusRQ")
@XmlAccessorType(XmlAccessType.FIELD)
public class FIDELIO_AvailabilityStatusRQ {

	@XmlAttribute(name = "echoToken")
	private String echoToken ;
	
	@XmlAttribute(name = "timeStamp")
	private String timeStamp ;
	
	@XmlAttribute(name = "version")
	private String version ;
	
	@XmlElement(name = "Properties")
	 public Properties properties;

	public String getEchoToken() {
		return echoToken;
	}

	public void setEchoToken(String echoToken) {
		this.echoToken = echoToken;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
