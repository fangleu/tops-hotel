package com.travelzen.tops.front.purchaser.hotel.booking.business;

import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.creme.search.client.CremeHotelServiceClient;
import com.travelzen.tops.hotel.thrift.creme.common.TPropertySearchBo;
import com.travelzen.tops.hotel.thrift.creme.pojo.searches.TPropertySearchResultBo;

@Service
public class BookingSearchAdvancePayBusiness {

	CremeHotelServiceClient thriftClient = CremeHotelServiceClient.getInstance();

	public TPropertySearchResultBo search(BookingSearchCriteria criteria) {

		// 自签酒店
		return thriftClient.searchProduct(criteria.getTInventoryContext(), criteria.getTPropertySearchCriteria());
	}

	public TPropertySearchBo searchSingle(BookingSearchCriteria criteria) {
		return thriftClient.getPropertySearchBo(criteria.getTInventoryContext(), criteria.getDetailTPropertySearchCriteria());
	}
	
	/**获取热门酒店*/
	public TPropertySearchResultBo searchHotHotel(BookingSearchCriteria criteria){
		return thriftClient.searchProductForHotHotel(criteria.getTInventoryContext(), criteria.getDetailTPropertySearchCriteria());
	}
	
	/**
	 * 搜索GTA酒店列表
	 * 2014-3-26
	 */
	public TPropertySearchResultBo searchGTAHotel(BookingSearchCriteria criteria) {
		return thriftClient.searchGTAHotel(criteria.getTInventoryContext(), criteria.getTPropertySearchCriteria());
	}
	
	/**
	 * 获得一个GTA酒店的详细信息，用于酒店详情页
	 * 2014-3-26
	 */
	public TPropertySearchBo getGTAPropertySearchBo(BookingSearchCriteria criteria) {
		return thriftClient.getGTAPropertySearchBo(criteria.getTInventoryContext(), criteria.getDetailTPropertySearchCriteria());
	}

}
