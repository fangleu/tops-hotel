package com.travelzen.tops.hotel.elong.mongo.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelGeoDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;

@Repository("hotelGeoDao")
public class HotelGeoDao extends ElongMongoBaseDao<HotelGeo,ObjectId> implements IHotelGeoDao {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public String findCityNameByCityCode(String cityCode){
		String result = null;
		Datastore datastore = this.createDatastore();
		Query<HotelGeo> hotelGeo = datastore.createQuery(HotelGeo.class).filter("cityCode", cityCode);
		if(hotelGeo == null || hotelGeo.get() == null || hotelGeo.get().getCityName() == null){
			return result;
		}
		result = hotelGeo.get().getCityName();
		return result;
	}
	
	@Override
	public Map<String,String> findGeoCityCodeAndName(String lang) {
		Map<String,String> result = null;
		if(lang == null || lang.length() <= 0){
			lang = "CN";
		}
		result = new LinkedHashMap<>();
		Datastore datastore = this.createDatastore();
		//默认中文的
		Iterable<HotelGeo> hotelGeoQuery = datastore.createQuery(HotelGeo.class).retrievedFields(true, "cityName","cityCode");
		Iterator<HotelGeo> hotelGeoIterator = hotelGeoQuery.iterator();
		while(hotelGeoIterator.hasNext()){
			HotelGeo hotelGeo = hotelGeoIterator.next();
			result.put(hotelGeo.getCityCode(), hotelGeo.getCityName());
		}
		if(LOG.isDebugEnabled()){
			if(result != null || result.size() <= 0){
				LOG.debug("[LOCAL_MONGO_HOTEL_COUNT = {}]",result.size());
			}
		}
		System.out.println(result);
		return result;
	}

	@Override
	public List<String> findGeoCityCodeList(String lang) {
		List<String> result = null;
		if(lang == null || lang.length() <= 0){
			lang = "CN";
		}
		result = new ArrayList<>();
		Datastore datastore = this.createDatastore();
		//默认中文的
		Iterable<HotelGeo> hotelGeoQuery = datastore.createQuery(HotelGeo.class).retrievedFields(true, "cityCode");
		Iterator<HotelGeo> hotelGeoIterator = hotelGeoQuery.iterator();
		while(hotelGeoIterator.hasNext()){
			HotelGeo hotelGeo = hotelGeoIterator.next();
			result.add(hotelGeo.getCityCode());
		}
		if(LOG.isDebugEnabled()){
			if(result != null || result.size() <= 0){
				LOG.debug("[LOCAL_MONGO_HOTEL_COUNT = {}]",result.size());
			}
		}
		return result;
	}
	
}
