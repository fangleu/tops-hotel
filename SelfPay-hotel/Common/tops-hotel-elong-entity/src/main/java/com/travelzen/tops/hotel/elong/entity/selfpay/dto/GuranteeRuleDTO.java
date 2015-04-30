package com.travelzen.tops.hotel.elong.entity.selfpay.dto;


public class GuranteeRuleDTO{
	/**
	 *  规则id;
	 */
	private int guranteeRuleId;
	/**
	 * 担保描述
	 */
	private String description;
	/**
	 * 担保类型
	 */
	private String startTime;
	/**
	 * 担保开始时间
	 */
	private String endTime;
	/**
	 * 担保结束时间
	 */
	private String guaranteeType;
	/**
	 * 是否需要数量担保
	 */
	private boolean isAmountGuarantee;
	/**
	 * 是否需要时间担保
	 */
	private boolean isTimeGuarantee;
	/**
	 * 担保房间数量
	 */
	private int amount;
	public int getGuranteeRuleId() {
		return guranteeRuleId;
	}
	public void setGuranteeRuleId(int guranteeRuleId) {
		this.guranteeRuleId = guranteeRuleId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public boolean isAmountGuarantee() {
		return isAmountGuarantee;
	}
	public void setAmountGuarantee(boolean isAmountGuarantee) {
		this.isAmountGuarantee = isAmountGuarantee;
	}
	public boolean isTimeGuarantee() {
		return isTimeGuarantee;
	}
	public void setTimeGuarantee(boolean isTimeGuarantee) {
		this.isTimeGuarantee = isTimeGuarantee;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}