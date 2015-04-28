package com.travelzen.tops.hotel.chinaonline.utils;

import com.micros.webservices.og._4_3.availability.ArrayOfHotelSearchCriterion;
import com.micros.webservices.og._4_3.availability.ArrayOfRatePlanCandidate;
import com.micros.webservices.og._4_3.availability.ArrayOfRoomStayCandidate;
import com.micros.webservices.og._4_3.availability.AvailRequestSegment;
import com.micros.webservices.og._4_3.availability.HotelSearchCriterion;
import com.micros.webservices.og._4_3.availability.RatePlanCandidate;
import com.micros.webservices.og._4_3.availability.RoomStayCandidate;
import com.micros.webservices.og._4_3.hotelcommon.AvailRequestType;
import com.micros.webservices.og._4_3.hotelcommon.HotelReference;
import com.micros.webservices.og._4_3.hotelcommon.TimeSpan;
import com.micros.webservices.ows._5_1.availability.AvailabilityRequest;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.hotel.chinaonline.bean.QueryRoomRequest;

public class AvailabilityConvert {

	public static AvailabilityRequest converCheckPriceRequest(QueryRoomRequest request ){

		AvailabilityRequest availabilityRequest = new AvailabilityRequest();
		availabilityRequest.setLoadRateOnly(Boolean.TRUE);
		availabilityRequest.setSummaryOnly(false);

		AvailRequestSegment availRequestSegment = new AvailRequestSegment();
		availRequestSegment.setAvailReqType(AvailRequestType.ROOM);
		availRequestSegment.setNumberOfRooms(request.getRoomNumber());			//
		availRequestSegment.setTotalNumberOfGuests(request.getGuestNum());			//

		TimeSpan timeSpan = new TimeSpan();
		timeSpan.setStartDate(ChinaonlineConvertUtil.convertToXMLGregorianCalendar(request.getStartDate())); 	 //
		timeSpan.setEndDate(ChinaonlineConvertUtil.convertToXMLGregorianCalendar(request.getEndDate()));		//
		availRequestSegment.setStayDateRange(timeSpan);

		ArrayOfRatePlanCandidate arrayOfRatePlanCandidate = new ArrayOfRatePlanCandidate();
		RatePlanCandidate ratePlanCandidate = new RatePlanCandidate();
		ratePlanCandidate.setRatePlanCode(request.getRateCode());						//
		ratePlanCandidate.setQualifyingIdType(HotelChinaOnlineConfigUtil.QUALIFYING_ID_TYPE);
		ratePlanCandidate.setQualifyingIdValue(HotelChinaOnlineConfigUtil.QUALIFYING_ID_VALUE);
		arrayOfRatePlanCandidate.getRatePlanCandidate().add(ratePlanCandidate);
		availRequestSegment.setRatePlanCandidates(arrayOfRatePlanCandidate);

		ArrayOfRoomStayCandidate arrayOfRoomStayCandidate = new ArrayOfRoomStayCandidate();
		RoomStayCandidate roomStayCandidate = new RoomStayCandidate();
		roomStayCandidate.setRoomTypeCode(request.getRoomCode());					//
		arrayOfRoomStayCandidate.getRoomStayCandidate().add(roomStayCandidate);
		availRequestSegment.setRoomStayCandidates(arrayOfRoomStayCandidate);

		ArrayOfHotelSearchCriterion arrayOfHotelSearchCriterion = new ArrayOfHotelSearchCriterion();
		HotelSearchCriterion hotelSearchCriterion = new HotelSearchCriterion();
		HotelReference hotelReference = new HotelReference();
		hotelReference.setChainCode(HotelChinaOnlineConfigUtil.CHINCODE);
		hotelReference.setHotelCode(request.getHotelCode());					//
		hotelSearchCriterion.setHotelRef(hotelReference);
		arrayOfHotelSearchCriterion.getCriterion().add(hotelSearchCriterion);

		availRequestSegment.setHotelSearchCriteria(arrayOfHotelSearchCriterion);
		availabilityRequest.getAvailRequestSegment().add(availRequestSegment);

		return availabilityRequest;
	} 

	/**
	 * 参数检查
	 * @author Loufanglei
	 * @data 2015-1-21 下午3:32:15
	 */
	public static boolean checkRequest(QueryRoomRequest request ) throws Exception{
		if(ValidationUtil.isNull(request, request.getHotelCode(),
			request.getStartDate(), request.getEndDate(),
			request.getRateCode(), request.getRoomNumber())) {
			throw new Exception("验价接口参数校验不通过");
		}
		return true;
	}

}
