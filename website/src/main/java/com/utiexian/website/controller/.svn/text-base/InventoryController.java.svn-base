package com.utiexian.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 库存清单
 * @author RHD
 */
@Controller
public class InventoryController {

	/**
	 * 进入库存清单主页面
	 * @return
	 */
	@RequestMapping("inventory")
	public String paperMarket(){
		return "inventory/inventory";
	}
	
	/**
	 *  我的库存清单
	 *  @author MH
	 */
	@RequestMapping("/discount/inventory")
	public String inventory(Model modle){
		return "bisic_message/inventory";
	}
}
