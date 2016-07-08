package com.car.dao;

import java.io.Serializable;

import com.car.bean.Promotion;
import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;

public interface IPromotionDao extends IBaseDao<Promotion, Serializable> {
	
	PageResults<Promotion> getPromotionList(int pageNo, int pageSize, Long type);

}
