package com.travelzen.tops.hotel.elong.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelIndexDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelUpdateService;

public class HotelUpdateServiceTest extends BaseTest {
	
	@Resource
	private IHotelUpdateService hotelUpdateService = null;
	
	@Resource IHotelIndexDao hotelIndexDao = null;
	
	@Test
	public void testHotelIndexUpdate() throws FileDownloadException, CommonException, InvaildParameterException, InterruptedException, IOException{
		Assert.assertNotNull(hotelUpdateService);
		hotelUpdateService.hotelDetailStaticFileUpdate();
	}
	
	@Test
	public void testSaveHotelIndex() throws FileDownloadException, CommonException, InvaildParameterException, InterruptedException, IOException{
		List<HotelIndex> hotelIndexs = hotelUpdateService.findHotelListFromElong();
		Assert.assertNotNull(hotelIndexs);
		List<HotelIndex> persistentHotelIndexs = new ArrayList<>();
		if(hotelIndexs.size() > 0){
			for(HotelIndex hotelIndex:hotelIndexs){
				if(hotelIndex.getStatus() != 0){
					continue;
				}
				persistentHotelIndexs.add(hotelIndex);
			}
		}
		LOG.info("[PERSISTENT_HOTEL_INDEXS_SIZE = {}]",persistentHotelIndexs.size());
		Assert.assertNotNull(hotelIndexDao);
		hotelIndexDao.saveEntity(persistentHotelIndexs);
	}

}
