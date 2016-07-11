package com.car.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.car.bean.Custom;
import com.car.common.dao.IBaseDao;

public interface ICustomDao extends IBaseDao<Custom, Serializable>{
	
	void updateCustom(Custom custom);
	
	
	List<String> getCustom(Set<String> customId);
	
	List<Custom> getCustom(Long levelId);
	

}
