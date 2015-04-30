package com.travelzen.tops.hotel.elong.staticfile.parser;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.service.BaseTest;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.HotelListParser;

public class HotelListParserTest extends BaseTest {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IHotelStaticFileDownloadService hotelStaticFileDownloadService = null;
	
	@Test
	public void testParse(){
		HotelListParser hotelListParser = new HotelListParser(getInputStream("hotelList/hotellist_mini.xml"));
		Assert.assertNotNull(hotelListParser.getHotelIndex());
		LOG.info("[hotel size = {}]",hotelListParser.getHotelIndex().size());
	}
	
	@Test
	public void testFullParse(){
		HotelListParser hotelListParser = new HotelListParser(getInputStream("hotelList/hotellist.xml"));
		Assert.assertNotNull(hotelListParser.getHotelIndex());
		LOG.info("[hotel size = {}]",hotelListParser.getHotelIndex().size());
	}
	
	@Test
	public void testDownloadAndFullParse() throws FileDownloadException{
		HotelListParser hotelListParser = (HotelListParser) hotelStaticFileDownloadService.downloadHotelList();
		Assert.assertNotNull(hotelListParser.getHotelIndex());
		LOG.info("[hotel size = {}]",hotelListParser.getHotelIndex().size());
	}
	
}
