package com.utiexian.website.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Member;
import com.ry.core.service.DiscountrecordPlService;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.DiscountrecordSpService;
import com.ry.core.service.DistributeOrderPlService;
import com.ry.core.service.DistributeOrderService;
import com.ry.core.service.DistributeOrderSpService;
import com.ry.core.service.OrgInfoService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;

/**
 * 接单大厅
 * @author KHC
 */
@Controller
public class HallController {
	
	private static final Logger logger = Logger.getLogger(HallController.class);
	
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
	 * 接单大厅页面(机构端 待接单)
	 * @author KHC
	 * @param request
	 * @since 2016年12月12日 下午4:37:13
	 */
	@RequestMapping("/hall/index")
	public String index(HttpServletRequest request){
		return "/hall/receiveOrderall1";
	}
	
	/**
	 * 接单大厅页面（机构端 已接单）
	 * @author MH
	 * @param request
	 * @since 2016年12月12日 下午4:37:13
	 */
	@RequestMapping("/hall/index2")
	public String index2(HttpServletRequest request){
		return "/hall/receiveOrderall2";
	}
	
	/**
	 * 接单大厅页面（企业端）
	 * @author MH
	 * @param request
	 * @since 2016年12月12日 下午4:37:13
	 */
	@RequestMapping("/hall/disc/index")
	public String discindex(HttpServletRequest request){
		return "/discountorder/discountorderall";
	}
	
	/**
	 * 银票待接单
	 * @param orgId
	 * @throws ParseException
	 * @author KHC
	 */
	private List<Map<String,Object>> getDtboByOrgId(Integer orgId,Float version) throws ParseException{
		String switch_ = Constant.properties.getProperty("PAYSWITCH");//支付开关
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
		Date s = DateUtil.parser(start + " 00:00:00", DateUtil.FORMART);

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
			
			if("off".equals(switch_)){//关闭保证金
				order.put("bail", 0);
			}
		}
		return orderList;
	}
	
	/**
	 * 商票待接单
	 * @param orgId
	 * @throws ParseException
	 * @author KHC
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
	 * @author KHC
	 * @param request
	 * @param response
	 * @param model
	 * @since 2016年10月20日 下午1:20:58
	 */
	@RequestMapping("/hall/list")
	public String list(HttpServletRequest request, HttpServletResponse response,Model model){
		Member member = (Member) request.getSession().getAttribute("member");
		Integer memberId = member.getId();
		Map<String,Object> result = new HashMap<String, Object>();
		Map<String, Object> map = orgInfoService.getByMemberIdAndType(memberId, 1);//查询机构
		if(map!=null && map.get("org_id")!=null){
			Integer orgId = Integer.valueOf(map.get("org_id").toString());
			try {
				List<Map<String,Object>> discList = getDtboByOrgId(orgId,null);
				
				List<Map<String,Object>> discSpList = getDtboSpByOrgId(orgId);
				for(Map<String, Object> dtboSp: discSpList){
					Object time = dtboSp.get("create_time");//接单生成日期
					if(time!=null){
						int timer = DateUtil.calSeconds(DateUtil.parser(time.toString(), DateUtil.FORMART), new Date());
						dtboSp.put("timerSp", 1800-timer);
					}
				}
				result.put("data", discList);//银票待接单
				result.put("dataSp", discSpList);//商票待接单
				result.put("dataPl", getDtboPlByOrgId(orgId));//批量待接单
				result.put("response", "success");
				result.put("msg", "查询成功");
			} catch (Exception e) {
				result.put("response", "failed");
				result.put("msg", "查询失败");
				logger.info("更新数据出错:",e);
			}
		}else{
			result.put("response", "failed");
			result.put("msg", "查询失败");
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}