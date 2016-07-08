package com.car.dao.impl;

import java.io.Serializable;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.Models;

import com.car.common.dao.BaseDaoImpl;
import com.car.common.dao.PageResults;
import com.car.dao.IModelsDao;


@Component(value = "modelsDao")
@Transactional
public class ModelsDao extends BaseDaoImpl<Models, Serializable> implements IModelsDao{



	@Override
	public PageResults<Models> getModelsList(int pageNo, int pageSize) {
		String sql = "select * from models p order by p.create_time desc";
		Object[] object = null;
		PageResults<Models> results = super.findPageByFetchedHql(sql , Models.class,null,pageNo, pageSize, object);
		
		return results;
	}

}
