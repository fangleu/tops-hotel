package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.TestRecord;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.ITestRecordDao;
import com.car.service.ITestRdService;

@Service
public class TestRdService extends BaseServiceImpl<TestRecord, Serializable> implements ITestRdService{

	@Autowired
	private ITestRecordDao testRecordDao;
	
	@Resource(name = "testRecordDao")  
    public void setDao(IBaseDao<TestRecord,Serializable> dao) {  
        super.setDao(dao);  
    }
	
}
