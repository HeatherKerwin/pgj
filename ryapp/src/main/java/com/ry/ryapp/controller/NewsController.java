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

import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.core.service.NewsService;
import com.ry.util.page.PageResults;

import net.sf.json.JSONArray;


@Controller
public class NewsController {
	
	@Resource
	NewsService newsService;
	
	@RequestMapping("/app/newlist/")
	public void newlist(String code, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{							
			Integer type = Integer.valueOf(request.getParameter("type"));
			Integer pagenumber = Integer.valueOf(request.getParameter("pagenumber"));
			Integer pageSize = request.getParameter("pageSize")==null? 10 : Integer.parseInt(request.getParameter("pageSize"));
			NewsForm nf = new NewsForm();
			nf.setType(type);			
			PageResults<News> pageResults = newsService.getPageList(nf, pagenumber, pageSize);			
			List<News> newsList = newsService.getTopList(type, 1);
			News news = null;
			if(newsList!=null&&newsList.size()!=0){
				news = newsList.get(0);
				String title = news.getTitle();
				String time = news.getPublishtime().toString();
				news.setTitleShow(title.length()>18?title.substring(0,18)+"...":title);
				news.setPublishtimeStr(time);
			}
			map.put("response", "success");
			map.put("msg", new Object[]{news,pageResults.getResults(),pageResults});
			out.print(JSONArray.fromObject(map));
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
			out.print(JSONArray.fromObject(map));
			
		}				
	}

	/**
	 * 新闻资讯（去除内容样式：字体大小）
	 * @param code
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/app/newsdetail/")
	public void newsdetail(String code, HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String newsid = request.getParameter("newsid");
			News news = newsService.getNewsById(Integer.parseInt(newsid));
			if(news != null){
				news.setContent(news.getContent().replaceAll("font-size", "font-size-my"));//@WKX 去除内容样式
				map.put("response", "success");
				map.put("msg", news);
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
