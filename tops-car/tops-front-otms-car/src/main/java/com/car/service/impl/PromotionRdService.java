package com.car.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.PromotionRecord;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.ICustomDao;
import com.car.dao.IPromotionRecordDao;
import com.car.service.IPromotionRdService;

@Service
public class PromotionRdService extends BaseServiceImpl<PromotionRecord, Serializable> implements IPromotionRdService{

	@Autowired
	private IPromotionRecordDao promotionRecordDao;
	
	@Autowired
	private ICustomDao customDao;
	
	@Resource(name = "promotionRecordDao")  
    public void setDao(IBaseDao<PromotionRecord,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public List<String> getPromotionRdUserId(Long type) {
		
		
		Set<String> customId = promotionRecordDao.getPromotionRecordDao(type);
		List<String> userId = customDao.getCustom(customId);
		
		return userId;
	}
	
}
