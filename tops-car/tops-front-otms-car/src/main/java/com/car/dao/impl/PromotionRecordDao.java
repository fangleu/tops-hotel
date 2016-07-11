package com.car.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.PromotionRecord;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.IPromotionRecordDao;

@Component(value = "promotionRecordDao")
@Transactional
public class PromotionRecordDao extends BaseDaoImpl<PromotionRecord, Serializable> implements IPromotionRecordDao{
	
	
	public Set<String> getPromotionRecordDao(Long type){
		
		DateTime startDate = new DateTime().minusDays(1).withTimeAtStartOfDay();
		DateTime endtDate = new DateTime().withTimeAtStartOfDay();
		String sql = "select * from promotion_record m where m.type = ? and m.create_time >= ? and m.create_time < ?";
		Set<String> customId = new HashSet<>();
		Object[] objects = {type , startDate.toDate() , endtDate.toDate()};
		List<PromotionRecord> result = super.getListBySQL(sql, PromotionRecord.class, objects);
		for(PromotionRecord record : result) {
			customId.add(record.getCustomId().toString());
		}
		
		return customId;
		
		
	}
	

}
