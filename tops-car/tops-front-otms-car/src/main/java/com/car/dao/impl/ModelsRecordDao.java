package com.car.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.ModelsRecord;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.IModelsRecordDao;

@Component(value = "mdelsRecordDao")
@Transactional
public class ModelsRecordDao extends BaseDaoImpl<ModelsRecord, Serializable> implements IModelsRecordDao{

	@Override
	public Set<String> getModelsRecordDao() {

		DateTime startDate = new DateTime().minusDays(1).withTimeAtStartOfDay();
		DateTime endtDate = new DateTime().withTimeAtStartOfDay();
		String sql = "select * from models_record m where m.create_time >= ? and m.create_time < ?";
		Set<String> customId = new HashSet<>();
		Object[] objects = {startDate.toDate() , endtDate.toDate()};
		List<ModelsRecord> result = super.getListBySQL(sql, ModelsRecord.class, objects);
		for(ModelsRecord record : result) {
			customId.add(record.getCustomId().toString());
		}
		
		return customId;
	}
	
	
}
