package com.car.service;

import java.io.Serializable;
import java.util.List;

import com.car.bean.Custom;
import com.car.bean.TestRecord;
import com.car.common.dao.IBaseService;

public interface ITestRdService extends IBaseService<TestRecord, Serializable>{
	
	/**
	 * 获取看过预约试驾的最新customId
	 * @param type
	 * @return
	 */
	public List<Custom> getTestRdUserId(Long isCommit);

}
