package com.ry.ryapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.UserSign;
import com.ry.core.service.ActivityService;
import com.ry.core.service.UserSignService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;

@Controller
public class UserSignController {

	@Resource
	UserSignService userSignService;
	
	@Resource
	ActivityService activityService;
	
	/**
	 * 根据用户Id判断是否签到，同时存入缓存
	 * @author MH
	 * @param memberId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/app/usersign/getmodel")
	public String getByModel(Integer memberId,Model model) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		if(memberId == null) throw  new Exception("用户Id不能不存在");
		UserSign userSign = userSignService.getByModel(memberId, DateUtil.formart(new Date(), DateUtil.FORMART3));
	
		if(userSign != null){
			result.put("data", userSign);
			result.put("response", "success");
			result.put("msg", "已签到");
		}else{
			result.put("response", "error");
			result.put("msg", "未签到");
		}
		
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 用户签到
	 * @author MH
	 * @param usersign
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/app/usersign/save")
	public String saveModel(UserSign usersign,Model model) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		if(usersign == null || usersign.getMemberId() == null) throw new Exception("签到失败");
		usersign.setSignTime(new Date());
		userSignService.saveModel(usersign);
		activityService.timingIntegral(usersign.getMemberId(), 10, "签到", null);
		result.put("response", "success");
		result.put("msg", "已签到");
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
