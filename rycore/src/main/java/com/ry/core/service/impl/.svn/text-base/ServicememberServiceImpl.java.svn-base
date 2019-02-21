/**
* @Title: ServicememberServiceImpl.java
* @Package com.ry.core.service.impl
* @Description: TODO
* @author Ry-wk
* @date 2015年10月16日
* @version V1.0
*/
package com.ry.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.ActivecountDao;
import com.ry.core.dao.MemberDao;
import com.ry.core.dao.ServicememberDao;
import com.ry.core.dao.SitestatisticsDao;
import com.ry.core.entity.Servicemember;
import com.ry.core.service.ServicememberService;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;
import com.ry.util.page.PageResults;

import freemarker.template.Configuration;

/**
 * @ClassName: ServicememberServiceImpl
 * @Description: TODO
 * @author Ry-wk
 * @date 2015年10月16日
 *
 */
@Service
public class ServicememberServiceImpl implements ServicememberService{
	
	private static Logger logger = Logger.getLogger(ServicememberServiceImpl.class);
	
	@Resource
	ServicememberDao servicememberDao;
	
	@Resource
	ActivecountDao activecountDao;
	
	@Resource
	SitestatisticsDao sitestatisticsDao;
	@Resource
	MemberDao memberDao;
	@Value("${saleNums}") 
	String saleNums;
	@Value("${saleDataEmail}") 
	String saleDataEmail;
	@Resource
	Configuration freemarkerConfiguration;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#saveServicemember(com.ry.core.entity.Servicemember)
	 */
	@Override
	public Integer saveServicemember(Servicemember s) {
		return servicememberDao.saveServicemember(s);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#getPageList(com.ry.core.entity.Servicemember, int, int)
	 */
	@Override
	public PageResults<Servicemember> getPageList(Servicemember sm, int pageNo,
			int pageSize) {
		PageResults<Servicemember> pr =null;
//		try {
//			pr = servicememberDao.pageList(sm, pageNo, pageSize);
//			Long nowtime = System.currentTimeMillis();
//			SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM");
//			Long begintime1 = dateFormat2.parse(dateFormat1.format(new Date(nowtime))+" 00:00:00").getTime();
//			Long endtime1 = dateFormat2.parse(dateFormat1.format(new Date(nowtime))+" 23:59:59").getTime();
//			Long begintime2 = dateFormat2.parse(dateFormat3.format(new Date(nowtime))+"-01 00:00:00").getTime();
//			Long endtime2 = dateFormat2.parse(dateFormat1.format(new Date(nowtime))+" 23:59:59").getTime();
//			List<Servicemember> servicememberList = pr.getResults();
//			if(!(servicememberList == null || servicememberList.size() == 0)){
//				//获取前月的最后一天
//			    Calendar cale = Calendar.getInstance();   
//			    cale.set(Calendar.DAY_OF_MONTH,0);
//			    Long lastMonth = dateFormat2.parse(dateFormat1.format(cale.getTime())+" 23:59:59").getTime();    	        
//				List<Servicemember> targetList = new ArrayList<Servicemember>();
//				for(Servicemember servicemember : servicememberList){
//					Servicemember smember = servicemember;
//					String rp =  smember.getServicenumber();
//					Integer daycount = activecountDao.getCountByRpAndTime(rp, begintime1,endtime1).intValue();
//					Integer monthcount = activecountDao.getCountByRpAndTime(rp, begintime2,endtime2).intValue();
//					Integer allcount = activecountDao.getCountByRpAndTime(rp, null,null).intValue();
//					MemberForm mf = new MemberForm();
//					mf.setRecommendpeople(rp);
//					Integer membercount = memberDao.countMember(mf).intValue();
//					mf.setBeginRegDate(lastMonth);
//					Integer addmembercount = memberDao.countMember(mf).intValue();
//					smember.setAllcount(allcount);
//					smember.setDaycount(daycount);
//					smember.setMonthcount(monthcount);
//					smember.setMembercount(membercount);
//					smember.setAddmembercount(addmembercount);
//					targetList.add(servicemember);
//				}
//				pr.setResults(targetList);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		pr = servicememberDao.pageList(sm, pageNo, pageSize);
		
		
		return pr;
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#removeServicemember(java.lang.Integer)
	 */
	@Override
	public void removeServicemember(Integer id) {
		Servicemember servicemember = servicememberDao.getById(id);
		servicemember.setFlag(1);
		servicememberDao.updateServicemember(servicemember);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#modifyServicemember(com.ry.core.entity.Servicemember)
	 */
	@Override
	public void modifyServicemember(Servicemember s) {
		servicememberDao.updateServicemember(s);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#getServicememberById(java.lang.Integer)
	 */
	@Override
	public Servicemember getServicememberById(Integer id) {
		return servicememberDao.getById(id);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#emailSaleData()
	 */
	@Override
	public void emailSaleData() {
		logger.info("------------------销售数据统计定时任务执行begin------------------");
		if(StringUtils.hasText(saleNums)||StringUtils.hasText(saleDataEmail)){
			Date now = Calendar.getInstance().getTime();
			Map<String, Object> map = new HashMap<String, Object>();
			//统计数据
			String begtime = DateUtil.formart(DateUtil.addDays(-1), DateUtil.FORMART3);
			String endtime = DateUtil.formart(new Date(), DateUtil.FORMART3);
			String date1 = begtime + " 17:30:00"; 
			String date2 = endtime + " 17:30:00"; 
			List<Map<String,Object>> datalist = servicememberDao.recommendEmail(date1,date2,saleNums,null,null,0,0);
			List<Object> datalist2 = new ArrayList<Object>();
			String a = "101";
			String b = "1204";
			String c = "1205";
			String d = "1206";
			Map<String, Object> maps = new HashMap<String, Object>();
			for (Map<String, Object> map2 : datalist) {
				maps = map2;
				maps.put("start", DateUtil.formart(new Date(), DateUtil.FORMART3));
				datalist2.add(maps);
				if(a.equals(String.valueOf(map2.get("recommendpeople")))){
					a="";
				}else if(b.equals(String.valueOf(map2.get("recommendpeople")))){
					b="";
				}else if(c.equals(String.valueOf(map2.get("recommendpeople")))){
					c="";
				}else if(d.equals(String.valueOf(map2.get("recommendpeople")))){
					d="";
				}
			}
			//对没有的数据填充
			if(a!=""){
				maps = new HashMap<String, Object>();
				maps.put("start", DateUtil.formart(new Date(), DateUtil.FORMART3));
				maps.put("recommendpeople", a);
				maps.put("num", 0);
				datalist2.add(maps);
			}
			if(b!=""){
				maps = new HashMap<String, Object>();
				maps.put("start", DateUtil.formart(new Date(), DateUtil.FORMART3));
				maps.put("recommendpeople", b);
				maps.put("num", 0);
				datalist2.add(maps);
			}
			if(c!=""){
				maps = new HashMap<String, Object>();
				maps.put("start", DateUtil.formart(new Date(), DateUtil.FORMART3));
				maps.put("recommendpeople", c);
				maps.put("num", 0);
				datalist2.add(maps);
			}
			if(d!=""){
				maps = new HashMap<String, Object>();
				maps.put("start", DateUtil.formart(new Date(), DateUtil.FORMART3));
				maps.put("recommendpeople", d);
				maps.put("num", 0);
				datalist2.add(maps);
			}
			
			//周统计
			Calendar cal=Calendar.getInstance();
			int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		    List<Object> datalistweek = new ArrayList<Object>();
		    if(week == 1){
		    	String endtime1 = DateUtil.formart(new Date(), DateUtil.FORMART3);
		    	String weekendtime = endtime1 + " 00:00:00"; 
		    	String begtime1 = DateUtil.formart(DateUtil.addDays(-7), DateUtil.FORMART3);
				String weekbegtime = begtime1 + " 00:00:00"; 
				List<Map<String,Object>> datalist3 = servicememberDao.recommendEmail(null,null,saleNums,weekbegtime,weekendtime,0,0);
				 a = "101";
				 b = "1204";
				 c = "1205";
				 d = "1206";
				Map<String, Object> mapsweek = new HashMap<String, Object>();
				for (Map<String, Object> map2 : datalist3) {
					mapsweek = map2;
					mapsweek.put("start", begtime1+"至"+endtime1);
					datalistweek.add(mapsweek);
					if(a.equals(String.valueOf(map2.get("recommendpeople")))){
						a="";
					}else if(b.equals(String.valueOf(map2.get("recommendpeople")))){
						b="";
					}else if(c.equals(String.valueOf(map2.get("recommendpeople")))){
						c="";
					}else if(d.equals(String.valueOf(map2.get("recommendpeople")))){
						d="";
					}
				}
				//对没有的数据填充
				if(a!=""){
					mapsweek = new HashMap<String, Object>();
					mapsweek.put("start",begtime1+"至"+endtime1);
					mapsweek.put("recommendpeople", a);
					mapsweek.put("num", 0);
					datalistweek.add(mapsweek);
				}
				if(b!=""){
					mapsweek = new HashMap<String, Object>();
					mapsweek.put("start", begtime1+"至"+endtime1);
					mapsweek.put("recommendpeople", b);
					mapsweek.put("num", 0);
					datalistweek.add(mapsweek);
				}
				if(c!=""){
					mapsweek = new HashMap<String, Object>();
					mapsweek.put("start", begtime1+"至"+endtime1);
					mapsweek.put("recommendpeople", c);
					mapsweek.put("num", 0);
					datalistweek.add(mapsweek);
				}
				if(d!=""){
					mapsweek = new HashMap<String, Object>();
					mapsweek.put("start", begtime1+"至"+endtime1);
					mapsweek.put("recommendpeople", d);
					mapsweek.put("num", 0);
					datalistweek.add(mapsweek);
				}
				map.put("datalistweek", datalistweek);
		    }
		    
			//月统计
		    int datenum=cal.get(Calendar.DATE);
			Date monthendtime = DateUtil.addDays(-1);
			cal.setTime(monthendtime);
			int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH) + 1; // 0-based!
		    List<Object> datalistmonth = new ArrayList<Object>();
			if(datenum == 1){
				List<Map<String,Object>> datalist4 = servicememberDao.recommendEmail(null,null,saleNums,null,null,year,month);
				 a = "101";
				 b = "1204";
				 c = "1205";
				 d = "1206";
				Map<String, Object> mapsmonth = new HashMap<String, Object>();
				for (Map<String, Object> map2 : datalist4) {
					mapsmonth = map2;
					mapsmonth.put("start", year+"年"+month+"月");
					datalistmonth.add(mapsmonth);
					if(a.equals(String.valueOf(map2.get("recommendpeople")))){
						a="";
					}else if(b.equals(String.valueOf(map2.get("recommendpeople")))){
						b="";
					}else if(c.equals(String.valueOf(map2.get("recommendpeople")))){
						c="";
					}else if(d.equals(String.valueOf(map2.get("recommendpeople")))){
						d="";
					}
				}
				//对没有的数据填充
				if(a!=""){
					mapsmonth = new HashMap<String, Object>();
					mapsmonth.put("start", year+"年"+month+"月");
					mapsmonth.put("recommendpeople", a);
					mapsmonth.put("num", 0);
					datalistmonth.add(mapsmonth);
				}
				if(b!=""){
					mapsmonth = new HashMap<String, Object>();
					mapsmonth.put("start",year+"年"+month+"月");
					mapsmonth.put("recommendpeople", b);
					mapsmonth.put("num", 0);
					datalistmonth.add(mapsmonth);
				}
				if(c!=""){
					mapsmonth = new HashMap<String, Object>();
					mapsmonth.put("start", year+"年"+month+"月");
					mapsmonth.put("recommendpeople", c);
					mapsmonth.put("num", 0);
					datalistmonth.add(mapsmonth);
				}
				if(d!=""){
					mapsmonth = new HashMap<String, Object>();
					mapsmonth.put("start", year+"年"+month+"月");
					mapsmonth.put("recommendpeople", d);
					mapsmonth.put("num", 0);
					datalistmonth.add(mapsmonth);
				}
				map.put("datalistmonth", datalistmonth);
			}
			map.put("datalist", datalist2);
			String curDay = DateUtil.formart(now, DateUtil.FORMART3);
			String html = EmailUtil.getHtml(freemarkerConfiguration, "/emailtemplate/saleCount.ftl", map);
			List<String> targetPerson = Arrays.asList(saleDataEmail.split(","));
			EmailUtil.sendEmail(targetPerson, "销售数据统计 "+curDay, html);
			logger.info("------------------销售数据统计定时任务执行end------------------");
		}
	}


	@Override
	public Servicemember getServicemember(String servicenumber) {
		
		return servicememberDao.getServicemember(servicenumber);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#saleLivenessData(java.lang.String)
	 */
	@Override
	public List saleLivenessData(String type) {
		return servicememberDao.saleLivenessData(type);
	}


	/* (non-Javadoc)
	 * @see com.ry.core.service.ServicememberService#saleCustomCount(java.lang.String)
	 */
	@Override
	public List saleCustomCount(String type) {
		return servicememberDao.saleCustomCount(type);
	}

	public List<Servicemember> getList(Servicemember sm) {
		return servicememberDao.getList(sm);
	}
}
