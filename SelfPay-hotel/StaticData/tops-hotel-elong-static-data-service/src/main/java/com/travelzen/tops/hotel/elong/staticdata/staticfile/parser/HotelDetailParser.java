package com.travelzen.tops.hotel.elong.staticdata.staticfile.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Detail;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Image;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.ImageLocation;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Review;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Room;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Supplier;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.utils.HotelDetailParserUtil;

/**
 * 解析ELONG酒店 HOTEL DETAIL FILE 静态文件
 * @author muyuansun
 * @date 2014-1-6 下午7:13:13
 */
public class HotelDetailParser extends DefaultHandler {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass()); 
	
	private Hotel hotel = null;
	
	private Detail detail = null;
	
	private String text;
	
	private String lastElementNameFlag;
	
	private String currentElementNameFlag;
	
	private StringBuffer sb;
	
	private List<Supplier> suppliers;
	
	private Supplier supplier;
	
	private List<Room> rooms;
	
	private Map<String,List<Room>> roomsCustomFormat = null;

	private Room room;
	
	private List<Image> images;
	
	private Map<String,List<Map<String,List<ImageLocation>>>> imagesCustomFormat;
	
	private Map<String,List<ImageLocation>> imagesCustomFormatImageLocations;
	
	private Map<String,Map<String,List<ImageLocation>>> roomIdImagesCustomFormat;
	
	private Image image;
	
	private List<ImageLocation> locations;
	
	private ImageLocation imageLocation;
	
	private Review review;
	
    public HotelDetailParser(InputStream inputStream, String lang) {
    	hotel = new Hotel();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputStream, this);
        } catch (ParserConfigurationException e) {
            LOG.debug( e.getMessage(),e);
        } catch (SAXException e) {
        	LOG.debug( e.getMessage(),e);
        } catch (IOException e) {
        	LOG.debug( e.getMessage(),e);
        }
    }
    
    public HotelDetailParser(InputStream inputStream) {
        this(inputStream, null);
    }
    
    @Override
    public void startElement(String localName, String qName, String elementName, Attributes attributes) throws SAXException {
    	//如果是Hotel元素开始，取出属性ID的信息
    	if(elementName.equals("Hotel")){
    		hotel.setHotelId(attributes.getValue("Id"));
    		LOG.debug(attributes.getValue("Id"));
    	}
    	/**
    	 * 组装 {@link com.travelzen.elong.newest.inter.entity.staticfile.Detail} 对象相关
    	 */
    	//如果是Detail元素开始，初始化detail对象 
    	if(elementName.equals("Detail")){
    		detail = new Detail();
    		lastElementNameFlag = elementName;
    	}
    	
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail")){
    		//见方法说明
    		gatherDetailBeginPerpareStart(elementName);
    		//见方法说明
    		gatherSupplierStart(elementName, attributes);
    	}
    	
    	/**
    	 * 组装 rooms 对象 
    	 */
    	//如果是Rooms元素开始，初始化rooms对象 
    	if(elementName.equals("Rooms")){
    		rooms = new ArrayList<>();
    		//自定义数据格式
    		roomsCustomFormat = new LinkedHashMap<>();
    		lastElementNameFlag = elementName;
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Rooms")){
    		//见方法说明
    		gatherRoomStart(elementName, attributes);
    	}
		/**
		 * 组装 images 对象 
		 */
    	//如果是Images元素开始，初始化images对象 
    	if(elementName.equals("Images")){
    		images = new ArrayList<>();
    		//自定义数据格式
    		imagesCustomFormat = new LinkedHashMap<>();
    		//自定义数据格式
    		roomIdImagesCustomFormat = new LinkedHashMap<>();
    		lastElementNameFlag = elementName;
    	}
    	
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images")){
    		gatherImageStart(elementName, attributes);
    	}
    	
       	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images") && elementName.equals("Locations")){
    		locations = new ArrayList<>();
    		imagesCustomFormatImageLocations = new LinkedHashMap<>();
    	}
       	
       	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images") && elementName.equals("Location")){
       		gatherLocationStart(elementName, attributes);
    	}
       	
    	if(elementName.equals("Review")){
    		lastElementNameFlag = "Review";
    		gatherReviewStart(elementName, attributes);
    	}
       	
    }
    
    /**
     * {@link com.travelzen.tops.hotel.elong.mongo.staticfile.Image} 对象属性字段的组装
     * @author muyuansun
     * @date 2014-1-6 下午3:53:12
     * @param elementName
     * @param attributes
     */
    public void gatherImageStart(String elementName, Attributes attributes){
    	if(elementName == null || !elementName.equals("Image")){
    		return;
    	}
    	image = new Image();
		// 图片类型 (@Type)
		if(attributes.getValue("Type") != null){
			image.setType(attributes.getValue("Type"));
		}
		// 图片类型 (@Type)
		if(attributes.getValue("Type") != null){
			image.setType(attributes.getValue("Type"));
		}
		
    }
    
    /**
     * {@link com.travelzen.tops.hotel.elong.mongo.staticfile.Review} 对象属性字段的组装
     * @author muyuansun
     * @date 2014-1-6 下午7:01:54
     * @param elementName
     * @param attributes
     */
    public void gatherReviewStart(String elementName, Attributes attributes){
     	if(elementName == null || !elementName.equals("Review")){
    		return;
    	}
    	review = new Review();
		// 点评 (@Count)
		if(attributes.getValue("Count") != null){
			review.setCount(Integer.valueOf(attributes.getValue("Count")));
		}
		// 好评数 (@Good)
		if(attributes.getValue("Good") != null){
			review.setGood(Integer.valueOf(attributes.getValue("Good")));
		}
		// 差评数 (@Poor)
		if(attributes.getValue("Poor") != null){
			review.setPoor(Integer.valueOf(attributes.getValue("Poor")));
		}
		// 评分 (@Score)
		if(attributes.getValue("Score") != null){
			review.setScore(attributes.getValue("Score"));
		}
    }
    
    public void gatherLocationStart(String elementName, Attributes attributes){
    	if(elementName == null || !elementName.equals("Location")){
    		return;
    	}
    	imageLocation = new ImageLocation();
    	// @WaterMark 是否有水印 0-N,1-Y
		if(attributes.getValue("WaterMark") != null){
			imageLocation.setWaterMark(Integer.valueOf(attributes.getValue("WaterMark")));
		}
		// @SizeType 图片规格
		if(attributes.getValue("Size") != null){
			imageLocation.setSizeType(Integer.valueOf(attributes.getValue("Size")));
		}
    }
    
    /**
     * {@link com.travelzen.tops.hotel.elong.mongo.staticfile.Room} 对象属性字段的组装
     * @author muyuansun
     * @date 2014-1-6 下午3:06:10
     * @param elementName
     * @param attributes
     */
    public void gatherRoomStart(String elementName, Attributes attributes){
    	if(elementName == null || !elementName.equals("Room")){
    		return;
    	}
    	room = new Room();
		// 房型编号 (@Id)
		if(attributes.getValue("Id") != null){
			room.setId(attributes.getValue("Id"));
		}
		// 名称 (@Name)
		if(attributes.getValue("Name") != null){
			room.setName(attributes.getValue("Name"));
		}
		// 面积 (@Area)
		if(attributes.getValue("Area") != null){
			room.setArea(attributes.getValue("Area"));
		}
		// 楼层 (@Floor)
		if(attributes.getValue("Floor") != null){
			room.setFloor(attributes.getValue("Floor"));
		}
		// 是否有宽带 (@BroadnetAccess) 0表示无宽带，1 表示有宽带
		if(attributes.getValue("BroadnetAccess") != null){
			room.setBroadnetAccess(Integer.valueOf(attributes.getValue("BroadnetAccess")));
		}
		// 宽带是否收费 (@BroadnetFee) 0表示免费，1 表示收费
		if(attributes.getValue("BroadnetFee") != null){
			room.setBroadnetFee(Integer.valueOf(attributes.getValue("BroadnetFee")));
		}
		// 床型 (@BedType)
		if(attributes.getValue("BedType") != null){
			room.setBedType(attributes.getValue("BedType"));
			room.setBedTypeLabel(HotelDetailParserUtil.gatherRoomBedTypeLabel(attributes.getValue("BedType")));
		}
		// 描述 (@Description)
		if(attributes.getValue("Description") != null){
			room.setDescription(attributes.getValue("Description"));
		}
		// 备注 (@Comments)
		if(attributes.getValue("Comments") != null){
			room.setComments(attributes.getValue("Comments"));
		}
		// 最大入住人数 (@Capacity)
		if(attributes.getValue("Capacity") != null){
			room.setCapacity(attributes.getValue("Capacity"));
		}
    }
    /**
     * 再Rooms Room 元素结束时的动作
     * @author muyuansun
     * @date 2014-1-6 下午3:26:24
     * @param element
     */
    public void gatherRoomsEnd(String element){
    	//Rooms 结束
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Rooms") && element.equals("Rooms")){
    		hotel.setRooms(rooms);
    		hotel.setRoomsCustomFormat(roomsCustomFormat);
    	}
    	//添加房型
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Rooms") && element.equals("Room")){
    		rooms.add(room);
    		//检查当前room的key是否存在，不存在则新增空集合
    		if(roomsCustomFormat.get(room.getId()) == null){
    			List<Room> roomsValue = new ArrayList<>();
    			roomsCustomFormat.put(room.getId(), roomsValue);
    		}
    		//设值
    		roomsCustomFormat.get(room.getId()).add(room);
    	}
    }
    
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
    	gatherDetailTextEnd(element);
    	gatherRoomsEnd(element);
    	gatherImagesEnd(element);
    	gatherReviewEnd(element);
    }
    
    /**
     * 提取{@link com.travelzen.tops.hotel.elong.mongo.staticfile.Detail} 对象开始准备工作
     * @author muyuansun
     * @date 2014-1-4 下午9:47:52
     * @param elementName
     */
    public void gatherDetailBeginPerpareStart(String elementName){
    	
    	//酒店支持的信用卡
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("CreditCards")){
    		currentElementNameFlag = "CreditCards";
    		sb = new StringBuffer();
    	}
    	//介绍信息
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("IntroEditor")){
    		currentElementNameFlag = "IntroEditor";
    		sb = new StringBuffer();
    	}
    	//描述
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Description")){
    		currentElementNameFlag = "Description";
    		sb = new StringBuffer();
    	}
    	//服务设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("GeneralAmenities")){
    		currentElementNameFlag = "GeneralAmenities";
    		sb = new StringBuffer();
    	}
    	//休闲设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("RecreationAmenities")){
    		currentElementNameFlag = "RecreationAmenities";
    		sb = new StringBuffer();
    	}
    	//会议设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("ConferenceAmenities")){
    		currentElementNameFlag = "ConferenceAmenities";
    		sb = new StringBuffer();
    	}
    	//餐饮设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("DiningAmenities")){
    		currentElementNameFlag = "DiningAmenities";
    		sb = new StringBuffer();
    	}
    	//周边交通
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Traffic")){
    		currentElementNameFlag = "Traffic";
    		sb = new StringBuffer();
    	}
    	//周边信息
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Surroundings")){
    		currentElementNameFlag = "Surroundings";
    		sb = new StringBuffer();
    	}
    	//设施列表
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Facilities")){
    		currentElementNameFlag = "Facilities";
    		sb = new StringBuffer();
    	}
    	
    }
	/**
	 * 组装review对象
	 * @author muyuansun
	 * @date 2014-1-6 下午7:09:37
	 * @param elementName
	 */
    public void gatherReviewEnd(String element){
    	//名称
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Review") && element.equals("Review")){
    		if(review != null){
    			hotel.setReview(review);
    		}
    		lastElementNameFlag = null;
    	}
    }
    
	/**
	 * 组装 images 对象
	 * @author muyuansun
	 * @date 2014-1-6 下午6:43:22
	 * @param elementName
	 */
    public void gatherImagesEnd(String elementName){
    	if(text == null || text.length() <= 0){
    		return;
    	}
    	//名称
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images") && elementName.equals("Location")){
    		if(text.startsWith("http")){
    			imageLocation.setContent(text);
    		}else{
    			//如果url不是以http开始，则需要增加前缀http://www.elongstatic.com/pp/hotels/hotel
    			StringBuffer url = new StringBuffer("http://www.elongstatic.com/pp/hotels/hotel");
    			url.append(text);
    			imageLocation.setContent(url.toString());
    		}
    		/**
    		 * 设置自定义的imageLocation的数据格式，便于前端查询使用
    		 */
    		StringBuffer waterMarkSize = new StringBuffer();
    		waterMarkSize.append(imageLocation.getWaterMark());
    		waterMarkSize.append(imageLocation.getSizeType());
    		if(!imagesCustomFormatImageLocations.containsKey(waterMarkSize.toString())){
    			List<ImageLocation> value = new ArrayList<>();
    			imagesCustomFormatImageLocations.put(waterMarkSize.toString(), value);
    		}
    		imagesCustomFormatImageLocations.get(waterMarkSize.toString()).add(imageLocation);
    		/**
    		 * 设置imageLocation（按XML格式组装的对象）
    		 */
    		locations.add(imageLocation);
    		LOG.debug("[URL = {}]",imageLocation.getContent());
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images") && elementName.equals("RoomId")){
    		if(image != null){
    			image.setRoomId(text);
    		}
    		String[] roomIds =  HotelDetailParserUtil.gatherRoomIds(text);
    		if(roomIds != null && roomIds.length > 0){
    			for(String roomId:roomIds){
    				roomIdImagesCustomFormat.put(roomId, imagesCustomFormatImageLocations);
    			}
    		}
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images") && elementName.equals("Locations")){
    		if(locations != null){
    			image.setLocations(locations);
    		}
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images") && elementName.equals("Image")){
    		
    		images.add(image);
    		
    		//Image 结束后将当前的 image 信息新增的自定义格式中
    		if(imagesCustomFormatImageLocations != null){
    			if(!imagesCustomFormat.containsKey(image.getType())){
    				List<Map<String,List<ImageLocation>>> imageTypes = new ArrayList<>();
    				imagesCustomFormat.put(image.getType(), imageTypes);
    			}
    			imagesCustomFormat.get(image.getType()).add(imagesCustomFormatImageLocations);
    		}
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Images") && elementName.equals("Images")){
    		if(images != null){
    			hotel.setImages(images);
    		}
    		if(imagesCustomFormat != null){
    			hotel.setImagesCustomFormat(imagesCustomFormat);
    		}
    		if(roomIdImagesCustomFormat != null){
    			hotel.setRoomIdImagesCustomFormat(roomIdImagesCustomFormat);
    		}
    		lastElementNameFlag = null;
    	}
    }
    
    /**
     * {@link com.travelzen.tops.hotel.elong.mongo.staticfile.Detail} 对象文本字段的组装
     * 用于 endElement 方法
     * @author muyuansun
     * @date 2014-1-4 下午7:33:46
     */
    public void gatherDetailTextEnd(String elementName){
    	if(text == null || text.length() <= 0){
    		return;
    	}
    	//名称
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Name")){
    		detail.setName(text);
    		LOG.debug("[Name = {}]",text);
    	}
    	//地址
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Address")){
    		detail.setAddress(text);
    		LOG.debug("[Address = {}]",text);
    	}
    	//邮编
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("PostalCode")){
    		detail.setPostalCode(text);
    		LOG.debug("[PostalCode = {}]",text);
    	}
    	//挂牌星级
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("StarRate")){
    		detail.setStarRate(Integer.valueOf(text));
    		LOG.debug("[StarRate = {}]",text);
    	}
    	//艺龙推荐星级
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Category")){
    		detail.setCategory(Integer.valueOf(text));
    		LOG.debug("[Category = {}]",text);
    	}
    	//电话
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Phone")){
    		detail.setPhone(text);
    		LOG.debug("[Phone = {}]",text);
    	}
    	//传真
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Fax")){
    		detail.setFax(text);
    		LOG.debug("[Fax = {}]",text);
    	}
    	//开业时间
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("EstablishmentDate")){
    		detail.setEstablishmentDate(text);
    		LOG.debug("[EstablishmentDate = {}]",text);
    	}
    	//装修时间
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("RenovationDate")){
    		detail.setRenovationDate(text);
    		LOG.debug("[RenovationDate = {}]",text);
    	}
    	//集团编号
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("GroupId")){
    		detail.setGroupId(Integer.valueOf(text));
    		LOG.debug("[GroupId = {}]",text);
    	}
    	//品牌编号
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("BrandId")){
    		detail.setBrandId(Integer.valueOf(text));
    		LOG.debug("[BrandId = {}]",text);
    	}
    	//是否经济型
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("IsEconomic")){
    		detail.setIsEconomic(Integer.valueOf(text));
    		LOG.debug("[IsEconomic = {}]",text);
    	}
    	//是否是公寓
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("IsApartment")){
    		detail.setIsApartment(Integer.valueOf(text));
    		LOG.debug("[IsApartment = {}]",text);
    	}
    	//纬度(GoogleLat)
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("GoogleLat")){
    		detail.setGoogleLat(text);
    		LOG.debug("[GoogleLat = {}]",text);
    	}
    	//经度(GoogleLon)
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("GoogleLon")){
    		detail.setGoogleLon(text);
    		LOG.debug("[GoogleLon = {}]",text);
    	}
    	//纬度(BaiduLat)
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("BaiduLat")){
    		detail.setBaiduLat(text);
    		LOG.debug("[BaiduLat = {}]",text);
    	}
    	//经度(BaiduLon)
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("BaiduLon")){
    		detail.setBaiduLon(text);
    		LOG.debug("[BaiduLon = {}]",text);
    	}
    	
    	//城市
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("CityId")){
    		detail.setCity(text);
    		LOG.debug("[CityId = {}]",text);
    	}
    	//商圈
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("BusinessZone")){
    		detail.setBusinessZone(text);
    		LOG.debug("[BusinessZone = {}]",text);
    	}
    	//行政区
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("District")){
    		detail.setDistrict(text);
    		LOG.debug("[District = {}]",text);
    	}
    	//酒店支持的信用卡
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("CreditCards")){
    		String fullText = sb.toString();
    		detail.setCreditCards(fullText);
    		LOG.debug("[CreditCards = {}]",fullText);
    		clearFlag();
    	}
    	//介绍信息
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("IntroEditor")){
    		String fullText = sb.toString();
    		detail.setIntroEditor(fullText);
    		LOG.debug("[IntroEditor = {}]",fullText);
    		clearFlag();
    	}
    	//描述
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Description")){
    		String fullText = sb.toString();
    		detail.setDescription(fullText);
    		LOG.debug("[Description = {}]",fullText);
    		clearFlag();
    	}
    	//服务设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("GeneralAmenities")){
    		String fullText = sb.toString();
    		detail.setGeneralAmenities(fullText);
    		LOG.debug("[GeneralAmenities = {}]",fullText);
    		clearFlag();
    	}
    	//休闲设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("RecreationAmenities")){
    		String fullText = sb.toString();
    		detail.setRecreationAmenities(fullText);
    		LOG.debug("[RecreationAmenities = {}]",fullText);
    		clearFlag();
    	}
    	//会议设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("ConferenceAmenities")){
    		String fullText = sb.toString();
    		detail.setConferenceAmenities(fullText);
    		LOG.debug("[ConferenceAmenities = {}]",fullText);
    		clearFlag();
    	}
    	//餐饮设施
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("DiningAmenities")){
    		String fullText = sb.toString();
    		detail.setDiningAmenities(fullText);
    		LOG.debug("[DiningAmenities = {}]",fullText);
    		clearFlag();
    	}
    	//周边交通
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Traffic")){
    		String fullText = sb.toString();
    		detail.setTraffic(fullText);
    		LOG.debug("[Traffic = {}]",fullText);
    		clearFlag();
    	}
    	//周边信息
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Surroundings")){
    		String fullText = sb.toString();
    		detail.setSurroundings(fullText);
    		LOG.debug("[Surroundings = {}]",fullText);
    		clearFlag();
    	}
    	//设施列表
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Facilities")){
    		String fullText = sb.toString();
    		detail.setFacilities(fullText);
    		LOG.debug("[Facilities = {}]",fullText);
    		clearFlag();
    	}
    	//特殊政策
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("AvailPolicy")){
    		String fullText = sb.toString();
    		supplier.setAvailPolicy(fullText);
    		LOG.debug("[AvailPolicy = {}]",fullText);
    		clearFlag();
    	}
    	/**
    	 * 供应商相关
    	 */
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Supplier")){
    		suppliers.add(supplier);
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Suppliers")){
    		if(suppliers == null || suppliers.size() <= 0){
    			LOG.warn("[suppliers is null] [HotelID = {}] [HotelName = {}]",hotel.getId(),detail.getName());
    		}else{
    			detail.setSuppliers(suppliers);
    		}
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Detail")){
    		hotel.setDetail(detail);
    		lastElementNameFlag = null;
    	}
    }
    
    /**
     * {@link com.travelzen.tops.hotel.elong.mongo.staticfile.Supplier} 对象属性字段的提取组装
     * @author muyuansun
     * @date 2014-1-4 下午8:57:27
     * @param elementName
     */
    public void gatherSupplierStart(String elementName, Attributes attributes){
    	//detail对象中的suppliers对象初始化(供应商)
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Suppliers")){
    		suppliers = new ArrayList<>();
    		return;
    	}
    	//detail对象中的supplier对象初始化(供应商)
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("Supplier")){
    		supplier = new Supplier();
    		//即时确认的销售房型
    		if(attributes.getValue("InstantRoomTypes") != null){
    			supplier.setInstantRoomTypes(attributes.getValue("InstantRoomTypes"));
    		}
    		//有效状态
    		if(attributes.getValue("Status") != null){
    			supplier.setStatus(Boolean.valueOf(attributes.getValue("Status")));
    		}
    		//星期开始设置
    		if(attributes.getValue("WeekendStart") != null){
    			supplier.setWeekendStart(Integer.valueOf(attributes.getValue("WeekendStart")));
    		}
    		//星期结束设置
    		if(attributes.getValue("WeekendEnd") != null){
    			supplier.setWeekendEnd(Integer.valueOf(attributes.getValue("WeekendEnd")));
    		}
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("Detail") && elementName.equals("AvailPolicy")){
    		currentElementNameFlag = "AvailPolicy";
    		sb = new StringBuffer();
    		//特殊政策-开始日期
    		if(attributes.getValue("StartDate") != null){
    			supplier.setAvailStartDate(attributes.getValue("StartDate"));
    		}
    		//特殊政策-结束日期
     		if(attributes.getValue("EndDate") != null){
    			supplier.setAvailEndDate(attributes.getValue("EndDate"));
    		}
    	}
    }
    
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
    	text = new String(ac, i, j);
    	//酒店支持的信用卡
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("CreditCards")){
    		sb.append(text);
    	}
    	//介绍信息
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("IntroEditor")){
    		sb.append(text);
    	}
    	//描述
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("Description")){
    		sb.append(text);
    	}
    	//服务设施
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("GeneralAmenities")){
    		sb.append(text);
    	}
    	//休闲设施
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("RecreationAmenities")){
    		sb.append(text);
    	}
    	//会议设施
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("ConferenceAmenities")){
    		sb.append(text);
    	}
    	//餐饮设施
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("DiningAmenities")){
    		sb.append(text);
    	}
    	//周边交通
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("Traffic")){
    		sb.append(text);
    	}
    	//周边信息
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("Surroundings")){
    		sb.append(text);
    	}
    	//设施列表
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("Facilities")){
    		sb.append(text);
    	}
    	//特殊政策
    	if(currentElementNameFlag != null && currentElementNameFlag.equals("AvailPolicy")){
    		sb.append(text);
    	}
    }
    
    public void clearFlag(){
		sb = null;
		currentElementNameFlag = null;
    }
    
    public Hotel getHotel() {
        return hotel;
    }

}
