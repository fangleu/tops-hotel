package com.travelzen.tops.hotel.chinaonline.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.Holder;

import org.apache.commons.lang3.time.StopWatch;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tempuri.AvailabilityServiceSoap;
import org.tempuri.ReservationServiceSoap;

import cn.net.chinaonline.webservices._switch._1_5_1.reservation.CreateBookingRequest;

import com.micros.webservices.og._4_3.availability.AvailResponseSegment;
import com.micros.webservices.og._4_3.common.ResultStatusFlag;
import com.micros.webservices.og._4_3.common.UniqueID;
import com.micros.webservices.og._4_3.core.OGHeader;
import com.micros.webservices.og._4_3.hotelcommon.ChargesForTheDay;
import com.micros.webservices.og._4_3.hotelcommon.DailyChargeList;
import com.micros.webservices.ows._5_1.availability.AvailabilityRequest;
import com.micros.webservices.ows._5_1.availability.AvailabilityResponse;
import com.micros.webservices.ows._5_1.reservation.CancelBookingRequest;
import com.micros.webservices.ows._5_1.reservation.CancelBookingResponse;
import com.micros.webservices.ows._5_1.reservation.CreateBookingResponse;
import com.travelzen.tops.hotel.chinaonline.bean.CancelOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.CancelOrderResponse;
import com.travelzen.tops.hotel.chinaonline.bean.CreateOrderRequest;
import com.travelzen.tops.hotel.chinaonline.bean.CreateOrderResponse;
import com.travelzen.tops.hotel.chinaonline.bean.QueryRoomRequest;
import com.travelzen.tops.hotel.chinaonline.bean.QueryRoomResponse;
import com.travelzen.tops.hotel.chinaonline.service.IChinaOnlineSoapService;
import com.travelzen.tops.hotel.chinaonline.utils.AvailabilityConvert;
import com.travelzen.tops.hotel.chinaonline.utils.ChinaonlineConnectUtil;
import com.travelzen.tops.hotel.chinaonline.utils.ChinaonlineConvertUtil;
import com.travelzen.tops.hotel.chinaonline.utils.ReservationConvert;

@Service(value="chinaOnlineSoapService")
public class ChinaOnlineSoapService implements IChinaOnlineSoapService{
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Override
	public QueryRoomResponse chinaonlineAvailability(QueryRoomRequest request) throws Exception {
		
		AvailabilityConvert.checkRequest(request);
		AvailabilityRequest availabilityRequest = AvailabilityConvert.converCheckPriceRequest(request);
	        Holder<OGHeader> oGHeader = ChinaonlineConvertUtil.getHolder();
	        AvailabilityServiceSoap service = ChinaonlineConnectUtil.getChinaonlineAvailabilityService();
	        StopWatch stopwatch = new StopWatch();
	        stopwatch.start();
	        AvailabilityResponse  availabilityResponse = service.availability(availabilityRequest, oGHeader);
	        stopwatch.stop();
	        LOG.info("畅联验价耗时{}ms", stopwatch.getTime());
	        stopwatch.reset();
	        QueryRoomResponse response = new QueryRoomResponse();
	        if(availabilityResponse.getAvailResponseSegments()!=null){
		        List<AvailResponseSegment> availResponseSegmentList = availabilityResponse.getAvailResponseSegments().getAvailResponseSegment();
		        AvailResponseSegment availResponseSegment = availResponseSegmentList.get(0);
		        DailyChargeList dailyChargeList =availResponseSegment.getRoomStayList().getRoomStay().get(0).getExpectedCharges();
		        List<ChargesForTheDay> chargesForTheDay = dailyChargeList.getChargesForPostingDate();
		        Map<DateTime,Double> priceMap = new HashMap<DateTime,Double>();
		        for(int i = 0; i<chargesForTheDay.size(); i++){
		        	DateTime date = null;
		        	Double price;
		        	date = ChinaonlineConvertUtil.convertToDateTime(chargesForTheDay.get(i).getPostingDate());
		        	price = chargesForTheDay.get(i).getRoomRateAndPackages().getTotalCharges();
		        	priceMap.put(date , price);
		        }
		        response.setPriceList(priceMap);
	        }
		return response;
	}

	/**
	 * 创建订单
	 * @author Loufanglei
	 * @throws Exception 
	 * @data 2015-1-13 上午11:11:18 
	 */
	@Override
	public CreateOrderResponse chinaonlineCreateOrder(CreateOrderRequest request) throws Exception {
		
		ReservationConvert.checkRequest(request);
		CreateBookingRequest createBookingRequest = ReservationConvert.convertCreateOrderRequest(request);
		Holder<OGHeader> oGHeader = ChinaonlineConvertUtil.getHolder();
		ReservationServiceSoap service = ChinaonlineConnectUtil.getChinaonlineReservationService();
		StopWatch stopwatch = new StopWatch();
	      	stopwatch.start();
		CreateBookingResponse createBookingResponse = service.createBooking(createBookingRequest, oGHeader);
		stopwatch.stop();
	        LOG.info("畅联建单耗时{}ms", stopwatch.getTime());
	        stopwatch.reset();
		CreateOrderResponse createOrderResponse = new CreateOrderResponse();
		if(ResultStatusFlag.SUCCESS.equals(createBookingResponse.getResult().getResultStatusFlag())){
			createOrderResponse.setResult(true);
			UniqueID uniqueID = createBookingResponse.getHotelReservation().getUniqueIDList().getUniqueID().get(0);
			createOrderResponse.setHotelConfirmCode(uniqueID.getValue());
		}else{
			createOrderResponse.setResult(false);
			createOrderResponse.setErrorText(createBookingResponse.getResult().getGDSError().getValue());
		}
		return createOrderResponse;
	}

	/**
	 * 取消订单
	 * @author Loufanglei
	 * @data 2015-1-20 下午5:44:33
	 */
	@Override
	public CancelOrderResponse cancelOrder(CancelOrderRequest request) {
		CancelOrderResponse response= new CancelOrderResponse();
		
		CancelBookingRequest cancelBookingRequest;
		cancelBookingRequest = ReservationConvert.convertCancelOrderRequest(request);
		Holder<OGHeader> oGHeader = ChinaonlineConvertUtil.getHolder();
		ReservationServiceSoap service = ChinaonlineConnectUtil.getChinaonlineReservationService(
				);
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		CancelBookingResponse cancelBookingResponse = service.cancelBooking(cancelBookingRequest, oGHeader);
		stopwatch.stop();
	        LOG.info("畅联取消订单耗时{}ms", stopwatch.getTime());
	        stopwatch.reset();
		if(cancelBookingResponse.getResult().getResultStatusFlag().equals(ResultStatusFlag.SUCCESS)){
			response.setResult(true);
		} else {
			response.setResult(false);
			response.setErrorText(cancelBookingResponse.getResult().getGDSError().getValue());
		}
		return response;
	}
	
}
