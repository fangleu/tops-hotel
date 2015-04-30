package com.travelzen.tops.hotel.elong.staticfile.parser;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.common.exception.InvaildParameterException;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Image;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.ImageLocation;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Room;
import com.travelzen.tops.hotel.elong.service.BaseTest;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.HotelDetailParser;

public class HotelDetailParserTest extends BaseTest {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IHotelStaticFileDownloadService hotelStaticFileDownloadService = null;
	
	@Test
	public void testParseHotelId(){
		HotelDetailParser hotelDetailParser = new HotelDetailParser(getInputStream("hotelDetail/00101579.xml"));
		//Detail
		Assert.assertNotNull(hotelDetailParser.getHotel());
		Assert.assertNotNull(hotelDetailParser.getHotel().getHotelId());
		Assert.assertNotNull(hotelDetailParser.getHotel().getDetail());
		Assert.assertNotNull(hotelDetailParser.getHotel().getDetail().getSuppliers());
//		Assert.assertEquals(5, hotelDetailParser.getHotel().getDetail().getStarRate());
//		Assert.assertEquals(5, hotelDetailParser.getHotel().getDetail().getCategory());
//		Assert.assertEquals("010-65286666、010-65225286", hotelDetailParser.getHotel().getDetail().getPhone());
//		Assert.assertEquals("010-65132005", hotelDetailParser.getHotel().getDetail().getFax());
//		Assert.assertEquals(6, hotelDetailParser.getHotel().getDetail().getBrandId());
		LOG.info("[BrandId = {}]",hotelDetailParser.getHotel().getDetail().getBrandId());
		LOG.info("[GroupId = {}]",hotelDetailParser.getHotel().getDetail().getGroupId());
//		Assert.assertEquals(4, hotelDetailParser.getHotel().getDetail().getSuppliers().size());
//		Assert.assertNotNull(hotelDetailParser.getHotel().getDetail().getFacilities());
		LOG.info("[Facilities Info = {}]",hotelDetailParser.getHotel().getDetail().getFacilities());
		LOG.info("[GoogleLat = {}]",hotelDetailParser.getHotel().getDetail().getGoogleLat());
		LOG.info("[GoogleLon = {}]",hotelDetailParser.getHotel().getDetail().getGoogleLon());
		//Rooms
		Assert.assertNotNull(hotelDetailParser.getHotel().getRooms());
//		Assert.assertEquals(13,hotelDetailParser.getHotel().getRooms().size());
//		Assert.assertEquals("1113", hotelDetailParser.getHotel().getRooms().get(0).getId());
//		Assert.assertEquals("1114", hotelDetailParser.getHotel().getRooms().get(1).getId());
//		Assert.assertEquals("1115", hotelDetailParser.getHotel().getRooms().get(2).getId());
//		Assert.assertEquals("1116", hotelDetailParser.getHotel().getRooms().get(3).getId());
//		Assert.assertEquals("1117", hotelDetailParser.getHotel().getRooms().get(4).getId());
		LOG.info("---------------------------------------roomsCustomFormat-----------------------------------------------");
		
		//roomsCustomFormat
		Assert.assertNotNull(hotelDetailParser.getHotel().getRoomsCustomFormat());
//		Assert.assertEquals(hotelDetailParser.getHotel().getRooms().size(),hotelDetailParser.getHotel().getRoomsCustomFormat().size());
		Map<String, List<Room>> roomsCustomFormat = hotelDetailParser.getHotel().getRoomsCustomFormat();
		for(Map.Entry<String, List<Room>> item:roomsCustomFormat.entrySet()){
			LOG.info("[ROOMID = {}][SIZE = {}][BED_TYPE = {}][BED_TYPE_LABEL = {}]",item.getKey(),item.getValue().size(),item.getValue().get(0).getBedType(),item.getValue().get(0).getBedTypeLabel());
		}
		LOG.info("---------------------------------------imagesCustomFormat-----------------------------------------------");
		
		//imagesCustomFormat
		Assert.assertNotNull(hotelDetailParser.getHotel().getImagesCustomFormat());
		Map<String, List<Map<String, List<ImageLocation>>>> ImagesCustomFormat = hotelDetailParser.getHotel().getImagesCustomFormat();
		int imageCount = 0;
		for(Map.Entry<String, List<Map<String, List<ImageLocation>>>> item:ImagesCustomFormat.entrySet()){
			imageCount += item.getValue().size();
			LOG.info("[TYPE = {}][SIZE = {}]",item.getKey(),item.getValue().size());
		}
		
		Assert.assertEquals(hotelDetailParser.getHotel().getImages().size(),imageCount);
		
		LOG.info("---------------------------------------roomIdImagesCustomFormat-----------------------------------------------");
		
		
		Assert.assertNotNull(hotelDetailParser.getHotel().getRoomIdImagesCustomFormat());
		Map<String, Map<String, List<ImageLocation>>> roomIdImagesCustomFormat = hotelDetailParser.getHotel().getRoomIdImagesCustomFormat();
		for(Map.Entry<String, Map<String, List<ImageLocation>>> item:roomIdImagesCustomFormat.entrySet()){
			LOG.info("[TYPE = {}][SIZE = {}]",item.getKey(),item.getValue().size());
			Map<String, List<ImageLocation>> imageLocations = item.getValue();
			
			for(Map.Entry<String, List<ImageLocation>> entry:imageLocations.entrySet()){
				Object[] param = new Object[]{
						item.getKey(),
						entry.getKey(),
						entry.getValue().size(),
						entry.getValue().get(0).getContent()
				};
				LOG.info("	[TYPE = {}][WATERMARK_SIZE = {}][IMAGELOCATION_SIZE = {}][CONTENT = {}]",param);
			}
		}
		
		
		
		LOG.info("---------------------------------------Images-----------------------------------------------");
		//Images
		Assert.assertNotNull(hotelDetailParser.getHotel().getImages());
		LOG.info("[Image size = {}]",hotelDetailParser.getHotel().getImages().size());
		for(Image image:hotelDetailParser.getHotel().getImages()){
			LOG.info("[RoomId = {}] [Location size = {}]",image.getRoomId(),image.getLocations().size());
		}
		//Review
		Assert.assertNotNull(hotelDetailParser.getHotel().getReview());
//		Assert.assertEquals(474, hotelDetailParser.getHotel().getReview().getCount());
//		Assert.assertEquals(322, hotelDetailParser.getHotel().getReview().getGood());
//		Assert.assertEquals(34, hotelDetailParser.getHotel().getReview().getPoor());
//		Assert.assertEquals("90.4%", hotelDetailParser.getHotel().getReview().getScore());
	}
	
	
	@Test
	public void testParse_90101713(){
		HotelDetailParser hotelDetailParser = new HotelDetailParser(getInputStream("hotelDetail/90101713.xml"));
		Assert.assertNotNull(hotelDetailParser.getHotel());
		Assert.assertNotNull(hotelDetailParser.getHotel().getDetail());
		Assert.assertNotNull(hotelDetailParser.getHotel().getDetail().getSuppliers());
		Assert.assertEquals(1,hotelDetailParser.getHotel().getDetail().getSuppliers().size());
		Assert.assertNotNull(hotelDetailParser.getHotel().getRooms());
		Assert.assertEquals(4,hotelDetailParser.getHotel().getRooms().size());
		Assert.assertEquals(0,hotelDetailParser.getHotel().getImages().size());
		//Review
		Assert.assertNotNull(hotelDetailParser.getHotel().getReview());
		Assert.assertEquals(0, hotelDetailParser.getHotel().getReview().getCount());
		Assert.assertEquals(0, hotelDetailParser.getHotel().getReview().getGood());
		Assert.assertEquals(0, hotelDetailParser.getHotel().getReview().getPoor());
		Assert.assertNull(hotelDetailParser.getHotel().getReview().getScore());
	}
	
