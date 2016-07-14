package com.car.dao;

import java.io.Serializable;
import java.util.List;

import com.car.bean.ConsultingRecord;
import com.car.bean.Custom;
import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;

public interface IConsultingRecordDao extends IBaseDao<ConsultingRecord, Serializable> {

	PageResults<ConsultingRecord> getConsultingRecordList(int pageNo, int pageSize, String customId);


}
