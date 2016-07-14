package com.car.dao;

import java.io.Serializable;

import com.car.bean.AfterSalesRecord;
import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;

public interface IAfterSalesRecordDao extends IBaseDao<AfterSalesRecord, Serializable>{

	PageResults<AfterSalesRecord> getAfterSalesRecordList(int pageNo, int pageSize, Long type);

}
