package com.ry.rycms.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.News;
import com.ry.core.form.NewsForm;
import com.ry.core.service.ActivecountService;
import com.ry.core.service.NewsService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.PropertiesUtil;
import com.ry.util.page.PageResults;
import com.ry.web.html.CommonHtmlGenerator;

/**
 * @author Ry-wk
 */
@Controller
public class NewsController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	CommonHtmlGenerator commonHtmlGenerator;
	
	@Resource
	NewsService newsService;
	
	@Resource
	ActivecountService activecountService;
	
	/**
	 * 新增
	 * @author KHC
	 * @param sm
	 * @param request
	 * @param state
	 * @param response
	 * @throws Exception
	 * @since 2016年8月16日 上午9:53:45
	 */
	@RequestMapping("/news/add/")
	public String add(News sm, HttpServletRequest request,String state, HttpServletResponse response,Model model) throws Exception{
		String preimgUrl = PropertiesUtil.getConfigPropertiesValue("preimgUrl",null);
		if(state.equals("0")){
			sm.setState(0);
			if(sm.getPublishtime()==null){
				sm.setPublishtime(new Date());
			}else{
				sm.setPublishtime(sm.getPublishtime());
			}
		}
		if(state.equals("1")){
			sm.setState(1);
			sm.setPublishtime(null);
		}
		sm.setCreateTime(new Date());
		if(StringUtils.hasText(sm.getPic())){
			String pic = sm.getPic().replace(preimgUrl, "");
			sm.setPic(pic);
		}
		if(StringUtils.hasText(sm.getPic1())){
			String pic1 = sm.getPic1().replace(preimgUrl, "");
			sm.setPic1(pic1);
		}
		newsService.saveNews(sm);
		
		if(sm!=null && sm.getState()==0){//发布咨询（生成PC端静态页面）
			try {
				CommonHtmlGenerator generator = CommonHtmlGenerator.clone(commonHtmlGenerator);
				this.createNews(generator, sm, model);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "发布失败");
				return "redirect:/news/search/";
			}
		}
		request.setAttribute("message", "添加成功");
		try {
		} catch (Exception e) {
			log.info("news save  error！", e);
			
		}
		return "redirect:/news/search/";
	}
	
	/**
	 * 进入编辑页面
	 * @author KHC
	 * @param id
	 * @param request
	 * @param response
	 * @since 2016年8月16日 上午9:54:03
	 */
	@RequestMapping("/news/toupdate/")
	public String toUpdate(Integer id,HttpServletRequest request, HttpServletResponse response){
		News news = newsService.getNewsById(id);
		if (news.getPic()!=null) {				
			news.setPic(Constant.properties.getProperty("preimgUrl")+news.getPic());				
		}
		if (news.getPic1()!=null) {				
			news.setPic1(Constant.properties.getProperty("preimgUrl")+news.getPic1());				
		}
		request.setAttribute("news", news);
		return "/information/newsUpdate";
	}
	
	/**
	 * 编辑
	 * @author KHC
	 * @param sm
	 * @param state
	 * @param request
	 * @param response
	 * @since 2016年8月16日 上午9:54:21
	 */
	@RequestMapping("/news/update/")
	public String update(News sm, String state, HttpServletRequest request, HttpServletResponse response,Model model){
		String preimgUrl = PropertiesUtil.getConfigPropertiesValue("preimgUrl",null);
		if(state.equals("0")){
			sm.setState(0);
			if(sm.getPublishtime()==null){
				sm.setPublishtime(new Date());
			}
			//发布咨询（生成PC端静态页面）
			try {
				CommonHtmlGenerator generator = CommonHtmlGenerator.clone(commonHtmlGenerator);
				this.createNews(generator, sm, model);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "发布失败");
				return "redirect:/news/search/";
			}
		}
		if(state.equals("1")){
			sm.setState(1);
		}
		sm.setUpdateTime(new Date());
		if(StringUtils.hasText(sm.getPic())){
			String pic = sm.getPic().replace(preimgUrl, "");
			sm.setPic(pic);
		}
		if(StringUtils.hasText(sm.getPic1())){
			String pic1 = sm.getPic1().replace(preimgUrl, "");
			sm.setPic1(pic1);
		}
		newsService.modifyNews(sm);			
		request.setAttribute("message", "修改成功");
		return "redirect:/news/search/";
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
	 * @since 2016年8月16日 上午9:54:30
	 */
	@RequestMapping("/news/search/")
	public String list(PageResults<News> pr,String beginDate,String endDate,String type,String title,String state,  HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
		if(pr.getCurrentPage()==null){
			pr.setCurrentPage(0);
		}
		
		NewsForm nf = new NewsForm();
		if(nf!=null){
			if(StringUtils.hasText(beginDate)){
				nf.setBeginDate(DateUtil.parser(beginDate,  DateUtil.FORMART3));
			}
			if(StringUtils.hasText(endDate)){
				nf.setEndDate(DateUtil.parser(endDate,  DateUtil.FORMART3));
			}
			if(!(type==null||type.equals("-1"))){
				nf.setType(Integer.valueOf(type));
			}
			if(title!=null){
				nf.setTitle(title);
			}
			if(!(state==null||state.equals("-1"))){
				nf.setState(Integer.valueOf(state));
			}
		}
		nf.setTimeSort(1);
	    pr = newsService.getPageList(nf, pr.getCurrentPage(), 20);				
		
		model.addAttribute("begintimeStr", beginDate);
		model.addAttribute("endtimeStr",endDate);
		model.addAttribute("type", nf.getType());
		model.addAttribute("title", nf.getTitle());
		model.addAttribute("state", nf.getState());
		model.addAttribute("pr", pr);
		return "/information/newsList";
	}
	
	@RequestMapping("/news/delete/")
	public String delete(Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			News news = newsService.getNewsById(id);
			news.setState(2);//已删除
			newsService.modifyNews(news);
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
			try {
				request.getRequestDispatcher("/manage/tip.jsp").forward(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "redirect:/news/search/";
	}
	
	/**
	 * 进入新增页面
	 * @author KHC
	 * @param request
	 * @param response
	 * @since 2016年8月16日 上午9:54:45
	 */
	@RequestMapping("/news/addInformation/")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		return "/information/newsAdd";
	}
			
	/**
	 * 创建页面（咨询详情页面）PC官网
	 * @author WKX
	 * @param generator 生产工具
	 * @param news 咨询
	 * @param model
	 * @throws Exception
	 * @since 2016年12月16日 下午3:38:16
	 */
	private void createNews(CommonHtmlGenerator generator,News news,Model model) throws Exception{
		if(news!=null){
			if (news.getPic1()!=null) {				
				news.setPic1(Constant.properties.getProperty("preimgUrl")+news.getPic1());				
			}
			if (news.getPublishtime() != null) {
				news.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(news.getPublishtime()));
			}
			model.addAttribute("news", news);
		}
		String prefix = File.separator + "news";//保存地址前缀（日期）
		if(news!=null){
			prefix += File.separator + DateUtil.formart(news.getPublishtime(), DateUtil.FORMART3);
		}
		generator.setOutputPath(Constant.properties.getProperty("websiteRootPath")+prefix);//此路径和ContextRealPath 只要一个完整配置就可以了
		generator.setOutputFileName(news.getId() + ".html");
		generator.setTemplateFileName("/website/news/info.ftl");
		generator.generate(model.asMap());
	}
}