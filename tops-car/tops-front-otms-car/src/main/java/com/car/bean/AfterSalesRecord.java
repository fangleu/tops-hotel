package com.car.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * after_sales_record （售后记录）
 */

@Entity(name="after_sales_record")
public class AfterSalesRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  
	
	private Long userId;
	
	/**
	 * 车型
	 */
	private String models;
	
	/**
	 * 售后类型（保养，维修）
	 */
	private Long type;
	
	/**
	 * 预约时间
	 */
	private Date TimeOfAppointment;
	
	/**
	 * 到店时间
	 */
	private Date timeToShop;
	
	/**
	 * 备注
	 */
	private String remarks;
	
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

	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="models")
	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	@Column(name="type")
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name="Time_of_appointment")
	public Date getTimeOfAppointment() {
		return TimeOfAppointment;
	}

	public void setTimeOfAppointment(Date timeOfAppointment) {
		TimeOfAppointment = timeOfAppointment;
	}

	@Column(name="time_to_shop")
	public Date getTimeToShop() {
		return timeToShop;
	}

	public void setTimeToShop(Date timeToShop) {
		this.timeToShop = timeToShop;
	}

	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
