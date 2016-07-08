package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="models")
public class Models implements Serializable{
	
	/**
	 * 用户级别表
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  

	/**
	 * 品牌
	 */
	private String brand;
	
	/**
	 * 车型
	 */
	private String models;
	
	/**
	 * 图片地址
	 */
	private String image;
	
	/**
	 * 官方指导价
	 */
	private String officialPrice;
	
	/**
	 * 文章
	 */
	private String sketch;
	
	/**
	 * 官方 URL
	 */
	private String link;
	
	/**
	 * 创建日期
	 */
	private Date createTime;

	
	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="id",unique=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="brand")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name="models")
	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	@Column(name="image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name="sketch")
	public String getSketch() {
		return sketch;
	}

	public void setSketch(String sketch) {
		this.sketch = sketch;
	}

	@Column(name="link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Column(name="official_price")
	public String getOfficialPrice() {
		return officialPrice;
	}

	public void setOfficialPrice(String officialPrice) {
		this.officialPrice = officialPrice;
	}

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
