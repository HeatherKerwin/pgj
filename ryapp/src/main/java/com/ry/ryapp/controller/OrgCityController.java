package com.ry.ryapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.OrgCity;
import com.ry.core.service.OrgCityService;
import com.ry.core.service.PriceService;
import com.ry.util.JacksonUtil;

@Controller
@RequestMapping("app/orgCity")
public class OrgCityController {

	@Resource
	private OrgCityService orgCityService;
	
	@Resource
	private PriceService priceService;
	
	/**
	 * 根据机构ID查询交易城市:只获取交易城市这一个信息
	 * @param orgId 机构主键
	 * @author BKY
	 */
	@RequestMapping("/getByOrgId")
	public String getOrgCityByOrgId(Model model, Integer orgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<OrgCity> orgCityList = orgCityService.getByOrgId(orgId);
			result.put("orgCityList", orgCityList);
			result.put("response", "success");
			result.put("msg", "查询交易城市成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "查询交易城市失败！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取交易城市的所有信息 包含城市的名称
	 * @param orgId 机构ID
	 * @author BKY
	 */
	@RequestMapping("/getCityByOrgId")
	public String getCityByOrgId(Model model, Integer orgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> priceList = priceService.getPtid(orgId, "");
			if (priceList != null && priceList.size() > 0) {
				result.put("priceList", priceList);
			}
			List<Map<String, Object>> orgCityList = orgCityService.getCityByOrgId(orgId);
			result.put("orgCityList", orgCityList);
			result.put("response", "success");
			result.put("msg", "查询交易城市成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "查询交易城市失败！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}
