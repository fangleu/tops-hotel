package com.car.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.bean.Promotion;
import com.car.bean.PromotionRecord;
import com.car.common.dao.PageResults;
import com.car.service.IPromotionRdService;
import com.car.service.IPromotionService;
import com.car.util.LinkUtil;
import com.car.util.RefreshAccessToken;
import com.car.weixin.bean.AccessToken;
import com.car.weixin.bean.Result;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
	
	private Logger log = Logger.getLogger(PromotionController.class);
	
	@Autowired
	private IPromotionService promotionService;
	
	@Autowired
	private IPromotionRdService promotionRdService;
	
	@Autowired
	private RefreshAccessToken refreshAccessToken;
	
	
	@RequestMapping(value = {"/addPromotion"})
	public String addPromotion(Promotion promotion) {
		
		promotionService.save(promotion);
		
		return null;
		
	}
	
	@RequestMapping(value = {"/getPromotion"})
	public String getPromotion(int pageNo, int pageSize) {
		
		PageResults<Promotion> list = promotionService.getPromotionList(pageNo, pageSize);
		System.out.println(list.getResults().size());
		return null;
		
	}
	
	@RequestMapping(value = {"/getPromotionDetail"})
	public String getPromotionDetail(HttpServletRequest request, HttpServletResponse response, Long id) {
		
		System.out.println("Promotion code  " + request.getParameter("code"));
		Promotion promotion = promotionService.get(id);
		AccessToken accessToken =  refreshAccessToken.getAccessToken(); ;
		System.out.println("accessToken  " + accessToken.getAccess_token());
		Result result = LinkUtil.oAuth2GetUserByCode(accessToken.getAccess_token(), request.getParameter("code"));  
		System.out.println("userID " + result.getUserid());
		
		PromotionRecord promotionRecord = new PromotionRecord();
		promotionRdService.save(promotionRecord);
		
		System.out.println(promotion);
		
		return "/view/Test.jsp";
		
	}
	
	

}
