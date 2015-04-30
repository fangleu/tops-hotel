package com.travelzen.tops.hotel.order.schedule.selfpay.staticfile.updater;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelUpdateService;
import com.travelzen.tops.hotel.order.schedule.selfpay.staticfile.updater.ElongStaticFileUpdateScheduler;

@ContextConfiguration(locations={"classpath:spring/applicationContext-hotel-order-schedule.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ElongStaticFileUpdateSchedulerTest {
	
	@Resource
	private ElongStaticFileUpdateScheduler elongStaticFileUpdateScheduler = null;
	
	@Resource
	private IHotelUpdateService hotelUpdateService = null;
	
	@Test
	public void testHotelIndexAndDetailsUpdater(){
		Assert.assertNotNull(elongStaticFileUpdateScheduler);
		elongStaticFileUpdateScheduler.hotelIndexAndDetailsUpdater();
	}
	
	@Test
	public void testFindHotelListFromElong(){
		Assert.assertNotNull(hotelUpdateService);
		try {
			List<HotelIndex> hotelIndexs = hotelUpdateService.findHotelListFromElong();
			System.out.println(hotelIndexs.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
