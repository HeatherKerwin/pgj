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
@RequestMapping("/bigsmall/")
public class BigSmallController {
	
	/**
	 * 跳转到企业预审页面
	 */
	@RequestMapping("/qiye/yushen")
	public String qiyeyushen(HttpServletRequest req,Model model){
		return "/big_small/qiyeyushen";	
	}
	
	/**
	 * 跳转到企业审核详情页面
	 */
	@RequestMapping("/qiye/xiangqing")
	public String qiyexiangqing(HttpServletRequest req,Model model){
		return "/big_small/qiyexiangqing";	
	}
	
	/**
	 * 跳转到企业修改编辑页面
	 */
	@RequestMapping("/qiye/update")
	public String qiyeupdate(HttpServletRequest req,Model model){
		return "/big_small/qiyeupdate";	
	}
	
	/**
	 * 跳转到大换小页面
	 */
	@RequestMapping("/big")
	public String big(HttpServletRequest req,Model model){
		return "/big_small/big";	
	}
	
	/**
	 * 跳转到小换大页面
	 */
	@RequestMapping("/small")
	public String small(HttpServletRequest req,Model model){
		return "/big_small/small";	
	}
	
	/**
	 * 跳转到大换小订单页面
	 */
	@RequestMapping("/big/list")
	public String bigList(HttpServletRequest req,Model model){
		return "/big_small/big_list";	
	}
	
	/**
	 * 跳转到小换大订单页面
	 */
	@RequestMapping("/small/list")
	public String smallList(HttpServletRequest req,Model model){
		return "/big_small/small_list";	
	}
	
	/**
	 * 跳转到小换大订单页面
	 * @param id 订单的主键
	 */
	@RequestMapping("/{id}/detail")
	public String details(@PathVariable("id")Integer id,Model model){
		model.addAttribute("id", id);
		return detail(model);	
	}
	
	/**
	 * 跳转到小换大订单页面
	 * @param id 订单的主键
	 */
	@RequestMapping("/detail")
	public String detail(Model model){
		return "/big_small/detail";	
	}
	
	/**
	 * 企业预审说明页面
	 */
	@RequestMapping("/explain")
	public String explain(Model model){
		return "/big_small/explain";	
	}
}
