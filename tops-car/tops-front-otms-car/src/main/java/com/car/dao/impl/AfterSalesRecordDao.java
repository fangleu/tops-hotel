package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.AfterSalesRecord;
import com.car.bean.Custom;
import com.car.bean.Promotion;
import com.car.common.dao.BaseDaoImpl;
import com.car.common.dao.PageResults;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.ICustomDao;

@Component(value = "afterSalesRecordDao")
@Transactional
public class AfterSalesRecordDao extends BaseDaoImpl<AfterSalesRecord, Serializable> implements IAfterSalesRecordDao{
	@Override
	public PageResults<AfterSalesRecord> getAfterSalesRecordList(int pageNo, int pageSize, Long type) {
		String sql;
		String countSql;
		if(type == null) {
			sql = "select * from after_sales_record p order by p.create_time desc";
			countSql = "select count(1) from after_sales_record";
		} else {
 			sql = "select * from after_sales_record p where p.type=? order by p.create_time desc";
 			countSql = "select count(1) from after_sales_record p where p.type=? ";
		}	
		Object[] object = {type};
		PageResults<AfterSalesRecord> results = super.findPageByFetchedHql(sql , AfterSalesRecord.class, countSql, pageNo, pageSize, object);
		
		return results;
	}
}
