package com.car.send.message.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.car.bean.Promotion;
import com.car.common.dao.PageResults;
import com.car.service.IModelsRdService;
import com.car.service.IPromotionService;
import com.car.util.Constants;
import com.car.util.SendWeChatMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class SchedulerTest {
	
	@Autowired
	private IModelsRdService modelsRdService;
	
	@Autowired
	private SendWeChatMessage sendWeChatMessage;
	
	@Autowired
	private IPromotionService promotionService;
	
	@Test
	public void sendMessageTask(){
		
		List<String> userIdList = modelsRdService.getModelsRecordUserId();
		PageResults<Promotion> pageResults = promotionService.getPromotionList(1, 1, 1L);
		
		if(userIdList.size() > 0 && pageResults != null &&  pageResults.getResults() != null && pageResults.getResults().size() > 0){
			StringBuffer userId = new StringBuffer();
			Promotion promotion = pageResults.getResults().get(0);
			for(int i = 0; i < userIdList.size(); i++){
				if(i == 0) userId.append(userIdList.get(i));
				else userId.append("|" + userIdList.get(i));
			}
			sendWeChatMessage.sendWeChatMsg("news", userId.toString(), "0", "", "", "", promotion.getTitle(), promotion.getSketch(), 
					Constants.WEIXIN_SEND_URL + "?id=" + promotion.getId() + "&response_type=code&scope=snsapi_base&state=sunlight#wechat_redirect", 
				     promotion.getFocus(), "0");
			
		}
		
	}

}
