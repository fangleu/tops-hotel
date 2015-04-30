package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.travelzen.tops.hotel.elong.mongo.dao.base.IElongCoreMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest.HotCity;

public interface IHotCityDao extends IElongCoreMongoBaseDao<HotCity, ObjectId> {
	 /**
     * 根据tabId 查询热门城市对数据
     * @param tabId
     * @return null or List<HotCity>
     */
    public List<HotCity> findResultByTabId(String tabId);
}
