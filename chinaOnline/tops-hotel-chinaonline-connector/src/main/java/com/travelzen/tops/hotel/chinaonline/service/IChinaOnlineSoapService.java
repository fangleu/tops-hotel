package com.travelzen.tops.hotel.chinaonline.service;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.travelzen.tops.hotel.chinaonline.bean.CancelOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.CancelOrderResponse;
import com.travelzen.tops.hotel.chinaonline.bean.CreateOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.CreateOrderResponse;
import com.travelzen.tops.hotel.chinaonline.bean.QueryRoomRequest;
import com.travelzen.tops.hotel.chinaonline.bean.QueryRoomResponse;

public interface IChinaOnlineSoapService {

	
	/**
	 * 建单前验价
	 * @author Loufanglei
	 * @throws Exception
	 * @data 2015-1-20 上午10:34:23 
	 */
	public QueryRoomResponse chinaonlineAvailability(QueryRoomRequest  request) throws Exception;
	
	/**
	 * 创建订单
	 * @author Loufanglei
	 * @throws Exception 
	 * @data 2015-1-20 上午10:35:23 
	 */
	public CreateOrderResponse chinaonlineCreateOrder(CreateOrderRequest  request) throws Exception;
	
	/**
	 * 取消订单
	 * @author Loufanglei
	 * @throws BadHanyuPinyinOutputFormatCombination
	 * @data 2015-1-20 上午10:35:41
	 */
	public CancelOrderResponse cancelOrder(CancelOrderRequest Request);
	
}