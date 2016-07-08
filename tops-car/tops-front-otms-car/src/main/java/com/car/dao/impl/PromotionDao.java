package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.Promotion;
import com.car.common.dao.BaseDaoImpl;
import com.car.common.dao.PageResults;
import com.car.dao.IPromotionDao;

@Component(value = "promotionDao")
@Transactional
public class PromotionDao extends BaseDaoImpl<Promotion, Serializable> implements IPromotionDao{

	@Override
	public PageResults<Promotion> getPromotionList(int pageNo, int pageSize, Long type) {
		String sql;
		if(type == null)
		 sql = "select * from promotion p order by p.create_time desc";
		else
			sql = "select * from promotion p where p.type=? order by p.create_time desc";
//		String sql = "select * from promotion";
		Object[] object = {type};
		PageResults<Promotion> results = super.findPageByFetchedHql(sql , Promotion.class,null,pageNo, pageSize, object);
		
		return results;
	}

}
