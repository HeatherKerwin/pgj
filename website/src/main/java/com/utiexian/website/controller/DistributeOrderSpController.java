package com.utiexian.website.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.entity.DiscountrecordTask.OperatorType;
import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Member;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.DistributeOrderTaskService;
import com.ry.core.service.MemberService;
import com.ry.core.service.OrgInfoService;
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
@RequestMapping("/distributeordersp/")
public class DistributeOrderSpController {
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DistributeOrderTaskService distributeOrderTaskService;
	
	@Resource
	MemberService memberService;
	
	@Resource
	OrgInfoService orgInfoService;
	
	/**
	 * 机构商票列表
	 * @author KHC
	 * @param model
	 * @since 2016年12月12日 下午4:38:20
	 */
	@RequestMapping("list")
	public String list(Model model){
		return "/orgorder/sp_list";
	}
	
	/**
	 * 机构商票详情
	 * @author KHC
	 * @param no
	 * @param model
	 * @since 2016年11月20日 下午7:13:46
	 */
	@RequestMapping("detail")
	public String detail(String no,Model model){
		model.addAttribute("id",no);
		return "/hall/receiveOrderSp";
	}
	
	/**
	 * 机构接单商票详情
	 * @author KHC
	 * @param no
	 * @param model
	 * @since 2016年11月20日 下午7:13:46
	 */
	@RequestMapping("detail/wait")
	public String getWait(String no,Model model){
		model.addAttribute("no",no);
		return "distributeorder_sp/distributeorder_sp";
	}
	
	/**
	 * 分页获取机构商票列表
	 * @author KHC
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @since 2016年11月7日 下午4:08:53
	 */
	@RequestMapping("list/search")
	public String list(DistributeOrderSpForm form,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
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
			PageResults<Map<String,Object>> page = distributeOrderSpService.getPageListPC(pageIndex, pageSize, form);
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
	 * 获取机构派单的详情
	 * @author ZY
	 * @param no 订单号
	 * @param model
	 * 2016年12月12日下午2:35:24
	 */
	@RequestMapping("get")
	public String get(String no,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			DistributeOrderSp dist = distributeOrderSpService.getByNo(no);
			if(dist==null)throw new Exception("数据异常");
			Map<String,Object> dtbo = distributeOrderSpService.getInfoByDtboId(dist.getId());
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
			if(dtbo!=null){
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
				result.put("begintime", fm.format(DateUtil.parser(dtbo.get("begintime").toString(), DateUtil.FORMART3)));
				result.put("endtime", fm.format(DateUtil.parser(dtbo.get("endtime").toString(), DateUtil.FORMART3)));
				result.put("printtime", fm.format(DateUtil.parser(dtbo.get("printtime").toString(), DateUtil.FORMART3)));
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
	 * @param model
	 * @author ZY
	 */
	@RequestMapping("accept")
	public String accept(DistributeOrderSp form, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			DistributeOrderSp dist = distributeOrderSpService.getByNo(form.getNo());
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
			java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
			if(temp!=0D)txlx = df.format(temp);
		}
		return txlx;
	}

	/**
	 * 接单成功后跳转
	 * @author ZY
	 * @since 2016年10月26日 上午10:09:36
	 */
	@RequestMapping("success")
	public String redirectCallback(){
		return "distributeorder_sp/success";
	}
	
	/**
	 * 机构取消（拒绝）订单
	 * @author ZY
	 * @param no 订单号
	 * @param cancel 取消原因
	 * @param reason 原因文本
	 * @param currentState 当前状态
	 * @param memberId 用户主键
	 * @param model
	 * 2016年11月12日下午2:37:36
	 */
	@RequestMapping("cancel")
	public String cancle(String no,Integer cancel,String reason,Integer currentState,HttpServletRequest request,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		String alert = "操作成功";
		String ok = "确定";
		try {
			DistributeOrderSp dist = distributeOrderSpService.getByNo(no);
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
		DistributeOrderSpForm form = new DistributeOrderSpForm();
		Map<String, Object> map = orgInfoService.getByMemberIdAndType(memberId, 1);//查询机构
		if(map!=null && map.get("org_id")!=null){
			try {
				form.setOrgId(Integer.valueOf(map.get("org_id").toString()));
				form.setState(2);
				PageResults<Map<String,Object>> page = distributeOrderSpService.getPageListPC(0, 10, form);
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