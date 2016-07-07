package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.Custom;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.ICustomDao;

@Component(value = "customeDao")
@Transactional
public class CustomDao extends BaseDaoImpl<Custom, Serializable> implements ICustomDao{

	@Override
	public void updateCustom(Custom custom) {
		
		String sql = sqlJoint(custom);
		super.getSession().createSQLQuery(sql).executeUpdate();
		
	}
	
	private String sqlJoint(Custom custom){
		
		StringBuilder sql = new StringBuilder();
		boolean boo = false;
		sql.append("update custom c set ");
		
		if(custom.getName() != null) {
			sql.append("c.name = " + "'" + custom.getName() + "'");
			boo=true;
		}
		if(custom.getWechatId() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.wechat_id = " + "'" + custom.getWechatId() + "'");
			boo=true;
		}
		if(custom.getName() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.name = " + "'" + custom.getName() + "'");
			boo=true;
		}
		if(custom.getSex() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.sex = " + "'" + custom.getSex() + "'");
			boo=true;
		}
		if(custom.getAddress() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.address = " + "'" + custom.getAddress() + "'");
			boo=true;
		}
		if(custom.getModels() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.models = " + "'" + custom.getModels() + "'");
			boo=true;
		}
		if(custom.getDatePurchase() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.date_purchase = " + "'" + custom.getDatePurchase() + "'");
			boo=true;
		}
		if(custom.getPlateNumber() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.plate_number = " + "'" + custom.getPlateNumber() + "'");
			boo=true;
		}
		if(custom.getBrand() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.brand = " + "'" + custom.getBrand() + "'");
			boo=true;
		}
		if(custom.getLevelId() != null) {
			if(boo) sql.append(" , ");
			sql.append(" c.level_id = " + "'" + custom.getLevelId() + "'");
			boo=true;
		}
		
		sql.append(" where c.phone = " + "'" + custom.getPhone() + "'");
		return sql.toString();
		
	}
	

}
