package com.travelzen.tops.hotel.elong.service;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.staticdata.service.ICityHotsuggestUpdateService;


public class CityHotsuggestUpdateServiceTest  extends BaseTest {

	
	@Resource
	private ICityHotsuggestUpdateService cityUpdateService = null;
	
	
	@Test
	public void testHotSuggestUpdate() {
		Assert.assertNotNull(cityUpdateService);
		cityUpdateService.cityHotSuggestUpdate();
	}

}
