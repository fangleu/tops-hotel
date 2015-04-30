package com.travelzen.tops.hotel.order.schedule.selfpay.staticfile.updater;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.travelzen.tops.hotel.elong.staticdata.service.IHotelGeoUpdateService;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelUpdateService;

@Component("elongStaticFileScheduler")
public class ElongStaticFileUpdateScheduler {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IHotelUpdateService hotelUpdateService = null;
	
	@Resource
	private IHotelGeoUpdateService hotelGeoUpdateService = null;
	
	//每天凌晨1点运行
	@Scheduled(cron = "${elong.hotelindex.and.details.updater.cron.expression}")
	public void hotelIndexAndDetailsUpdater(){
		try {
			LOG.info("[Elong酒店静态数据更新开始]");
			hotelUpdateService.hotelDetailStaticFileUpdate();
			LOG.info("[Elong酒店静态数据更新结束]");
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		} 
	}
	//每周五凌晨3点运行,更新Elong酒店地理、行政区等信息
	@Scheduled(cron = "${elong.geo.updater.cron.expression}")
	public void geoUpdater(){
		try {
			LOG.info("[Elong酒店地理信息更新开始]");
			hotelGeoUpdateService.hotelGeoStaticFileUpdate();
			LOG.info("[Elong酒店地理信息更新结束]");
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		} 
	}

}
