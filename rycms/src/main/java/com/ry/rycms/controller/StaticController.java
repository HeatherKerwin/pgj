package com.ry.rycms.controller;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ry.core.entity.Appimage;
import com.ry.core.entity.Gongcui;
import com.ry.core.entity.News;
import com.ry.core.entity.Shibor;
import com.ry.core.entity.SiteContent;
import com.ry.core.form.NewsForm;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.GongcuiService;
import com.ry.core.service.NewsService;
import com.ry.core.service.PicmanageService;
import com.ry.core.service.ShiborService;
import com.ry.core.service.SiteContentService;
import com.ry.core.service.VariablesService;
import com.ry.core.service.VersionInfoService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.util.JacksonUtil;
import com.ry.util.PropertiesUtil;
import com.ry.web.html.CommonHtmlGenerator;

@Controller
@RequestMapping("/generate")
public class StaticController {
	
	@Autowired
	CommonHtmlGenerator commonHtmlGenerator;
	
	@Resource					
	NewsService newsService;
	
	@Resource					
	VersionInfoService versionInfoService;
	
	@Resource
	DiscountrecordService discountrecordService;
	
	@Resource
	VariablesService variablesService;
	
	@Resource
	PicmanageService picmanageService;
	
	@Resource
	ShiborService shiborService;
	
	@Resource
	GongcuiService gongcuiService;
	
	@Resource
	SiteContentService siteContentService;
	
	/**
	 * 测试页面静态化
	 * @author WKX
	 * @param model
	 * @param request
	 * @since 2016年12月15日 下午5:02:47
	 */
	@RequestMapping(value = "/test.htm")
	public String generate(Model model,HttpServletRequest request){
		Map<String, Object> m = new HashMap<String, Object>();
		CommonHtmlGenerator generator = CommonHtmlGenerator.clone(commonHtmlGenerator);
		
		List<String>names = new ArrayList<String>();
		names.add("wang");
		names.add("li");
		names.add("zhao");
		model.addAttribute("names", names);
		try {
			generator.setContextRealPath(PropertiesUtil.wwwroot);
			//generator.setOutputPath(PropertiesUtil.wwwroot);//此路径和ContextRealPath 只要一个完整配置就可以了
			generator.setOutputFileName("/test.html");//../../../tmp0/wtpwebapps/website/test.html
			generator.setTemplateFileName("/website/test.ftl");
			generator.generate(model.asMap());
			m.put("message", "Province City And Area Html is Generated Successfully！");	
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Province City And Area Html is Generated Failed！");
		}
		model.addAttribute("retValue", JacksonUtil.objWriteStr(m));
		return "ajax";
	}
	
	/**
	 * 缓存交易额
	 * @author WKX
	 * @since 2016年12月15日 下午6:05:47
	 */
	private static Map<String,Object> index_params = null;
	private Map<String,Object> getJye(){
		if(index_params!=null){
			if(index_params.get("date")!=null){
				String tag1 = index_params.get("date").toString();
				String tag2 = DateUtil.formart(new Date(),"yyyy-MM-dd");
				if(!tag1.equals(tag2)){//已过期
				}else{//未过期
					return index_params;
				}
			}else{//缓存中不存在过期标示
			}
		}
		index_params = new HashMap<String, Object>();
		//缓存中没有数据需要重新获取
		Double month = discountrecordService.getMoneyLastMonth();
		Double temp = discountrecordService.getMoneyAllFinish();
		if(month==null){
			month = 0.0;
		}
		if(temp == null){
			temp = 0.0;
		}
		Double allMoney = temp + 600000;
		String add = variablesService.getByCode("ADD_TURNOVER", null);//获取额外配置额
		if(StringUtils.isNotBlank(add)){
			allMoney += Double.valueOf(add);
		}
		String add_m = variablesService.getByCode("ADD_TURNOVER_MONTH", null);//获取额外配置额(上月交易额)
		if(StringUtils.isNotBlank(add_m)){
			month += Double.valueOf(add_m);
		}
		
		DecimalFormat df = new DecimalFormat("0");
		index_params.put("date", DateUtil.formart(new Date(),"yyyy-MM-dd"));
		index_params.put("month", df.format(month));
		index_params.put("all", df.format(allMoney));
		return index_params;
	}
	
