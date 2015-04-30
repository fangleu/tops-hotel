package com.travelzen.tops.hotel.elong.mongo.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Room;

@Repository("hotelDao")
public class HotelDao extends ElongMongoBaseDao<Hotel, ObjectId> implements IHotelDao {

    private static Logger LOG = LoggerFactory.getLogger(HotelDao.class);

    @Override
    protected Class<Hotel> getEntityClass() {
        return Hotel.class;
    }

    @Override
    public List<Hotel> findAllHotel() {
        Query<Hotel> query = getDatastore().createQuery(getEntityClass());
        return query.asList();
    }

    @Override
    public List<Hotel> findHotelById(String hotelId) {
        return findHotelById(hotelId, null);
    }

    @Override
    public List<Hotel> findHotelById(String hotelId, String lang) {
        Query<Hotel> query = getDatastore().createQuery(getEntityClass());
        if (hotelId != null) {
            query.field("hotelId").equal(hotelId);
        }
        if (lang != null) {
            query.field("lang").equal(lang);
        }
        List<Hotel> result = query.asList();
        LOG.debug("Hotel: ID: {}, Found {}", hotelId, result.size());
        return result;
    }

    @Override
    public Hotel findByHotelIdAndCityCode(String hotelId, String cityCode) {

        return findByHotelIdAndCityCode(hotelId, cityCode, null);
    }

    @Override
    public Hotel findByHotelIdAndCityCode(String hotelId, String cityCode, String lang) {
        Query<Hotel> query = getDatastore().createQuery(getEntityClass());
        if (hotelId != null) {
            query.field("hotelId").equal(hotelId);
        }
        if (cityCode != null) {
            query.field("city").equal(cityCode);
        }
        if (lang != null) {
            query.field("lang").equal(lang);
        }
        LOG.debug("Find Hotel By ID: {} and City ID: {}", hotelId, cityCode);
        return query.get();
    }

	@Override
	public Map<String, Hotel> findHotelFromLocal(String lang) {
		Map<String,Hotel> result = null;
		if(lang == null || lang.length() <= 0){
			lang = "CN";
		}
		result = new HashMap<>();
		Datastore datastore = this.createDatastore();
		//默认中文的
		Iterable<Hotel> hotelQuery = datastore.createQuery(Hotel.class).retrievedFields(true, "hotelId","updatedTime").batchSize(200);
		Iterator<Hotel> hotelIterator = hotelQuery.iterator();
		int threshold = 100;
		int flag = 0;
		while(hotelIterator.hasNext()){
			Hotel hotel = hotelIterator.next();
			result.put(hotel.getHotelId(), hotel);
			if(++flag >= threshold){
				LOG.info("[成批获得酒店数量 = {}][获得酒店总数量 = {}]",flag,result.size());
				flag = 0;
			}
		}
		if(flag > 0){
			LOG.info("[成批获得酒店数量 = {}][获得酒店总数量 = {}]",flag,result.size());
		}
		if(LOG.isDebugEnabled()){
			if(result != null || result.size() <= 0){
				LOG.debug("[本地MONGO数据库酒店总数 = {}]",result.size());
			}
		}
		return result;
	}

	@Override
	public Map<String, Hotel> findCityHotelNameMap() {
		Map<String, Hotel> result = null;
		result = new HashMap<>();
		Datastore datastore = this.createDatastore();
		Iterable<Hotel> hotelQuery = datastore.createQuery(Hotel.class).retrievedFields(true ,"hotelId", "detail.name", "detail.city");
		Iterator<Hotel> hotelIterator = hotelQuery.iterator();
//		for (Hotel hotel : entities) {
		while(hotelIterator.hasNext()){
			Hotel hotel = hotelIterator.next(); 
			if (hotel.getDetail() != null) {
				
				result.put(hotel.getHotelId(), hotel);
			} else {
				LOG.debug("[hotel.getHotelId = {}]", hotel.getHotelId());
				deleteById(hotel.getId());
			}
		}
		
		if(LOG.isDebugEnabled()){
			if(result != null || result.size() <= 0){
				LOG.debug("[LOCAL_MONGO_CITYHOTELNAMEMAP_COUNT = {}]",result.size());
			}
		}
		return result;
	}

	@Override
	public String findHotelRoomNameById(String hotelId, String roomId) {
		if (hotelId != null && roomId != null) {
			
			List<Hotel> hotelList = findHotelById(hotelId);
			if (hotelList != null && hotelList.size() > 0) {
				List<Room> rooms = hotelList.get(0).getRooms();
				for (Room room: rooms) {
					if (room.getId().equalsIgnoreCase(roomId)) {
						return room.getName();
					}
				}
			}
		}
		return null;
	}

}
