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

import com.alibaba.fastjson.JSON;
import com.github.jmkgreen.morphia.Datastore;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.mongo.dao.ICitySuggestDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest.CitySuggest;
import com.travelzen.tops.hotel.elong.mongo.json.hotsuggest.SuggestData;
import com.travelzen.tops.hotel.elong.mongo.json.hotsuggest.SuggestNode;
import com.travelzen.tops.hotel.elong.mongo.json.hotsuggest.SuggestTagDataNode;

@Repository("elong_citySuggestDao")
public class CitySuggestDao extends ElongMongoBaseDao<CitySuggest, ObjectId> implements ICitySuggestDao {

	 private static Logger LOG = LoggerFactory.getLogger(CitySuggestDao.class);

    @Override
    protected Class<CitySuggest> getEntityClass() {
        return CitySuggest.class;
    }
    
	@Override
	public List<CitySuggest> findCityResultById(String cityId) {
		Query<CitySuggest> query = getDatastore().createQuery(getEntityClass());
        if (cityId != null) {
            query.field("cityId").equal(cityId);
        }
        
        List<CitySuggest> result = query.asList();
        LOG.debug("City: ID: {}, Found {}", cityId, result.size());
        return result;
	}



	@Override
	public Map<String, List<String>> findAllKeywords() {

		Map<String,List<String>> result = null;
		result = new LinkedHashMap<>();
		Datastore datastore = this.createDatastore();
		Iterable<CitySuggest> citySuggestQuery = datastore.createQuery(CitySuggest.class).retrievedFields(true, "cityId","result");
		Iterator<CitySuggest> citySuggestIterator = citySuggestQuery.iterator();
		while(citySuggestIterator.hasNext()){
			CitySuggest citySuggest = citySuggestIterator.next();
			String keywords = citySuggest.getResult();
			List<SuggestNode> jsonResult = JSON.parseArray(keywords, SuggestNode.class);
			List<String> keywordList = null;
			if (jsonResult != null && jsonResult.size() > 0) {
				
				for (SuggestNode suggestNode : jsonResult) {
					
					List<SuggestTagDataNode> tagNodes = suggestNode.getSuggestTagDataNodes();
					for (SuggestTagDataNode subNode : tagNodes) {
						List<SuggestData> datas = subNode.getSuggestDatas(); 
						for (SuggestData data : datas) {
							if (keywordList == null) {
								keywordList = new ArrayList<>();
							}
							keywordList.add(data.getDataName());
						}
					}
					
				}
				result.put(citySuggest.getCityId(), keywordList);
			}
		}
		if(LOG.isDebugEnabled()){
			if(result != null || result.size() <= 0){
				LOG.debug("[LOCAL_MONGO_KEYWORDS_COUNT = {}]",result.size());
			}
		}
        return result;
	}

}
