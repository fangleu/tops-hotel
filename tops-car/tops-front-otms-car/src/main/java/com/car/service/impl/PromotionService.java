package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.Promotion;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;
import com.car.dao.IPromotionDao;
import com.car.service.IPromotionService;

@Service
public class PromotionService extends BaseServiceImpl<Promotion, Serializable> implements IPromotionService{
	
	@Autowired
	private IPromotionDao promotionDao;
	
	@Resource(name = "promotionDao")  
    public void setDao(IBaseDao<Promotion,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public PageResults<Promotion> getPromotionList(int pageNo, int pageSize, Long type) {
		
		return promotionDao.getPromotionList(pageNo, pageSize , type);
	}

}
