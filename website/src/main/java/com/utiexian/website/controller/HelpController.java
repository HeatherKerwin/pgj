package com.utiexian.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KHC
 * 帮助中心
 */
@Controller
public class HelpController {
	
	/**
	 * 帮助中心常见问题
	 * @author KHC
	 * @param request
	 * @since 2016年11月11日 下午1:25:30
	 */
	@RequestMapping("/help")
	public String list(Integer num,HttpServletRequest request){
		request.getSession().setAttribute("num", num);
		if(num==null || num==1){
			return "/help/login";
		}else if(num==2){
			return "/help/renzheng";
		}else if(num==3){
			return "/help/xunjia";
		}else if(num==4){
			return "/help/tiexian";
		}else if(num==5){
			return "/help/shoupiao";
		}else if(num==6){
			return "/help/manage";
		}else if(num==7){
			return "/help/tools";
		}else if(num==8){
			return "/help/other";
		}else if(num==9){
			return "/help/jingdong";
		}
		return null;
	}
	
	/**
	 * 帮助中心票据学
	 * @author KHC
	 * @param request
	 * @since 2016年11月11日 下午1:25:30
	 */
	@RequestMapping("/xueyuan")
	public String xueyuan(Integer num,HttpServletRequest request){
		if(num==null || num==1){
			return "/piaojuxueyuan/bns_zhiyin";//企业指引
		}else if(num==2){
			return "/piaojuxueyuan/org_zhiyin";//机构指引
		}else if(num==3){
			return "/piaojuxueyuan/manage_zhiyin";//票据管理使用指引
		}else if(num==4){
			return "/piaojuxueyuan/chupiao_discount";//贴现下单
		}else if(num==5){
			return "/piaojuxueyuan/chupiao_view";//查看下单
		}else if(num==6){
			return "/piaojuxueyuan/shoupiao_offer";//如何报价
		}else if(num==7){
			return "/piaojuxueyuan/shoupiao_receive";//如何接单
		}
		
		return null;
	}
	
	/**
	 * 用户协议
	 * @author KHC
	 * @param request
	 * @since 2016年11月18日 下午1:47:15
	 */
	@RequestMapping("/xieyi")
	public String xieyi(HttpServletRequest request){
		return "agreements/yonghuxieyi";
	}
	
	/**
	 * 联系客服
	 * @author KHC
	 * @param request
	 * @since 2016年11月18日 下午1:47:15
	 */
	@RequestMapping("/contact")
	public String contact(HttpServletRequest request){
		return "/help/contact";
	}
}
