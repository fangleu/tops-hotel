package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.CreditCard;

public interface ICreditCardDao extends IElongCoreMongoBaseDao<CreditCard, ObjectId> {
	
	public Map<String,String> findCreditCardAndName();
	
	public List<String> findCreditCardList(String lang);

}
