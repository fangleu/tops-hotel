package com.car.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="level") 
public class Level implements Serializable{
	
	/**
	 * 用户级别表
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  

	/**
	 * 姓名
	 */
	private String shopName;
	
	/**
	 * 不同级别
	 * 0：最新关注（潜客）
	 * 1：看过车型信息 （潜客）
	 * 2：看过促销信息
	 * 3：看过到店地址
	 * 4：看过预约
	 * 5：增加，删除，修改 （操作）
	 * 6：保存并发布 （操作）
	 * 7:4s人员
	 */
	private Long level;

	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="id",unique=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="shop_name") 
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	@Column(name="level")
	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}
	

}
