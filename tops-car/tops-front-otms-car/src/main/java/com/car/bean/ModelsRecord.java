package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="models_record")
public class ModelsRecord implements Serializable{
	
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

	@Column(name="stopby_id")
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
