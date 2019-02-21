package com.utiexian.website.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/requirementsp/")
public class RequirementSpController {

	@Resource	
	RequirementSpService requirementSpService;
	
	@Resource
	OrgLimitService orgLimitService;
	
	/**
	 * 跳转至银票报价
	 * @author ZY
	 * @param request
	 * 2016年11月1日下午6:11:15
	 */
	@RequestMapping("change")
	public String tiaozhuan(HttpServletRequest request){
		return "price/price";
	}
	
	/**
	 * 获取今日的实时额度
	 * @param orgId 机构主键
	 * @param model 
	 * @author ZY
	 */
	@RequestMapping("getorglimit")
	public String getOrgLimit(Model model, Integer orgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
			result.put("orgLimit", orgLimit);
			result.put("response", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "实时查看今日过往报价时出错！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 修改或保存今日过往报价的最新报价额度
	 * @author ZY
	 * @param model
	 * @param orgId 机构ID
	 * @param priceSp 新的商票报价
	 * @since 2016年10月31日 上午10:45:21
	 */
	@RequestMapping("update/priceandtime")
	public String updatePriceAndTime(Model model, Integer orgId,Integer priceSp,String remarkSp) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
			if (orgLimit != null ) {
				if(priceSp != null ){
				orgLimit.setPriceSp(priceSp);
				orgLimit.setRemarkSp(remarkSp);
				}
				orgLimit.setCreateTime(new Date());
			} else {
				orgLimit = new OrgLimit();
				Date date = new Date();
				orgLimit.setOrgId(orgId);
				orgLimit.setPriceSp(priceSp);
				orgLimit.setCreateTime(date);
				orgLimit.setRemarkSp(remarkSp);
				orgLimit.setDay(new SimpleDateFormat("yyyy-MM-dd").format(date));
			}
			orgLimitService.saveModel(orgLimit);
			result.put("response", "success");
			result.put("msg", "保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "查看今日过往报价时出错！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 保存商票报价
	 * @author ZY
	 * @param model
	 * @param requirementSp
	 * 2016年11月1日下午2:11:28
	 */
	@RequestMapping("update/pricesp")
	public String save(Model model, RequirementSp requirementSp) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			boolean isTime = false;
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
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "获取失败!");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取商票报价
	 * @author ZY
	 * @param model
	 * @param orgId
	 * 2016年10月31日下午7:23:55
	 */
	@RequestMapping("getbyorgid")
	public String get(Model model, Integer orgId){
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
