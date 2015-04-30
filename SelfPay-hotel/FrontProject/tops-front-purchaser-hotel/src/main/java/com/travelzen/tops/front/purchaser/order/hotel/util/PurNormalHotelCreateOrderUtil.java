package com.travelzen.tops.front.purchaser.order.hotel.util;

import com.travelzen.framework.core.exception.BizException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.hotel.converter.HotelOrderBoConverter;
import com.travelzen.tops.order.hotel.creme.dto.CreateOrderCriteria;

public class PurNormalHotelCreateOrderUtil {

	public static CreateOrderCriteria buildCreateCriteria(HotelOrderBo hotelOrderBo) {
		CreateOrderCriteria criteria = new CreateOrderCriteria();
		criteria.setHotelId(hotelOrderBo.getHotelDetailBo().getHotelId());
		criteria.setCheckInDate(DateTimeUtil.format(hotelOrderBo.getHotelDetailBo().getCheckinDate(), DateTimeUtil.DATE_PATTERN));
		criteria.setCheckOutDate(DateTimeUtil.format(hotelOrderBo.getHotelDetailBo().getCheckoutDate(), DateTimeUtil.DATE_PATTERN));
		criteria.setRoomCatId(hotelOrderBo.getHotelDetailBo().getRoomcatId());
		criteria.setBookingClassId(hotelOrderBo.getHotelDetailBo().getBookingclassId());
		criteria.setRoomNo(hotelOrderBo.getHotelDetailBo().getRoomNo());
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		criteria.setCustomer(TopsSecurityUtils.getUserFromSession().getCustomerData());
		return criteria;
	}

	public static void checkOrderPriceBeforeCreate(HotelOrderBo hotelOrderBo,
			TPropertySearchBo propertySearchBo, CreateOrderCriteria criteria) throws BizException {

		HotelOrderBo order = new HotelOrderBo();
		HotelOrderBoConverter.convertTPropertySearchBo2HotelOrderBo(order,null, propertySearchBo, criteria, OrderType.PURCHASER_NORMAL_HOTEL);
		if (order.getTotalOrderFee().compareTo(hotelOrderBo.getTotalOrderFee()) != 0
				|| order.getTotalBasicFee().compareTo(hotelOrderBo.getTotalBasicFee()) != 0
				|| order.getTotalMarkupFee().compareTo(hotelOrderBo.getTotalMarkupFee()) != 0) {
			throw BizException.instance("创建酒店订单失败，订单价格金额计算不正确。");
		}
	}
}
