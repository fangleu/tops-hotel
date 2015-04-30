package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;
@Entity("elong.CreditCard")
public class CreditCard extends AbstractMorphiaEntity {
	/**
	 * 银行编号，不可为空
	 */
	private int categoryId;
	/**
	 * 银行中文名，不可为空
	 */
	private String categoryName;
	/**
	 * 银行英文名，不可为空
	 */
	private String categoryNameEn;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryNameEn() {
		return categoryNameEn;
	}
	public void setCategoryNameEn(String categoryNameEn) {
		this.categoryNameEn = categoryNameEn;
	}
}
