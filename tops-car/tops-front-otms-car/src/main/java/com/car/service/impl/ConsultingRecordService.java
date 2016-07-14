package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.AfterSalesRecord;
import com.car.bean.ConsultingRecord;
import com.car.bean.Promotion;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.IConsultingRecordDao;
import com.car.service.IConsultingRecordService;
import com.car.service.IPromotionService;

@Service
public class ConsultingRecordService extends BaseServiceImpl<ConsultingRecord, Serializable> implements IConsultingRecordService{

	
	@Autowired
	private IConsultingRecordDao consultingRdDao;
	
	@Resource(name = "consultingRecordDao")  
    public void setDao(IBaseDao<ConsultingRecord,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public PageResults<ConsultingRecord> getConsultingRecordList(int pageNo, int pageSize, String custom_id) {
		return consultingRdDao.getConsultingRecordList(pageNo, pageSize, custom_id);
	}

}
