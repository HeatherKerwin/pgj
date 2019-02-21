package com.ry.ryapp.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.OrgLimit;
import com.ry.core.entity.RequirementSp;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.RequirementSpService;
import com.ry.util.JacksonUtil;

/**
 * 商票报价app2.3新增
 * @author ZY
 */
@Controller
@RequestMapping("/app/requirementsp/")
public class RequirementSpController {

	@Resource	
	RequirementSpService requirementSpService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	@RequestMapping("save")
	public String save(Model model, RequirementSp requirementSp, Integer price,Integer priceSp,String remarkSp) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(requirementSp.getOrgId(), "");
			if (orgLimit != null ) {
				if(price != null)
				{orgLimit.setPrice(price);}
				if(priceSp != null ){
					orgLimit.setPriceSp(priceSp);
					orgLimit.setRemarkSp(remarkSp);
				}
				orgLimit.setCreateTime(new Date());
				orgLimitService.saveModel(orgLimit);
			} else {
				orgLimit = new OrgLimit();
				Date date = new Date();
				orgLimit.setOrgId(requirementSp.getOrgId());
				orgLimit.setPrice(price);
				orgLimit.setPriceSp(priceSp);
				if(priceSp!=null){
					orgLimit.setRemarkSp(remarkSp);
				}
				orgLimit.setPriceSp(priceSp);
				orgLimit.setCreateTime(date);
				orgLimit.setDay(new SimpleDateFormat("yyyy-MM-dd").format(date));
				orgLimitService.saveModel(orgLimit);
			}
			
			boolean isTime = false;
			RequirementSp req =null;
			if(requirementSp.getId() != null){
			req = requirementSpService.getById(requirementSp.getId());
			}
			if (req != null && req.getMaxDay() == requirementSp.getMaxDay()
					&& Math.abs(req.getMaxPrice() - requirementSp.getMaxPrice()) <= 1e-6
					&& Math.abs(req.getMinPrice() - requirementSp.getMinPrice()) <= 1e-6
					&& req.getMinDay() == requirementSp.getMinDay()) {//如果值没有改变
				result.put("response", "fail");
				result.put("msg", "输入没有发生变化");
			} else {//值发生改变
				
				isTime = isTime(result, requirementSp);// 报价的时间是否满足时间条件
				if (isTime) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					requirementSp.setCreateTime(sdf.parse(sdf.format(new Date())));
					requirementSpService.saveOrUpdate(requirementSp);
					result.put("msg", "保存成功");
					result.put("response", "success");
				} else {
					result.put("response", "error");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "获取失败!");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	
	@RequestMapping("getbyorgid")
	public String save(Model model, Integer orgId){
		Map<String, Object> result = new HashMap<String, Object>();
		List <RequirementSp> lists = requirementSpService.getByOrgId(orgId,null);//time没有时默认为今天
		if(lists != null && lists.size() > 0){
			if(lists.get(0) != null && lists.get(0).getType() == 1){//纸票
				result.put("zhipiao",lists.get(0));
			}else if(lists.get(0) != null && lists.get(0).getType() == 2){//电票
				result.put("dianpiao",lists.get(0));
			}
			if(lists.size() == 2){
			if(lists.get(1) != null && lists.get(1).getType() == 1){
				result.put("zhipiao",lists.get(1));
			}else if(lists.get(1) != null && lists.get(1).getType() == 2){//电票
				result.put("dianpiao",lists.get(1));
			}
			}
			result.put("msg", "获取成功");
			result.put("response", "success");
		}else{
			result.put("msg", "获取失败");
			result.put("response", "success");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 一小时内不能重复报价app2.3新增
	 * @param result	返回的数据
	 * @param requirementSp		商票报价
	 * @return		是否是可以报价的时间
	 */
	public boolean isTime(Map<String, Object> result, RequirementSp requirementSp) {
		boolean isTime = false;
		try {
			Integer orgId = requirementSp.getOrgId();
			Integer type = requirementSp.getType();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Long minute = null;		//一小时内不能重复报价
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();	
			calendar.setTime(date);
			//DateUtil.formart(date, DateUtil.FORMART3);
			String createTime = sdf.format(date);		//当前日期
			RequirementSp list = requirementSpService.getByAll(orgId, type, createTime);
			if (list != null ) {
				Boolean hasType = false;
				if (list.getType() == type) {
						hasType = true;
				}
				if (hasType) {						
					Calendar calendar2 = Calendar.getInstance();
					Date create_time = list.getCreateTime();
					calendar2.setTime(create_time);
					minute = (calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / (1000 * 60);
				}
			}
			if (minute != null && minute >= 0 && minute < 60) {				
				result.put("msg", "距离下次报价还剩" + (60 - minute) + "分钟");
			} else {
				isTime = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isTime;
	}

}
