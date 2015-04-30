package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.Map;

import org.bson.types.ObjectId;

import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;

public interface IHotelIndexDao extends IElongCoreMongoBaseDao<HotelIndex, ObjectId>  {
	
	public Map<String,HotelIndex> findHotelIndexFromLocal(String lang);

}
