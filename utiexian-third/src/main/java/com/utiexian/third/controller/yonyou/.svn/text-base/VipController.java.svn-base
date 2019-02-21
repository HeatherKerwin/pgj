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
@RequestMapping("/yonyou/vip")
public class VipController extends BaseController{
	
	@Autowired
    RestTemplate restTemplate;
	
    /**
     * 显示会员列表（月、季度、年）
     * @author MH
     * @param vipType 类型：企业0、机构1
     * @param model
     */
    @PostMapping("/list")
    public String getByList(Integer vipType,Model model){
    	if(vipType==null)return this.DATE_FAILED("操作失败！请联系客服", model);
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		
		params.set("vipType", vipType);
		String res = restTemplate.postForObject("http://utiexian-server/vip/list",params, String.class);
		return this.DATE_SUCCESS(res, model);
	}
}