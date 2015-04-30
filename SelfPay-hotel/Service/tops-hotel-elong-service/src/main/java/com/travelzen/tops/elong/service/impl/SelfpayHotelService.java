package com.travelzen.tops.elong.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.travelzen.tops.elong.converter.SelfPayHotelConverter;
import com.travelzen.tops.elong.request.model.SelfPaySearchCriteria;
import com.travelzen.tops.elong.service.ISelfpayHotelService;
import com.travelzen.tops.elong.utils.ElongConnectorUtils;
import com.travelzen.tops.elong.utils.ParamUtils;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.ElongConfigurationKey;
import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;
import com.travelzen.tops.hotel.elong.entity.selfpay.custom.HotelListResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelDetailDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelItemDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelListDTO;
import com.travelzen.tops.hotel.elong.mongo.dao.IAmenityDao;
import com.travelzen.tops.hotel.elong.mongo.dao.ICitySuggestDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotCityDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelGeoDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;


@Service(value="selfpayHotel_Search")
public class SelfpayHotelService implements ISelfpayHotelService {

	private static Logger LOG = LoggerFactory.getLogger(SelfpayHotelService.class);

	@Resource
	private ElongConfiguration elongConfiguration = null;
	
	@Resource
	private IHotelDao hotelDao;
	
	@Resource
	private ICitySuggestDao citySuggestDao;
	
	@Resource
	private IHotCityDao hotCityDao;
	
	@Resource
	private IAmenityDao amenityDao;
	
	@Resource
	private IHotelGeoDao hotelGeoDao;
	
	//ELONG连接器URL
	private String elongConnectorServerUrl =  null;
	//accessElongEnableGzip
	private String accessElongEnableGzip = null;
	
	
	/**
	 * 查询酒店List
	 * @author Loufanglei
	 * @data 2014-10-30 上午11:39:11 
	 */
	@Override
	public HotelListDTO queryHotels(SelfPaySearchCriteria criteria, String pageSize, String pageIndex) throws CommonException {
		try {
			//封装请求数据
			long startTime = System.currentTimeMillis();   //获取开始时间
			String data = ElongConnectorUtils.generateQueryHotelsData(criteria,pageSize,pageIndex);
			String format = "json";
			String method = "hotel.list";
			String dataUTF8String = ElongConnectorUtils.getURLStringByUTF8Encode(data);
			String requestUrl = ElongConnectorUtils.generateRequestElongConnectorUrl(format, method, dataUTF8String,elongConnectorServerUrl);
			LOG.info("[生成国内酒店列表请求参数耗时:{}ms]",System.currentTimeMillis()-startTime);
			LOG.info("[查询酒店列表请求URL = {}]",requestUrl);
			//获得elong返回数据
			startTime = System.currentTimeMillis();
			String result = null;
			if(accessElongEnableGzip != null && accessElongEnableGzip.length() > 0 && accessElongEnableGzip.equals("true")){
				result = ElongConnectorUtils.requestElongConnector(requestUrl,true);
			}else{
				result = ElongConnectorUtils.requestElongConnector(requestUrl,false);
			}
			if (StringUtils.isBlank(result)) {
				LOG.info("[从艺龙未得到返回数据]");
				return null;
			}
			LOG.info("[获取国内酒店列表信息耗时 = {} ms]",System.currentTimeMillis()-startTime);

			//转换数据
			startTime = System.currentTimeMillis();
			HotelListResult jsonResult = JSON.parseObject(result.replaceAll("0001-01-01T00:00:00", "2001-01-01T00:00:00"),HotelListResult.class);
			if(jsonResult != null && jsonResult.getResult() != null){
				if (!jsonResult.getCode().equals("0")) {
					LOG.info("[酒店列表获取，艺龙返回错误信息：{}]", result);
					return null;
				}
				if(jsonResult.getResult() != null){
					LOG.info("[酒店列表结果总条数 = {}]",jsonResult.getResult().getCount());
				}
				HotelListDTO hotelListDto = new HotelListDTO();
				if (jsonResult.getResult().getCount() == 0) {
					hotelListDto.setCounts(0);
				} else {
					//数据转换
					List<HotelItemDTO> hotelItemList = SelfPayHotelConverter.formatHotelItem(criteria.getCheckInDate(), jsonResult);
					if (hotelItemList == null) {
						hotelListDto.setCounts(0);
					} else {
						hotelListDto.setCounts(jsonResult.getResult().getCount());
					}
					hotelListDto.setHotelItemList(hotelItemList);
				}
				
				LOG.info("[解析国内酒店列表信息耗时 = {} ms]",System.currentTimeMillis() - startTime);
				return hotelListDto;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return null;
		
	}
	
	
     /**
	 * 查询酒店详情
	 * @author Loufanglei
	 * @data 2014-10-30 上午11:44:08 
	 */
	@Override
	public HotelDetailDTO queryHotelDetail(String hotelId,String checkInDate ,String checkOutDate) throws CommonException {
		long startTime = System.currentTimeMillis();   //获取开始时间
		if (!ParamUtils.checkStringArrayNotEmpty(checkInDate, checkOutDate, hotelId)) {
			return null;
		}
		
		//封装请求数据
		String data = ElongConnectorUtils.generateQueryHotelDetailByHotelId(hotelId,checkInDate,checkOutDate);
		String format = "json";
		String method = "hotel.detail";
		String dataUTF8String = ElongConnectorUtils.getURLStringByUTF8Encode(data);
		String requestUrl = ElongConnectorUtils.generateRequestElongConnectorUrl(format, method, dataUTF8String,elongConnectorServerUrl);
		LOG.info("[生成国内酒店详情请求参数耗时:{}ms]",System.currentTimeMillis()-startTime);
		
		//获得elong返回数据
		startTime = System.currentTimeMillis();
		String result = null;
		if(accessElongEnableGzip != null && accessElongEnableGzip.length() > 0 && accessElongEnableGzip.equals("true")){
			result = ElongConnectorUtils.requestElongConnector(requestUrl,true);
		}else{
			result = ElongConnectorUtils.requestElongConnector(requestUrl,false);
		}
		if (result == null || result.length() < 1) {
			return null;
		}
		LOG.info("[获取国内酒店详情信息耗时:{}ms]",System.currentTimeMillis()-startTime);
		//数据转换
		startTime = System.currentTimeMillis();
		HotelListResult jsonResult = JSON.parseObject(result.replaceAll("0001-01-01T00:00:00", "2001-01-01T00:00:00"),HotelListResult.class);
		HotelDetailDTO detailDTO = SelfPayHotelConverter.generateHotelDetailDTO(checkInDate, jsonResult,hotelId);
		LOG.info("[解析国内酒店详情信息耗时:{}ms]",System.currentTimeMillis()-startTime);
		return detailDTO;
	}
	
	
	/**
	 * 获取行政区
	 * @author Loufanglei
	 * @data 2014-10-30 下午12:28:23 
	 */
	@Override
	public HotelGeo queryTrafficInfo(String cityId) {
		if (cityId == null) {
			return null;
		}
		HotelGeo dirctList= hotelGeoDao.createQuery().field("cityCode").equal(cityId).get();
		return dirctList;
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		elongConnectorServerUrl =  elongConfiguration.get(ElongConfigurationKey.ENCAPSULATION_ELONG_SERVER_URL.keyName());
		accessElongEnableGzip = elongConfiguration.get(ElongConfigurationKey.ACCESS_ELONG_ENABLE_GZIP.keyName());
	}

}
