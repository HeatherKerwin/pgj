package com.ry.ryapp.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.form.DiscountrecordForm;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.form.DiscountrecordSpForm;
import com.ry.core.form.DistributeOrderForm;
import com.ry.core.form.DistributeOrderPlForm;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.OrgInfoService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.page.PageResults;

/**
 * 接单大厅
 * @author WKX
 */
@Controller
@RequestMapping("/app/hall/")
public class HallController {
	
	@Resource
	OrgInfoService orgInfoService;
	
	@Resource
	DistributeOrderService distributeOrderService;
	
	@Resource
	DistributeOrderSpService distributeOrderSpService;
	
	@Resource
	DistributeOrderPlService distributeOrderPlService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	DiscountrecordSpService discountrecordSpService;
	
	@Resource
	DiscountrecordPlService discountrecordPlService;
	
	/**
	 * 银票待接单
	 * @param orgId
	 * @throws ParseException
	 * @author WKX
	 */
	private List<Map<String,Object>> getDtboByOrgId(Integer orgId,Float version) throws ParseException{
		List<Map<String, Object>> orderList = distributeOrderService.getAssignedByOrgId(orgId,version);
		Iterator<Map<String, Object>> i = orderList.iterator();
		int certificate = 0;// 认证
		int finish = 0;// 完成
		int reject = 0;// 拒绝
		// 认证过：0；未认证：30
		Map<String, Object> info = orgInfoService.getByOrgIdAndType(orgId, 1);
		if (info != null && info.get("state") != null) {
			if (2 != Integer.valueOf(info.get("state").toString())) {
				certificate += 30;
			}
		} else {
			certificate += 0;
		}
		// 上月有已完成订单：10；上月无已完成订单：30
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		String end = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date e = DateUtil.parser(end + " 23:59:59", DateUtil.FORMART);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String start = DateUtil.formart(calendar.getTime(), DateUtil.FORMART3);
		Date s;
		s = DateUtil.parser(start + " 00:00:00", DateUtil.FORMART);

		Long amount = distributeOrderService.countbyOrdertime(orgId, s.getTime(), e.getTime(), 3);// 已完成
		if (amount != null && amount > 0) {
			finish += 10;
		} else {
			finish += 30;
		}
		// 上月有拒绝订单：30；上月无拒绝订单：0
		Long bmount = distributeOrderService.countbyOrdertime(orgId, s.getTime(), e.getTime(), -2);// 拒绝
		if (bmount != null && bmount > 0) {
			reject += 30;
		}
		while (i.hasNext()) {
			Map<String, Object> order = i.next();
			Object time = order.get("create_time");
			if(time!=null){
				int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
				order.put("timer", 600-timer);
			}
			order.put("myTime", 20);
			
			Float bail = 0f;
			//加大小票（大票：30；小票：10）
			if (order.get("allmoney") != null && Double.valueOf(order.get("allmoney").toString()) > 500) {
				bail += 30;
			} else {
				bail += 10;
			}
			//加是否认证金额（认证：0；未认证：30）
			bail += certificate;
			//加上月是否已完成订单金额（有已完成：10；无已完成：30）
			bail += finish;
			//加上月是否拒绝订单金额（有：30；无：0）
			bail += reject;
			order.put("bail", bail);
		}
		return orderList;
	}
	
	/**
	 * 商票待接单
	 * @param orgId
	 * @throws ParseException
	 * @author WKX
	 */
	private List<Map<String,Object>> getDtboSpByOrgId(Integer orgId) throws ParseException{
		
		List<Map<String,Object>> list = distributeOrderSpService.getWaitByOrgId(orgId);
		return list;
	}
	
	/**
	 * 商票待接单
	 * @param orgId
	 * @throws ParseException
	 * @author WKX
	 */
	private List<Map<String,Object>> getDtboPlByOrgId(Integer orgId) throws ParseException{
		
		List<Map<String,Object>> list = distributeOrderPlService.getWaitByOrgId(orgId);
		return list;
	}
	
