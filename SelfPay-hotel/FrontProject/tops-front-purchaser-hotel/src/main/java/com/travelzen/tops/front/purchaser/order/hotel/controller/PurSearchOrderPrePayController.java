package com.travelzen.tops.front.purchaser.order.hotel.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.MoneyUtil;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.framework.formbean.PageBean;
import com.travelzen.framework.logger.core.ri.RequestIdentityLogger;
import com.travelzen.framework.util.LogFormatter;
import com.travelzen.framework.util.LogFormatter.Project;
import com.travelzen.framework.util.TZBeanUtils;
import com.travelzen.tops.common.dict.core.OrderType;
import com.travelzen.tops.common.dict.order.hotel.HotelBookingType;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderSource;
import com.travelzen.tops.common.dict.order.hotel.HotelOrderTable;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderStateCustomerView;
import com.travelzen.tops.common.dict.order.hotel.view.HotelOrderTypeView;
import com.travelzen.tops.common.dict.order.hotel.view.QuickQueryTabCustomerView;
import com.travelzen.tops.front.purchaser.common.controller.HotelBaseController;
import com.travelzen.tops.front.purchaser.order.hotel.util.PurHotelOrderOperationUtil;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.order.core.hotel.bo.common.HotelOrderCommonBo;
import com.travelzen.tops.order.core.hotel.bo.common.HotelOrderPurchaserQueryCriteria;
import com.travelzen.tops.order.core.hotel.bo.common.HotelOrderQueryCriteria;
import com.travelzen.tops.order.core.hotel.bo.gta.GTAHotelOrderBo;
import com.travelzen.tops.order.hotel.common.service.query.IHotelOrderQueryCommonService;
import com.travelzen.tops.order.hotel.common.service.query.IHotelOrderQueryIdService;
import com.travelzen.tops.order.hotel.gta.service.IGTAHotelOrderQueryService;

/**
 * 
 * @author wangmeng
 * 
 */
