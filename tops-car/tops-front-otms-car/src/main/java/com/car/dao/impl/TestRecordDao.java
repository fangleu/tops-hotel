package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.TestRecord;
import com.car.common.dao.BaseDaoImpl;

import com.car.dao.ITestRecordDao;

@Component(value = "testRecordDao")
@Transactional
public class TestRecordDao extends BaseDaoImpl<TestRecord, Serializable> implements ITestRecordDao{

}