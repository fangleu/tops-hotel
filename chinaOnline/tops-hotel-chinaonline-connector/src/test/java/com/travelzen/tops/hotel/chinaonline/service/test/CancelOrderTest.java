package com.travelzen.tops.hotel.chinaonline.service.test;

import javax.annotation.Resource;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.tops.hotel.chinaonline.bean.CancelOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.CancelOrderResponse;
import com.travelzen.tops.hotel.chinaonline.service.IChinaOnlineSoapService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-hotel-chinaonline-service.xml"})
public class CancelOrderTest {

	@Resource(name ="chinaOnlineSoapService")
	private IChinaOnlineSoapService  service;
	
	@Test
	public void test() throws BadHanyuPinyinOutputFormatCombination{
		
		CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
		
		
		cancelOrderRequest.setComments("测试");
		cancelOrderRequest.setGuestName("李四");
		cancelOrderRequest.setHotelCode("CETSH");
		cancelOrderRequest.setHotelConfirmCode("(COL)711174,(COL)711175");
		
		CancelOrderResponse response =service.cancelOrder(cancelOrderRequest);
		
		System.out.println(response.getResult());
		if(!response.getResult())
		System.out.println(response.getErrorText());
		
		
	}
	
	
}
