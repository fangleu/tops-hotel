package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.travelzen.framework.core.exception.BizException;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.StringUtil;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.framework.util.DateUtils;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderSource;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView.OperationSource;
import com.travelzen.tops.elong.request.model.SelfPaySearchCriteria;
import com.travelzen.tops.elong.service.ISelfpayHotelConnectorService;
import com.travelzen.tops.elong.service.ISelfpayHotelService;
import com.travelzen.tops.front.common.hotel.selfpay.business.SelfpayHotelSalesPolicyBusiness;
import com.travelzen.tops.front.purchaser.common.controller.HotelBaseController;
import com.travelzen.tops.front.purchaser.order.hotel.util.PurHotelOrderAuthUtil;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.DayPriceListDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelDetailDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.RatePlanItem;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.RoomInfo;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreditCardInfo;
import com.travelzen.tops.hotel.order.engine.client.proxy.HotelOrderEngineProxy;
import com.travelzen.tops.hotel.order.engine.model.component.THotelGuest;
import com.travelzen.tops.hotel.order.engine.model.component.THotelOrder;
import com.travelzen.tops.hotel.order.engine.model.create.request.TCreateOrderRequest;
import com.travelzen.tops.hotel.order.engine.model.create.request.TSelfPay;
import com.travelzen.tops.hotel.order.engine.model.create.response.TCreateOrderResponse;
import com.travelzen.tops.hotel.order.engine.model.detail.request.TOrderDetailRequest;
import com.travelzen.tops.hotel.order.engine.model.detail.response.TOrderDetailResponse;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.AddPrice;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.iservice.CustomerService;
import com.travelzen.tops.member.common.vo.Customer;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.core.po.gen.HotelGuest;
import com.travelzen.tops.order.hotel.common.log.service.HotelOrderLogEvent;
import com.travelzen.tops.order.hotel.common.service.HotelOrderAuthCenterFactory;
import com.travelzen.tops.order.hotel.common.service.factory.HotelOrderTransactionServiceFactory;
import com.travelzen.tops.order.hotel.creme.dto.HotelLogDTO;
import com.travelzen.tops.order.hotel.creme.service.query.ICremeHotelOrderQueryService;

@Controller
@RequestMapping("/pur/order/hotel/selfpay")
public class PurSelfpayHotelOrderController extends HotelBaseController {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Resource(name="selfpayHotel_Search")
	private ISelfpayHotelService selfpayHotelService;
	@Resource
	private CustomerService pCustomerService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private SelfpayHotelSalesPolicyBusiness selfpaySalesPolicyBusiness;
	@Resource
	private ISelfpayHotelConnectorService selfpayHotelConnectorService;
	@Resource(name="hotelOrderTransactionServiceFactory")
	private HotelOrderTransactionServiceFactory serviceFactory;
	@Resource(name="hotel_order_cremeQueryService")
	private ICremeHotelOrderQueryService hotelOrderQueryService;
	@Resource(name="hotelOrder_authCenterFactory")
	private HotelOrderAuthCenterFactory authCenterFactory;
	@Resource(name="purHotelOrderAuthUtil")
	private PurHotelOrderAuthUtil purHotelOrderAuthUtil;
	
