package com.car.service.test;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.car.bean.Custom;
import com.car.service.ICustomService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class CustomServiceTest {
	
	@Autowired
	private ICustomService service;
	
	
	@Test
	public void Test(){
		
//		Custom custom = new Custom();
//		custom.setCreateDate(new Date());
//		custom.setPhone("15800795127");
//		custom.setSex(1);
//		custom.setBrand("rqrrqwrqw");
//		custom.setModels("qqqq");
//		service.updateCustom(custom);
////		service.addCustom(custom);
//		
//		Custom custom1 = service.get(1L);
//		
//		Custom custom2 = service.get(10L);
//		
//		System.out.println(custom1.getSex() + "" + custom2);
//		
		Object[] object = {"15800795127"};
//		Object custom3 = service.getBySQL("select * from custom c where c.phone = ?", object);
//		Object custom3 = service.getBySQL("select * from custom c where c.phone = " + "15800795127", object);
//		System.out.println(custom3);
//		System.out.println(custom3.getWechatId());
		
		Custom custom = service.findCustomByPhone("15800795127");
		
		System.out.println("ss " + custom.getId());
		
	}
	

}
