package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Test_record （试驾记录）
 */

@Entity(name="test_record")
public class TestRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  

	/**
	 * 车型 ID
	 */
	private Long modelsId;
	
	/**
	 * 客户 ID
	 */
	private Long customId;
	
	/**
	 * 次数
	 */
	private Long frequency;
	
	/**
	 * 车型
	 */
	private String models;
	
	/**
	 * 是否提交
	 */
	private Long isCommit;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 销售人员
	 */
	private String salesStaff;
	
	/**
	 * 提交时间
	 */
	private Date commitTime;
	
	/**
	 * 到店时间
	 */
	private Date timeToShop;
	
	/**
	 * 4s 店
	 */
	private String shopName;
	
	/**
	 * 创建时间
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
	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Column(name="is_commit")
	public Long getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(Long isCommit) {
		this.isCommit = isCommit;
	}

	@Column(name="sales_staff")
	public String getSalesStaff() {
		return salesStaff;
	}

	public void setSalesStaff(String salesStaff) {
		this.salesStaff = salesStaff;
	}

	@Column(name="commit_time")
	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	@Column(name="time_to_shop")
	public Date getTimeToShop() {
		return timeToShop;
	}

	public void setTimeToShop(Date timeToShop) {
		this.timeToShop = timeToShop;
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
