package com.car.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.Level;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.ILevelDao;

@Component(value = "levelDao")
@Transactional
public class LevelDao extends BaseDaoImpl<Level, Serializable> implements ILevelDao{

	@Override
	public List<Level> getListBySQLs(String string, Object object) {
		
		
		return null;
	}

	
	
	

}
