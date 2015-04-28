package com.travelzen.tops.front.hotel.chinaonline.updater.test;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.tops.front.hotel.chinaonline.common.MessageResult;
import com.travelzen.tops.front.hotel.chinaonline.service.IChinaOnlineUpdaterService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/app-hotel-ChinaolineUpdater.xml"})
public class MessageResultTest {

	@Resource
	private IChinaOnlineUpdaterService chinaOnlineUpdaterService;
	
	@Test
	public void test() throws JAXBException{
		
		MessageResult result = new MessageResult();
		
		result.setResult("success");
		
		chinaOnlineUpdaterService.BeanToXml(result, MessageResult.class);
		
		
	}
	
}
