package com.travelzen.tops.front.purchaser.hotel.vo;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.travelzen.framework.core.dict.GatheringRecordState;
import com.travelzen.framework.core.dict.PayQueryState;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.tops.common.dict.finance.enums.PayType;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderStateCustomerView;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelGuestBo;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.core.po.gen.HotelGuest;
import com.travelzen.tops.order.core.po.gen.HotelOrder;

/**
 * @deprecated
 */
public class HotelOrderVo extends HotelOrder{
	
	private static final long serialVersionUID = -7414798296067894926L;
	private String totalBasicFeeYuan;    //基本房费总额
	private String totalMarkupFeeYuan;    //利润总额
	private String totalRoomFeeYuan;    //房费,=基本房费总额+利润总额
	private String totalAddServiceFeeYuan;    //增值服务总额
	private String orderProcedureFeeYuan;    //手续费
	private String totalOrderFeeYuan;        //订单总额
	private String settleOrderFeeYuan;
	private Integer roomNightNo;//间夜
	private Integer roomNo;//间数
	private String roomCat;//房型
	private String pagePaymentType;
	private String guestNames;//入住人
	private String payWay;//付款方式
	
	public HotelOrderVo() {
		
	}
	
	public HotelOrderVo(HotelOrderBo bo) {
		try {
			PropertyUtils.copyProperties(this, bo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		if(null != HotelOrderStateCustomerView.getCustomerState(bo.getState(), bo.getGatheringState(), bo.getConfirmStatus())){
			this.setState(HotelOrderStateCustomerView.getCustomerState(bo.getState(), bo.getGatheringState(), bo.getConfirmStatus()).getDesc());
		}
		totalBasicFeeYuan = MoneyUtil.cent2Yuan(bo.getTotalBasicFee() == null ? 0 : bo.getTotalBasicFee());
		totalMarkupFeeYuan = MoneyUtil.cent2Yuan(bo.getTotalMarkupFee() == null ? 0 : bo.getTotalMarkupFee());
		totalRoomFeeYuan = MoneyUtil.cent2Yuan(bo.getTotalMarkupFee() == null ? 0 : bo.getTotalMarkupFee());
		totalAddServiceFeeYuan = MoneyUtil.cent2Yuan(bo.getTotalAddServiceFee() == null ? 0 : bo.getTotalAddServiceFee());
		orderProcedureFeeYuan = MoneyUtil.cent2Yuan(bo.getOrderProcedureFee() == null ? 0 : bo.getOrderProcedureFee());
		totalOrderFeeYuan = MoneyUtil.cent2Yuan(bo.getTotalOrderFee() == null ? 0 : bo.getTotalOrderFee());
		settleOrderFeeYuan = MoneyUtil.cent2Yuan(bo.getSettleOrderFee() == null ? 0 : bo.getSettleOrderFee());
		
		roomCat = bo.getHotelDetailBo().getRoomCat();
		roomNo = bo.getHotelDetailBo().getRoomNo();
		roomNightNo = (bo.getHotelDetailBo().getRoomNo()==null?0:bo.getHotelDetailBo().getRoomNo())*(bo.getHotelDetailBo().getCheckinDayNo()==null?0:bo.getHotelDetailBo().getCheckinDayNo());
		if (StringUtils.isNotBlank(bo.getPaymentType())) {
			pagePaymentType = PayType.valueOf(bo.getPaymentType()).getDescription();
		}
		if(StringUtils.isNotBlank(this.getGatheringState())){
			GatheringRecordState state = GatheringRecordState.valueOf(this.getGatheringState());
			switch (state) {
			case req_frozen:
			case frozen_fail:
			case unfrozen_causedBy_cancel:
				payWay = PayQueryState.notPaid.getDesc(); break;
			case frozen_success:
			case gathering_fail:
			case gathering_success:			
			case req_gathering: 
				payWay = PayQueryState.paid.getDesc(); break;
				default:
			}
		}
		List<HotelGuestBo>  guests = bo.getHotelGuestBo();
		guestNames = "";
		if(guests != null && !guests.isEmpty()){
			int i = 1;
			int guestNum = guests.size();
			for (HotelGuest guest : guests) {
				if(i != guestNum){
					guestNames += guest.getName()+",";
					i++;
				}else{
					guestNames += guest.getName(); //最后一个客户名后不加“,”
				}
			}
		}
	}
	
	
	public String getTotalBasicFeeYuan() {
		return totalBasicFeeYuan;
	}
	public void setTotalBasicFeeYuan(String totalBasicFeeYuan) {
		this.totalBasicFeeYuan = totalBasicFeeYuan;
	}
	public String getTotalMarkupFeeYuan() {
		return totalMarkupFeeYuan;
	}
	public void setTotalMarkupFeeYuan(String totalMarkupFeeYuan) {
		this.totalMarkupFeeYuan = totalMarkupFeeYuan;
	}
	public String getTotalRoomFeeYuan() {
		return totalRoomFeeYuan;
	}
	public void setTotalRoomFeeYuan(String totalRoomFeeYuan) {
		this.totalRoomFeeYuan = totalRoomFeeYuan;
	}
	public String getTotalAddServiceFeeYuan() {
		return totalAddServiceFeeYuan;
	}
	public void setTotalAddServiceFeeYuan(String totalAddServiceFeeYuan) {
		this.totalAddServiceFeeYuan = totalAddServiceFeeYuan;
	}
	public String getOrderProcedureFeeYuan() {
		return orderProcedureFeeYuan;
	}
	public void setOrderProcedureFeeYuan(String orderProcedureFeeYuan) {
		this.orderProcedureFeeYuan = orderProcedureFeeYuan;
	}
	public String getTotalOrderFeeYuan() {
		return totalOrderFeeYuan;
	}
	public void setTotalOrderFeeYuan(String totalOrderFeeYuan) {
		this.totalOrderFeeYuan = totalOrderFeeYuan;
	}
	public String getSettleOrderFeeYuan() {
		return settleOrderFeeYuan;
	}
	public void setSettleOrderFeeYuan(String settleOrderFeeYuan) {
		this.settleOrderFeeYuan = settleOrderFeeYuan;
	}
	public Integer getRoomNightNo() {
		return roomNightNo;
	}
	public void setRoomNightNo(Integer roomNightNo) {
		this.roomNightNo = roomNightNo;
	}
	public String getRoomCat() {
		return roomCat;
	}
	public void setRoomCat(String roomCat) {
		this.roomCat = roomCat;
	}
	public Integer getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	public String getPagePaymentType() {
		return pagePaymentType;
	}
	public void setPagePaymentType(String pagePaymentType) {
		this.pagePaymentType = pagePaymentType;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getGuestNames() {
		return guestNames;
	}

	public void setGuestNames(String guestNames) {
		this.guestNames = guestNames;
	}

	
}
