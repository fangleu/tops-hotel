package com.car.dao;

import java.io.Serializable;

import com.car.bean.Custom;
import com.car.common.dao.IBaseDao;

public interface ICustomDao extends IBaseDao<Custom, Serializable>{
	
	void updateCustom(Custom custom);

}
