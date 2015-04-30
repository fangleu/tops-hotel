package com.travelzen.tops.front.purchaser.hotel.booking.selfpay.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.tops.elong.request.model.SelfPaySearchCriteria;
import com.travelzen.tops.elong.service.ISelfpayHotelService;
import com.travelzen.tops.hotel.elong.common.exception.CommonException;


@ContextConfiguration(locations={"classpath:spring/app-ctx-SelfpayHotelService.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SelfpayHotelServiceTest {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "selfpayHotel_Search")
	private ISelfpayHotelService selfpayHotelService = null;
	
	@Test
	public void testQueryHotels(){
		Assert.assertNotNull(selfpayHotelService);
		try {
			SelfPaySearchCriteria criteria = new SelfPaySearchCriteria();
			criteria.setCheckInDate("2014-10-14");
			criteria.setCheckOutDate("2014-10-18");
			criteria.setCityName("北京");
			criteria.setCityIsoCode("CN010");
			criteria.setCustomerKey("524d2798e4b0b65f682186fb");
			criteria.setPageNo(1);
			criteria.setPageSize(20);
			selfpayHotelService.queryHotels(criteria,String.valueOf(criteria.getPageSize()),String.valueOf(criteria.getPageNo()));
		} catch (CommonException e) {
			LOG.error(e.getMessage(),e);
		}
	}

}
