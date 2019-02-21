package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Sitestatistics;


public interface SitestatisticsService {
	
	void emailData();
	/**
	 * 
	* @Title: emailSiteData
	* @Description: 每天发邮件网站的流量
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	void emailSiteData();
	
	
	public void saveSitestatistics(Sitestatistics s);
	
	/**
	 * 获取hezuo是pinyou的相关统计数据
	 * @author ZY
	 * @param date
	 * 2016年10月9日上午11:02:01
	 */
	public List<Map<String,Object>> getPinyouCountByDay(String date);
}
