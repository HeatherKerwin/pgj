package com.ry.ryapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.form.DistributeOrderPlForm;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.DistributeOrderTaskService;
import com.ry.core.service.MemberService;
import com.ry.core.util.Constant;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

/**
 * 批量机构订单
 * @author KHC
 */
@Controller
@RequestMapping("/app/distributeorderpl/")
public class DistributeOrderPlController {
	
	@Resource
	DistributeOrderPlService distributeOrderPlService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	@Resource
	DistributeOrderTaskService distributeOrderTaskService;
	
	@Resource
	MemberService memberService;
	
	/**
	 * 根据机构获取对应的批量订单
	 * @author KHC
	 * @param orgId
	 * @param model
	 * @since 2016年8月18日 上午11:24:34
	 */
	@RequestMapping("list")
	public String list(DistributeOrderPlForm form,Integer pageIndex,Integer pageSize,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		if(form.getOrgId()!=null){
			PageResults<Map<String, Object>> page = distributeOrderPlService.getPageList(pageIndex, pageSize, form);
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
	 * 获取批量订单详情
	 * @author KHC
	 * @since 2016年8月23日 下午2:20:52
	 */
	@RequestMapping("get")
	public String getInfo(Integer id,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(id==null)throw new Exception("数据异常");
			DistributeOrderPl dist = distributeOrderPlService.getById(id);
			if(dist==null)throw new Exception("数据异常");
			Map<String, Object> info = discountrecordPlService.getInfoById(dist.getDcrdPlId());
			
			Object lon_a = info.get("lon");
			Object lat_a = info.get("lat");
			Object lon_b = info.get("longitude");
			Object lat_b = info.get("latitude");
			if(lon_a!=null && lat_a!=null && lon_b!=null && lat_b!=null){//距离
				Double distance = DistanceUtil.getDistance(Float.valueOf(lat_a.toString()), Float.valueOf(lon_a.toString()),Float.valueOf(lat_b.toString()),Float.valueOf(lon_b.toString()));
				info.put("distance",distance);
			}
			
			result.put("data",info);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 机构端取消订单
	 * @author KHC
	 * @param dtboId
	 * @param reason
	 * @param currentState
	 * @param memberId
	 * @param model
	 * @since 2016年9月1日 上午10:37:44
	 */
	@RequestMapping("cancel")
	public String cancle(Integer dtboId,String reason,Integer cancel,Integer currentState,Integer memberId,Float version,Model model){
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
			DistributeOrderPl dist = distributeOrderPlService.getById(dtboId);
			if(dist.getState()!=currentState)throw new Exception("数据已过期");
			DiscountrecordPl disc = discountrecordPlService.getById(dist.getDcrdPlId());
			if(disc==null || disc.getOrderflag()==0)throw new Exception("数据已过期");
			
			if(reason!=null) dist.setCancelCause(reason);
			dist.setCancel(cancel);
			dist.setState(-2);// 机构端取消订单
			distributeOrderPlService.updateModel(dist);
			DistributeOrderTask task = new DistributeOrderTask();
			task.setDtboId(dist.getId());
			task.setCreateTime(new Date());
			task.setRemarks(reason);// 取消理由
			task.setState(-2);
			task.setOperatorId(memberId);
			task.setOperatorType(OperatorType.MEMBER);
			task.setType(2);// 批量
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
	
	/**
	 * 接受订单
	 * @param form
	 * @param memberId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("accept")
	public String accept(DistributeOrderPl form, Integer memberId,Float version, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String ps = Constant.properties.getProperty("VERSION_ALERT");//@WKX EDIT版本过期提示[因之前版本强制更新有问题，现做提示处理]
    	if("on".equals(ps) && version!=null && version<=2.32F){
    		result.put("response", "failed");
			result.put("msg", "当前版本已停用，请下载最新版本票据管家APP。");
			model.addAttribute("retValue",JacksonUtil.objToJson(result));
			return "ajax";
    	}
		
		try {
			DistributeOrderPl order = distributeOrderPlService.getById(form.getId());
			if(order==null || order.getState()!=1)throw new Exception("数据已过期");
			
			order.setLongitude(form.getLongitude());
			order.setLatitude(form.getLatitude());
			order.setTake(1);//接单标识
			
			distributeOrderPlService.updateToAccept(order);
			
			DiscountrecordPl dis=discountrecordPlService.getById(order.getDcrdPlId());
			Map<String,Object> member = memberService.getInfoByOrgId(order.getOrgId());
			Map<String,String> param = new HashMap<String, String>();
			param.put("allmoney", dis.getAllmoney()!=null?dis.getAllmoney().toString():"");
			if(member!=null && member.get("mobile")!=null){
				SendMessagesUtil.sendMessage(member.get("mobile").toString(), "SMS_14520131", param);
			}
			
			result.put("data",order);
			result.put("response", "success");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "操作失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}