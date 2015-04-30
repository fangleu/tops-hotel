package com.travelzen.tops.hotel.elong.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;

public class HotelStaticFileDownloadServiceTest extends BaseTest {
	
	@Resource
	private IHotelStaticFileDownloadService hotelStaticFileDownloadService = null;
	
	@Test
	public void testDownloadHotelList(){
		Assert.assertNotNull(hotelStaticFileDownloadService);
	}
}
