package com.travelzen.tops.hotel.elong.staticdata.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.common.exception.FileDownloadException;
import com.travelzen.tops.hotel.elong.mongo.dao.ICreditCardDao;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.CreditCard;
import com.travelzen.tops.hotel.elong.staticdata.service.ICreditCardUpdateService;
import com.travelzen.tops.hotel.elong.staticdata.service.IHotelStaticFileDownloadService;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.CreditCardParser;

@Service(value="creditCardUpdateService")
public class CreditCardUpdateService implements ICreditCardUpdateService {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private IHotelStaticFileDownloadService hotelStaticFileDownloadService = null;
	
	@Resource
	private ICreditCardDao creditCardDao = null;
	
	@Override
	public void creditCardUpdate() throws CommonException{
		LOG.info("开始更新信用卡信息");
		long startTime = System.currentTimeMillis();
		List<CreditCard> creditCards = null;
		try {
			CreditCardParser creditCardParser = (CreditCardParser) hotelStaticFileDownloadService.downloadCreditCard();
			creditCards = creditCardParser.getCreditCards();
			if(creditCards == null || creditCards.size() <= 0){
				throw CommonException.instance("没有获得信用卡基础数据");
			}
		} catch (FileDownloadException e) {
			LOG.error(e.getMessage(), e);
		}
		if(creditCardDao.deleteAll()){
			creditCardDao.saveEntity(creditCards);
		}
		long endTime = System.currentTimeMillis();
		LOG.info("[更新信用卡信息完毕][用时：{} seconds]",(endTime - startTime) / 1000);
	}

	@Override
	public Map<String, String> findCreditCardAndName() {
		return creditCardDao.findCreditCardAndName();
	}

	@Override
	public List<String> findCreditCardList(String lang) {
		return creditCardDao.findCreditCardList(lang);
	}
	
}
