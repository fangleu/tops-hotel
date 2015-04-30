package com.travelzen.tops.hotel.elong.entity.selfpay.dto;

import java.io.Serializable;
import java.util.List;
public class RoomImageDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3967215113706714953L;

	/**
	 * 床型
	 */
	private String bedType;
	/**
	 * 房间最大入住人数
	 */
	private String capacity;
	/**
	 * 房间面积
	 */
	private String roomArea;
	
	/**
	 * 房间层数
	 */
	private String floor;
	
	/**
	 * 其他信息
	 */
	private String others;
	/**
	 * 图片列表
	 */
	private List<String> imageSrcList;
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getRoomArea() {
		return roomArea;
	}
	public void setRoomArea(String roomArea) {
		this.roomArea = roomArea;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public List<String> getImageSrcList() {
		return imageSrcList;
	}
	public void setImageSrcList(List<String> imageSrcList) {
		this.imageSrcList = imageSrcList;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
}
