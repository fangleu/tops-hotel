package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.PromotionRecord;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.IPromotionRecordDao;
import com.car.service.IPromotionRdService;

@Service
public class PromotionRdService extends BaseServiceImpl<PromotionRecord, Serializable> implements IPromotionRdService{

	@Autowired
	private IPromotionRecordDao promotionRecordDao;
	
	@Resource(name = "promotionRecordDao")  
    public void setDao(IBaseDao<PromotionRecord,Serializable> dao) {  
        super.setDao(dao);  
    }
	
}
