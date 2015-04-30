package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;

public interface IHotelDao extends IElongCoreMongoBaseDao<Hotel, ObjectId> {

    /**
     * 根据elongHotelId 查询酒店
     * @param hotelId
     * @return null or List<Hotel>
     */
    public List<Hotel> findHotelById(String hotelId);
    
    /**
     * 根据elongHotelId 查询酒店
     * @param hotelId
     * @param lang : "CN" or "EN"
     * @return null or List<Hotel>
     */
    public List<Hotel> findHotelById(String hotelId, String lang);

    /**
     * 根据酒店Id 及城市Id
     *
     * @param hotelId
     * @param cityCode
     * @return Hotel
     */
    public Hotel findByHotelIdAndCityCode(String hotelId, String cityCode);
    
    /**
     * 根据酒店Id 及城市Id
     *
     * @param hotelId
     * @param cityCode
     * @param lang : "CN" or "EN"
     * @return Hotel
     */
    public Hotel findByHotelIdAndCityCode(String hotelId, String cityCode, String lang);

    public List<Hotel> findAllHotel();
    
    public Map<String,Hotel> findHotelFromLocal(String lang);
    
    public Map<String,Hotel> findCityHotelNameMap();
    
    /**
     * 根据elongHotelId roomId查询酒店房间名
     * @param hotelId
     * @param roomId
     * @return 
     */
    public String findHotelRoomNameById(String hotelId, String roomId);
    
}
