package com.car.service;

import java.io.Serializable;
import java.util.List;

import com.car.bean.PromotionRecord;
import com.car.common.dao.IBaseService;

public interface IPromotionRdService extends IBaseService<PromotionRecord, Serializable>{
	
	/**
	 * 获取看过促销信息的最新customId
	 * @param type
	 * @return
	 */
	public List<String> getPromotionRdUserId(Long type);
	

}
