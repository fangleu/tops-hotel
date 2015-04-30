package com.travelzen.tops.hotel.elong.mongo.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.jmkgreen.morphia.Datastore;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelIndexDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;

@Repository("hotelIndexDao")
public class HotelIndexDao extends ElongMongoBaseDao<HotelIndex, ObjectId> implements IHotelIndexDao {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 从本地mongo数据库获得酒店信息列表
	 * 目前mongo中存储的酒店信息都是可用的（status = 0）
	 * @author muyuansun
	 * @date 2014-1-8 下午11:02:24
	 * @return
	 */
	public Map<String,HotelIndex> findHotelIndexFromLocal(String lang){
		Map<String,HotelIndex> result = null;
		if(lang == null || lang.length() <= 0){
			lang = "CN";
		}
		result = new HashMap<>();
		Datastore datastore = this.createDatastore();
		//默认中文的
		Iterable<HotelIndex> hotelIndexQuery = datastore.createQuery(HotelIndex.class).retrievedFields(true, "hotelId","updatedTime");
		Iterator<HotelIndex> hotelIndexIterator = hotelIndexQuery.iterator();
		while(hotelIndexIterator.hasNext()){
			HotelIndex hotelIndex = hotelIndexIterator.next();
			result.put(hotelIndex.getHotelId(), hotelIndex);
		}
		if(LOG.isDebugEnabled()){
			if(result != null || result.size() <= 0){
				LOG.debug("[LOCAL_MONGO_HOTEL_COUNT = {}]",result.size());
			}
		}
		return result;
	}

}
