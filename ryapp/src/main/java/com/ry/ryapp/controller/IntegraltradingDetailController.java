package com.ry.ryapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.service.ActionlogService;
import com.ry.core.service.IntegraltradingDetailService;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 积分收支明细
 * @author MH
 */
@Controller
public class IntegraltradingDetailController {
	
	@Resource
	ActionlogService actionlogService;
	
	@Resource
	IntegraltradingDetailService integraltradingDetailService;
	
	/**
	 * 获取到最近一次的积分收支明细的对象
	 * @author MH
	 * @return
	 */
	@RequestMapping("/app/integraltradingdetail/getmodel")
	public String getByModel(IntegraltradingDetail integraltradingDetail,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		String msg = "";
		List<IntegraltradingDetail> listIntegeral = integraltradingDetailService.getList(integraltradingDetail);
		if(listIntegeral.size()>0){
			msg = "获取数据成功";
			result.put("integeral",listIntegeral.get(0));
			result.put("response","success");
			result.put("msg",msg);
		}else{
			result.put("response","failed");
			result.put("msg",msg);
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 	分页获取积分收支明细
	 * @author MH
	 * @param model
	 * @param pageIndex
	 * @param pageSize
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/app/integraltradingdetail/getlist")
	public  String getList(Model model,Integer pageIndex,Integer pageSize,Integer memberId) throws Exception{
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(memberId!=null){
			PageResults<IntegraltradingDetail> page = integraltradingDetailService.getPageList(pageIndex, pageSize, memberId);
			result.put("data",page.getResults());
			result.put("response", "success");
			result.put("msg", "获取成功");
		}else{
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
