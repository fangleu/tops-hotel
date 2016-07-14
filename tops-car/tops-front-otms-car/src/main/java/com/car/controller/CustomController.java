package com.car.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.bean.AfterSalesRecord;
import com.car.bean.ConsultingRecord;
import com.car.bean.Custom;
import com.car.bean.Promotion;
import com.car.common.dao.PageResults;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.IConsultingRecordDao;
import com.car.dao.ICustomDao;

import com.car.service.ICustomService;

import com.car.dao.impl.AfterSalesRecordDao;
import com.car.dao.impl.CustomDao;
import com.car.send.message.test.MessageTest;
import com.car.service.IAfterSalesRecordService;
import com.car.service.IConsultingRecordService;
import com.car.service.ICustomService;
import com.car.service.impl.AfterSalesRecordService;
import com.car.service.impl.ConsultingRecordService;
import com.car.service.impl.CustomService;
import com.car.util.Constants;

import com.car.util.CreateMember;
import com.car.util.LinkUtil;
import com.car.util.RefreshAccessToken;
import com.car.weixin.bean.AccessToken;
import com.car.weixin.bean.Member;
import com.car.weixin.bean.Result;
import com.car.weixin.bean.UserInfo;

import net.sf.json.JSONObject;

import java.util.*;


@Controller
@RequestMapping("/user")
public class CustomController {
	
	private Logger log = Logger.getLogger(CustomController.class);
	
	@Autowired
	private ICustomService customService;
	
	@Autowired
	private IAfterSalesRecordDao afterSalesDao;
	
	@Autowired
	private IConsultingRecordService consultingRdService;
	
	@Autowired
	private IConsultingRecordDao consultDao;
	
	@Autowired
	private ICustomDao preSalesDao;
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	private Custom currentCustom;
	/**
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = { "/addUser" })
	@ResponseBody
    public JSONObject addCustom(@RequestParam String phone, @RequestParam String name){
		String accessToken = refreshAccessToken.getAccessToken().getAccess_token();
		Member member = new Member();
		member.setMobile(phone);
		member.setName(name);
		member.setUserid(phone);
		member.setDepartment("25");
		Custom custom = new Custom();
		custom.setPhone(phone);
		custom.setName(name);
		custom.setWechatId(phone);
		custom.setCreateDate(new Date());
		custom.setLevelId(0L);
		if(customService.findCustomByWechatId(phone) == null) {
			customService.addCustom(custom);
			CreateMember.create(member, accessToken);
		}	
		log.info("新潜客加入 姓名,手机号:" + name + " , " + phone);
		JSONObject json = new JSONObject();
		json.accumulate("status", "success");
    	return json;
    	
    }
	
	
	/** 
     * 二次验证 
     *  
     * @param index 
     * @return 
     */  
    @RequestMapping(value = { "/oauth2" })  
    public void Oauth2API(HttpServletRequest request) { 
    	String userId = null;
    	String token = refreshAccessToken.getAccessToken().getAccess_token();
    	userId = LinkUtil.oAuth2GetUserByCode(token, request.getParameter("code")).getUserid(); 
    	try {
			LinkUtil.getAuthsucc(token, userId);
			UserInfo userInfo = LinkUtil.getUserInfo(token, userId);
			Custom custom = customService.findCustomByWechatId(userId);
			custom.setName(userInfo.getName());
			custom.setAvatar(userInfo.getAvatar());
			customService.update(custom);
			System.out.println(userInfo.getAvatar());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }  
  
    @RequestMapping(value="/value" , method = RequestMethod.GET)
    public String getUserDetail(HttpServletRequest request, HttpServletResponse response, Model model) throws MalformedURLException, IOException{
    	        AccessToken accessToken = refreshAccessToken.getAccessToken();  

        String userId = null;
        System.out.println("ｃｏｄｅ　" +request.getParameter("code"));
        if (accessToken != null && accessToken.getAccess_token() != null) {  
        	String token = accessToken.getAccess_token();
        	String code = request.getParameter("code");
    		Result result = LinkUtil.oAuth2GetUserByCode(token, code);  
    		userId = result.getUserid();
	    		
            String headImg = LinkUtil.getUserInfo(token,userId).getAvatar();

        	model.addAttribute("headImg", headImg);   		
        }  
        // find the customer by wechatId
        Custom cust = customService.findCustomByWechatId(userId);
        currentCustom = cust;
        
        Long id = cust.getId();
        String name = cust.getName();
        model.addAttribute("name", name);
        String phone = cust.getPhone();
        model.addAttribute("phone", phone);
        // get the customer's level
        int level = Integer.parseInt(Long.toString(cust.getLevelId()));
        switch(level){
        	case 5: {        		
        		String brand = cust.getBrand();
        		model.addAttribute("brand", brand);
        		String plate = cust.getPlateNumber();
        		model.addAttribute("plate", plate);
        		String carId = cust.getCarIdNumber();
        		model.addAttribute("carId", carId);
        		AfterSalesRecord record = afterSalesDao.getBySQL("select * from after_sales_record c where c.id = ?", AfterSalesRecord.class, id.toString());
        		String models = record.getModels();
        		model.addAttribute("models", models);
        		
        		long type = record.getType();
        		
        		List<Custom> preSales = preSalesDao.getListBySQL("select * from custom c where c.level_id = 6", Custom.class);
        		model.addAttribute("preSales", preSales);
        	
        		break;
        	}
        	default: System.out.println("other");
        
        }
    	return "/view/centreOwners1";
    }
    
    @RequestMapping(value="/appointment" , method = RequestMethod.GET)
    public String getAppointment(HttpServletRequest request, HttpServletResponse response, Model model){			
		int pageNo = 1;
		int pageSize =5;
		Long id = currentCustom.getId();
		//System.out.println("pageNo " + pageNo +"\npageSize "+ pageSize);
		PageResults<ConsultingRecord> list = consultingRdService.getConsultingRecordList(pageNo, pageSize, currentCustom.getId().toString());
		model.addAttribute("conRecord", list);
		return "view/reservationRecord";
    }
    
    @RequestMapping(value="/appointmentPage" , method = RequestMethod.GET)
    public String getAppointmentPage(HttpServletRequest request, HttpServletResponse response, Model model){			
		int pageNo = 1;
		int pageSize =5;
		Long id = currentCustom.getId();
		//System.out.println("pageNo " + pageNo +"\npageSize "+ pageSize);
		PageResults<ConsultingRecord> list = consultingRdService.getConsultingRecordList(pageNo, pageSize, currentCustom.getId().toString());
		model.addAttribute("conRecord", list);
		return "view/reservationRecordPage";
    }
    
    @RequestMapping(value="/query" , method = RequestMethod.GET)
    public String getOnlineQuery(HttpServletRequest request, HttpServletResponse response, Model model){	
//		int pageNo = 1;
//		int pageSize =5;
//		System.out.println("pageNo " + pageNo +"\npageSize "+ pageSize);
//		PageResults<ConsultingRecord> list = consultingRdService.getConsultingRecordList(pageNo, pageSize, currentCustom.getId().toString());
//		System.out.println(list.getResults().size());
//		model.addAttribute("queries", list);
//		
//		
//		ArrayList<Object> names = new ArrayList<Object>();
//		for(ConsultingRecord a : list.getResults()){
//			names.add(a.getSalesStaff());
//		}
//		
//		Object[] obj = names.toArray();
//		StringBuilder 
//		
//		
//		
//		String sql = "select "
//		PageResults<Custom> headResults = customService.getListBySQL("select * from custom c where c.name = ?", Custom.class, ); 
//		
		return "view/onlineQuery";
    }
}
