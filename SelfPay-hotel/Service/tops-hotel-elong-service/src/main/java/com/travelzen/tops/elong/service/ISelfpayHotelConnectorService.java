package com.travelzen.tops.elong.service;

import org.springframework.beans.factory.InitializingBean;

import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CancelOrderCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CancelOrderResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreateOrderCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreateOrderResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.CreditCardInfo;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.DetailHotel;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelDetailCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelList;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelListCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.OrderDetailResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.OrderIdsCondition;


public interface ISelfpayHotelConnectorService extends InitializingBean {
	/**
	 * 获取elong订单详情
	 * @author muyuansun
	 * @date 2014-10-28 下午9:44:28
	 * @return
	 */
	public OrderDetailResult elongHotelOrderDetail(OrderIdsCondition orderIdsCondition);
	
	/**
	 * 创建elong订单
	 * @author muyuansun
	 * @date 2014-10-28 下午10:01:31
	 * @param orderIdsCondition
	 * @return
	 */
	public CreateOrderResult elongHotelOrderCreate(CreateOrderCondition createOrderCondition);
	
	/**
	 * 取消elong订单
	 * @author muyuansun
	 * @date 2014-11-13 上午10:59:24
	 * @param cancelOrderCondition
	 * @return
	 */
	public CancelOrderResult elongHotelOrderCancel(CancelOrderCondition cancelOrderCondition);
	
	/**
	 * 获取elong酒店详情
	 * @author muyuansun
	 * @date 2014-10-28 下午11:54:44
	 * @param hotelDetailCondition
	 * @return
	 */
	public DetailHotel elongHotelDetail(HotelDetailCondition hotelDetailCondition);
	
	/**
	 * 获取elong酒店list
	 * @param hotelListCondition
	 * @return
	 */
	public HotelList elongHotelList(HotelListCondition hotelListCondition);
	
	/**
	 * 信用卡验证
	 * @author Loufanglei
	 * @data 2014-10-30 下午4:28:27 
	 */
	public CreditCardInfo creditcardValidate(String number);
	
}
