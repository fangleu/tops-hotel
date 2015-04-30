package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest.CitySuggest;

public interface ICitySuggestDao extends IElongCoreMongoBaseDao<CitySuggest, ObjectId> {
	 /**
     * 根据cityId 查询城市json结果数据
     * @param cityId
     * @return null or CitySuggest
     */
    public List<CitySuggest> findCityResultById(String cityId);
    /**
     * 查询所有关键词信息
     * @param 
     * @return null or Map<String, List<String>>
     */
    public Map<String, List<String>> findAllKeywords();
}
