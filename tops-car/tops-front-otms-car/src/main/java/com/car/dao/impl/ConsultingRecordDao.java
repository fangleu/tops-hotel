package com.car.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.AfterSalesRecord;
import com.car.bean.ConsultingRecord;
import com.car.bean.Promotion;
import com.car.common.dao.BaseDaoImpl;
import com.car.common.dao.PageResults;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.IConsultingRecordDao;

@Component(value = "consultingRecordDao")
@Transactional
public class ConsultingRecordDao extends BaseDaoImpl<ConsultingRecord, Serializable> implements IConsultingRecordDao{

	@Override
	public PageResults<ConsultingRecord> getConsultingRecordList(int pageNo, int pageSize, String customId) {
		String sql;
		sql = "select * from consulting_records p where p.custom_id=?";
		String countSql = "select count(*) from consulting_records p where p.custom_id=?";
		Object[] obj = {customId};
		PageResults<ConsultingRecord> results = super.findPageByFetchedHql(sql , ConsultingRecord.class, null, pageNo, pageSize, obj);
		
		return results;
	}

}
