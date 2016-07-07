package com.car.service;

import java.io.Serializable;

import com.car.bean.Custom;
import com.car.common.dao.IBaseService;

public interface ICustomService extends IBaseService<Custom , Serializable>{
	
	Custom findCustomByPhone(String phone);
	
	void addCustom(Custom custom);
	
	void updateCustom(Custom custom);
	
	Custom findCustomByWechatId(String phone);

	
}
