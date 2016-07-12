package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Consulting_records （咨询记录）
 */

@Entity(name="consulting_records")
public class ConsultingRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id; 
	
	/**
	 * 车型ID
	 */
	private Long modelsId;
	
	/**
	 * 客户ID
	 */
	private Long customId;
	
	/**
	 * 次数
	 */
	private Long frequency;
	
	/**
	 * 车型
	 */
	private Long models;
	
	/**
	 * 是否提交
	 */
	private Long commit;
	
	/**
	 * 销售人员
	 */
	private String salesStaff;
	
	/**
	 * 4s 店
	 */
	private String shopName;
	
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

	@Column(name="models")
	public Long getModels() {
		return models;
	}

	public void setModels(Long models) {
		this.models = models;
	}

	@Column(name="commit")
	public Long getCommit() {
		return commit;
	}

	public void setCommit(Long commit) {
		this.commit = commit;
	}

	@Column(name="models_id")
	public Long getModelsId() {
		return modelsId;
	}

	public void setModelsId(Long modelsId) {
		this.modelsId = modelsId;
	}

	@Column(name="custom_id")
	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	@Column(name="sales_staff")
	public String getSalesStaff() {
		return salesStaff;
	}

	public void setSalesStaff(String salesStaff) {
		this.salesStaff = salesStaff;
	}

	@Column(name="shop_name")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
