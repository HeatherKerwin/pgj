package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Appversion;
import com.ry.core.entity.Phonedetail;
import com.ry.core.service.AppversionService;
import com.ry.core.service.MemberService;
import com.ry.core.service.PhonedetailService;

import net.sf.json.JSONArray;


@Controller
public class VersonController {
	
	@Resource
	AppversionService appversionService;
	
	@Resource
	PhonedetailService phonedetailService;
	
	@Resource
	MemberService memberService;
		
	@RequestMapping("/app/phonedetail/")
	public void phonedetail(Phonedetail phonedetail, HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		if (phonedetail.getMemberid() == null || phonedetail.getMemberid() <= 0) {
			map.put("response", "error");
			out.print(JSONArray.fromObject(map));
			return;
		}
		//添加设备信息
		Phonedetail p = new Phonedetail();
		p.setMemberid(phonedetail.getMemberid());
		List<Phonedetail> list = phonedetailService.getPhonedetail(p);
		
		if (list != null && list.size()>0) {
			Phonedetail pd = new Phonedetail();
			pd = list.get(0);
			String qudao = pd.getQudao();
			if(StringUtils.isBlank(qudao)){
				pd.setQudao(phonedetail.getQudao());
				pd.setUpdateDate(new Date());
				phonedetailService.updatePhonedetail(pd);
			}
			map.put("response", "success");
			
		} else {
			phonedetail.setCreateDate(new Date());
			phonedetailService.addPhonedetail(phonedetail);
			map.put("response", "success");
		}
		
		out.print(JSONArray.fromObject(map));	
	}	
	
	@RequestMapping("/app/version/")
	public void verson(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			List<Appversion> baseEntityList = appversionService.getList();
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				Appversion appversion = (Appversion)baseEntityList.get(0);
				map.put("response", "success");
				map.put("msg", appversion);
				out.print(JSONArray.fromObject(map));
			}else{
				map.put("response", "failed");
				map.put("msg", "无数据");
				out.print(JSONArray.fromObject(map));
			}
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
			out.print(JSONArray.fromObject(map));
		}
	}	
	
}
