package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Embedded;

@Embedded
public class Room {
	/**
	 * 房型编号,不能为空
	 */
	private String id;
	/**
	 * 名称,不能为空
	 */
	private String name;
	/**
	 * 面积
	 */
	private String area;
	/**
	 * 楼层
	 */
	private String floor;
	/**
	 * 是否有宽带，0表示无宽带，1 表示有宽带
	 */
	private int broadnetAccess;
	/**
	 * 宽带是否收费，0表示免费，1 表示收费
	 */
	private int broadnetFee;
	/**
	 * 床型，0表示免费，1 表示收费
	 */
	private String bedType;
	/**
	 * 床型，0表示免费，1 表示收费(界面显示使用)
	 */
	private String bedTypeLabel;
	/**
	 * 描述，包括大床、双床、宽带等一类描述
	 */
	private String description;
	/**
	 * 备注
	 */
	private String comments;
	/**
	 * 如没有提供请根据房间名称判断：单人间或有单字的为1人，三人间的为3人，其他的默认2人
	 */
	private String capacity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getBroadnetAccess() {
		return broadnetAccess;
	}
	public void setBroadnetAccess(int broadnetAccess) {
		this.broadnetAccess = broadnetAccess;
	}
	public int getBroadnetFee() {
		return broadnetFee;
	}
	public void setBroadnetFee(int broadnetFee) {
		this.broadnetFee = broadnetFee;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBedTypeLabel() {
		return bedTypeLabel;
	}
	public void setBedTypeLabel(String bedTypeLabel) {
		this.bedTypeLabel = bedTypeLabel;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
}
