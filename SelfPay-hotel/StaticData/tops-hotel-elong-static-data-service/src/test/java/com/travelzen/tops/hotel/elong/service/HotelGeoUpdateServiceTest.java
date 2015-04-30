package com.travelzen.tops.hotel.elong.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelGeoUpdateService;

public class HotelGeoUpdateServiceTest extends BaseTest  {
	
	@Resource
	private IHotelGeoUpdateService hotelGeoUpdateService = null;
	
	
	@Test
	public void testHotelGeoStaticFileUpdate() throws CommonException {
		Assert.assertNotNull(hotelGeoUpdateService);
		hotelGeoUpdateService.hotelGeoStaticFileUpdate();
	}

}
