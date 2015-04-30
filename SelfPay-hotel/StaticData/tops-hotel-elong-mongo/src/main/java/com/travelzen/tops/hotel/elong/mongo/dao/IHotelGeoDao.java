package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;

public interface IHotelGeoDao extends IElongCoreMongoBaseDao<HotelGeo, ObjectId> {
	
	public Map<String,String> findGeoCityCodeAndName(String lang);
	
	public List<String> findGeoCityCodeList(String lang);
	
	public String findCityNameByCityCode(String cityCode);

}
