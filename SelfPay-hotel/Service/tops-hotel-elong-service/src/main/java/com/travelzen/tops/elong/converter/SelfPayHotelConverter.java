package com.travelzen.tops.elong.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.elong.utils.ElongConnectorUtils;
import com.travelzen.tops.elong.utils.ElongHotelGuranteeRule;
import com.travelzen.tops.elong.utils.ParamUtils;
import com.travelzen.tops.hotel.elong.entity.selfpay.custom.HotelListResult;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.DayPrice;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.DayPriceListDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelDetailDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelItemDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.RatePlanItem;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.RoomImageDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.RoomInfo;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Detail;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Gift;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.GuaranteeRule;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Hotel;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.ListRatePlan;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.NightlyRate;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.Room;
import com.travelzen.tops.hotel.elong.entity.selfpay.jsonxml.ValueAdd;
import com.travelzen.tops.hotel.elong.mongo.dao.IAmenityDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotCityDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelDao;
import com.travelzen.tops.hotel.elong.mongo.dao.IHotelGeoDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Amenity;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.ImageLocation;

@Component
public class SelfPayHotelConverter {

	private static Logger LOG = LoggerFactory.getLogger(SelfPayHotelConverter.class);
	
	private static SelfPayHotelConverter selfPayHotelConverter;
	
	@Resource
	private  IHotelDao hotelDao;
	
	@Resource
	private IHotCityDao hotCityDao;
	
	@Resource
	private IAmenityDao amenityDao;
	
	@Resource
	private IHotelGeoDao hotelGeoDao;
	
	private static Map<String,String[]> STATIC_FACILITIES = SelfpayHotelStaticContants.getSTATIC_FACILITIES();
	
	@PostConstruct
	public void  init(){
		selfPayHotelConverter = this;
	}
	
	/**
	 * 转换酒店List 
	 * @author Loufanglei
	 * @data 2014-10-30 上午11:41:56 
	 */
	public static List<HotelItemDTO> formatHotelItem(String checkInDate, HotelListResult result) {
		if (!ParamUtils.checkStringArrayNotEmpty(checkInDate) || result == null) {
        	return null;
		}
		List<Hotel> hotels = result.getResult().getHotels();
		List<HotelItemDTO> items = new ArrayList<>();
		
		for (Hotel hotel : hotels) {
			HotelItemDTO resultItem = null;
			try {
				resultItem = convertHotelData(checkInDate, hotel, hotel.getValueAdds());
			} catch (Exception e) {
				LOG.error(e.getMessage(),e);
			}
            if (null != resultItem) {
                items.add(resultItem);
            }
		}
		return items;
	}
	
