package com.travelzen.tops.hotel.elong.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.InternationalHotelField;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelInternationalActivePropertyListService;
import com.travelzen.tops.hotel.elong.staticdata.service.impl.HotelInternationalActivePropertyListService.Country;
import com.travelzen.tops.hotel.elong.staticdata.service.impl.HotelInternationalActivePropertyListService.CountryHotelSummary;

public class HotelInternationalActivePropertyListServiceTest extends BaseTest {
	
	protected Logger STATISTIC_INTERNATIONAL_HOTEL_LOG = LoggerFactory.getLogger("STATISTIC_INTERNATIONAL_HOTEL");
	
	@Resource
	private IHotelInternationalActivePropertyListService hotelInternationalActivePropertyListService = null;
	
	
	@Test
	public void  testFindCountryIdAndZhCnNamePair(){
		Assert.assertNotNull(hotelInternationalActivePropertyListService);
		File file = new File("/opt/data/elong_new_interface/static_file/international/CountryList_zh_CN.txt");
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			Map<String,String> result = hotelInternationalActivePropertyListService.findCountryIdAndZhCnNamePair(inputStream);
			LOG.info("[VALID_COUNTRY_ZHCN_COUNT = {}][INVALID_COUNTRY_ZHCN_COUNT = {}][TOTAL_COUNTRY_ZHCN_COUNT = {}][RESULT_SIZE = {}]"
						,hotelInternationalActivePropertyListService.getVALID_COUNTRY_ZHCN_COUNT()
						,hotelInternationalActivePropertyListService.getINVALID_COUNTRY_ZHCN_COUNT()
						,hotelInternationalActivePropertyListService.getTOTAL_COUNTRY_ZHCN_COUNT()
						,result.size());
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	
	@Test
	public void testFindSummaryFromLine(){
		Assert.assertNotNull(hotelInternationalActivePropertyListService);
		String line1 = "363530|1|Mandurah Quay Resort|40 Marina Quay Drive||Erskine||6210|AU|-32.55957|115.70747|   |16|AUD|4.0|100|ESR|Erskine||6140114|0|0|2 PM|10 AM";
		String line2 = "118583|1001|Circus Circus Hotel & Casino|2880 Las Vegas Blvd S||Las Vegas|NV|89109|US|36.13636|-115.16225|LAS|3|USD|3.0|52|ESR|Near Fremont Street Experience|2102|2008|228|62|3 PM|11 AM";
		hotelInternationalActivePropertyListService.findSummaryFromLine(line1);
		hotelInternationalActivePropertyListService.findSummaryFromLine(line2);
	}
	
	@Test
	public void testFindCountryCodeAndIdPairFromLine(){
		Assert.assertNotNull(hotelInternationalActivePropertyListService);
		Map<String,Country> pointer = new HashMap<>();
		String line1 = "166|en_US|Spain|ES|Spain|6022967";
		hotelInternationalActivePropertyListService.findCountryCodeAndIdPairFromLine(line1, pointer);
		String line2 = "132|en_US|Papua New Guinea|PG|Papua New Guinea|6023180";
		hotelInternationalActivePropertyListService.findCountryCodeAndIdPairFromLine(line2, pointer);
		Assert.assertEquals(2, pointer.size());
	}
	
	@Test
	public void testfindSummaryInfo(){
		Assert.assertNotNull(hotelInternationalActivePropertyListService);
		File file = new File("/opt/data/elong_new_interface/static_file/international/ActivePropertyList.txt");
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			hotelInternationalActivePropertyListService.findSummaryInfo(inputStream);
			LOG.info("[VALID_HOTEL_COUNT = {}][INVALID_HOTEL_COUNT = {}][TOTAL_HOTEL_COUNT = {}][STAR_RATING_IS_NULL_COUNT = {}]"
						,hotelInternationalActivePropertyListService.getVALID_HOTEL_COUNT()
						,hotelInternationalActivePropertyListService.getINVALID_HOTEL_COUNT()
						,hotelInternationalActivePropertyListService.getTOTAL_HOTEL_COUNT()
						,hotelInternationalActivePropertyListService.getSTAR_RATING_IS_NULL_COUNT());
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	@Test
	public void testFindCountryCodeAndIdPair(){
		Assert.assertNotNull(hotelInternationalActivePropertyListService);
		File file = new File("/opt/data/elong_new_interface/static_file/international/CountryList.txt");
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			Map<String,Country> result = hotelInternationalActivePropertyListService.findCountryCodeAndIdPair(inputStream);
			LOG.info("[VALID_COUNTRY_COUNT = {}][INVALID_COUNTRY_COUNT = {}][TOTAL_COUNTRY_COUNT = {}][RESULT_SIZE = {}]"
						,hotelInternationalActivePropertyListService.getVALID_COUNTRY_COUNT()
						,hotelInternationalActivePropertyListService.getINVALID_COUNTRY_COUNT()
						,hotelInternationalActivePropertyListService.getTOTAL_COUNTRY_COUNT()
						,result.size());
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	@Test
	public void testCompositeInfo(){
		
		/**
		 * 获得国家基本数据信息
		 */
		File countryListFile = new File("/opt/data/elong_new_interface/static_file/international/CountryList.txt");
		InputStream countryListInputStream;
		Map<String,Country> countryBaseInfo = null;
		try {
			countryListInputStream = new FileInputStream(countryListFile);
			countryBaseInfo = hotelInternationalActivePropertyListService.findCountryCodeAndIdPair(countryListInputStream);
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
		
		/**
		 * 获得国家ID和中文名称的对应信息
		 */
		File countryListZhCNFile = new File("/opt/data/elong_new_interface/static_file/international/CountryList_zh_CN.txt");
		InputStream countryListZhCNInputStream;
		Map<String,String> countryZhCN = null;
		try {
			countryListZhCNInputStream = new FileInputStream(countryListZhCNFile);
			countryZhCN = hotelInternationalActivePropertyListService.findCountryIdAndZhCnNamePair(countryListZhCNInputStream);
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
		
		/**
		 * 获得所有酒店摘要信息
		 */
		File activePropertyListFile = new File("/opt/data/elong_new_interface/static_file/international/ActivePropertyList.txt");
		InputStream activePropertyListInputStream;
		List<Map<String,String>> activePropertyListRecords = null;
		try {
			activePropertyListInputStream = new FileInputStream(activePropertyListFile);
			activePropertyListRecords = hotelInternationalActivePropertyListService.findSummaryInfo(activePropertyListInputStream);
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
		
		if(activePropertyListRecords == null || activePropertyListRecords.size() <= 0){
			return;
		}
		
		//最后的组装信息
		LinkedHashMap<String,LinkedHashMap<String,CountryHotelSummary>> compositeInfo = new LinkedHashMap<>();
		
		for(Map<String,String> record:activePropertyListRecords){
			//酒店ID
			String hotelID = record.get(InternationalHotelField.EAN_HOTEL_ID.filed());
			//酒店所在的国家code
			String countryCode = record.get(InternationalHotelField.COUNTRY.filed());
			/**
			 * 根据国家code获得酒店的中文名
			 */
			
			//国家摘要信息对象
			Country countryObj = countryBaseInfo.get(countryCode);
			if(countryObj == null){
				LOG.info("[country info is null][country code = {}][hotel id = {}]",countryCode,hotelID);
				continue;
			}
			
			//获得国家ID
			String countryId = countryObj.getCountryID();
			if(countryId == null || countryId.length() <= 0){
				LOG.info("[country id is null][country code = {}][hotel id = {}]",countryCode,hotelID);
				continue;
			}
			
			//根据国家ID,获得国家中文名称
			String countryZhCNName = countryZhCN.get(countryId);
			if(countryZhCNName == null || countryZhCNName.length() <= 0){
				LOG.info("[country Zh CN Name is null][country code = {}][hotel id = {}]",countryCode,hotelID);
				continue;
			}
			
			//国家所再大洲的ID
			String continentID = countryObj.getContinentID();
			if(continentID == null || continentID.length() <= 0){
				LOG.info("[continent id is null][country code = {}][hotel id = {}]",countryCode,hotelID);
				continue;
			}
			
			//酒店星级
			String starRating = record.get(InternationalHotelField.STAR_RATING.filed());
			if(starRating == null || starRating.length() <= 0){
				LOG.info("[star Rating  is null][country code = {}][hotel id = {}]",countryCode,hotelID);
				continue;
			}
			/**
			 * 信息组装阶段
			 */
			//没有次大洲的信息，设值
			if(compositeInfo.get(continentID) == null){
				LinkedHashMap<String,CountryHotelSummary>  tmpContinentInfo = new LinkedHashMap<>();
				compositeInfo.put(continentID, tmpContinentInfo);
			}
			//根据此大洲的信息，获得当前国家
			LinkedHashMap<String,CountryHotelSummary> continentInfo = compositeInfo.get(continentID);
			//没有当前国家的信息，设值
			if(continentInfo.get(countryId) == null){
				CountryHotelSummary tmpCountryHotelSummary = new CountryHotelSummary();
				tmpCountryHotelSummary.setCountryNameCn(countryZhCNName);
				continentInfo.put(countryId, tmpCountryHotelSummary);
			}
			//获得此国家的酒店摘要信息
			CountryHotelSummary countryHotelSummary = continentInfo.get(countryId);
			//2以下的算普通
			if(Double.valueOf(starRating) <= 2){
				//经济型
				countryHotelSummary.addNormalHotelCount();
			}else{
				//星级
				countryHotelSummary.addStarHotelCount();
			}
		}//for
		//打印
		for(Map.Entry<String,LinkedHashMap<String,CountryHotelSummary>> info:compositeInfo.entrySet()){
			String continentID = info.getKey();
			for(Map.Entry<String,CountryHotelSummary> item:info.getValue().entrySet()){
				CountryHotelSummary countryHotelSummary = item.getValue();
				STATISTIC_INTERNATIONAL_HOTEL_LOG.info("{},{},{},{}",
							continentID,
							countryHotelSummary.getCountryNameCn(),
							countryHotelSummary.getNormalHotelCount(),
							countryHotelSummary.getStarHotelCount());
			}
		}
	}
	
}
