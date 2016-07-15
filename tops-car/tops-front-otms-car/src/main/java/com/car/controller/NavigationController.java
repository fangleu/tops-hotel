package com.car.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.bean.Navigation;
import com.car.service.INavigationService;

@Controller
@RequestMapping("/navigation")
public class NavigationController {
	
	private Logger log = Logger.getLogger(NavigationController.class);
	
	@Autowired
	private INavigationService navigationService;
	
	@RequestMapping(value = { "/detail" }) 
	public String getNavigation(Model model){
		
		Navigation navigation = navigationService.get(0L);
		model.addAttribute("navigation", navigation);
		
		
		return "/view/storeNavigation";
		
	}
	
	

}
