package com.travelzen.tops.hotel.elong.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4;

public class BaseTest extends UnitilsJUnit4 {
	
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	public void writeToFile(String requestPath,String fileNameSuffix,String format,String responseString) throws IOException{
		//相应内容写入文件
		File requestContentFile = getFile(requestPath);
		String outPutFile =  StringUtils.replace(requestContentFile.getAbsolutePath(), fileNameSuffix + "." + format, "Response" + "." + format);
		outPutFile = StringUtils.replace(outPutFile, "request", "response");
		outPutFile = StringUtils.replace(outPutFile, "bin", "src/test/resources");
		
		File file = new File(outPutFile);
		if(!file.getParentFile().exists()){
			file.mkdirs();
		}
		if(file.exists()){
			file.delete();
			file.createNewFile();
		}
		FileUtils.writeStringToFile(new File(outPutFile), responseString);
	}
	
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
	

}
