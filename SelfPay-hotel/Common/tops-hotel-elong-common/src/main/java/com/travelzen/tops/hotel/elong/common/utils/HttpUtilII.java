package com.travelzen.tops.hotel.elong.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 此工具类为对HttpUtil的优化
 * 对inputStream的处理流程需实现IHttpUtilExternalProcess接口
 * 调用getDataByURL时传进来
 * @author jianming.zhou
 *
 */
public class HttpUtilII {
	
	private static Logger LOG = LoggerFactory.getLogger(HttpUtilII.class); 
	
	public static Object getDataByURL(IHttpUtilExternalProcess externalProcess, String url,String method,int soTimeout){
		InputStream inputStream = null;
		if(StringUtils.isEmpty(url)){
			LOG.warn("Url is null");
			return null;
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
	    			return null;
	    		}
	    		inputStream = getMethod.getResponseBodyAsStream();
	    	}else{
	    		postMethod = new PostMethod(url);
	    		int code = httpclient.executeMethod(postMethod);
	    		if (code != 200) {
	    			LOG.warn("Http code = {}" + code);
	    			return null;
	    		}
	    		inputStream = postMethod.getResponseBodyAsStream();
	    	}
	    	/** 外部实现 */
	    	return externalProcess.process(inputStream);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally{
        	try {
				inputStream.close();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
        	
        	if(getMethod != null){
        		getMethod.releaseConnection();
        	}
        	if(postMethod != null){
        		postMethod.releaseConnection();
        	}
        }
		return null;
	}

}
