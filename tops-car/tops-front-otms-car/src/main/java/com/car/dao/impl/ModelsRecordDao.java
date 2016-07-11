package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.ModelsRecord;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.IModelsRecordDao;

@Component(value = "modelsRecordDao")
@Transactional
public class ModelsRecordDao extends BaseDaoImpl<ModelsRecord, Serializable> implements IModelsRecordDao{

}