package com.ry.ryapp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ry.core.entity.Actionlog;
import com.ry.core.entity.da.AppVisitLog;
import com.ry.core.service.ActionlogService;
import com.ry.core.service.AppVisitLogService;
import com.ry.util.HttpUtil;
import com.ry.util.JacksonUtil;

@Controller
public class AppVisitLogController {

	@Resource
	private AppVisitLogService appVisitLogService;
	
	@Resource
	private ActionlogService actionlogService;
	
	/**
	 * 添加APP访问记录
	 * @author LYQ
	 * @param url
	 * @param code，统计标识
	 * @param version,版本
	 * @param memberId
	 * @param type,0：app访问记录:，1：微信广告红包记录
	 * @param req
	 */
	@ResponseBody
	@RequestMapping("/addAppVisitLog.htm")
	public String addAppVisitLog(String url,String code,String version, String memberId,String type, HttpServletRequest req){
		AppVisitLog visit = new AppVisitLog();
		visit.setIp(HttpUtil.getIpAddr(req));
		visit.setActionDate(new Date());
		if(!StringUtils.isEmpty(memberId)){
			visit.setMemberId(Integer.valueOf(memberId));
		}
		visit.setUrl(url);
		visit.setCode(code);
		visit.setType(type);
		visit.setVersion(version);
		appVisitLogService.add(visit);
		return "ajax";
	}
	
	/**
	 * 功能统计
	 * @author WKX
	 * @param actionlog
	 * @param model
	 * @param request
	 * @throws IOException
	 * @since 2016年6月18日 上午12:06:48
	 */
	@RequestMapping("/addActionLog.htm")
	public String addActionLog(Actionlog actionlog,Model model,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			String ipadr = HttpUtil.getIpAddr(request);
			if(actionlog==null)throw new Exception();
			actionlog.setIpadr(ipadr);
			actionlog.setActiontime(new Date());
			actionlogService.addActionlog(actionlog);
			result.put("response", "success");
		} catch (Exception e) {
			result.put("response", "failed");
			e.printStackTrace();
		}
		model.addAttribute("retValue",JacksonUtil.objToJson(result));
		return "ajax";
	}
}
