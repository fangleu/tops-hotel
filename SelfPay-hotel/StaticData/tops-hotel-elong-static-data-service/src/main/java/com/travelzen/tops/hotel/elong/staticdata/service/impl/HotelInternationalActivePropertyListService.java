package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.InternationalHotelField;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelInternationalActivePropertyListService;

@Service(value="hotelInternationalActivePropertyListService")
public class HotelInternationalActivePropertyListService implements IHotelInternationalActivePropertyListService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 国家ID和基础信息中文
	 */
	private AtomicInteger VALID_COUNTRY_ZHCN_COUNT = new AtomicInteger();
	private AtomicInteger INVALID_COUNTRY_ZHCN_COUNT = new AtomicInteger();
	private AtomicInteger TOTAL_COUNTRY_ZHCN_COUNT = new AtomicInteger();
	
	/**
	 * 国家Code和基础信息
	 */
	private AtomicInteger VALID_COUNTRY_COUNT = new AtomicInteger();
	private AtomicInteger INVALID_COUNTRY_COUNT = new AtomicInteger();
	private AtomicInteger TOTAL_COUNTRY_COUNT = new AtomicInteger();

	/**
	 * 酒店基本信息
	 */
	private AtomicInteger VALID_HOTEL_COUNT = new AtomicInteger();
	private AtomicInteger INVALID_HOTEL_COUNT = new AtomicInteger();
	private AtomicInteger TOTAL_HOTEL_COUNT = new AtomicInteger();
	private AtomicInteger STAR_RATING_IS_NULL_COUNT = new AtomicInteger();
	
	@Override
	public Map<String, String> findCountryIdAndZhCnNamePair(InputStream input) {
		Map<String, String> result = null;
		if(input == null){
			return result;
		}
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));  
		try {
			result = new HashMap<>();
			while((line = reader.readLine()) != null){
				line = line.trim();
				if(StringUtils.startsWith(line, "CountryID")){
					continue;
				}
				TOTAL_COUNTRY_ZHCN_COUNT.addAndGet(1);
				findCountryIdAndZhCnNamePairFromLine(line, result);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	@Override
	public void findCountryIdAndZhCnNamePairFromLine(String line,Map<String, String> pointer) {
		if(line == null || line.length() <= 0){
			return;
		}
		if(pointer == null){
			return;
		}
		
		String [] lineTuples  = line.split("\\|");
		if(lineTuples == null || lineTuples.length != 3){
			LOG.warn("[FIle : CountryList_zh_CN.txt][LINE : {}][LENGTH NOT IS 3]",line);
			INVALID_COUNTRY_ZHCN_COUNT.addAndGet(1);
			return;
		}
		
		//国家ID
		String countryId = lineTuples[0].trim();
		if(countryId == null || countryId.length() <= 0){
			LOG.warn("[NO FOUND COUNTRY ID : Line {}]",line);
			INVALID_COUNTRY_ZHCN_COUNT.addAndGet(1);
			return;
		}
		
		//国家中文名称
		String countryName = lineTuples[2].trim();
		if(countryName == null || countryName.length() <= 0){
			LOG.warn("[NO FOUND COUNTRY CN NAME : Line {}]",line);
			INVALID_COUNTRY_ZHCN_COUNT.addAndGet(1);
			return;
		}
		pointer.put(countryId, countryName);
		VALID_COUNTRY_ZHCN_COUNT.addAndGet(1);
	}

	@Override
	public Map<String, Country> findCountryCodeAndIdPair(InputStream input) {
		Map<String, Country> result = null;
		if(input == null){
			return result;
		}
		String line = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));  
		try {
			result = new HashMap<>();
			while((line = reader.readLine()) != null){
				line = line.trim();
				if(StringUtils.startsWith(line, "CountryID")){
					continue;
				}
				TOTAL_COUNTRY_COUNT.addAndGet(1);
				findCountryCodeAndIdPairFromLine(line, result);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	@Override
	public void findCountryCodeAndIdPairFromLine(String line,Map<String, Country> pointer) {
		if(line == null || line.length() <= 0){
			return;
		}
		if(pointer == null){
			return;
		}
		String [] lineTuples  = line.split("\\|");
		if(lineTuples == null || lineTuples.length != 6){
			LOG.warn("[FIle : CountryList.txt][LINE : {}][LENGTH NOT IS 6]",line);
			INVALID_COUNTRY_COUNT.addAndGet(1);
			return;
		}
		Country country = new Country();
		
		//国家ID
		String countryId = lineTuples[0].trim();
		if(countryId == null || countryId.length() <= 0){
			LOG.warn("[NO FOUND COUNTRY ID : Line {}]",line);
			INVALID_COUNTRY_COUNT.addAndGet(1);
			return;
		}
		country.setCountryID(countryId);
		
		//国家code
		String countryCode = lineTuples[3].trim();
		if(countryCode == null || countryCode.length() <= 0){
			LOG.warn("[NO FOUND COUNTRY CODE : Line {}]",line);
			INVALID_COUNTRY_COUNT.addAndGet(1);
			return;
		}
		country.setCountryCode(countryCode);
		
		//大洲ID
		String continentID = lineTuples[5].trim();
		if(continentID == null || continentID.length() <= 0){
			LOG.warn("[NO FOUND CONTINENT CODE : Line {}]",line);
			INVALID_COUNTRY_COUNT.addAndGet(1);
			return;
		}
		country.setContinentID(continentID);
		pointer.put(countryCode, country);
		VALID_COUNTRY_COUNT.addAndGet(1);
	}
	
	@Override
	public List<Map<String, String>> findSummaryInfo(InputStream input) {
		List<Map<String, String>>  result = null;
		String line = null;
		if(input == null){
			return result;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));  
		try {
			result = new ArrayList<>();
			while((line = reader.readLine()) != null){
				line = line.trim();
				if(StringUtils.startsWith(line, "EANHotelID")){
					continue;
				}
				TOTAL_HOTEL_COUNT.addAndGet(1);
				Map<String, String> summary = findSummaryFromLine(line);
				if(summary == null || summary.size() <= 0 ){
					INVALID_HOTEL_COUNT.addAndGet(1);
					continue;
				}
				VALID_HOTEL_COUNT.addAndGet(1);
				result.add(summary);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 
	 * @author muyuansun
	 * @date 2014-1-21 上午10:45:22
	 * @return
	 */
	@Override
	public Map<String, String> findSummaryFromLine(String line){
		Map<String, String> result = null;
		if(line == null || line.length() <= 0){
			return result;
		}
		String [] lineTuples = line.split("\\|");
		
		if(lineTuples == null){
			return result;
		}
		if(lineTuples.length < 15){
			LOG.warn("[LINE : {}][LENGTH is smaller than 15][Length = {}]",line,lineTuples.length);
		}
		result = new HashMap<String, String>();
		//酒店ID
		String eANHotelID = lineTuples[0].trim();
		if(eANHotelID != null && eANHotelID.length() > 0){
			result.put(InternationalHotelField.EAN_HOTEL_ID.filed(), eANHotelID);
		}else{
			return null;
		}
		//酒店所在的国家
		String country = lineTuples[8].trim();
		if(country != null && country.length() > 0){
			result.put(InternationalHotelField.COUNTRY.filed(), country);
		}else{
			LOG.warn("[HOTELID : {}，Country info is null]",eANHotelID);
			return null;
		}
		//酒店星级
		String starRating = lineTuples[14].trim();
		if(starRating != null && starRating.length() > 0){
			if(starRating.equals(".0")){
				starRating = "0";
				if(LOG.isDebugEnabled()){
					LOG.debug("[HOTELID : {}，Star Rating is .0]",eANHotelID);
				}
			}
			result.put(InternationalHotelField.STAR_RATING.filed(), starRating);
		}else{
			if(LOG.isDebugEnabled()){
				LOG.debug("[HOTELID : {}，Star Rating info is null]",eANHotelID);
			}
			//酒店星级是NULL，则默认讲其设置为零
			starRating = "0";
			result.put(InternationalHotelField.STAR_RATING.filed(), starRating);
			STAR_RATING_IS_NULL_COUNT.addAndGet(1);
		}
		return result;
	}
	
	public static class CountryHotelSummary{
		
		private String countryNameCn = null;
		
		private int normalHotelCount = 0;
		
		private int starHotelCount = 0;

		public String getCountryNameCn() {
			return countryNameCn;
		}

		public void setCountryNameCn(String countryNameCn) {
			this.countryNameCn = countryNameCn;
		}

		public int getNormalHotelCount() {
			return normalHotelCount;
		}

		public void setNormalHotelCount(int normalHotelCount) {
			this.normalHotelCount = normalHotelCount;
		}

		public int getStarHotelCount() {
			return starHotelCount;
		}

		public void setStarHotelCount(int starHotelCount) {
			this.starHotelCount = starHotelCount;
		}
		
		/**
		 * 将星级酒店数量加一
		 * @author muyuansun
		 * @date 2014-1-21 下午2:28:24
		 */
		public void addStarHotelCount() {
			starHotelCount ++;
		}
		/**
		 * 将经济型酒店数量加一
		 * @author muyuansun
		 * @date 2014-1-21 下午2:28:28
		 */
		public void addNormalHotelCount() {
			normalHotelCount ++;
		}
	}
	
	public static class Country{
		
		//国家ID
		private String countryID = null;
		
		//大洲ID
		private String continentID = null;
		
		//国家Code
		private String countryCode = null;
		
		public String getCountryID() {
			return countryID;
		}
		public void setCountryID(String countryID) {
			this.countryID = countryID;
		}
		public String getContinentID() {
			return continentID;
		}
		public void setContinentID(String continentID) {
			this.continentID = continentID;
		}
		public String getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		
	}
	
	public AtomicInteger getVALID_COUNTRY_COUNT() {
		return VALID_COUNTRY_COUNT;
	}

	public AtomicInteger getINVALID_COUNTRY_COUNT() {
		return INVALID_COUNTRY_COUNT;
	}
	
	public AtomicInteger getVALID_HOTEL_COUNT() {
		return VALID_HOTEL_COUNT;
	}

	public AtomicInteger getINVALID_HOTEL_COUNT() {
		return INVALID_HOTEL_COUNT;
	}
	
	public AtomicInteger getSTAR_RATING_IS_NULL_COUNT() {
		return STAR_RATING_IS_NULL_COUNT;
	}
	
	public AtomicInteger getVALID_COUNTRY_ZHCN_COUNT() {
		return VALID_COUNTRY_ZHCN_COUNT;
	}

	public AtomicInteger getINVALID_COUNTRY_ZHCN_COUNT() {
		return INVALID_COUNTRY_ZHCN_COUNT;
	}
	
	public AtomicInteger getTOTAL_COUNTRY_COUNT() {
		return TOTAL_COUNTRY_COUNT;
	}

	public AtomicInteger getTOTAL_COUNTRY_ZHCN_COUNT() {
		return TOTAL_COUNTRY_ZHCN_COUNT;
	}

	public AtomicInteger getTOTAL_HOTEL_COUNT() {
		return TOTAL_HOTEL_COUNT;
	}
	
}
