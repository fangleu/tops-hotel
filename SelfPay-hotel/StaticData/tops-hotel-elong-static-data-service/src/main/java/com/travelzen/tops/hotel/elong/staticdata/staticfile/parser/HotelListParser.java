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

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelIndex;

/**
 * 酒店列表及酒店更新列表文件解析器
 * @author muyuansun
 * @date 2014-1-6 下午7:28:30
 */
public class HotelListParser extends DefaultHandler {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass()); 
	
	private List<HotelIndex> hotelIndexs = null;
	
	private HotelIndex hotelIndex = null;
	
    public HotelListParser(InputStream inputStream, String lang) {
    	hotelIndexs = new ArrayList<>();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(inputStream, this);
        } catch (ParserConfigurationException e) {
            LOG.info( e.getMessage(),e);
        } catch (SAXException e) {
        	LOG.info( e.getMessage(),e);
        } catch (IOException e) {
        	LOG.info( e.getMessage(),e);
        }
    }
    
    public HotelListParser(InputStream inputStream) {
        this(inputStream, null);
    }
    
    @Override
    public void startElement(String localName, String qName, String elementName, Attributes attributes) throws SAXException {
    	if(elementName.equals("Hotel")){
    		hotelIndex = new HotelIndex();
    		// HotelId 酒店ID
    		if(attributes.getValue("HotelId") != null){
    			hotelIndex.setHotelId(attributes.getValue("HotelId"));
    		}
    		// UpdatedTime 更新时间
    		if(attributes.getValue("UpdatedTime") != null){
    			hotelIndex.setUpdatedTime(attributes.getValue("UpdatedTime"));
    		}
    		// Modification 更新内容  0-酒店、1-房型、2-图片、3-产品（产品已修改使用动态接口中的状态增量）
    		if(attributes.getValue("modification") != null){
    			hotelIndex.setModification(attributes.getValue("modification"));
    		}
    		// Products 包含产品
    		if(attributes.getValue("Products") != null){
    			hotelIndex.setProducts(attributes.getValue("Products"));
    		}
    		// Status 可用状态 0--可用，1--不可用
    		if(attributes.getValue("Status") != null){
    			hotelIndex.setStatus(Integer.valueOf(attributes.getValue("Status")));
    		}
    	}
    }
    
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
    	if(element.equals("Hotel")){
    		hotelIndexs.add(hotelIndex);
    	}
    }
    
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
    }
    
	public List<HotelIndex> getHotelIndex() {
		return hotelIndexs;
	}

}
