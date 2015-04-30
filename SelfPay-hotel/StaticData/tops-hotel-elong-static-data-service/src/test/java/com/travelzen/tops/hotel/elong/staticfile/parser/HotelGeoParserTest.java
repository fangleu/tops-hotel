package com.travelzen.tops.hotel.elong.staticfile.parser;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;
import com.travelzen.tops.hotel.elong.service.BaseTest;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.HotelGeoParser;

public class HotelGeoParserTest extends BaseTest {
	
	@Test
	public void testHotelGeoCNParser(){
		HotelGeoParser hotelGeoParser = new HotelGeoParser(getInputStream("hotelGeo/geo_cn.xml"));
		Assert.assertNotNull(hotelGeoParser.getHotelGeos());
		LOG.info("[HotelGeos = {}]",hotelGeoParser.getHotelGeos().size());
		Assert.assertNotNull(hotelGeoParser.getHotelGeos().get(0));
		HotelGeo hotelGeo = hotelGeoParser.getHotelGeos().get(0);
		LOG.info("[CityCode = {}]",hotelGeo.getCityCode());
		Assert.assertNotNull(hotelGeo.getDistricts());
		Assert.assertEquals(18, hotelGeo.getDistricts().size());
	}
		
}
