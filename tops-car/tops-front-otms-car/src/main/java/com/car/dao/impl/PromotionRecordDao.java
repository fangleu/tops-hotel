package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.PromotionRecord;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.IPromotionRecordDao;

@Component(value = "promotionRecordDao")
@Transactional
public class PromotionRecordDao extends BaseDaoImpl<PromotionRecord, Serializable> implements IPromotionRecordDao{

}
