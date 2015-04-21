package com.travelzen.tops.front.hotel.chinaonline.service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;




public interface IChinaOnlineUpdaterService {
	
	/**
	 * HttpServletRequest转Xml
	 * @author Loufanglei
	 * @throws Exception 
	 * @data 2015-1-5 上午10:47:51 
	 */
	public String requestToXML(HttpServletRequest request) throws Exception;
	
	/**
	 * BookingLimitMessage消息 , Xml转JavaBean
	 * @author Loufanglei
	 * @data 2015-1-5 上午10:44:59 
	 */
	public <T> T XmlToBean(String xml, Class<T> c)  throws JAXBException, SAXException, ParserConfigurationException ;
	
	
	public String BeanToXml(Object obj , Class<?> beanClass) throws JAXBException;
	
	
}
