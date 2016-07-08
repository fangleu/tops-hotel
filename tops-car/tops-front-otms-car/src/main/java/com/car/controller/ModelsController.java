package com.car.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.bean.Models;
import com.car.common.dao.PageResults;

import com.car.service.IModelsService;
import com.car.util.LinkUtil;
import com.car.util.RefreshAccessToken;
import com.car.weixin.bean.AccessToken;
import com.car.weixin.bean.Result;

@Controller
@RequestMapping("/models")
public class ModelsController {
	
	private Logger log = Logger.getLogger(ModelsController.class);
	
	@Autowired
	private IModelsService modelsService;
	

	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	
	@RequestMapping(value = {"/addModels"})
	public String addModels(Models model) {
		
		modelsService.save(model);
		
		return null;
		
	}
	
	@RequestMapping(value = {"/getModels"})
	public String getModels(int pageNo, int pageSize) {
		
		PageResults<Models> list = modelsService.getModelsList(pageNo, pageSize);
		System.out.println(list.getResults().size());
		return null;
		
	}
	
	@RequestMapping(value = {"/getModelsDetail"})
	public String getModelsDetail(HttpServletRequest request, HttpServletResponse response, Long id) {
		
		System.out.println("Models code  " + request.getParameter("code"));
		Models models = modelsService.get(id);
		AccessToken accessToken =  refreshAccessToken.getAccessToken(); 
		System.out.println("accessToken  " + accessToken.getAccess_token());
		Result result = LinkUtil.oAuth2GetUserByCode(accessToken.getAccess_token(), request.getParameter("code"));  
		System.out.println("userID " + result.getUserid());
		
		System.out.println(models);
		
		return "/view/Test.jsp";
		
	}
	
	

}
