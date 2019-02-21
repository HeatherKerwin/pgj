package com.utiexian.third.controller.yonyou;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.utiexian.utils.BaseController;

@Controller
@RequestMapping("/yonyou/accountlog")
public class AccountLogController extends BaseController{
	
/*	@Autowired
    private RedisUtil redisUtil;*/
	
	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * 提现（生成提现记录）
	 * @author WKX
	 * @param code 验证码
	 * @param memberId 用户主键
	 * @param cardBank 提现账户（银行）
	 * @param cardUserName 提现账户（用户名）
	 * @param cardNumber 提现账户（卡号）
	 * @param money 提现金额
	 * @param mobile 登录用户手机号（验证短信用）
	 * @param model
	 */
	@PostMapping(value="/save/tx")
    public String tx(String code,Integer memberId,String cardBank,String cardUserName,String cardNumber,String money,String mobile,Model model,HttpServletRequest request){
		String smscode = request.getSession().getAttribute("code").toString();//参照IndexController公共发送短信的参数
		if(StringUtils.isBlank(code) || !code.equals(smscode)){
			return this.DATE_FAILED("验证码输入有误！", model);
		}
		if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		if(StringUtils.isBlank(cardBank))return this.DATE_FAILED("请选择所属银行！", model);
		if(StringUtils.isBlank(cardUserName))return this.DATE_FAILED("请输入用户名！", model);
		if(StringUtils.isBlank(cardNumber))return this.DATE_FAILED("请输入卡号！", model);
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("memberId",memberId);
		params.set("cardBank",cardBank);
		params.set("cardUserName",cardUserName);
		params.set("cardNumber",cardNumber);
		params.set("money",money);
		String res = restTemplate.postForObject("http://utiexian-server/accountlog/save/tx",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
	
	/**
	 * 分页显示账户金额流转记录
	 * @author WKX
	 * @param pageIndex 第几页
	 * @param pageSize 一页多少条
	 * @param memberId 用户主键
	 * @param type 收支类型[CZ充值、TX提现、USE使用]
	 * @param model
	 */
	@PostMapping(value="/page")
    public String page(Integer pageIndex,Integer pageSize,Integer memberId,String type,Model model){
		if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("pageIndex",pageIndex);
		params.set("pageSize",pageSize);
		params.set("memberId",memberId);
		params.set("type",type);
		String res = restTemplate.postForObject("http://utiexian-server/accountlog/page",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
}