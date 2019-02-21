package com.ry.rycms.task;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ry.core.service.NewsCrawlerService;

@Component
public class GongcuiTask {
	private static Logger logger = Logger.getLogger(GongcuiTask.class);
	
	@Resource
	NewsCrawlerService newsCrawlerService;

	public void execute() {
		//"http://www.live.chinacourt.org/fygg.shtml",
		//"http://rmfygg.court.gov.cn/psca/lgnot/bulletin/公示催告_0_0.html"
		try {
			String urls [] = {"http://rmfygg.court.gov.cn/psca/lgnot/bulletin/公示催告_0_0.html",
					"http://www.live.chinacourt.org/fygg.shtml"};
			String articleSource[]={
					"中国法院网","人民法院公告网"
			};
			for (int i = 0; i < urls.length; i++) {
				newsCrawlerService.savepiaojuAll(urls[i], null, null,articleSource[i]);
				Thread.sleep(300);
			}
			for(int i = 1;i<10;i++){
				newsCrawlerService.savepiaojuAll("http://rmfygg.court.gov.cn/psca/lgnot/bulletin/公示催告_0_"+i+".html", null, null,"人民法院公告网");
				Thread.sleep(300);
			}
		} catch (Exception e) {
			logger.error("爬虫网站新闻异常："+e.getMessage());
			e.printStackTrace();
		}
	}
}
