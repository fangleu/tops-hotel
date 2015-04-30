package com.travelzen.tops.hotel.elong.mongo.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotCityDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest.HotCity;

@Repository("hotCityDao")
public class HotCityDao extends ElongMongoBaseDao<HotCity, ObjectId> implements IHotCityDao {

	 private static Logger LOG = LoggerFactory.getLogger(HotCityDao.class);

    @Override
    protected Class<HotCity> getEntityClass() {
        return HotCity.class;
    }

	    
	@Override
	public List<HotCity> findResultByTabId(String tabId) {
		Query<HotCity> query = getDatastore().createQuery(getEntityClass());
        if (tabId != null) {
            query.field("tabId").equal(tabId);
        }
        
        List<HotCity> result = query.asList();
        LOG.debug("Hot City: ID: {}, Found {}", tabId, result.size());
        return result;
	}

}
