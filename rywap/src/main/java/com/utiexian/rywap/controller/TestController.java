package com.utiexian.rywap.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转首页
 * @author WKX
 */
@Controller
@RequestMapping("")
public class TestController {
	
	/**
	 * 跳转等到首页（有用）
	 * @param request
	 */
	@RequestMapping("")
	public String index(Model model){
		return "redirect:/wap/index";
	}
	
	/**
	 * 支付嵌套外壳（里面在嵌套支付的页面）
	 * @author WKX
	 * @param model
	 * @param url 支付具体请求
	 */
	@RequestMapping("/wap/pay")
	public String pay(Model model,String url){
		if(StringUtils.isNotBlank(url)){
			model.addAttribute("url", url);
			return "pay-iframe";
		}else{
			return "redirect:/wap/index";
		}
	}
}