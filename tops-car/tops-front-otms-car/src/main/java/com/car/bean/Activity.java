package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Activity （H5 活动）
 */

@Entity(name="activity")
public class Activity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	/**
	 * 活动详情
	 */
	private String detail;
	
	/**
	 * 奖品设置
	 */
	private String prizeSetting;
	
	/**
	 * 获奖概率
	 */
	private String probaility;
	
	/**
	 * 活动类型
	 */
	private String activityType;
	
	/**
	 * 奖品失效时间
	 */
	private Date failureTime;
	
	/**
	 * 创建日期
	 */
	private String createDate;


	
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

	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="detail")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name="prize_setting")
	public String getPrizeSetting() {
		return prizeSetting;
	}

	public void setPrizeSetting(String prizeSetting) {
		this.prizeSetting = prizeSetting;
	}

	@Column(name="probaility")
	public String getProbaility() {
		return probaility;
	}

	public void setProbaility(String probaility) {
		this.probaility = probaility;
	}

	@Column(name="activity_type")
	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@Column(name="failure_time")
	public Date getFailureTime() {
		return failureTime;
	}

	public void setFailureTime(Date failureTime) {
		this.failureTime = failureTime;
	}

	@Column(name="create_date")
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
