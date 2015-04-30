package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.io.Serializable;
import java.util.List;

public class RoomInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -653203862755116979L;

	/**
	 * 房间名称
	 */
	private String roomName;
	
	/**
	 * 房型id
	 */
	private String roomId;
	
	/**
	 * 子房间类型
	 */
	private List<RatePlanItem> ratePlanList;
	
	/**
	 * 房间图片信息
	 */
	private RoomImageDTO roomImageDto;
	
	/**
	 * 房型面积
	 */
	private String area;
	
	/**
	 * 房型楼层
	 */
	private String floor;
	
	/**
	 * 房型备注
	 */
	private String comments;
	/**
	 * 房量预警
	 */
	private String roomNum;
	
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<RatePlanItem> getRatePlanList() {
		return ratePlanList;
	}

	public void setRatePlanList(List<RatePlanItem> ratePlanList) {
		this.ratePlanList = ratePlanList;
	}

	public RoomImageDTO getRoomImageDto() {
		return roomImageDto;
	}

	public void setRoomImageDto(RoomImageDTO roomImageDto) {
		this.roomImageDto = roomImageDto;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
