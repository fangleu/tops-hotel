package com.travelzen.tops.front.purchaser.hotel.booking.controller;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.travelzen.framework.core.wrapper.Pagination;
import com.travelzen.tops.common.dict.hotel.enums.HotelSearchEnum;
import com.travelzen.tops.front.common.jsonserializer.JsonSerializers;
import com.travelzen.tops.front.common.utils.AutoComplateUtil;
import com.travelzen.tops.front.purchaser.hotel.booking.business.BookingSearchAdvancePayBusiness;
import com.travelzen.tops.front.purchaser.hotel.booking.business.BookingSearchCriteria;
import com.travelzen.tops.front.purchaser.hotel.booking.business.GTAAutoCompleteBusiness;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.TPropertySearchResultBo;
import com.travelzen.tops.hotel.thrift.gta.service.db.City;
import com.travelzen.tops.hotel.thrift.gta.service.db.SearchCityResult;
import com.travelzen.tops.hotel.thrift.gta.service.db.SearchCountryResult;
import com.travelzen.tops.member.authorization.common.TopsSecurityUtils;
import com.travelzen.tops.member.common.iservice.CustomerService;
import com.travelzen.tops.member.common.vo.Customer;

@Controller
@RequestMapping("/hotel/booking/international")
public class GTABookingSearchController {

	@Resource
	private CustomerService pCustomerService;

	@Resource
	private BookingSearchAdvancePayBusiness bookingSearchAdvancePayBusiness;

	@Resource
	private GTAAutoCompleteBusiness gtaAutoCompleteBusiness;

	/**
	 * 酒店搜索页面
	 */
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String searchPage(BookingSearchCriteria criteria,Model model) {
		model.addAttribute("criteria", criteria);
		return "page/hotel/booking/international/search";
	}

	/**
	 * 搜索页面筛选控制栏
	 */
	@RequestMapping(value = "/getFilterPanel", method = RequestMethod.POST)
	public String getFileterDiv(BookingSearchCriteria criteria,Model model) {
		model.addAttribute("sortItems",HotelSearchEnum.SortItem.values());
		return "page/hotel/booking/international/components/filterPanel";
	}

	/**
	 * 酒店搜索列表结果
	 */
	@RequestMapping(value = "/filterSearch", method = RequestMethod.POST)
	public String filterSearch(BookingSearchCriteria criteria,Model model) {
		getSearchResult(criteria,model);
		return "page/hotel/booking/international/searchResultPage";
	}

	/**
	 * 酒店详情页
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public ModelAndView detail(BookingSearchCriteria criteria) {
		ModelAndView mav = new ModelAndView();
		//调用member,获取用户，放到ModelANdView
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		Customer purchaser = pCustomerService.getCustomerByKey(criteria.getCustomerKey());
		if(criteria!=null){
			criteria.setCustomer(purchaser);
		}
		TPropertySearchBo searchResult = bookingSearchAdvancePayBusiness.getGTAPropertySearchBo(criteria);
		mav.setViewName("page/hotel/booking/international/detail");
		mav.addObject("hotel",searchResult);
		mav.addObject("purchaser",purchaser);	//好像没有用到
		mav.addObject("criteria",criteria);
		return mav;
	}

	@SuppressWarnings("rawtypes")
	private void getSearchResult(BookingSearchCriteria criteria,Model model){
		criteria.setCustomerKey(TopsSecurityUtils.getUserFromSession().getCustomerKey());
		if(StringUtils.isNotBlank(criteria.getCustomerKey())){
			criteria.setCustomer(pCustomerService.getCustomerByKey(criteria.getCustomerKey()));
		}
		TPropertySearchResultBo result = bookingSearchAdvancePayBusiness.searchGTAHotel(criteria);
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

	/**
	 * 国际城市自动补全
	 */
	@RequestMapping("/searchCityByName")
	@ResponseBody
	public String searchCityByName(@RequestParam(value = "$filter", required = true) String filter,
			@RequestParam(value = "$callback", required = true) String callback,
			@RequestParam(value = "$top", required = true) String limit) {
		String searchData = "";
		Integer limitNum = 0;
		if (StringUtils.isNotBlank(limit)) {
			limitNum = Integer.parseInt(limit);
		}
		if (StringUtils.isNotBlank(filter) && (filter.indexOf("(") + 2) <= filter.indexOf("',")) {
			searchData = filter.substring(filter.indexOf("(") + 2, filter.indexOf("',"));
		}
		SearchCityResult cityResult = gtaAutoCompleteBusiness.searchGTACityOrAreaByWord(searchData, "zh");

		JSONArray autoCompleteJSON = new JSONArray();
		if (cityResult != null && cityResult.getData() != null) {
			for (City c : cityResult.getData()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("cityCode", c.getCode());
				jsonObject.accumulate("cityNameCN", c.getDesc());
				autoCompleteJSON.add(jsonObject);
				if (autoCompleteJSON.size() >= limitNum) {
					break;
				}
			}
		}
		return callback + AutoComplateUtil.JSONArrayToOdata(autoCompleteJSON);
	}


	/**
	 * 客人国籍自动补全
	 */
	@RequestMapping("/searchCountryByName")
	@ResponseBody
	public String searchCountryByName(@RequestParam("filter[filters][0][value]") String filter, @RequestParam("callback") String callback) {
		SearchCountryResult countryResult = gtaAutoCompleteBusiness.searchGTACountryByWord(filter, "zh");
		String json = "[]";
		if (countryResult != null) {
			json = JsonSerializers.serializeCollection(countryResult.getData());
		}
		return StringUtils.isNotBlank(callback) ? callback + "(" + json + ")" : json;
	}

}
