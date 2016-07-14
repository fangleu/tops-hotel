package com.car.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 文章表
 * @author Administrator
 *
 */
@Entity(name="promotion")
public class Promotion  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 促销开始时间
	 */
	private Date startTime;
	
	/**
	 * 促销结束时间
	 */
	private Date endTime;
	
	/**
	 * 发布时间 （创建时间）
	 */
	private Date createTime;
	
	/**
	 * 文章类型
	 * 0. 企业动态
	 * 1. 车型促销
	 * 2. 到店优惠
	 * 3. 车主福利
	 * 4. 其他活动
	 */
	private Long type;
	
	/**
	 * 焦点图
	 */
	private String focus;
	
	/**
	 * 文章简述
	 */
	private String sketch;
	
	/**
	 * 正文内容
	 */
	private String detail;
	
	/**
	 * 图片地址
	 */
	private String image;
	
	/**
	 * 车型 ID
	 */
	private int modelsId;

	
	@Id  
    @GeneratedValue(strategy=GenerationType.AUTO)  
    @Column(name="id",unique=true)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="type")
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name="focus")
	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	@Column(name="sketch")
	public String getSketch() {
		return sketch;
	}

	public void setSketch(String sketch) {
		this.sketch = sketch;
	}

	@Column(name="detail")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name="image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="models_id")
	public int getModelsId() {
		return modelsId;
	}

	public void setModelsId(int modelsId) {
		this.modelsId = modelsId;
	}
	
	
}
