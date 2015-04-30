package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.mongo.dao.IAmenityDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Amenity;
import com.travelzen.tops.hotel.elong.staticdata.service.IAmenityUpdateService;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;
import com.travelzen.tops.hotel.elong.staticdata.service.utils.ExcelReader;


@Service(value="amenityUpdateService")
public class AmenityUpdateService implements IAmenityUpdateService  {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IHotelStaticFileDownloadService hotelStaticFileDownloadService = null;
	
	@Resource
	private IAmenityDao amenityDao = null;

	@Override
	public void amenityUpdate(){
		LOG.info("[开始更新酒店设施静态信息]");
		long startTime = System.currentTimeMillis();
		try {
			Sheet amenitySheet = (Sheet) hotelStaticFileDownloadService.downloadAmenity();
			if(amenitySheet == null){
				throw CommonException.instance("酒店设施静态excel文件中的sheet信息为空。");
			}
			List<Object[]> excelRecords = ExcelReader.listFromSheet(amenitySheet);
			if(excelRecords == null || excelRecords.size() <= 0){
				throw CommonException.instance("酒店设施静态excel文件中的sheet记录为空。");
			}
			List<Amenity> amenities = generaterAmenities(excelRecords);
			if(amenities != null && amenities.size() > 0){
				amenityDao.deleteAll();
				amenityDao.saveEntity(amenities);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		long endTime = System.currentTimeMillis();
		LOG.info("[更新酒店设施静态信息完毕][用时：{} seconds]",(endTime - startTime) / 1000);
	}
	
	private List<Amenity> generaterAmenities(List<Object[]> excelRecords){
		List<Amenity> result = null;
		if(excelRecords == null || excelRecords.size() <= 0){
			return result;
		}
		result = new ArrayList<>();
		for(int i = 0;i < excelRecords.size();i++){
			Amenity amenity = new Amenity();
			
			if(i == 0){
				continue;
			}
			Object[] excelRecord = excelRecords.get(i);
			if(excelRecord.length > 0 && excelRecord[0] == null){
				continue;
			}else{
				amenity.setAmenityId(Double.valueOf(String.valueOf(excelRecord[0])).intValue());
			}
			if(excelRecord.length > 1 && excelRecord[1] == null){
				continue;
			}else{
				amenity.setParentID(Double.valueOf(String.valueOf(excelRecord[1])).intValue());
			}
			
			if(excelRecord.length > 2 && excelRecord[2] != null){
				amenity.setName(String.valueOf(excelRecord[2]));
			}
			
			if(excelRecord.length > 3 && excelRecord[3] != null){
				amenity.setNameEn(String.valueOf(excelRecord[3]));
			}
			
			result.add(amenity);
		}
		return result;
	}

}
