package com.car.check.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.car.util.Constants;
import com.car.weixin.bean.AccessToken;

import net.sf.json.JSONObject;

public class AccessTokenTest {

	@Test
	public void test() {
		
		String url = Constants.ACCESS_TOKEN_URL; 
		HttpsURLConnection urlCon = null;  
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
                System.out.println(line);  
                json.append(line);
            }  
            
            
            AccessToken accessToken = (AccessToken)JSONObject.toBean(JSONObject.fromObject(json), AccessToken.class); 
            
            System.out.println(accessToken.getAccess_token());
            System.out.println(accessToken.getErrcode());
    		System.out.println(accessToken.getErrmsg());
            System.out.println(accessToken.getExpires_in());
            
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

}
