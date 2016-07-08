package com.car.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.car.bean.ModelsRecord;
import com.car.common.dao.IBaseDao;

public interface IModelsRecordDao extends IBaseDao<ModelsRecord, Serializable> {

	/**
	 * 获取前一天看过车型的customId
	 * @return
	 */
	Set<String> getModelsRecordDao();
	
}