	/**
	 * 金额每三位加，来显示
	 * @author WKX
	 * @param money 传进来的参数
	 * @since 2016年12月15日 下午6:06:05
	 */
	private String reverse(String money){
		String str2="";
		String str1=new StringBuffer(money).reverse().toString();
		for(int i=0;i<str1.length();i++){
			if(i*3+3>=str1.length()){
				str2 += str1.substring(i*3);
				break;
			}
			str2 += str1.substring(i*3, i*3+3)+",";
		}
		if(str2.endsWith(",")){//这一步其实只是不需要了
			str2 = str2.substring(0, str2.length()-1);
		}
		return new StringBuffer(str2).reverse().toString();
	}
	
	/**
	 * 创建website首页
	 * @author WKX
	 * @param model
	 * @param requestd
	 * @since 2016年12月15日 下午6:06:31
	 */
	@RequestMapping(value = "/index.htm")
	public String index(Model model,HttpServletRequest requestd){
		Map<String, Object> m = new HashMap<String, Object>();
		CommonHtmlGenerator generator = CommonHtmlGenerator.clone(commonHtmlGenerator);
		
		Map<String, Object> result = getJye();
		model.addAttribute("month", reverse(result.get("month").toString()));
		model.addAttribute("all",  reverse(result.get("all").toString()));
		//bannner
		List<Appimage> banners = picmanageService.getPicList("index_pc");
		model.addAttribute("banners", banners);
		
		//shibor查询
		List<Shibor> shibors = shiborService.getList(0, 1);
		model.addAttribute("shibor", shibors.get(0));
		//工催
		List<Gongcui> gongcuis = gongcuiService.getList(0, 5);
		model.addAttribute("gongcuiList", gongcuis);
		
		//第一条图片新闻
		SiteContent siteContent = siteContentService.get();
		if(siteContent!=null && StringUtils.isNotBlank(siteContent.getAbstracts()) && siteContent.getAbstracts().length()>=50){
			siteContent.setAbstracts(siteContent.getAbstracts().substring(0,50)+"...");
		}
		model.addAttribute("news", siteContent);
		
		//市场信息
		List<News> newslist = newsService.getList(0, 3);
		List<News> NewsList = new ArrayList<News>();
		if(!(newslist == null || newslist.size() == 0)){
			for(News news : newslist){					
				String title = news.getTitle();
				String content = news.getContent();
				news.setContentShow(content);
				news.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(news.getPublishtime()));
				if(title!=null) news.setTitleShow(title.length()>30?title.substring(0,30)+"...":title);
				NewsList.add(news);
			}
		}
		model.addAttribute("newsList", NewsList);
		try {
			//generator.setContextRealPath(PropertiesUtil.wwwroot);
			generator.setOutputPath(Constant.properties.getProperty("websiteRootPath"));//此路径和ContextRealPath 只要一个完整配置就可以了
			generator.setOutputFileName("index.html");
			generator.setTemplateFileName("/website/index.ftl");
			generator.generate(model.asMap());
			m.put("message", "Html is Generated Successfully！");
		} catch (Exception e) {
			e.printStackTrace();
			m.put("message", "Html is Generated Failed！");
		}
		model.addAttribute("retValue", JacksonUtil.objWriteStr(m));
		return "ajax";
	}
	
	/**
	 * 新闻详情页面（创建页面）PC官网
	 * @author WKX
	 * @param id 主键
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param model
	 * @throws Exception
	 * @since 2016年12月16日 下午3:30:03
	 */
	@RequestMapping("/news.htm")
	public String info(Integer id,String start,String end,Model model) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		CommonHtmlGenerator generator = CommonHtmlGenerator.clone(commonHtmlGenerator);
		NewsForm nf = new NewsForm();
		if(id!=null)nf.setId(id);
		if(StringUtils.isNotBlank(start)){
			nf.setBeginDate(DateUtil.parser(start, DateUtil.FORMART3));
		}
		if(StringUtils.isNotBlank(end)){
			nf.setBeginDate(DateUtil.parser(end, DateUtil.FORMART3));
		}
		List<News> list = newsService.getList(nf);
		if(list!=null && list.size()>0){
			for(News news:list){
				if(news!=null){
					if (news.getPic1()!=null) {				
						news.setPic1(Constant.properties.getProperty("preimgUrl")+news.getPic1());				
					}
					if (news.getPublishtime() != null) {
						news.setPublishtimeStr(new SimpleDateFormat("yyyy-MM-dd").format(news.getPublishtime()));
					}
					model.addAttribute("news", news);
				}
				try {
					this.createNews(generator, news, model);
					result.put("message", "Html is Generated Successfully！" + "（"+ list.size() +"）");
				} catch (Exception e) {
					e.printStackTrace();
					result.put("message", "Html is Generated Failed！");
				}
			}
		}
		model.addAttribute("retValue", JacksonUtil.objWriteStr(result));
		return "ajax";
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