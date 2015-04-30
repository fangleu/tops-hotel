package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Entity;

@Entity("elong.Brand")
public class Brand {

	/**
	 * 品牌编号，不可为空
	 */
	private int brandId;
	/**
	 * 集团编号，不可为空
	 */
	private int groupId;
	/**
	 * 品牌简称，不可为空
	 */
	private String shortName;

	/**
	 * 品牌全称，不可为空
	 */
	private String name;
	/**
	 * 品牌首字母
	 */
	private String letters;
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLetters() {
		return letters;
	}
	public void setLetters(String letters) {
		this.letters = letters;
	}
	
}
