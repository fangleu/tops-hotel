package com.travelzen.tops.hotel.elong.controller;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import com.travelzen.tops.hotel.elong.BaseTest;
import com.travelzen.tops.hotel.elong.common.constants.CommonConstants.ElongRequestParameter;
import com.travelzen.tops.hotel.elong.common.utils.ElongInterUtil;
import com.travelzen.tops.hotel.elong.connector.controller.HotelController;

@SpringApplicationContext({"classpath:spring/applicationContext-hotel-interface.xml","classpath:spring/webApplicationContext-hotel-interface.xml","classpath*:spring/applicationContext-eni*.xml"})
public class HotelControllerTest extends BaseTest {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@SpringBeanByType
	private AnnotationMethodHandlerAdapter handlerAdapter;
	
	@SpringBeanByType
	private HotelController hotelController;
	
	private MockHttpServletRequest request;
	
	private MockHttpServletResponse response;
	
	@Before
	public void before(){
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void testHotelListByJson() throws Exception{
		String fileNamePrefix = "request/hotel_list/hotelList";
		String fileNameSuffix = "Request";
		String format = "json";
		request.setRequestURI("/hotel");
		//method
		request.addParameter(ElongRequestParameter.METHOD.paramName(), "hotel.list");
		//format
		request.addParameter(ElongRequestParameter.FORMAT.paramName(), format);
		//data
		String requestPath = fileNamePrefix + fileNameSuffix + "." + format;
		String data = ElongInterUtil.getFileContent(requestPath);
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(data);
		request.addParameter(ElongRequestParameter.DATA.paramName(), dataUTF8String);
		request.setMethod("GET");
		handlerAdapter.handle(request, response, hotelController);
		LOG.info(response.getContentAsString());
//		HotelListResult testResult = JSON.parseObject(response.getContentAsString().replaceAll("0001-01-01T00:00:00", "2001-01-01T00:00:00"),HotelListResult.class);
//		LOG.info(testResult.getCode());
//		LOG.info(testResult.getResult().getHotels().get(0).getHotelId());
//		LOG.info(testResult.getResult().getHotels().get(0).getDetail().getHotelName());
		writeToFile(requestPath, fileNameSuffix, format, response.getContentAsString(),false);
//		String timestamp = ElongInterUtil.getTimestamp();
		//user
//		request.addParameter(ElongRequestParameter.USER.paramName(), "smy");
		//timestamp
//		request.addParameter(ElongRequestParameter.TIMESTAMP.paramName(), timestamp);
		//signature
//		String signature = ElongInterUtil.getSignature(timestamp, "0f4b4be05aef8304171f463aeedfe064", "12172180afa0f965cfd8b7998c779d38", data);
//		request.addParameter(ElongRequestParameter.SIGNATURE.paramName(), signature);
	}
	
	@Test
	public void testHotelListByXml() throws Exception{
		String fileNamePrefix = "request/hotel_list/hotelList";
		String fileNameSuffix = "Request";
		String format = "xml";
		request.setRequestURI("/hotel");
		//method
		request.addParameter(ElongRequestParameter.METHOD.paramName(), "hotel.list");
		//format
		request.addParameter(ElongRequestParameter.FORMAT.paramName(), format);
		//data
		String requestPath = fileNamePrefix + fileNameSuffix + "." + format;
		String data = ElongInterUtil.getFileContent(requestPath);
		String dataUTF8String = ElongInterUtil.getURLStringByUTF8Encode(data);
		request.addParameter(ElongRequestParameter.DATA.paramName(), dataUTF8String);
		request.setMethod("GET");
		handlerAdapter.handle(request, response, hotelController);
		LOG.info(response.getContentAsString());
		writeToFile(requestPath, fileNameSuffix, format, response.getContentAsString(),false);
	}

}
