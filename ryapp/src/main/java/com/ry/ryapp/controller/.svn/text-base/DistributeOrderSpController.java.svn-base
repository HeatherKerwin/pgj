package com.ry.ryapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.DistributeOrderTaskService;
import com.ry.core.service.MemberService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

/**
 * 商票机构订单
 * @author KHC
 */
@Controller
@RequestMapping("/app/distributeordersp/")
public class DistributeOrderSpController {
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DistributeOrderTaskService distributeOrderTaskService;
	
	@Resource
	MemberService memberService;
	
	/**
	 * 企业商票报价提醒
	 * @author KHC
	 * @param orgId 机构主键
	 * @param model
	 * @since 2016年8月17日 下午7:42:38
	 */
	@RequestMapping("index")
	public String index(Integer memberId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = distributeOrderSpService.getList(memberId);//待确认
		if(list!=null && list.size()>0){
			result.put("response", "success");
			result.put("data", list.get(0));
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 机构获取派单的列表
	 * @author ZY
	 * @param orgId 机构主键
	 * @param state 订单状态，如果是null就是全部订单，其他的就是分别的订单类型
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年8月29日 下午7:15:12
	 */
	@RequestMapping("list")
	public String list(DistributeOrderSpForm form,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form.getOrgId() != null ){
			PageResults<Map<String,Object>> page = distributeOrderSpService.getPageList(pageIndex, pageSize, form);
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
	
	/**
	 * 获取机构派单的详情
	 * @author ZY
	 * @param dtboId 派单的主键 id
	 * @since 2016年8月29日 下午4:14:04
	 */
	@RequestMapping("get")
	public String get(Integer dtboId,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(dtboId==null)throw new Exception("数据异常");
			Map<String,Object> dtbo = distributeOrderSpService.getInfoByDtboId(dtboId);
			Object time = dtbo.get("createTime");//接单生成日期
			if(time!=null){
				int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
				dtbo.put("timer", 1800-timer);
			}
			Object lon_a = dtbo.get("lon");
			Object lat_a = dtbo.get("lat");
			Object lon_b = dtbo.get("longitude");
			Object lat_b = dtbo.get("latitude");
			if(lon_a!=null && lat_a!=null && lon_b!=null && lat_b!=null){//距离
				Double distance = DistanceUtil.getDistance(Float.valueOf(lat_a.toString()), Float.valueOf(lon_a.toString()),Float.valueOf(lat_b.toString()),Float.valueOf(lon_b.toString()));
				dtbo.put("distance",distance);
			}
			result.put("data",dtbo);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 接受订单
	 * @param form
	 * @param memberId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("accept")
	public String accept(DistributeOrderSp form, Integer memberId,Float version, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		try {
			DistributeOrderSp dist = distributeOrderSpService.getById(form.getId());
			if(dist==null || dist.getState()!=1)throw new Exception("数据已过期");
			
			DiscountrecordSp disc = discountrecordSpService.getById(dist.getDcrdSpId());
			
			dist.setTodoorPrice(form.getTodoorPrice());
			dist.setTodoorTime(form.getTodoorTime());
			dist.setLongitude(form.getLongitude());
			dist.setLatitude(form.getLatitude());
			
			dist.setPrice(form.getPrice());
			dist.setPrice1(form.getPrice1());
			dist.setPrice2(form.getPrice2());
			dist.setWay(form.getWay());
			dist.setNeedStuff(form.getNeedStuff());
			dist.setTxlx(getTxlx(disc,dist));//计算贴现利息
			
			distributeOrderSpService.updateModel(dist);
			
			Map<String,Object> member = memberService.getInfoByOrgId(dist.getOrgId());
			Map<String,String> param = new HashMap<String, String>();
			param.put("allmoney", disc.getAllmoney()!=null?disc.getAllmoney().toString():"");
			param.put("endtime",DateUtil.formart(disc.getEndtime(), DateUtil.FORMART3));
			if(member!=null && member.get("mobile")!=null){
				SendMessagesUtil.sendMessage(member.get("mobile").toString(), "SMS_14495061", param);
			}
			
			result.put("data",dist);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	

	/**
	 * 根据贴现订单获取-贴现利息
	 * @author WKX
	 * @param disc 贴现订单
	 * @param jxts 计息天数
	 * @since 2016年9月16日 下午6:11:35
	 */
	private String getTxlx(DiscountrecordSp disc,DistributeOrderSp dist){
		String txlx = "--";
		if(dist!=null && StringUtils.isNotBlank(dist.getJxts()) && !"--".equals(dist.getJxts())){
			Integer jxts = Integer.valueOf(dist.getJxts());
			Integer way = dist.getWay();
			String price = dist.getPrice();
			String price1 = dist.getPrice1();
			String price2 = dist.getPrice2();
			Double allmoney = disc.getAllmoney();
			Double temp = 0D;
			if(way==0){//月息
				if(StringUtils.isNotBlank(price) && !"--".equals(price.trim())){
					Double r = Double.valueOf(price);
					temp = (allmoney*10000)*jxts*((r/30)/1000);
					if(StringUtils.isNotBlank(price1) && !"--".equals(price1.trim())){
						temp += (allmoney/10)*Float.valueOf(price1);
					}
				}
			}else if(way==2){//年息
				if(StringUtils.isNotBlank(price) && !"--".equals(price.trim())){
					Double r = Double.valueOf(price);
					temp = (allmoney*10000)*jxts*((r/360)/100);
					if(StringUtils.isNotBlank(price1) && !"--".equals(price1.trim())){
						temp += (allmoney/10)*Float.valueOf(price1);
					}
				}
			}else if(way==1){//每十万
				if(StringUtils.isNotBlank(price2) && !"--".equals(price2.trim())){
					Double r2 = Double.valueOf(price2);
					temp = (allmoney/10)*r2;
				}
			}
			if(temp!=0D)txlx = temp.toString();
		}
		return txlx;
	}
	
	/**
	 * 拒绝接单
	 * @param form
	 * @param memberId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("reject")
	public String reject(DistributeOrderSp form, Integer memberId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrderSp order = distributeOrderSpService.getById(form.getId());
			if(order==null || order.getState()!=1)throw new Exception("数据已过期");
			
			order.setState(-2);
			
			distributeOrderSpService.updateModel(order);
			
			result.put("data",order);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 机构取消（拒绝）订单
	 * @param dtboId 接单主键
	 * @param cancel 取消原因
	 * @param reason 原因文本
	 * @param currentState 当前状态
	 * @param memberId 用户主键
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("cancel")
	public String cancle(Integer dtboId,Integer cancel,String reason,Integer currentState,Integer memberId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrderSp dist = distributeOrderSpService.getById(dtboId);
			if(dist.getState()!=currentState)throw new Exception("数据已过期");
			DiscountrecordSp disc = discountrecordSpService.getById(dist.getDcrdSpId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");
			
			dist.setCancel(cancel);
			dist.setCancelCause(reason);
			dist.setState(-2);//机构端取消订单
			distributeOrderSpService.updateModel(dist);
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(dist.getId());
			task.setCreateTime(new Date());
			task.setRemarks(reason);//取消理由
			task.setState(-2);
			task.setOperatorId(memberId);
			task.setOperatorType(OperatorType.MEMBER);
			task.setType(1);//商票
			distributeOrderTaskService.saveModel(task);
			result.put("response", "success");
			result.put("msg", "操作成功");
			if(currentState==1){//待接单
				alert = "亲，该笔订单已成功拒绝！";
			}else{
				alert = "亲，该笔订单已成功取消！";
			}
			ok = "好，我知道了";
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		result.put("alert", alert);
		result.put("ok", ok);
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}