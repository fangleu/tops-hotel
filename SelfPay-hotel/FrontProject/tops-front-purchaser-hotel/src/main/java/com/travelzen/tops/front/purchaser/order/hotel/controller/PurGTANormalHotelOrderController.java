package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.framework.logger.core.ri.RequestIdentityLogger;
import com.travelzen.framework.util.LogFormatter;
import com.travelzen.framework.util.LogFormatter.Project;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderSource;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView.OperationSource;
import com.travelzen.tops.finance.account.dto.SettlementBean;
import com.travelzen.tops.finance.account.service.IOrderPaymentService;
import com.travelzen.tops.front.purchaser.hotel.vo.GTAHotelOrderVo;
import com.travelzen.tops.front.purchaser.order.hotel.util.PurHotelOrderAuthUtil;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.hotel.thrift.creme.common.TRoomCatBo;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.enums.CustomerType;
import com.travelzen.tops.member.common.iservice.CustomerService;
import com.travelzen.tops.member.common.vo.Contact;
import com.travelzen.tops.member.common.vo.Customer;
import com.travelzen.tops.order.core.po.gen.HotelOrder;
import com.travelzen.tops.order.finance.callback.bean.AccountFrozenReq;
import com.travelzen.tops.order.hotel.common.log.service.HotelOrderLogEvent;
import com.travelzen.tops.order.hotel.common.service.IHotelOrderAuthCenter;
import com.travelzen.tops.order.hotel.common.service.IHotelOrderFinanceService;
import com.travelzen.tops.order.hotel.common.service.availability.IHotelOrderAvailabilityService;
import com.travelzen.tops.order.hotel.creme.dto.HotelLogDTO;
import com.travelzen.tops.order.hotel.gta.dto.GTAOrderCriteria;
import com.travelzen.tops.order.hotel.gta.service.IGTAHotelOrderClientService;
import com.travelzen.tops.order.hotel.gta.service.IGTAHotelOrderQueryService;
import com.travelzen.tops.order.hotel.gta.service.IGTAHotelOrderTransactionService;

