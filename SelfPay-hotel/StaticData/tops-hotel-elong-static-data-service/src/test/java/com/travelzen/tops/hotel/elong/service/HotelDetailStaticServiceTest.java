package com.travelzen.tops.hotel.elong.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelDetailStaticService;

public class HotelDetailStaticServiceTest extends BaseTest{
	
	@Resource(name="elong_hotelDetial_static_service")
	private IHotelDetailStaticService hotelDetailStaticService = null;

	@Test
	public void testHotelData(){
		Hotel hotel = hotelDetailStaticService.getElongHotelByHotelId("00301196");
		System.out.println(hotel);
		
	}
}
