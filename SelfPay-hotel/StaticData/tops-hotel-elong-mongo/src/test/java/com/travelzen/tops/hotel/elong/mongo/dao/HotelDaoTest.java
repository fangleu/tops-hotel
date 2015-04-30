package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jmkgreen.morphia.query.CriteriaContainerImpl;
import com.github.jmkgreen.morphia.query.Query;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;


public class HotelDaoTest extends BaseTest {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IHotelDao hotelDao;
	
	@Test
	public void testCreate(){
		Query<Hotel> hotelQuery = hotelDao.createQuery();
//		System.out.println(hotelQuery.countAll());
//		Iterator<Hotel> hotelIterator = hotelQuery.iterator();
//		List<String> hotelsId = new ArrayList<>();
//		long start = System.currentTimeMillis();
//		
//		int number = 0;
//		while(hotelIterator.hasNext()){
//			Hotel hotel = hotelIterator.next();
//			hotelsId.add(hotel.getHotelId());
//			if(++number >= 200){
//				break;
//			}
//		}
//		long end = System.currentTimeMillis();
//		
//		System.out.println(hotelsId.size() + " = " + (end - start));
//		
//		CriteriaContainerImpl[] criterias = new CriteriaContainerImpl[hotelsId.size()];
//		for(int i = 0;i < hotelsId.size();i++){
//			criterias[i] = hotelQuery.criteria("hotelId").equal(hotelsId.get(i));
//		}
//		hotelQuery.or(  
//				criterias
//		);  
//		System.out.println(hotelQuery.asList().size() + "------------------------------------");
//		long start1 = System.currentTimeMillis();
//		hotelDao.createDatastore().delete(hotelQuery);
//		long end1 = System.currentTimeMillis();
//		System.out.println(hotelDao.createQuery().countAll() + " = " + (end1 - start1));
		
		
		CriteriaContainerImpl[] criteriaContainerImpls = new CriteriaContainerImpl[200];
		criteriaContainerImpls[0] = hotelQuery.criteria("hotelId").equal("111111111");
		System.out.println(criteriaContainerImpls.length);
		System.out.println(criteriaContainerImpls[0]);
		criteriaContainerImpls = new CriteriaContainerImpl[200];
		System.out.println(criteriaContainerImpls[0]);
		
		//List<Hotel> hotel = query.asList();
	}
	
	@Test
	public void testFindHotelMap(){
		Map<String, Hotel> map = hotelDao.findCityHotelNameMap();
//		for (Map.Entry<String, Hotel> hotelCity : map.entrySet()) { 
//			LOG.info("Hotel: {}, hotelName: {}", hotelCity.getKey(), hotelCity.getValue());
//		}
		LOG.info("Hotel Counts: {};", map.size());
		
		LOG.info(hotelDao.findHotelRoomNameById("80101240",""));
	}
	
	@Test
	public void testDelete(){
		
//		hotelDao.createQuery().or(criteria).or(criteria);
		
		
		
		Query<Hotel> dasd = hotelDao.createQuery().filter("hotelId","03108009");
		System.out.println(dasd.asList().size());
		hotelDao.createDatastore().delete(dasd);
//		hotelDao.delete(dasd);
	}

}
