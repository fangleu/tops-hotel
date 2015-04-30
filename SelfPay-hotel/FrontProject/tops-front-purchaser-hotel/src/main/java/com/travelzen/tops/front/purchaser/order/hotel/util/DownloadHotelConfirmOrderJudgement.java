package com.travelzen.tops.front.purchaser.order.hotel.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.search.util.StringUtil;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderState;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.order.core.hotel.bo.common.HotelOrderCommonBo;
import com.travelzen.tops.order.core.po.gen.HotelOrder;
import com.travelzen.tops.order.hotel.common.service.query.IHotelOrderQueryCommonService;

/**
 *    判断当前订单是否可以下载客户确认单
 * <p>判断逻辑为:
 * <p>1.非调账单已完成订单可以下载
 * <p>2.金额不为0的已完成调账单可以下载
 * <p>3.使用1,2两个条件判断后续订单，如果存在其他可以下载的，则当前订单不可下载，如果其他订单均不可下载，则当前订单可以下载
 * @author xumeng
 * @since 2014-09-10
 *
 */
@Component("downloadHotelConfirmOrderJudgement")
public class DownloadHotelConfirmOrderJudgement {

	@Resource(name="hotel_order_hotelOrderQueryCommonService")
	private IHotelOrderQueryCommonService<HotelOrderCommonBo> hotelOrderQueryCommonService;

	public boolean canDownloadConfirmOrder(String hotelOrderId) {
		if (StringUtil.isEmpty(hotelOrderId)) return false;
		HotelOrder hotelOrder = hotelOrderQueryCommonService.getById(hotelOrderId, HotelOrderTable.hotel_order);
		return canDownloadConfirmOrder(hotelOrder);
	}

	public boolean canDownloadConfirmOrder(HotelOrder hotelOrder) {
		if (ValidationUtil.isNull(hotelOrder)) return false;
		if (singleConfirmOrderCanDownload(hotelOrder)) return true;

		List<HotelOrder> orderList = hotelOrderQueryCommonService.getHotelOrderByOriginalOrderId(hotelOrder.getOriginalOrderId());
		if (orderList != null && orderList.size() > 0) {
			HotelOrder theLastOrder = null;
			Map<String,HotelOrder> orderMap = new HashMap<>();
			for (HotelOrder order : orderList) {
				if (HotelOrderState.review_success.name().equals(order.getState())) {
					theLastOrder = order;
					orderMap.put(order.getId(), order);
				}
			}

			while (theLastOrder != null) {
				if (singleConfirmOrderCanDownload(theLastOrder)) {
					if (theLastOrder.getId().equals(hotelOrder.getId())) {
						return true;
					}
					break;
				} else {
					theLastOrder = orderMap.get(theLastOrder.getOrigOrderId());
				}
			}
		}
		return false;
	}

	/**
	 * 判断一张订单是否可以下载客户确认单，如果一定可以，则返回true，如果根据当前订单不确定是否可以，则返回false
	 * @param hotelOrder
	 * @return
	 */
	private boolean singleConfirmOrderCanDownload(HotelOrder hotelOrder) {
		if (HotelOrderState.review_success.name().equals(hotelOrder.getState())) {
			if (OrderType.NORMAL_HOTEL.name().equals(hotelOrder.getOrderType())
					||OrderType.PURCHASER_NORMAL_HOTEL.name().equals(hotelOrder.getOrderType())
					||OrderType.GTA_NORMAL_HOTEL.name().equals(hotelOrder.getOrderType())
					||OrderType.ENDORSE_HOTEL.name().equals(hotelOrder.getOrderType())
					||OrderType.PURCHASER_ENDORSE_HOTEL.name().equals(hotelOrder.getOrderType())
					||OrderType.OFFLINE_NORMAL_HOTEL.name().equals(hotelOrder.getOrderType())
					||OrderType.OFFLINE_ENDORSE_HOTEL.name().equals(hotelOrder.getOrderType())){
				return true;
			}
			if (OrderType.ADJUST_HOTEL.name().equals(hotelOrder.getOrderType())) {
				if (hotelOrder.getTotalOrderFee() != 0l) {
					return true;
				}
			}
		}
		return false;
	}
}
