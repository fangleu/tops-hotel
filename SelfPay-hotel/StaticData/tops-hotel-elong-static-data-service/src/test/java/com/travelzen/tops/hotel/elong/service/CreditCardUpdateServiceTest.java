package com.travelzen.tops.hotel.elong.service;


import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;
import com.travelzen.tops.hotel.elong.staticdata.service.ICreditCardUpdateService;


public class CreditCardUpdateServiceTest  extends BaseTest {

	
	@Resource
	private ICreditCardUpdateService creditCardUpdateService = null;
	
	
	@Test
	public void testCreditCardUpdate() throws CommonException {
		Assert.assertNotNull(creditCardUpdateService);
		creditCardUpdateService.creditCardUpdate();
	}

}
