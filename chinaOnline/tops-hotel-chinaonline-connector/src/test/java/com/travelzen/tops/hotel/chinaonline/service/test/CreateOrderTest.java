package com.travelzen.tops.hotel.chinaonline.service.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.tops.hotel.chinaonline.bean.CreateOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.CreateOrderResponse;
import com.travelzen.tops.hotel.chinaonline.bean.base.GuestInformation;
import com.travelzen.tops.hotel.chinaonline.bean.base.Rates;
import com.travelzen.tops.hotel.chinaonline.service.IChinaOnlineSoapService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-hotel-chinaonline-service.xml"})
public class CreateOrderTest {
	
	@Resource(name ="chinaOnlineSoapService")
	private IChinaOnlineSoapService  service;
	
	@Test
	public void Test(){
	
		CreateOrderRequest createOrderRequest = new CreateOrderRequest();
		
		createOrderRequest.setOrderId("150128123622278");
		createOrderRequest.setHotelCode("ADHZJ");
		createOrderRequest.setRateCode("WHL05");
		createOrderRequest.setRoomCode("1BSTS");
		DateTime startDateTime = new DateTime("2015-02-04");
		DateTime endDateTime = new DateTime("2015-02-05");
		createOrderRequest.setStartDate(startDateTime);
		createOrderRequest.setEndDate(endDateTime);
		createOrderRequest.setRoomNumber(1);
		createOrderRequest.setEarliestCheckInTime("14:00");
		createOrderRequest.setLastestCheckInTime("12:00");
		List<Rates> rateList = new ArrayList<Rates>();
		Rates rates = new Rates();
		rates.setRateDate(startDateTime);
		rates.setRate(180.0);
		rateList.add(rates);
//		rates = new Rates();
//		rates.setRateDate(startDateTime.plusDays(1));
//		rates.setRate(780.0);
//		rateList.add(rates);
		createOrderRequest.setRate(rateList);
		
		List<GuestInformation> guestList = new ArrayList<GuestInformation>();
		GuestInformation guest = new GuestInformation();
		guest.setName("三十四");
//		guest.setAddressType("HOME");
//		guest.setAddressLine("大连市某某街");
//		guest.setCityName("大连市");
//		guest.setStateProv("辽宁");
//		guest.setCountryCode("CN");
//		guest.setPostCode("100035");
//		guest.setPhoneRole("PHONE");
//		guest.setPhoneType("MOBILE");
//		guest.setPhoneNumber("13600000000");
		guestList.add(guest);
		
		
		createOrderRequest.setGuestList(guestList);
		
		CreateOrderResponse createOrderResponse = new CreateOrderResponse();
		try {
			createOrderResponse = service.chinaonlineCreateOrder(createOrderRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	//  (COL)710914
		if(createOrderResponse != null){
			System.out.println(createOrderResponse.getResult());
		
		if(createOrderResponse.getHotelConfirmCode() != null){
		System.out.println(createOrderResponse.getHotelConfirmCode());
		}else{
			System.out.println(createOrderResponse.getErrorText());
			
		}
		}
	}
	
	

}
