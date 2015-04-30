package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import java.util.List;

import com.github.jmkgreen.morphia.annotations.Embedded;

@Embedded
public class Image {
	/**
	 * 关联的房型
	 */
	private String roomId;
	
	/**
	 * 图片类型
	 */
	private String type;
	
	/**
	 * 图片地址
	 * 
	 */
	@Embedded
	private List<ImageLocation> locations;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ImageLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<ImageLocation> locations) {
		this.locations = locations;
	}
	
}
