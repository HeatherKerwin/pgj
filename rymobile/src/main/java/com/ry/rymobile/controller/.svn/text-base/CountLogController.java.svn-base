package com.ry.rymobile.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.da.AppVisitLog;
import com.ry.core.service.AppVisitLogService;
import com.ry.util.HttpUtil;

/**
 * 目前是处理微信广告红包活动的行为记录
 * @author lvyanqin
 * @date 2016年1月15日
 */
@Controller
public class CountLogController {

	@Resource
	private AppVisitLogService appVisitLogService;

	/**
	 * 添加微信广告红包记录行为记录
	 * @param url
	 * @param code，统计标识
	 * @param version,版本
	 * @param memberId
	 * @param type  0：app访问记录:，1：微信广告红包记录
	 * @param req
	 * @return
	 * @date 2016年1月15日
	 * @author lvyanqin
	 */
	@RequestMapping("/addLog.htm")
	public String addAppVisitLog(String url,String code, String memberId,String type, HttpServletRequest req){
		AppVisitLog visit = new AppVisitLog();
		visit.setIp(HttpUtil.getIpAddr(req));
		visit.setActionDate(new Date());
		if(!StringUtils.isEmpty(memberId)){
			visit.setMemberId(Integer.valueOf(memberId));
		}
		visit.setUrl(url);
		visit.setCode(code);
		visit.setType(type);
		appVisitLogService.add(visit);
		return "ajax";
	}
	
}
