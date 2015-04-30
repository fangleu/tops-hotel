package com.travelzen.tops.hotel.elong.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.staticdata.service.IAmenityUpdateService;

public class AmenityUpdateServiceTest extends BaseTest {
	
	@Resource
	private IAmenityUpdateService amenityUpdateService = null;
	
	@Test
	public void testAmenityUpdate() {
		Assert.assertNotNull(amenityUpdateService);
		amenityUpdateService.amenityUpdate();
	}

}
