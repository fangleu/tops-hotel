package com.travelzen.tops.hotel.elong.service;

import java.io.File;
import java.io.InputStream;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.travelzen.tops.hotel.elong.common.constants.CommonConstants;
import com.travelzen.tops.hotel.elong.common.utils.ElongConfiguration;

@ContextConfiguration(locations={"classpath*:spring/applicationContext-hotel-elong-service.xml","classpath*:spring/applicationContext-hotel-elong-mongo.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
	
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private ElongConfiguration elongConfiguration = null;
	
	public File getFile(String path) {
		if (path == null || path.length() <= 0) {
			return null;
		}
		File input = null;
		try {
			String inputFile = Thread.currentThread().getContextClassLoader().getResource(path).getFile();
			LOG.info(inputFile);
			input = new File(inputFile);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		if (input == null || !input.exists()) {
			return null;
		}
		return input;
	}
	
	public InputStream getInputStream(String path) {
		if (path == null || path.length() <= 0) {
			return null;
		}
		InputStream input = null;
		try {
			input = ClassLoader.getSystemResourceAsStream(path);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		if (input == null) {
			return null;
		}
		return input;
	}
	
	@Test
	public void testConfiguration(){
		Assert.assertNotNull(elongConfiguration);
		try {
			int soTimeout = elongConfiguration.getInt(CommonConstants.ElongConfigurationKey.HOTELDETAIL_DOWNLOAD_TIMEOUT.keyName(),20000);
			LOG.info("soTimeout = {}",soTimeout);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}

}
