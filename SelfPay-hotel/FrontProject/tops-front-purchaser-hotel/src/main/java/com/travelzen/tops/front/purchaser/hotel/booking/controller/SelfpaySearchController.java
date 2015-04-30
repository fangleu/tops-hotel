package com.travelzen.tops.front.purchaser.hotel.booking.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travelzen.framework.core.wrapper.Pagination;
import com.travelzen.framework.util.DateUtils;
import com.travelzen.tops.elong.converter.SelfpayHotelStaticContants;
import com.travelzen.tops.elong.request.model.SelfPaySearchCriteria;
import com.travelzen.tops.elong.service.ISelfpayHotelService;
import com.travelzen.tops.front.common.hotel.selfpay.business.SelfpayHotelSalesPolicyBusiness;
import com.travelzen.tops.front.purchaser.common.controller.HotelBaseController;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelDetailDTO;
import com.travelzen.tops.hotel.elong.entity.selfpay.dto.HotelListDTO;
import com.travelzen.tops.hotel.elong.mongo.dao.ICitySuggestDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.GeoLocation;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.AddPrice;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.iservice.CustomerService;
import com.travelzen.tops.member.common.vo.Customer;



@Controller
@RequestMapping("/hotel/booking/selfpay")
public class SelfpaySearchController extends HotelBaseController {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Resource(name="selfpayHotel_Search")
	private ISelfpayHotelService selfpayHotelService;
	
	@Resource
	private CustomerService pCustomerService;
	
	@Resource
	private ICitySuggestDao citySuggestDao;
	
	@Resource
	private SelfpayHotelSalesPolicyBusiness selfpaySalesPolicyBusiness;
	
	/**
	 *  酒店搜索页面
	 * @author Loufanglei
	 * @data 2014-10-30 下午3:42:44 
	 */
	@RequestMapping(value="/selfPaySearch",method = RequestMethod.POST)
	public String selfPaySearch(SelfPaySearchCriteria criteria,Model model){
		model.addAttribute("criteria", criteria);
		return "page/hotel/booking/selfpay/search";
	}
	
	/**
	 * 搜索页面筛选控制栏
	 * @author Loufanglei
	 * @data 2014-10-30 下午4:01:45 
	 */
	@RequestMapping(value = "/getFilterPanel", method = RequestMethod.POST)
	public String getFileterDiv(SelfPaySearchCriteria criteria,Model model) {
		criteria.setCityIsoCode(SelfpayHotelStaticContants.transferTzCode2Elong(criteria.getCityIsoCode()));
		//城市行政区
		HotelGeo districtBusinessZone = selfpayHotelService.queryTrafficInfo(criteria.getCityIsoCode());
		List<GeoLocation> district = null;
		if(districtBusinessZone != null){
			 district = districtBusinessZone.getDistricts();
		}
		model.addAttribute("districtPageList", district);
		return "page/hotel/booking/selfpay/filterPanel";
	}
	
	/**
	 * 酒店搜索列表结果
	 * @author Loufanglei
	 * @data 2014-10-30 下午3:41:27 
	 */
	@RequestMapping(value = "/filterSearch", method = RequestMethod.POST)
	public String filterSearch(SelfPaySearchCriteria criteria,Model model) {
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		Customer customer = TopsSecurityUtils.getUserFromSession().getCustomerData();
		HotelListDTO hotellist = null;
		Map<String,AddPrice> salesPolicy = null;
		criteria.setPageSize(10);
		Pagination<?> pagination = new Pagination<>(criteria.getPageNo(),criteria.getPageSize());
		try {
			hotellist = selfpayHotelService.queryHotels(criteria,String.valueOf(criteria.getPageSize()), String.valueOf(criteria.getPageNo()));
			salesPolicy = selfpaySalesPolicyBusiness.getSelfpayHotelSalesPolicy(customer, criteria.getCheckInDate(), criteria.getCheckOutDate());
			
			if( salesPolicy != null && salesPolicy.get(criteria.getCheckInDate()) != null ){
				if(hotellist != null){
					pagination.setTotalItemCount((long) hotellist.getCounts());
				}else{
					LOG.info("[酒店列表未取到]");
				}
				model.addAttribute("isSalesPolicyNull", false);
				model.addAttribute("commissionRate", salesPolicy.get(criteria.getCheckInDate()).getMarkuprate());
			}else{
				model.addAttribute("isSalesPolicyNull", true);
				LOG.info("[政策未取到]");
			}
			
			model.addAttribute("page", pagination);
			model.addAttribute("result",hotellist);
			model.addAttribute("conditions",criteria.getConditions());
			model.addAttribute("criteria",criteria);
			model.addAttribute("weekList",getWeekList(DateUtils.getJustDate(criteria.getCheckInDate())));
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	
		return "page/hotel/booking/selfpay/searchResultPage";
	}
	
	/**
	 * 现付酒店详情页
	 * @author Loufanglei
	 * @data 2014-10-30 下午3:42:26 
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView detail(SelfPaySearchCriteria searchCriteria) {
			ModelAndView mav = new ModelAndView();
			//调用member,获取用户，放到ModelANdView
			searchCriteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
			Customer purchaser = pCustomerService.getCustomerByKey(searchCriteria.getCustomerKey());
			Customer customer = TopsSecurityUtils.getUserFromSession().getCustomerData();
			HotelDetailDTO hotelDetailDTO=null;
			Map<String,AddPrice> salesPolicy = null;
			try {
				hotelDetailDTO = selfpayHotelService.queryHotelDetail(searchCriteria.getHotelId(), searchCriteria.getCheckInDate(), searchCriteria.getCheckOutDate());
				salesPolicy = selfpaySalesPolicyBusiness.getSelfpayHotelSalesPolicy(customer, searchCriteria.getCheckInDate(), searchCriteria.getCheckOutDate());
			} catch (Exception e) {
				LOG.error(e.getMessage(),e);
			}
			if( salesPolicy != null){
			    mav.addObject("commissionRate", salesPolicy.get(searchCriteria.getCheckInDate()).getMarkuprate());
			   }
			mav.addObject("hotel",hotelDetailDTO);
			mav.addObject("purchaser",purchaser);
			mav.addObject("criteria",searchCriteria);
			mav.addObject("weekList",getWeekList(DateUtils.getJustDate(searchCriteria.getCheckInDate())));
			mav.setViewName("page/hotel/booking/selfpay/detail");
			return mav;
		}
	
}
