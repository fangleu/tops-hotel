package com.travelzen.tops.hotel.elong;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.travelzen.tops.hotel.elong.common.constants.CommonConstants;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.ElongRequestParameter;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;
import com.travelzen.tops.hotel.elong.common.utils.ElongInterUtil;
import com.travelzen.tops.hotel.elong.connector.controller.ElongTool;

public class HotelInterfaceTest extends BaseTest {
	
//	private static final String HOTEL_INTERFACE_URL = "http://hotel.if.tdxinfo.com/hotel-interface/hotel";
	
//	private static final String HOTEL_INTERFACE_URL = "http://127.0.0.1:8083/tops-hotel-elong-connector/hotel";
	
	private static final String HOTEL_INTERFACE_URL = "http://192.168.164.222:8980/tops-hotel-elong-connector/hotel";
	
//	private static final String HOTEL_INTERFACE_URL = "http://192.168.161.72:8980/tops-hotel-elong-connector/hotel";

	@Resource
	private ElongConfiguration elongConfiguration = null;
	@Test
	public void testRequesthotelListByJson() throws IOException {
		String fileNamePrefix = "request/hotel_list/hotelList";
		String fileNameSuffix = "Request";
		String format = "json";
		String method = "hotel.list";
		// data
		String requestPath = fileNamePrefix + fileNameSuffix + "." + format;
		String data = ElongInterUtil.getFileContent(requestPath);
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(data);
		// 请求url
		String requestUrl = generateRequestUrl(format, method, dataUTF8String);
		LOG.info(requestUrl);
		String result = doRequest(requestUrl,false);
		LOG.info(result);
		writeToFile(requestPath, fileNameSuffix, format, result, true);
	}

	@Test
	public void testRequesthotelDetailsByJson() throws IOException {
		String fileNamePrefix = "request/hotel_detail/hotelDetail";
		String fileNameSuffix = "Request";
		String format = "json";
		String method = "hotel.detail";
		// data
		String requestPath = fileNamePrefix + fileNameSuffix + "." + format;
		String data = ElongInterUtil.getFileContent(requestPath);
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(data);
		// 请求url
		String requestUrl = generateRequestUrl(format, method, dataUTF8String);
		LOG.info(requestUrl);
		String result = doRequest(requestUrl,true);
		writeToFile(requestPath, fileNameSuffix, format, result, true);
	}

	// --------------orders---------------------

	@Test
	public void testHotelOrderCreate() throws IOException {
		String fileNamePrefix = "request/hotel_order_create/hotelOrderCreate";
		String fileNameSuffix = "Request";
		String format = "json";
		String method = "hotel.order.create";
		// data
		String requestPath = fileNamePrefix + fileNameSuffix + "." + format;
		String data = ElongInterUtil.getFileContent(requestPath);
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(data);
		// 请求url
		String requestUrl = generateRequestUrl(format, method, dataUTF8String);
		LOG.info(requestUrl);
		String result = doRequest(requestUrl,false);
		LOG.info(result);
		writeToFile(requestPath, fileNameSuffix, format, result, true);
	}
	
	private String validateCreditcard(String creditCard) throws Exception{
		String format = "json";
		String method = "common.creditcard.validate";
		StringBuffer sb = new StringBuffer();
		sb.append("{\"Local\": \"zh_CN\", \"Request\": {\"CreditCardNo\":\"");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-eni*.xml");
		ElongConfiguration configuration = (ElongConfiguration) context.getBean("elongConfiguration");
		String appkey = configuration.get(CommonConstants.ElongConfigurationKey.APP_KEY.keyName());
		String des = ElongTool.encryptDES(ElongInterUtil.getTimestamp()+"#" + creditCard, appkey.substring(appkey.length()-8));
		sb.append(des);
		sb.append("\"},\"Version\": 1.1}");
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(sb.toString());
		String requestUrl = generateRequestUrl(format, method, dataUTF8String);
		LOG.info(requestUrl);
		return doRequest(requestUrl,true);
	}
	
	@Test
	public void testValidateCreditcard() throws Exception{

		String result = validateCreditcard("5227830611178761");
		System.out.println(result);
	}
	
	@Test
	public void testHotelOrderDetail() throws IOException {
		String fileNamePrefix = "request/hotel_order_details/hotelOrderDetails";
		String fileNameSuffix = "Request";
		String format = "json";
		String method = "hotel.order.detail";
		// data
		String requestPath = fileNamePrefix + fileNameSuffix + "." + format;
		String data = ElongInterUtil.getFileContent(requestPath);
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(data);
		// 请求url
		String requestUrl = generateRequestUrl(format, method, dataUTF8String);
		LOG.info(requestUrl);
		String result = doRequest(requestUrl,false);
		LOG.info(result);
		writeToFile(requestPath, fileNameSuffix, format, result, true);

	}

	@Test
	public void testHotelOrderUpdate() {
		String format = "json";
		String requestPath = "request/hotel_order/hotelOrderUpdate." + format;
		String method = "hotel.order.update";

		String data = ElongInterUtil.getFileContent(requestPath);
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(data);
		// 请求url
		String requestUrl = generateRequestUrl(format, method, dataUTF8String);
		LOG.info(requestUrl);
		String result = doRequest(requestUrl,true);
		System.out.println(result);
	}

	public String generateRequestUrl(String format, String method,
			String dataUTF8String) {
		StringBuffer sb = new StringBuffer(HOTEL_INTERFACE_URL + "?");
		sb.append(ElongRequestParameter.METHOD.paramName());
		sb.append("=");
		sb.append(method);
		sb.append("&");
		sb.append(ElongRequestParameter.FORMAT.paramName());
		sb.append("=");
		sb.append(format);
		sb.append("&");
		sb.append(ElongRequestParameter.DATA.paramName());
		sb.append("=");
		sb.append(dataUTF8String);
		return sb.toString();
	}

}
