package com.car.service;

import java.io.Serializable;
import java.util.List;

import com.car.bean.Level;
import com.car.common.dao.IBaseService;

public interface ILevelService extends IBaseService<Level, Serializable>{

	public List<Level> getListBySQLs(String string, Object object);
	

}
