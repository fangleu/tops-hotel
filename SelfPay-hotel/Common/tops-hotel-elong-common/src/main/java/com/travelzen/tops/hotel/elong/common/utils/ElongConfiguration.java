package com.travelzen.tops.hotel.elong.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.tops.hotel.elong.common.exception.CommonException;

/**
 * XML配置文件读取工具类
 * 
 */
public class ElongConfiguration {

	private static Logger LOG = LoggerFactory.getLogger(ElongConfiguration.class);
	
	private Properties PROPERTIES = null;
	
	private static ClassLoader CLASSLOADER = null;
	
	static {
		CLASSLOADER = Thread.currentThread().getContextClassLoader();
		if (CLASSLOADER == null) {
			CLASSLOADER = ElongConfiguration.class.getClassLoader();
		}
	}
	
	public void addResourceByFileSystemPath(String path) throws CommonException{
		if(path == null || path.length() <= 0){
			throw CommonException.instance("请提供资源文件路径");
		}
		StringBuilder builder = null;
		try {
			String classPath = ElongConfiguration.class.getResource("").getPath();
			if(classPath == null || classPath.length() <= 0){
				throw CommonException.instance("没有发现配置文件资源，路径：" + path);
			}
			builder = new StringBuilder();
			/**
			 * 如果路径是以file:，说明是命令行，应用程序环境
			 * 例如：file:/home/muyuansun/travelzen/code/b2b/tz/b2g/b2g-sheduler/target/distributions/b2g-sheduler-1.0.0/lib/elong-newest-interface-common.jar!/com/travelzen/elong/newest/inter/common/utils/
			 */
			if(StringUtils.startsWith(classPath, "file:")){
				String confDirPath = StringUtils.substringBetween(classPath, "file:", "lib");
				if(confDirPath == null || confDirPath.length() <= 0){
					throw CommonException.instance("无法获得配置文件所在的文件系统文件夹路径");
				}
				builder.append(confDirPath);
				builder.append(path);
				if(builder.length() <= 0){
					throw CommonException.instance("无法获得配置文件路径");
				}
				File confFile =  new File(builder.toString());
				if(!confFile.exists() || !confFile.isFile()){
					throw CommonException.instance("配置文件不存在：路径 " + builder.toString());
				}
				InputStream inputStream = new FileInputStream(confFile);
				addProperties(inputStream);
				return;
			}else{
				addResource(path);
			}
		} catch (Exception e) {
			throw CommonException.instance(e.getMessage(),e);
		}
	}
	
	public void addResource(byte[] confContent) throws CommonException{
		if(confContent == null || confContent.length <= 0){
			throw CommonException.instance("请提有效配置文件内容");
		}
		InputStream inputStream = new ByteArrayInputStream(confContent);
		addProperties(inputStream);
	}
	
	public void addResource(String path) throws CommonException{
		if(path == null || path.length() <= 0){
			throw CommonException.instance("请提供资源文件路径");
		}
		InputStream inputStream = ElongConfiguration.class.getClassLoader().getResourceAsStream(path);
		if(inputStream == null){
			throw CommonException.instance("没有发现配置文件资源，路径：" + path);
		}
		addProperties(inputStream);
	}
	
	@SuppressWarnings("rawtypes")
	private void addProperties(InputStream inputStream) throws CommonException{
		if(inputStream == null){
			throw CommonException.instance("输入流信息为NULL，请检查配置文件读取是否正确。");
		}
		SAXReader saxReadr = new SAXReader();
		Document document = null;
		try {
			document = saxReadr.read(inputStream);
			if(document == null){
				throw CommonException.instance("文档模型不存在");
			}
			List properties = document.getRootElement().selectNodes("//property" );
			if(properties == null || properties.size() <= 0){
				return;
			}
			if(PROPERTIES == null){
				PROPERTIES = new Properties();
			}
			Iterator iterator = properties.iterator();
			while (iterator.hasNext()) {
				Element element = (Element) iterator.next();
				String key = element.element("name").getTextTrim();
				String value = element.element("value").getTextTrim();
				LOG.info("[key = {}][value = {}]",key,value);
				PROPERTIES.put(key, value);
			}
		} catch (DocumentException e) {
			throw CommonException.instance(e.getMessage(),e);
		} catch (CommonException e){
			throw CommonException.instance(e.getMessage(),e);
		}
	}
	
	public String get(String key) {
		return (String) PROPERTIES.getProperty(key);
	}
	
	public String getString(String key) {
		return (String) PROPERTIES.getProperty(key);
	}
	
	public String getString(String key,String defaultValue) {
		if(getString(key) == null){
			return defaultValue;
		}
		return getString(key);
	}

	public Integer getInt(String key) {
		if(PROPERTIES.getProperty(key) == null){
			return 0;
		}
		return Integer.parseInt(PROPERTIES.getProperty(key));
	}
	
	public Integer getInt(String key,int defaultValue) {
		if(PROPERTIES.getProperty(key) == null || PROPERTIES.getProperty(key).toString().length() <= 0){
			return defaultValue;
		}
		return Integer.parseInt(PROPERTIES.getProperty(key));
	}
	
	public boolean getBoolean(String key,boolean defaultValue) {
		if(PROPERTIES.getProperty(key) == null || PROPERTIES.getProperty(key).toString().length() <= 0){
			return defaultValue;
		}
		return Boolean.parseBoolean(PROPERTIES.getProperty(key));
	}

}
