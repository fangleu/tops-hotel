package com.travelzen.tops.hotel.elong.staticdata.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.travelzen.tops.hotel.elong.staticdata.service.impl.HotelInternationalActivePropertyListService.Country;

public interface IHotelInternationalActivePropertyListService {
	/**
	 * 获得国际酒店摘要信息
	 * @author muyuansun
	 * @date 2014-1-21 下午1:17:57
	 * @param input
	 * @return
	 */
	public List<Map<String,String>> findSummaryInfo(InputStream input);
	/**
	 * 从每行中提取国际酒店摘要信息
	 * @author muyuansun
	 * @date 2014-1-21 下午1:17:54
	 * @param line
	 * @return
	 */
	public Map<String, String> findSummaryFromLine(String line);
	
	/**
	 * 提取城市国家code对应国家摘要信息
	 * @author muyuansun
	 * @date 2014-1-21 下午1:18:33
	 * @param input
	 * @return
	 */
	public Map<String, Country> findCountryCodeAndIdPair(InputStream input);
	/**
	 * 从每行中提取国家code对应国家摘要信息
	 * @author muyuansun
	 * @date 2014-1-21 下午1:19:39
	 * @param line
	 * @param pointer
	 */
	public void findCountryCodeAndIdPairFromLine(String line,Map<String, Country> pointer);
	
	/**
	 * 获得国家ID和中文名称的对应
	 * @author muyuansun
	 * @date 2014-1-21 下午1:16:40
	 * @param input
	 * @return
	 */
	public Map<String, String> findCountryIdAndZhCnNamePair(InputStream input);
	
	/**
	 * 从每行中提取国家ID和中文名称对
	 * @author muyuansun
	 * @date 2014-1-21 下午1:17:06
	 * @param line
	 * @param pointer
	 */
	public void findCountryIdAndZhCnNamePairFromLine(String line,Map<String, String> pointer);
	
	public AtomicInteger getVALID_COUNTRY_COUNT();

	public AtomicInteger getINVALID_COUNTRY_COUNT();
	
	public AtomicInteger getVALID_HOTEL_COUNT();

	public AtomicInteger getINVALID_HOTEL_COUNT();
	
	public AtomicInteger getSTAR_RATING_IS_NULL_COUNT();
	
	public AtomicInteger getVALID_COUNTRY_ZHCN_COUNT();

	public AtomicInteger getINVALID_COUNTRY_ZHCN_COUNT();
	
	public AtomicInteger getTOTAL_COUNTRY_COUNT();

	public AtomicInteger getTOTAL_COUNTRY_ZHCN_COUNT();

	public AtomicInteger getTOTAL_HOTEL_COUNT();
	
}
