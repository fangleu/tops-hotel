package com.travelzen.tops.hotel.elong.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(HttpUtil.class); 
	
	public static InputStream getInputStreamByURL(String url,String method,int soTimeout){
		InputStream result = null;
		if(StringUtils.isEmpty(url)){
			return result;
		}
		if(StringUtils.isEmpty(method)){
			method = "GET";
		}
		//最大100分钟
		if(soTimeout > 6000000){
			soTimeout = 6000000;
		}
		if(soTimeout < 10000){
			soTimeout = 10000;
		}
		HttpClient httpclient = null;
		GetMethod getMethod = null;
		PostMethod postMethod = null;
	    try {
	    	httpclient = new HttpClient();
	    	httpclient.getParams().setSoTimeout(soTimeout);
	    	httpclient.getParams().setContentCharset("UTF-8");
	    	
	    	if(method.equals("GET")){
	    		getMethod = new GetMethod(url);
	     		int code = httpclient.executeMethod(getMethod);
	    		if (code != 200) {
	    			LOG.warn("Http code = {}" + code);
	    			return result;
	    		}
	    		result = getMethod.getResponseBodyAsStream();
	    	}else{
	    		postMethod = new PostMethod(url);
	    		int code = httpclient.executeMethod(postMethod);
	    		if (code != 200) {
	    			LOG.warn("Http code = {}" + code);
	    			return result;
	    		}
	    		result = postMethod.getResponseBodyAsStream();
	    	}
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
		return result;
	}

}
