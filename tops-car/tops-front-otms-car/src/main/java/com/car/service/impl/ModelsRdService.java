package com.car.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.ModelsRecord;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.ICustomDao;
import com.car.dao.IModelsRecordDao;
import com.car.service.IModelsRdService;

@Service
public class ModelsRdService extends BaseServiceImpl<ModelsRecord, Serializable> implements IModelsRdService{

	@Autowired
	private IModelsRecordDao mdelsRecordDao;
	
	@Autowired
	private ICustomDao customDao;
	
	@Resource(name = "mdelsRecordDao")  
    public void setDao(IBaseDao<ModelsRecord,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public List<String> getModelsRecordUserId() {
		
		Set<String> customId = mdelsRecordDao.getModelsRecordDao();
		List<String> userId = customDao.getCustom(customId);
		
		return userId;
	}
	
}
