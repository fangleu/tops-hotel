package com.travelzen.tops.hotel.elong.staticdata.service;

import java.util.Map;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;

public interface IHotelGeoUpdateService {
	
	public void hotelGeoStaticFileUpdate() throws CommonException;
	
	public Map<String,String> findGeoCityCodeAndName();

}
