package com.car.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.car.util.Constants;
import com.car.util.LinkUtil;
import com.car.util.RefreshAccessToken;
import com.car.weixin.api.AesException;
import com.car.weixin.api.WXBizMsgCrypt;
import com.car.weixin.bean.AccessToken;
import com.car.weixin.bean.Result;

@Controller
@RequestMapping("/check")
public class ValidateController {
	
	private Logger log = Logger.getLogger(ValidateController.class);
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	@RequestMapping(value="/value" , method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Model model){
		
		System.out.println("authCode " + request.getParameter("auth_code"));
		System.out.println("Spring MVC code  " + request.getParameter("code"));
		AccessToken accessToken =  refreshAccessToken.getAccessToken(); ;
		System.out.println("accessToken  " + accessToken.getAccess_token());
		Result result = LinkUtil.oAuth2GetUserByCode(accessToken.getAccess_token(), request.getParameter("code"));  
		model.addAttribute("userId", result.getUserid());
		System.out.println("userID " + result.getUserid());
		LinkUtil.userIdConverOpenId(accessToken.getAccess_token(), request.getParameter("code"), result.getUserid());
		
		return "/view/Test.jsp";
		
	}
	
	@RequestMapping(value="/validate" , method = RequestMethod.GET)
	public void Checkbox(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
        String signature = request.getParameter("msg_signature");  
        String timestamp = request.getParameter("timestamp");  
        String nonce = request.getParameter("nonce");  
        String echostr = request.getParameter("echostr");  
        System.out.println("signature : " + signature);
        System.out.println("timestamp : " + timestamp);
        System.out.println("nonce : " + nonce);
        System.out.println("echostr : " + echostr);
        
        try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Constants.TOKEN, Constants.ENCODING_AESKEY, Constants.CORP_ID);
			String sEchoStr = wxcpt.VerifyURL(signature, timestamp, nonce, echostr);
			System.out.println("sEchoStr : " + sEchoStr);
			PrintWriter writer = response.getWriter();
			writer.print(sEchoStr);
			
		} catch (AesException e) {
			e.printStackTrace();
		}
        
		
	}
	
	
	
	
	
}
