package com.car.service;

import java.io.Serializable;

import com.car.bean.AfterSalesRecord;
import com.car.common.dao.IBaseService;
import com.car.common.dao.PageResults;

public interface IAfterSalesRecordService extends IBaseService<AfterSalesRecord, Serializable>{

	PageResults<AfterSalesRecord> getAfterSalesRecordList(int pageNo, int pageSize, Long type);

}