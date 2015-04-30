package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.elong.common.constants.CommonConstants;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;
import com.travelzen.tops.hotel.elong.common.utils.B2GGZIPUtils;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;
import com.travelzen.tops.hotel.elong.mongo.dao.ICitySuggestDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelGeoDao;
import com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest.CitySuggest;
import com.travelzen.tops.hotel.elong.staticdata.service.ICityHotsuggestUpdateService;

@Service(value="cityHotsuggestUpdateService")
public class CityHotsuggestUpdateService implements ICityHotsuggestUpdateService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
		
	private ExecutorService pool = Executors.newFixedThreadPool(100);
	
	@Resource
	private ElongConfiguration elongConfiguration = null;
	
	@Resource
	private ICitySuggestDao citySuggestDao = null;
	
	@Resource
	private IHotelGeoDao hotelGeoDao = null;
	
	@Override
	public void cityHotSuggestUpdate() {
		long startTime = System.currentTimeMillis();
		citySuggestDao.deleteAll();
		List<String> cityIds = hotelGeoDao.findGeoCityCodeList(null);		
		try {
			downLoadAndPersistentCitySuggestInfo(cityIds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTIme = System.currentTimeMillis();
		LOG.info("[COST TIME = {}]",(endTIme - startTime) / 1000);
	}
	
	public String requestCityHotsuggest(String cityId,String lang, String baseUrl) throws InvaildParameterException {
		String result = null;
		if(StringUtils.isEmpty(cityId)){
			throw InvaildParameterException.instance("城市ID为空");
		}
		if(StringUtils.isEmpty(lang)){
			lang = "CN";
		}

		StringBuffer urlGenerator = new StringBuffer(elongConfiguration.get(baseUrl));
		urlGenerator.append("?callback=1&cityid=");
		urlGenerator.append(cityId);
		urlGenerator.append("&language=");
		urlGenerator.append(lang);
		if(LOG.isDebugEnabled()){
			LOG.debug("[The URL of City hot suggest json result = {}]",urlGenerator.toString());
		}
		result = doRequest(urlGenerator.toString());
		return result;
	}
	
	public String doRequest(String url) {
		try {
			String result = null;
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Connection", "keep-alive");
			getMethod.setRequestHeader("Accept-Encoding", "gzip");
			HttpClient httpclient = new HttpClient();
			httpclient.getParams().setContentCharset("UTF-8");
			Header responseHeader = getMethod.getResponseHeader("Content-Encoding");
			if(responseHeader != null && responseHeader.getValue() != null && responseHeader.getValue().equals("gzip")){
				result = B2GGZIPUtils.decompressGzipInputStream(getMethod.getResponseBodyAsStream(), "UTF-8");
			}else{
				result = getMethod.getResponseBodyAsString();
			}
			if (result.length() > 3) {
				return result.substring(2, result.length()-1);
			} else {
				return null;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 下载并存储城市建议交通信息
	 */
	public void downLoadAndPersistentCitySuggestInfo(List<String> cityIds) throws InterruptedException{
		List<List<String>> blockInfo = divideSegmentByThreadNumber(cityIds);
		if(blockInfo == null || blockInfo.size() <= 0){
			return;
		}
		LOG.info("[CITY_IDS_SIZE = {}]",cityIds.size());
		LOG.info("[BLOCK_SIZE = {}]",blockInfo.size());
		CountDownLatch countDownLatch = new CountDownLatch(blockInfo.size());
		for(List<String> block:blockInfo){
			pool.execute(new CitySuggestDownloadAndStore(block,countDownLatch));
		}
		countDownLatch.await();
	}
	
	/**
	 * 将需要更新的数据按线程数分块，用于每个线程分批执行下载
	 * @author muyuansun
	 * @date 2014-1-7 下午8:52:00
	 */
	public List<List<String>> divideSegmentByThreadNumber(List<String> cityIds){
		List<List<String>> result = null;
		if(cityIds == null || cityIds.size() <= 0){
			return result;
		}
		result = new ArrayList<>();
		
		/**
		 * 下载线程数，最大100，如果大于100，则设置成一百，如果小于10，则设置成10
		 */
		int threadNumber = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_THREAD_NUM.keyName(), 50);
		if(threadNumber < 10){
			threadNumber = 10;
		}
		if(threadNumber > 100){
			threadNumber = 100;
		}
		LOG.info("[THREAD_NUMBER = {}]",threadNumber);
		//如果小于线程数，直接返回一个block
		if(cityIds.size() < threadNumber){
			result.add(cityIds);
			return result;
		}
		//
		int blockSize;
		//被除数
		BigDecimal dividend = new BigDecimal(cityIds.size());
		//除数
		BigDecimal divisor = new BigDecimal(threadNumber);
		//块大小
		blockSize = dividend.divide(divisor,10,BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_UP).intValue();
		//块信息
		List<String> blockInfo = new ArrayList<>();
		//设值
		for(String cityId:cityIds){
			
			blockInfo.add(cityId);
			if(blockInfo.size() >= blockSize){
				result.add(blockInfo);
				blockInfo = new ArrayList<>();
			}
		}
		if(blockInfo != null && blockInfo.size() > 0 ){
			result.add(blockInfo);
		}
		return result;
	}
	
	/**
	 * - 根据提供的城市IDs（CityId）列表集合下载城市建议交通信息，并存储到本地数据库（mongo）
	 * - 存储酒店基本信息到本地数据库（mongo）
	 */
	private class CitySuggestDownloadAndStore implements Runnable{
		
		private Logger LOG = LoggerFactory.getLogger(this.getClass());
		
		private List<String>  cityIds = null;
		
		private CountDownLatch countDownLatch = null;
		
		private int batchSize = 20;
		
		public CitySuggestDownloadAndStore(List<String> cityIds, CountDownLatch countDownLatch){
			this.cityIds = cityIds;
			this.countDownLatch = countDownLatch;
			batchSize = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTEL_BATCH_THRESHOLD.keyName(), 20);
		}
		
		@Override
		public void run() {
				if(cityIds == null || cityIds.size() <= 0){
					return;
				}
				List<CitySuggest> batchCityList = new ArrayList<>();
				for(String cityId:cityIds){
					String result = null;
					String keyWordsResult = null;
					try {
						result = requestCityHotsuggest(cityId, null, CommonConstants.ElongConfigurationKey.CITYSUGGEST_ELONG_JSONRESULT_URL.keyName());
						keyWordsResult = requestCityHotsuggest(cityId, null, CommonConstants.ElongConfigurationKey.CITYSUGGEST_ELONG_KEYWORDS_URL.keyName());
						if (result != null || keyWordsResult != null) {
							CitySuggest citySuggest = new CitySuggest();
							citySuggest.setCityId(cityId);
							citySuggest.setResult(result);
							citySuggest.setKeywords(keyWordsResult);
							batchCityList.add(citySuggest);
							if (batchCityList.size() >= batchSize) {
								citySuggestDao.saveEntity(batchCityList);
								LOG.info("[BATCH_SAVE_FINISHED,SIZE = {}]",batchCityList.size());
								batchCityList.clear();
							}
						}else{
							LOG.warn("[The URL of City hot suggest json result is null, which city id = {}]",cityId);
						}
					} catch (InvaildParameterException e) {
						LOG.error(e.getMessage(),e);
					}
				}// for end
				//如果还有剩余小于阀值残留，也要持久化
				if(batchCityList.size() > 0){
					citySuggestDao.saveEntity(batchCityList);
					LOG.info("[BATCH_SAVE_FINISHED,SIZE = {}]",batchCityList.size());
				}
				countDownLatch.countDown();
			}
		}
}
