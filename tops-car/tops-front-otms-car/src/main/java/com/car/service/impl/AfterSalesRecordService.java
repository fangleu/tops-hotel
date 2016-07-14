package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.AfterSalesRecord;
import com.car.bean.Custom;
import com.car.bean.Promotion;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.ICustomDao;
import com.car.dao.IPromotionDao;
import com.car.service.IAfterSalesRecordService;

@Service
public class AfterSalesRecordService extends BaseServiceImpl<AfterSalesRecord , Serializable> implements IAfterSalesRecordService{
	
	@Autowired
	private IAfterSalesRecordDao afterSalesRdDao;
	
	@Resource(name = "afterSalesRecordDao")  
    public void setDao(IBaseDao<AfterSalesRecord,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public PageResults<AfterSalesRecord> getAfterSalesRecordList(int pageNo, int pageSize, Long type) {
		return afterSalesRdDao.getAfterSalesRecordList(pageNo, pageSize , type);
	}
	
	
	


}
