package com.car.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.car.weixin.bean.AccessToken;

import net.sf.json.JSONObject;

@Component
public class RefreshAccessToken {
	
private AccessToken accessToken = null;
	
	
	public AccessToken getAccessToken(){
		if(accessToken == null)
			refreshAccessToken();
		System.out.println("使用AccessToken");
		return accessToken;
	}
	
	public void setAccessToken(AccessToken accessToken){
		this.accessToken = accessToken;
	}
	
	@Scheduled(cron = "0 */30 * * * ?")
	private void refreshAccessToken(){
		 HttpsURLConnection urlCon = null; 
		 String url = Constants.ACCESS_TOKEN_URL.replace("{corpid}", Constants.CORP_ID).replace("{corpsecret}", Constants.CORP_SECRET);
		 System.out.println(new Date() + " 定时更新AccessToken URL  " + url);
		 try {  
	           urlCon = (HttpsURLConnection) (new URL(url)).openConnection();  
	           urlCon.setDoInput(true);  
	           urlCon.setDoOutput(true);  
	           urlCon.setRequestMethod("GET");  
	           urlCon.setUseCaches(false);  
	           urlCon.getOutputStream().flush();  
	           urlCon.getOutputStream().close();  
	           BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));  
	           String line;  
	           StringBuilder json = new StringBuilder();
	           while ((line = in.readLine()) != null) {
	               json.append(line);
	           }  
	           accessToken = (AccessToken)JSONObject.toBean(JSONObject.fromObject(json.toString()), AccessToken.class); 
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		
	}

}
