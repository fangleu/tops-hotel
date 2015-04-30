package com.travelzen.tops.front.purchaser.order.hotel.util;

import org.springframework.stereotype.Service;

import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.BizException;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.order.core.po.gen.HotelOrder;

@Service("purHotelOrderAuthUtil")
public class PurHotelOrderAuthUtil {

	/**
	 * 判断当前登录客户操作的订单是否是当前客户的订单，此接口由于bug发现的时候临近上线，而修改
	 * HotelOrderAuthCenter.canExcuteOperation接口则影响范围太大，不宜大幅度改动，因此
	 * 暂时提供此接口，待以后有时间即将此接口融入到上述接口中。
	 * @deprecated
	 */
	public void doesOrderBelongToCurrentUser(HotelOrder hotelOrder) {
		String customerKey = TopsSecurityUtils.getUserFromSession().getCustomerKey();
		if (hotelOrder == null || !customerKey.equals(hotelOrder.getCustomerKey())) {
			throw BizException.instance(ReturnCode.ERROR, "不可对当前订单进行当前操作。");
		}
	}
}
