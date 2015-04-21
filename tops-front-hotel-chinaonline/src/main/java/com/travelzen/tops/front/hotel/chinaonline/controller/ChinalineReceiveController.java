package com.travelzen.tops.front.hotel.chinaonline.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.tops.front.hotel.chinaonline.bookingLimit.BookingLimitMessage;
import com.travelzen.tops.front.hotel.chinaonline.bookingLimit.RoomTypeSegment;
import com.travelzen.tops.front.hotel.chinaonline.common.MessageResult;
import com.travelzen.tops.front.hotel.chinaonline.common.PublisherRequest;
import com.travelzen.tops.front.hotel.chinaonline.rateupdate.RateUpdateMessage;
import com.travelzen.tops.front.hotel.chinaonline.rateupdate.RateUpdateSegment;
import com.travelzen.tops.front.hotel.chinaonline.restriction.AvailUpdateSegment;
import com.travelzen.tops.front.hotel.chinaonline.restriction.RestrictionMessage;
import com.travelzen.tops.front.hotel.chinaonline.service.IChinaOnlineUpdaterService;
import com.travelzen.tops.hotel.icecreme.beans.jstar.HotelAmount;
import com.travelzen.tops.hotel.icecreme.beans.jstar.HotelAvailability;
import com.travelzen.tops.hotel.icecreme.beans.jstar.HotelRestriction;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RateAmount;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RatePlanAmount;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RoomAvailability;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RoomRestriction;
import com.travelzen.tops.hotel.icecreme.service.IJstarUpdateService;

@Controller
@RequestMapping("/hotel/chinaonline")
public class ChinalineReceiveController {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private static final String CHINA_ONLINE_RESPONSE_FAIL_STR = "fail";
	private static final String CHINA_ONLINE_RESPONSE_SUCCESS_STR = "success";
	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
	 
	@Resource(name="hotel_icecremeJstarUpdateService")
	private IJstarUpdateService jstarUpdateService;

	@Resource(name="ChinaOnlineUpdater")
	private IChinaOnlineUpdaterService chinaOnlineUpdaterService;
	@RequestMapping(value="/Update",method = RequestMethod.POST)
	public void chinolineUpdate( HttpServletRequest request , HttpServletResponse response) {
		
		String responseXml = null;
		PublisherRequest publisherRequest = null;
		MessageResult result = new MessageResult();
		result.setResult(CHINA_ONLINE_RESPONSE_SUCCESS_STR);
		
		try{
			String chinaOnlinePushDataXML = chinaOnlineUpdaterService.requestToXML(request);
			LOG.info("畅联推送数据:{}", chinaOnlinePushDataXML);
		  	publisherRequest = chinaOnlineUpdaterService.XmlToBean(chinaOnlinePushDataXML,  PublisherRequest.class);

		  	if (publisherRequest == null) {
		  		throw new Exception("畅联推送数据转换为Bean时结果为空");
		  	} else {
		  		if("BookingLimitMessage".equals(publisherRequest.getType())){
	  				BookingLimitMessage bookingLimitMessage = null;
		  			bookingLimitMessage = chinaOnlineUpdaterService.XmlToBean(chinaOnlinePushDataXML,  BookingLimitMessage.class);
		  			HotelAvailability hotelAvailability =convertFromBookingLimitMessage(bookingLimitMessage);
		  			jstarUpdateService.updateAvailability(hotelAvailability);
		  		}
		  		else if("AvailabilityRestrictionMessage".equals(publisherRequest.getType())){
		  			RestrictionMessage restrictionMessage =null;
		  			restrictionMessage = chinaOnlineUpdaterService.XmlToBean(chinaOnlinePushDataXML,  RestrictionMessage.class);
		  			HotelRestriction hotelRestriction = convertFromRestrictionMessage(restrictionMessage);
		  			jstarUpdateService.updateRestriction(hotelRestriction);
		  		}
		  		else if("RateMessage".equals(publisherRequest.getType())){
	  				RateUpdateMessage rateMessage = null;
		  			rateMessage = chinaOnlineUpdaterService.XmlToBean(chinaOnlinePushDataXML,  RateUpdateMessage.class);
		  			HotelAmount hotelAmount = convertFromRateUpdateMessage(rateMessage);
					jstarUpdateService.updateRate(hotelAmount);
		  		}
		  	}

		} catch (JAXBException | SAXException | ParserConfigurationException e) {
			result.setResult(CHINA_ONLINE_RESPONSE_FAIL_STR);
			LOG.info("畅联推送数据xml装换错误",e);
		} catch (Exception e) {
			result.setResult(CHINA_ONLINE_RESPONSE_FAIL_STR);
			LOG.info("畅联推送数据入库失败",e);
		}

		try {
			responseXml = chinaOnlineUpdaterService.BeanToXml(result, MessageResult.class);
			response.getWriter().write(responseXml);
		} catch (IOException | JAXBException e) {
			LOG.error("畅联推送数据写回失败", e);
		}
	}
	
