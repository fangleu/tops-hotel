/**
 *
 */
package com.travelzen.tops.front.purchaser.order.hotel.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderSource;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView.OperationSource;
import com.travelzen.tops.finance.account.dto.SettlementBean;
import com.travelzen.tops.finance.account.service.IOrderPaymentService;
import com.travelzen.tops.front.purchaser.common.controller.HotelBaseController;
import com.travelzen.tops.front.purchaser.order.hotel.util.PurHotelOrderAuthUtil;
import com.travelzen.tops.front.purchaser.order.hotel.util.PurNormalHotelCreateOrderUtil;
import com.travelzen.tops.hotel.creme.search.client.CremeHotelServiceClient;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.enums.CustomerType;
import com.travelzen.tops.member.common.iservice.CustomerService;
import com.travelzen.tops.member.common.vo.Contact;
import com.travelzen.tops.member.common.vo.Customer;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.core.po.gen.HotelOrder;
import com.travelzen.tops.order.finance.callback.bean.AccountFrozenReq;
import com.travelzen.tops.order.hotel.common.log.service.HotelOrderLogEvent;
import com.travelzen.tops.order.hotel.common.log.service.IHotelOrderLogService;
import com.travelzen.tops.order.hotel.common.service.IHotelOrderAuthCenter;
import com.travelzen.tops.order.hotel.common.service.IHotelOrderFinanceService;
import com.travelzen.tops.order.hotel.common.service.availability.IHotelOrderAvailabilityService;
import com.travelzen.tops.order.hotel.common.service.factory.HotelOrderTransactionServiceFactory;
import com.travelzen.tops.order.hotel.common.service.state.IHotelOrderStateCenter;
import com.travelzen.tops.order.hotel.converter.HotelOrderBoConverter;
import com.travelzen.tops.order.hotel.creme.dto.CreateOrderCriteria;
import com.travelzen.tops.order.hotel.creme.dto.HotelLogDTO;
import com.travelzen.tops.order.hotel.creme.service.flow.ICremeHotelOrderWriteService;
import com.travelzen.tops.order.hotel.creme.service.query.ICremeHotelOrderQueryService;
import com.travelzen.tops.order.hotel.creme.vo.CremeHotelOrderVoConverterHandler;

/**
 *
 * @author wangmeng
 *
 */
@Controller
@RequestMapping("/pur/order/hotel")
public class PurNormalHotelOrderController extends HotelBaseController{
	@Resource
	private CustomerService pCustomerService;

	@Resource(name="hotelOrderTransactionServiceFactory")
	private HotelOrderTransactionServiceFactory serviceFactory;

	@Resource(name="hotelOrderAvailabilityService")
	private IHotelOrderAvailabilityService hotelOrderAvailabilityService;

	@Resource(name="hotel_order_cremeQueryService")
	private ICremeHotelOrderQueryService hotelOrderQueryService;

	@Resource(name="hotel_order_writeService")
	private ICremeHotelOrderWriteService hotelOrderWriteService;

	@Resource(name="hotel_order_financeService")
	private IHotelOrderFinanceService hotelOrderFinanceService;
	
	@Resource
	private ApplicationContext applicationContext;
	
	@Resource(name="hotelOrder_stateCenter")
	private IHotelOrderStateCenter hotelOrderStateCenter;
	
	@Resource(name="normalHotelOrderAuthCenter")
	private IHotelOrderAuthCenter normalHotelOrderAuthCenter;
	@Resource
	private IOrderPaymentService orderPaymentService;
	
	@Resource(name="hotel_order_LogService")
	private IHotelOrderLogService hotelOrderLogService;

	@Resource(name="purHotelOrderAuthUtil")
	private PurHotelOrderAuthUtil purHotelOrderAuthUtil;

