package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import java.util.List;
import java.util.Map;

import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.travelzen.tops.hotel.elong.mongo.entity.base.AbstractMorphiaEntity;

@Entity("elong.Hotel")
public class Hotel extends AbstractMorphiaEntity {
	/**
	 * 酒店编号
	 */
	@Indexed
	private String hotelId;
	
	/**
	 * 更新时间，不可为空
	 */
	private String updatedTime;
	/**
	 * 详情
	 */
	@Embedded
	private Detail detail;
	/**
	 * 房型
	 */
	@Embedded
	private List<Room> rooms;
	/**
	 * 图片
	 */
	@Embedded
	private List<Image> images;
	/**
	 * 房型（便于界面查找指定房型信息）
	 * key = id，value =  room 集合，目前从酒店详细静态文件看，不会出现一个 room 会出现两次，为了容错，
	 * 所以用List<Room> 作为value。
	 */
	@Embedded
	private Map<String,List<Room>> roomsCustomFormat = null;
	/**
	 * 图片,便于前端需要信息的快速查找
	 * Map<${Image#type},List<Map<${ImageLocation#WaterMark}+${ImageLocation#Size},List<ImageLocation>>>>
	 */
	@Embedded
	private Map<String,List<Map<String,List<ImageLocation>>>> imagesCustomFormat;
	/**
	 * 图片,指定房型的图片信息
	 * Map<${Room#id},Map<${ImageLocation#WaterMark}+${ImageLocation#Size},List<ImageLocation>>>
	 */
	@Embedded
	private Map<String,Map<String,List<ImageLocation>>> roomIdImagesCustomFormat;
	/**
	 * 点评
	 */
	@Embedded
	private Review review;
	
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public Map<String, List<Room>> getRoomsCustomFormat() {
		return roomsCustomFormat;
	}
	public void setRoomsCustomFormat(Map<String, List<Room>> roomsCustomFormat) {
		this.roomsCustomFormat = roomsCustomFormat;
	}
	public Map<String, List<Map<String, List<ImageLocation>>>> getImagesCustomFormat() {
		return imagesCustomFormat;
	}
	public void setImagesCustomFormat(
			Map<String, List<Map<String, List<ImageLocation>>>> imagesCustomFormat) {
		this.imagesCustomFormat = imagesCustomFormat;
	}
	public Map<String, Map<String, List<ImageLocation>>> getRoomIdImagesCustomFormat() {
		return roomIdImagesCustomFormat;
	}
	public void setRoomIdImagesCustomFormat(
			Map<String, Map<String, List<ImageLocation>>> roomIdImagesCustomFormat) {
		this.roomIdImagesCustomFormat = roomIdImagesCustomFormat;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
}
