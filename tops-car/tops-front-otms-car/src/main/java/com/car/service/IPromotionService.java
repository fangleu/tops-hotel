package com.car.service;

import java.io.Serializable;

import com.car.bean.Promotion;
import com.car.common.dao.IBaseService;
import com.car.common.dao.PageResults;

public interface IPromotionService extends IBaseService<Promotion, Serializable>{
	
	PageResults<Promotion> getPromotionList(int pageNo, int pageSize);

}
