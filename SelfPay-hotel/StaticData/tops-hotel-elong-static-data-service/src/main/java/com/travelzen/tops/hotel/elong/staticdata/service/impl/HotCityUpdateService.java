package com.travelzen.tops.hotel.elong.staticdata.service.impl;

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
import com.travelzen.tops.hotel.elong.mongo.dao.IHotCityDao;
import com.travelzen.tops.hotel.elong.mongo.entity.hotsuggest.HotCity;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotCityUpdateService;

@Service(value="hotCityUpdateService")
public class HotCityUpdateService implements IHotCityUpdateService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
			
	@Resource
	private ElongConfiguration elongConfiguration = null;
	
	@Resource
	private IHotCityDao hotCityDao = null;
	
	@Override
	public void hotCityUpdate() {
		long startTime = System.currentTimeMillis();
		String[] tabIds = {"1", "2", "3", "4", "5", "6"};
		try {
			for (String tabId : tabIds) {
				String result = requestHotCity(tabId);
				if (result != null) {
					HotCity hotCity = new HotCity();
					hotCity.setTabId(tabId);
					hotCity.setResult(result);
					hotCityDao.saveEntity(hotCity);
				} else {
					LOG.warn("[HotCity : {} no result find!", tabId);
				}
			}
		} catch (InvaildParameterException e) {
			LOG.error("[HotCityUpdate failed, message = {}]", e.getMessage());
		}
		long endTIme = System.currentTimeMillis();
		LOG.info("[COST TIME = {}]",(endTIme - startTime) / 1000);
	}
	
	public String requestHotCity(String tabId) throws InvaildParameterException {
		String result = null;
		if(StringUtils.isEmpty(tabId)){
			throw InvaildParameterException.instance("城市ID为空");
		}
		
		StringBuffer urlGenerator = new StringBuffer(elongConfiguration.get(CommonConstants.ElongConfigurationKey.HOTCITY_ELONG_URL.keyName()));
		urlGenerator.append(tabId);
		urlGenerator.append(".html");
		if(LOG.isDebugEnabled()){
			LOG.debug("[The URL of hot city json result = {}]",urlGenerator.toString());
		}
		result = doRequest(urlGenerator.toString());
		
		return result;
	}
	
	public String doRequest(String url) {
		try {
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Connection", "keep-alive");
			getMethod.setRequestHeader("Accept-Encoding", "gzip");
			HttpClient httpclient = new HttpClient();
			httpclient.getParams().setContentCharset("UTF-8");
			httpclient.executeMethod(getMethod);
			Header responseHeader = getMethod.getResponseHeader("Content-Encoding");
			if(responseHeader != null && responseHeader.getValue() != null && responseHeader.getValue().equals("gzip")){
				return B2GGZIPUtils.decompressGzipInputStream(getMethod.getResponseBodyAsStream(), "UTF-8");
			}else{
				return getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	
}
