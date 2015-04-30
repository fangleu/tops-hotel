package com.travelzen.tops.hotel.elong.redis.dao;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.redis.BaseTest;
import com.travelzen.tops.hotel.elong.redis.entity.TestEntity;

public class SpringRedisDaoTest extends BaseTest {
	
	@Resource
	private ISpringRedisDao springRedisDao = null;
	
	@Test
	public void testSave(){
		Assert.assertNotNull(springRedisDao);
		TestEntity testEntity = new TestEntity();
		testEntity.setHotelListCacheResult("aaaaaaaaaaa都你玩");
		boolean saveResult = springRedisDao.save("tops_hotel_spotpay_elong_12312312", testEntity, 60);
		Assert.assertEquals(true, saveResult);
		TestEntity testEntity1 = (TestEntity) springRedisDao.read("tops_hotel_spotpay_elong_12312312");
		Assert.assertEquals("aaaaaaaaaaa都你玩", testEntity1.getHotelListCacheResult());
	}
	
	@Test
	public void testRead(){
		TestEntity testEntity1 = (TestEntity) springRedisDao.read("tops_hotel_spotpay_elong_12312312");
		Assert.assertNotNull(testEntity1);
		Assert.assertEquals("aaaaaaaaaaa都你玩", testEntity1.getHotelListCacheResult());
	}
}
