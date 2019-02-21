package com.utiexian.third.controller.yonyou;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.utiexian.utils.BaseController;

import freemarker.template.Configuration;

@Controller
@RequestMapping("/yonyou/orginfo")
public class OrgInfoController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
	@Resource
	private Configuration configuration;
	
	/**
     * 根据用户主键获取账户信息（兴业数金账户正常）
     * @author WKX
     * @param company 公司名称
     * @param removeMemberId 排除指定机构（下单指定用户不能选择自己）
     */
    @PostMapping("/search")
    public String getByMemberId(Integer pageIndex,Integer pageSize,String company,Integer removeMemberId,Model model){
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("pageIndex",pageIndex);
		params.set("pageSize",pageSize);
		params.set("company",company);
		params.set("removeMemberId",removeMemberId);
		String res = restTemplate.postForObject("http://utiexian-server/orginfo/search",params, String.class);
		model.addAttribute("data", res);
		model.addAttribute("sign", "DATA");
		return "ajax";
	}
    
	/**
	 * 根据用户主键和角色获取认证信息
	 * @author MH
	 * @param memberId 用户主键
	 * @param type 角色：企业0、机构1
	 */
	@PostMapping(value="/rz")
	public String rz(Integer memberId,Integer type,Model model){
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("memberId",memberId);
		params.set("type",type);
		String res = restTemplate.postForObject("http://utiexian-server/orginfo/rz",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
}