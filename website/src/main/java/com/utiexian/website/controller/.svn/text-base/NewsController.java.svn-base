package com.utiexian.website.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.core.service.NewsService;
import com.ry.core.util.Constant;
import com.ry.util.JacksonUtil;
import com.ry.util.StringUtil;
import com.ry.util.page.PageResults;

/**
 * @author KHC
 * 市场信息
 */
@Controller
public class NewsController {
	
	@Resource
	NewsService newsService;
	
	/**
	 * 全部新闻
	 * @author KHC
	 * @param request
	 * @since 2016年12月8日 下午5:35:01
	 */
	@RequestMapping("/news/index")
	public String index(HttpServletRequest request){
		return "/news/list";
	}
	
	/**
	 * 票据新闻
	 * @author KHC
	 * @param request
	 * @since 2016年12月8日 下午5:35:01
	 */
	@RequestMapping("/news/pjxw")
	public String index1(HttpServletRequest request){
		return "/news/pjxw";
	}
	
	/**
	 * 金融动态
	 * @author KHC
	 * @param request
	 * @since 2016年12月8日 下午5:35:01
	 */
	@RequestMapping("/news/jrdt")
	public String index2(HttpServletRequest request){
		return "/news/jrdt";
	}
	
	/**
	 * 管家说事
	 * @author KHC
	 * @param request
	 * @since 2016年12月8日 下午5:35:01
	 */
	@RequestMapping("/news/gjss")
	public String index3(HttpServletRequest request){
		return "/news/gjss";
	}
	
	/**
	 * 媒体报道
	 * @author KHC
	 * @param request
	 * @since 2016年12月8日 下午5:35:01
	 */
	@RequestMapping("/news/mtbd")
	public String index4(HttpServletRequest request){
		return "/news/mtbd";
	}
	
	/**
	 * @author KHC
	 * @param keyword
	 * @param nf
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @throws Exception
	 * @since 2016年10月21日 下午2:56:16
	 */
	@RequestMapping("/news/search")
	public String list(String keyword,NewsForm nf,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String regEx_html = "<[^>]+>";//去掉html标签
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
		try{
			if(pageIndex==null)pageIndex = 0;
			if(pageSize==null)pageSize = 10;
			if(StringUtils.isNotBlank(keyword)){
				nf.setTitle(keyword);
				nf.setContent(keyword);
			}
			PageResults<News> pageResults = newsService.getPageList(nf, pageIndex, pageSize);
			List<News> newsList = pageResults.getResults();
			for (int i=0; i<newsList.size(); i++) {
				String content = newsList.get(i).getContent();
				Matcher m_html = p_html.matcher(content); 
				content = m_html.replaceAll("");
				newsList.get(i).setContentShow(content.length()>150?content.substring(0,150)+"...":content);;
			}
			pageResults.setResults(newsList);
			map.put("response", "success");
			map.put("msg", "操作成功");
			map.put("data",pageResults);
		}catch(Exception e){
			e.printStackTrace();
			map.put("response", "failed");
			map.put("msg", "获取失败");
		}	
		model.addAttribute("retValue", JacksonUtil.objToJson(map));
		return "ajax";
	}
	
	/**
	 * 新闻详情页面
	 * @author KHC
	 * @param id
	 * @param request
	 * @throws Exception
	 * @since 2016年10月21日 下午5:02:27
	 */
	@RequestMapping("/news/detail/{id}")
	public String info(@PathVariable(value="id")Integer id,HttpServletRequest request) throws Exception{
		News news = newsService.getNewsById(id);
		if(news==null){
			return StringUtil.processRedirectUrl(Constant.properties.getProperty("baseUrl"),"/news/index");
		}else{
			if (news.getPic1()!=null) {				
				news.setPic1(Constant.properties.getProperty("preimgUrl")+news.getPic1());				
			}
			if (news.getPublishtime() != null) {
				news.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(news.getPublishtime()));
			}
			request.setAttribute("news", news);
			return "/news/info";
		}
	}
}
