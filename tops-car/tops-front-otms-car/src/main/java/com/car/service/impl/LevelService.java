package com.car.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.Level;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.ILevelDao;
import com.car.service.ILevelService;

@Service
public class LevelService extends BaseServiceImpl<Level, Serializable> implements ILevelService{
	
	@Autowired
	private ILevelDao dao;
	
	@Resource(name = "levelDao")  
    public void setDao(IBaseDao<Level,Serializable> dao) {  
        super.setDao(dao);  
    }
	
	public List<Level> getListBySQLs(String string, Object object){
		List<Level> list = new ArrayList<>();
		
		Level level = dao.getBySQL(string, Level.class, object);
		list.add(level);
		
		return list;
	}

	

}
