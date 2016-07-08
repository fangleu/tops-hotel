package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="promotion_record")
public class PromotionRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  
	
	/**
	 * 促销 ID
	 */
	private Long promotionId;
	
	/**
	 * 客户 ID
	 */
	private Long customId;
	
	/**
	 * 次数
	 */
	private Long frequency;
	
	/**
	 * 浏览时长
	 */
	private Date stopbyTime;
	
	/**
	 * 创建日期
	 */
	private Date createDate;


	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="id",unique=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="frequency")
	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	@Column(name="promotion_id")
	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	@Column(name="custom_id")
	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	@Column(name="stopby_time")
	public Date getStopbyTime() {
		return stopbyTime;
	}

	public void setStopbyTime(Date stopbyTime) {
		this.stopbyTime = stopbyTime;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
