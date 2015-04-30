package com.travelzen.tops.hotel.elong.mongo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.travelzen.tops.hotel.elong.mongo.dao.IAmenityDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Amenity;

@Repository("amenityDao")
public class AmenityDao extends ElongMongoBaseDao<Amenity, ObjectId> implements IAmenityDao {

	@Override
	public Amenity findAmenityByAmenityId(String amenityId) {
		return this.createQuery().field("amenityId").equal(Integer.valueOf(amenityId)).get();
	}

	@Override
	public List<Amenity> findAmenityByAmenityIds(String[] amenityIds) {
		List<Amenity> result = null;
		if(amenityIds == null){
			return result;
		}
		result = new ArrayList<>();
		for(String amenityId:amenityIds){
			Amenity amenity = this.createQuery().field("amenityId").equal(Integer.valueOf(amenityId)).get();
			if(amenity == null){
				continue;
			}
			result.add(amenity);
		}
		return result;
	}

}
