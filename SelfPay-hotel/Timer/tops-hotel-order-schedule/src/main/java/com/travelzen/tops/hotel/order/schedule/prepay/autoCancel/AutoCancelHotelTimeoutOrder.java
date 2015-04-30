package com.travelzen.tops.hotel.order.schedule.prepay.autoCancel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.util.LogFormatter;
import com.travelzen.framework.util.LogFormatter.Project;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelBookingType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderSource;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderOperationView.OperationSource;
import com.travelzen.tops.order.core.dao.IHotelOrderDao;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.core.po.gen.HotelOrder;
import com.travelzen.tops.order.core.po.gen.HotelOrderExample;
import com.travelzen.tops.order.hotel.common.log.service.IHotelOrderLogService;
import com.travelzen.tops.order.hotel.common.service.IHotelOrderMessageAsynProxyService;
import com.travelzen.tops.order.hotel.common.service.factory.HotelOrderTransactionServiceFactory;
import com.travelzen.tops.order.hotel.creme.dto.HotelMessageDTO;
import com.travelzen.tops.order.hotel.creme.dto.HotelMessageDTO.MessageType;

/**
 * 自动取消酒店支付时间超时的订单
 * 
 * @author Adam
 * 
 */
@Service
public class AutoCancelHotelTimeoutOrder {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="hotel_order_messageAsynProxyService")
	private IHotelOrderMessageAsynProxyService hotelOrderMessageAsynProxyService;

	@Resource
	private IHotelOrderDao hotelOrderDao;

	@Resource(name="hotelOrderTransactionServiceFactory")
	private HotelOrderTransactionServiceFactory serviceFactory;

	@Resource(name = "hotel_order_LogService")
	private IHotelOrderLogService hotelOrderLogService;

	private static final List<String> allowedOrderTypes = new ArrayList<>();
	private static final List<String> states = new ArrayList<>();
	private static final List<String> bookingTypes = new ArrayList<>();
	static {
		allowedOrderTypes.add(OrderType.PURCHASER_NORMAL_HOTEL.name());
		allowedOrderTypes.add(OrderType.GTA_NORMAL_HOTEL.name());
		states.add("init_not_pay");
		states.add("waiting_for_pay");
		bookingTypes.add(HotelBookingType.PRE_PAID.name());
		bookingTypes.add(HotelBookingType.GTA_PRE_PAID.name());
	}

	@Scheduled(fixedDelay= 5 * DateUtils.MILLIS_PER_MINUTE)
	public void autoCancelAndNotification() {
		logger.info(LogFormatter.enterFormat(Project.tops_front_purchaser, "autoCancelAndNotification", "START"));
		Date now = new Date();
		Date messageNotificatioinStartPeriod = DateTimeUtil.addMin(now, -35).toDate();//35
		Date messageNotificatioinendPeriod = DateTimeUtil.addMin(now, -30).toDate();//30
		Date allowedEarliestIssueDate = DateTimeUtil.addMin(now, -60).toDate();//60
		try{
			HotelOrderExample example = new HotelOrderExample();
			example.createCriteria().andOrderTypeIn(allowedOrderTypes).andStateIn(states).andBookingTypeIn(bookingTypes).andCreateDateLessThan(messageNotificatioinendPeriod);
			List<HotelOrder> orderList = hotelOrderDao.selectByExample(example);
	
			if(orderList != null && orderList.size() > 0){
				for(HotelOrder order : orderList){
					try{
						Date createDate = order.getCreateDate();
						if(createDate!=null){
							HotelMessageDTO messageDto = new HotelMessageDTO();
							HotelMessageDTO.OrderMessage orderMessage = messageDto.new OrderMessage();
							messageDto.setMessageType(MessageType.orderMessage);
							orderMessage.setHotelOrderBo(new HotelOrderBo(order));
							if((createDate.getTime() >= messageNotificatioinStartPeriod.getTime()) && (createDate.getTime() < messageNotificatioinendPeriod.getTime())){//timeOut notitication
								orderMessage.setOperation(HotelOrderOperationView.system_send_message);
								orderMessage.setSuccess(true);
								logger.info(LogFormatter.format(Project.tops_front_purchaser, "autoCancelAndNotification->timeout", order.getId(),"timeout notification for purchaser","CreatorName:"+order.getCreatorName()));
							}else if(createDate.getTime() < allowedEarliestIssueDate.getTime()){
								serviceFactory.getCancelService(null, order).cancelOrder(order.getId(), "一个小时未支付，系统自动取消订单。");
								orderMessage.setOperation(HotelOrderOperationView.purchaser_cancel_order);
								orderMessage.setSuccess(true);
								hotelOrderLogService.generateHotelOrderLog("支付超时", order.getId(),OperationSource.other, null,"0");
								logger.info(LogFormatter.format(Project.tops_front_purchaser, "autoCancelAndNotification->cancel", order.getId(),"cancel hotel order booked from purchaser","CreatorName:"+order.getCreatorName(),"smsReceiver:"));
							}
							messageDto.setOrderMessage(orderMessage);
							if (false == HotelOrderSource.isFitOrder(order.getOrderSource())) {	//自由行酒店不发消息
								hotelOrderMessageAsynProxyService.sendMessage(messageDto);
							}
						}
					}catch(Exception e){
						logger.error(LogFormatter.errorFormat(Project.tops_front_purchaser, "autoCancelAndNotification_for_single_order", "orderId:"+order.getId()+"time:"+now.toString(),"AUTO_CANCEL_FAILED","cancel hotel order failed for one order",e),e);
					}
				}
			}
		}catch(Exception e){
			logger.error(LogFormatter.errorFormat(Project.tops_front_purchaser, "autoCancelAndNotification", "time:"+now.toString(),"AUTO_CANCEL_FAILED","cancel hotel order failed booked from purchaser",e),e);
		}
	}
}
