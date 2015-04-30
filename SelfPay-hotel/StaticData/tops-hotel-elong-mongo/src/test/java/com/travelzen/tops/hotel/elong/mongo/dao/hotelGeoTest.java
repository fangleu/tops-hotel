package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;



public class hotelGeoTest extends BaseTest {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IHotelGeoDao hotelGeoDao;
	@Resource
	private ICreditCardDao iCreditCardDao;
	
	@Test
	public void testFindCityNameByCityCode(){
		System.out.println(hotelGeoDao.findCityNameByCityCode("0101"));
	}
	
	
	@Test
	public void testCreate(){
		
		
//		HotelGeo hotelGeo = hotelGeoDao.createQuery().field("cityCode").equal("0101").get();
//		
//		Map<String, String> cityMap = hotelGeoDao.findGeoCityCodeAndName(null);
		
		Map<String, String> cardList = iCreditCardDao.findCreditCardAndName();
		
		if (cardList != null) {
			for (Map.Entry<String, String> hotelCity : cardList.entrySet()) { 
				LOG.info(hotelCity.getKey()+","+hotelCity.getValue());
			}
			
		}
		
		
	}

}
