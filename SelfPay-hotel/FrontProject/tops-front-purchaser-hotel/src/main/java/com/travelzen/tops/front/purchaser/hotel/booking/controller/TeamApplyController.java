package com.travelzen.tops.front.purchaser.hotel.booking.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.travelzen.tops.common.dict.hotel.enums.HotelEnum;
import com.travelzen.tops.common.dict.hotel.enums.HotelSearchEnum;
import com.travelzen.tops.front.purchaser.hotel.vo.TeamApplySearchVo;
import com.travelzen.tops.order.core.po.gen.HotelTeamApply;
import com.travelzen.tops.order.hotel.creme.service.additional.IHotelTeamApplyService;

/**
 * Created with IntelliJ IDEA.
 * User: lyy
 * Date: 13-9-16
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/hotel/booking/teamApply")
public class TeamApplyController {
	@Resource(name = "hotel_team_apply")
	private IHotelTeamApplyService hotelTeamApplyService;

	@RequestMapping("/goToTeamApply")
	public ModelAndView goToTeamApply() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("page/hotel/booking/teamApply/teamApply");

		mav.addObject("cityName", "上海");
		mav.addObject("cityIsoCode", "CN021");

		mav.addObject("checkinDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		mav.addObject("checkoutDate", new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));

		mav.addObject("default_count", 1);

		return mav;
	}

	@RequestMapping("/save")
	public ModelAndView save(TeamApplySearchVo teamApplySearchVo) throws ParseException, SQLException {
		ModelAndView mav = new ModelAndView("redirect:/hotel/booking/search");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HotelTeamApply hotelTeamApply = new HotelTeamApply();
		hotelTeamApply.setId(UUID.randomUUID().toString());
		hotelTeamApply.setCityCode(teamApplySearchVo.getCityCode());
		hotelTeamApply.setCityName(teamApplySearchVo.getCityName());
		hotelTeamApply.setFromDate(sdf.parse(teamApplySearchVo.getFromDate()));
		hotelTeamApply.setLeaveDate(sdf.parse(teamApplySearchVo.getLeaveDate()));

		//房间数量
		if (!teamApplySearchVo.getRoomNumber1().trim().isEmpty()) {
			hotelTeamApply.setRoomNumber(teamApplySearchVo.getRoomNumber1());
		} else {
			hotelTeamApply.setRoomNumber(HotelEnum.HotelRoomNumberState.getEnum(teamApplySearchVo.getRoomNumber2()).name());
		}

		//酒店星级
		if (teamApplySearchVo.getHotelLevel().trim().isEmpty()) {
			hotelTeamApply.setHotelLevel(null);
		} else {
			String levels[] = teamApplySearchVo.getHotelLevel().split(",");
			String hotelLevel = "";
			for (int i = 0; i < levels.length; ++i) {
				hotelLevel += HotelEnum.Rating.getRatingEntity(levels[i], 0).name();
				if (i != levels.length - 1) {
					hotelLevel += ",";
				}
			}
			hotelTeamApply.setHotelLevel(hotelLevel);
		}

		//酒店品牌
//		if (!teamApplySearchVo.getHotelBrand().isEmpty()) {
//			hotelTeamApply.setHotelBrand(null);
//		}

		//房间价格
		if (!teamApplySearchVo.getFromPrice().trim().isEmpty()) {
			hotelTeamApply.setFromPrice(Double.parseDouble(teamApplySearchVo.getFromPrice()));
			hotelTeamApply.setToPrice(Double.parseDouble(teamApplySearchVo.getToPrice()));
		} else {
			hotelTeamApply.setFromPrice((double) HotelSearchEnum.TeamPriceRange.getChildrenAgeEntity(teamApplySearchVo.getPrice()).getMin());    //都等于-1表示不限
			hotelTeamApply.setToPrice((double) HotelSearchEnum.TeamPriceRange.getChildrenAgeEntity(teamApplySearchVo.getPrice()).getMax());
		}
		hotelTeamApply.setRemark(teamApplySearchVo.getRemark());
		hotelTeamApply.setClientName(teamApplySearchVo.getClientName());
		hotelTeamApply.setClientPhone(teamApplySearchVo.getClientPhone());
		hotelTeamApplyService.save(hotelTeamApply);
		return mav;
	}
}
