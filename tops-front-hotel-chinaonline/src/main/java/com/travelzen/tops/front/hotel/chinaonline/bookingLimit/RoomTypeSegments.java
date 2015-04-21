package com.travelzen.tops.front.hotel.chinaonline.bookingLimit;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RoomTypeSegments")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoomTypeSegments{

	 @XmlElement(name = "RoomTypeSegment")
	 private List<RoomTypeSegment> roomTypeSegmentList;

	public List<RoomTypeSegment> getRoomTypeSegmentList() {
		return roomTypeSegmentList;
	}

	public void setRoomTypeSegmentList(List<RoomTypeSegment> roomTypeSegmentList) {
		this.roomTypeSegmentList = roomTypeSegmentList;
	}
	
}
