package com.travelzen.tops.front.purchaser.hotel.booking.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.travelzen.framework.core.wrapper.Pagination;
import com.travelzen.framework.redis.client.SpringRedisClient;
import com.travelzen.tops.common.dict.hotel.enums.HotelEnum;
import com.travelzen.tops.common.dict.hotel.enums.HotelEnum.Rating;
import com.travelzen.tops.common.dict.hotel.enums.HotelSearchEnum;
import com.travelzen.tops.core.mongo.entity.District;
import com.travelzen.tops.core.mongo.service.ICityService;
import com.travelzen.tops.core.mongo.service.IDistrictService;
import com.travelzen.tops.creme.service.ICategoryService;
import com.travelzen.tops.creme.service.IChainBrandService;
import com.travelzen.tops.creme.service.IFacilityService;
import com.travelzen.tops.creme.service.IHotelService;
import com.travelzen.tops.front.purchaser.hotel.booking.business.BookingSearchAdvancePayBusiness;
import com.travelzen.tops.front.purchaser.hotel.booking.business.BookingSearchCriteria;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.TPropertySearchResultBo;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.iservice.CustomerService;
import com.travelzen.tops.member.common.vo.Customer;

@Controller
@RequestMapping("/hotel/booking/search")
public class BookingSearchController {

	@Resource(name="hotel_creme_facilityService")
	IFacilityService facilityService;

	@Resource(name="hotel_creme_categoryService")
	ICategoryService categoryService;

	@Resource(name="hotel_creme_chainBrandService")
	IChainBrandService chainBrandService;

	@Resource(name = "common_districtService")
	private IDistrictService districtService;
	@Resource(name = "common_cityService")
	private ICityService citySerivice;

	@Resource(name = "hotel_creme_hotelService")
	private IHotelService hotelService;

	@Resource
	private CustomerService pCustomerService;

	@Resource
	private BookingSearchAdvancePayBusiness bookingSearchAdvancePayBusiness;

	public void getFilterInfo(Model model){
		model.addAttribute("hotelRatings", HotelEnum.Rating.values());	//星级
		model.addAttribute("hotelCategories", categoryService.getAll());	//分类
		model.addAttribute("hotelChainBrands", chainBrandService.getAll());
		model.addAttribute("priceRange", HotelSearchEnum.PriceRange.values());
		model.addAttribute("sortItems",HotelSearchEnum.SortItem.values());
		model.addAttribute("hotelFacilitys",facilityService.getAll());
	}

	//进入初始页面
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String indexPage(BookingSearchCriteria criteria,Model model) {
		long count;
		SpringRedisClient redis = SpringRedisClient.getInstance();

		Object obj = redis.read("b2b_countHotel"); // 从缓存获取酒店总数
		if(obj == null) {
			count = hotelService.getHotelTotalCount();
			redis.save("b2b_countHotel", count);
		}else {
			count = (long) obj;

		}
		model.addAttribute("countHotel",count);
		model.addAttribute("cityName", "上海");
		model.addAttribute("cityIsoCode", "CN021");
		model.addAttribute("hotelRatings", Rating.values());
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		if(hour>=18) {
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		model.addAttribute("checkinDate", new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));

		c.add(Calendar.DAY_OF_MONTH, 1);
		model.addAttribute("checkoutDate", new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
		//初始化热门酒店
		return "page/hotel/hotelMain";
	}

	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String searchPage(BookingSearchCriteria criteria,Model model) {
		model.addAttribute("criteria", criteria);
		return "page/hotel/booking/search/search";
	}

	//ajax获取筛选div
	@RequestMapping(value = "/getFilterDiv", method = RequestMethod.POST)
	public String getFileterDiv(BookingSearchCriteria criteria,Model model) {
		if (StringUtils.isBlank(criteria.getCityIsoCode()) && StringUtils.isNotBlank(criteria.getCityName())) {
			criteria.setCityIsoCode(citySerivice.findByCityName(criteria.getCityName()).getCode());
		}
		List<District> districts = districtService.getDistrictsByCityIsoCode(criteria.getCityIsoCode());
		model.addAttribute("districts", districts);
		model.addAttribute("criteria", criteria);
		getFilterInfo(model);
		return "page/hotel/booking/search/filter";
	}

	//筛选位置搜索,ajax获取排序列表和列表和分页div
	@RequestMapping(value = "/filterSearch", method = RequestMethod.POST)
	public String filterSearch(BookingSearchCriteria criteria,Model model) {
		getSearchResult(criteria,model);
		model.addAttribute("searchParam", criteria) ;
		return "page/hotel/booking/search/sortResultPage";
	}

	//分页搜索，ajax返回列表页面, useless
	@RequestMapping(value = "/pageSearch", method = RequestMethod.POST)
	public String pageSearch(BookingSearchCriteria criteria,Model model) {
		getSearchResult(criteria,model);
		return "page/hotel/booking/search/sortResultPage";
	}

	//预付酒店详情页
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView detail(BookingSearchCriteria searchCriteria) {
		ModelAndView mav = new ModelAndView();
		//调用member,获取用户，放到ModelANdView
		searchCriteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		Customer purchaser = pCustomerService.getCustomerByKey(searchCriteria.getCustomerKey());
		if(searchCriteria!=null){
			searchCriteria.setCustomer(purchaser);
		}
		TPropertySearchBo searchResult = bookingSearchAdvancePayBusiness.searchSingle(searchCriteria);
		mav.setViewName("page/hotel/booking/search/detail");
		mav.addObject("hotel",searchResult);
		mav.addObject("purchaser",purchaser);
		mav.addObject("criteria",searchCriteria);
		return mav;
	}


	@SuppressWarnings("rawtypes")
	private void getSearchResult(BookingSearchCriteria criteria,Model model){
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		if(StringUtils.isNotBlank(criteria.getCustomerKey())){
			criteria.setCustomer(pCustomerService.getCustomerByKey(criteria.getCustomerKey()));
		}
		TPropertySearchResultBo result = bookingSearchAdvancePayBusiness.search(criteria);
		Pagination<?> pagination = new Pagination(criteria.getPageNo(),criteria.getPageSize());
		if(result != null){
			pagination.setTotalItemCount(result.getTotalCounts());
			model.addAttribute("result", result);
		}else{
			pagination.setTotalItemCount(0l);
		}
		model.addAttribute("page", pagination);
		model.addAttribute("conditions",criteria.getConditions());
		model.addAttribute("criteria",criteria);
	}
}
