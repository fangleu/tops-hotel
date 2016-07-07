package com.car.util;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class SchedulerTask {
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	@Scheduled(cron = "0 */1 * * * ?")
	public void sendMessageTask(){
		
		System.out.println(new Date() + " task");
		
	}
	
}
