package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelzen.framework.core.common.ReturnCode;
import com.travelzen.framework.core.exception.BizException;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView.OperationSource;
import com.travelzen.tops.front.purchaser.common.controller.HotelBaseController;
import com.travelzen.tops.hotel.creme.search.client.CremeHotelServiceClient;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.member.common.iservice.CustomerService;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.finance.callback.bean.AccountFrozenReq;
import com.travelzen.tops.order.hotel.common.log.service.IHotelOrderLogService;
import com.travelzen.tops.order.hotel.common.service.IHotelOrderFinanceService;
import com.travelzen.tops.order.hotel.common.service.factory.HotelOrderTransactionServiceFactory;
import com.travelzen.tops.order.hotel.converter.HotelOrderBoConverter;
import com.travelzen.tops.order.hotel.creme.dto.CreateOrderCriteria;
import com.travelzen.tops.order.hotel.creme.service.flow.ICremeHotelOrderWriteService;
import com.travelzen.tops.order.hotel.creme.service.query.ICremeHotelOrderQueryService;
import com.travelzen.tops.order.hotel.creme.util.CreateEndorseHotelOrderInfoProcessor;
@Controller
@RequestMapping("/pur/order/hotel/endorse")
public class PurEndorseHotelOrderController extends HotelBaseController {
	@Resource
	private CustomerService pCustomerService;

	@Resource(name = "hotel_order_writeService")
	private ICremeHotelOrderWriteService hotelOrderWriteService;

	@Resource(name = "hotel_order_cremeQueryService")
	private ICremeHotelOrderQueryService hotelOrderQueryService;
	
	@Resource(name="hotelOrderTransactionServiceFactory")
	private HotelOrderTransactionServiceFactory serviceFactory;
	
	@Resource(name="hotel_order_financeService")
	private IHotelOrderFinanceService hotelOrderFinanceService;

	@Resource(name="hotel_order_LogService")
	private IHotelOrderLogService hotelOrderLogService;

	// 跳转到变更单创建页面
	@RequestMapping(value = "/orderView")
	public String orderView(Model model, CreateOrderCriteria criteria) throws Exception {
		if (ValidationUtil.isNull(criteria.getOrigOrderId())) {
			throw BizException.instance(ReturnCode.HOTEL_ORDER_ORI_ORDER_ERROR, "origOrderId不能为空");
		}
		if (StringUtils.isNotBlank(criteria.getCustomerKey())) {
			criteria.setCustomer(pCustomerService.getCustomerByKey(criteria.getCustomerKey()));
		}
		// 通调用thrift,取出预订的酒店信息(包含政策信息)
		TPropertySearchBo propertySearchBo = CremeHotelServiceClient.getInstance().getPropertySearchBo(criteria.getTInventoryContext(), criteria.getDetailTPropertySearchCriteria());

		// 转换结果result到HotelOrderBo
		HotelOrderBo hotelOrderBo = new HotelOrderBo();

		// 获取原订单信息
		HotelOrderBo origOrderBo = hotelOrderQueryService.getHotelOrderBo(criteria.getOrigOrderId(), HotelOrderTable.hotel_added_service_detail, HotelOrderTable.hotel_price_detail,
				HotelOrderTable.hotel_detail, HotelOrderTable.hotel_guest);
		List<HotelOrderBo> origHotelOrderList = hotelOrderQueryService.getHotelOrderByOriginalOrderId(origOrderBo.getOriginalOrderId());
		CreateEndorseHotelOrderInfoProcessor.computedHotelEndorseCost(hotelOrderBo, origOrderBo, origHotelOrderList);

		HotelOrderBoConverter.convertTPropertySearchBo2HotelOrderBo(hotelOrderBo, origOrderBo, propertySearchBo, criteria,OrderType.ENDORSE_HOTEL);
		return "opPage/order/hotel/onlineHotelOrderView";
	}

	// 创建变更单
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder(HotelOrderBo hotelOrderBo, Model model) throws Exception {
		hotelOrderBo.setOrderType(OrderType.PURCHASER_ENDORSE_HOTEL.name());
		String hotelOrderId = serviceFactory.getCreateService(hotelOrderBo).createOrder(hotelOrderBo);
		// 维护收款记录,跳转到冻结账户
		AccountFrozenReq accountFrozenReq = hotelOrderFinanceService.reqFrozenForPurchaser(hotelOrderId, OrderType.PURCHASER_ENDORSE_HOTEL);
		model.addAttribute("accountFrozenReq", accountFrozenReq);
		hotelOrderLogService.generateHotelOrderLog(HotelOrderOperationView.purchaser_create_endorse_order.getDesc(), hotelOrderBo.getId(), OperationSource.purchaser, null, "0");
		return "/opPage/order/jumpToFinanceFrozenPage";

	}
}
