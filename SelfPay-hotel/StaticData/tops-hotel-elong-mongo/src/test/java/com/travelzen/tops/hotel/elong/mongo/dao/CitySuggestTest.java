package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CitySuggestTest extends BaseTest {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Resource
	private ICitySuggestDao citySuggestDao;
	
	@Test
	public void testFindAllKeywords(){
		Map<String, List<String>> cityMap = citySuggestDao.findAllKeywords();
		if (cityMap != null) {
			for (Map.Entry<String, List<String>> hotelCity : cityMap.entrySet()) { 
				LOG.info(hotelCity.getKey()+","+hotelCity.getValue().size());
			}
			
		}
	}
	
	@Test
	public void testCount(){
		Assert.assertNotNull(citySuggestDao);
		LOG.info("[CitySuggestCount = {}]",citySuggestDao.createQuery().countAll());
	}

}