@Controller
@RequestMapping("/pur/order/hotel/international/normal")
public class PurGTANormalHotelOrderController{
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
	}

	private static Logger LOGGER =  RequestIdentityLogger.getLogger(PurGTANormalHotelOrderController.class);
    
	@Resource
	private CustomerService pCustomerService;
    
	@Resource(name="hotel_order_financeService")
	private IHotelOrderFinanceService hotelOrderFinanceService;
	
	@Resource(name="hotel_order_gtaTransactionService")
	private IGTAHotelOrderTransactionService hotelOrderGTATransactionService;
	
	@Resource(name="hotel_order_gtaClientService")
	private IGTAHotelOrderClientService hotelOrderGTAClientService;
	
	 @Resource(name = "gtaNormalHotelOrderAuthCenter")
	private IHotelOrderAuthCenter hotelOrderAuthCenter;
	 
    @Resource(name = "hotel_order_gtaQueryService")
    private IGTAHotelOrderQueryService hotelOrderGTAQueryService;

	@Resource(name="hotelOrderAvailabilityService")
	private IHotelOrderAvailabilityService hotelOrderAvailabilityService;

    @Resource
    private IOrderPaymentService orderPaymentService;
    
    @Resource
	private ApplicationContext applicationContext;

	@Resource(name="purHotelOrderAuthUtil")
	private PurHotelOrderAuthUtil purHotelOrderAuthUtil;
	
	/**跳转到建单页面*/
	@RequestMapping(value = "/orderView")
    public String orderView(Model model, GTAOrderCriteria criteria) throws Exception {
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
//		AccountBean account = null;
		if(StringUtils.isNotBlank(criteria.getCustomerKey())){
			criteria.setCustomer(pCustomerService.getCustomerByKey(criteria.getCustomerKey()));
//			account = financeAmountService.searchAccount(criteria.getCustomerKey());
		}
		//get gta hotel
		TPropertySearchBo gtaHotel = hotelOrderGTAClientService.getGTAPropertySearchBo(criteria);
		model.addAttribute("hotel", gtaHotel);
		//房型
		for (TRoomCatBo roomCat : gtaHotel.getRoomCats()) {
			if (roomCat.getId().equals(criteria.getRoomCatId())) {
				model.addAttribute("roomCat", roomCat);
				model.addAttribute("bookingClass", roomCat.getBookingClass().get(0));
				break;
			}
		}
		model.addAttribute("criteria", criteria);
		model.addAttribute("contact", contact);
        return "page/order/hotel/gta/orderView";
    }
	
	/**创建订单*/
	@RequestMapping(value = "create")
	public String createOrder(Model model, GTAHotelOrderVo gtaVo) throws Exception {
		LOGGER.info(LogFormatter.enterFormatWithBeans(Project.tops_front_purchaser, "createOrder", null, gtaVo));
		gtaVo.setOrderSource(HotelOrderSource.purchaser.name());
		String hotelOrderId = hotelOrderGTATransactionService.createOrder(gtaVo);
		LOGGER.info(LogFormatter.format(Project.tops_front_purchaser, "createOrder", hotelOrderId, "requet frozen..."));
		hotelOrderAuthCenter.canExcuteOperation(gtaVo, null, HotelOrderOperationView.gta_purchaser_pay_order);
		AccountFrozenReq accountFrozenReq = hotelOrderFinanceService.reqFrozenForPurchaser(hotelOrderId,OrderType.GTA_NORMAL_HOTEL);	//插入收款记录,构建支付请求
		model.addAttribute("accountFrozenReq", accountFrozenReq);
		model.addAttribute("hotelOrderId", gtaVo.getId());
		model.addAttribute("checksum", reqChecksum(accountFrozenReq));
		
		/**监听者模式,记录日志*/
		applicationContext.publishEvent(new HotelOrderLogEvent(new HotelLogDTO(hotelOrderId,gtaVo,OperationSource.purchaser,HotelOrderOperationView.purchaser_create_order)));
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
	
	//重新支付
	@RequestMapping(value = "/payOrder", method = RequestMethod.GET)
	public String gotoPayOrder(String hotelOrderId, Model model) throws Exception {
		LOGGER.info(LogFormatter.enterFormat(Project.tops_front_purchaser, "gotoPayOrder", hotelOrderId,"repay gta order"));
		HotelOrder order = hotelOrderGTAQueryService.getById(hotelOrderId, HotelOrderTable.hotel_order);
		purHotelOrderAuthUtil.doesOrderBelongToCurrentUser(order);
		hotelOrderAuthCenter.canExcuteOperation(order, null, HotelOrderOperationView.gta_purchaser_pay_order);
		hotelOrderAvailabilityService.beforePay(hotelOrderId);
		AccountFrozenReq accountFrozenReq = hotelOrderFinanceService.reqFrozenForPurchaser(hotelOrderId,OrderType.GTA_NORMAL_HOTEL);	//插入收款记录,构建支付请求
		model.addAttribute("accountFrozenReq", accountFrozenReq);
		model.addAttribute("hotelOrderId", hotelOrderId);
		model.addAttribute("checksum", reqChecksum(accountFrozenReq));
		return "page/order/hotel/normalOrderSubmit";
	}
	
	//取消订单
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public String cancelOrder(String hotelOrderId, String cancelReason) throws Exception{
		purHotelOrderAuthUtil.doesOrderBelongToCurrentUser(hotelOrderGTAQueryService.getById(hotelOrderId, HotelOrderTable.hotel_order));
		hotelOrderGTATransactionService.cancelOrder(hotelOrderId, cancelReason);	//取消订单
		applicationContext.publishEvent(new HotelOrderLogEvent(new HotelLogDTO(hotelOrderId,OrderType.GTA_NORMAL_HOTEL.name(),null,OperationSource.purchaser,HotelOrderOperationView.gta_purchaser_cancel_order)));
		return "redirect:/pur/hotel/ordermanage/detail?hotelOrderId="+hotelOrderId;
	}
   
}
