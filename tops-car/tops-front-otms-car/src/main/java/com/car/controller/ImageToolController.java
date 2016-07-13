package com.car.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.car.bean.Custom;
import com.car.util.Constants;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/component")
public class ImageToolController {
	
	
	@RequestMapping(value="/test" , method = RequestMethod.GET)
	public String  Test(HttpServletRequest request, HttpServletResponse response, Model model){
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		String a = "aaa";
		System.out.println("path " + path + "\n" + "basePath " + basePath);
		model.addAttribute("path", a);
//		return "/view/centreOwners.html";
		Custom cust = new Custom();
		
		cust.setName("sfafa");
		model.addAttribute("cust", cust);
		
		return "/view/fremarkerTest";
	}
	
	
	  @RequestMapping(value="/common/uploadImg", method=RequestMethod.POST)
	  @ResponseBody
	  public String uploadImg(@RequestParam(value="img")MultipartFile img, HttpServletResponse response, HttpServletRequest request){
	      JSONObject result = new JSONObject();
	      System.out.println("test");
	      boolean flag = true;
	      try {
	          flag = upload(img, result , request);
	      } catch (Exception e) {
	          result.put("mess", "调用失败");
	         flag = false;
	         e.printStackTrace();
	     }
	     result.put("flag", flag);
	     
	     response.setContentType("text/html;charset=UTF-8");
	     //解决跨域名访问问题
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     
	     return result.toString();
	 }
	
	
	 public boolean upload(MultipartFile file, JSONObject params, HttpServletRequest request) throws Exception{
	 //过滤合法的文件类型
	     String fileName = file.getOriginalFilename();
	     String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
	     String allowSuffixs = "gif,jpg,jpeg,bmp,png,ico";
	     if(allowSuffixs.indexOf(suffix) == -1){
	         params.put("resultStr", "not support the file type!");
	         return false;
	     }
	     
	     String newFileName = UUID.randomUUID().toString() + "." + suffix;
	     String imageUrl = Constants.DOMAIN_NAME + newFileName;
	     file.transferTo(new File(Constants.IMAGE_ADREESS + newFileName));
	     
	     System.out.println("图片名字: " + newFileName);
	     System.out.println("图片访问url " + imageUrl);
	     
	     params.put("resultStr", imageUrl);
	     
	     return true;
	 }

}
