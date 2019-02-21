package com.ry.ryapp.controller;

import net.sf.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Activecount;
import com.ry.core.entity.Gongcui;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.GongcuiService;


@Controller
public class GongcuiController {
	@Resource					
	GongcuiService gongcuiService;
	
	@Resource
	ActivecountService activecountService;

	private Long count;
	
	@RequestMapping("/app/gongcui/")
	public void gongcui(String gongcuinumber, Integer memberid, HttpServletRequest request, HttpServletResponse response) throws IOException {		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{						
			Activecount activecount = new Activecount();
			Long activetime = System.currentTimeMillis();
			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long begintimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 00:00:00").getTime();
			Long endtimelong = dateFormat2.parse(dateFormat1.format(new Date(activetime))+" 23:59:59").getTime();

			count = activecountService.countActive(begintimelong, endtimelong, memberid);
			if(count != null || count <= 0){
			
			}else{
				activecount.setActivetime(activetime);
				activecount.setMemberid(memberid);
				activecountService.addActivecount(activecount);
			}
			
			List<Gongcui> baseEntityList = gongcuiService.getList(gongcuinumber);
			
			if(baseEntityList!=null&&baseEntityList.size()!=0){
				map.put("response", "success");
				Gongcui gongcui = (Gongcui)baseEntityList.get(0);
				if(gongcui.getGongcuidate()!=null){
					gongcui.setGongcuidateStr(new SimpleDateFormat("yyyy-MM-dd").format(gongcui.getGongcuidate()));
				}
				map.put("msg", gongcui);
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
