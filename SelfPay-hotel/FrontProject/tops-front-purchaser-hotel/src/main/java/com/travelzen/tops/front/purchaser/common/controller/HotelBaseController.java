package com.travelzen.tops.front.purchaser.common.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.travelzen.framework.core.exception.BizException;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.common.dict.core.ReturnCode;
import com.travelzen.tops.common.dict.hotel.enums.HotelEnum.BookingRequirement;
import com.travelzen.tops.hotel.thrift.creme.common.TBookingClassBo;
import com.travelzen.tops.hotel.thrift.creme.common.TBookingRequirement;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.hotel.thrift.creme.common.TRoomCatBo;
import com.travelzen.tops.order.hotel.creme.dto.CreateOrderCriteria;

public class HotelBaseController {
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
	}
	
	protected void checkBookingRequirement(TPropertySearchBo propertySearchBo, CreateOrderCriteria criteria){

		if(ValidationUtil.isNotNull(propertySearchBo)){
			List<TRoomCatBo> roomCatBos = propertySearchBo.getRoomCats();
			if(ValidationUtil.isNotNull(criteria.getRoomCatId())){
				String roomCatId = criteria.getRoomCatId();
				TRoomCatBo orderRoomCat = null;
				for (TRoomCatBo roomCatBo : roomCatBos) {
					if (ValidationUtil.isNotNull(roomCatBo.getId()) && roomCatBo.getId().equals(roomCatId)) {
						orderRoomCat = roomCatBo;
						break;
					}
				}
				if(ValidationUtil.isNotNull(orderRoomCat)||ValidationUtil.isNotNull(criteria.getBookingClassId())){
					List<TBookingClassBo> bookingClassBos = orderRoomCat.getBookingClass();
					if(ValidationUtil.isNotNull(bookingClassBos)){
						TBookingClassBo bookingClass = null;
						for (TBookingClassBo bookingClassBo : bookingClassBos) {
							if (ValidationUtil.isNotNull(bookingClassBo.getId()) && bookingClassBo.getId().equals(criteria.getBookingClassId())) {
								bookingClass = bookingClassBo;
							}
						}
						if(ValidationUtil.isNotNull(bookingClass)&&ValidationUtil.isNotNull(bookingClass.getBookingRequirement())){
							TBookingRequirement bq=bookingClass.getBookingRequirement();
							DateFormat dateDf = new SimpleDateFormat("yyyy-MM-dd");
							Calendar c=Calendar.getInstance(Locale.CHINA);
							Date checkinDate=null;
							Date checkoutDate=null;
							Date nowDate = new Date();
							Date tempDate=null;
							int tempTime=0;
							long checkinDays=0;
							long checkoutDays=0;
							int ad=bookingClass.getAdvanceDays();
							int cd =bookingClass.getCheckInDays();
							String at=bookingClass.getAdvanceTime();
							try {
								checkinDate = dateDf.parse(criteria.getCheckInDate());
								checkoutDate = dateDf.parse(criteria.getCheckOutDate());
								tempDate = dateDf.parse(dateDf.format(nowDate));
								tempTime = c.get(Calendar.HOUR_OF_DAY);
								long diff = checkinDate.getTime() - tempDate.getTime();
								checkinDays = diff / (1000 * 60 * 60 * 24);
								long diff2 = checkoutDate.getTime() - checkinDate.getTime();
								checkoutDays = diff2 / (1000 * 60 * 60 * 24);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							if(bq.name().equals(BookingRequirement.ADVANCE.name())){
								if(checkinDays<ad||checkinDays==ad&&tempTime>=Integer.parseInt(Arrays.asList(at.split(":")).get(0))){
									throw new BizException(ReturnCode.HOTEL_ORDER_FLOW_ERROR.getErrorCode(),"您的预订日期不满足选择产品的条件，请修改预定日期或选择其他产品");
								}
							}else if(bq.name().equals(BookingRequirement.SUCCESSIVE_LIVE.name())){
								if(checkoutDays<cd){
									throw new BizException(ReturnCode.HOTEL_ORDER_FLOW_ERROR.getErrorCode(),"您的预订日期不满足选择产品的条件，请修改预定日期或选择其他产品");
								}
							}else if(bq.name().equals(BookingRequirement.ADVANCE_AND_SUCCESSIVE_LIVE.name())){
								if(checkinDays<ad||checkinDays==ad&&tempTime>=Integer.parseInt(Arrays.asList(at.split(":")).get(0))){
									throw new BizException(ReturnCode.HOTEL_ORDER_FLOW_ERROR.getErrorCode(),"您的预订日期不满足选择产品的条件，请修改预定日期或选择其他产品");
								}
								if(checkoutDays<cd){
									throw new BizException(ReturnCode.HOTEL_ORDER_FLOW_ERROR.getErrorCode(),"您的预订日期不满足选择产品的条件，请修改预定日期或选择其他产品");
								}
							}
							
						}
					}
				}
				
			}
		}
	
	}
	
	/**
	 * 根据入住日期，返回入住日期开始往后一周的星期List
	 * @param date
	 * @return
	 */
	protected List<String> getWeekList(Date date ){
		
		List<String> weekList = new ArrayList<>();
		Calendar  cal = Calendar.getInstance();
		cal.setTime(date);
		String[] weekDays = {"周六","周日", "周一", "周二", "周三", "周四", "周五" };
		int start = cal.get(Calendar.DAY_OF_WEEK) % 7;
		for (int i = start ; i < start + 7 ; ++i) {
			weekList.add( weekDays[i%7]);
		}
		return weekList;
	}
	
}
