package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;

@Entity("elong.HotelIndex")
public class HotelIndex extends AbstractMorphiaEntity {
	/**
	 * 酒店ID，不可为空
	 */
	@Indexed
	private String hotelId;
	
	/**
	 * 更新时间，不可为空
	 */
	private String updatedTime;
	/**
	 * 更新内容
	 * 
	 * 0-酒店、1-房型、2-图片、3-产品（产品已修改使用动态接口中的状态增量）
	 * 
	 */
	private String modification;
	
	/**
	 * 包含产品
	 * 
	 * 0-现付、1-预付、2-今日特价、3-限时抢购、4-钟点房（酒店不可用时为空）
	 */
	private String products;
	/**
	 * 可用状态
	 * 
	 * 0--可用，1--不可用
	 */
	private int status;
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
