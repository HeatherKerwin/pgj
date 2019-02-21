package com.utiexian.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 票据市场
 * @author RHD
 */
@Controller
public class PaperMarketController {

	/**
	 * 进入票据市场主页面
	 * @return
	 */
	@RequestMapping("paperMarket")
	public String paperMarket(){
		return "paperMarket/paperMarket";
	}
	
	/**
	 * 进入票据市场的详情页面
	 * @return
	 */
	@RequestMapping("paperMarketDetail")
	public String paperMarketDetail(){
		return "paperMarket/paperMarketDetail";
	}

}
