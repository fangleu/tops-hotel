package com.car.dao.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.car.bean.Level;
import com.car.bean.Promotion;
import com.car.common.dao.PageResults;
import com.car.dao.ILevelDao;
import com.car.service.ILevelService;
import com.car.service.IPromotionService;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class LevelDaoTest {
	
	@Autowired
	private ILevelDao dao;
	
	@Autowired
	private ILevelService service;
	
	@Autowired
	private IPromotionService promotionService;
	
	@Test
	public void test(){
		
//		System.out.println("start");
//		Level level = new Level();
//		level.setName("kkkkkk");
//		level.setLevel("3");
//		 dao.save(level);
//		Object[] object = {"1"};
//		 Level list = dao.getBySQL("select * from level where id=?", Level.class ,  object);
//		Level level = dao.get(1L);
		
//		Level list = service.getBySQL("select * from level where id=1", Level.class ,  object);
//		Level level = dao.get(1L);
		
		Long type = 0L;
		int pageNo = 1;
		int pageSize =5;
		PageResults<Promotion> list1 = promotionService.getPromotionList(pageNo, pageSize, null);
		System.out.println("list1 " + list1.getResults().size());
		
//		System.out.println(list.getShopName()+ " , " + level.getShopName());
		
		
	}
	

}
