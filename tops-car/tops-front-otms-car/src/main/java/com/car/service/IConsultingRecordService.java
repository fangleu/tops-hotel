package com.car.service;

import java.io.Serializable;

import com.car.bean.ConsultingRecord;
import com.car.common.dao.IBaseService;
import com.car.common.dao.PageResults;

public interface IConsultingRecordService extends IBaseService<ConsultingRecord, Serializable>{
	
	PageResults<ConsultingRecord> getConsultingRecordList(int pageNo, int pageSize, String custom_id);

}