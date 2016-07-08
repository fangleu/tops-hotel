package com.car.dao.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


import com.car.bean.Models;
import com.car.common.dao.PageResults;

import com.car.dao.IModelsDao;


import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class ModelsDaoTest {
	
	@Autowired
	private IModelsDao dao;
	

	@Test
	public void test(){
		

		PageResults<Models> modelResults = dao.getModelsList(2,2);
		List<Models> listResults = modelResults.getResults();
		for(Models i : listResults){
			System.out.println(i.getBrand());
		}
		

		
	}
	

}
