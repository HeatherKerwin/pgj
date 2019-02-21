package com.utiexian.website.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Member;
import com.ry.core.form.DistributeOrderPlForm;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.DistributeOrderTaskService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
import com.ry.util.DateUtil;
import com.ry.util.DistanceUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.SendMessagesUtil;
import com.ry.util.page.PageResults;

/**
 * 批量机构订单
 * @author KHC
 */
@Controller
@RequestMapping("/distributeorderpl/")
public class DistributeOrderPlController {
	
	@Resource
	DistributeOrderPlService distributeOrderPlService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	@Resource
	DistributeOrderTaskService distributeOrderTaskService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	/**
	 * 机构批量列表
	 * @author KHC
	 * @param model
	 * @since 2016年12月12日 下午4:38:55
	 */
	@RequestMapping("list")
	public String list(Model model){
		return "/orgorder/pl_list";
	}
	
	/**
	 * 机构批量详情
	 * @author KHC
	 * @param no
	 * @param model
	 * @since 2016年11月20日 下午7:13:46
	 */
	@RequestMapping("detail")
	public String detail(String no,Model model){
		model.addAttribute("no",no);
		return "/orgorder/pl_xq";
	}
	
	/**
	 * 机构接单批量详情
	 * @author KHC
	 * @param no
	 * @param model
	 * @since 2016年11月20日 下午7:13:46
	 */
	@RequestMapping("detail/wait")
	public String getWait(String no,Model model){
		model.addAttribute("no",no);
		return "distributeorder_pl/distributeorder_pl";
	}
	
	/**
	 * 分页获取机构批量列表
	 * @author KHC
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年11月7日 下午4:08:53
	 */
	@RequestMapping("list/search")
	public String list(DistributeOrderPlForm form,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String, Object> map = orgInfoService.getByMemberIdAndType(memberId, 1);//查询机构
		if(map!=null && map.get("org_id")!=null){
			form.setOrgId(Integer.valueOf(map.get("org_id").toString()));
		}
		if(pageIndex==null)pageIndex = 0;
		if(pageSize==null)pageSize = 10;
		try {
			PageResults<Map<String,Object>> page = distributeOrderPlService.getPageListPC(pageIndex, pageSize, form);
			result.put("data",page);
			result.put("response", "success");
			result.put("msg", "获取成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取批量订单详情
	 * @author KHC
	 * @since 2016年10月23日 下午2:20:52
	 */
	@RequestMapping("get")
	public String getInfo(String no,Model model){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrderPl dist = distributeOrderPlService.getByNo(no);
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
			if(info!=null){
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
				result.put("endtime", fm.format(DateUtil.parser(info.get("endtime").toString(), DateUtil.FORMART3)));
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
	 * @author ZY
	 * @param no 订单号
	 * @param reason 取消理由
	 * @param cancel 取消的类型
	 * @param currentState 订单状态
	 * @param request
	 * @param model
	 * @since 2016年10月24日 上午10:37:44
	 */
	@RequestMapping("cancel")
	public String cancle(String no,String reason,Integer cancel,Integer currentState,HttpServletRequest request,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrderPl dist = distributeOrderPlService.getByNo(no);
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
	 * @author ZY
	 * @param form 订单详情
	 * @param model
	 * 2016年12月12日下午4:44:56
	 */
	@RequestMapping("accept")
	public String accept(DistributeOrderPl form, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrderPl order = distributeOrderPlService.getByNo(form.getNo());
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
	
	/**
	 * 接单成功后跳转
	 * @author ZY
	 * @since 2016年10月26日 上午10:09:36
	 */
	@RequestMapping("success")
	public String success(){
		return "distributeorder_pl/success";
	}
	
	/**
	 * 加载状态角标
	 * @author KHC
	 * @param type
	 * @param orgId
	 * @param version
	 * @param model
	 * @since 2016年11月14日 下午2:25:35
	 */
	@RequestMapping("badge")
	public String orgBadge(HttpServletRequest request,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		DistributeOrderPlForm form = new DistributeOrderPlForm();
		Map<String, Object> map = orgInfoService.getByMemberIdAndType(memberId, 1);//查询机构
		if(map!=null && map.get("org_id")!=null){
			try {
				form.setOrgId(Integer.valueOf(map.get("org_id").toString()));
				form.setState(2);
				PageResults<Map<String,Object>> page = distributeOrderPlService.getPageListPC(0, 10, form);
				result.put("list",page.getTotalCount());
				result.put("response", "success");
			} catch (Exception e) {
				e.printStackTrace();
				result.put("response", "failed");
				result.put("msg", "查询失败");
			}
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}