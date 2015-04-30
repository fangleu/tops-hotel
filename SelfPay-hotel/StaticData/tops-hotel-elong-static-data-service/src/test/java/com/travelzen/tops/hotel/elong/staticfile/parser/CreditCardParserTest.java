package com.travelzen.tops.hotel.elong.staticfile.parser;

import org.junit.Assert;
import org.junit.Test;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.CreditCard;
import com.travelzen.tops.hotel.elong.service.BaseTest;
import com.travelzen.tops.hotel.elong.staticdata.staticfile.parser.CreditCardParser;

public class CreditCardParserTest extends BaseTest {
	
	@Test
	public void testCreditCardParser(){
		CreditCardParser creditCardParser = new CreditCardParser(getInputStream("creditCard/creditcards.xml"));
		Assert.assertNotNull(creditCardParser);
		LOG.info("[CreditCards = {}]",creditCardParser.getCreditCards().size());
		Assert.assertNotNull(creditCardParser.getCreditCards().get(3));
		CreditCard creditCard = creditCardParser.getCreditCards().get(0);
		LOG.info("[CategoryName = {}]",creditCard.getCategoryName());
		LOG.info("[CategoryNameEn = {}]",creditCard.getCategoryNameEn());
		
	}
		
}
