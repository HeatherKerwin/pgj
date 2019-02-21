package com.utiexian.website.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Appimage;
import com.ry.core.service.PicmanageService;
import com.ry.util.JacksonUtil;

/**
 * @author MH
 *
 */
@Controller
public class PcImgController {
	@Resource
	PicmanageService picmanageService;

	/**
	 * @author MH
	 * @param code 类型
	 * @param model
	 * @return
	 */
	@RequestMapping("pc/img/calendar")
	public String loadCalendar(String code,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Appimage> appimages = picmanageService.getXzPiclist(code);
		if(appimages!=null){
			result.put("success", "success");
			result.put("calendar", appimages);
		}else{
			result.put("success", "fail");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
