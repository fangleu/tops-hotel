package com.car.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.bean.Custom;
import com.car.common.dao.BaseServiceImpl;
import com.car.common.dao.IBaseDao;
import com.car.dao.ICustomDao;
import com.car.service.ICustomService;

@Service
public class CustomService extends BaseServiceImpl<Custom , Serializable> implements ICustomService{
	
	@Autowired
	private ICustomDao customDao;
	
	@Resource(name = "customeDao")  
    public void setDao(IBaseDao<Custom,Serializable> dao) {  
        super.setDao(dao);  
    }

	@Override
	public void addCustom(Custom custom) {
		
		customDao.save(custom);
		
	}

	@Override
	public void updateCustom(Custom custom) {
		
		customDao.updateCustom(custom);
		
	}

	@Override
	public Custom findCustomByPhone(String phone) {
		
		return customDao.getBySQL("select * from custom c where c.phone = ?", Custom.class, phone);
		
	}

	@Override
	public Custom findCustomByWechatId(String wechatId) {
		
		Object[] objects = {wechatId};
		return customDao.getBySQL("select * from custom c where c.wechat_id = ?", Custom.class, objects);
	}
	
	

}
