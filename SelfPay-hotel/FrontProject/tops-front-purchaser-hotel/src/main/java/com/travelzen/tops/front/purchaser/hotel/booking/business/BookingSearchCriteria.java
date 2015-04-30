package com.travelzen.tops.front.purchaser.hotel.booking.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.travelzen.framework.config.tops.TopsConfReader;
import com.travelzen.framework.config.tops.TopsConfEnum.ConfScope;
import com.travelzen.framework.core.time.DateTimeUtil;
import com.travelzen.framework.core.util.ValidationUtil;
import com.travelzen.tops.common.dict.hotel.enums.HotelEnum;
import com.travelzen.tops.hotel.thrift.creme.common.TInventoryContext;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.TProcessorType;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.TPropertySearchCriteria;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.TSortItem;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.TSortOrder;
import com.travelzen.tops.member.common.MemberConstants;
import com.travelzen.tops.member.common.enums.CustomerType;
import com.travelzen.tops.member.common.vo.Customer;

/**
 * 预订搜索条件，完成和thrift条件的转化
 *
 * @author wangmeng
 *
 */
public class BookingSearchCriteria {

	private static final Integer SEARCH_DEFAULT_NUM = Integer.parseInt(TopsConfReader.getConfContent("tops-hotel/hotel-constants.properties", "hotel.search.defaultNum",ConfScope.R));
	private int pageNo; //
	private int pageSize; //
	private String cityIsoCode; //
	private String cityName; //
	private List<String> districtCodes; //
	private List<String> districtNames; //
	private List<String> commercialCodes; //
	private List<String> commercialNames; //
	private String checkInDate = "1"; //
	private String checkOutDate = "1"; //
	private String hotelName; //
	private List<String> hotelRatings; //
	private List<String> hotelCategoryIds; //
	private List<String> chainBrandIds; //
	private List<String> facilitys;
	private Double minPrice; //
	private Double maxPrice; //
	private String sortItem; //
	private String sortOrder; //
	private String customerKey;
	private Customer customer;
	private String keywords;   //关键字
	
	
	

	private String hotelId;
	private List<String> hotelIds;

	// 订单参数
	private String roomNo;
	private String roomCatId;
	private String bookingClassId;

	private List<String> conditions;// 查询条件（前台展示用）
	private List<String> validDates;

	// GTA
	private Boolean fromGTA; //false时从缓存读取
	private String roomType;
	private String nationality;	//客人国籍Code，如CN
	private String nationalityName;	//客人国籍名字，如中国