	//平台建单跳转到创建订单页面
	@RequestMapping(value = "/orderView", method = RequestMethod.POST)
	public String orderView(Model model, CreateOrderCriteria criteria) throws Exception{
		Customer customer = TopsSecurityUtils.getUserFromSession().getCustomerData();
		Contact contact = null;
		if(ValidationUtil.isNotNull(customer)){
			if(customer.getType().equals(CustomerType.INDIVIDUAL)){//个人客户
				if(ValidationUtil.isNotNull(customer.getPersonalInfo())
						&& ValidationUtil.isNotNull(customer.getPersonalInfo().getPersonalContact())){
					contact = customer.getPersonalInfo().getPersonalContact();
				}
			}else if(customer.getType().equals(CustomerType.COMPANY)){//公司
				if(ValidationUtil.isNotNull(customer.getCompanyInfo())
						&& ValidationUtil.isNotNull(customer.getCompanyInfo().getBusinessContact())){
					contact = customer.getCompanyInfo().getBusinessContact();
				}
			}
		}
			
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		if(StringUtils.isNotBlank(criteria.getCustomerKey())){
			criteria.setCustomer(pCustomerService.getCustomerByKey(criteria.getCustomerKey()));
		}
		TPropertySearchBo propertySearchBo = CremeHotelServiceClient.getInstance().getPropertySearchBo(criteria.getTInventoryContext(), criteria.getDetailTPropertySearchCriteria());			//通调用thrift,取出预订的酒店信息(包含政策信息)

		//转换结果result到HotelOrderBo
		HotelOrderBo hotelOrderBo = new HotelOrderBo();
		HotelOrderBoConverter.convertTPropertySearchBo2HotelOrderBo(hotelOrderBo,null, propertySearchBo, criteria, OrderType.PURCHASER_NORMAL_HOTEL);
		super.checkBookingRequirement(propertySearchBo,criteria);
		normalHotelOrderAuthCenter.canExcuteOperation(hotelOrderBo,null, HotelOrderOperationView.purchaser_view_order);
		hotelOrderStateCenter.setHotelState(hotelOrderBo,null,HotelOrderOperationView.purchaser_view_order, true);

		model.addAttribute("hotelOrderVo", CremeHotelOrderVoConverterHandler.getInstance().bo2VoHandler(hotelOrderBo));
		model.addAttribute("defaultPhoto",propertySearchBo.getDefaultPhoto());
		model.addAttribute("criteria",criteria);
		model.addAttribute("contact", contact);

		return "page/order/hotel/normalOrderCreate";
	}


	//平台建单
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder(HotelOrderBo hotelOrderBo,Model model) throws Exception {
		CreateOrderCriteria criteria = PurNormalHotelCreateOrderUtil.buildCreateCriteria(hotelOrderBo);
		TPropertySearchBo propertySearchBo = CremeHotelServiceClient.getInstance().getPropertySearchBo(criteria.getTInventoryContext(), criteria.getDetailTPropertySearchCriteria());			//通调用thrift,取出预订的酒店信息(包含政策信息)
		PurNormalHotelCreateOrderUtil.checkOrderPriceBeforeCreate(hotelOrderBo,propertySearchBo,criteria);
		super.checkBookingRequirement(propertySearchBo,criteria);
		hotelOrderBo.setOrderType(OrderType.PURCHASER_NORMAL_HOTEL.name());
		hotelOrderBo.setOrderSource(HotelOrderSource.purchaser.name());
		String hotelOrderId = serviceFactory.getCreateService(hotelOrderBo).createOrder(hotelOrderBo);	//创建订单
		AccountFrozenReq accountFrozenReq = hotelOrderFinanceService.reqFrozenForPurchaser(hotelOrderId,OrderType.PURCHASER_NORMAL_HOTEL);	//插入收款记录,构建支付请求
		model.addAttribute("accountFrozenReq", accountFrozenReq);
		model.addAttribute("hotelOrderId", hotelOrderId);
		model.addAttribute("checksum", reqChecksum(accountFrozenReq));
		applicationContext.publishEvent(new HotelOrderLogEvent(new HotelLogDTO(hotelOrderId,hotelOrderBo,OperationSource.purchaser,HotelOrderOperationView.purchaser_create_order)));
		return "page/order/hotel/normalOrderSubmit";
	}


	//取消订单
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public String cancelOrder(String hotelOrderId, String cancelReason) throws Exception{
		purHotelOrderAuthUtil.doesOrderBelongToCurrentUser(hotelOrderQueryService.getHotelOrderBo(hotelOrderId,HotelOrderTable.hotel_order));
		serviceFactory.getCancelService(OrderType.PURCHASER_NORMAL_HOTEL.name(), null).cancelOrder(hotelOrderId, cancelReason);	//取消订单
		//订单类型，占时写死，采购商正常订单
		applicationContext.publishEvent(new HotelOrderLogEvent(new HotelLogDTO(hotelOrderId,OrderType.PURCHASER_NORMAL_HOTEL.name(),null,OperationSource.purchaser,HotelOrderOperationView.purchaser_cancel_order)));
		return "redirect:/pur/hotel/ordermanage/detail?hotelOrderId="+hotelOrderId;
	}

