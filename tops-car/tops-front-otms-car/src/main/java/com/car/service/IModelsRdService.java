package com.car.service;

import java.io.Serializable;
import java.util.List;

import com.car.bean.AfterSalesRecord;
import com.car.bean.ModelsRecord;
import com.car.common.dao.IBaseService;
import com.car.common.dao.PageResults;

public interface IModelsRdService extends IBaseService<ModelsRecord, Serializable>{
	
	/**
	 * 获取前一天看过车型的userId
	 * @return
	 */
	List<String> getModelsRecordUserId();

	PageResults<ModelsRecord> getModelsRecordList(int pageNo, int pageSize, Long id);
}
