package com.car.util;

public class Constants {  
    
	
//    public static final int AGENTID = 1;
	
	//潜客部门ID
	public static final String DEPARTMENT_ID = "21";
	
    // 车主
    public static final String TOKEN = "YocGLus4y9bCIFz6YgCH";
    public static final String ENCODING_AESKEY = "gcJrHYLOJe8UPUSfu1cfCCNrqSya2fD3qCXGcVRYNzS"; 
    
    //管理员 
    public static final String CORP_ID = "wxfdfd1b46c655aacf";  
    public static final String CORP_SECRET = "SQAWxSYvbmkOBUf_e67T1TtU0qRfUXN5ZlA6uaCUGXZT_5UwE5AtaAHoKelMpDB7";
     
    // 获取access_token URL
    public static final String ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}";
    
    public static final String GET_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token={ACCESS_TOKEN}&code={CODE}";
    
    public static final String CREATE_USER_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=";
    
    // 发消息URL
    public final static String CREATE_SESSION_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
    
}  
