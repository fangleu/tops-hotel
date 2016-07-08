package com.car.dao;

import java.io.Serializable;
import com.car.bean.Models;

import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;

public interface IModelsDao extends IBaseDao<Models, Serializable>  {
	public PageResults<Models> getModelsList(int pageNo, int pageSize); 
}
