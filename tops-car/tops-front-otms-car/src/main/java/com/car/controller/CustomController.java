package com.car.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.car.bean.Custom;
import com.car.service.ICustomService;
import com.car.util.Constants;
import com.car.util.CreateMember;
import com.car.util.LinkUtil;
import com.car.util.RefreshAccessToken;
import com.car.weixin.bean.AccessToken;
import com.car.weixin.bean.Member;
import com.car.weixin.bean.Result;


@Controller
@RequestMapping("/user")
public class CustomController {
	
	private Logger log = Logger.getLogger(CustomController.class);
	
	@Autowired
	private ICustomService customService;
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	
	/**
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = { "/addUser" })
    public boolean addCustom(@RequestParam String phone, @RequestParam String name){
		String accessToken = refreshAccessToken.getAccessToken().getAccess_token();
		Member member = new Member();
		member.setMobile(phone);
		member.setName(name);
		member.setUserid(phone);
		member.setDepartment("21");
		Custom custom = new Custom();
		custom.setPhone(phone);
		custom.setName(name);
		custom.setWechatId(phone);
		custom.setCreateDate(new Date());
		customService.addCustom(custom);
		CreateMember.create(member, accessToken);
		log.info("新潜客加入 姓名,手机号:" + name + " , " + phone);
    	return true;
    }
	
	
	/** 
     * 构造参数并将请求重定向到微信API获取登录信息 
     *  
     * @param index 
     * @return 
     */  
    @RequestMapping(value = { "/oauth2" })  
    public String Oauth2API(HttpServletRequest request) { 
    	
    	String CropId = Constants.CORP_ID;  
    	System.out.println("CropId : " + CropId); 
    	String resultUrl = request.getRequestURL().toString().replace("/oauth2", "/oauth2url.do");
        String redirectUrl = "";  
        
        System.out.println("resultUrl : " + request.getParameter("resultUrl"));
        if (resultUrl != null) {  
            String reqUrl = "fangleu.ngrok.cc/tops-front-otms-car/user";
     
            String backUrl ="http://" + reqUrl + "/oauth2url.do?oauth2url=" + resultUrl;  
            System.out.println("backUrl="+backUrl);  
            redirectUrl = oAuth2Url(CropId, backUrl);  
        }  
        
        return "redirect:" + redirectUrl;
    }  
    
    /** 
     * 根据code获取Userid后跳转到需要带用户信息的最终页面 
     *  
     * @param request 
     * @param code 
     *            获取微信重定向到自己设置的URL中code参数 
     * @param oauth2url 
     *            跳转到最终页面的地址 
     * @return 
     */  
    @RequestMapping(value = { "/oauth2url.do" })  
    public String Oauth2MeUrl(HttpServletRequest request, @RequestParam String code, @RequestParam String oauth2url) {  
        AccessToken accessToken = refreshAccessToken.getAccessToken();  
        HttpSession session = request.getSession(); 
        String Userid = null;
        if (accessToken != null && accessToken.getAccess_token() != null) {  
            Userid = getMemberGuidByCode(accessToken.getAccess_token(), code);  
            if (Userid != null) {  
                session.setAttribute("UserId", Userid);  
                System.out.println("UserId : " + Userid);
            }  
        }  
        System.out.println("oauth2url  " + oauth2url);
        System.out.println("1redirectUrl  " + oauth2url);
        return "redirect:" + oauth2url;  
    }  
  
    /** 
     * 构造带员工身份信息的URL 
     *  
     * @param corpid 
     *            企业id 
     * @param redirect_uri 
     *            授权后重定向的回调链接地址，请使用urlencode对链接进行处理 
     * @param state 
     *            重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值 
     * @return 
     */  
    private String oAuth2Url(String corpid, String redirect_uri) {  
        try {  
            redirect_uri = java.net.URLEncoder.encode(redirect_uri, "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        String oauth2Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + corpid + "&redirect_uri=" + redirect_uri  
                + "&response_type=code&scope=snsapi_base&state=sunlight#wechat_redirect";  
        System.out.println("oauth2Url=" + oauth2Url);  
        return oauth2Url;  
    }  
  
    /** 
     * 调用接口获取用户信息 
     *  
     * @param token 
     * @param code 
     * @param agentId 
     * @return 
     * @throws SQLException 
     * @throws RemoteException 
     */  
    public String getMemberGuidByCode(String token, String code) {  
        System.out.println("code==" + code + "\ntoken=" + token);  
        Result result = LinkUtil.oAuth2GetUserByCode(token, code);  
        System.out.println("result=" + result);  
        
                return result.getUserid();  
    }  

}
