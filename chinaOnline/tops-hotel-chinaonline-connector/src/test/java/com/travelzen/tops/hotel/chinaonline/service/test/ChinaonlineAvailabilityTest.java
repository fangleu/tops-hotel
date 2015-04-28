package com.travelzen.tops.hotel.chinaonline.service.test;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.tops.hotel.chinaonline.bean.QueryRoomRequest;
import com.travelzen.tops.hotel.chinaonline.bean.QueryRoomResponse;
import com.travelzen.tops.hotel.chinaonline.service.IChinaOnlineSoapService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-hotel-chinaonline-service.xml"})
public class ChinaonlineAvailabilityTest {
	
	@Resource(name ="chinaOnlineSoapService")
	private IChinaOnlineSoapService  service;
	
	@Test
	public void testChinaonlineAvailability(){
		QueryRoomRequest request = new QueryRoomRequest();
		DateTime startDate = new DateTime("2015-01-29");
		DateTime endDate = new DateTime("2015-01-30");
		request.setStartDate(startDate);
		request.setEndDate(endDate);
		request.setRoomNumber(1);
		request.setGuestNum(1);
		request.setHotelCode("ADHZJ");
		request.setRoomCode("1BSTS");
		request.setRateCode("WHL04");
		
		QueryRoomResponse response = null;
			try {
				response = service.chinaonlineAvailability(request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for(int i=0; i<response.getPriceList().size(); i++){
			DateTime keydate = new DateTime(startDate.getMillis()+i*1000*3600*24);
			System.out.print("---"+response.getPriceList().get(keydate));
		}
		System.out.print("\n");
	
	}
	
}