@Controller
@RequestMapping("/pur/hotel/ordermanage")
public class PurSearchOrderPrePayController extends HotelBaseController {
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	}

	private static Logger logger = RequestIdentityLogger.getLogger(PurSearchOrderPrePayController.class);

	private static final Integer DEFAUT_PAGE_SIZE = 20;

	@Resource(name = "hotel_order_hotelOrderQueryCommonService")
	private IHotelOrderQueryCommonService<HotelOrderCommonBo> hotelOrderQueryCommonService;
	@Resource(name = "hotel_order_gtaQueryService")
	private IGTAHotelOrderQueryService gtaHotelOrderQueryService;
	@Resource(name="purHotelOrderOperationUtil")
	private PurHotelOrderOperationUtil purHotelOrderOperationUtil;
	@Resource(name = "hotel_order_hotelOrderQueryIdService")
	private IHotelOrderQueryIdService hotelOrderQueryIdService;

	@RequestMapping(value = "/searchOrders", method = RequestMethod.POST)
	public String searchOrders(HotelOrderPurchaserQueryCriteria criteria, Model model) {
		logger.info(LogFormatter.enterFormat(Project.tops_order_core, "searchOrders", "采购商查询订单,查询条件:" + TZBeanUtils.describe(criteria)));
		PageBean<String> hotelOrderIds = hotelOrderQueryIdService.searchOrders(buildCriteria(criteria));
		List<HotelOrderCommonBo> hotelOrderBos = hotelOrderQueryCommonService.getByIds(hotelOrderIds.getData());
		processHotelOrderBo(hotelOrderBos);
		model.addAttribute("pageBean", hotelOrderIds);
		model.addAttribute("hotelOrderBos", hotelOrderBos);
		model.addAttribute("batchOrderOperations", purHotelOrderOperationUtil.getBatchOperations(hotelOrderBos));
		model.addAttribute("criteria", criteria);
		setAssistData(model);
		return "page/order/hotel/listOrder";
	}

	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String getOrders(Model model, HotelOrderPurchaserQueryCriteria criteria) {
		logger.info(LogFormatter.enterFormat(Project.tops_order_core, "getOrders", "采购商查询订单"));
		PageBean<String> hotelOrderIds = hotelOrderQueryIdService.searchOrders(buildCriteria(criteria));
		List<HotelOrderCommonBo> hotelOrderBos = hotelOrderQueryCommonService.getByIds(hotelOrderIds.getData());
		processHotelOrderBo(hotelOrderBos);
		model.addAttribute("pageBean", hotelOrderIds);
		model.addAttribute("hotelOrderBos", hotelOrderBos);
		model.addAttribute("batchOrderOperations", purHotelOrderOperationUtil.getBatchOperations(hotelOrderBos));
		model.addAttribute("criteria", criteria);
		setAssistData(model);
		return "page/order/hotel/listOrder";
	}

	private void setAssistData(Model model) {
		model.addAttribute("customerQuickQueryTabs", QuickQueryTabCustomerView.values());
		model.addAttribute("customerHotelOrderTypes", HotelOrderTypeView.values());
		model.addAttribute("customerHotelOrderStates", HotelOrderStateCustomerView.values());
	}

	private void processHotelOrderBo(List<HotelOrderCommonBo> hotelOrderBos) {
		if (ValidationUtil.isNotNull(hotelOrderBos)) {
			for (HotelOrderCommonBo hotelOrderBo : hotelOrderBos) {
				if (OrderType.GTA_NORMAL_HOTEL.name().equals(hotelOrderBo.getOrderType()) || OrderType.GTA_REFUND_HOTEL.name().equals(hotelOrderBo.getOrderType())) {
					GTAHotelOrderBo gtaBo = gtaHotelOrderQueryService.getById(hotelOrderBo.getId(), HotelOrderTable.gta_detail);
					hotelOrderBo.setTotalOrderFeeYuan(MoneyUtil.cent2Yuan(gtaBo.getHotelOrderGTADetailBo().getTotalOrderFeeRmb() == null ? 0 : gtaBo.getHotelOrderGTADetailBo().getTotalOrderFeeRmb()));
				}
			}
		}
	}

	private HotelOrderQueryCriteria buildCriteria(HotelOrderPurchaserQueryCriteria customerCriteria) {
		HotelOrderQueryCriteria criteria = new HotelOrderQueryCriteria();
		
		if (customerCriteria.getPageNo() == null) {
			customerCriteria.setPageNo(1);
		}
		if (customerCriteria.getPageSize() == null || customerCriteria.getPageSize().intValue() == 0) {
			customerCriteria.setPageSize(DEFAUT_PAGE_SIZE);
		}
		criteria.pageIndex = customerCriteria.getPageNo();
		criteria.pageNum = customerCriteria.getPageSize();

		criteria.setOrderNo(customerCriteria.getId());
		criteria.setCityName(customerCriteria.getCityName());
		criteria.setGuestName(customerCriteria.getGuestName());
		criteria.setOrderSources(Arrays.asList(HotelOrderSource.tops_b2b.name(),
				HotelOrderSource.purchaser.name(), HotelOrderSource.api_dist.name(),
				HotelOrderSource.api_app.name(), HotelOrderSource.api_weixin.name()));
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());

		// 处理orderTypes
		if (customerCriteria.getCustomerHotelOrderType() != null) {
			List<String> orderTypes = new ArrayList<String>();
			OrderType[] orderTypeEnums = customerCriteria.getCustomerHotelOrderType().getOrderTypes();
			for (OrderType orderTypeEnum : orderTypeEnums) {
				orderTypes.add(orderTypeEnum.name());
			}
			criteria.setOrderTypes(orderTypes);
		}
		// 设置默认日期
		Date date = new Date();
		if (customerCriteria.getCreateDate() == null) {
			customerCriteria.setCreateDate(date);//页面显示
			criteria.setBookingBeginDate(DateTimeUtil.format(date, DateTimeUtil.DATE_PATTERN));
		} else {
			criteria.setBookingBeginDate(DateTimeUtil.format(customerCriteria.getCreateDate(), DateTimeUtil.DATE_PATTERN));
		}

		if (customerCriteria.getEndDate() == null) {
			customerCriteria.setEndDate(date);//页面显示
			criteria.setBookingEndDate(DateTimeUtil.format(date, DateTimeUtil.DATE_PATTERN));
		} else {
			criteria.setBookingEndDate(DateTimeUtil.format(customerCriteria.getEndDate(), DateTimeUtil.DATE_PATTERN));
		}
		String checkinDate = customerCriteria.getCheckinDate() == null ? null : DateTimeUtil.format(customerCriteria.getCheckinDate(), DateTimeUtil.DATE_PATTERN);
		criteria.setCheckInDate(checkinDate);

		List<String> bookingTypes = new ArrayList<String>();
		bookingTypes.add(HotelBookingType.GTA_PRE_PAID.name());
		bookingTypes.add(HotelBookingType.PRE_PAID.name());
		criteria.setBookingTypes(bookingTypes);

		HotelOrderStateCustomerView hotelOrderPageState = customerCriteria.getHotelOrderPageState();
		QuickQueryTabCustomerView queryTab = customerCriteria.getQueryTab();
		if (queryTab == null) {
			queryTab = QuickQueryTabCustomerView.queryTab;
			customerCriteria.setQueryTab(queryTab);//页面显示
		}

		List<String> orderTypes = new ArrayList<String>();
		List<String> states = new ArrayList<String>();
		List<String> confirmStatusList = new ArrayList<String>();
		List<String> gatheringStatusList = new ArrayList<String>();

		if (queryTab.getStates() != null && queryTab.getStates().length > 0) {
			states.addAll(Arrays.asList(queryTab.getStates()));
		}
		if (queryTab.getConfirmStatus() != null && queryTab.getConfirmStatus().length > 0) {
			confirmStatusList.addAll(Arrays.asList(queryTab.getConfirmStatus()));
		}
		if (queryTab.getOrderTypes() != null && queryTab.getOrderTypes().length > 0) {
			orderTypes.addAll(Arrays.asList(queryTab.getOrderTypes()));
		}
		if (queryTab.getGatheringStates() != null && queryTab.getGatheringStates().length > 0) {
			gatheringStatusList.addAll(Arrays.asList(queryTab.getGatheringStates()));
		}

		if(ValidationUtil.isNotNull(customerCriteria.getCustomerHotelOrderType())){
			for(OrderType orderType:customerCriteria.getCustomerHotelOrderType().getOrderTypes()){
				orderTypes.add(orderType.name());
			}
		}
		if (hotelOrderPageState != null) {
			if (hotelOrderPageState.getStates() != null && hotelOrderPageState.getStates().length > 0) {
				states.addAll(Arrays.asList(hotelOrderPageState.getStates()));
			}
			if (hotelOrderPageState.getConfirmStatus() != null && hotelOrderPageState.getConfirmStatus().length > 0) {
				confirmStatusList.addAll(Arrays.asList(hotelOrderPageState.getConfirmStatus()));
			}
			if (hotelOrderPageState.getGatheringStatus() != null && hotelOrderPageState.getGatheringStatus().length > 0) {
				gatheringStatusList.addAll(Arrays.asList(hotelOrderPageState.getGatheringStatus()));
			}

		}

		if (ValidationUtil.isNotNull(orderTypes)) {
			criteria.setOrderTypes(orderTypes);
		}
		if (ValidationUtil.isNotNull(states)) {
			criteria.setOrderStates(states);
		}
		if (ValidationUtil.isNotNull(confirmStatusList)) {
			criteria.setConfirmStatus(confirmStatusList);
		}
		if (ValidationUtil.isNotNull(gatheringStatusList)) {
			criteria.setGatheringStates(gatheringStatusList);
		}
		criteria.setAdjustFilter(true);

		return criteria;
	}
}
