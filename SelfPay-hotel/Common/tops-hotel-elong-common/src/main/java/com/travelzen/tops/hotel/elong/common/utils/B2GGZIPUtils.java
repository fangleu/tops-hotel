package com.travelzen.tops.hotel.elong.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class B2GGZIPUtils {
	
	private static Logger LOG = LoggerFactory.getLogger(B2GGZIPUtils.class);
	
	public static String decompressGzipInputStream(InputStream inputStream,String charsetName){
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
	
	public static String convertInputStreamToString(InputStream inputStream,String charsetName){
		String result = null;
		if(inputStream == null){
			return result;
		}
		if(charsetName == null || charsetName.length() <= 0){
			charsetName = "UTF-8";
		}
		BufferedReader bufferedReader = null;
		StringBuffer sb = null;
		String line = null; 
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        	sb = new StringBuffer();  
        	while((line = bufferedReader.readLine()) != null){
        		sb.append(line);
        	}
        	if(sb.length() > 0){
        		result = sb.toString();
        	}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
		return result;
	}
	
}
