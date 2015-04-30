package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelGeoDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelGeoUpdateService;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.HotelGeoParser;

@Service(value="hotelGeoUpdateService")
public class HotelGeoUpdateService implements IHotelGeoUpdateService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IHotelStaticFileDownloadService hotelStaticFileDownloadService = null;
	
	@Resource
	private IHotelGeoDao hotelGeoDao = null;

	//每周五凌晨三点执行一次
	@Override
	public void hotelGeoStaticFileUpdate() throws CommonException{
		LOG.info("开始更新酒店GEO信息");
		long startTime = System.currentTimeMillis();
		List<HotelGeo> hotelGeos = null;
		try {
			HotelGeoParser hotelGeoParser = (HotelGeoParser) hotelStaticFileDownloadService.downloadHotelGeo();
			hotelGeos = hotelGeoParser.getHotelGeos();
			if(hotelGeos == null || hotelGeos.size() <= 0){
				throw CommonException.instance("没有获得酒店geo基本数据");
			}
		} catch (FileDownloadException e) {
			LOG.error(e.getMessage(), e);
		}
		if(hotelGeoDao.deleteAll()){
			hotelGeoDao.saveEntity(hotelGeos);
		}
		long endTime = System.currentTimeMillis();
		LOG.info("[更新酒店GEO信息完毕][用时：{} seconds]",(endTime - startTime) / 1000);
	}
	
	@Override
	public Map<String,String> findGeoCityCodeAndName(){
		return hotelGeoDao.findGeoCityCodeAndName(null);
	}
	
}
