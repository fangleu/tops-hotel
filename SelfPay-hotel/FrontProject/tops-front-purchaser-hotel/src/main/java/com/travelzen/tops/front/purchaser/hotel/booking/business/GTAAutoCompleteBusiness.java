package com.travelzen.tops.front.purchaser.hotel.booking.business;

import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.creme.search.client.CremeHotelServiceClient;
import com.travelzen.tops.hotel.thrift.gta.service.db.SearchAreaResult;
import com.travelzen.tops.hotel.thrift.gta.service.db.SearchCityResult;
import com.travelzen.tops.hotel.thrift.gta.service.db.SearchCountryResult;

/**
 * GTA 城市数据自动补全类
 * @author xumeng
 *
 */
@Service
public class GTAAutoCompleteBusiness {

	CremeHotelServiceClient thriftClient = CremeHotelServiceClient.getInstance();

	public SearchCityResult searchGTACityOrAreaByWord(String word, String lang) {
		return thriftClient.searchGTACityOrAreaByWord(word, lang);
	}

	public SearchAreaResult searchGTAAreaByWord(String word, String lang) {
		return thriftClient.searchGTAAreaByWord(word, lang);
	}

	public SearchCountryResult searchGTACountryByWord(String word, String lang) {
		return thriftClient.searchGTACountryByWord(word, lang);
	}

}
