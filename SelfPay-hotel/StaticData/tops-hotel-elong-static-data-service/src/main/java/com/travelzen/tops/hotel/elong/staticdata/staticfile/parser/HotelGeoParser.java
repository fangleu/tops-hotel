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

import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.GeoLocation;
import com.travelzen.tops.hotel.elong.mongo.entity.staticfile.HotelGeo;

public class HotelGeoParser extends DefaultHandler {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass()); 
	
	private List<HotelGeo> hotelGeos = null;
	
	private HotelGeo hotelGeo = null;
	
	private List<GeoLocation> districts = null;
	
	private List<GeoLocation> commericalLocations = null;
	
	private List<GeoLocation> landmarkLocations = null;
	
	private String currentElementFlag =  null;
	
	private GeoLocation geoLocation = null;
	
    public HotelGeoParser(InputStream inputStream, String lang) {
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
    
    public HotelGeoParser(InputStream inputStream) {
        this(inputStream, null);
    }
    
    /**
     * 获得HotelGeo元素基本属性
     * @author muyuansun
     * @date 2014-1-17 下午5:03:37
     * @param elementName
     * @param attributes
     */
    public void gatherHotelGeoStart(String elementName, Attributes attributes){
    	if(elementName == null || !elementName.equals("HotelGeo")){
    		return;
    	}
		hotelGeo = new HotelGeo();
    	// HotelGeo@Country 国家
		if(attributes.getValue("Country") != null){
			hotelGeo.setCountry(attributes.getValue("Country"));
		}
		// HotelGeo@ProvinceName 省份名称
		if(attributes.getValue("ProvinceName") != null){
			hotelGeo.setProvinceName(attributes.getValue("ProvinceName"));
		}
		// HotelGeo@ProvinceId 省份编号
		if(attributes.getValue("ProvinceId") != null){
			hotelGeo.setProvinceId(attributes.getValue("ProvinceId"));
		}
		// HotelGeo@CityName 城市名称
		if(attributes.getValue("CityName") != null){
			hotelGeo.setCityName(attributes.getValue("CityName"));
		}
		// HotelGeo@CityCode 城市名称
		if(attributes.getValue("CityCode") != null){
			hotelGeo.setCityCode(attributes.getValue("CityCode"));
		}
    }
    
    public void gatherGeoLocationStart(String elementName, Attributes attributes){
    	
    	if(elementName == null || !elementName.equals("Location")){
    		return;
    	}
    	
    	geoLocation = new GeoLocation();
    	
    	// Location@Name 位置名称
    	if(attributes.getValue("Name") != null){
    		geoLocation.setName(attributes.getValue("Name"));
    	}
    	
    	// Location@Id 位置编号
		if(attributes.getValue("Id") != null){
			if(attributes.getValue("Id").length() <= 0){
				LOG.warn("[LOCATION_ID_NULL][City_Code = {}][Name = {}]",hotelGeo.getCityCode(),attributes.getValue("Name"));
				return;
			}else{
				geoLocation.setLocationId(String.valueOf(attributes.getValue("Id")));
			}
		}
		
		if(currentElementFlag != null && currentElementFlag.equals("Districts")){
			districts.add(geoLocation);
		}
		if(currentElementFlag != null && currentElementFlag.equals("CommericalLocations")){
			commericalLocations.add(geoLocation);
		}
		if(currentElementFlag != null && currentElementFlag.equals("LandmarkLocations")){
			landmarkLocations.add(geoLocation);
		}
    }
    
    @Override
    public void startElement(String localName, String qName, String elementName, Attributes attributes) throws SAXException {
    	if(elementName.equals("HotelGeoList")){
    		hotelGeos = new ArrayList<>();
    	}
    	//GEO数据-基本信息
    	if(elementName.equals("HotelGeo")){
    		gatherHotelGeoStart(elementName, attributes);
    	}
    	/**
    	 * GEO数据-行政区
    	 */
    	if(elementName.equals("Districts")){
    		districts = new ArrayList<>();
    		currentElementFlag = "Districts";
    	}
    	/**
    	 * GEO数据-商圈
    	 */
    	if(elementName.equals("CommericalLocations")){
    		commericalLocations = new ArrayList<>();
    		currentElementFlag = "CommericalLocations";
    	}
    	/**
    	 * GEO数据-地标
    	 */
    	if(elementName.equals("LandmarkLocations")){
    		landmarkLocations = new ArrayList<>();
    		currentElementFlag = "LandmarkLocations";
    	}
    	/**
    	 * GEO数据-GeoLocation
    	 */
    	gatherGeoLocationStart(elementName, attributes);
    }
    
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
    	/**
    	 * GEO数据-行政区
    	 */
    	if(element.equals("Districts")){
    		hotelGeo.setDistricts(districts);
    	}
    	/**
    	 * GEO数据-商圈
    	 */
    	if(element.equals("CommericalLocations")){
    		hotelGeo.setCommericalLocations(commericalLocations);
    	}
    	/**
    	 * GEO数据-地标
    	 */
    	if(element.equals("LandmarkLocations")){
    		hotelGeo.setLandmarkLocations(landmarkLocations);
    	}
    	if(element.equals("HotelGeo")){
    		hotelGeos.add(hotelGeo);
    	}
    }
    
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
    }
    
	public List<HotelGeo> getHotelGeos() {
		return hotelGeos;
	}
    

}
