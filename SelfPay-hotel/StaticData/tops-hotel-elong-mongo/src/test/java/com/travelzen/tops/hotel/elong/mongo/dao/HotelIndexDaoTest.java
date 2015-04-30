package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.github.jmkgreen.morphia.query.CriteriaContainerImpl;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelIndexDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;

public class HotelIndexDaoTest extends BaseTest {
	
	@Resource
	private IHotelIndexDao hotelIndexDao;
	
	@Test
	public void testFindHotelIndexFromLocal(){
		long startTime = System.currentTimeMillis();
		Map<String,HotelIndex> result = hotelIndexDao.findHotelIndexFromLocal(null);
		Assert.assertNotNull(result);
		long endTIme = System.currentTimeMillis();
		LOG.info("[COST TIME = {} seconds][Size = {}]",(endTIme - startTime) / 1000,result.size());
	}
	
	@Test
	public void testBuildQuery(){
		Query<HotelIndex> query = hotelIndexDao.createQuery();
		CriteriaContainerImpl[] criterias = new CriteriaContainerImpl[2];
		criterias[0] = query.criteria("hotelId").equal("42202111");
		criterias[1] = query.criteria("hotelId").equal("11201102");
		query.or(  
				criterias
		);  
		Assert.assertNotNull(query);
		Assert.assertNotNull(query.asList());
		LOG.info("[Size = {}]",query.asList().size());
		for(HotelIndex hotelIndex:query.asList()){
			LOG.info("[id = {}]",hotelIndex.getId());
			hotelIndexDao.deleteById(hotelIndex.getId());
		}
		LOG.info("[Size = {}]",query.asList().size());
	}

}
