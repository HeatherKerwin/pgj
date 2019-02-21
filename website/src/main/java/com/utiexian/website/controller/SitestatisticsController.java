package com.utiexian.website.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Sitestatistics;
import com.ry.core.service.SitestatisticsService;

@Controller
public class SitestatisticsController {
	
	@Resource
	SitestatisticsService sitestatisticsService;
	
	@RequestMapping("/site/addSitestatistics")
	public String add(String from, String url, String type, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try {				
			//统计合作网站过来的访问量
			String ip = getIpAddr(request);
			if ("180.166.201.178".equals(ip)) {
				return "ajax";
			}
			Sitestatistics sitestatistics = new Sitestatistics();			
			sitestatistics.setUrl(url);
			sitestatistics.setInvitedate(new Date());	
			sitestatistics.setIp(ip);			
			sitestatistics.setType(type);
			if (from !=null && !"".equals(from.trim()) && !"null".equals(from.trim())) {
				sitestatistics.setHezuo(from);			
			}

			sitestatisticsService.saveSitestatistics(sitestatistics);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ajax";
	}	
	

	public static String getIpAddr(HttpServletRequest request) {
		
		String ip = request.getHeader("x-forwarded-for");
		if(ip != null&&ip.contains(",")){
			ip=ip.split(",")[0];
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}	

}
