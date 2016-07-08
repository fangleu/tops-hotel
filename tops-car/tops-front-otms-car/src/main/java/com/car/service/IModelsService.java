package com.car.service;

import java.io.Serializable;

import com.car.bean.Models;
import com.car.common.dao.IBaseService;
import com.car.common.dao.PageResults;

public interface IModelsService extends IBaseService<Models, Serializable>{
	
	PageResults<Models> getModelsList(int pageNo, int pageSize);

}
