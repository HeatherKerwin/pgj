package com.utiexian.third.controller.yonyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.utiexian.utils.BaseController;

/**
 * 用友云票据
 * @author RHD
 */
@Controller
@RequestMapping(value = "/yonyou/page")
public class YonyouController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * 首页（index无需登录、main需要登录）
	 */
	@RequestMapping(value={"/index","/main"})
	public String index() {
    	return "yonyou/index";
    }
	
	/**
	 * 银票出票
	 */
	@RequestMapping("/discountrecord")//银票
	public String discountrecord() {
    	return "yonyou/discount/discountrecord";
    }

	/**
	 * 商票出票
	 */
	@RequestMapping("/discountrecordsp")//商票
	public String discountrecordSp() {
		return "yonyou/discount/discountrecord_sp";
	}
	
	/**
	 * 询价
	 */
	@RequestMapping("/inquiry")
	public String inquiry() {
    	return "yonyou/inquiry";
    }
	
	/**
	 * 计算器
	 */
	@RequestMapping("/calculator")
	public String calulator() {
    	return "yonyou/calculator";
    }

	/**
	 * 开户管理-上传证件
	 */
	@RequestMapping("/authentication")
	public String authentication_upload() {
    	return "yonyou/authentication/authentication_upload";
    }
	/**
	 * 开户管理-新开户
	 */
	@RequestMapping("/authentication_open")
	public String authentication_open() {
    	return "yonyou/authentication/authentication_open";
    }
	/**
	 * 开户管理-账户绑定
	 */
	@RequestMapping("/authentication_bind")
	public String authentication_bind() {
    	return "yonyou/authentication/authentication_bind";
    }
	/**
	 * 开户管理-审核
	 */
	@RequestMapping("/authentication_review")
	public String authentication_review() {
    	return "yonyou/authentication/authentication_review";
    }
	/**
	 * 开户管理-开户成功
	 */
	@RequestMapping("/authentication_success")
	public String authentication_success() {
    	return "yonyou/authentication/authentication_success";
    }
	/**
	 * 开户管理-小额限定
	 */
	@RequestMapping("/authentication_appraisal")
	public String authentication_appraisal() {
    	return "yonyou/authentication/authentication_appraisal";
    }
	
	
	/**
	 * 公司主页-我的钱包
	 */
	@RequestMapping("/account")
	public String acount() {
    	return "yonyou/account/account";
	}
	/**
	 * 我的钱包-交易记录
	 */
	@RequestMapping("/accountrecord")
	public String accountRecord() {
		return "yonyou/account/account_record";
	}
	
	/**
	 * 订单中心-银票
	 */
	@RequestMapping("/order/list")//银票
	public String yporder() {
    	return "yonyou/order/order_list";
    }
	/**
	 * 订单中心-商票
	 */
	@RequestMapping("/ordersp/list")//商票
	public String orderSp() {
    	return "yonyou/order/order_sp_list";
    }
	/**
	 * 订单中心-商票详情
	 */
	@RequestMapping("/order/detail")
	public String orderDetail() {
    	return "yonyou/order/order_detail";
    }
}