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

import com.travelzen.tops.front.hotel.chinaonline.bookingLimit.BookingLimitMessage;
import com.travelzen.tops.front.hotel.chinaonline.bookingLimit.RoomTypeSegment;
import com.travelzen.tops.front.hotel.chinaonline.service.IChinaOnlineUpdaterService;
import com.travelzen.tops.hotel.icecreme.beans.jstar.HotelAvailability;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RoomAvailability;
import com.travelzen.tops.hotel.icecreme.service.IJstarUpdateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/app-hotel-ChinaolineUpdater.xml","classpath*:spring/tops-hotel-creme-service-min-ctx.xml"})
public class BookingLimitMessageTest {
	
	@Resource
	private IChinaOnlineUpdaterService chinaOnlineUpdaterService;
	
	@Resource(name="hotel_icecremeJstarUpdateService")
	IJstarUpdateService jstarUpdateService;

	@Test
	public void test() throws IOException, JAXBException, SAXException, ParserConfigurationException{
		
		String xml = null ;
		String encoding="utf-8";
		File file  = new File("/home/loufanglei/bookinglimite1.xml");
		
		if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                        xml = lineTxt;
	                    }
	                    read.close();
	                    
	                    BookingLimitMessage bookingLimitMessage = chinaOnlineUpdaterService.XmlToBean(xml, BookingLimitMessage.class);
	                    
	                    HotelAvailability hotelAvailability = convertFromBookingLimitMessage(bookingLimitMessage);
	                    
	                    jstarUpdateService.updateAvailability(hotelAvailability);
		    	    System.out.println("<------end-------->");
		}
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
}
