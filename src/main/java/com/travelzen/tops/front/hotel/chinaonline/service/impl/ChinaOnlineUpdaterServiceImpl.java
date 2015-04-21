package com.travelzen.tops.front.hotel.chinaonline.service.impl;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.travelzen.tops.front.hotel.chinaonline.service.IChinaOnlineUpdaterService;

@Service(value="ChinaOnlineUpdater")
public class ChinaOnlineUpdaterServiceImpl implements IChinaOnlineUpdaterService{

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Override
	public String requestToXML(HttpServletRequest request) throws IOException  {
 		StringBuffer xml = new StringBuffer();
 		BufferedReader reader = request.getReader();
 		if(reader == null){
 			LOG.info("畅联推送获取HttpServletRequest数据:{}", reader);
 			return null; 
 		}
 		String line = null;
		while((line = reader.readLine()) != null) {
			xml.append(line);
		}
		return xml.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T XmlToBean(String xml, Class<T> c) throws JAXBException, SAXException, ParserConfigurationException {
		T message = null; 
		JAXBContext context = JAXBContext.newInstance(c);  
		Unmarshaller unmarshaller = context.createUnmarshaller();  
		StringReader reader = new StringReader(xml);  
		SAXParserFactory sax = SAXParserFactory.newInstance();  
		sax.setNamespaceAware(false);   						//忽略xml命名空间
		XMLReader xmlReader = sax.newSAXParser().getXMLReader();
		Source source = new SAXSource(xmlReader, new InputSource(reader));
		message = (T) unmarshaller.unmarshal(source); 
		return message;
	}

	@Override
	public String BeanToXml(Object obj, Class<?> beanClass) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(beanClass);
		    Marshaller marshaller = context.createMarshaller();
		    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    marshaller.marshal(obj, baos);
		    String xmlObj = new String(baos.toByteArray());
		    return xmlObj.replace("standalone=\"yes\"", "");
	}
}
