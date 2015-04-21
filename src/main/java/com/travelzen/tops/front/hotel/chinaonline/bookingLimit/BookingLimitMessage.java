package com.travelzen.tops.front.hotel.chinaonline.bookingLimit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "GDSPublisherRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookingLimitMessage {
	
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
	
	@XmlElement(name = "FIDELIO_AvailabilityStatusRQ")
	 public FIDELIO_AvailabilityStatusRQ AvailabilityStatusRQ;
	 
	 @XmlElement(name = "user_agent")
	 private String user_agent ;

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

	public String getUser_agent() {
		return user_agent;
	}

	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}

	@Override
	public String toString() {
		return "BookingLimitMessage [type=" + type + ", password="
				+ password + ", userID=" + userID
				+ ", profile=" + profile + ", destination="
				+ destination + ", user_agent=" + user_agent
				+ "]";
	}

	public FIDELIO_AvailabilityStatusRQ getAvailabilityStatusRQ() {
		return AvailabilityStatusRQ;
	}

	public void setAvailabilityStatusRQ(
			FIDELIO_AvailabilityStatusRQ availabilityStatusRQ) {
		AvailabilityStatusRQ = availabilityStatusRQ;
	}


	
	
	
	
	
}
