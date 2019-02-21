/**
 *
 */
package com.ry.rycms.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.service.MemberService;
import com.ry.core.util.Constant;
import com.ry.rycms.util.EmailUtil;
import com.ry.util.DateUtil;

import freemarker.template.Configuration;

/**
 * @author MH
 * 导出最近两个月的推广
 */
public class MumberTask {
	
	private static Logger logger = Logger.getLogger(MumberTask.class);
	
	@Resource
	MemberService memberservice;
	
	@Resource
	private Configuration configuration;

	public void execute() throws ParseException {
		
		GregorianCalendar g = new GregorianCalendar();
		g.setTime(new Date());
		if(g.get(Calendar.WEEK_OF_YEAR)%2 == 0){
			String sendemail = Constant.properties.getProperty("OPERATIVEEMAIL");
			String operate = Constant.properties.getProperty("operateNums");
			
			logger.info("------------------递推数据统计定时任务执行begin------------------");
			
			String endtime = DateUtil.formart(new Date(),DateUtil.FORMART3);
			String endtime1 = endtime + " 00:00:00" ;
			//获取前61天的时间
			Date begint = DateUtil.addDays(-61);
			String beginttime = DateUtil.formart(begint,DateUtil.FORMART3);
			String beginttime1 = beginttime + " 00:00:00" ;
			
			List<Map<String, Object>> listmember = memberservice.getGroomList(beginttime1, endtime1,operate);
			
			List<Object> dataList = new ArrayList<Object>();
			Map<String, Object> maps = new HashMap<String, Object>();
			for (Map<String, Object> map : listmember) {
				map.put("date",DateUtil.formart(new Date(), DateUtil.FORMART3));
				map.put("range",DateUtil.formart(begint, DateUtil.FORMART3)+"至"+DateUtil.formart(DateUtil.addDays(-1), DateUtil.FORMART3));
				dataList.add(map);
			}
			maps.put("groom", dataList);
			List<String> targetPerson = new ArrayList<String>();
			targetPerson.add(sendemail);
			String curDay = DateUtil.formart(new Date(), DateUtil.FORMART3);
			String html = EmailUtil.getHtml(configuration, "/emailtemplate/tuijian.ftl", maps);
			EmailUtil.sendEmail(targetPerson, "递推推荐统计" + curDay, html);
			
			logger.info("------------------递推数据统计定时任务执行end------------------");
		}
		
	}
}