	private HotelAmount convertFromRateUpdateMessage(RateUpdateMessage rateUpdateMessage) {
		HotelAmount hotelAmount = new HotelAmount();
		com.travelzen.tops.front.hotel.chinaonline.rateupdate.Property property = rateUpdateMessage.getRateUpdateNotifRQ().getProperties().getProperty();
		hotelAmount.setHotelCode(property.getHotelCode());
		List<RateUpdateSegment> segments = property.getRateUpdateSegments().getRateUpdateSegmentList();
		List<RatePlanAmount> ratePlanAmounts = new ArrayList<RatePlanAmount>();
		for (RateUpdateSegment segment : segments) {
			RatePlanAmount ratePlanAmount = new RatePlanAmount();
			ratePlanAmount.setRoomCode(segment.getRoomTypeCode());
			ratePlanAmount.setRateCode(segment.getRateCode());
			DateTime startDate = new DateTime(segment.getTimeSpan().getStart());
			DateTime endDate = new DateTime(segment.getTimeSpan().getEnd());
			DateTime startDateWithoutTime = new DateTime(DateTimeUtil.format(startDate, DateTimeUtil.DATE_PATTERN));
			DateTime endDateWithoutTime = new DateTime(DateTimeUtil.format(endDate, DateTimeUtil.DATE_PATTERN));
			int duration =(int)( (endDateWithoutTime.getMillis() - startDateWithoutTime.getMillis()) / MILLIS_PER_DAY);
			List<RateAmount> rateAmounts = new ArrayList<RateAmount>();
			for (int i = 0; i <= duration; ++i) {
				com.travelzen.tops.front.hotel.chinaonline.rateupdate.RateAmount  ra = segment.getRateAmounts().getRateAmountList().get(0);
					RateAmount rateAmount = new RateAmount();
					rateAmount.setDate(startDate.plusDays(i));
					rateAmount.setOnePerson(Double.valueOf(ra.getAmountType()));
					rateAmounts.add(rateAmount);
			}
			ratePlanAmount.setRateAmounts(rateAmounts);
			ratePlanAmounts.add(ratePlanAmount);
		}
		hotelAmount.setRatePlanAmounts(ratePlanAmounts);
		
		return hotelAmount;
	}
	
	private HotelAvailability convertFromBookingLimitMessage(BookingLimitMessage bookingLimitMessage) {
		HotelAvailability hotelAvailability = new HotelAvailability();
		com.travelzen.tops.front.hotel.chinaonline.bookingLimit.Property property = bookingLimitMessage.getAvailabilityStatusRQ().getProperties().getProperty();
		hotelAvailability.setHotelCode(property.getHotelCode());
		hotelAvailability.setStartDate(new DateTime(property.getStartDate()));
		List<RoomAvailability> roomAvailabilities = new ArrayList<RoomAvailability>();
		List<RoomTypeSegment> segments = property.getRoomTypeSegments().getRoomTypeSegmentList();
		for (RoomTypeSegment segment : segments) {
			RoomAvailability roomAvailability = new RoomAvailability();
			roomAvailability.setRoomCode(segment.getRoomTypeCode());
			roomAvailability.setAvailable(Integer.valueOf(segment.getRoomsAvailable()));
			roomAvailabilities.add(roomAvailability);
		}
		hotelAvailability.setRoomAvailabilities(roomAvailabilities);
		return hotelAvailability;
	}

	private HotelRestriction convertFromRestrictionMessage(RestrictionMessage restrictionMessage) {
		HotelRestriction hotelRestriction = new HotelRestriction();
		com.travelzen.tops.front.hotel.chinaonline.restriction.Property property = restrictionMessage.getAvailUpdateNotifRQ().getProperties().getProperty();
		hotelRestriction.setHotelCode(property.getHotelCode());
		List<RoomRestriction> roomRestrictions = new ArrayList<RoomRestriction>();
		List<AvailUpdateSegment> segments = property.getAvailUpdateSegments().getAvailUpdateSegment();
		for (AvailUpdateSegment segment : segments) {
			RoomRestriction roomRestriction = new RoomRestriction();
			roomRestriction.setRoomCode(segment.getRoomTypeCode());
			roomRestriction.setRateCode(segment.getRateCode());
			roomRestriction.setOpen(segment.getActionCode().equals("Open"));
			roomRestriction.setTargetGDS(segment.getTargetGDS());
			DateTime startDate = new DateTime(segment.getTimeSpan().getStart());
			DateTime endDate = new DateTime(segment.getTimeSpan().getEnd());
			DateTime startDateWithoutTime = new DateTime(DateTimeUtil.format(startDate, DateTimeUtil.DATE_PATTERN));
			DateTime endDateWithoutTime = new DateTime(DateTimeUtil.format(endDate, DateTimeUtil.DATE_PATTERN));
			int duration =(int)( (endDateWithoutTime.getMillis() - startDateWithoutTime.getMillis()) / MILLIS_PER_DAY);
			roomRestriction.setDuration(duration);
			roomRestriction.setStartDate(startDate);
			roomRestriction.setEndDate(endDate);
			roomRestrictions.add(roomRestriction);
		}
		hotelRestriction.setRoomRestrictions(roomRestrictions);
		return hotelRestriction;
	}

	
}
