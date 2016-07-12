package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * winning_record （中奖纪录）
 */

@Entity(name="winning_record")
public class WinningRecord implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  

	/**
	 * 活动 ID
	 */
	private Long activityId;
	
	/**
	 * 客户 ID
	 */
	private Long customId;
	
	/**
	 * 领奖状态
	 */
	private String awardStatus;
	
	/**
	 * 获奖等级
	 */
	private String winningGrade;
	
	/**
	 * 是否使用
	 */
	private String useStatus;
	
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

	@Column(name="activity_id")
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Column(name="custom_id")
	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	@Column(name="award_status")
	public String getAwardStatus() {
		return awardStatus;
	}

	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}

	@Column(name="winning_grade")
	public String getWinningGrade() {
		return winningGrade;
	}

	public void setWinningGrade(String winningGrade) {
		this.winningGrade = winningGrade;
	}

	@Column(name="use_status")
	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
