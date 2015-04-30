package com.travelzen.tops.front.purchaser.order.hotel.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView.OperationSource;
import com.travelzen.tops.hotel.order.engine.model.component.THotelOrder;
import com.travelzen.tops.order.core.po.gen.HotelOrder;
import com.travelzen.tops.order.hotel.common.service.HotelOrderAuthCenterFactory;

/**
 * @author xumeng
 */
@Component("purHotelOrderOperationUtil")
public class PurHotelOrderOperationUtil {

	@Resource(name = "hotelOrder_authCenterFactory")
	private HotelOrderAuthCenterFactory hotelOrderAuthCenterFactory;

	/**
	 * 批量获取订单可进行的操作，返回已订单ID为Key的Map实例
	 */
	public Map<String, Collection<HotelOrderOperationView>> getBatchOperations(List<? extends HotelOrder> hotelOrders) {
		if (ValidationUtil.isNull(hotelOrders)) return null;

		Map<String, Collection<HotelOrderOperationView>> orderOperations = new HashMap<>();
		for (HotelOrder hotelOrder : hotelOrders) {
			orderOperations.put(hotelOrder.getId(), 
					hotelOrderAuthCenterFactory.getAuthIntercace(hotelOrder).getOperations(hotelOrder, OperationSource.purchaser));
		}
		return orderOperations;
	}

	/**
	 * 批量获取订单可进行的操作，返回已订单ID为Key的Map实例
	 */
	public Map<String, Collection<HotelOrderOperationView>> getBatchOperationsForTHotelOrder(List<THotelOrder> tHotelOrders) {
		if (ValidationUtil.isNull(tHotelOrders)) return null;
		List<HotelOrder> hotelOrders = new ArrayList<>();
		for (THotelOrder tOrder : tHotelOrders) {
			HotelOrder order = new HotelOrder();
			order.setId(tOrder.getId());
			order.setState(tOrder.getState());
			order.setSettleDetailState(tOrder.getSettleDetailState());
			order.setFlowState(tOrder.getFlowState());
			order.setFlowOperator(tOrder.getFlowOperator());
			order.setOrderType(tOrder.getOrderType());
			order.setHotelInterfaceSupplier(tOrder.getHotelInterfaceSupplier());
			hotelOrders.add(order);
		}
		return getBatchOperations(hotelOrders);
	}
}
