package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.AfterSalesRecord;
import com.car.bean.ConsultingRecord;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.IConsultingRecordDao;

@Component(value = "consultingRecordDao")
@Transactional
public class ConsultingRecordDao extends BaseDaoImpl<ConsultingRecord, Serializable> implements IConsultingRecordDao{

}
