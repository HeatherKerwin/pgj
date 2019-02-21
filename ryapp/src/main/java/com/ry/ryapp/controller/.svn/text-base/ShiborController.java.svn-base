package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Shibor;
import com.ry.core.service.ShiborService;


@Controller
public class ShiborController {
	
	@Resource
	ShiborService shiborService;
	
	@RequestMapping("/app/shibor/")
	public void shibor(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String day = request.getParameter("day");
			Shibor shibor = shiborService.getShibor(day);
			if(shibor!=null){				
				map.put("response", "success");
				map.put("msg", shibor);
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