	//重新支付
	@RequestMapping(value = "/payOrder", method = RequestMethod.GET)
	public String gotoPayOrder(String hotelOrderId, Model model) throws Exception {
		HotelOrder hotelOrder = hotelOrderQueryService.getHotelOrderBo(hotelOrderId,HotelOrderTable.hotel_order);
		purHotelOrderAuthUtil.doesOrderBelongToCurrentUser(hotelOrder);
		normalHotelOrderAuthCenter.canExcuteOperation(hotelOrder, null, HotelOrderOperationView.purchaser_pay_order);
		hotelOrderAvailabilityService.beforePay(hotelOrderId);
		AccountFrozenReq accountFrozenReq = hotelOrderFinanceService.reqFrozenForPurchaser(hotelOrderId,OrderType.PURCHASER_NORMAL_HOTEL);	//插入收款记录,构建支付请求
		model.addAttribute("accountFrozenReq", accountFrozenReq);
		model.addAttribute("hotelOrderId", hotelOrderId);
		model.addAttribute("checksum", reqChecksum(accountFrozenReq));
//		applicationContext.publishEvent(new HotelOrderLogEvent(new HotelLogDTO(hotelOrderId,OperationSource.customer,HotelOrderOperationView.purchaser_pay_order)));
		return "page/order/hotel/normalOrderSubmit";
	}
	private String reqChecksum(AccountFrozenReq req) throws Exception {
		SettlementBean bean = new SettlementBean();
		bean.setCustomerId(req.getCustomerId());
		bean.setOrderId(req.getOrderId());
		bean.setSerialNumber(req.getSerialNumber());
		bean.setProductType(com.travelzen.tops.common.dict.finance.enums.ProductType.valueOf(req.getProductType()));
		bean.setOrderType(com.travelzen.tops.common.dict.finance.enums.OrderType.valueOf(req.getOrderType()));
		bean.setCheckoutAmount(req.getActualCheckoutAmount());
		bean.setActualCheckoutAmount(req.getActualCheckoutAmount());
		return orderPaymentService.genChecksum(bean);
	}
	@RequestMapping(value = "/updateHotelOrder", method = RequestMethod.POST)
	public String updateOrder(HotelOrderBo hotelOrderBo, Model model) throws Exception{
		hotelOrderBo.setOrderType(OrderType.PURCHASER_NORMAL_HOTEL.name());
		serviceFactory.getUpdateService(hotelOrderBo).updateHotelOrder(hotelOrderBo);
		hotelOrderLogService.generateHotelOrderLog(HotelOrderOperationView.purchaser_update_order.getDesc(), hotelOrderBo.getId(),OperationSource.purchaser,null,"0");
		return "redirect:/order/hotel/viewHotelOrderDetail?hotelOrderId="+hotelOrderBo.getId();
	}
	/**
	 * ajax获取订单价格详情
	 * @param model
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNormalOrderDiv", method = RequestMethod.POST)
	public String getNormalOrderDiv(Model model, CreateOrderCriteria criteria) throws Exception {

		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		if(StringUtils.isNotBlank(criteria.getCustomerKey())){
			criteria.setCustomer(pCustomerService.getCustomerByKey(criteria.getCustomerKey()));
		}
		TPropertySearchBo propertySearchBo = CremeHotelServiceClient.getInstance().getPropertySearchBo(criteria.getTInventoryContext(), criteria.getDetailTPropertySearchCriteria());			//通调用thrift,取出预订的酒店信息(包含政策信息)

		//转换结果result到HotelOrderBo
		HotelOrderBo hotelOrderBo = new HotelOrderBo();
		HotelOrderBoConverter.convertTPropertySearchBo2HotelOrderBo(hotelOrderBo,null, propertySearchBo, criteria,
				OrderType.PURCHASER_NORMAL_HOTEL);
		model.addAttribute("criteria",criteria);
		model.addAttribute("hotelOrderBo", hotelOrderBo);
		return "page/order/hotel/normalOrderDiv";
	}
	
}
