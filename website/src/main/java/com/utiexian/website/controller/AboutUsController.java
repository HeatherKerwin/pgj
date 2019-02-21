package com.utiexian.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 关于我们
 * @author MH
 */
@Controller
public class AboutUsController {

	/**
	 * 跳转到关于我们下面的票管家介绍
	 * @return
	 */
	@RequestMapping("aboutus/choice/suggest")
	public String choiceAboutusSuggest(){
		return "aboutus/about1";
	}
	
	/**
	 * 跳转到关于我们下面的票管家CEO
	 * @return
	 */
	@RequestMapping("aboutus/choice/ceo")
	public String choiceAboutusCEO(){
		return "aboutus/about2";
	}
	
	/**
	 * 跳转到关于我们下面的合作伙伴
	 * @return
	 */
	@RequestMapping("aboutus/choice/partner")
	public String choiceAboutusPartner(){
		return "aboutus/about3";
	}
	
	/**
	 * 跳转到关于我们下面的联系我们
	 * @return
	 */
	@RequestMapping("aboutus/choice/contact")
	public String choiceAboutusContact(){
		return "aboutus/about4";
	}
	
	/**
	 * 加入我们
	 * @author ZY
	 * @since 2016年11月18日 下午1:25:30
	 */
	@RequestMapping("/join/app")
	public String join(){
		return "/join/app";
	}
	
	/**
	 * 加入我们
	 * @author ZY
	 * @since 2016年11月18日 下午1:25:30
	 */
	@RequestMapping("/join/dianhua")
	public String join1(){
		return "/join/dianhua";
	}/**
	 * 加入我们
	 * @author ZY
	 * @since 2016年11月18日 下午1:25:30
	 */
	@RequestMapping("/join/xiaoshou")
	public String join2(){
		return "/join/xiaoshou";
	}/**
	 * 加入我们
	 * @author ZY
	 */
	@RequestMapping("/join/shixi")
	public String join3(){
		return "/join/shixi";
	}
	
	/**
	 * 跳转到到票据分析（app，不需要登录）
	 * @return
	 */
	@RequestMapping("aboutus/statistical")
	public String statistical( Integer memberId,Model model){
		model.addAttribute("memberId", memberId);
		return "/app/statistical";
	}
}
