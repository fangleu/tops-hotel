package com.travelzen.tops.hotel.order.schedule.main;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class HotelOrderScheduleMain {
	
	private static Logger LOG = LoggerFactory.getLogger(HotelOrderScheduleMain.class);

	public static void main(String[] args){
		long startTime = System.currentTimeMillis();
		LOG.info("[酒店定时器，启动成功开始！]");
		//加载logback配置文件
		initSpecifyLogbackConfigFile();
		//初始化spring容器
		initSpringContext();
		long endTime = System.currentTimeMillis();
		LOG.info("[酒店定时器，启动成功！][花费时间 = {} 毫秒]",endTime - startTime);
	}
	
	@SuppressWarnings("resource")
	private static void initSpringContext(){
		new ClassPathXmlApplicationContext(
			"classpath:spring/applicationContext-hotel-order-schedule.xml"
		);
	}
	
	private static void initSpecifyLogbackConfigFile(){
		LoggerContext loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(loggerContext);
        loggerContext.reset();
        try {
        	//从文件系统中加载，用于开发环境，eclipse中运行
        	File file = new File("src/main/resources/logback-hotel-order-schedule.xml");
			if(file.exists()){
				configurator.doConfigure(file);
				StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
				System.out.println("******************* 【开发环境】从本地文件系统加载 logback-hotel-order-schedule.xml 配置文件完成********************");
			}else{
				//用于jar包运行
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				if (classLoader == null) {
					classLoader = HotelOrderScheduleMain.class.getClassLoader();
				}
				@SuppressWarnings("static-access")
				InputStream inputStream = classLoader.getSystemResourceAsStream("logback-hotel-order-schedule.xml");
				if(inputStream == null){
					System.out.println("*******************无法从类路径上获得 logback-hotel-order-schedule.xml 配置文件********************");
				}
				configurator.doConfigure(inputStream);
				StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
				System.out.println("******************* 【jar包环境】从类路径上加载 logback-hotel-order-schedule.xml 配置文件完成********************");
			}
       } catch (JoranException e) {
    	   e.printStackTrace();
        }
	}
	
}
