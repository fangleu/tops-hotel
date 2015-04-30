package com.travelzen.tops.hotel.elong.mongo.dao;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"classpath:spring/applicationContext-hotel-elong-mongo.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
	
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	
}
