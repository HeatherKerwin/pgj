package com.ry.rycms.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.service.NewsCrawlerService;

public class NewsCrawlerTask {
	
	private static Logger logger = Logger.getLogger(NewsCrawlerTask.class);
	
	@Resource
	private NewsCrawlerService newsCrawlerService;
	
	public void execute(){
		logger.info("------------------定时任务:爬虫网站新闻------------------");
		try {
			String urls [] = {"http://www.yicai.com/news/",
					  "http://finance.qq.com/gdyw.htm",
					  "http://www.bbtnews.com.cn/news/",
					  "http://business.sohu.com/business_scrollnews.shtml",
					  "http://finance.21cn.com/newsdoc/zx/",
					  "http://www.jjckb.cn/yw.htm",
					  "http://finance.ce.cn/rolling/index.shtml",
					  "http://news.cnstock.com/news/sns_yw/index.html",
					  "http://www.thepaper.cn/index_masonry.jsp",
					  "http://finance.eastmoney.com/news/ccjdd.html"};
			String articleSource[]={
					"第一财经","腾讯财经","北京商报网","搜狐财经","21世纪经济报道","经济参考网","中国经济网","上海证券报","澎湃新闻","东方财富网"
			};
			for (int i = 0; i < urls.length; i++) {
				newsCrawlerService.saveAll(urls[i], null, null,articleSource[i]);
				Thread.sleep(300);
			}
		} catch (Exception e) {
			logger.error("爬虫网站新闻异常："+e.getMessage());
			e.printStackTrace();
		}
	}
}