	/**
	 * 接单列表
	 * @param orgId
	 * @param model
	 * @author WKX
	 */
	@RequestMapping("list")
	public String list(Integer orgId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			result.put("data", getDtboByOrgId(orgId,version));//银票待接单
			result.put("dataSp", getDtboSpByOrgId(orgId));//商票待接单
			result.put("dataPl", getDtboPlByOrgId(orgId));//批量待接单
			result.put("response", "success");
			result.put("msg", "查询成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "查询失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 列表中角标的加载
	 * @author ZY
	 * @param type sp,yp,pl
	 * @param memberId 
	 * @param version
	 * @param model
	 * @since 2016年9月10日 上午10:59:10
	 */
	@RequestMapping("bns/badge")
	public String badge(String type,Integer memberId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		DiscountrecordForm form = new DiscountrecordForm();
		DiscountrecordSpForm form1 = new DiscountrecordSpForm();
		DiscountrecordPlForm form2 = new DiscountrecordPlForm();
		if(type == null || memberId ==null ) return null;
		if(type.equals("yp")){
			form.setMemberid(memberId);
			form.setOrderflag(1);
			form.setDepositState(0);
			PageResults<Map<String,Object>> page = discountrecordService.getPageList(0, 10, form);
			result.put("list_4",page.getTotalCount());
			form.setOrderflag(null);
			form.setDepositState(null);
			form.setState(new Integer[]{1,4});
			PageResults<Map<String,Object>> page1 = discountrecordService.getPcPageList(0, 10, form);
			result.put("list_",page1.getTotalCount());
			form.setOrderflag(3);
			form.setComment(1);
			PageResults<Map<String,Object>> page2 = discountrecordService.getPageList(0, 10, form);
			result.put("list_2",page2.getTotalCount());
		}else if(type.equals("sp")){
			form1.setMemberId(memberId);
			form1.setOrderflag(1);
			PageResults<Map<String,Object>> page = discountrecordSpService.getPageList(0, 10, form1);
			result.put("list_4",page.getTotalCount());
			form1.setOrderflag(2);
			PageResults<Map<String,Object>> page1 = discountrecordSpService.getPageList(0, 10, form1);
			result.put("list_",page1.getTotalCount());
			form1.setOrderflag(3);
			form1.setComment(1);
			PageResults<Map<String,Object>> page2 = discountrecordSpService.getPageList(0, 10, form1);
			result.put("list_2",page2.getTotalCount());
		}else if(type.equals("pl")){
			form2.setMemberId(memberId);
			form2.setOrderflag(1);
			PageResults<Map<String,Object>> page = discountrecordPlService.getPageList(0, 10, form2);
			result.put("list_4",page.getTotalCount());
			form2.setOrderflag(2);
			PageResults<Map<String,Object>> page1 = discountrecordPlService.getPageList(0, 10, form2);
			result.put("list_",page1.getTotalCount());
			form2.setOrderflag(3);
			form2.setComment(1);
			PageResults<Map<String,Object>> page2 = discountrecordPlService.getPageList(0, 10, form2);
			result.put("list_2",page2.getTotalCount());
		}else{
			result.put("response", "failed");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 更新角标
	 * @param type 银票商票批量
	 * @param orgId 机构主键
	 * @param version 版本
	 * @param model
	 */
	@RequestMapping("org/badge")
	public String orgBadge(String type,Integer orgId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		DistributeOrderForm form = new DistributeOrderForm();
		DistributeOrderSpForm form1 = new DistributeOrderSpForm();
		DistributeOrderPlForm form2 = new DistributeOrderPlForm();
		if(type == null || orgId ==null ) return null;
		if(type.equals("yp")){
			form.setVersion(version);
			form.setOrgId(orgId);
			form.setState(1);
			form.setDepositState(0);
			PageResults<Map<String,Object>> page = distributeOrderService.getPageList(0, 10, form);
			result.put("list_4",page.getTotalCount());
			form.setState(null);
			form.setDepositState(1);
			form.setStates(new Integer[]{1,2,4,5});
			PageResults<Map<String,Object>> page1 = distributeOrderService.getPageList(0, 10, form);
			result.put("list_",page1.getTotalCount());
		}else if(type.equals("sp")){
			form1.setOrgId(orgId);
			form1.setState(2);
			PageResults<Map<String,Object>> page1 = distributeOrderSpService.getPageList(0, 10, form1);
			result.put("list_",page1.getTotalCount());
		}else if(type.equals("pl")){
			form2.setOrgId(orgId);
			form2.setState(2);
			PageResults<Map<String,Object>> page1 = distributeOrderPlService.getPageList(0, 10, form2);
			result.put("list_",page1.getTotalCount());
		}else{
			result.put("response", "failed");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
	
	/**
	 * 获取待接单数量
	 * @author WKX
	 * @param orgId 机构主键
	 * @param version 版本
	 * @param model
	 * @since 2016年9月11日 下午3:57:02
	 */
	@RequestMapping("accept/badge")
	public String acceptBadge(Integer orgId,Float version,Model model){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			Integer amount = 0;
			List<Map<String, Object>> list_yp = distributeOrderService.getAssignedByOrgId(orgId,version);
			List<Map<String,Object>> list_sp = distributeOrderSpService.getWaitByOrgId(orgId);
			List<Map<String,Object>> list_pl = distributeOrderPlService.getWaitByOrgId(orgId);
			if(list_yp!=null && list_yp.size()>0){
				amount += list_yp.size();
			}
			if(list_sp!=null && list_sp.size()>0){
				amount += list_sp.size();
			}
			if(list_pl!=null && list_pl.size()>0){
				amount += list_pl.size();
			}
			result.put("data", amount);//待接单数量
			result.put("response", "success");
			result.put("msg", "查询成功");
		} catch (Exception e) {
			result.put("response", "failed");
			result.put("msg", "查询失败");
			e.printStackTrace();
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}