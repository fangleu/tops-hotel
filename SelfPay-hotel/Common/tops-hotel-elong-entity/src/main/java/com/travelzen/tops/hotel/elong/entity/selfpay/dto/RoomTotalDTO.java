package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.util.List;

public class RoomTotalDTO {
	
	private List<RoomInfo> roomInfoList;
	
	/**
	 * 房间价格
	 */
	private int lowRate;

	public List<RoomInfo> getRoomInfoList() {
		return roomInfoList;
	}

	public void setRoomInfoList(List<RoomInfo> roomInfoList) {
		this.roomInfoList = roomInfoList;
	}

	public int getLowRate() {
		return lowRate;
	}

	public void setLowRate(int lowRate) {
		this.lowRate = lowRate;
	}
}
