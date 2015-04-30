package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Amenity;

public interface IAmenityDao extends IElongCoreMongoBaseDao<Amenity, ObjectId> {
	
	public Amenity findAmenityByAmenityId(String amenityId);
	
	public List<Amenity> findAmenityByAmenityIds(String[] amenityIds);
}
