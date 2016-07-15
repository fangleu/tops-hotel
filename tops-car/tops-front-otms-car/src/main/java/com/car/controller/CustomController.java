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
import com.car.bean.Models;
import com.car.bean.ModelsRecord;
import com.car.bean.Promotion;
import com.car.common.dao.PageResults;
import com.car.dao.IAfterSalesRecordDao;
import com.car.dao.IConsultingRecordDao;
import com.car.dao.ICustomDao;
import com.car.dao.IModelsDao;
import com.car.dao.IModelsRecordDao;
import com.car.service.ICustomService;
import com.car.service.IModelsRdService;
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
import com.car.util.SendWeChatMessage;
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
	private IModelsRdService modelsRdService;
	
	@Autowired
	private IConsultingRecordDao consultDao;
	
	@Autowired
	private ICustomDao preSalesDao;
	
	@Autowired
	private IModelsDao modelsDao;
	
	@Autowired
	private IModelsRecordDao mdelsRecordDao;
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	@Autowired
	private SendWeChatMessage sendWeChatMessage;
	
	private Custom currentCustom;
	
	/**
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = { "/addUser" }, produces="application/json;charset=UTF-8")
    public String addCustom(@RequestParam String phone, @RequestParam String name){
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
    	return json.toString();
    	
    }
	
	
	/** 
     * 二次验证 
     *  
     * @param index 
     * @return 
     */  
    @RequestMapping(value = { "/oauth2" })  
    public void Oauth2API(HttpServletRequest request, HttpServletResponse response) { 
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
    	System.out.println("URL " + request.getRequestURL());
    	 request.setAttribute("jsonObject", "你已成功关注企业号");
    	 sendWeChatMessage.sendWeChatMsg("text", userId, "0", "", "测试senMsg", "", "", "", "", "", "0");
    	
    }  
  
    @RequestMapping(value="/detail" , method = RequestMethod.GET)
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
        
        if(level == 5){
        	//车主, 级别5
        	
    		String brand = cust.getBrand();
    		model.addAttribute("brand", brand);
    		String plate = cust.getPlateNumber();
    		model.addAttribute("plate", plate);
    		String carId = cust.getCarIdNumber();
    		model.addAttribute("carId", carId);
    		AfterSalesRecord record = afterSalesDao.getBySQL("select * from after_sales_record c where c.id = ?", AfterSalesRecord.class, id.toString());
    		String models = record.getModels();
    		model.addAttribute("models", models);
    		  		
    		List<Custom> preSales = preSalesDao.getListBySQL("select * from custom c where c.level_id = 6", Custom.class);
    		model.addAttribute("preSales", preSales);        	
        }
        else if (level < 5) {
        	//客户，售前，级别 0 ~ 4
        	String sql = "select * from models_record c where c.custom_id = ?";
        	sql = sql.replace("?", currentCustom.getId().toString());
        	List<ModelsRecord> modelRecord = mdelsRecordDao.getListBySQL(sql, ModelsRecord.class);
        	
        	//获取已关注车型的models_id
        	StringBuilder modelId = new StringBuilder();
        	modelId.append("(");        	
        	for(ModelsRecord a : modelRecord){
        		modelId.append(a.getModelsId().toString() + ",");
        	}
        	modelId.deleteCharAt(modelId.length() - 1);
        	modelId.append(")");
        	System.out.println(modelId);
        	
        	//用modelId获取车的列表        	
        	sql = "select * from models c where c.id in " + modelId.toString();
        	List<Models> modelsList = modelsDao.getListBySQL(sql, Models.class);
        	model.addAttribute("modelsList", modelsList);
        	
        	return "/view/personalCenter";
		}
        
        
    	return "/view/centreOwners1";
    }
    
    @RequestMapping(value="/appointment" , method = RequestMethod.GET)
    public String getAppointment(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, Model model){			
		PageResults<ConsultingRecord> list = consultingRdService.getConsultingRecordList(pageNo, pageSize, currentCustom.getId().toString());	
		model.addAttribute("conRecord", list);
		return "view/reservationRecord";
    }
    
    @RequestMapping(value="/appointmentPage" , method = RequestMethod.GET)
    public String getAppointmentPage(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, Model model){			
		PageResults<ConsultingRecord> list = consultingRdService.getConsultingRecordList(pageNo, pageSize, currentCustom.getId().toString());
		model.addAttribute("conRecord", list);
		return "view/reservationRecordPage";
    }
    
    @RequestMapping(value="/query" , method = RequestMethod.GET)
    public String getOnlineQuery(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, Model model){	
		PageResults<ConsultingRecord> list = consultingRdService.getConsultingRecordList(pageNo, pageSize, currentCustom.getId().toString());	
		model.addAttribute("conRecord", list);
		return "view/onlineQuery";
    }
    
    @RequestMapping(value="/queryPage" , method = RequestMethod.GET)
    public String getOnlineQueryPage(HttpServletRequest request, HttpServletResponse response, int pageNo, int pageSize, Model model){	
		PageResults<ConsultingRecord> list = consultingRdService.getConsultingRecordList(pageNo, pageSize, currentCustom.getId().toString());	
		model.addAttribute("conRecord", list);
		return "view/onlineQueryPage";
    }
}
