package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.Models;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.common.dao.PageResults;
import com.car.dao.IModelsDao;
import com.car.service.IModelsService;

@Service
public class ModelsService extends BaseServiceImpl<Models, Serializable> implements IModelsService{
	
	@Autowired
	private IModelsDao modelsDao;
	
	@Resource(name = "modelsDao")  
    public void setDao(IBaseDao<Models,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public PageResults<Models> getModelsList(int pageNo, int pageSize) {
		
		return modelsDao.getModelsList(pageNo, pageSize);
	}

}
