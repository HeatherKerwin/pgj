package com.ry.core.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.ActionlogDao;
import com.ry.core.dao.ClickCountDao;
import com.ry.core.dao.SitestatisticsDao;
import com.ry.core.entity.Actionlog;
import com.ry.core.entity.ClickCount;
import com.ry.core.entity.Sitestatistics;
import com.ry.core.service.SitestatisticsService;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;
import com.ry.util.PropertiesUtil;

import freemarker.template.Configuration;
@Service
public class SitestatisticsServiceImpl implements SitestatisticsService{
	
	@Resource
	SitestatisticsDao sitestatisticsDao;
	
	String coopStr;
	
	String coopEmailAddress;
	
	String siteEmailAddress;
	
	@Resource
	Configuration freemarkerConfiguration;
	
	@Resource
	ActionlogDao actionlogDao;
	
	@Resource
	ClickCountDao clickCountDao;
	
	@Override
	public void emailData() {
		coopStr = PropertiesUtil.getConfigPropertiesValue("coopStr", null);
		coopEmailAddress = PropertiesUtil.getConfigPropertiesValue("coopEmailAddress", null);
		
		if(StringUtils.hasText(coopStr)||StringUtils.hasText(coopEmailAddress)){
			Map map = new HashMap();
			//总数据
			List list = sitestatisticsDao.getListCountByDay(null);
			//合作统计数据
			List clist = sitestatisticsDao.getListCountByDayAndCooperation(null,coopStr);
			map.put("total", list.get(0));
			map.put("clist", clist);
			Date yesd = DateUtil.addDays(-1);
			map.put("dataDate", yesd);
			String html = EmailUtil.getHtml(freemarkerConfiguration, "/emailtemplate/coopCount.ftl", map);
			List<String> targetPerson = Arrays.asList(coopEmailAddress.split(","));
			EmailUtil.sendEmail(targetPerson, "合作方数据统计 "+DateUtil.formart(yesd, DateUtil.FORMART3), html);
		}
	}
	
	@Override
	public void saveSitestatistics(Sitestatistics s) {
		sitestatisticsDao.addSitestatistics(s);;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.SitestatisticsService#emailSiteData()
	 */
	@Override
	public void emailSiteData() {
		
		siteEmailAddress = PropertiesUtil.getConfigPropertiesValue("siteEmailAddress", null);
		
		Map map = new HashMap();
		//总数据
		List list = sitestatisticsDao.getListCountByDay(null);
		
		Date start = DateUtil.addDays(-1);
		Date end = DateUtil.getStartDate(new Date());
		
		//APP总数据
		List<Actionlog> listA = actionlogDao.getListCountByDay(start, end,"APP");
		
		//WAp总数据
		List<ClickCount> lictC = clickCountDao.getListCountByDay(start, end, null);
	
		map.put("total", list.get(0));
		if(listA.size()>0){
			map.put("totalA", listA.get(0));
		}
		if(lictC.size()>0){
			map.put("totalC", lictC.get(0));
		}
		
		map.put("total", list.get(0));
		Date yesd = DateUtil.addDays(-1);
		map.put("dataDate", yesd);
		String html = EmailUtil.getHtml(freemarkerConfiguration, "/emailtemplate/siteCount.ftl", map);
		List<String> targetPerson = Arrays.asList(siteEmailAddress.split(","));
		EmailUtil.sendEmail(targetPerson, "网站流量数据统计 "+DateUtil.formart(yesd, DateUtil.FORMART3), html);
		
	}
	
	@Override
	public List<Map<String,Object>> getPinyouCountByDay(String date) { 
		return sitestatisticsDao.getPinyouCountByDay(date);
	}
}