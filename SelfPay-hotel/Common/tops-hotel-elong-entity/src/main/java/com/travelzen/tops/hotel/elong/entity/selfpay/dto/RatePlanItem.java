package com.travelzen.tops.hotel.elong.entity.selfpay.dto;
import java.io.Serializable;
import java.util.List;

import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Gift;
public class RatePlanItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8542242313825462493L;
	/**
	 * 剩余房间数
	 */
	private int remainCount;
	/**
	 * 房间费率名称
	 */
	private String ratePlanName;
	/**
	 * 房间费率Id
	 */
	private int ratePlanId;
	/**
	 * 房间类型Id
	 */
	private String roomTypeId;
	/**
	 * 供应商酒店编码
	 */
	private String hotelCode;
	/**
	 * 床型
	 */
	private String bedType;
	/**
	 * 床型描述
	 */
	private String bedTypeDesc;
	/**
	 * 早餐
	 */
	private String breakfastType;
	/**
	 * 宽带
	 */
	private String netType;
	/**
	 * 限制条件
	 */
	private String limitType;
	/**
	 * 限制条件描述
	 */
	private String limitDesc;
	/**
	 * 房价（含服务费）
	 */
	private double roomPrice;
	/**
	 * 担保规则
	 * 值可为0,00,01,10,11
	 * 0表示无担保，00表示强制担保
	 * 两位数时表示房量担保+时间担保。1表示担保，0表示无担保
	 */
	private String guaranteeRule;
	/**
	 * 担保规则id
	 */
	private int guaranteeId;
	/**
	 * 最大入住人数
	 */
	private String capacity;
	/**
	 * 总价
	 */
	private double totalPrice;
	/**
	 * 限时取消提示信息
	 */
	private String cancelMsg;
	/**
	 * 客户类型
	 */
	private String customType;
	/**
	 * 担保类型
	 */
	private String guaranteeType;
	/**
	 * 房量担保数
	 */
	private Integer amount;
	/**
	 * 到店担保开始时间
	 */
	private String startTime;
	/**
	 * 到店担保结束时间
	 */
	private String endTime;
	/**
	 * 是否强制担保?是否担保
	 */
	private String guarantee;
	/**
	 * 货币类型
	 */
	private String currencyCode;
	/**
	 * 价格日历
	 */
	private DayPriceListDTO dayPriceList;
	/**
	 * 礼包
	 */
	private List<Gift> giftList;
	
	/**
	 * 房量预警
	 */
	private String roomNum;
	
	/**
	 * 担保条件描述
	 */
	private String guaranteeDesc;
	
	
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public List<Gift> getGiftList() {
		return giftList;
	}
	public void setGiftList(List<Gift> giftList) {
		this.giftList = giftList;
	}
	public DayPriceListDTO getDayPriceList() {
		return dayPriceList;
	}
	public void setDayPriceList(DayPriceListDTO dayPriceList) {
		this.dayPriceList = dayPriceList;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	
	public String getNetType() {
		return netType;
	}
	public void setNetType(String netType) {
		this.netType = netType;
	}
	
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public String getRatePlanName() {
		return ratePlanName;
	}
	public void setRatePlanName(String ratePlanName) {
		this.ratePlanName = ratePlanName;
	}
	public String getBreakfastType() {
		return breakfastType;
	}
	public void setBreakfastType(String breakfastType) {
		this.breakfastType = breakfastType;
	}
	public String getGuaranteeRule() {
		return guaranteeRule;
	}
	public void setGuaranteeRule(String guaranteeRule) {
		this.guaranteeRule = guaranteeRule;
	}
	public int getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
	}
	public String getLimitType() {
		return limitType;
	}
	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}
	public String getGuaranteeDesc() {
		return guaranteeDesc;
	}
	public void setGuaranteeDesc(String guaranteeDesc) {
		this.guaranteeDesc = guaranteeDesc;
	}
	public String getLimitDesc() {
		return limitDesc;
	}
	public void setLimitDesc(String limitDesc) {
		this.limitDesc = limitDesc;
	}
	public int getRatePlanId() {
		return ratePlanId;
	}
	public void setRatePlanId(int ratePlanId) {
		this.ratePlanId = ratePlanId;
	}
	public String getBedTypeDesc() {
		return bedTypeDesc;
	}
	public void setBedTypeDesc(String bedTypeDesc) {
		this.bedTypeDesc = bedTypeDesc;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public int getGuaranteeId() {
		return guaranteeId;
	}
	public void setGuaranteeId(int guaranteeId) {
		this.guaranteeId = guaranteeId;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCancelMsg() {
		return cancelMsg;
	}
	public void setCancelMsg(String cancelMsg) {
		this.cancelMsg = cancelMsg;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
}
