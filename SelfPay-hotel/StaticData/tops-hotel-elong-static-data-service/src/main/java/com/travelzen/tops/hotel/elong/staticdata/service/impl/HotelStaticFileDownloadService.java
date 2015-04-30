package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.elong.common.constants.CommonConstants;
import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;
import com.travelzen.tops.hotel.elong.common.utils.HttpUtilII;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;
import com.travelzen.tops.hotel.elong.staticdata.service.externalProcess.DownloadAmenityExtProcess;
import com.travelzen.tops.hotel.elong.staticdata.service.externalProcess.DownloadCreditCardExtProcess;
import com.travelzen.tops.hotel.elong.staticdata.service.externalProcess.DownloadHotelDetailExtProcess;
import com.travelzen.tops.hotel.elong.staticdata.service.externalProcess.DownloadHotelGeoExtProcess;
import com.travelzen.tops.hotel.elong.staticdata.service.externalProcess.DownloadHotelListExtProcess;

@Service(value="hotelStaticFileDownloadService")
public class HotelStaticFileDownloadService implements IHotelStaticFileDownloadService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private ElongConfiguration elongConfiguration = null;
	
	@Override
	public Object downloadHotelGeo() throws FileDownloadException {
		try {
			int soTimeout = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_TIMEOUT.keyName(),20000);
			return HttpUtilII.getDataByURL(new DownloadHotelGeoExtProcess(), 
					elongConfiguration.get(CommonConstants.ElongConfigurationKey.HOTEL_GEO_CN_DOWNLOAD_URL.keyName()), null,soTimeout);
		} catch (Exception e) {
			throw FileDownloadException.instance("下载艺龙Geo信息文件失败",e);
		}
	}

	@Override
	public Object downloadHotelList() throws FileDownloadException {
		try {
			int soTimeout = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_TIMEOUT.keyName(),20000);
			return HttpUtilII.getDataByURL(new DownloadHotelListExtProcess(), 
					elongConfiguration.get(CommonConstants.ElongConfigurationKey.HOTELLIST_ELONG_DOWNLOAD_URL.keyName()), null,soTimeout);
		} catch (Exception e) {
			throw FileDownloadException.instance("下载艺龙全部可用酒店列表及酒店更新列表文件失败",e);
		}
	}
	@Override
	public Object downloadHotelDetail(String hotelId,String lang) throws FileDownloadException,InvaildParameterException {
		if(StringUtils.isEmpty(hotelId)){
			throw InvaildParameterException.instance("艺龙酒店ID为空");
		}
		if(StringUtils.isEmpty(lang)){
			lang = "cn";
		}
		try {
			StringBuffer urlGenerator = new StringBuffer(elongConfiguration.get(CommonConstants.ElongConfigurationKey.HOTELDETAIL_ELONG_DOWNLOAD_URL.keyName()));
			urlGenerator.append(lang);
			urlGenerator.append("/");
			urlGenerator.append(StringUtils.substring(hotelId, hotelId.length() - 2));
			urlGenerator.append("/");
			urlGenerator.append(hotelId);
			urlGenerator.append(".xml");
			if(LOG.isDebugEnabled()){
				LOG.debug("[The URL of hotel detail file = {}]",urlGenerator.toString());
			}
			int soTimeout = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_TIMEOUT.keyName(),20000);
			return HttpUtilII.getDataByURL(new DownloadHotelDetailExtProcess(), 
					urlGenerator.toString(),null,soTimeout);
		} catch (Exception e) {
			throw FileDownloadException.instance("下载详细信息文件失败",e);
		}
	}

	@Override
	public Object downloadCreditCard() throws FileDownloadException {
		try {
			int soTimeout = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_TIMEOUT.keyName(),20000);
			return HttpUtilII.getDataByURL(new DownloadCreditCardExtProcess(), 
					elongConfiguration.get(CommonConstants.ElongConfigurationKey.CREDIT_CARD_DOWNLOAD_URL.keyName()), null,soTimeout);
		} catch (Exception e) {
			throw FileDownloadException.instance("下载信用卡信息文件失败",e);
		}
	}

	@Override
	public Object downloadAmenity() throws FileDownloadException {
		try {
			int soTimeout = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_TIMEOUT.keyName(),20000);
			return HttpUtilII.getDataByURL(new DownloadAmenityExtProcess(), 
					elongConfiguration.get(CommonConstants.ElongConfigurationKey.HOTEL_AMENITY_DOWNLOAD_URL.keyName()), null,soTimeout);
		} catch (Exception e) {
			throw FileDownloadException.instance("下载艺龙酒店设施静态信息文件失败",e);
		}
	}

}
