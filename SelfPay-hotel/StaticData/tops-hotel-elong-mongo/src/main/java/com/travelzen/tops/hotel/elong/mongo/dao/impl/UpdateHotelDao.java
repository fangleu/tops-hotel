package com.travelzen.tops.hotel.elong.mongo.dao.impl;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.travelzen.tops.hotel.elong.mongo.dao.IUpdateHotelDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.statistic.UpdateHotel;

@Repository("updateHotelDao")
public class UpdateHotelDao extends ElongMongoBaseDao<UpdateHotel, ObjectId> implements IUpdateHotelDao {

}
