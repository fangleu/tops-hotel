package com.car.send.message.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.car.util.Constants;
import com.car.util.SendWeChatMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class MessageTest {
	
	@Autowired
	private SendWeChatMessage sendWeChatMessage;
	
	@Test
	public void test() {
		SendWeChatMessage weChat = new SendWeChatMessage();
//		weChat.sendWeChatMsgText("LouFangLei", 0, "", "测试消息", "0");
		
//		weChat.sendWeChatMsg("text", "LouFangLei", "2", "", "测试senMsg", "", "", "", "", "", "0");
		
//		weChat.sendWeChatMsg("news", "LouFangLei", "0", "", "测试senMsg", 
//				"http://cnews.chinadaily.com.cn/img/attachement/png/site1/20160629/d8cb8a14fb9018dd3ddb08.png", "", "", "", "", "0");
		
		sendWeChatMessage.sendWeChatMsg("news", "LouFangLei", "0", "", "", "", "车型推荐", "废物如其人", 
				"https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.CORP_ID + 
		"&redirect_uri=http://fangleu.ngrok.cc/tops-front-otms-car/check/value"  
			     + "&response_type=code&scope=snsapi_base&state=sunlight#wechat_redirect", 
		"http://cnews.chinadaily.com.cn/img/attachement/png/site1/20160629/d8cb8a14fb9018dd3ddb08.png", "0");
		
//		sendWeChatMessage.sendWeChatMsg("news", "LouFangLei", "0", "", "", "", "车型推荐", "废物如其人", 
//				"https://qy.weixin.qq.com/cgi-bin/loginpage?corp_id=wxfdfd1b46c655aacf&redirect_uri="
//				+ "http://fangleu.ngrok.cc/tops-front-otms-car/check/value", 
//		"http://cnews.chinadaily.com.cn/img/attachement/png/site1/20160629/d8cb8a14fb9018dd3ddb08.png", "0");
		
		 
	}
	

}
