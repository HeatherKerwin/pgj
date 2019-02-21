package com.ry.rycms.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ry.core.service.ServicememberService;
import com.ry.core.service.SitestatisticsService;

@Component
public class EmailTask {
	@Resource
	SitestatisticsService sitestatisticsService;
	@Resource
	ServicememberService servicememberService;
	
	
	public void cooperation(){
		sitestatisticsService.emailSiteData();
	}
	
	public void saleData(){
		servicememberService.emailSaleData();
	}
}
