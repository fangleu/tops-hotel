package com.car.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.car.weixin.bean.Member;

import net.sf.json.JSONObject;

public class CreateMember {
	
	private static Logger log = Logger.getLogger(CreateMember.class);
	
	public static void create(Member member , String accessToken){
		
		String url = Constants.CREATE_USER_URL + accessToken;
		JSONObject json = JSONObject.fromObject(member);
		URL uRl;
		try {
			uRl = new URL(url);
			HttpsURLConnection http = (HttpsURLConnection) uRl.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
			"application/json;charset=UTF-8");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//
			// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //
			// 读取超时30秒
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(json.toString().getBytes("UTF-8"));// 传入参数
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");
			log.info("CreateMember请求返回结果:" + result);
			
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
