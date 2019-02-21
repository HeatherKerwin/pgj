package com.ry.rycms.task;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ry.core.entity.Appimage;
import com.ry.core.entity.Gongcui;
import com.ry.core.entity.News;
import com.ry.core.entity.Shibor;
import com.ry.core.entity.SiteContent;
import com.ry.core.service.DiscountrecordService;
import com.ry.core.service.GongcuiService;
import com.ry.core.service.NewsService;
import com.ry.core.service.PicmanageService;
import com.ry.core.service.ShiborService;
import com.ry.core.service.SiteContentService;
import com.ry.core.service.VariablesService;
import com.ry.core.util.Constant;
import com.ry.util.DateUtil;
import com.ry.web.html.CommonHtmlGenerator;

/**
 * 定时任务（生成PC首页）
 * @author WKX
 */
public class StaticTask{
	
	private static Logger logger = Logger.getLogger(StaticTask.class);
	
	@Autowired
	CommonHtmlGenerator commonHtmlGenerator;
	
	@Resource					
	NewsService newsService;
	
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
	
	public void execute(){
		logger.info("index generate beginning！");
		CommonHtmlGenerator generator = CommonHtmlGenerator.clone(commonHtmlGenerator);
		Map<Object,Object> model = new HashMap<Object,Object>();
		
		Map<String, Object> result = getJye();
		model.put("month", reverse(result.get("month").toString()));
		model.put("all",  reverse(result.get("all").toString()));
		//bannner
		List<Appimage> banners = picmanageService.getPicList("index_pc");
		model.put("banners", banners);
		
		//shibor查询
		List<Shibor> shibors = shiborService.getList(0, 1);
		model.put("shibor", shibors.get(0));
		//工催
		List<Gongcui> gongcuis = gongcuiService.getList(0, 5);
		model.put("gongcuiList", gongcuis);
		
		//第一条图片新闻
		SiteContent siteContent = siteContentService.get();
		if(siteContent!=null && StringUtils.isNotBlank(siteContent.getAbstracts()) && siteContent.getAbstracts().length()>=50){
			siteContent.setAbstracts(siteContent.getAbstracts().substring(0,50)+"...");
		}
		model.put("news", siteContent);
		
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
		model.put("newsList", NewsList);
		try {
			generator.setOutputPath(Constant.properties.getProperty("websiteRootPath"));//此路径和ContextRealPath 只要一个完整配置就可以了
			generator.setOutputFileName("index.html");
			generator.setTemplateFileName("/website/index.ftl");
			generator.generate(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("[定时任务-失败]：生成PC端首页静态文件出错。");
		}
		logger.info("index generate end！");
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
}
