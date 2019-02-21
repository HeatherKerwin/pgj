package com.ry.rycms.task;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.ry.core.service.appmanage.ActiveByFunctionService;
import com.ry.core.service.appmanage.ActiveByQudaoService;
import com.ry.core.service.appmanage.ActiveByTuiGuangService;
import com.ry.core.service.appmanage.ActiveDayByQudaoService;
import com.ry.core.service.appmanage.ActiveRatioByQudaoService;
import com.ry.core.service.appmanage.ActiveRatioByScaleService;
import com.ry.core.service.appmanage.CountButtonService;
import com.ry.core.service.appmanage.CountIpByHourService;
import com.ry.core.service.appmanage.CountIpService;
import com.ry.core.service.appmanage.CountPvByHourService;
import com.ry.core.service.appmanage.CountUvByHourService;
import com.ry.core.service.appmanage.CountUvService;
import com.ry.core.service.appmanage.IncreaseUserByFunctionService;
import com.ry.core.service.appmanage.IncreaseUserByQudaoService;
import com.ry.core.service.appmanage.IncreaseUserByTuiGuangService;
import com.ry.core.service.appmanage.RemainMonthByFunctionService;
import com.ry.core.service.appmanage.RemainMonthByQudaoService;
import com.ry.core.service.appmanage.RemainMonthByScaleService;
import com.ry.core.service.appmanage.RemainWeekByFunctionService;
import com.ry.core.service.appmanage.RemainWeekByQudaoService;
import com.ry.core.service.appmanage.RemainWeekByScaleService;
import com.ry.util.DateUtil;
import com.ry.util.EmailUtil;

import freemarker.template.Configuration;

public class DataananChangeTask {

	private static Logger logger = Logger.getLogger(DataananChangeTask.class);
	
	@Resource
	private ActiveByFunctionService activeByFunctionService;
	
	@Resource
	private ActiveDayByQudaoService activeDayByQudaoService;
	
	@Resource
	private ActiveByQudaoService activeByQudaoService;
	
	@Resource
	private ActiveRatioByQudaoService activeRatioByQudaoService;
	
	@Resource
	private ActiveRatioByScaleService activeRatioByScaleService;
	
	@Resource
	private IncreaseUserByTuiGuangService increaseUserByTuiGuangService;
	
	@Resource
	private ActiveByTuiGuangService activeByTuiGuangService;
	
	@Resource
	private CountPvByHourService countPvByHourService;
	
	@Resource
	private CountIpByHourService countIpByHourService;
	
	@Resource
	private CountUvByHourService countUvByHourService;
	
	@Resource
	private CountIpService countIpService;
	
	@Resource
	private CountUvService countUvService;
	
	@Resource
	private CountButtonService countButtonService;
	
	@Resource
	private IncreaseUserByFunctionService increaseUserFunctionService;
	
	@Resource
	private IncreaseUserByQudaoService increaseUserByQudaoService;
	
	@Resource
	private RemainWeekByFunctionService remainWeekByFunctionService;
	
	@Resource
	private RemainMonthByFunctionService remainMonthByFunctionService;
	
	@Resource
	private RemainWeekByQudaoService remainWeekByQudaoService;
	
	@Resource
	private RemainMonthByQudaoService remainMonthByQudaoService;
	
	@Resource
	private RemainWeekByScaleService remainWeekByScaleService;
	
	@Resource
	private RemainMonthByScaleService remainMonthByScaleService;
	
	@Resource
	Configuration freemarkerConfiguration;
	
	//邮件发送人列表
	List<String> targetPerson = Arrays.asList("wangkaixuan@ryfinance.com","lvyanqin@ryfinance.com");
	
	/**
	 * 分析程序执行时间
	 * @param now,执行任务时的当前时间{
	 * @param info,发送邮件内容，提示错误
	 * @date 2016年2月26日
	 * @author lvyanqin
	 */
	private boolean alarm(Date now, String info){
		int hour = now.getHours();
		if(hour <= 24 && hour >= 20){//标识是在凌晨之前执行
			return true;
		}else{//发送邮件，第二天人工执行
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("info", info);
			String html = EmailUtil.getHtml(freemarkerConfiguration, "/emailtemplate/timeTask.ftl", map);
			EmailUtil.sendEmail(targetPerson,"app后台管理系统-定时任务",html);
			logger.info("-------------------<" + info + ">定时任务执行失败-----------------");
			return false;
		}
		
	}
	
