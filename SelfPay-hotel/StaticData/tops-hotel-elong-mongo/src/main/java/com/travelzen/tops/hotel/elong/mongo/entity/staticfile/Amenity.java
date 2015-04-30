package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;

@Entity("elong.Amenity")
public class Amenity extends AbstractMorphiaEntity {
	/**
	 * elong系统设施ID
	 */
	@Indexed
	private int amenityId;

	/**
	 * 字段意思不明
	 */
	private int parentID;
	/**
	 * 设施中文名称
	 */
	private String name;
	
	/**
	 * 设施英文名称
	 */
	private String NameEn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return NameEn;
	}

	public void setNameEn(String nameEn) {
		NameEn = nameEn;
	}
	
	public int getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(int amenityId) {
		this.amenityId = amenityId;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
	
}