	public List<String> getValidDates() {
		validDates = new ArrayList<String>();
		if (StringUtils.isNotBlank(checkInDate) && StringUtils.isNotBlank(checkOutDate)) {
			Calendar startCalendar = Calendar.getInstance();
			Calendar endCalendar = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate;
			try {
				startDate = df.parse(checkInDate);

				startCalendar.setTime(startDate);
				Date endDate = df.parse(checkOutDate);
				endCalendar.setTime(endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			int day = (int) ((endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000)) - 1;
			if (day == 0)
				return null;// 相差一天，返回空
			day += 7 - day % 7;
			for (int i = 0; i < day; i++) {
				validDates.add(df.format(startCalendar.getTime()));
				startCalendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		}
		return validDates;
	}


	public TInventoryContext getTInventoryContext() {
		if (customer != null) {
			TInventoryContext context = new TInventoryContext();
			if (customer.getKey() != null)
				context.putToContext(HotelEnum.SEARCHCONTEXT_CUSTOMER_KEY,
						customer.getKey());
			if (customer.getCustomerGroups() != null) {
				String groupKey = customer.getCustomerGroups().get(MemberConstants.HOTEL);
				if (StringUtils.isNotBlank(groupKey)) {
					context.putToContext(HotelEnum.SEARCHCONTEXT_CUSTOMER_GROUPID, groupKey);
				}
			}
			if (customer.getType() != null) {
				if (customer.getType().equals(CustomerType.COMPANY) && customer.getCompanyType() != null) {
					context.putToContext(HotelEnum.SEARCHCONTEXT_CUSTOMER_TYPE, customer.getCompanyType().name());
				} else {
					context.putToContext(HotelEnum.SEARCHCONTEXT_CUSTOMER_TYPE, customer.getType().name());
				}
			}
			if (customer.getAccountType() != null)
				context.putToContext(
						HotelEnum.SEARCHCONTEXT_CUSTOMER_ACCOUNTTYPE, customer
								.getAccountType().name());
			if (customer.getCreditPeriod() != null)
				context.putToContext(
						HotelEnum.SEARCHCONTEXT_CUSTOMER_CYCLETIME,
						customer.getCreditPeriod());
			
			if(StringUtils.isNotBlank(customer.getPurchaserType())){
				context.putToContext(HotelEnum.SEARCHCONTEXT_CUSTOMER_PURCHASERTYPE, customer.getPurchaserType());
			}
			return context;
		}
//		TInventoryContext context = new TInventoryContext();
//		context.putToContext(HotelEnum.SEARCHCONTEXT_CUSTOMER_KEY, "ASDFASFAS");
//		// criteria.setCustomerKey("afasdfsdf");
//		return context;
		return null;
	}

	public TPropertySearchCriteria getTPropertySearchCriteria() {
		TPropertySearchCriteria tCriteria = new TPropertySearchCriteria();
		tCriteria.setOffset(getPageSize() * (getPageNo() - 1));
		tCriteria.setLimit(getPageSize());

		if (StringUtils.isNotBlank(cityIsoCode)) {
			List<String> cityIsoCodes = new LinkedList<String>();
			cityIsoCodes.add(cityIsoCode);
			tCriteria.setCityIsoCodes(cityIsoCodes);
		}
		if (StringUtils.isNotBlank(cityName)) {
			List<String> cityNames = new LinkedList<String>();
			cityNames.add(cityName);
			tCriteria.setCityNames(cityNames);
		}

		if (districtCodes != null && !districtCodes.isEmpty()) {
			tCriteria.setDistrictCodes(districtCodes);
		}

		if (districtNames != null && !districtNames.isEmpty()) {
			tCriteria.setDistrictNames(districtNames);
		}
		if (commercialCodes != null && !commercialCodes.isEmpty()) {
			tCriteria.setCommercialCodes(commercialCodes);
		}
		if (commercialNames != null && !commercialNames.isEmpty()) {
			tCriteria.setCommercialNames(commercialNames);
		}

		if (chainBrandIds != null && !chainBrandIds.isEmpty()) {
			tCriteria.setChainBrandIds(chainBrandIds);
		}
		if(facilitys != null && !facilitys.isEmpty()) {
			tCriteria.setFacilityNames(facilitys);
		}

		if (StringUtils.isNotBlank(checkInDate)) {
			tCriteria.setCheckInDate(checkInDate);
		} else {
			tCriteria
					.setCheckInDate(DateTimeUtil.getDate(new DateTime(), null));
		}

		if (StringUtils.isNotBlank(checkOutDate)) {
			tCriteria.setCheckOutDate(checkOutDate);
		} else {
			tCriteria.setCheckOutDate(tCriteria.getCheckInDate());
		}

		if (StringUtils.isNotBlank(hotelName)) {
			tCriteria.setHotelName(hotelName);
		}

		if (hotelRatings != null && !hotelRatings.isEmpty()) {

			tCriteria.setHotelRatings(hotelRatings);
		}

		if (hotelCategoryIds != null && !hotelCategoryIds.isEmpty()) {
			tCriteria.setHotelCategoryIds(hotelCategoryIds);
		}
		if (minPrice != null && minPrice <= 0) {
			minPrice = null;
		}
		if (maxPrice != null && maxPrice <= 0) {
			maxPrice = null;
		}
		if (minPrice != null & maxPrice != null
				&& minPrice.doubleValue() > maxPrice.doubleValue()) {
			double tmp = 0;
			tmp = minPrice;
			minPrice = maxPrice;
			maxPrice = tmp;

		}
		if (minPrice != null) {
			tCriteria.setMinPrice(minPrice.doubleValue());
		}

		if (maxPrice != null) {
			tCriteria.setMaxPrice(maxPrice.doubleValue());
		}

		if (StringUtils.isNotBlank(sortItem)) {
			tCriteria.setSortItem(TSortItem.findByValue(Integer
					.parseInt(sortItem)));
		}

		if (StringUtils.isNotBlank(sortOrder)) {
			tCriteria.setSortOrder(TSortOrder.findByValue(Integer
					.parseInt(sortOrder)));
		}
		if (StringUtils.isNotBlank(this.roomNo)) {
			try{
				tCriteria.setRoomNo(Integer.parseInt(this.roomNo));
			}catch(Exception e){
				tCriteria.setRoomNo(1);
			}
		}
		if (StringUtils.isNotBlank(roomType)) {
			tCriteria.setRoomType(roomType);
		}
		if (StringUtils.isNotBlank(nationality)) {
			tCriteria.setNationality(nationality);
		}
		
		tCriteria.setProcessorType(TProcessorType.PURCHASER);
		return tCriteria;
	}

	public TPropertySearchCriteria getDetailTPropertySearchCriteria() {
		TPropertySearchCriteria tCriteria = new TPropertySearchCriteria();

		if (StringUtils.isNotBlank(checkInDate)) {
			tCriteria.setCheckInDate(checkInDate);
		} else {
			tCriteria.setCheckInDate(DateTimeUtil.getDate(new DateTime(), null));
		}

		if (StringUtils.isNotBlank(checkOutDate)) {
			tCriteria.setCheckOutDate(checkOutDate);
		} else {
			tCriteria.setCheckOutDate(tCriteria.getCheckInDate());
		}

		if (StringUtils.isNotBlank(hotelId)) {
			tCriteria.setHotelId(hotelId);
		}
		if(ValidationUtil.isNotNull(hotelIds)){
			tCriteria.setHotelIds(hotelIds);
		}

		if (StringUtils.isNotBlank(cityIsoCode)) {
			List<String> cityIsoCodes = new ArrayList<>();
			cityIsoCodes.add(cityIsoCode);
			tCriteria.setCityIsoCodes(cityIsoCodes);
		}
		tCriteria.setRoomNo(StringUtils.isBlank(roomNo) ? 1 : Integer.parseInt(roomNo));
		
		if(StringUtils.isNotBlank(roomType)){
			tCriteria.setRoomType(roomType);
		}
		tCriteria.setProcessorType(TProcessorType.PURCHASER);
		return tCriteria;
	}

	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public int getPageNo() {
		if (pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		if (pageSize <= 0) {
			pageSize = SEARCH_DEFAULT_NUM;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getCityIsoCode() {
		return cityIsoCode;
	}

	public void setCityIsoCode(String cityIsoCode) {
		this.cityIsoCode = cityIsoCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<String> getDistrictCodes() {
		return districtCodes;
	}

	public void setDistrictCodes(List<String> districtCodes) {
		this.districtCodes = districtCodes;
	}

	public List<String> getDistrictNames() {
		return districtNames;
	}

	public void setDistrictNames(List<String> districtNames) {
		this.districtNames = districtNames;
	}

	public List<String> getCommercialCodes() {
		return commercialCodes;
	}

	public void setCommercialCodes(List<String> commercialCodes) {
		this.commercialCodes = commercialCodes;
	}

	public List<String> getCommercialNames() {
		return commercialNames;
	}

	public void setCommercialNames(List<String> commercialNames) {
		this.commercialNames = commercialNames;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public List<String> getHotelRatings() {
		return hotelRatings;
	}

	public void setHotelRatings(List<String> hotelRatings) {
		this.hotelRatings = hotelRatings;
	}

	public List<String> getHotelCategoryIds() {
		return hotelCategoryIds;
	}

	public void setHotelCategoryIds(List<String> hotelCategoryIds) {
		this.hotelCategoryIds = hotelCategoryIds;
	}

	public List<String> getChainBrandIds() {
		return chainBrandIds;
	}

	public void setChainBrandIds(List<String> chainBrandIds) {
		this.chainBrandIds = chainBrandIds;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getCustomerKey() {
		return customerKey;
	}

	public void setCustomerKey(String customerKey) {
		this.customerKey = customerKey;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomCatId() {
		return roomCatId;
	}

	public void setRoomCatId(String roomCatId) {
		this.roomCatId = roomCatId;
	}

	public String getBookingClassId() {
		return bookingClassId;
	}

	public void setBookingClassId(String bookingClassId) {
		this.bookingClassId = bookingClassId;
	}

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {

		this.conditions = conditions;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	public List<String> getFacilitys() {
		return facilitys;
	}

	public void setFacilitys(List<String> facilitys) {
		this.facilitys = facilitys;
	}
	public Boolean getFromGTA() {
		return fromGTA;
	}

	public void setFromGTA(Boolean fromGTA) {
		this.fromGTA = fromGTA;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public List<String> getHotelIds() {
		return hotelIds;
	}

	public void setHotelIds(List<String> hotelIds) {
		this.hotelIds = hotelIds;
	}

}
