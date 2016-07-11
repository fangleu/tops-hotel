package com.car.service;

import java.io.Serializable;

import com.car.bean.Activity;
import com.car.common.dao.IBaseService;
import com.car.common.dao.PageResults;

public interface IActivityService extends IBaseService<Activity, Serializable>{
	
	PageResults<Activity> getActivityList(int pageNo, int pageSize);

}
