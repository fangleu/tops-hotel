package com.travelzen.tops.hotel.elong.mongo.entity.staticfile;

import java.util.List;

import com.github.jmkgreen.morphia.annotations.Embedded;

@Embedded
public class Detail {

	/**
	 * 名称，不可为空
	 */
	private String name;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮编
	 */
	private String postalCode;
	/**
	 * 挂牌星级
	 * 
	 * 0-无星级；1-一星级；2-二星级；3-三星级；4-四星级；5-五星级。当为0时对外显示可用Category的值，但请进行图标区分。
	 * 
	 */
	private int starRate;
	/**
	 * 艺龙推荐星级
	 * 
	 * -1，0均代表经济型酒店，（此处酒店星级是艺龙推荐星级，而非酒店挂牌星级）
	 */
	private int category;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 开业时间，年-月
	 */
	private String establishmentDate;
	/**
	 * 装修时间，年-月
	 */
	private String renovationDate;
	/**
	 * 参考品牌数据：集团编号
	 */
	private int groupId;
	/**
	 * 参考品牌数据：品牌编号
	 */
	private int brandId;
	/**
	 * 是否经济型, 默认值为0，1代表是经济型酒店
	 */
	private int isEconomic;
	/**
	 * 是否是公寓,默认值为0，1代表是酒店式公寓
	 */
	private int isApartment;
	/**
	 * 纬度
	 * 
	 * 用于位置搜索，经纬度为Google经纬度
	 */
	private String googleLat;
	/**
	 * 经度
	 */
	private String googleLon;
	/**
	 * 纬度
	 * 
	 * 仅用于本地地图展示，经纬度为Baidu经纬度
	 */
	private String baiduLat;
	/**
	 * 经度
	 */
	private String baiduLon;
	/**
	 * 推荐排序
	 */
	private int rank;
	/**
	 * 城市，不可为空
	 * 
	 * 编码，请在GEO数据中对应
	 * 
	 */
	private String city;
	/**
	 * 关联城市
	 */
	private String city2;
	/**
	 * 行政区
	 * 
	 * 编码，请在GEO数据中对应
	 */
	private String district;
	/**
	 * 商圈
	 * 
	 * 编码，请在GEO数据中对应
	 */
	private String businessZone;
	/**
	 * 酒店支持的信用卡
	 * 
	 */
	private String creditCards;
	/**
	 * 介绍信息
	 */
	private String introEditor;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 接机服务
	 * 
	 * 格式：开始日期,结束日期, 开始时间,结束时间
	 * 比如  2013-01-10,2015-01-10,10：00,20：00
	 */
	private String airportPickUpService;
	/**
	 * 服务设施
	 */
	private String generalAmenities;
	/**
	 * 房间设施
	 */
	private String roomAmenities;
	/**
	 * 休闲设施
	 */
	private String recreationAmenities;
	/**
	 * 会议设施
	 */
	private String conferenceAmenities;
	/**
	 * 餐饮设施
	 */
	private String diningAmenities;
	/**
	 * 周边交通
	 */
	private String traffic;
	/**
	 * 周边信息
	 */
	private String surroundings;
	/**
	 * 特色信息
	 */
	private String features;
	/**
	 * 设施列表
	 * 
	 * 1 免费wifi, 2 收费wifi, 3 免 费宽带, 4 收费宽带,5 免费停车场
	 * 6 收费停车场, 7 免费接机服务, 8 收费接机服务, 9 室内游泳池
	 * 10 室外游泳池,11 健身房,12 商务中心,13 会议室,14 酒店餐厅
	 */
	private String facilities;
	
	/**
	 * 供应商
	 */
	@Embedded
	private List<Supplier> suppliers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getStarRate() {
		return starRate;
	}

	public void setStarRate(int starRate) {
		this.starRate = starRate;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(String establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public String getRenovationDate() {
		return renovationDate;
	}

	public void setRenovationDate(String renovationDate) {
		this.renovationDate = renovationDate;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getIsEconomic() {
		return isEconomic;
	}

	public void setIsEconomic(int isEconomic) {
		this.isEconomic = isEconomic;
	}

	public int getIsApartment() {
		return isApartment;
	}

	public void setIsApartment(int isApartment) {
		this.isApartment = isApartment;
	}

	public String getGoogleLat() {
		return googleLat;
	}

	public void setGoogleLat(String googleLat) {
		this.googleLat = googleLat;
	}

	public String getGoogleLon() {
		return googleLon;
	}

	public void setGoogleLon(String googleLon) {
		this.googleLon = googleLon;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBusinessZone() {
		return businessZone;
	}

	public void setBusinessZone(String businessZone) {
		this.businessZone = businessZone;
	}

	public String getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(String creditCards) {
		this.creditCards = creditCards;
	}

	public String getIntroEditor() {
		return introEditor;
	}

	public void setIntroEditor(String introEditor) {
		this.introEditor = introEditor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAirportPickUpService() {
		return airportPickUpService;
	}

	public void setAirportPickUpService(String airportPickUpService) {
		this.airportPickUpService = airportPickUpService;
	}

	public String getGeneralAmenities() {
		return generalAmenities;
	}

	public void setGeneralAmenities(String generalAmenities) {
		this.generalAmenities = generalAmenities;
	}

	public String getRoomAmenities() {
		return roomAmenities;
	}

	public void setRoomAmenities(String roomAmenities) {
		this.roomAmenities = roomAmenities;
	}

	public String getRecreationAmenities() {
		return recreationAmenities;
	}

	public void setRecreationAmenities(String recreationAmenities) {
		this.recreationAmenities = recreationAmenities;
	}

	public String getConferenceAmenities() {
		return conferenceAmenities;
	}

	public void setConferenceAmenities(String conferenceAmenities) {
		this.conferenceAmenities = conferenceAmenities;
	}

	public String getDiningAmenities() {
		return diningAmenities;
	}

	public void setDiningAmenities(String diningAmenities) {
		this.diningAmenities = diningAmenities;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getSurroundings() {
		return surroundings;
	}

	public void setSurroundings(String surroundings) {
		this.surroundings = surroundings;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public String getBaiduLat() {
		return baiduLat;
	}

	public void setBaiduLat(String baiduLat) {
		this.baiduLat = baiduLat;
	}

	public String getBaiduLon() {
		return baiduLon;
	}

	public void setBaiduLon(String baiduLon) {
		this.baiduLon = baiduLon;
	}
	
}
