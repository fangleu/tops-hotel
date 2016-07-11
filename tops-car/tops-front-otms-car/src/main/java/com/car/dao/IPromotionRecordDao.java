package com.car.dao;

import java.io.Serializable;
import java.util.Set;

import com.car.bean.PromotionRecord;
import com.car.common.dao.IBaseDao;

public interface IPromotionRecordDao extends IBaseDao<PromotionRecord, Serializable> {

	/**
	 * 获取看过促销信息的customId
	 * @param type
	 * @return
	 */
	Set<String> getPromotionRecordDao(Long type);
	
}
