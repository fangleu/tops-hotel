package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import com.github.jmkgreen.morphia.annotations.Embedded;

@Embedded
public class ImageLocation {
	/**
	 * 节点内容
	 * 
	 * 如果url不是以http开始，则需要增加前缀http://www.elongstatic.com/pp/hotels/hotel
	 */
	private String content;
	/**
	 * 图片规格
	 * 
	 * 1：jpg图片，固定长边350，固定长边350缩放(用于详情页图片展示)
	 * 2：jpg图片，尺寸70x70(用于详情页图片列表的缩微图)
	 * 3：jpg图片，尺寸120x120(用于列表页)
	 * 5：png图片，尺寸70x70
	 * 6：png图片，尺寸120x120
	 * 7：png图片，固定长边640放缩
	 */
	private int sizeType;
	/**
	 * 是否有水印, 0-N,1-Y
	 */
	private int waterMark;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSizeType() {
		return sizeType;
	}
	public void setSizeType(int sizeType) {
		this.sizeType = sizeType;
	}
	public int getWaterMark() {
		return waterMark;
	}
	public void setWaterMark(int waterMark) {
		this.waterMark = waterMark;
	}
	
}
