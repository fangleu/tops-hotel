package com.travelzen.tops.front.hotel.chinaonline.bookingLimit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class RoomTypeSegment {

	@XmlAttribute(name = "roomTypeCode")
	String roomTypeCode ;
	@XmlAttribute(name = "roomsAvailable")
	String roomsAvailable ;
	
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getRoomsAvailable() {
		return roomsAvailable;
	}
	public void setRoomsAvailable(String roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}
	
	
}