	/**
	 * 建单页显示
	 * @author Loufanglei
	 * @data 2014-10-30 下午3:49:49 
	 */
	@RequestMapping(value="/orderView",method = RequestMethod.POST)
	public String orderView(SelfPaySearchCriteria criteria,Model model){
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		int roomNumber = StringUtil.isEmpty(criteria.getRoomNo()) ? 1 : Integer.valueOf(criteria.getRoomNo());
		Customer customer = TopsSecurityUtils.getUserFromSession().getCustomerData();
		HotelDetailDTO hotelDetailDTO = null;
		Map<String,AddPrice> salesPolicy = null;
		try {
			hotelDetailDTO = selfpayHotelService.queryHotelDetail(criteria.getHotelId(), criteria.getCheckInDate(), criteria.getCheckOutDate());
			salesPolicy = selfpaySalesPolicyBusiness.getSelfpayHotelSalesPolicy(customer, criteria.getCheckInDate(), criteria.getCheckOutDate());
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		
		if(ValidationUtil.isNull(hotelDetailDTO)){
			LOG.error("[酒店静态信息无法获取][酒店ID = {}][CheckInDate = {}][CheckOutDate = {}]",criteria.getHotelId(), criteria.getCheckInDate(), criteria.getCheckOutDate());
			throw BizException.instance("您预订的酒店详细信息不存在，请重新查询预订。");
		}
		
		if (ValidationUtil.isNull(salesPolicy)) {
			LOG.error("[酒店销售政策无法获取][Customer = {}][CheckInDate = {}][CheckOutDate = {}]",customer, criteria.getCheckInDate(), criteria.getCheckOutDate());
			throw BizException.instance("您预订的销售政策不存在，请重新查询预订。");
		}

		RatePlanItem theRatePlan = null;
		for (RoomInfo roomInfo : hotelDetailDTO.getRoomList()) {
			if (criteria.getRoomId().equals(roomInfo.getRoomId())) {
				model.addAttribute("roomInfo", roomInfo);
				for (RatePlanItem ratePlan : roomInfo.getRatePlanList()) {
					if (criteria.getRoomCatId().equals(ratePlan.getRoomTypeId())
							&& criteria.getBookingClassId().equals(String.valueOf(ratePlan.getRatePlanId()))) {
						DayPriceListDTO dayPriceList = ratePlan.getDayPriceList();
						double totalPrice = ratePlan.getTotalPrice() * roomNumber;
						double guaranteeAmount = 0;
						if("首晚".equals(ratePlan.getGuaranteeType())){
							guaranteeAmount = Integer.parseInt(dayPriceList.getDayPriceList().get(0).getPrice()) * roomNumber;
						}else if ("全额".equals(ratePlan.getGuaranteeType())){
							guaranteeAmount = totalPrice;
						}
						
						double commissionAmount = 0;
						for(int i=0; i<dayPriceList.getDayPriceList().size(); i++){
							String date = DateTimeUtil.format(DateTimeUtil.parseDate10(criteria.getCheckInDate()).plusDays(i),DateTimeUtil.DATE_PATTERN);
							double rate = salesPolicy.get(date).getMarkuprate();
							double price = Double.parseDouble((dayPriceList.getDayPriceList().get(i).getPrice()));
							commissionAmount += Math.floor(price * rate / 100) * roomNumber;
						}
						
						model.addAttribute("guaranteeAmount", guaranteeAmount);
						model.addAttribute("commissionAmount", commissionAmount);
						model.addAttribute("totalPrice", totalPrice);
						model.addAttribute("ratePlan", ratePlan);
						theRatePlan = ratePlan;
						break;
					}
				}
				break;
			}
		}
		if (ValidationUtil.isNull(theRatePlan)) {
			throw BizException.instance("您预订的酒店价格不存在，请重新查询预订。");
		}
		
		model.addAttribute("criteria", criteria);
		model.addAttribute("hotel", hotelDetailDTO);
		model.addAttribute("roomNumber", roomNumber);
		model.addAttribute("weekList",getWeekList(DateUtils.getJustDate(criteria.getCheckInDate())));
		return "page/order/hotel/selfpay/createOrder";
	}

	/**
	 * 提交订单
	 * @author Loufanglei
	 * @data 2014-10-30 下午3:50:17 
	 */
	@ResponseBody
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public String createOrder(Model model, HttpServletRequest request, TCreateOrderRequest createOrderRequest, Boolean isTimeGuarantee, Boolean isChooseTimeGuarantee)  {
		TSelfPay selfPay = createOrderRequest.getSelfPay();
		String ip = request.getRemoteAddr();// 返回发出请求的IP地址
		selfPay.setCustomerIPAddress(ip);
		createOrderRequest.setOrderType(OrderType.SELFPAY_NORMAL_HOTEL.name());
		createOrderRequest.setOrderSource(HotelOrderSource.purchaser.name());
		createOrderRequest.setCreatorName(TopsSecurityUtils.getUserFromSession().getCustomerData().getShortName());
		
		List <THotelGuest> hotelGuests = createOrderRequest.getHotelGuests();
		
		int sz = hotelGuests.size();
		List<THotelGuest> rightGuests = hotelGuests.subList(sz / 2, sz); 
		Iterator<THotelGuest> iterator = rightGuests.iterator();
		for (int i = 0; i < sz  / 2; ++i) {
			THotelGuest hotelGuest = (THotelGuest) iterator.next();
			if (null != hotelGuest.getName()) {
				if(hotelGuest.getName() != "")
					hotelGuests.get(i).setName(hotelGuests.get(i).getName() + "，" + hotelGuest.getName());
				iterator.remove();
			}
		}
		
		
		//设置最早/最晚到店时间
		setArriveTime(createOrderRequest, isTimeGuarantee, isChooseTimeGuarantee);
		
		//担保验证
		if (selfPay.isGuarantee) {  //需要担保
			CreditCardInfo creditCardInfo = selfpayHotelConnectorService.creditcardValidate(createOrderRequest.getCreditCard().getNumber());
			if (creditCardInfo.isIsValid()) {
				if (creditCardInfo.isIsNeedVerifyCode() && StringUtils.isBlank(createOrderRequest.getCreditCard().getCvv())) {
					return "{\"status\":\"notPass\",\"errorInfo\":\"cvv不能为空\"}";
				} else if (creditCardInfo.isIsNeedVerifyCode() == false) {
					 createOrderRequest.getCreditCard().setCvv(null);
				}
			} else {
				return "{\"status\":\"notPass\",\"errorInfo\":\"信用卡不可用\"}";
			}
		}else{
			createOrderRequest.setCreditCard(null);
		}

		//建单
		TCreateOrderResponse response = new TCreateOrderResponse();
		try {
			response = HotelOrderEngineProxy.createOrder(createOrderRequest);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		if (response.getResponseStatus().isSuccess()) {
			THotelOrder hotelOrder = response.getHotelOrder();
			applicationContext.publishEvent(new HotelOrderLogEvent(new HotelLogDTO(hotelOrder.getId(),
					hotelOrderQueryService.getHotelOrderBo(hotelOrder.getId(), HotelOrderTable.hotel_order),OperationSource.purchaser,HotelOrderOperationView.purchaser_create_order)));
			return "{\"status\":\"success\",\"hotelOrderId\":\""+hotelOrder.getId()+"\"}";
		} else {
			String errorText = response.getResponseStatus().getErrorText();
			errorText = StringUtils.substringAfter(errorText, "|");
			errorText = StringUtils.substringBefore(errorText, ", objects");
			return "{\"status\":\"field\",\"errorInfo\":\""+errorText+"\"}";
		}
	}
	
	@RequestMapping(value = "/orderResult")
	public String orderResult(Model model, String orderId, String isSuccess, String errorInfo) {
		try {
			if ("success".equals(isSuccess)) {
				TOrderDetailRequest request = new TOrderDetailRequest();
				request.setOrderId(orderId);
				request.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
				TOrderDetailResponse response = HotelOrderEngineProxy.orderDetail(request);
				THotelOrder tHotelOrder = response.getHotelOrder();
				
				model.addAttribute("orderId", orderId);
				model.addAttribute("hotelName", tHotelOrder.getHotelName());
				model.addAttribute("address", tHotelOrder.getHotelDetail().getHotelAddress());
				model.addAttribute("isGuarantee", tHotelOrder.getHotelSelfpayBookingInfo().isGuarantee);
				model.addAttribute("hotelOrder", tHotelOrder);
				return "page/order/hotel/selfpay/orderSuccess";
			} else {
				model.addAttribute("errorInfo", errorInfo);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return "page/order/hotel/selfpay/orderFailure";
	}

	//取消订单
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
	public String cancelOrder(String hotelOrderId, String cancelReason) throws Exception{
		HotelOrderBo hotelOrderBo = hotelOrderQueryService.getHotelOrderBo(hotelOrderId, HotelOrderTable.hotel_order);
		purHotelOrderAuthUtil.doesOrderBelongToCurrentUser(hotelOrderBo);
		authCenterFactory.getAuthIntercace(hotelOrderBo).canExcuteOperation(hotelOrderBo, null, HotelOrderOperationView.selfpay_purchaser_cancel_order);
		serviceFactory.getCancelService(OrderType.SELFPAY_NORMAL_HOTEL.name(), null).cancelOrder(hotelOrderId, cancelReason);	//取消订单
		applicationContext.publishEvent(new HotelOrderLogEvent(new HotelLogDTO(hotelOrderId,OrderType.SELFPAY_NORMAL_HOTEL.name(),cancelReason,OperationSource.purchaser,HotelOrderOperationView.selfpay_purchaser_cancel_order)));
		return "redirect:/pur/hotel/ordermanage/detail?hotelOrderId="+hotelOrderId;
	}

	/**
	 * 设置最早/最晚到店时间
	 * @author jianming.zhou
	 */
	private void setArriveTime(TCreateOrderRequest createOrderRequest, Boolean isTimeGuarantee, Boolean isChooseTimeGuarantee){
		try {
			TSelfPay selfPay = createOrderRequest.getSelfPay();
			SimpleDateFormat sdfDT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String checkinDateStr = createOrderRequest.getCheckinDate();
			String latestArrivalTimeStr = selfPay.getLatestArrivalTime();
			DateTime checkinDate = new DateTime(checkinDateStr);
			DateTime currentDateTimeCeil = getCeilDateTime(new DateTime().plusMinutes(30));  //当前时间加30分钟后再向上以：0,：30取整
			String currentDateTimeCeilStr = currentDateTimeCeil.toString("yyyy-MM-dd HH:mm");
			if (!isTimeGuarantee) {  //无时间担保
				if (!currentDateTimeCeil.isBefore(checkinDate)) {  //当天
					selfPay.setEarliestArrivalTime(currentDateTimeCeilStr);
				}else{
					selfPay.setEarliestArrivalTime(checkinDateStr + " 14:00");
				}
				selfPay.setLatestArrivalTime(checkinDateStr + " 23:59");
			} else {  //有时间担保
				DateTime latestArrivalTime = new DateTime(sdfDT.parse(latestArrivalTimeStr));
				if (currentDateTimeCeil.isAfter(new DateTime(sdfDT.parse(checkinDateStr + " 23:59")))) {  //当天23:30之后预订,因为有向上取整
					selfPay.setEarliestArrivalTime(checkinDateStr + " 23:59");
					selfPay.setLatestArrivalTime(checkinDate.plus(1) + " 6:00");
				} else if (!currentDateTimeCeil.isBefore(latestArrivalTime)){  //当天最晚到店时间之后预订
					selfPay.setEarliestArrivalTime(currentDateTimeCeilStr);
					selfPay.setLatestArrivalTime(checkinDateStr + " 23:59");
				} else if (!currentDateTimeCeil.isBefore(checkinDate)) {  //担保时间之前预订,且为当天
					selfPay.setEarliestArrivalTime(currentDateTimeCeilStr);
					if (isChooseTimeGuarantee) {  //选择时间担保
						selfPay.setLatestArrivalTime(checkinDateStr + " 23:59");
					} else {
						selfPay.setLatestArrivalTime(latestArrivalTimeStr);
					}
				} else {  //担保时间之前预订，且非当天
					selfPay.setEarliestArrivalTime(checkinDateStr + " 14:00");
					if (isChooseTimeGuarantee) {  //选择时间担保
						selfPay.setLatestArrivalTime(checkinDateStr + " 23:59");
					} else {
						selfPay.setLatestArrivalTime(latestArrivalTimeStr);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 时间以：00和：30向上取整
	 * @author jianming.zhou
	 * @param dateTime
	 * @return
	 */
	private DateTime getCeilDateTime(DateTime dateTime) {
		long millis = (long) (Math.ceil(dateTime.getMillis() / (1000.0 * 60 * 30)) * (1000.0 * 60 * 30));
		return new DateTime(millis);
	}
}