	/**
	 * 转换酒店信息
	 * @author Loufanglei
	 * @data 2014-10-30 上午11:42:39 
	 */
   private static HotelItemDTO convertHotelData(String checkInDate, Hotel hotel, List<ValueAdd> valueAdds) {
		if (!ParamUtils.checkStringArrayNotEmpty(checkInDate) || hotel == null) {
        	return null;
		}
		
		List<Room> rooms = hotel.getRooms();
		if(rooms == null || rooms.size() == 0){
			LOG.info("[过滤无房型酒店：{}，id：{}]", hotel.getDetail() != null? hotel.getDetail().getHotelName() : "Detail节点不存在", hotel.getHotelId());
			return null;
		}
		
		HotelItemDTO itemDto = new HotelItemDTO();
		itemDto.setHotelId(hotel.getHotelId());
		String hotelId = hotel.getHotelId();
		List<com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel> localHotelList = selfPayHotelConverter.hotelDao.findHotelById(hotelId);
		if(localHotelList == null || localHotelList.size() == 0){
			LOG.info("[过滤无静态数据酒店：{}，id：{}]", hotel.getDetail() != null? hotel.getDetail().getHotelName() : "Detail节点不存在", hotel.getHotelId());
			return null;
		}else{
			itemDto.setHotelDesc( localHotelList.get(0).getDetail().getIntroEditor());
			double lowRate = Double.parseDouble(hotel.getLowRate().toString());
			itemDto.setLowRate(lowRate);
			
			String thumbNailUrl = "";
			Map<String, List<Map<String, List<ImageLocation>>>> ImagesCustomFormat = localHotelList.get(0).getImagesCustomFormat();
			if (ImagesCustomFormat != null) {
				List<Map<String, List<ImageLocation>>> mapList = ImagesCustomFormat.get("5");
				if (mapList != null && mapList.size() > 0) {
					thumbNailUrl = mapList.get(0).get("01").get(0).getContent();
				}				
			}
			
			if (hotel.getDetail() != null) {
				Detail detail = hotel.getDetail();
				if(StringUtils.isNotBlank(detail.getThumbNailUrl())){
					itemDto.setThumbNailUrl(detail.getThumbNailUrl());
				}else{
					itemDto.setThumbNailUrl(thumbNailUrl);
				}
				
				itemDto.setHotelName(detail.getHotelName());
				itemDto.setAddress(detail.getAddress());

				itemDto.setLatitude(detail.getLatitude());
				itemDto.setLongitude(detail.getLongitude());
				
				int starRate = detail.getStarRate();
				int tzRate = detail.getCategory();
				if (starRate != 0) {
					itemDto.setStarRate(starRate);
					itemDto.setStarDetail(ElongConnectorUtils.translateStarRate(starRate));			
				} else if(tzRate != 0) {
//					itemDto.setTzRate(tzRate);
					itemDto.setStarRate(tzRate);
					itemDto.setStarDetail(ElongConnectorUtils.translateTzRate(tzRate));			
				}
			}
			
			String facilities = localHotelList.get(0).getDetail().getFacilities();
			if (StringUtils.isNotBlank(facilities)) {
				String[] facilitiesNumber = facilities.split(",");
				String[] facilityType = new String[14];
				String[] facilityName = new String[14];
				for (int i = 0;i < facilitiesNumber.length;i++) {
					if(facilitiesNumber[i] == null || facilitiesNumber[i].trim().length() <= 0){
						continue;
					}
					String[] facilityInfo = STATIC_FACILITIES.get(facilitiesNumber[i].trim());
					if(facilityInfo == null){
						continue;
					}
					facilityType[i] = facilityInfo[0];
					facilityName[i] = facilityInfo[1];
				}
				itemDto.setFacilityType(facilityType);
				itemDto.setFacilityName(facilityName);
			}
		}

		//担保信息
		Map<Integer,GuaranteeRule> guaranteeRuleMap = new HashMap<Integer,GuaranteeRule>();
		if (hotel.getGuaranteeRules() != null && !hotel.getGuaranteeRules().isEmpty()) {
			for (GuaranteeRule guaranteeRule : hotel.getGuaranteeRules()) {
				guaranteeRuleMap.put(guaranteeRule.getGuranteeRuleId(), guaranteeRule);
			}
		}
		
		//礼包信息
		Map<Integer,Gift> giftsMap = new HashMap<Integer,Gift>();
		if (hotel.getGifts()!= null && !hotel.getGifts().isEmpty()) {
			for (Gift gift : hotel.getGifts()) {
				giftsMap.put(gift.getGiftId(), gift);
			}
		}
		
		List<RoomInfo> roomInfoList = generatorRoomInfos(checkInDate, guaranteeRuleMap, giftsMap, rooms, localHotelList, valueAdds);
		if (roomInfoList == null) {
			LOG.info("[过滤无可用房型酒店：{}，id：{}]", hotel.getDetail() != null? hotel.getDetail().getHotelName() : "Detail节点不存在", hotel.getHotelId());
			return null;
		}
		itemDto.setRoomList(roomInfoList);
		
		return itemDto;
	}
	
	
	/**
	 * 转换房型价格计划　担保规则
	 * @author Loufanglei
	 * @data 2014-10-30 下午12:00:37 
	 */
	public static List<RoomInfo> generatorRoomInfos(String checkInDate, Map<Integer,GuaranteeRule> guaranteeRuleMap, Map<Integer,Gift> giftsMap, List<Room> rooms, List<com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel> localHotelList, List<ValueAdd> valueAdds) {
		List<RoomInfo> roomInfoList = null;
		
		for (Room room : rooms) {
			RoomInfo roomInfo = new RoomInfo();
			String roomId = room.getRoomId();
			roomInfo.setRoomId(roomId);
			List<RatePlanItem> subRoomLists = null;
			List<ListRatePlan> ratePlans = room.getRatePlans();
			for (ListRatePlan ratePlan : ratePlans) {
				if (ratePlan.getCurrentAlloment() < 0 || !ratePlan.isStatus()) {
					LOG.info("[过滤无库存或不可用价格计划:{}-{}]", room.getName(), ratePlan.getRatePlanName());
					continue;
				}
				
				DayPriceListDTO priceDto = DayPriceList(ratePlan, valueAdds);
				String breakFastType = ElongConnectorUtils.getBreakFastType(ratePlan.getRatePlanName());
				
 				RatePlanItem ratePlanItem = new RatePlanItem();
 				ratePlanItem.setDayPriceList(priceDto);
 				ratePlanItem.setHotelCode(ratePlan.getHotelCode());
				ratePlanItem.setRemainCount(ratePlan.getCurrentAlloment());
				ratePlanItem.setRoomTypeId(ratePlan.getRoomTypeId());
				ratePlanItem.setCustomType(ratePlan.getCustomerType().value());
				String netType = null;
				if (localHotelList.size() > 0) {
					List<com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Room> roomList = localHotelList.get(0).getRooms();
					for (com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Room subRoom: roomList) {
						if (subRoom.getId().equals(roomId)) {
							ratePlanItem.setBedType(subRoom.getBedTypeLabel());
							//获取床行信息
							if(subRoom.getBedType() == null || subRoom.getBedType().length() <= 0){
								ratePlanItem.setBedTypeDesc("床型描述无");
							}else{
								ratePlanItem.setBedTypeDesc(subRoom.getBedType().replace(",", "/"));
							}
							if (subRoom.getArea() != null) {
								roomInfo.setArea(subRoom.getArea());
							}
							if (subRoom.getFloor() != null) {
								roomInfo.setFloor(subRoom.getFloor());
							}
							if (subRoom.getComments() != null) {
								roomInfo.setComments(subRoom.getComments());
							}
							if (subRoom.getCapacity() != null) {
								ratePlanItem.setCapacity(subRoom.getCapacity().substring(0, 1));
							}
							int broadnetAccess = subRoom.getBroadnetAccess();
							int broadnetFee = subRoom.getBroadnetFee();
							if (broadnetAccess == 0) {
								netType = "无";
							} else if (broadnetAccess != 0 && broadnetFee == 0) {
								netType = "免费";
							} else if (broadnetAccess != 0 && broadnetFee != 0) {
								netType = "收费";
							} 
							break;
						}
					}
				}
				
				ratePlanItem.setRoomNum(ratePlan.getCurrentAlloment()+"");
				ratePlanItem.setCurrencyCode(ratePlan.getCurrencyCode().value());
				ratePlanItem.setNetType(netType);
				ratePlanItem.setRatePlanId(ratePlan.getRatePlanId());
				ratePlanItem.setRatePlanName(ratePlan.getRatePlanName());
				ratePlanItem.setRoomPrice(ratePlan.getAverageRate().intValue());
				ratePlanItem.setTotalPrice(Double.parseDouble(ratePlan.getTotalRate().toString()));
				
				if (StringUtils.isNotBlank(ratePlan.getGiftIds())) {
					String[] gitfId = ratePlan.getGiftIds().split(",");
					List<Gift> giftList = new ArrayList<>();
					for(int i = 0; i<gitfId.length; i++ ){
						giftList.add(giftsMap.get(Integer.parseInt(gitfId[i].toString())));
					}
					ratePlanItem.setGiftList(giftList);
				}
				ratePlanItem.setGuaranteeRule("0");  //无担保
				ratePlanItem.setLimitDesc("免费取消");
				ratePlanItem.setCancelMsg("订单提交后，可随时免费取消/变更");
				if (StringUtils.isNotBlank(ratePlan.getGuaranteeRuleIds())) {
					int ruleId = Integer.parseInt(ratePlan.getGuaranteeRuleIds());
					ratePlanItem.setGuaranteeId(ruleId);
					GuaranteeRule rule = guaranteeRuleMap.get(ruleId);
					
					if(rule!=null){
						ElongHotelGuranteeRule.elongGuranteeRule(checkInDate, rule, ratePlanItem);
					}
					
//					ratePlanItem.setGuarantee("true");
					//强制担保
					if (!rule.isIsAmountGuarantee() && !rule.isIsTimeGuarantee()) {
						ratePlanItem.setGuaranteeRule("00");  //房量担保.时间担保。1表示担保，0表示无担保
					}
					//时间担保
					else if (!rule.isIsAmountGuarantee() && rule.isIsTimeGuarantee()) {
						ratePlanItem.setGuaranteeRule("01");  //房量担保.时间担保。1表示担保，0表示无担保
						ratePlanItem.setStartTime(rule.getStartTime());
						ratePlanItem.setEndTime(rule.getEndTime());
					}
					//房量担保
					else if (rule.isIsAmountGuarantee() && !rule.isIsTimeGuarantee()) {
						ratePlanItem.setGuaranteeRule("10");  //房量担保.时间担保。1表示担保，0表示无担保
						ratePlanItem.setAmount(rule.getAmount());
					}
					//房量时间担保
					else if (rule.isIsAmountGuarantee() && rule.isIsTimeGuarantee()) {
						ratePlanItem.setGuaranteeRule("11");  //房量担保.时间担保。1表示担保，0表示无担保
						ratePlanItem.setStartTime(rule.getStartTime());
						ratePlanItem.setEndTime(rule.getEndTime());
						ratePlanItem.setAmount(rule.getAmount());
					}
				}
					
				//早餐
				if (breakFastType != null) {
					ratePlanItem.setBreakfastType(breakFastType);
				}
				
				if(ratePlanItem.getRoomTypeId() != null){
					ratePlanItem.setBedTypeDesc(ElongConnectorUtils.getBedType(ratePlanItem.getRoomTypeId()));
				}
			
				if (null == subRoomLists) {
					subRoomLists = new ArrayList<>();
				}
					
				subRoomLists.add(ratePlanItem);
			}
			
			if (roomInfo.getRoomId() != null && subRoomLists != null) {
				if (subRoomLists.size() > 1){					
					Comparator<RatePlanItem> subRoomComparator = new Comparator<RatePlanItem>(){
						public int compare(RatePlanItem s1, RatePlanItem s2) {
							double p1 = s1.getRoomPrice();
							double p2 = s2.getRoomPrice();
							if (p1 > p2) return 1;
							else if (p1 < p2) return -1;
							else return 0;
						}
					};
					Collections.sort(subRoomLists,subRoomComparator);
				}
                roomInfo.setRatePlanList(subRoomLists);
                roomInfo.setRoomName(room.getName());

                if (localHotelList.size() > 0) {
                	roomInfo.setRoomImageDto(queryHotelRoomImage(roomInfo.getRoomId(), localHotelList.get(0)));

                }
                if (null == roomInfoList) {
                    roomInfoList = new ArrayList<>();
                }
                roomInfoList.add(roomInfo);
            }
			if (roomInfoList != null && roomInfoList.size() > 1) {				
				Comparator<RoomInfo> roomInfoComparator = new Comparator<RoomInfo>(){
					public int compare(RoomInfo s1, RoomInfo s2) {
						double p1 = s1.getRatePlanList().get(0).getRoomPrice();
						double p2 = s2.getRatePlanList().get(0).getRoomPrice();
						if (p1 > p2) return 1;
						else if (p1 < p2) return -1;
						else return 0;
					}
				};
				Collections.sort(roomInfoList,roomInfoComparator);
			}
		}
		return roomInfoList;
	}

	
	/**
	 * 价格日历
	 * @author Loufanglei
	 * @data 2014-10-30 下午3:29:51 
	 */
	public static DayPriceListDTO DayPriceList(ListRatePlan ratePlan,  List<ValueAdd> valueAdds){
		
		DayPriceListDTO priceDto = new DayPriceListDTO();
		List<DayPrice> dayPriceList = null;
		List<NightlyRate> nightlyRates = ratePlan.getNightlyRates();
		String breakFastType = ElongConnectorUtils.getBreakFastType(ratePlan.getRatePlanName());
		for (NightlyRate nightlyRate : nightlyRates) {
			DayPrice dayPrice = new DayPrice();
			dayPrice.setPrice(MoneyUtil.cent2Yuan(MoneyUtil.yuan2Cent(nightlyRate.getMember())).replace(".00", ""));
			dayPrice.setFull(!(nightlyRate.isStatus()));
			dayPrice.setWeekDay(ElongConnectorUtils.getWeekDayOfWeek(nightlyRate.getDate()));
			if (breakFastType != null) {
				dayPrice.setBreakfast(breakFastType.substring(0, 2));
			}
			if (null == dayPriceList) {
				dayPriceList = new ArrayList<>();
			}
			dayPriceList.add(dayPrice);

		}
		
		priceDto.setDayPriceList(dayPriceList);
		
		String bedRuleDesc = "";
		if (nightlyRates.get(0).getAddBed().abs().intValue() != 1) {
			bedRuleDesc = ParamUtils.stringBufferAppendStrings("", "提供加床，加床价￥", Integer.toString(nightlyRates.get(0).getAddBed().intValue()), "元。");
		} else {
			bedRuleDesc = ParamUtils.stringBufferAppendStrings("", "不提供加床服务。");
		}
		
		String valueAddIds = ratePlan.getValueAddIds();
		String[] valueAddIdList = valueAddIds.split(",");
		
		String breakfastDesc = "";
		List<ValueAdd> valueAdd1 = valueAdds;
		if (valueAdd1.size() > 0) {			
			for (int i = 0; i < valueAddIdList.length; i++) {
				if (valueAddIdList[i].startsWith("1")) {
					for (ValueAdd valueadd: valueAdd1) {
						if (valueadd.getValueAddId().equalsIgnoreCase(valueAddIdList[i])) {
							if (valueadd.getDescription() != "") {
								breakfastDesc = ParamUtils.stringBufferAppendStrings("",breakfastDesc, valueadd.getDescription().substring(5));
							}
						}
					}
				}
			}
		}
		
		String priceRuleDesc = "";
		if (breakfastDesc != "") {
			priceRuleDesc = ParamUtils.stringBufferAppendStrings("", priceRuleDesc, "<span class=\"tdTit\"  style='font-weight:bold;'>早餐：</span>", breakfastDesc, "<br>");
		}
		if (bedRuleDesc != "") {
			priceRuleDesc = ParamUtils.stringBufferAppendStrings("", priceRuleDesc, "<span class=\"tdTit\"   style='font-weight:bold;'>加床：</span>", bedRuleDesc, "<br>");
		}
		priceRuleDesc = ParamUtils.stringBufferAppendStrings("", priceRuleDesc, "<span class=\"tdTit\"  style='font-weight:bold;'>支付方式：</span>预订免费，入住后酒店前台付款。", "<br>");

		priceDto.setPriceRuleDesc(priceRuleDesc);
		
		return priceDto;
	}
	
