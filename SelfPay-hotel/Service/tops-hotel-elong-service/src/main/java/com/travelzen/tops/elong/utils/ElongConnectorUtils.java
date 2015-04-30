package com.travelzen.tops.elong.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.travelzen.tops.elong.converter.SelfpayHotelStaticContants;
import com.travelzen.tops.elong.request.model.SelfPaySearchCriteria;
import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.common.utils.B2GGZIPUtils;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.BaseRequst;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumLocal;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumQueryType;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.EnumSortType;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelDetailCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.HotelListCondition;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Position;

public class ElongConnectorUtils {
	
	private static Logger LOG = LoggerFactory.getLogger(ElongConnectorUtils.class);
	
	private static ObjectMapper JSON_OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * 艺龙接口请求参数名称
	 * @author muyuansun
	 * @date 2013-12-31 下午7:31:52
	 */
	public enum ElongRequestParameter{
		USER("user"),
		METHOD("method"),
		TIMESTAMP("timestamp"),
		FORMAT("format"),
		DATA("data"),
		SIGNATURE("signature");
		private String paramName;
		private ElongRequestParameter(String paramName){
			this.paramName = paramName;
		}
		public String paramName(){
			return paramName;
		}
	}
	
	/**
	 * UTF-8 编码
	 * @author muyuansun
	 * @date 2014-10-28 下午9:14:33
	 * @param data
	 * @return
	 */
	public static String getURLStringByUTF8Encode(String data) {
		String xmString = null;
		String xmlUTF8 = null;
		try {
			xmString = new String(data.getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
		}
		return xmlUTF8;
	}
	
	/**
	 * UTF-8 解码
	 * @author muyuansun
	 * @date 2014-10-28 下午9:14:44
	 * @param data
	 * @return
	 */
	public static String getURLStringByUTF8Decode(String data) {
		String decodeDate = null;
		try {
			data = new String(data.getBytes("UTF-8"));
			decodeDate = URLDecoder.decode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
		}
		return decodeDate;
	}
	
	/**
	 * 请求Elong Connnector
	 * @author muyuansun
	 * @date 2014-10-28 下午9:05:29
	 * @param url
	 * @return
	 */
	public static String requestElongConnector(String url,boolean gzipEnable) {
		try {
			GetMethod getMethod = new GetMethod(url);
			getMethod.setRequestHeader("Connection", "keep-alive");
			if(gzipEnable){
				getMethod.setRequestHeader("Accept-Encoding", "gzip");
				getMethod.setRequestHeader("Content-Encoding", "gzip");
			}
			HttpClient httpclient = new HttpClient();
			httpclient.getParams().setSoTimeout(20000);
			httpclient.getParams().setContentCharset("UTF-8");
			httpclient.executeMethod(getMethod);
			Header responseHeader = getMethod.getResponseHeader("Content-Encoding");
			if(responseHeader != null && responseHeader.getValue() != null && responseHeader.getValue().equals("gzip")){
				return B2GGZIPUtils.decompressGzipInputStream(getMethod.getResponseBodyAsStream(), "UTF-8");
			}else{
				return B2GGZIPUtils.convertInputStreamToString(getMethod.getResponseBodyAsStream(), "UTF-8");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	/**
	 * 生成请求Elong连接器的URL
	 * @author muyuansun
	 * @date 2014-10-28 下午9:12:35
	 * @param format
	 * @param method
	 * @param dataUTF8String
	 * @param elongConnectorServerUrl
	 * @return
	 * @throws CommonException
	 */
	public static String generateRequestElongConnectorUrl(String format,String method,String dataUTF8String,String elongConnectorServerUrl) throws CommonException{
		if(elongConnectorServerUrl == null || elongConnectorServerUrl.length()  <= 0){
			throw CommonException.instance("没有获得封装的Elong接口的配置信息");
		}
		StringBuffer sb = new StringBuffer(elongConnectorServerUrl + "?");
		sb.append(ElongConnectorUtils.ElongRequestParameter.METHOD.paramName());
		sb.append("=");
		sb.append(method);
		sb.append("&");
		sb.append(ElongConnectorUtils.ElongRequestParameter.FORMAT.paramName());
		sb.append("=");
		sb.append(format);
		sb.append("&");
		sb.append(ElongConnectorUtils.ElongRequestParameter.DATA.paramName());
		sb.append("=");
		sb.append(dataUTF8String);
		return sb.toString();
	}
	
	public static String getBreakFastType(String ratePlan){
		String[] breakfastType = {"无早餐", "单早餐", "双早餐"};
		if (ratePlan.matches(".*无.*") || ratePlan.matches("不.*")) {
			return breakfastType[0];
		} else if (ratePlan.matches(".*单.*")) {
			return breakfastType[1];
		} else if (ratePlan.matches(".*双.*")) {
			return breakfastType[2];
		} else {
			return "含早";			
		}
	}
	
	
	public static String getBedType(String bedType){
		String[] beds = bedType.split(",");
		String bedResult = "";
		if (beds.length == 1) {
			bedResult = beds[0].substring(0, 2);
		} else {
			for (int i=0; i < beds.length-1; i++) {
				bedResult = ParamUtils.stringBufferAppendStrings("", bedResult, beds[i].substring(0, 1), "/");
			}
			bedResult = ParamUtils.stringBufferAppendStrings("", bedResult, beds[beds.length-1].substring(0, 2));
		}
		return bedResult;
	}
	
	public static String getWeekDayOfWeek(Date date) {
		Calendar  cal = Calendar.getInstance();
		cal.setTime(date);
		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		return weekDays[cal.get(Calendar.DAY_OF_WEEK) % 7];
	}
	
	/**
	 * 星级描述
	 * @param starRate
	 * @return
	 */
	public static String translateStarRate(int starRate) {
		String starDetail = "";
		if (starRate == 5) {
			starDetail = "五星/豪华";
		} else if (starRate == 4) {
			starDetail = "四星/高档";
		} else if (starRate == 3) {
			starDetail = "三星/舒适";
		} else if (starRate < 3) {
			starDetail = "二星及以下/经济";
		}
		return starDetail;
		
	}
	
	/**
	 * 推荐星级描述
	 * @param tzRate
	 * @return
	 */
	public static String translateTzRate(int tzRate) {
		String starDetail = "";
		if (tzRate == 5) {
			starDetail = "准五冠/豪华";
		} else if (tzRate == 4) {
			starDetail = "准四冠/高档";
		} else if (tzRate == 3) {
			starDetail = "准三冠/舒适";
		} else if (tzRate < 3) {
			starDetail = "准二冠及以下/经济";
		}
		return starDetail;
	}
	
	/**
	 * 将json对象转换成json字符串
	 * @author muyuansun
	 * @date 2014-10-28 下午9:24:01
	 * @param jsonObject
	 * @return
	 */
	public static String convertJsonObjectToJsonString(Object jsonObject) {
		String result = null;
		if(jsonObject == null){
			return result;
		}
		result = JSON.toJSONString(jsonObject);
		return result;
	}
	
	
	/**
	 * 用Jackson框架转换JsonObject到Json字符串
	 * @author muyuansun
	 * @date 2014-10-28 下午10:46:40
	 * @param jsonObject
	 * @return
	 */
	public static String convertJsonObjectToJsonStringUsingJackson(Object jsonObject) {
		String result = null;
		if(jsonObject == null){
			return result;
		}
		try {
			result = JSON_OBJECT_MAPPER.writeValueAsString(jsonObject);
		} catch (JsonGenerationException e) {
			LOG.error(e.getMessage(),e);
		} catch (JsonMappingException e) {
			LOG.error(e.getMessage(),e);
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
	
	/**
	 * 生成酒店查询data数据
	 * @author muyuansun
	 * @date 2014-10-28 下午9:29:31
	 * @param criteria
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public static String generateQueryHotelsData(SelfPaySearchCriteria criteria, String pageSize, String pageIndex) {
		HotelListCondition condition = new HotelListCondition();
		/*
		 * 参数检查
		 */
		if (criteria.getSortItem() != null) {
			if("1".equals(criteria.getSortItem()) &&"1".equals(criteria.getSortOrder())){
				condition.setSort(EnumSortType.fromValue("RateDesc"));
			}else if("1".equals(criteria.getSortItem())&&"0".equals(criteria.getSortOrder())){
				condition.setSort(EnumSortType.fromValue("RateAsc"));
			}else if("0".equals(criteria.getSortItem())&&"0".equals(criteria.getSortOrder())){
				condition.setSort(EnumSortType.fromValue("StarRankDesc"));
			}else if("0".equals(criteria.getSortItem())&&"1".equals(criteria.getSortOrder())){
				condition.setSort(EnumSortType.fromValue("StarRankDesc"));
			}
		}

		if (criteria.getKeywords() != null) {
			condition.setQueryText(criteria.getKeywords());
			condition.setQueryType(EnumQueryType.HotelName);
		}
		if(criteria.getHotelRatings()!=null){
			String rating=null;
			rating=criteria.getHotelRatings().toString();
			rating=rating.substring(1,rating.length()-1);
			condition.setStarRate(rating);
		}
		if(criteria.getDistrictCodes()!=null){
			String ss=criteria.getDistrictCodes().toString();
			ss=ss.substring(1,ss.length()-1);
			condition.setDistrictId(ss);
		}
		if(criteria.getCommercialCodes()!=null){
			String ss=criteria.getCommercialCodes().toString();
			ss=ss.substring(1,ss.length()-1);
			condition.setLocationId(ss);
		}
		if (criteria.getMinPrice() != null) {
			condition.setLowRate(Integer.parseInt(new java.text.DecimalFormat("0").format(criteria.getMinPrice())));
		}
		if (criteria.getMaxPrice() != null) {
			condition.setHighRate(Integer.parseInt(new java.text.DecimalFormat("0").format(criteria.getMaxPrice())));
		}
		if (criteria.getFacilitys() != null) {
			condition.setFacilities(criteria.getFacilitys().toString());
		}
       
//		int radius = 20000;
//		Position position = new Position();
//		position.setRadius(radius);
//		condition.setPosition(position);

		if(SelfpayHotelStaticContants.transferTzCode2Elong(criteria.getCityIsoCode())==null){
			return null;
		}
		criteria.setCityIsoCode(SelfpayHotelStaticContants.transferTzCode2Elong(criteria.getCityIsoCode()));
		condition.setArrivalDate(criteria.getCheckInDate());
		condition.setDepartureDate(criteria.getCheckOutDate());
		condition.setCityId(criteria.getCityIsoCode());
		condition.setPageIndex(criteria.getPageNo());
		condition.setPageSize(criteria.getPageSize());
		condition.setResultType("1,2,3,4");
    	BaseRequst<HotelListCondition> req = new BaseRequst<HotelListCondition>();
    	req.Version = 1.1;
    	req.Local = EnumLocal.zh_CN;
    	req.Request = condition;
		return ElongConnectorUtils.convertJsonObjectToJsonString(req);
	}
	/**
	 * 生成查询酒店详情（只用酒店ID）data数据
	 * @author muyuansun
	 * @date 2014-10-28 下午9:32:43
	 * @param hotelId
	 * @param checkInDate
	 * @param checkOutDate
	 * @return
	 */
	public static String generateQueryHotelDetailByHotelId(String hotelId,String checkInDate ,String checkOutDate) {
		HotelDetailCondition condition = new HotelDetailCondition();
		condition.setArrivalDate(checkInDate);
		condition.setDepartureDate(checkOutDate);
		condition.setHotelIds(hotelId);
		condition.setOptions("1,2,3");
    	BaseRequst<HotelDetailCondition> req = new BaseRequst<HotelDetailCondition>();
    	req.Version = 1.1;
    	req.Local = EnumLocal.zh_CN;
    	req.Request = condition;
		return ElongConnectorUtils.convertJsonObjectToJsonString(req);
	}
	/**
	 * 生成查询酒店详情（用SelfPaySearchCriteria）data数据
	 * @author muyuansun
	 * @date 2014-10-28 下午9:35:11
	 * @param criteria
	 * @return
	 */
	public static String generateQueryHotelDetailByCriteria(SelfPaySearchCriteria criteria) {
		HotelDetailCondition condition = new HotelDetailCondition();
		condition.setArrivalDate(criteria.getCheckInDate());
		condition.setDepartureDate(criteria.getCheckOutDate());
		condition.setHotelIds(criteria.getHotelId());
		condition.setRoomTypeId(criteria.getRoomCatId());
		condition.setRatePlanId(Integer.parseInt(criteria.getBookingClassId()));
    	BaseRequst<HotelDetailCondition> req = new BaseRequst<HotelDetailCondition>();
    	req.Version = 1.1;
    	req.Local = EnumLocal.zh_CN;
    	req.Request = condition;
		return ElongConnectorUtils.convertJsonObjectToJsonString(req);
	}
	
	public static String encryptDES(String message, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		return toHexString(cipher.doFinal(message.getBytes("UTF-8")));
	}
	
	public static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}

		return hexString.toString();
	}
	
	public static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}
		return digest;
	}

	public static String decryptDES(String message, String key) throws Exception {
		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}
	
	/**
	 * 获得当前日期的格林威治时间戳，忽略毫秒
	 * @author muyuansun
	 * @date 2014-1-2 上午11:14:40
	 * @return
	 */
	public static String getTimestamp() {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.MILLISECOND, 0);
		long timestamp = cl.getTimeInMillis() / 1000;
		return String.valueOf(timestamp);
	}

}
