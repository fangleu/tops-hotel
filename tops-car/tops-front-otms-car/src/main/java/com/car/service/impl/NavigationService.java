package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.Navigation;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.INavigationDao;
import com.car.service.INavigationService;

@Service
public class NavigationService extends BaseServiceImpl<Navigation, Serializable> implements INavigationService{
	
	@Autowired
	private INavigationDao navigationDao;
	
	@Resource(name = "navigationDao")  
    public void setDao(IBaseDao<Navigation,Serializable> dao) {  
        super.setDao(dao);  
    }
	
	
	

}
