package com.car.service.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.car.bean.Promotion;
import com.car.common.dao.PageResults;
import com.car.service.IPromotionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class PromotionServiceTest {
	
	@Autowired
	private IPromotionService promotionService;
	
	@Test
	public void Test(){
		
		Promotion promotion = new Promotion();
		
		promotion.setTitle("测试标题");
		promotion.setStartTime(new Date());
		promotion.setEndTime(new Date());
		promotion.setCreateTime(new Date());
		promotion.setType(0L);
		promotion.setFocus("http://n.sinaimg.cn/translate/20160705/9TTK-fxtspsa6651757.jpg");
		promotion.setSketch("可以自定义数据库与POJO属性的对应关系");
		promotion.setImage("http://n.sinaimg.cn/mobileh5/20160705/JFWW-fxtsatm1375638.jpg");
		promotion.setDetail("可以自定义数据库与POJO属性的对应关系");
		promotionService.save(promotion);
		
	}
	
	
	@Test
	public void TestList(){
		
		PageResults<Promotion> list = promotionService.getPromotionList(1, 10,null);
		
		System.out.println(list.getTotalCount());
		
		Promotion promotion = promotionService.get(4L);
		
		System.out.println(promotion.getTitle());
		
	}

}