	/**
	 * 获取酒店图片
	 * @author Loufanglei
	 * @data 2014-10-30 上午11:40:05 
	 */
	public static RoomImageDTO queryHotelRoomImage(String roomId, com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel staticHotel) {
		if (!ParamUtils.checkStringArrayNotEmpty(roomId)) {
        	return null;
		}
		RoomImageDTO roomImageDto = new RoomImageDTO();
		List<com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Room> roomList = staticHotel.getRooms();
		for (com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Room staticRoom: roomList) {
			if (staticRoom.getId().equalsIgnoreCase(roomId)) {
				if (staticRoom.getBedType() != null && staticRoom.getBedType() != "") {
					String bedType = ParamUtils.stringBufferAppendStrings("", "床型：",staticRoom.getBedType());
					roomImageDto.setBedType(bedType);
				}
				if (staticRoom.getCapacity() != null && staticRoom.getCapacity() != "") {
					String capacity = ParamUtils.stringBufferAppendStrings("", "最大入住人数：",staticRoom.getCapacity().substring(0, 1), "人");
					roomImageDto.setCapacity(capacity);
				}
				if (staticRoom.getFloor() != null && staticRoom.getFloor() != "") {
					
					String floor = ParamUtils.stringBufferAppendStrings("", "楼层：", staticRoom.getFloor(), "层");
					roomImageDto.setFloor(floor);
				}
				if (staticRoom.getArea() != null && staticRoom.getArea() != "") {
					
					String roomArea = ParamUtils.stringBufferAppendStrings("", "房间面积：", staticRoom.getArea(), "平米");
					roomImageDto.setRoomArea(roomArea);
				}
				if (staticRoom.getComments() != null && staticRoom.getComments() != "") {
					
					String others = ParamUtils.stringBufferAppendStrings("", "其它：", staticRoom.getComments());
					roomImageDto.setOthers(others);
				}
			}
		}
		
		List<String> imageUrlList = null;
		Map<String, Map<String, List<ImageLocation>>> roomIdImagesCustomFormat = staticHotel.getRoomIdImagesCustomFormat();
		if (roomIdImagesCustomFormat != null) {
			
			Map<String, List<ImageLocation>> mapList = roomIdImagesCustomFormat.get(roomId);
			if (mapList != null) {
				List<ImageLocation> imageLocations = mapList.get("03");
				
				if (imageLocations != null) {
					for (ImageLocation location: imageLocations) {
						String imageUrl = "";
						imageUrl = location.getContent();
						if (null == imageUrlList) {
							imageUrlList = new ArrayList<>();
						}
						imageUrlList.add(imageUrl);
					}
				}
			}
			roomImageDto.setImageSrcList(imageUrlList);
		}

		if (imageUrlList == null && roomImageDto.getBedType() == null && roomImageDto.getFloor() == null) {
			return null;
		}
		return roomImageDto;
	}
	
	
	/**
     * 数据库获取酒店详细信息
     * @author Loufanglei
     * @data 2014-10-30 上午11:47:56 
     */
    public static HotelDetailDTO generateHotelDetailDTO(String checkInDate, HotelListResult result,String hotelId) {
		if (!ParamUtils.checkStringArrayNotEmpty(checkInDate, hotelId)) {
			return null;
		}
		if (null == result) {
			return null;
		}
		if (null!=result.getResult()&&result.getResult().getCount() == 0) {
			return null;
		}
		Hotel hotel = result.getResult().getHotels().get(0);
		HotelDetailDTO hotelDto = new HotelDetailDTO();
		List<com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Hotel> localHotelList = selfPayHotelConverter.hotelDao.findHotelById(hotelId);
		hotelDto.setHotelId(hotelId);
		if(null!=localHotelList && localHotelList.size() > 0) {

			com.travelzen.tops.hotel.elong.mongo.entity.staticfile.Detail staticHotelDetail = localHotelList.get(0).getDetail();
			hotelDto.setLatitude(staticHotelDetail.getBaiduLat());
			hotelDto.setLongitude(staticHotelDetail.getBaiduLon());
			hotelDto.setHotelCity(selfPayHotelConverter.hotelGeoDao.findCityNameByCityCode(staticHotelDetail.getCity()));

			// TODO 这里要拆成俩字段
			hotelDto.setIntroEditor((staticHotelDetail.getIntroEditor() == null ? "" : staticHotelDetail.getIntroEditor())
					+ (staticHotelDetail.getDescription() == null ? "" : staticHotelDetail.getDescription())
					);
			
			if(StringUtils.isNotBlank(staticHotelDetail.getTraffic())){
				String [] trafficsHalf = staticHotelDetail.getTraffic().split("\n"); 
				List<String> traffics = new ArrayList<String>();
				for (int i=0;i<trafficsHalf.length;i++)
				{
					traffics.add(trafficsHalf[i]);
				}
				hotelDto.setTraffic(traffics);
			}
			
			List<String> cards = new ArrayList<String>();
			if (staticHotelDetail.getCreditCards() != null) {
				String [] card = staticHotelDetail.getCreditCards().split(",");
				for(int i=0;i<card.length;i++)
				{
					
					if(card[i].equals("牡丹卡")||card[i].equals("金穗卡")||card[i].equals("长城卡")||card[i].equals("龙卡")||card[i].equals("太平洋卡")||card[i].equals("东方卡")) {
						int tag = 0;
						for(int j=0;j<cards.size();j++){
							if(cards.get(j).equals("card_UnionPay")) {
								tag = 1;
							}
						}
						if(tag==0) {
							cards.add("card_UnionPay");
						}
					}
					else if(card[i].equals("Visacard")) {
						cards.add("card_VISA");
					}
					else if(card[i].equals("万事达卡")) {
						cards.add("card_MasterCard");
					}
					else if(card[i].equals("运通卡")) {
						cards.add("card_APRESS");
					}
					else if(card.equals("大莱卡")) {
						cards.add("card_AE");
					}
					else if(card[i].equals("JCBcard")) {
						cards.add("card_JCB");
					}
				}
				hotelDto.setCreditCards(cards);
			}
			
			String facilities = staticHotelDetail.getFacilities();
			if (facilities != null) {
				String[] facilitiesNumber = facilities.split(",");
				String[] fullFacility = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13","14"};
				String[] facilitiesType = {"免费wifi", "收费wifi", "免费宽带", "收费宽带", "免费停车场", "收费停车场", "免费接机服务", 
										"收费接机服务", "室内游泳池", "室外游泳池", "健身房", "商务中心", "会议室", "酒店餐厅" };
				String[] facilitiesIcon = {"free-wifi", "wifi", "free-broadband", "broadband", "free-park", "park", null, null, 
										"indoorswim", "outdoorswim", "gym", null, null, null };
				String[] facilityType = new String[14];
				String[] facilityName = new String[14];
				for (int i = 0, j = 0; i < facilitiesNumber.length; ) {
					if(j==14) j=0;
					if (facilitiesNumber[i].equals(fullFacility[j])) {
						facilityType[i] = facilitiesType[j];
						facilityName[i] = facilitiesIcon[j];
						i++;
					}
					if (j < fullFacility.length) {
						j++;
					}
				}
				hotelDto.setFacilityType(facilityType);
				hotelDto.setFacilityName(facilityName);
				
				for (int i = 0; i < facilityType.length; i++) {
					if (facilityType[i] == null) {
						break;
					} else if (facilityType[i] == facilitiesType[0]) {
						hotelDto.setFreeWifi("公共区域提供<span class=\"free-text\">免费</span>WiFi");
						continue;
					} else if (facilityType[i] == facilitiesType[1]) {
						hotelDto.setFreeWifi("公共区域提供收费WiFi");
						continue;
					} else if (facilityType[i] == facilitiesType[4]) {
						hotelDto.setPark("酒店提供<span class=\"free-text\">免费</span>停车位");
						continue;
					} else if (facilityType[i] == facilitiesType[5]) {
						hotelDto.setPark("酒店提供收费停车位");
						continue;
					}
				}
			
			}
					
			hotelDto.setOpenDate(staticHotelDetail.getEstablishmentDate());
			hotelDto.setRenovationDate(staticHotelDetail.getRenovationDate());
			hotelDto.setPhone(staticHotelDetail.getPhone());
			hotelDto.setAddress(staticHotelDetail.getAddress());
			
			int starRate = staticHotelDetail.getStarRate();
			int tzRate = staticHotelDetail.getCategory();
			if (starRate != 0) {
				hotelDto.setStarRate(starRate);
				hotelDto.setStarDetail(ElongConnectorUtils.translateStarRate(starRate));
			} else if(tzRate != 0) {
				hotelDto.setStarRate(tzRate);
				hotelDto.setStarDetail(ElongConnectorUtils.translateTzRate(tzRate));
			}
			
			if(null!=staticHotelDetail.getGeneralAmenities()) {
				List<String> generalAmenities = new ArrayList<String>();
				String [] general = staticHotelDetail.getGeneralAmenities().split(",");
				if (general.length < 2) {
					general = localHotelList.get(0).getDetail().getGeneralAmenities().split("\n");
				}
				for(int i=0; i<general.length;i++){
					Amenity amenity =	selfPayHotelConverter.amenityDao.findAmenityByAmenityId(general[i]);
					 generalAmenities.add( amenity.getName());
				}
				hotelDto.setGeneralAmenities(generalAmenities);
			}
			
			if(null!=staticHotelDetail.getRecreationAmenities()) {
				List<String> recreationAmenities = new ArrayList<String>();
				String [] recreation = staticHotelDetail.getRecreationAmenities().split(",");
				for(int i=0;i<recreation.length;i++)
				{
					try{
						Amenity amenity =	selfPayHotelConverter.amenityDao.findAmenityByAmenityId(recreation[i]);
						recreationAmenities.add(amenity.getName());
					}catch(Exception e){
					}
				}
				hotelDto.setRecreationAmenities(recreationAmenities);
			}
			
			if(null!=staticHotelDetail.getConferenceAmenities()) {
				List<String> conferenceAmenities = new ArrayList<String>();
				String [] conference = staticHotelDetail.getConferenceAmenities().split("\n");
				for(int i=0;i<conference.length;i++)
				{
					conferenceAmenities.add(conference[i]);
				}
				hotelDto.setConferenceAmenities(conferenceAmenities);
			}
			
			if(null!=staticHotelDetail.getDiningAmenities()) {
				List<String> diningAmenities = new ArrayList<String>();
				String [] dining = staticHotelDetail.getDiningAmenities().split("\n");
				for(int i=0;i<dining.length;i++)
				{
					diningAmenities.add(dining[i]);
				}
				hotelDto.setDiningAmenities(diningAmenities);
			}

			String thumbNailUrl = "";
			Map<String, List<Map<String, List<ImageLocation>>>> imagesCustomFormat = localHotelList.get(0).getImagesCustomFormat();
			if (imagesCustomFormat != null) {
				List<Map<String, List<ImageLocation>>> mapList = imagesCustomFormat.get("5");
				if (mapList != null && mapList.size() > 0 && ValidationUtil.isNotNull(mapList.get(0).get("01"), mapList.get(0).get("01").get(0))) {
					thumbNailUrl = mapList.get(0).get("01").get(0).getContent();
				}
				
				String[] imageTypes = {"展示图", "餐厅", "休闲室", "会议室", "服务", "酒店外观", "大堂/接待台", "酒店介绍", "房型", "背景图", "其他", "公共区域"};
				List<String> imageInfos = new ArrayList<String>();
				List<String> pictures = new ArrayList<String>();
				for (int i=0;i<12;i++) {
					String iString = String.valueOf(i);
					if (null!=imagesCustomFormat.get(iString)) {
						if (null!=imagesCustomFormat.get(iString).get(0).get("01")) {
							pictures.add(imagesCustomFormat.get(iString).get(0).get("01").get(0).getContent());
							imageInfos.add(imageTypes[i]);
						}
					}
				}
				hotelDto.setThumbNailUrlList(pictures);
				hotelDto.setThumbNailInfoList(imageInfos);
				hotelDto.setNumberOfPicture(pictures.size());
				
			}
			if(hotel.getDetail().getThumbNailUrl() != null){
				hotelDto.setThumbNailUrl(hotel.getDetail().getThumbNailUrl());
			}else{
			   hotelDto.setThumbNailUrl(thumbNailUrl);
			}
			double lowRate = Double.parseDouble((hotel.getLowRate().toString()));
			hotelDto.setLowRate(lowRate);
			
			//担保信息
			Map<Integer,GuaranteeRule> guaranteeRuleMap = new HashMap<Integer,GuaranteeRule>();
			if (hotel.getGuaranteeRules() != null && !hotel.getGuaranteeRules().isEmpty()) {
				for (GuaranteeRule guaranteeRule : hotel.getGuaranteeRules()) {
					guaranteeRuleMap.put(guaranteeRule.getGuranteeRuleId(), guaranteeRule);
				}
			}
			//礼包信息
			Map<Integer,Gift> giftsMap = new HashMap<Integer,Gift>();
			if (hotel.getGifts()!= null && !hotel.getGifts().isEmpty()) {
				for (Gift gift : hotel.getGifts()) {
					giftsMap.put(gift.getGiftId(), gift);
				}
			}
			List<Room> rooms = hotel.getRooms();
			List<RoomInfo> roomInfoList = SelfPayHotelConverter.generatorRoomInfos(checkInDate, guaranteeRuleMap, giftsMap, rooms, localHotelList, hotel.getValueAdds());
			
			hotelDto.setRoomList(roomInfoList);

			if (ValidationUtil.isNotNull(roomInfoList, roomInfoList.get(0), roomInfoList.get(0).getRatePlanList(),
					roomInfoList.get(0).getRatePlanList().get(0), roomInfoList.get(0).getRatePlanList().get(0).getNetType())) {
				if (roomInfoList.get(0).getRatePlanList().get(0).getNetType().equals("无")) {
					hotelDto.setBroadband(null);
				} else if (roomInfoList.get(0).getRatePlanList().get(0).getNetType().equals("免费")) {
					hotelDto.setBroadband("酒店提供宽带<span class=\"free-text\">免费</span>上网");
				} else if (roomInfoList.get(0).getRatePlanList().get(0).getNetType().equals("收费")) {
					hotelDto.setBroadband("酒店提供宽带收费上网");
				}
			}
		}
		
		
		hotelDto.setHotelName(hotel.getDetail().getHotelName());
		
		return hotelDto;
		
	}
	
	
}
