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
@RequestMapping("/yonyou/vipmember")
public class VipMemberController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
    /**
     * 购买会员
     * @author MH
     * @param memberId 用户主键
     * @param vipType 类型：企业0、机构1
     * @param vipId VIP主键
     * @param model
     */
    @PostMapping("/save")
    public String save(Integer memberId,Integer vipType,Integer vipId,Model model){
    	if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
    	if(vipType==null)return this.DATE_FAILED("操作失败！请联系客服", model);
    	if(vipId==null)return this.DATE_FAILED("操作失败！请联系客服", model);
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		params.set("memberId",memberId);
		params.set("vipType",vipType);
		params.set("vipId",vipId);
		String res = restTemplate.postForObject("http://utiexian-server/vipmember/save",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
    
    /**
     * 根据用户主键获取会员
     * @author MH
     * @param memberId 用户主键
     * @param vipType 类型：企业0、机构1
     * @param model
     */
    @PostMapping("/get/by/memberid")
    public String save(Integer memberId,Integer vipType,Model model){
    	if(memberId==null)return this.DATE_FAILED("获取用户信息失败！请重新登录", model);
    	if(vipType==null)return this.DATE_FAILED("操作失败！请联系客服", model);
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		params.set("memberId",memberId);
		params.set("vipType",vipType);
		String res = restTemplate.postForObject("http://utiexian-server/vipmember/get/by/memberid",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
}