	public void executeDay(){
		Date now = new Date();
		if(!alarm(now, "日")){
			return;
		}
		try {
			Date start = new Date();
			logger.info("------------------DataananChangeTask-Day定时任务》》执行 " + com.ry.util.DateUtil.formart(start, com.ry.util.DateUtil.FORMART)+"  ------------------");
			/*Date nowTime = new Date(System.currentTimeMillis());
			SimpleDateFormat sdFormatter = new SimpleDateFormat("HH:mm:ss");
			System.out.println(sdFormatter.format(nowTime));*/
			//渠道新增
			if(!increaseUserByQudaoService.count1(now, now)){
				increaseUserByQudaoService.countIncreaseUserByQudao(now, now);
			}
			//功能日活跃
			activeByFunctionService.countActiveByFunction("2",now, now);
			//渠道日活跃
			activeDayByQudaoService.countDay(now, now);
			//推广日新增
			increaseUserByTuiGuangService.countIncreaseUser(2,now, now);
			//推广日活跃
			activeByTuiGuangService.countActive(2,now, now);
			//ip
			countIpService.countIp(2,now, now);
			//uv
			countUvService.countUv(2,now, now);
			//推广特殊按钮
			countButtonService.countButton(2,now, now);
			//功能新增
			increaseUserFunctionService.countIncreaseUserByFlag(2,now, now);
			/*Date nowTime1 = new Date(System.currentTimeMillis());
			System.out.println(sdFormatter.format(nowTime1));*/
			Date end = new Date();
			int mins = DateUtil.minusBetween(start, end);
			logger.info("------------------DataananChangeTask-Day定时任务》》结束 "+com.ry.util.DateUtil.formart(end, com.ry.util.DateUtil.FORMART)+"--总用时："+mins+"----------------");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
	
	public void executeWeek(){
		Date now = new Date();
		if(!alarm(now, "周")){
			return;
		}
		try {
			Date start = new Date();
			logger.info("------------------DataananChangeTask-Week定时任务》》执行 "+com.ry.util.DateUtil.formart(start, com.ry.util.DateUtil.FORMART)+"------------------");
			//防止日定时任务失败，日渠道新增就没有数据，这里就是获取数据
			increaseUserByQudaoService.count1(now, now);
			//功能周活跃
			activeByFunctionService.countActiveByFunction("0", now, now);
			//渠道周活跃
			activeByQudaoService.countActive(now, now);
			//渠道周活跃率
			activeRatioByQudaoService.countActiveRatioWeeks(now, now);
			//规模周活跃率
			activeRatioByScaleService.countActiveRatioWeeks(now, now);
			//推广周新增  
			increaseUserByTuiGuangService.countIncreaseUser(0, now, now);
			//推广周活跃 
			activeByTuiGuangService.countActive(0, now, now);
			//ip
			countIpService.countIp(0, now, now);
			//uv 
			countUvService.countUv(0, now, now);
			//推广特殊按钮
			countButtonService.countButton(0, now, now);
			//功能新增
			increaseUserFunctionService.countIncreaseUserByFlag(0, now, now);
			//功能模块，分析统计周留存信息并入库   @param field 1功能，2推广PC,3推广WAP
			remainWeekByFunctionService.countRemainWeekByFunction("1", now, now);
			remainWeekByFunctionService.countRemainWeekByFunction("2", now, now);
			remainWeekByFunctionService.countRemainWeekByFunction("3", now, now);
			//渠道留存
			remainWeekByQudaoService.countReaminWeekByQudao(now, now);
			//规模留存
			remainWeekByScaleService.countReaminWeekByScale(now, now);
			Date end = new Date();
			int mins = DateUtil.minusBetween(start, end);
			logger.info("------------------DataananChangeTask-Week定时任务》》结束 "+com.ry.util.DateUtil.formart(end, com.ry.util.DateUtil.FORMART)+"--总用时："+mins+"----------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void executeMonth(){
		Date now = new Date();
		if(!alarm(now, "月")){
			return;
		}
		try {
			Date start = new Date();
			logger.info("------------------DataananChangeTask-Month定时任务》》执行 "+com.ry.util.DateUtil.formart(start, com.ry.util.DateUtil.FORMART)+"------------------");
			//防止日定时任务失败，日渠道新增就没有数据，这里就是获取数据
			increaseUserByQudaoService.count1(now, now);
			//功能月活跃
			activeByFunctionService.countActiveByFunction("1", now, now);
			//渠道月活跃
			activeByQudaoService.countActiveMonths(now, now);
			//渠道月活跃率
			activeRatioByQudaoService.countActiveRatioMonths(now, now);
			//规模月活跃率
			activeRatioByScaleService.countActiveRatioMonths(now, now);
			//推广月新增
			increaseUserByTuiGuangService.countIncreaseUser(1, now, now);
			//推广月活跃
			activeByTuiGuangService.countActive(1, now, now);
			//推广特殊按钮
			countButtonService.countButton(1, now, now);
			//功能新增
			increaseUserFunctionService.countIncreaseUserByFlag(1, now, now);
			//功能模块，分析统计月留存信息并入库   @param field 1功能，2推广PC,3推广WAP
			remainMonthByFunctionService.countRemainMonthByFunction("1", now, now);
			remainMonthByFunctionService.countRemainMonthByFunction("2", now, now);
			remainMonthByFunctionService.countRemainMonthByFunction("3", now, now);
			//渠道月留存
			remainMonthByQudaoService.countReaminMonthByQudao(now, now);
			//规模月留存
			remainMonthByScaleService.countReaminMonthByScale(now, now);
			Date end = new Date();
			int mins = DateUtil.minusBetween(start, end);
			logger.info("------------------DataananChangeTask-Month定时任务》》结束 "+com.ry.util.DateUtil.formart(end, com.ry.util.DateUtil.FORMART)+"--总用时："+mins+"----------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void executeHour(){
		Date now = new Date();
		int minute = now.getMinutes();
		if(minute <= 59 && minute >= 50){//小时之前
			
		}else{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("info", "时");
			String html = EmailUtil.getHtml(freemarkerConfiguration, "/emailtemplate/timeTask.ftl", map);
			EmailUtil.sendEmail(targetPerson,"app后台管理系统-定时任务",html);
			return;
		}
		try {
			Date start = new Date();
			logger.info("------------------DataananChangeTask-Hour定时任务》》执行 "+com.ry.util.DateUtil.formart(start, com.ry.util.DateUtil.FORMART)+"------------------");
			//pv
			countPvByHourService.countPvByHour(now, now);
			//ip
			countIpByHourService.countIpByHour(now, now);
			//uv
			countUvByHourService.countUvByHour(now, now);
			Date end = new Date();
			int mins = DateUtil.minusBetween(start, end);
			logger.info("------------------DataananChangeTask-Hour定时任务》》结束 "+com.ry.util.DateUtil.formart(end, com.ry.util.DateUtil.FORMART)+"--总用时："+mins+"----------------");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
}
