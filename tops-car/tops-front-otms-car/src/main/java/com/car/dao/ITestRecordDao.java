package com.car.dao;

import java.io.Serializable;
import java.util.Set;

import com.car.bean.TestRecord;
import com.car.common.dao.IBaseDao;

public interface ITestRecordDao extends IBaseDao<TestRecord, Serializable>  {
	
	/**
	 * 获取看过预约试驾信息的customId
	 * @param type
	 * @return
	 */
	Set<String> getTestRecordDao(Long isCommit);

}
