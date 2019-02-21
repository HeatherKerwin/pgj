package com.ry.rycms.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.News;
import com.ry.core.entity.NewsCrawler;
import com.ry.core.form.NewsCrawlerForm;
import com.ry.core.service.NewsCrawlerService;
import com.ry.core.service.NewsService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.page.PageResults;

/**
 * 爬虫资讯
 * @author KHC
 */
@Controller
public class NewsCrawlerController {
	
	@Resource
	NewsCrawlerService newsCrawlerService;
	
	@Resource
	NewsService newsService;
	
	/**
	 * 进入编辑页面
	 * @author KHC
	 * @param id
	 * @param request
	 * @param response
	 * @since 2016年8月15日 下午5:37:29
	 */
	@RequestMapping("/newscrawler/toupdate/")
	public String toUpdate(Integer id,HttpServletRequest request, HttpServletResponse response){
		NewsCrawler newsCrawler = newsCrawlerService.getById(id);
		if (newsCrawler.getPic()!=null) {				
			newsCrawler.setPic(Constant.properties.getProperty("preimgUrl")+newsCrawler.getPic());				
		}
		if (newsCrawler.getPic1()!=null) {				
			newsCrawler.setPic1(Constant.properties.getProperty("preimgUrl")+newsCrawler.getPic1());				
		}
		request.setAttribute("news", newsCrawler);
		try {
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "/information/newsCrawlerUpdate";
	}
	
	/**
	 * 编辑
	 * @author KHC
	 * @param nc
	 * @param state
	 * @param request
	 * @param response
	 * @since 2016年8月15日 下午5:37:49
	 */
	@RequestMapping("/newscrawler/update/")
	public String update(NewsCrawler nc, String state, HttpServletRequest request, HttpServletResponse response){
		if (StringUtils.hasText(nc.getPic())) {
			if (nc.getPic().contains("temp") || nc.getPic().contains("upload")) {
				String uPath = nc.getPic().replaceFirst("temp", "upload");
				int idx = uPath.indexOf("upload");
				String p = uPath.substring(idx);
				nc.setPic(p);
			}				
		}	
		if (StringUtils.hasText(nc.getPic1())) {
			if (nc.getPic1().contains("temp") || nc.getPic1().contains("upload")) {
				String uPath1 = nc.getPic1().replaceFirst("temp", "upload");
				int idx1 = uPath1.indexOf("upload");
				String p1 = uPath1.substring(idx1);
				nc.setPic1(p1);
			}				
		}
		nc.setCreateTime(new Date());	
		if(state.equals("0")){//发布时保存到资讯列表中
			nc.setState(0);
			newsCrawlerService.updateModel(nc);
			News news = new News();
			news.setType(nc.getType());
			news.setTitle(nc.getTitle());
			news.setState(1);
			news.setContent(nc.getContent());
			news.setArticleSource(nc.getArticleSource());
			news.setPic(nc.getPic());
			news.setPic1(nc.getPic1());
			news.setCreateTime(new Date());
			news.setUrlSource(nc.getUrlSource());
			news.setAbstracts(nc.getAbstractCrawler());
			newsService.saveNews(news);
		}
		if(state.equals("1")){
			nc.setState(1);
			newsCrawlerService.updateModel(nc);	
		}
		request.setAttribute("message", "修改成功");
		return "redirect:/newscrawler/search/";
	}
	
	/**
	 * 列表
	 * @author KHC
	 * @param pr
	 * @param beginDate
	 * @param endDate
	 * @param type
	 * @param title
	 * @param state
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 * @since 2016年8月15日 下午5:38:18
	 */
	@RequestMapping("/newscrawler/search/")
	public String list(PageResults<NewsCrawler> pr,String beginDate,String endDate,String type,String title,String state,  HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		
		NewsCrawlerForm ncf = new NewsCrawlerForm();
		if(ncf!=null){
			if(StringUtils.hasText(beginDate)){
				ncf.setBeginDate(DateUtil.parser(beginDate,  DateUtil.FORMART3));
			}
			if(StringUtils.hasText(endDate)){
				ncf.setEndDate(DateUtil.parser(endDate,  DateUtil.FORMART3));
			}
			if(!(type==null||type.equals("-1"))){
				ncf.setType(Integer.valueOf(type));
			}
			if(title!=null){
				ncf.setTitle(title);
			}
			if(!(state==null||state.equals("-1"))){
				ncf.setState(Integer.valueOf(state));
			}
		}
	    pr = newsCrawlerService.getPageList(ncf, pr.getCurrentPage(), 20);				
		model.addAttribute("begintimeStr", beginDate);
		model.addAttribute("endtimeStr",endDate);
		model.addAttribute("type", ncf.getType());
		model.addAttribute("title", ncf.getTitle());
		model.addAttribute("state", ncf.getState());
		model.addAttribute("pr", pr);
		return "/information/newsCrawlerList";
	}
			
	/**
	 * 删除
	 * @author KHC
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 * @since 2016年8月15日 下午5:38:45
	 */
	@RequestMapping("/newscrawler/delete/")
	public String delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			newsCrawlerService.deleteById(id);;
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
			try {
				request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/newscrawler/search/";
	}
			
}


