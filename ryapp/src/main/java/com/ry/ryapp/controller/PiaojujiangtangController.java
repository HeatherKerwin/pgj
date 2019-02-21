package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Piaojujiangtang;
import com.ry.core.form.PiaojujiangtangForm;
import com.ry.core.service.PiaojujiangtangService;
import com.ry.util.page.PageResults;


@Controller
public class PiaojujiangtangController {
	
	@Resource
	PiaojujiangtangService piaojujiangtangService;
	
	@RequestMapping("/app/piaojujiangtanglist/")
	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Integer type = Integer.valueOf(request.getParameter("type"));
			String keyword = request.getParameter("keyword");
			Integer pagenumber = Integer.valueOf(request.getParameter("pagenumber")); 
			if(keyword==null){
				keyword = "";
			}			
			PiaojujiangtangForm nf = new PiaojujiangtangForm();
			nf.setType(type);
			nf.setTitle(keyword);
			
			PageResults<Piaojujiangtang> pageResults = piaojujiangtangService.getPageList(nf, pagenumber, 10);
			List<Piaojujiangtang> piaojujiangtangList = pageResults.getResults();
			map.put("response", "success");
			map.put("msg", new Object[]{pageResults,piaojujiangtangList});
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
			out.print(JSONArray.fromObject(map));
			
		}
	}	
	
	@RequestMapping("/app/piaojujiangtangdetail/")
	public void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String piaojujiangtangid = request.getParameter("piaojujiangtangid");
			Piaojujiangtang piaojujiangtang = piaojujiangtangService.getPiaojujiangtangById(Integer.parseInt(piaojujiangtangid));
			if(piaojujiangtang!=null){
				map.put("response", "success");
				map.put("msg", piaojujiangtang);
				out.print(JSONArray.fromObject(map));
			}else{
				map.put("response", "failed");
				map.put("msg", "获取失败");
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
