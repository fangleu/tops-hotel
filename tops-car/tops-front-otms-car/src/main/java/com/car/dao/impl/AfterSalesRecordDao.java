package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.AfterSalesRecord;
import com.car.bean.Custom;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.ICustomDao;

@Component(value = "afterSalesRecordDao")
@Transactional
public class AfterSalesRecordDao extends BaseDaoImpl<AfterSalesRecord, Serializable> implements IAfterSalesRecordDao{

}
