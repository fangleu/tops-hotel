package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.travelzen.framework.core.dict.PayQueryState;
import com.travelzen.framework.formbean.PageBean;
import com.travelzen.framework.poi.util.ExcelHeadFactory;
import com.travelzen.framework.poi.util.ExcelHelper;
import com.travelzen.tops.common.dict.finance.enums.PayType;
import com.travelzen.tops.front.purchaser.hotel.vo.HotelOrderVo;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.iservice.UserService;
import com.travelzen.tops.member.common.vo.User;
import com.travelzen.tops.member.common.wrapper.UserDataWrapper;
import com.travelzen.tops.member.common.wrapper.UserSearchCriteria;
import com.travelzen.tops.order.core.hotel.bo.creme.BookingRoomQuery;
import com.travelzen.tops.order.core.hotel.bo.creme.HotelOrderBo;
import com.travelzen.tops.order.hotel.common.service.state.IHotelOrderStateCenter;
import com.travelzen.tops.order.hotel.creme.service.query.ICremeHotelOrderQueryOtherService;

@Controller
@RequestMapping("/order/report/hotel")
public class BookingRoomOrderDetailController {

	private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final String CURRENT_DATE = FORMAT.format(new Date());
	private static final String fileHead = "酒店统计报表";

	private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BookingRoomOrderDetailController.class);

	
	@Resource(name = "hotel_order_queryOtherService")
	private ICremeHotelOrderQueryOtherService hotelOrderQueryOtherService;
	@Resource
	private UserService pUserService;
	
	@Resource(name="hotelOrder_stateCenter")
	private IHotelOrderStateCenter hotelOrderStateCenter;

	private Map<String, String> genPayType() {
		PayType[] payTypeList = PayType.values();
		Map<String, String> payTypes = new HashMap<String, String>();
		for (PayType payType : payTypeList) {
			payTypes.put(payType.name(), payType.getDescription());
		}
		return payTypes;
	}

	private Map<String, String> genPayQueryState() {
		PayQueryState[] payQueryStateList = PayQueryState.values();
		Map<String, String> payQueryStates = new HashMap<String, String>();
		for (PayQueryState payQueryState : payQueryStateList) {
			payQueryStates.put(payQueryState.name(), payQueryState.getDesc());
		}
		return payQueryStates;
	}

	@RequestMapping(value = "/seach", method = RequestMethod.GET)
	public String viewDetail(Model model) {
		model.addAttribute("payTypes", genPayType());
		model.addAttribute("payQueryStates", genPayQueryState());
		return "page/order/hotel/bookingRoomSearch";
	}

	@RequestMapping(value = "/seachDetail", method = RequestMethod.POST)
	@ResponseBody
	public String seachDetail(BookingRoomQuery query) {
		Map<String, Object> resultData = new HashMap<String, Object>();
		PageBean<HotelOrderVo> pageBean = new PageBean<HotelOrderVo>();
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		try {
			List<String> customerKeys = getCustomerKeysCondition();
			query.setCustomerKeys(customerKeys);

			countMap = hotelOrderQueryOtherService.countOrderTypeHotelOrderWithHotelDetail(query);
			List<HotelOrderBo> hotelOrderBoList = hotelOrderQueryOtherService.queryHotelOrderWithHotelDetail(query);
			int count = hotelOrderQueryOtherService.countHotelOrderWithHotelDetail(query);
			List<HotelOrderVo> hotelOrderVoList = convertHotelOrderVo(hotelOrderBoList);
			pageBean.setPage(query.getPage());
			pageBean.setPageSize(query.getPageSize());
			pageBean.setTotalCount(count);
			int totalPage = 0;
			if((count % query.getPageSize()) > 0){
				totalPage = count / query.getPageSize() + 1;
			}else{
				totalPage = count / query.getPageSize();
			}
			pageBean.setTotalPage(totalPage);
			pageBean.setData(hotelOrderVoList);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		resultData.put("pageBean", pageBean);
		resultData.put("countMap", countMap);
		return new Gson().toJson(resultData);
	}

	private List<String> getCustomerKeysCondition() {
		List<String> customerKeys = new ArrayList<String>();
		User currentUser = TopsSecurityUtils.getUserFromSession().getUserData();
		if (null != currentUser.getCustomerType()) {
			String currentCustomerKey = currentUser.getCustomerKey();
			switch (currentUser.getCustomerType()) {
			/** 公司 */
			case COMPANY:
				UserSearchCriteria lvUserSearchCriteria = new UserSearchCriteria();
				lvUserSearchCriteria.setCustomerKey(currentCustomerKey);
				UserDataWrapper userDataWrapper = pUserService.searchSomeUsers(lvUserSearchCriteria);
				if (null != userDataWrapper) {
					List<User> subUsers = userDataWrapper.getData();
					for (User user : subUsers) {
						customerKeys.add(user.getCustomerKey());
					}
				}
				break;
			/** 个人 */
			case INDIVIDUAL:
				customerKeys.add(TopsSecurityUtils.getUserFromSession().getCustomerKey());
				break;
			default:
				break;
			}
		}
		return customerKeys;
	}

	private List<HotelOrderVo> convertHotelOrderVo(List<HotelOrderBo> hotelOrderBoList) {
		List<HotelOrderVo> hotelOrderVoList = new ArrayList<HotelOrderVo>();
		for (HotelOrderBo hotelOrderBo : hotelOrderBoList) {
//		/	hotelOrderBo.setPageState(HotelOrderStateCustomerView.getCustomerState(hotelOrderBo.getState(), hotelOrderBo.getGatheringState(), hotelOrderBo.getConfirmStatus()));
			hotelOrderVoList.add(new HotelOrderVo(hotelOrderBo));
		}
		return hotelOrderVoList;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.POST)
	public void exportExcel(BookingRoomQuery query, HttpServletRequest request, HttpServletResponse response) {
		try {
			query.setPage(1);
			query.setPageSize(100000);
			StringBuffer fileName = new StringBuffer();
			fileName.append(fileHead);
			fileName.append("_");
			fileName.append(CURRENT_DATE);
			fileName.append(".xls");
			OutputStream os = response.getOutputStream();
			String returnFileName = converterFileName(fileName.toString(), request);
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-disposition", "attachment;filename=" + returnFileName);
			List<String> customerKeys = getCustomerKeysCondition();
			query.setCustomerKeys(customerKeys);  
			List<HotelOrderBo> hotelOrderBoList = hotelOrderQueryOtherService.queryHotelOrderWithHotelDetail(query);
			List<HotelOrderVo> hotelOrderVoList = convertHotelOrderVo(hotelOrderBoList);
			Class<HotelOrderVo> clazz = HotelOrderVo.class;
			ExcelHelper.getInstanse().exportToOS(os, ExcelHeadFactory.getPurchaserHotelOrderHead(), hotelOrderVoList, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private String converterFileName(String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
		if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0)
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		return fileName;
	}

}
