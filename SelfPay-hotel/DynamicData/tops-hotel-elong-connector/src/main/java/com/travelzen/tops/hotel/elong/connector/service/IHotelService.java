package com.travelzen.tops.hotel.elong.connector.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IHotelService {
	
	public void invokeElongInterfaceWithCache(HttpServletRequest request,HttpServletResponse response,boolean gzipEnable);

}
