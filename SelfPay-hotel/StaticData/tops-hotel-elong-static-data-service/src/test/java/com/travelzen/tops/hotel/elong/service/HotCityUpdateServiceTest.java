package com.travelzen.tops.hotel.elong.service;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.staticdata.service.IHotCityUpdateService;


public class HotCityUpdateServiceTest  extends BaseTest {

	
	@Resource
	private IHotCityUpdateService hotCityUpdateService = null;
	
	
	@Test
	public void testHotSuggestUpdate() {
		Assert.assertNotNull(hotCityUpdateService);
		hotCityUpdateService.hotCityUpdate();
	}

}
