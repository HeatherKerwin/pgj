package com.ry.ryapp.controller;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Actionlog;
import com.ry.core.service.ActionlogService;
import com.ry.core.util.Constant;
import com.ry.util.HttpUtil;


@Controller
public class JisuanqiController {
	
	@Resource
	ActionlogService actionlogService;
	
	@RequestMapping("/app/jisuanqi/")
	public void shibor(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");		
				
		String ip = HttpUtil.getIpAddr(request);
		String from = request.getParameter("from");
		String memberid = request.getParameter("memberid");
		Actionlog actionlog = new Actionlog();		
		if (memberid != null) {
			actionlog.setMemberId(Integer.parseInt(memberid));
		}
		actionlog.setCode(Constant.JISUANQI);		
		actionlog.setActiontime(new Date());
		actionlog.setIpadr(ip);
		actionlog.setFrom("APP");
		try {
			actionlogService.addActionlog(actionlog);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}	
	
}
