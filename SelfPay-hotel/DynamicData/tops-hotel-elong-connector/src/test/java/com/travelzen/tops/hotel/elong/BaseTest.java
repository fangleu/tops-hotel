package com.travelzen.tops.hotel.elong;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.UnitilsJUnit4;

public class BaseTest extends UnitilsJUnit4 {
	
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	public void writeToFile(String requestPath,String fileNameSuffix,String format,String responseString,boolean flag) throws IOException{
		//相应内容写入文件
		File requestContentFile = getFile(requestPath);
		String outPutFile =  StringUtils.replace(requestContentFile.getAbsolutePath(), fileNameSuffix + "." + format, "Response" + "." + format);
		if(flag){
			outPutFile = StringUtils.replace(outPutFile, "request", "response/service");
		}else{
			outPutFile = StringUtils.replace(outPutFile, "request", "response");
		}
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
	
    public String doRequest(String url,boolean gzipEnable) {
		try {
			GetMethod getMethod = new GetMethod(url);
			if(gzipEnable){
				getMethod.setRequestHeader("Content-Encoding", "gzip");
			}
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(getMethod);
			Header responseHeader = getMethod.getResponseHeader("Content-Encoding");
			if(responseHeader != null && responseHeader.getValue() != null && responseHeader.getValue().equals("gzip")){
				return decompressGzipInputStream(getMethod.getResponseBodyAsStream(),null);
			}else{
				return getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
    
    public String decompressGzipInputStream(InputStream inputStream,String charsetName){
		String result = null;
		if(inputStream == null){
			return result;
		}
		if(charsetName == null || charsetName.length() <= 0){
			charsetName = "UTF-8";
		}
		GzipCompressorInputStream gzipCompressorInputStream = null;
		BufferedReader bufferedReader = null;
		StringBuffer sb = null;
		String line = null; 
        try {
        	gzipCompressorInputStream = new GzipCompressorInputStream(inputStream);
        	bufferedReader = new BufferedReader(new InputStreamReader(gzipCompressorInputStream, "UTF-8"));
        	sb = new StringBuffer();  
        	while((line = bufferedReader.readLine()) != null){
        		sb.append(line);
        	}
        	if(sb.length() > 0){
        		result = sb.toString();
        	}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		} finally{
			try {
				if(bufferedReader != null){
					bufferedReader.close();
				}
				if(gzipCompressorInputStream != null){
					gzipCompressorInputStream.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(),e);
			}
		}
		return result;
	}

    
    public String doRequestPost(String url) {
		try {
			PostMethod postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			postMethod.addRequestHeader("Content-Type","text/xml;charset=UTF-8");
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(postMethod);
			return postMethod.getResponseBodyAsString();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}
	

}
