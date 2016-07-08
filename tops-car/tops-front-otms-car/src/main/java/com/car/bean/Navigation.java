package com.car.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="navigation") 
public class Navigation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  

	/**
	 * 店名
	 */
	private String name;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 售前热钱
	 */
	private String preSales;
	
	/**
	 * 售后热线
	 */
	private String afterSales;
	
	/**
	 * 经度
	 */
	private String longitude;
	
	/**
	 * 维度
	 */
	private String latitude;

	
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="id",unique=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="pre_sales")
	public String getPreSales() {
		return preSales;
	}

	public void setPreSales(String preSales) {
		this.preSales = preSales;
	}

	@Column(name="after_sales")
	public String getAfterSales() {
		return afterSales;
	}

	public void setAfterSales(String afterSales) {
		this.afterSales = afterSales;
	}
	

}

