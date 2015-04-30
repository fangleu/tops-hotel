package com.travelzen.tops.hotel.elong.mongo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.mongo.dao.ICreditCardDao;
import com.travelzen.tops.hotel.elong.mongo.dao.base.impl.ElongMongoBaseDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.CreditCard;


@Repository("creditCardDao")
public class CreditCardDao extends ElongMongoBaseDao<CreditCard,ObjectId> implements ICreditCardDao {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, String> findCreditCardAndName() {
		Query<CreditCard> query = getDatastore().createQuery(getEntityClass());
       
        List<CreditCard> result = query.asList();
        LOG.debug("CreditCard: Found {}", result.size());
        Map<String, String> creditCards = new HashMap<>();
        for (CreditCard card : result) {
        	creditCards.put(card.getCategoryName(), card.getCategoryNameEn());
        }
        return creditCards;
	}

	@Override
	public List<String> findCreditCardList(String lang) {
		Query<CreditCard> query = getDatastore().createQuery(getEntityClass());
	       
        List<CreditCard> result = query.asList();
        LOG.debug("CreditCard: Found {}", result.size());
        List<String> creditCards = new ArrayList<>();
        if (lang == null || lang == "cn" || lang == "CN") {
        	 for (CreditCard card : result) {
             	creditCards.add(card.getCategoryName());
             }
        } else {
        	 for (CreditCard card : result) {
              	creditCards.add(card.getCategoryNameEn());
              }
        }
        
        return creditCards;
	}
	
	
}
