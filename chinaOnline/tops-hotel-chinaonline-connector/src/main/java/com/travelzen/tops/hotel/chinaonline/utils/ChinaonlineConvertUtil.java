package com.travelzen.tops.hotel.chinaonline.utils;

import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.joda.time.DateTime;

import com.micros.webservices.og._4_3.core.EndPoint;
import com.micros.webservices.og._4_3.core.OGHeader;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import com.travelzen.search.util.Constants;

public class ChinaonlineConvertUtil {
	/**
	 * DateTime日期类型转XMLGregorianCalendarImpl
	 * @author Loufanglei
	 * @data 2015-1-19 下午3:56:02 
	 */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(DateTime dateTime){
		final GregorianCalendar calendar = new GregorianCalendar(dateTime.getZone().toTimeZone());
		calendar.setTimeInMillis(dateTime.getMillis());
		return new XMLGregorianCalendarImpl(calendar); 
	  }
	
	/**
	 * XMLGregorianCalendar的日期类型转DateTime
	 * @author Loufanglei
	 * @data 2015-1-19 下午3:56:40 
	 */
	public static DateTime convertToDateTime(final XMLGregorianCalendar xmlgc) {
		return new DateTime(xmlgc.toGregorianCalendar().getTime());
	}
	
	/**
	 * soap协议的Header
	 * @author Loufanglei
	 * @data 2015-1-19 下午3:54:14 
	 */
	public static Holder<OGHeader> getHolder() {

		OGHeader GHeader = new OGHeader();
		EndPoint endPoint = new EndPoint();
		endPoint.setEntityID(HotelChinaOnlineConfigUtil.ORIGIN_ENTITY_ID);
		endPoint.setSystemType(HotelChinaOnlineConfigUtil.ORIGIN_SYSTEM_TYPE);
		GHeader.setOrigin(endPoint);
		EndPoint destination = new EndPoint();
		destination.setEntityID(HotelChinaOnlineConfigUtil.DESTINATION_ENTITY_ID);
		destination.setSystemType(HotelChinaOnlineConfigUtil.DESTINATION_SYSTEM_TYPE);
		GHeader.setDestination(destination);
		Holder<OGHeader> oGHeader = new Holder<OGHeader>(GHeader);
		return oGHeader;
	}
	
	/**
	 * 中文转拼音，并在姓和名之间保留一个空格
	 * @author Loufanglei
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 * @data 2015-1-19 下午3:58:09 
	 */
	public static String chineseToPinyin(String chin) {
		char[] cs = Constants.getPinyinAndSim(chin)[0].toUpperCase().toCharArray();
		StringBuilder sb = new StringBuilder();
		boolean isFilter = false;
		for (char c : cs) {
			if (' ' != c) {
				sb.append(c);
			} else {
				if (isFilter == false) {
					isFilter = true;
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}
}
