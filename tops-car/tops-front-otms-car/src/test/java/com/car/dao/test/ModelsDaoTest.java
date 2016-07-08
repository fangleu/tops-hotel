package com.car.dao.test;


import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


import com.car.bean.Models;
import com.car.bean.ModelsRecord;
import com.car.common.dao.PageResults;

import com.car.dao.IModelsDao;
import com.car.dao.IModelsRecordDao;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class ModelsDaoTest {
	
	@Autowired
	private IModelsDao dao;
	
	@Autowired
	private IModelsRecordDao recordDao;
	
	
	

	@Test
	public void test(){
		
		ModelsRecord prom = new ModelsRecord();
		DateTime startDate = new DateTime().minusDays(1);
		prom.setCustomId(11L);
		prom.setFrequency(1L);
		prom.setModelsId(1L);
		prom.setCreateDate(startDate.toDate());
		
		recordDao.save(prom);

		PageResults<Models> modelResults = dao.getModelsList(2,2);
		List<Models> listResults = modelResults.getResults();
		for(Models i : listResults){
			System.out.println(i.getBrand());
		}
		
		
		recordDao.getModelsRecordDao();
		

		
	}
	

}
