package com.car.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.Custom;
import com.car.bean.TestRecord;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.ICustomDao;
import com.car.dao.ITestRecordDao;
import com.car.service.ITestRdService;

@Service
public class TestRdService extends BaseServiceImpl<TestRecord, Serializable> implements ITestRdService{

	@Autowired
	private ITestRecordDao testRecordDao;
	
	@Autowired
	private ICustomDao customDao;
	
	@Resource(name = "testRecordDao")  
    public void setDao(IBaseDao<TestRecord,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public List<Custom> getTestRdUserId(Long isCommit) {

		Set<String> customId = testRecordDao.getTestRecordDao(isCommit);
		List<Custom> userId = customDao.getCustomList(customId);
		
		return userId;
	}
	
}
