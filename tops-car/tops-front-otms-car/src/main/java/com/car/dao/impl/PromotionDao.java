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
			String sql = "select * from promotion p where p.type=? order by p.create_time desc";
			PageResults<Promotion> results = super.findPageByFetchedHql(sql , Promotion.class, null, pageNo, pageSize, type);
		
		return results;
	}

}
