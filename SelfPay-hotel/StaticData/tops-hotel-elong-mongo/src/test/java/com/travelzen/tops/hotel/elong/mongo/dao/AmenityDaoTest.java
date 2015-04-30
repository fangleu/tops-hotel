package com.travelzen.tops.hotel.elong.mongo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Amenity;

public class AmenityDaoTest extends BaseTest{
	
	@Resource
	private IAmenityDao amenityDao;
	
	@Test
	public void testFindAmenityByAmenityId(){
		Assert.assertNotNull(amenityDao);
		Assert.assertNotNull(amenityDao.findAmenityByAmenityId("9"));
	}
	
	@Test
	public void testFindAmenityByAmenityIds(){
		Assert.assertNotNull(amenityDao);
		String[] amenityIds = new String[]{"8","9"};
		List<Amenity> amenities = amenityDao.findAmenityByAmenityIds(amenityIds);
		Assert.assertNotNull(amenities);
		Assert.assertEquals(2, amenities.size());
	}

}
