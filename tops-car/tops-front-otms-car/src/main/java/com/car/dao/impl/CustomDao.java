package com.car.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	@Override
	public List<String> getCustom(Set<String> customId) {
		List<String> result = new ArrayList<>();
		StringBuffer parm = new StringBuffer();
		List<String> listCustom = new ArrayList<String>(customId);
		for(int i =0; i < listCustom.size(); i++){
			if(i == 0) parm.append(listCustom.get(i));
			else parm.append(","+listCustom.get(i));
		}
		if(parm != null && parm.length() > 0) {
			String sql = "select * from custom where id in (" + parm + ")";
			Object[] object = null;
			List<Custom> list = super.getListBySQL(sql, Custom.class, object);
			System.out.println("CustomDao " + list.size());
			for(Custom custom : list){
				result.add(custom.getWechatId());
			}
		}
		return result;
	}
	
	@Override
	public List<Custom> getCustomList(Set<String> customId) {
		StringBuffer parm = new StringBuffer();
		List<String> listCustom = new ArrayList<String>(customId);
		List<Custom> list = null;
		for(int i =0; i < listCustom.size(); i++){
			if(i == 0) parm.append(listCustom.get(i));
			else parm.append(","+listCustom.get(i));
		}
		if(parm != null && parm.length() > 0) {
			String sql = "select * from custom where id in (" + parm + ")";
			Object[] object = null;
			list = super.getListBySQL(sql, Custom.class, object);
			System.out.println("CustomDao " + list.size());
		}
		return list;
	}

	@Override
	public List<Custom> getCustom(Long levelId) {
		String sql = "select * from custom c where c.level_id=?";
		Object[] object = {levelId};
		List<Custom> list = super.getListBySQL(sql, Custom.class, object);
		return list;
	}
	

}
