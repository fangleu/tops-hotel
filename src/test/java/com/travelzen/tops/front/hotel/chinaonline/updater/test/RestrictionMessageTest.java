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
import com.travelzen.tops.front.hotel.chinaonline.restriction.AvailUpdateSegment;
import com.travelzen.tops.front.hotel.chinaonline.restriction.RestrictionMessage;
import com.travelzen.tops.front.hotel.chinaonline.service.IChinaOnlineUpdaterService;
import com.travelzen.tops.hotel.icecreme.beans.jstar.HotelRestriction;
import com.travelzen.tops.hotel.icecreme.beans.jstar.RoomRestriction;
import com.travelzen.tops.hotel.icecreme.service.IJstarUpdateService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/app-hotel-ChinaolineUpdater.xml","classpath*:spring/tops-hotel-creme-service-min-ctx.xml"})
public class RestrictionMessageTest {

	@Resource
	private IChinaOnlineUpdaterService chinaOnlineUpdaterService;
	
	@Resource(name="hotel_icecremeJstarUpdateService")
	IJstarUpdateService jstarUpdateService;
	
	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
	
	@Test
	public void test() throws IOException, JAXBException, SAXException, ParserConfigurationException{
		
		String xml = null ;
		String encoding="utf-8";
		File file  = new File("/home/loufanglei/桌面/ADS_Example_AvailabilityRestriction_价格状态11.xml");
		
		if(file.isFile() && file.exists()){ //判断文件是否存在
	                    InputStreamReader read = new InputStreamReader(
	                    new FileInputStream(file),encoding);//考虑到编码格式
	                    BufferedReader bufferedReader = new BufferedReader(read);
	                    String lineTxt = null;
	                    while((lineTxt = bufferedReader.readLine()) != null){
	                        xml = lineTxt;
	                    }
	                    read.close();
	                    RestrictionMessage restrictionMessage = chinaOnlineUpdaterService.XmlToBean(xml, RestrictionMessage.class);
	                    HotelRestriction hotelRestriction = convertFromRestrictionMessage(restrictionMessage);
	                    jstarUpdateService.updateRestriction(hotelRestriction);
	                    System.out.println(restrictionMessage.getType());
		    	    System.out.println("<------end-------->");
		}
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
