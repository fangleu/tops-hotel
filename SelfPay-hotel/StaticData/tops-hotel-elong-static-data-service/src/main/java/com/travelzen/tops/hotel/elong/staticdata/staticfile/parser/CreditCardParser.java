package com.travelzen.tops.hotel.elong.staticdata.staticfile.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.CreditCard;

public class CreditCardParser extends DefaultHandler {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass()); 
	
	private List<CreditCard> creditCards = null;
	
	private CreditCard creditCard = null;
	
	private String text;
	
	private String lastElementNameFlag;
		
    public CreditCardParser(InputStream inputStream, String lang) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputStream, this);
        } catch (ParserConfigurationException e) {
            LOG.debug( e.getMessage(),e);
        } catch (SAXException e) {
        	LOG.debug( e.getMessage(),e);
        } catch (IOException e) {
        	LOG.debug( e.getMessage(),e);
        }
    }
    
    public CreditCardParser(InputStream inputStream) {
        this(inputStream, null);
    }
    
    public void gatherCreditCardTextEnd(String elementName){
    	if(text == null || text.length() <= 0){
    		return;
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("CreditCard") && elementName.equals("CategoryId")){
    		creditCard.setCategoryId(Integer.parseInt(text));
    		LOG.debug("[CategoryId = {}]",text);
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("CreditCard") && elementName.equals("CategoryName")){
    		creditCard.setCategoryName(text);
    		LOG.debug("[CategoryName = {}]",text);
    		
    	}
    	if(lastElementNameFlag != null && lastElementNameFlag.equals("CreditCard") && elementName.equals("CategoryNameEn")){
    		creditCard.setCategoryNameEn(text);
    		LOG.debug("[CategoryNameEn = {}]",text);
    		
    	}
    }
    
    @Override
    public void startElement(String localName, String qName, String elementName, Attributes attributes) throws SAXException {
    	if(elementName.equals("CreditCardList")){
    		creditCards = new ArrayList<>();
    	}
    	if(elementName.equals("CreditCard")){
    		creditCard = new CreditCard();
        	lastElementNameFlag = elementName;
    	}
    }
    
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
    	gatherCreditCardTextEnd(element);
    	if(element.equals("CreditCard")){
    		creditCards.add(creditCard);
    	}
    }
    
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
    	text = new String(ac, i, j);
    	
    }
    
	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
    

}
