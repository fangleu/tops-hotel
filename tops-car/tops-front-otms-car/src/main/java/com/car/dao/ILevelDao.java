package com.car.dao;

import java.io.Serializable;
import java.util.List;

import com.car.bean.Level;
import com.car.common.dao.IBaseDao;

public interface ILevelDao extends IBaseDao<Level, Serializable>{

	List<Level> getListBySQLs(String string, Object object);
	
	

}
