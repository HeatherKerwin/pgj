package com.utiexian.rywap.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.service.AccountrecordService;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;

@Controller
public class AppChatController {
	private static final Logger logger = Logger.getLogger(AppChatController.class);
	@Resource
	private AccountrecordService accountrecordService;

	@RequestMapping("/app/chart")
	public String appchat(){
		System.out.println("-==================================");
		System.out.println("-==================================");
		System.out.println("-==================================");
		return "appchart";
	}
	
	@RequestMapping("/app/pieChart")
	public String appPieChart(String type,String start,String end,Integer memberId,Integer belong,Model model){
		model.addAttribute("type",type);
		model.addAttribute("memberId",memberId);
		model.addAttribute("belong",belong);
		if(start!=null){
			model.addAttribute("start",start);
		}
		if(start!=null){
			model.addAttribute("end",end);
		}
		return "appPieChart";
	}
	
	@RequestMapping("/app/chartApp")
	public String appchatApp(){
		return "chartApp";
	}
	
	/**
	 * 票据管理统计
	 * @param type 类型（1一个月、2三个月、3六个月、4自定义）
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param memberId
	 * @param model
	 * @author ZWD
	 */
	@RequestMapping("/app/zhangbu/tongji")
	public String tongji(String type,String start,String end,Integer memberId,Integer belong,Model model){
		if(belong==null)belong = 0;
		Map<String,Object> result = new HashMap<String,Object>();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		
		if("1".equals(type)){
			c.add(Calendar.MONTH, -1);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}else if("2".equals(type)){
			c.add(Calendar.MONTH, -3);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}else if("3".equals(type)){
			c.add(Calendar.MONTH, -6);
			start = format.format(c.getTime());
			end = format.format(new Date());
		}
		try {
			SimpleDateFormat fm = new SimpleDateFormat("yyyy年MM月dd日");
			result.put("start", fm.format(DateUtil.parser(start, DateUtil.FORMART3)));
			result.put("end", fm.format(DateUtil.parser(end, DateUtil.FORMART3)));
			
			List<Map<String,Object>> list = accountrecordService.getSumByMemberId(memberId,belong, start, end);
			if(list!=null && list.size()>0){
				Double tiexianlixi = 0D;
				Double allprice = 0D;
				for(Map<String,Object> map:list){
					if(map!=null && map.get("tiexianlixi")!=null){
						tiexianlixi += Double.valueOf(map.get("tiexianlixi").toString());
					}
					if(map!=null && map.get("allprice")!=null){
						allprice += Double.valueOf(map.get("allprice").toString());
					}
				}
				result.put("tiexianlixi", tiexianlixi);
				result.put("allprice", allprice);
			}
			result.put("response", "success");
			result.put("data", list);
			result.put("msg", "操作成功");
		} catch (Exception ex) {
			result.put("response", "failed");
			result.put("msg", "获取失败");
			logger.info("统计票据类型出错:",ex);
		}
		model.addAttribute("retValue", JacksonUtil.objToJson(result));
		return "ajax";
	}
}
