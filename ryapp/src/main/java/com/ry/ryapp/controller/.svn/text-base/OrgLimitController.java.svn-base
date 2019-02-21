package com.ry.ryapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.OrgLimit;
import com.ry.core.entity.Price;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.OrgLimitService;
import com.ry.core.service.PriceService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;

@Controller
@RequestMapping("app/orgLimit/")
public class OrgLimitController {

	@Resource
	OrgLimitService orgLimitService;
	
	@Resource
	PriceService priceService;
	
	@Resource
	private DistributeOrderService distributeOrderService;
	
	private static final Logger logger = Logger.getLogger(OrgLimitController.class);
	
	/**
	 * 查看今日过往报价额度
	 * @param orgId 机构ID
	 * @author BKY
	 */
	@RequestMapping("get/priceByOrgId")
	public String getByOrgId(Model model,Integer orgId, Integer cityId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
			result.put("orgLimit", orgLimit);
			String createTime = DateUtil.formart(new Date(), DateUtil.FORMART3);
			if (cityId == null) {
				cityId = Constant.DEFAULT_CITY_ID;
			}
			List<Price> priceList = priceService.getByOrgId(orgId, createTime, null, cityId);
			if (priceList != null && priceList.size() > 0) {
				result.put("priceList", priceList);
			}
			result.put("response", "success");
		} catch (Exception e) {
			logger.info("查看今日过往报价时出错！", e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "查看今日过往报价时出错！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取今日的实时额度
	 * @param orgId 机构主键
	 * @author BKY
	 */
	@RequestMapping("getOrgLimit")
	public String getOrgLimit(Model model, Integer orgId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//查询机构今天的报价额度
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
//			Integer money = orgLimit.getPrice();
//			String startDate = DateUtil.formart(DateUtil.getStartDate(new Date()), DateUtil.FORMART);
//			String endDate = DateUtil.formart(DateUtil.addDays(1), DateUtil.FORMART);
//			//获取今日已完成的订单
//			List<Map<String,Object>>  orderList = distributeOrderService.getByOrgIdAndTime(orgId, startDate, endDate, 3);
//			if (orderList != null && orderList.size() > 0) {
//				for (Map<String,Object> order : orderList) {
//					String allmoney = order.get("allmoney").toString();
//					float money1 = Float.valueOf(allmoney);
//					money -= (int) money1;
//				}
//			}
//			orgLimit.setPrice(money);
			result.put("orgLimit", orgLimit);
			result.put("response", "success");
		} catch (Exception e) {
			logger.info("实时查看今日过往报价时出错！", e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "实时查看今日过往报价时出错！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 修改或保存今日过往报价的最新报价额度(app2.3新增商票)
	 * @author ZY
	 * @param model
	 * @param orgId 机构ID
	 * @param price 新的报价
	 * @param priceSp 新的商票报价
	 * @since 2016年8月26日 上午10:45:21
	 */
	@RequestMapping("update/priceAndTime")
	public String updatePriceAndTime(Model model, Integer orgId, Integer price,Integer priceSp,String remarkYp) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			OrgLimit orgLimit = orgLimitService.getByOrgIdAndDay(orgId, "");
			if (orgLimit != null ) {
				if(price != null){
					orgLimit.setPrice(price);
					orgLimit.setRemarkYp(remarkYp);
				}
				if(priceSp != null ){
				orgLimit.setPriceSp(priceSp);}
				orgLimit.setCreateTime(new Date());
			} else {
				orgLimit = new OrgLimit();
				Date date = new Date();
				orgLimit.setOrgId(orgId);
				orgLimit.setPrice(price);
				orgLimit.setPriceSp(priceSp);
				orgLimit.setCreateTime(date);
				orgLimit.setRemarkYp(remarkYp);
				orgLimit.setDay(new SimpleDateFormat("yyyy-MM-dd").format(date));
			}
			orgLimitService.saveModel(orgLimit);
			result.put("response", "success");
			result.put("msg", "保存成功");
		} catch (Exception e) {
			logger.info("修改今日过往报价的最新报价！", e);
			e.printStackTrace();
			result.put("response", "error");
			result.put("msg", "查看今日过往报价时出错！");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
}
