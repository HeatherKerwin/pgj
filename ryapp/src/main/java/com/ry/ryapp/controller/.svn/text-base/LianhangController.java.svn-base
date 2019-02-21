package com.ry.ryapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Lianhang;
import com.ry.core.service.LianhangService;
import com.ry.util.page.PageResults;

import net.sf.json.JSONArray;


@Controller
public class LianhangController {
	@Resource					
	LianhangService lianhangService;
	
	@RequestMapping("/app/lianhang/")
	public void lianhang(String yinhang, String provice, String city, String keyword, HttpServletRequest request, HttpServletResponse response) throws IOException {		
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			if(provice.endsWith("省")||provice.endsWith("市")){
				provice = provice.substring(0, provice.length()-1);
			}
			if(city.endsWith("市")){
				city = city.substring(0, city.length()-1);
			}
			if("请选择省份".equals(provice)){
				map.put("response", "failed");
				map.put("msg", "请选择省份");
				out.print(JSONArray.fromObject(map));
				return;
			}
			if("请选择城市".equals(city)){
				map.put("response", "failed");
				map.put("msg", "请选择城市");
				out.print(JSONArray.fromObject(map));
				return;
			}
			Lianhang lianhang = new Lianhang();
			lianhang.setProvice(provice);
			
			if (yinhang != null && !"".equals(yinhang.trim()) && !"请选择银行".equals(yinhang.trim())) {
				lianhang.setYinhang(yinhang);
			}
			if (city != null && !"".equals(city.trim()) && !"请选择城".equals(city.trim())) {
				lianhang.setCity(city);
			}
			if (keyword != null && !"".equals(keyword.trim())) {
				lianhang.setYinhangdesc(keyword);
			}
			
			List<Lianhang> lianhangList = lianhangService.getList(lianhang);
		
			if(lianhangList!=null&&lianhangList.size()!=0){
				map.put("response", "success");
				map.put("msg", lianhangList);
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
	
	@RequestMapping("/app/getLianhang/")
	public void getLianhang(String yinhang, String provice, String city, String keyword, HttpServletRequest request, HttpServletResponse response) throws IOException {		
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			if(provice.endsWith("省")||provice.endsWith("市")){
				provice = provice.substring(0, provice.length()-1);
			}
			if(city.endsWith("市")){
				city = city.substring(0, city.length()-1);
			}
			if("请选择省份".equals(provice)){
				map.put("response", "failed");
				map.put("msg", "请选择省份");
				out.print(JSONArray.fromObject(map));
				return;
			}
			if("请选择城市".equals(city)){
				map.put("response", "failed");
				map.put("msg", "请选择城市");
				out.print(JSONArray.fromObject(map));
				return;
			}
			Lianhang lianhang = new Lianhang();
			lianhang.setProvice(provice);
			
			if (yinhang != null && !"".equals(yinhang.trim()) && !"请选择银行".equals(yinhang.trim())) {
				lianhang.setYinhang(yinhang);
			}
			if (city != null && !"".equals(city.trim()) && !"请选择城".equals(city.trim())) {
				lianhang.setCity(city);
			}
			if (keyword != null && !"".equals(keyword.trim())) {
				lianhang.setYinhangdesc(keyword);
			}
			//分页查询
			Integer pagenumber = Integer.valueOf(request.getParameter("pagenumber"));
			Integer pageSize = request.getParameter("pageSize")==null? 10 : Integer.parseInt(request.getParameter("pageSize"));
			PageResults<Lianhang> pageResults = lianhangService.getPageList(lianhang, pagenumber, pageSize);
			if(pageResults!=null){
				map.put("response", "success");
				map.put("msg", new Object[]{pageResults.getResults(),pageResults});
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
