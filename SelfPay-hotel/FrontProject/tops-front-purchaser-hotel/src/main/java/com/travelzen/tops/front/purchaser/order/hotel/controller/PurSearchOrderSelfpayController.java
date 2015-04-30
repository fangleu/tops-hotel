package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.util.Arrays;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.travelzen.framework.logger.core.ri.RequestIdentityLogger;
import com.travelzen.tops.common.dict.order.hotel.HotelBookingType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderState;
import com.travelzen.tops.front.purchaser.common.controller.HotelBaseController;
import com.travelzen.tops.front.purchaser.order.hotel.util.PurHotelOrderOperationUtil;
import com.travelzen.tops.hotel.order.engine.client.proxy.HotelOrderEngineProxy;
import com.travelzen.tops.hotel.order.engine.model.search.request.TOrderSearchRequest;
import com.travelzen.tops.hotel.order.engine.model.search.response.TOrderSearchResponse;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;

@Controller
@RequestMapping("/pur/hotel/ordermanage")
public class PurSearchOrderSelfpayController extends HotelBaseController {

	private static Logger LOG = RequestIdentityLogger.getLogger(PurSearchOrderSelfpayController.class);

	@Resource(name="purHotelOrderOperationUtil")
	private PurHotelOrderOperationUtil purHotelOrderOperationUtil;
	
	@RequestMapping(value = "/selfpayOrders")
	public String gotoOrderSearchPage(Model model) {
		model.addAttribute("hotelOrderStates", HotelOrderState.selfpayStateValues());
		return "page/order/hotel/selfpay/orderSearch";
	}
	
	@RequestMapping(value = "/getAllResult")
	public String getAllResult(Model model, TOrderSearchRequest queryCriteria) {
		queryCriteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		queryCriteria.setBookingTypes(Arrays.asList(HotelBookingType.SELF_PAID.name()));
		try {
			TOrderSearchResponse result = HotelOrderEngineProxy.orderSearch(queryCriteria);
			if (result != null) {
				model.addAttribute("hotelOrderBos", result);
				model.addAttribute("batchOrderOperations", purHotelOrderOperationUtil.getBatchOperationsForTHotelOrder(result.getHotelOrders()));
				model.addAttribute("hotelOrderStates", HotelOrderState.selfpayStateValues());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		
		return "page/order/hotel/selfpay/orderSearchResult";
	}
}
