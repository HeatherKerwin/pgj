package com.utiexian.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 开户（京东）
 * @author MH
 */
@Controller
@RequestMapping("/jd")
public class CibJdController {

	/**
	 * 用户申请开户的跳转（营业执照）
	 * @return
	 */
	@RequestMapping("/apply/open/account")
	public String applyOpenAccount(){
		return "cib_jd/license";
	}
	
	/**
	 * 用户申请开户的跳转（法人）
	 * @return
	 */
	@RequestMapping("/apply/open/person")
	public String applyOpenPerson(){
		return "cib_jd/person";
	}
	
	/**
	 * 用户申请开户的跳转（卡）
	 * @return
	 */
	@RequestMapping("/apply/open/card")
	public String applyOpenCard(){
		return "cib_jd/jd_card";
	}
	
	/**
	 * 用户申请开户审查
	 * @return
	 */
	@RequestMapping("/examine")
	public String examine(){
		return "cib_jd/examine";
	}
	
	/**
	 * 用户申请开户审查（修改）
	 * @return
	 */
	@RequestMapping("/update")
	public String update(){
		return "cib_jd/update";
	}
	
	/**
	 * 用户申请开户小额打款
	 * @return
	 */
	@RequestMapping("/small/money")
	public String money(){
		return "cib_jd/jd_small_money";
	}
	
	/**
	 * 用户申请开户成功
	 * @return
	 */
	@RequestMapping("/success")
	public String success(){
		return "cib_jd/jd_success";
	}
	
	/**
	 * 用户绑卡
	 * @return
	 */
	@RequestMapping("/bind/card")
	public String bindCard(){
		return "cib_jd/jd_bind_card";
	}
	
	/**
	 * 用户绑卡审核
	 * @return
	 */
	@RequestMapping("/bind/examine")
	public String bindExamine(){
		return "cib_jd/jd_bind_examine";
	}
	
	/**
	 * 用户绑卡小额打款
	 * @return
	 */
	@RequestMapping("/bind/small/money")
	public String BindMoney(){
		return "cib_jd/jd_bind_small_money";
	}
}
