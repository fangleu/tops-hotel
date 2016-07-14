package com.car.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

import com.car.weixin.bean.AccessToken;
import com.car.weixin.bean.Result;
import com.car.weixin.bean.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LinkUtil {
	
	public static Result oAuth2GetUserByCode(String token, String code){
		
		HttpsURLConnection urlCon = null; 
		Result result = null;
	 	String url = Constants.GET_USER_URL.replace("{ACCESS_TOKEN}", token).replace("{CODE}", code);
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
           JSONObject jsonObject = LinkUtil.transObject(JSONObject.fromObject(json.toString()));
           result = (Result)JSONObject.toBean(jsonObject , Result.class); 
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
			
		return result;
		
	} 
	
	
public static String userIdConverOpenId(String token, String code , String userId){
	StringBuilder json = new StringBuilder();
		HttpsURLConnection urlCon = null; 
		Result result = null;
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token="+token;
        System.out.println("url---" + url);
		try {  
           urlCon = (HttpsURLConnection) (new URL(url)).openConnection();  
           urlCon.setDoInput(true);  
           urlCon.setDoOutput(true);  
           urlCon.setRequestMethod("POST");
           
           JSONObject obj = new JSONObject();
           obj.put("userid", userId);
           
           urlCon.setUseCaches(false); 
           
           OutputStream out = urlCon.getOutputStream();//向对象输出流写出数据，这些数据将存到内存缓冲区中          
           out.write(obj.toString().getBytes());
           
           urlCon.getOutputStream().flush();  
           urlCon.getOutputStream().close();  
           BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));  
           String line;  
           
           while ((line = in.readLine()) != null) {
               json.append(line);
           }  
           System.out.println("userIdConverOpenId : " + json);
           JSONObject jsonObject = LinkUtil.transObject(JSONObject.fromObject(json.toString()));
           result = (Result)JSONObject.toBean(jsonObject , Result.class);
		
		
			} catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
			
		
		return result.getOpenid();
		
	}
	
	
	public static JSONObject transObject(JSONObject o1){
        JSONObject o2=new JSONObject();
		Iterator it = o1.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                Object object = o1.get(key);
                if(object.getClass().toString().endsWith("String")){
                    o2.accumulate(key.toLowerCase(), object);
                }else if(object.getClass().toString().endsWith("JSONObject")){
                    o2.accumulate(key.toLowerCase(), LinkUtil.transObject((JSONObject)object));
                }else if(object.getClass().toString().endsWith("JSONArray")){
                    o2.accumulate(key.toLowerCase(), LinkUtil.transArray(o1.getJSONArray(key)));
                }
            }
            return o2;
    }
	
	public static JSONArray transArray(JSONArray o1){
        JSONArray o2 = new JSONArray();
        for (int i = 0; i < o1.size(); i++) {
            Object jArray=o1.getJSONObject(i);
            if(jArray.getClass().toString().endsWith("JSONObject")){
                o2.add(LinkUtil.transObject((JSONObject)jArray));
            }else if(jArray.getClass().toString().endsWith("JSONArray")){
                o2.add(LinkUtil.transArray((JSONArray)jArray));
            }
        }
        return o2;
    }
	
	public static String getHeadImg(String token, String code, String userId) throws MalformedURLException, IOException{
		HttpsURLConnection urlCon = null; 
		UserInfo result = null;
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USER_ID";
		url = url.replace("ACCESS_TOKEN", token);

		//String openId = userIdConverOpenId(token, code, userId);
	
//		url = url.replace("OPENID", openId);

		url = url.replace("USER_ID", userId);
		
		System.out.println(url);
		//now url has the proper url
		
		//open connection, use GET method
        urlCon = (HttpsURLConnection) (new URL(url)).openConnection();  
        urlCon.setDoInput(true);  
        urlCon.setDoOutput(true);  
        urlCon.setRequestMethod("GET");
        urlCon.setUseCaches(false);  
        urlCon.getOutputStream().flush();  
        urlCon.getOutputStream().close();
        
        //read in result
        BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));  
        String line;  
        StringBuilder json = new StringBuilder();
        while ((line = in.readLine()) != null) {
            json.append(line);
        }  
        
        System.out.println(json);
        JSONObject jsonObject = JSONObject.fromObject(json.toString());
        result = (UserInfo)JSONObject.toBean(jsonObject , UserInfo.class);
	
        
        
        return result.getAvatar();
	}
	
	public static UserInfo getUserInfo(String token, String userId) throws MalformedURLException, IOException{
		HttpsURLConnection urlCon = null; 
		UserInfo result = null;
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USER_ID";
		url = url.replace("ACCESS_TOKEN", token);

		url = url.replace("USER_ID", userId);
		
		System.out.println(url);
		//now url has the proper url
		
		//open connection, use GET method
        urlCon = (HttpsURLConnection) (new URL(url)).openConnection();  
        urlCon.setDoInput(true);  
        urlCon.setDoOutput(true);  
        urlCon.setRequestMethod("GET");
        urlCon.setUseCaches(false);  
        urlCon.getOutputStream().flush();  
        urlCon.getOutputStream().close();
        
        //read in result
        BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));  
        String line;  
        StringBuilder json = new StringBuilder();
        while ((line = in.readLine()) != null) {
            json.append(line);
        }  
        
        System.out.println(json);
        JSONObject jsonObject = JSONObject.fromObject(json.toString());
        result = (UserInfo)JSONObject.toBean(jsonObject , UserInfo.class);
	
        
        
        return result;
	}
	

}
