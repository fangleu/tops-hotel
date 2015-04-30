package com.travelzen.tops.hotel.elong.connector.service.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.framework.security.MD5;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.ElongConfigurationKey;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.ElongRequestParameter;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;
import com.travelzen.tops.hotel.elong.common.utils.ElongInterUtil;
import com.travelzen.tops.hotel.elong.connector.service.IHotelService;
import com.travelzen.tops.hotel.elong.redis.dao.ISpringRedisDao;
import com.travelzen.tops.hotel.elong.redis.entity.HotelDetailRedisEntity;
import com.travelzen.tops.hotel.elong.redis.entity.HotelInvValidateRedisEntity;
import com.travelzen.tops.hotel.elong.redis.entity.HotelListRedisEntity;

/**
 * 
 * @author muyuansun
 * @date 2014-9-19 下午6:28:07
 */
@Service(value="hotelService")
public class HotelService implements IHotelService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	private Logger ACCESS_ELONG_INTERFACE_LOG = LoggerFactory.getLogger("ACCESS_ELONG_INTERFACE");
	
	private static String HOTEL_LIST_REDIS_KEY = "HOTEL_LIST_REDIS_KEY";
	
	private static String HOTEL_DETAIL_REDIS_KEY = "HOTEL_DETAIL_REDIS_KEY";
	
	private static String HOTEL_INV_VALIDATE_REDIS_KEY = "HOTEL_INV_VALIDATE_REDIS_KEY";
	
	private static ObjectMapper jsonMapper = new ObjectMapper();
	
	@Resource
	private ElongConfiguration elongConfiguration = null;
	@Resource
	private ISpringRedisDao springRedisDao = null;
	
	private static final ProtocolSocketFactory protocolSocketFactory = new CustomSSLProtocolSocketFactory();
    private static final int defaultPort = 443;
    
    static {
        // Elong所有与订单有关的接口都必须使用https
        Protocol.registerProtocol("https", new Protocol("https",protocolSocketFactory, defaultPort));
    }
    
    @Override
    public void invokeElongInterfaceWithCache(HttpServletRequest request,HttpServletResponse response,boolean gzipEnable){
    	try {
    		ElongRequestField elongRequestField = gatherElongRequestField(request);
    		//参数检查
    		String elongMethod = elongRequestField.getElongMethod();
    		if(elongMethod == null || elongMethod.length() <= 0){
    			processResponse(response, null,gzipEnable);
    			return;
    		}
    		ElongInterfaceResult content = null;
    		
    		//检查是否启用本地缓存逻辑
    		String elongDataLocalCache = elongConfiguration.get(ElongConfigurationKey.ELONG_DATA_LOCAL_CACHE.keyName());
    		if(elongDataLocalCache != null && elongDataLocalCache.length() > 0 && elongDataLocalCache.equals("true")){
    			
    			//酒店搜索缓存处理逻辑，缓存8分钟
    			if (elongMethod.equals("hotel.list")){
    				//获得酒店搜索缓存key
    				String hotelListCacheKey = getHotelListCacheKey(elongRequestField.getOriginElongData(),gzipEnable);
    				HotelListRedisEntity hotelListRedisEntity = (HotelListRedisEntity) springRedisDao.read(hotelListCacheKey);
    				//如果缓存中没有,查询elong后新增到缓存
    				if(hotelListRedisEntity == null || hotelListRedisEntity.cacheContent == null || hotelListRedisEntity.cacheContent.length <= 0){
    					ACCESS_ELONG_INTERFACE_LOG.info("[没有从缓存获得酒店列表信息,请求Elong接口获得实时信息][缓存key = {}][原始请求参数 = {}]",hotelListCacheKey,elongRequestField.getOriginElongData());
    					content = invokeElongInterface(request, elongRequestField,gzipEnable);
    					hotelListRedisEntity = new HotelListRedisEntity();
    					//如果内容不为空，并且不是异常信息，则加入缓存
    					if(content != null && content.getResult() != null && content.getResult().length > 0 && content.isSuccess()){
    						hotelListRedisEntity.setCacheContent(content.getResult());
    						boolean saveResult = springRedisDao.save(hotelListCacheKey, hotelListRedisEntity,60 * 8);
    						if(!saveResult){
    							LOG.warn("将酒店列表信息添加到缓存失败，艺龙请求数据 ： {}",elongRequestField.getOriginElongData());
    						}
    					}
    					//如果是错误信息，则没有加入缓存逻辑
    					if(content != null && content.getResult() != null && content.getResult().length > 0 && !content.isSuccess()){
    						hotelListRedisEntity.setCacheContent(content.getResult());
    					}
    				}else{
    					ACCESS_ELONG_INTERFACE_LOG.info("[从缓存获得酒店列表信息][缓存key = {}][原始请求参数 = {}]",hotelListCacheKey,elongRequestField.getOriginElongData());
    				}
    				processResponse(response, hotelListRedisEntity.getCacheContent(),gzipEnable);
    				return;
    				
    			}
    			
    			//酒店详情缓存处理逻辑，缓存4分钟
    			if (elongMethod.equals("hotel.detail")){
    				String hotelDetailCacheKey = gethotelDetailCacheKey(elongRequestField.getOriginElongData(),gzipEnable);
    				HotelDetailRedisEntity hotelDetailRedisEntity = (HotelDetailRedisEntity)springRedisDao.read(hotelDetailCacheKey);
    				//如果缓存中没有,查询elong后新增到缓存
    				if(hotelDetailRedisEntity == null || hotelDetailRedisEntity.cacheContent == null || hotelDetailRedisEntity.cacheContent.length <= 0){
    					ACCESS_ELONG_INTERFACE_LOG.info("[没有从缓存获得酒店详情信息][缓存key = {}][原始请求参数 = {}]",hotelDetailCacheKey,elongRequestField.getOriginElongData());
    					content = invokeElongInterface(request, elongRequestField,gzipEnable);
    					hotelDetailRedisEntity = new HotelDetailRedisEntity();
    					//如果内容不为空，并且不是异常信息，则加入缓存
    					if(content != null && content.getResult() != null && content.getResult().length > 0 && content.isSuccess()){
    						hotelDetailRedisEntity.setCacheContent(content.getResult());
    						boolean saveResult = springRedisDao.save(hotelDetailCacheKey, hotelDetailRedisEntity,60 * 4);
    						if(!saveResult){
    							LOG.warn("将酒店详情信息添加到缓存失败，艺龙请求数据 ： {}",elongRequestField.getOriginElongData());
    						}
    					}
    					//如果是错误信息，则没有加入缓存逻辑
    					if(content != null && content.getResult() != null && content.getResult().length > 0 && !content.isSuccess()){
    						hotelDetailRedisEntity.setCacheContent(content.getResult());
    					}
    				}else{
    					ACCESS_ELONG_INTERFACE_LOG.info("[从缓存获得酒店详情信息][缓存key = {}][原始请求参数 = {}]",hotelDetailCacheKey,elongRequestField.getOriginElongData());
    				}
    				processResponse(response, hotelDetailRedisEntity.getCacheContent(),gzipEnable);
    				return;
    				
    			}
    			
    			//酒店库存检查缓存处理逻辑，缓存2分钟
    			if (elongMethod.equals("hotel.inv.validate")){
    				String hotelInvValidateCacheKey = getHotelInvValidateCacheKey(elongRequestField.getOriginElongData(),gzipEnable);
    				HotelInvValidateRedisEntity hotelInvValidateRedisEntity = (HotelInvValidateRedisEntity)springRedisDao.read(hotelInvValidateCacheKey);
    				//如果缓存中没有,查询elong后新增到缓存
    				if(hotelInvValidateRedisEntity == null  || hotelInvValidateRedisEntity.cacheContent == null || hotelInvValidateRedisEntity.cacheContent.length <= 0){
    					ACCESS_ELONG_INTERFACE_LOG.info("[没有从缓存获得库存验证信息][缓存key = {}][原始请求参数 = {}]",hotelInvValidateCacheKey,elongRequestField.getOriginElongData());
    					content = invokeElongInterface(request, elongRequestField,gzipEnable);
    					hotelInvValidateRedisEntity = new HotelInvValidateRedisEntity();
    					//如果内容不为空，并且不是异常信息，则加入缓存
    					if(content != null && content.getResult() != null && content.getResult().length > 0 && content.isSuccess()){
    						hotelInvValidateRedisEntity.setCacheContent(content.getResult());
    						boolean saveResult = springRedisDao.save(hotelInvValidateCacheKey, hotelInvValidateRedisEntity,60 * 2);
    						if(!saveResult){
    							LOG.warn("将酒店库存检查信息添加到缓存失败，艺龙请求数据 ： {}",elongRequestField.getOriginElongData());
    						}
    					}
    					//如果是错误信息，则没有加入缓存逻辑
    					if(content != null && content.getResult() != null && content.getResult().length > 0 && !content.isSuccess()){
    						hotelInvValidateRedisEntity.setCacheContent(content.getResult());
    					}
    				}else{
    					ACCESS_ELONG_INTERFACE_LOG.info("[从缓存获得库存验证信息][缓存key = {}][原始请求参数 = {}]",hotelInvValidateCacheKey,elongRequestField.getOriginElongData());
    				}
    				processResponse(response, hotelInvValidateRedisEntity.getCacheContent(),gzipEnable);
    				return;
    				
    			}
    		}
    		//非上述三种接口不需要走缓存逻辑，每次都实时查询
    		content = invokeElongInterface(request, elongRequestField,gzipEnable);
    		processResponse(response, content.getResult(),gzipEnable);
    		return;
			
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
    }
    
    /**
     * 调用Elong酒店各种接口
     * @author muyuansun
     * @date 2014-9-19 下午5:08:50
     * @param request
     * @param elongRequestField
     * @return
     */
	public ElongInterfaceResult invokeElongInterface(HttpServletRequest request,ElongRequestField elongRequestField,boolean gzipEnable) {
		ElongInterfaceResult result = new ElongInterfaceResult();
		try {
			ACCESS_ELONG_INTERFACE_LOG.info("[================ELONG 酒店接口请求开始,请求参数如下================]");
			long startTime = System.currentTimeMillis();
			//如果要求gzip压缩，则检查请求的Content-Encoding头信息是否为GZIP
			if(gzipEnable){
				//获得客户端设置的响应编码
				String requestContentEncoding = request.getHeader("Content-Encoding");
				if(requestContentEncoding == null || !requestContentEncoding.equals("gzip")){
					byte[] errorInfo = new String("客户端请求内容编码必须为 gzip").getBytes();
					result.setResult(errorInfo);
					result.setSuccess(false);
					return result;
				}
			}
			/*
			 * 请求URL，以及header设置
			 */
			//通过请求参数组装访问elong接口请求URL
			String elongAPIUrl = compositeElongAPIUrl(elongRequestField);
			GetMethod getMethod = new GetMethod(elongAPIUrl);
			getMethod.setRequestHeader("Connection", "keep-alive");
			if(gzipEnable){
				getMethod.setRequestHeader("Accept-Encoding", "gzip");
				getMethod.setRequestHeader("Content-Encoding", "gzip");
			}
			//请求elong接口
			HttpClient httpclient = new HttpClient();
			httpclient.getParams().setContentCharset("UTF-8");
			httpclient.executeMethod(getMethod);
			//如果要求gzip压缩，则检查返回的Content-Encoding头信息是否为GZIP
			if(gzipEnable){
				//获得elong响应编码格式
				Header responseHeader = getMethod.getResponseHeader("Content-Encoding");
				//ELONG响应的内容编码必须是gzip
				if(responseHeader == null || responseHeader.getValue() == null || !responseHeader.getValue().equals("gzip")){
					byte[] errorInfo = new String("ELONG响应的内容编码不是gzip").getBytes();
					result.setResult(errorInfo);
					result.setSuccess(false);
					return result;
				}
			}
			if(getMethod.getResponseBody() != null && getMethod.getResponseBody().length > 0){
				result.setResult(getMethod.getResponseBody());
				result.setSuccess(true);
			}else{
				result.setResult(null);
				result.setSuccess(false);
			}
			long endTime = System.currentTimeMillis();
			if(gzipEnable){
				ACCESS_ELONG_INTERFACE_LOG.info("[ELONG 接口请求结束][响应信息字节长度 = {}][花费时间 = {} 毫秒，{} 秒][gzip]",result == null || result.getResult() == null ? "NULL" : result.getResult().length,(endTime - startTime),(endTime - startTime) / 1000);
			}else{
				ACCESS_ELONG_INTERFACE_LOG.info("[ELONG 接口请求结束][响应信息字节长度 = {}][花费时间 = {} 毫秒，{} 秒][!gzip]",result == null || result.getResult() == null ? "NULL" : result.getResult().length,(endTime - startTime),(endTime - startTime) / 1000);
			}
			return result;
		} catch (InvaildParameterException e) {
			LOG.error(e.getMessage(),e);
		} catch (HttpException e) {
			LOG.error(e.getMessage(),e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		} 
		return result;
	}
	
    private void processResponse(HttpServletResponse response,byte[] content,boolean gzipEnable){
		//获得响应输入流对象，将请求的elong数据写入
    	ServletOutputStream servletOutputStream = null;
        try {
        	if(content != null && content.length > 0){
        		if(gzipEnable){
        			response.setHeader("Content-Encoding", "gzip");
        			response.setContentType("application/x-gzip; charset=utf-8");
        		}else{
        			response.setContentType("application/json; charset=utf-8");
        		}
        	}else{
        		content = new String("没有获得相应信息，请检查。").getBytes("UTF-8");
        		response.setContentType("text/plain; charset=utf-8");
        	}
        	servletOutputStream = response.getOutputStream();
        	servletOutputStream.write(content);
        }catch(Exception e){
        	LOG.error(e.getMessage(),e);
		} finally {
			try {
				if(servletOutputStream != null){
					servletOutputStream.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(),e);
			}
		}
        return;
    }

	public void executeRequest(HttpServletRequest request,HttpServletResponse response,ElongRequestField elongRequestField) {
		ServletOutputStream servletOutputStream = null;
		InputStream inputStream = null;
		try {
			ACCESS_ELONG_INTERFACE_LOG.info("[================ELONG 酒店接口请求开始,请求参数如下================]");
			long startTime = System.currentTimeMillis();
			//获得响应输入流对象，将请求的elong数据写入
			servletOutputStream = response.getOutputStream();
			//获得客户端设置的响应编码
			String requestContentEncoding = request.getHeader("Content-Encoding");
			if(requestContentEncoding == null || !requestContentEncoding.equals("gzip")){
				LOG.error("客户端请求内容编码必须为 gzip");
				return;
			}
			/*
			 * 请求URL，以及header设置
			 */
			//通过请求参数组装访问elong接口请求URL
			String elongAPIUrl = compositeElongAPIUrl(elongRequestField);
			GetMethod getMethod = new GetMethod(elongAPIUrl);
			getMethod.setRequestHeader("Connection", "keep-alive");
			getMethod.setRequestHeader("Accept-Encoding", "gzip");
			getMethod.setRequestHeader("Content-Encoding", "gzip");
			//请求elong接口
			HttpClient httpclient = new HttpClient();
			httpclient.getParams().setContentCharset("UTF-8");
			httpclient.executeMethod(getMethod);
			//获得elong响应编码格式
			Header responseHeader = getMethod.getResponseHeader("Content-Encoding");
			//ELONG响应的内容编码必须是gzip
			if(responseHeader == null || responseHeader.getValue() == null || !responseHeader.getValue().equals("gzip")){
				LOG.info("ELONG响应的内容编码不是gzip");
				return;
			}
			//将响应内容
			inputStream = getMethod.getResponseBodyAsStream();
			long contentLength = 0;
			if(inputStream != null){
				byte[] buffer = new byte[1024];
				int length = -1;  
				while ((length = inputStream.read(buffer)) != -1) {  
					servletOutputStream.write(buffer, 0, length);  
					contentLength += length;
				}
			}
			long endTime = System.currentTimeMillis();
			ACCESS_ELONG_INTERFACE_LOG.info("[ELONG 接口请求结束][响应信息字节长度 = {}][花费时间 = {} 毫秒，{} 秒]",contentLength,(endTime - startTime),(endTime - startTime) / 1000);
		} catch (InvaildParameterException e) {
			LOG.error(e.getMessage(),e);
		} catch (HttpException e) {
			LOG.error(e.getMessage(),e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		} finally {
			try {
				response.setContentType("application/x-gzip; charset=utf-8");
				if(servletOutputStream != null){
					servletOutputStream.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(),e);
			}
		}
	}
	
	public String compositeElongAPIUrl(ElongRequestField elongRequestField) throws InvaildParameterException{
		String result = null;
		if(elongRequestField == null){
			return result;
		}
		//应用key
		String appKey = elongRequestField.getAppKey();
		ACCESS_ELONG_INTERFACE_LOG.info("[elongAppKey = {}]", appKey);
		//应用secretKey
		String secretKey = elongRequestField.getSecretKey();
		ACCESS_ELONG_INTERFACE_LOG.info("[elongSecretKey = {}]", secretKey);
		//elong接口用户名
		String elongUser = elongRequestField.getElongUser();
		ACCESS_ELONG_INTERFACE_LOG.info("[elongUser = {}]", elongUser);
		//访问的方法
		String elongMethod = elongRequestField.getElongMethod();
		ACCESS_ELONG_INTERFACE_LOG.info("[elongMethod = {}]", elongMethod);
		//时间戳
		String elongTimestamp = ElongInterUtil.getTimestamp();
		ACCESS_ELONG_INTERFACE_LOG.info("[elongTimestamp = {}]", elongTimestamp);
		//数据格式
		String elongFormat = elongRequestField.getElongFormat();
		ACCESS_ELONG_INTERFACE_LOG.info("[elongFormat = {}]", elongFormat);
		//原始数据请求参数
		ACCESS_ELONG_INTERFACE_LOG.info("[origin elongData = {}]", elongRequestField.getOriginElongData());
		//请求数据参数
		String elongData = ElongInterUtil.getURLStringByUTF8Decode(elongRequestField.getOriginElongData());
		ACCESS_ELONG_INTERFACE_LOG.info("[elongData = {}]", elongData);
		//请求数据参数
		String elongSignature = ElongInterUtil.getSignature(elongTimestamp,appKey,secretKey,elongData);
		ACCESS_ELONG_INTERFACE_LOG.info("[elongSignature = {}]", elongSignature);
		StringBuffer sb = new StringBuffer();
		if (elongMethod.equals("common.creditcard.validate") 
				|| elongMethod.equals("hotel.order.detail") 
					|| elongMethod.equals("hotel.order.create")
						|| elongMethod.equals("hotel.order.cancel")) {
			sb.append(elongConfiguration.get(ElongConfigurationKey.ELONG_API_SSL_URI.keyName()));
		}else{
			sb.append(elongConfiguration.get(ElongConfigurationKey.ELONG_API_URI.keyName()));
		}
		sb.append("user=");
		sb.append(elongUser);
		sb.append("&method=");
		sb.append(elongMethod);
		sb.append("&timestamp=");
		sb.append(elongTimestamp);
		sb.append("&format=");
		sb.append(elongFormat);
		sb.append("&data=");
		sb.append(ElongInterUtil.getURLStringByUTF8Encode(elongData));
		sb.append("&signature=");
		sb.append(elongSignature);
		result = sb.toString();
		ACCESS_ELONG_INTERFACE_LOG.info("[ACCESS_ELONG_INTERFACE_URL = {}]", result);
		return result;
	}
	
	public ElongRequestField gatherElongRequestField(HttpServletRequest request){
		ElongRequestField result = null;
		if(request == null){
			return result;
		}
		result = new ElongRequestField();
		//应用key
		String appKey = elongConfiguration.get(ElongConfigurationKey.APP_KEY.keyName());
		result.setAppKey(appKey);
		//应用secretKey
		String secretKey = elongConfiguration.get(ElongConfigurationKey.SECRET_KEY.keyName());
		result.setSecretKey(secretKey);
		//elong接口用户名
		String elongUser = elongConfiguration.get(ElongConfigurationKey.USER.keyName());
		result.setElongUser(elongUser);
		//访问的方法
		String elongMethod = request.getParameter(ElongRequestParameter.METHOD.paramName());
		result.setElongMethod(elongMethod);
		//数据格式
		String elongFormat = request.getParameter(ElongRequestParameter.FORMAT.paramName());
		result.setElongFormat(elongFormat);
		//原始数据请求参数
		String originElongData = request.getParameter(ElongRequestParameter.DATA.paramName());
		LOG.info("[原始数据请求参数 = {}]",originElongData);
		result.setOriginElongData(originElongData);
		return result;
	}
	
	public static class ElongRequestField{
		
		private String appKey = null;

		private String secretKey = null;
		
		private String elongUser = null;
		
		private String elongMethod = null;
		
		private String elongFormat = null;
		
		private String originElongData = null;
		
		public String getAppKey() {
			return appKey;
		}

		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

		public String getElongUser() {
			return elongUser;
		}

		public void setElongUser(String elongUser) {
			this.elongUser = elongUser;
		}

		public String getElongMethod() {
			return elongMethod;
		}

		public void setElongMethod(String elongMethod) {
			this.elongMethod = elongMethod;
		}

		public String getElongFormat() {
			return elongFormat;
		}

		public void setElongFormat(String elongFormat) {
			this.elongFormat = elongFormat;
		}

		public String getOriginElongData() {
			return originElongData;
		}

		public void setOriginElongData(String originElongData) {
			this.originElongData = originElongData;
		}
		
	}
	
	 private String getHotelListCacheKey(String hotelListRequestData,boolean gzipEnble){
	    	String result = null;
	    	try {
		    	if(hotelListRequestData == null || hotelListRequestData.length() <= 0){
		    		return result;
		    	}

		    	JsonNode node = jsonMapper.readTree(hotelListRequestData);
				
		    	if (node == null) {
					return result;
				}
		    	
		    	/*
		    	 * 如下三个参数是查询酒店列表必选参数
		    	 */
				
		    	//必选参数，参数检查。
				if(node.findValues("ArrivalDate") == null
						|| node.findValues("ArrivalDate").size() <= 0
							|| node.findValues("ArrivalDate").get(0) == null
								|| node.findValues("ArrivalDate").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取入住日期信息。");
				}
				
				if(node.findValues("CityId") == null 
						|| node.findValues("CityId").size() <= 0
							|| node.findValues("CityId").get(0) == null
								|| node.findValues("CityId").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取城市编码信息。");
				}
				
				if(node.findValues("DepartureDate") == null 
						|| node.findValues("DepartureDate").size() <= 0
							|| node.findValues("DepartureDate").get(0) == null
								|| node.findValues("DepartureDate").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取离店日期信息。");
				}
				
				//入住日期
				String arrivalDate = node.findValues("ArrivalDate").get(0).asText().trim();
				//城市编码
				String cityId = node.findValues("CityId").get(0).asText().trim();
				//离店日期
				String departureDate = node.findValues("DepartureDate").get(0).asText().trim();
				
				/*
				 * 提取非必须参数
				 */
				String queryText = null; // 查询文本,全文检索，可以是酒店名、位置或品牌等
				if(node.findValues("QueryText") != null && node.findValues("QueryText").size() > 0){
					queryText = node.findValues("QueryText").get(0).asText().trim();
				}
				
				String sort = null; // 排序类型,Default艺龙默认排序、StarRankDesc推荐星级降序、RateAsc价格升序、RateDesc价格降序、DistanceAsc距离升序
				if(node.findValues("Sort") != null && node.findValues("Sort").size() > 0){
					sort = node.findValues("Sort").get(0).asText().trim();
				}
				
				String starRate = null; // starRate,搜索多个星级以逗号分隔
				if(node.findValues("StarRate") != null && node.findValues("StarRate").size() > 0){
					starRate = node.findValues("StarRate").get(0).asText().trim();
				}
				
				String lowRate = null; // 最小价格
				if(node.findValues("LowRate") != null && node.findValues("LowRate").size() > 0){
					lowRate = node.findValues("LowRate").get(0).asText().trim();
				}
				
				String highRate = null; // 最大价格
				if(node.findValues("HighRate") != null && node.findValues("HighRate").size() > 0){
					highRate = node.findValues("HighRate").get(0).asText().trim();
				}
				
				String districtId = null; // 地区编码
				if(node.findValues("DistrictId") != null && node.findValues("DistrictId").size() > 0){
					districtId = node.findValues("DistrictId").get(0).asText().trim();
				}
				
				String pageIndex = null; //第几页
				if(node.findValues("PageIndex") != null && node.findValues("PageIndex").size() > 0){
					pageIndex = node.findValues("PageIndex").get(0).asText().trim();
				}
				
				String pageSize = null; //每页多少条
				if(node.findValues("PageSize") != null && node.findValues("PageSize").size() > 0){
					pageSize = node.findValues("PageSize").get(0).asText().trim();
				}
				
				//每页多少条，取值访问：1-20 （PageIndex>0时PageSize为必填，否则分页失败）
				if(pageIndex != null && pageSize == null){
					throw new IOException("pageIndex字段不为空的情况下pageSize字段必须不能为为空。");
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append(arrivalDate);
				sb.append("/");
				sb.append(cityId);
				sb.append("/");
				sb.append(departureDate);
				
				//分页
				if(pageIndex != null && pageSize != null){
					sb.append("/");
					sb.append(pageIndex);
					sb.append("/");
					sb.append(pageSize);
				}
				
				//关键词
				if(queryText != null && queryText.length() > 0){
					sb.append("/");
					sb.append(queryText);
				}
				//排序类型
				if(sort != null && sort.length() > 0){
					sb.append("/");
					sb.append(sort);
				}
				
				//星级
				if(starRate != null && starRate.length() > 0){
					sb.append("/");
					sb.append(starRate);
				}
				
				//最小价格
				if(lowRate != null && lowRate.length() > 0){
					sb.append("/");
					sb.append(lowRate);
				}
				
				//最大价格
				if(highRate != null && highRate.length() > 0){
					sb.append("/");
					sb.append(highRate);
				}
				
				//地区编码
				if(districtId != null && districtId.length() > 0){
					sb.append("/");
					sb.append(districtId);
				}
				//检查是否为gzip
				if(gzipEnble){
					sb.append("/gzip");
				}else{
					sb.append("/!gzip");
				}
				result = MD5.MD5Encode(HOTEL_LIST_REDIS_KEY + "/" + sb.toString());
				if(gzipEnble){
					ACCESS_ELONG_INTERFACE_LOG.info("[酒店列表][原始生成缓存KEY的请求参数 = {}][加入SALT的原始生成缓存KEY的请求参数 = {}][MD5生成的CacheKey = {}][gzip]",sb.toString(),HOTEL_LIST_REDIS_KEY + "/" + sb.toString(),result);
				}else{
					ACCESS_ELONG_INTERFACE_LOG.info("[酒店列表][原始生成缓存KEY的请求参数 = {}][加入SALT的原始生成缓存KEY的请求参数 = {}][MD5生成的CacheKey = {}][!gzip]",sb.toString(),HOTEL_LIST_REDIS_KEY + "/" + sb.toString(),result);
				}
	    	} catch (JsonProcessingException e) {
				LOG.error(e.getMessage(),e);
			} catch (IOException e) {
				LOG.error(e.getMessage(),e);
			}
	    	return result;
	    }
	    
	    
	    private String gethotelDetailCacheKey(String hotelDetailRequestData,boolean gzipEnble){
	    	
	    	String result = null;
	    	try {
	    		
		    	if(hotelDetailRequestData == null || hotelDetailRequestData.length() <= 0){
		    		return result;
		    	}

		    	JsonNode node = jsonMapper.readTree(hotelDetailRequestData);
				
		    	if (node == null) {
					return result;
				}
		    	
		    	/*
		    	 * 如下三个参数是查询酒店列表必选参数
		    	 */
				
		    	//必选参数，参数检查。
				if(node.findValues("ArrivalDate") == null
						|| node.findValues("ArrivalDate").size() <= 0
							|| node.findValues("ArrivalDate").get(0) == null
								|| node.findValues("ArrivalDate").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取入住日期信息。");
				}
				
				if(node.findValues("DepartureDate") == null 
						|| node.findValues("DepartureDate").size() <= 0
						|| node.findValues("DepartureDate").get(0) == null
						|| node.findValues("DepartureDate").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取离店日期信息。");
				}
				
				if(node.findValues("HotelIds") == null 
						|| node.findValues("HotelIds").size() <= 0
							|| node.findValues("HotelIds").get(0) == null
								|| node.findValues("HotelIds").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取酒店ID列表信息。");
				}
				
				//入住日期
				String arrivalDate = node.findValues("ArrivalDate").get(0).asText().trim();
				//离店日期
				String departureDate = node.findValues("DepartureDate").get(0).asText().trim();
				//城市编码
				String hotelIds = node.findValues("HotelIds").get(0).asText().trim();
				
				/*
				 * 提取非必须参数
				 */
				
				String roomTypeId = null; // 房型编码，当RatePlanId传值的时候不能为空。V1.10后对应销售房型编号
				if(node.findValues("RoomTypeId") != null && node.findValues("RoomTypeId").size() > 0){
					roomTypeId = node.findValues("RoomTypeId").get(0).asText().trim();
				}
				
				String ratePlanId = null; // 产品编码，默认请传0
				if(node.findValues("RatePlanId") != null && node.findValues("RatePlanId").size() > 0){
					ratePlanId = node.findValues("RatePlanId").get(0).asText().trim();
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append(arrivalDate);
				sb.append("/");
				sb.append(hotelIds);
				sb.append("/");
				sb.append(departureDate);
				
				if(ratePlanId != null && ratePlanId.length() > 0){
					sb.append("/");
					sb.append(ratePlanId);
				}
				
				if(roomTypeId != null && roomTypeId.length() > 0){
					sb.append("/");
					sb.append(roomTypeId);
				}
				
				//检查是否为gzip
				if(gzipEnble){
					sb.append("/gzip");
				}else{
					sb.append("/!gzip");
				}
				result = MD5.MD5Encode(HOTEL_DETAIL_REDIS_KEY + "/" + sb.toString());
				if(gzipEnble){
					ACCESS_ELONG_INTERFACE_LOG.info("[酒店详情][原始生成缓存KEY的请求参数 = {}][加入SALT的原始生成缓存KEY的请求参数 = {}][MD5生成的CacheKey = {}][gzip]",sb.toString(),HOTEL_DETAIL_REDIS_KEY + "/" + sb.toString(),result);
				}else{
					ACCESS_ELONG_INTERFACE_LOG.info("[酒店详情][原始生成缓存KEY的请求参数 = {}][加入SALT的原始生成缓存KEY的请求参数 = {}][MD5生成的CacheKey = {}][!gzip]",sb.toString(),HOTEL_DETAIL_REDIS_KEY + "/" + sb.toString(),result);
				}
	    	}catch(Exception e){
	    		LOG.error(e.getMessage(),e);
	    	}
	    	
	    	return result;
	    }
	    
	    private String getHotelInvValidateCacheKey(String hotelInvValidateRequestData,boolean gzipEnble){
	    	String result = null;
	    	
	    	try {
	    		
		    	if(hotelInvValidateRequestData == null || hotelInvValidateRequestData.length() <= 0){
		    		return result;
		    	}

		    	JsonNode node = jsonMapper.readTree(hotelInvValidateRequestData);
				
		    	if (node == null) {
					return result;
				}
		    	
		    	/*
		    	 * 如下参数是库存验证必选参数
		    	 */
				
		    	//必选参数，参数检查。
				if(node.findValues("ArrivalDate") == null
						|| node.findValues("ArrivalDate").size() <= 0
							|| node.findValues("ArrivalDate").get(0) == null
								|| node.findValues("ArrivalDate").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取入住日期信息。");
				}
				
				if(node.findValues("DepartureDate") == null 
						|| node.findValues("DepartureDate").size() <= 0
						|| node.findValues("DepartureDate").get(0) == null
						|| node.findValues("DepartureDate").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取离店日期信息。");
				}
				
				if(node.findValues("HotelId") == null 
						|| node.findValues("HotelId").size() <= 0
							|| node.findValues("HotelId").get(0) == null
								|| node.findValues("HotelId").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取酒店ID列表信息。");
				}
				
				if(node.findValues("HotelCode") == null 
						|| node.findValues("HotelCode").size() <= 0
							|| node.findValues("HotelCode").get(0) == null
								|| node.findValues("HotelCode").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取酒店编码列表信息。");
				}
				
				if(node.findValues("RoomTypeId") == null 
						|| node.findValues("RoomTypeId").size() <= 0
							|| node.findValues("RoomTypeId").get(0) == null
								|| node.findValues("RoomTypeId").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取房型编号列表信息。");
				}
				
				if(node.findValues("Amount") == null 
						|| node.findValues("Amount").size() <= 0
							|| node.findValues("Amount").get(0) == null
								|| node.findValues("Amount").get(0).asText().trim().length() <= 0){
					throw new RuntimeException("无法提取库存是否可用列表信息。");
				}
				
				//入住日期
				String arrivalDate = node.findValues("ArrivalDate").get(0).asText().trim();
				//离店日期
				String departureDate = node.findValues("DepartureDate").get(0).asText().trim();
				//酒店编号
				String hotelId = node.findValues("HotelId").get(0).asText().trim();
				//酒店编码
				String hotelCode = node.findValues("HotelCode").get(0).asText().trim();
				//房型编号
				String roomTypeId = node.findValues("RoomTypeId").get(0).asText().trim();
				//房间数量
				String amount = node.findValues("Amount").get(0).asText().trim();
				
				StringBuilder sb = new StringBuilder();
				sb.append(arrivalDate);
				sb.append("/");
				sb.append(departureDate);
				sb.append("/");
				sb.append(hotelId);
				sb.append("/");
				sb.append(hotelCode);
				sb.append("/");
				sb.append(roomTypeId);
				sb.append("/");
				sb.append(amount);
				
				//检查是否为gzip
				if(gzipEnble){
					sb.append("/gzip");
				}else{
					sb.append("/!gzip");
				}
				result = MD5.MD5Encode(HOTEL_INV_VALIDATE_REDIS_KEY + "/" + sb.toString());
				if(gzipEnble){
					ACCESS_ELONG_INTERFACE_LOG.info("[酒店库存验证][原始生成缓存KEY的请求参数 = {}][加入SALT的原始生成缓存KEY的请求参数 = {}][MD5生成的CacheKey = {}][gzip]",sb.toString(),HOTEL_INV_VALIDATE_REDIS_KEY + "/" + sb.toString(),result);
				}else{
					ACCESS_ELONG_INTERFACE_LOG.info("[酒店库存验证][原始生成缓存KEY的请求参数 = {}][加入SALT的原始生成缓存KEY的请求参数 = {}][MD5生成的CacheKey = {}][!gzip]",sb.toString(),HOTEL_INV_VALIDATE_REDIS_KEY + "/" + sb.toString(),result);
				}
	    	}catch(Exception e){
	    		LOG.error(e.getMessage(),e);
	    	}
	    	
	    	return result;
	    }
	
}
