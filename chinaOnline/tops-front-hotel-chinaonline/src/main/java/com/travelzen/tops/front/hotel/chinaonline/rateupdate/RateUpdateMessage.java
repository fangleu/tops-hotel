package com.travelzen.tops.front.hotel.chinaonline.rateupdate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "GDSPublisherRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class RateUpdateMessage {

	@XmlAttribute(name = "Type")
	private String type ;
	
	@XmlAttribute(name = "Password")
	private String password ;
	
	@XmlAttribute(name = "UserID")
	private String userID;
	
	@XmlAttribute(name = "Profile")
	private String profile;
	
	@XmlAttribute(name = "Destination")
	private String destination;
	
	@XmlAttribute(name = "RateNotificationType")
	private String rateNotificationType;
	
	@XmlElement(name = "FIDELIO_RateUpdateNotifRQ")
	 public FIDELIO_RateUpdateNotifRQ rateUpdateNotifRQ;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getRateNotificationType() {
		return rateNotificationType;
	}

	public void setRateNotificationType(String rateNotificationType) {
		this.rateNotificationType = rateNotificationType;
	}

	public FIDELIO_RateUpdateNotifRQ getRateUpdateNotifRQ() {
		return rateUpdateNotifRQ;
	}

	public void setRateUpdateNotifRQ(FIDELIO_RateUpdateNotifRQ rateUpdateNotifRQ) {
		this.rateUpdateNotifRQ = rateUpdateNotifRQ;
	}
	
	
}
