package com.car.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.bean.Custom;
import com.car.bean.Promotion;
import com.car.bean.PromotionRecord;
import com.car.common.dao.PageResults;
import com.car.service.ICustomService;
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
	
	@Autowired
	private ICustomService customService;
	
	
	@RequestMapping(value = {"/addPromotion"})
	public String addPromotion(Promotion promotion) {
		
		promotionService.save(promotion);
		
		return null;
		
	}
	
	@RequestMapping(value = {"/getPromotion"})
	public String getPromotion(int pageNo, int pageSize, Model model) {
		Long type = 0L;
//		pageNo = 1;
//		pageSize =5;
		System.out.println("pageNo " + pageNo +"\npageSize "+ pageSize);
		PageResults<Promotion> list = promotionService.getPromotionList(pageNo, pageSize, type);
		System.out.println(list.getResults().size());
		model.addAttribute("promotions", list);
		return "view/promotions";
		
	}
	
	@RequestMapping(value = {"/getPromotionPage"})
	public String getPromotionPage(int pageNo, int pageSize, Model model) {
		Long type = 0L;
//		pageNo = 1;
//		pageSize =5;
		System.out.println("pageNo " + pageNo +"\npageSize "+ pageSize);
		PageResults<Promotion> list = promotionService.getPromotionList(pageNo, pageSize, type);
		System.out.println(list.getResults().size());
		model.addAttribute("promotions", list);
		return "view/promotionsPage";
		
	}
	
	@RequestMapping(value = {"/getPromotionDetail"})
	public String getPromotionDetail(HttpServletRequest request, HttpServletResponse response, Long id) {
		
		System.out.println("Promotion code  " + request.getParameter("code"));
		Promotion promotion = promotionService.get(id);
		AccessToken accessToken =  refreshAccessToken.getAccessToken(); ;
		System.out.println("accessToken  " + accessToken.getAccess_token());
		Result result = LinkUtil.oAuth2GetUserByCode(accessToken.getAccess_token(), request.getParameter("code"));  
		System.out.println("userID " + result.getUserid());
		Custom custom = customService.findCustomByWechatId(result.getUserid());
		System.out.println("customID" + custom.getId());
		PromotionRecord promotionRecord = new PromotionRecord();
		promotionRecord.setCustomId(custom.getId());
		promotionRecord.setPromotionId(promotion.getId());
		promotionRecord.setType(promotion.getType());
		promotionRecord.setCreateDate(new Date());
		promotionRdService.save(promotionRecord);
		
		System.out.println(promotion);
		
		return "view/Test";
		
	}
	
	

}
