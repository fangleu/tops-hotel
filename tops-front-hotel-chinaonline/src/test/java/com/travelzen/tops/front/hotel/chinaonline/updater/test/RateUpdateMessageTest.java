package com.travelzen.tops.front.hotel.chinaonline.updater.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.tops.front.hotel.chinaonline.bookingLimit.BookingLimitMessage;
import com.travelzen.tops.front.hotel.chinaonline.bookingLimit.RoomTypeSegment;
import com.travelzen.tops.front.hotel.chinaonline.rateupdate.RateUpdateMessage;
import com.travelzen.tops.front.hotel.chinaonline.rateupdate.RateUpdateSegment;
import com.travelzen.tops.front.hotel.chinaonline.service.IChinaOnlineUpdaterService;
import com.travelzen.tops.hotel.icecreme.beans.jstar.HotelAmount;
import com.travelzen.tops.hotel.icecreme.beans.jstar.HotelAvailability;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RateAmount;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RatePlanAmount;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RoomAvailability;
import com.travelzen.tops.hotel.icecreme.service.IJstarUpdateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/app-hotel-ChinaolineUpdater.xml","classpath*:spring/tops-hotel-creme-service-min-ctx.xml"})
public class RateUpdateMessageTest {
	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

	@Resource(name="hotel_icecremeJstarUpdateService")
	IJstarUpdateService jstarUpdateService;
	
	
	@Resource
	private IChinaOnlineUpdaterService chinaOnlineUpdaterService;

	@Test
	public void test() throws IOException, JAXBException, SAXException, ParserConfigurationException {
//		jstarUpdateService = new JstarUpdateService();
		
		String xml = null ;
		String encoding="utf-8";
		File file  = new File("/home/loufanglei/rateUpdate.xml");
		
		if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                        xml = lineTxt;
	                    }
	                    read.close();
	                    
	                    RateUpdateMessage rateUpdateMessage = chinaOnlineUpdaterService.XmlToBean(xml, RateUpdateMessage.class);
	                    
	                    HotelAmount hotelAmount = convertFromRateUpdateMessage(rateUpdateMessage);
	                    jstarUpdateService.updateRate(hotelAmount);
	                    
		    	    System.out.println("<------end-------->");
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
			DateTime s = new DateTime(DateTimeUtil.format(startDate, DateTimeUtil.DATE_PATTERN));
			DateTime e = new DateTime(DateTimeUtil.format(endDate, DateTimeUtil.DATE_PATTERN));
			int duration =(int)( (e.getMillis() - s.getMillis()) / MILLIS_PER_DAY);
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
	
	@SuppressWarnings("unused")
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
	
}
