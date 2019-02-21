package com.utiexian.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 银行大小票
 * @author MH
 */
@Controller
@RequestMapping("/bank/sizeticket")
public class BankSizeTicketController {
	
	/**
	 * 跳转到后台审核
	 */
	@RequestMapping("/backstage/{index}")
	public String examine(@PathVariable("index")String index,HttpServletRequest req,Model model){
		if("enterprise".equals(index)){
			return "/bank_backstage/enterprise";
		}else if("big".equals(index)){
			return "/bank_backstage/big";
		}else if("small".equals(index)){
			return "/bank_backstage/small";
		}else{
			return "/bank_backstage/enterprise";	
		}
	}
}
