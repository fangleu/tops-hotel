package com.car.check.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.car.util.Constants;
import com.car.util.LinkUtil;
import com.car.util.RefreshAccessToken;
import com.car.weixin.api.AesException;
import com.car.weixin.api.WXBizMsgCrypt;
import com.car.weixin.bean.AccessToken;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-servlet.xml"})
public class CheckTest {
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	@Test
	public void test() throws AesException{
		
		String signature = "83313a5e9bab7e0d100d3067a972b44bf76c5831";
		String timestamp = "1466759963";
		String nonce = "1622302556";
		String echostr = "K4b3w8bOG8GdLNlVdX2/JQnXkn/kZKpOWof9CsScBwtoZzkAd7ymZ4mXYMza6OJGEYyDEcxyqvX7vWmpN1IiNw==";
		
		WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Constants.TOKEN, Constants.ENCODING_AESKEY, Constants.CORP_ID);
		String sEchoStr = wxcpt.VerifyURL(signature, timestamp, nonce, echostr);
		System.out.println("sEchoStr : " + sEchoStr);
		
		AccessToken accessToken = refreshAccessToken.getAccessToken(); 
		
		System.out.println(accessToken.getAccess_token());
		
		
	}

}
