package com.car.dao.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.car.bean.TestRecord;
import com.car.common.dao.BaseDaoImpl;
import com.car.dao.ITestRecordDao;

@Component(value = "testRecordDao")
@Transactional
public class TestRecordDao extends BaseDaoImpl<TestRecord, Serializable> implements ITestRecordDao{

	@Override
	public Set<String> getTestRecordDao(Long isCommit) {

		DateTime startDate = new DateTime().minusDays(1).withTimeAtStartOfDay();
		DateTime endtDate = new DateTime().withTimeAtStartOfDay();
		String sql = "select * from test_drive_record m where m.is_commit = ? and m.create_time >= ? and m.create_time < ?";
		Set<String> customId = new HashSet<>();
		Object[] objects = {isCommit , startDate.toDate() , endtDate.toDate()};
		List<TestRecord> result = super.getListBySQL(sql, TestRecord.class, objects);
		for(TestRecord record : result) {
			customId.add(record.getCustomId().toString());
		}
		
		return customId;

	}
	
	

}
