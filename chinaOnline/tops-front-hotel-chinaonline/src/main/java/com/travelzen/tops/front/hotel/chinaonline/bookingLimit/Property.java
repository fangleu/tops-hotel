package com.travelzen.tops.front.hotel.chinaonline.bookingLimit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Property")
@XmlAccessorType(XmlAccessType.FIELD)
public class Property {

	@XmlAttribute(name = "chainCode")
	private String chainCode ;
	
	@XmlAttribute(name = "hotelCode")
	private String hotelCode ;
	
	@XmlAttribute(name = "roomsAvailable")
	private String roomsAvailable ;
	
	@XmlAttribute(name = "startDate")
	private String startDate ;
	
	@XmlAttribute(name = "channelCode")
	private String channelCode ;
	
	 @XmlElement(name = "RoomTypeSegments")
	 public RoomTypeSegments roomTypeSegments;

	 
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

	public String getRoomsAvailable() {
		return roomsAvailable;
	}

	public void setRoomsAvailable(String roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public RoomTypeSegments getRoomTypeSegments() {
		return roomTypeSegments;
	}

	public void setRoomTypeSegments(RoomTypeSegments roomTypeSegments) {
		this.roomTypeSegments = roomTypeSegments;
	}
	
}
