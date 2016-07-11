package com.car.service;

import java.io.Serializable;
import java.util.List;

import com.car.bean.ModelsRecord;
import com.car.common.dao.IBaseService;

public interface IModelsRdService extends IBaseService<ModelsRecord, Serializable>{
	
	/**
	 * 获取前一天看过车型的userId
	 * @return
	 */
	List<String> getModelsRecordUserId();

}
