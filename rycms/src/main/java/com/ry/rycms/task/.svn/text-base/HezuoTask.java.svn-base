package com.ry.rycms.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ry.core.form.MemberForm;
import com.ry.core.service.MemberService;
import com.ry.core.service.PhonedetailService;
import com.ry.core.service.SitestatisticsService;
import com.ry.core.service.TagService;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;
import com.ry.util.PropertiesUtil;

import freemarker.template.Configuration;

/**
 * 定时发送邮件（根据hezuo字段查询新增用户）
 * @author RY
 */
public class HezuoTask {

	private static Logger logger = Logger.getLogger(HezuoTask.class);

	@Resource
	private MemberService memberService;
	
	@Resource
	private PhonedetailService phonedetailService;
	
	@Resource
	private TagService tagService;
	
	@Resource
	private SitestatisticsService sitestatisticsService;

	@Resource
	Configuration freemarkerConfiguration;

	public void execute() {
		String sendemail = PropertiesUtil.getConfigPropertiesValue("SENDEMAIL", "");
		String hezuo = PropertiesUtil.getConfigPropertiesValue("HEZUO", "");
		
		String qudao = PropertiesUtil.getConfigPropertiesValue("QUDAO", "");
		logger.info("------------------推广注册数据统计定时任务执行begin------合作："+hezuo+",sendemail:"+sendemail+"------------");
		String qudaophone = PropertiesUtil.getConfigPropertiesValue("QUDAOPHONE", "");
		if (StringUtils.isNotBlank(hezuo)) {
			List<String> hezuos = Arrays.asList(hezuo.split(","));
			List<String> qudaos = Arrays.asList(qudao.split(","));
			List<String> qudaophones = Arrays.asList(qudaophone.split(","));
			if (hezuos != null && hezuos.size() > 0 && qudaos != null && qudaos.size() > 0 && qudaophones != null && qudaophones.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();

				MemberForm form = new MemberForm();
				MemberForm form1 = new MemberForm();
				Calendar c = Calendar.getInstance();

				// 设置当前一天的时间
				c.setTime(new Date());
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				c.add(Calendar.DATE, -1);
				form1.setBeginRegDate(c.getTime());

				c.set(Calendar.HOUR_OF_DAY, 23);
				c.set(Calendar.MINUTE, 59);
				c.set(Calendar.SECOND, 59);
				form1.setEndRegDate(c.getTime());
				c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
				form.setEndRegDate(c.getTime());
				c.add(Calendar.MONTH, 0);
				c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
				c.set(Calendar.HOUR_OF_DAY, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
				form.setBeginRegDate(c.getTime());
				List<Object> list = new ArrayList<Object>();
				List<Object> list2 = new ArrayList<Object>();
				Map<String, Object> m = null;

				for (String qd : qudaos) {
					for (String hz : hezuos) {
						form.setQudao(qd);
						form1.setQudao(qd);
						form.setHezuo(hz);
						form1.setHezuo(hz);
						Long temp_m = memberService.count(form);// 月
						Long temp_d = memberService.count(form1);// 日

						m = new HashMap<String, Object>();
						m.put("hz", hz);
						m.put("qd", qd);
						m.put("m", temp_m);
						m.put("d", temp_d);
						list.add(m);
					}
				}
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE,-1);
				Date date=calendar.getTime();
				String createDate=DateUtil.formart(date, DateUtil.FORMART3);
				String createMonth=DateUtil.formart(date, DateUtil.FORMART4);
				
				for(String qdp : qudaophones){
					Long time_d=phonedetailService.countByQudaoandDay(qdp,createDate);
					Long time_m=phonedetailService.countByQudaoandMonth(qdp,createMonth);
					String name=tagService.getById(Integer.valueOf(qdp)).getName();
					m = new HashMap<String, Object>();
					m.put("hz", name);
					m.put("qd", qdp);
					m.put("m", time_m);
					m.put("d", time_d);
					list.add(m);
				}
				
				map.put("hezuo", list);
				
				String curDay = DateUtil.formart(DateUtil.addDays(-1), DateUtil.FORMART3);
				List<Map<String,Object>> list1 = sitestatisticsService.getPinyouCountByDay(curDay);
				if(list1 != null && list1.get(0)!= null){
					m = new HashMap<String, Object>();
					m.put("fangwen",list1.get(0).get("countvisit"));
					m.put("yonghu", list1.get(0).get("countip"));
					list2.add(m);
					map.put("total", list2);
				}
				Date yesd = DateUtil.addDays(-1);
				map.put("dataDate", yesd);
				
				String html = EmailUtil.getHtml(freemarkerConfiguration, "/emailtemplate/hezuo.ftl", map);
				System.err.println(map);
				List<String> targetPerson = Arrays.asList(sendemail.split(","));
				EmailUtil.sendEmail(targetPerson, "合作数据统计 " + curDay, html);
			}
		}
		logger.info("------------------推广注册数据统计定时任务执行end------------------");
	}

}