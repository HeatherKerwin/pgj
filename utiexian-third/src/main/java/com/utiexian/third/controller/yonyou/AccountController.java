package com.utiexian.third.controller.yonyou;

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
@RequestMapping("/yonyou/account")
public class AccountController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	/**
     * 根据用户主键获取账户信息
     * @author WKX
     * @param memberId 用户主键
     */
    @PostMapping("/by/member")
    public String getByMemberId(Integer memberId,Model model){
    	if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
    	
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("memberId",memberId);
		String res = restTemplate.postForObject("http://utiexian-server/account/by/member",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
    
    /**
     * 根据用户主键获取账户信息（内含：企业和机构角色下的占用金额）押金管理用
     * @author WKX
     * @param memberId 用户主键
     * @return [account账户余额、disc_deposit企业端占用金额、dist_deposit机构端占用金额]
     */
    @PostMapping("/info/by/member")
    public String getAccountInfoByMemberId(Integer memberId,Model model){
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("memberId",memberId);
		String res = restTemplate.postForObject("http://utiexian-server/account/info/by/member",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
}