	@Test
	public void testParse() throws FileDownloadException, InvaildParameterException{
		HotelDetailParser hotelDetailParser = (HotelDetailParser) hotelStaticFileDownloadService.downloadHotelDetail("90101713", null);
		Assert.assertNotNull(hotelDetailParser.getHotel());
		Assert.assertNotNull(hotelDetailParser.getHotel().getDetail());
		Assert.assertNotNull(hotelDetailParser.getHotel().getDetail().getSuppliers());
		Assert.assertEquals(1,hotelDetailParser.getHotel().getDetail().getSuppliers().size());
		Assert.assertNotNull(hotelDetailParser.getHotel().getRooms());
		Assert.assertEquals(4,hotelDetailParser.getHotel().getRooms().size());
		Assert.assertEquals(0,hotelDetailParser.getHotel().getImages().size());
		//Review
		Assert.assertNotNull(hotelDetailParser.getHotel().getReview());
		Assert.assertEquals(0, hotelDetailParser.getHotel().getReview().getCount());
		Assert.assertEquals(0, hotelDetailParser.getHotel().getReview().getGood());
		Assert.assertEquals(0, hotelDetailParser.getHotel().getReview().getPoor());
		Assert.assertNull(hotelDetailParser.getHotel().getReview().getScore());
	}

}
