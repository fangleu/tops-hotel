package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Entity;

@Entity("elong.GeoLocation")
public class GeoLocation {
	/**
	 * 位置编号
	 * 
	 */
	private String locationId;
	/**
	 * 位置名称
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
}
