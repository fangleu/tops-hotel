package com.travelzen.tops.hotel.elong.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.travelzen.framework.security.MD5;

public class ElongInterUtil {
	
	private static Logger LOG = LoggerFactory.getLogger(ElongInterUtil.class);
	
	/**
	 * 根据相关信息获得访问elong接口的数字签名
	 * @author muyuansun
	 * @date 2014-1-2 上午11:13:51
	 * @param timestamp
	 * @param appKey
	 * @param secretKey
	 * @param data
	 * @return
	 */
	public static String getSignature(String timestamp, String appKey,String secretKey, String data) {
		return MD5.MD5Encode(timestamp + MD5.MD5Encode((data + appKey)) + secretKey);
	}
	
	/**
	 * 获得当前日期的格林威治时间戳，忽略毫秒
	 * @author muyuansun
	 * @date 2014-1-2 上午11:14:40
	 * @return
	 */
	public static String getTimestamp() {
		Calendar cl = Calendar.getInstance();
		cl.set(Calendar.MILLISECOND, 0);
		long timestamp = cl.getTimeInMillis() / 1000;
		return String.valueOf(timestamp);
	}
	
	public static String getFileContent(String path) {
		String result = null;
		if (path == null || path.length() <= 0) {
			return result;
		}
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		try {
			inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(path);
			if (inputStream == null) {
				return null;
			}
			bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line.trim() + "\n");
			}
			result = sb.toString();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return result;
	}
	
	public static File getFile(String path) {
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
	
	public static String getURLStringByUTF8Encode(String data) {
		String xmString = null;
		String xmlUTF8 = null;
		try {
			xmString = new String(data.getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
		}
		return xmlUTF8;
	}
	
	public static String getURLStringByUTF8Decode(String data) {
		String decodeDate = null;
		try {
			data = new String(data.getBytes("UTF-8"));
			decodeDate = URLDecoder.decode(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
		}
		return decodeDate;
	}

}
