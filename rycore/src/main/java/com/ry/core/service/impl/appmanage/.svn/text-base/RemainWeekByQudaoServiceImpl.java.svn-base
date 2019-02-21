package com.ry.core.service.impl.appmanage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ry.core.dao.appmanage.RemainWeekByQudaoDao;
import com.ry.core.service.appmanage.RemainWeekByQudaoService;
import com.ry.util.DateUtil;

@Service
public class RemainWeekByQudaoServiceImpl implements RemainWeekByQudaoService {

	private static final Logger logger = Logger.getLogger(RemainWeekByQudaoServiceImpl.class);
	
	@Resource
	RemainWeekByQudaoDao remainWeekByQudaoDao;
	
	@Override
	public void countReaminWeekByQudao(Date executeDate, Date endDate) {
		remainWeekByQudaoDao.countReaminWeekByQudao(executeDate, endDate);
	}

	@Override
	public List<Map<String, Object>> showWeekRemain(String qudao, Date beginDate,
			Date endDate) {
		return remainWeekByQudaoDao.showWeekRemain(qudao,beginDate,endDate);
	}

	@Override
	public List<Integer> calTopInt(int flag, String otherDays){
		List<Integer> topInt = new ArrayList<Integer>();
		String days = null;
		if(StringUtils.isEmpty(otherDays)){
			return topInt;
		}else{
			days = otherDays.trim();//去空格
		}
		if(flag == 0){
			if("60".equals(days)){
				//1，2，3，4，5，6，8，10，13周
				for(int i = 1; i < 7; i ++){
					topInt.add(i);
				}
				topInt.add(8);
				topInt.add(10);
				topInt.add(13);
				
			}else if("180".equals(days)){
				//1，3，5，7，12，15，18，22，26周
				for(int i = 1; i < 8; i += 2){
					topInt.add(i);
				}
				topInt.add(12);
				topInt.add(15);
				topInt.add(18);
				topInt.add(22);
				topInt.add(26);
				
			}else{
				for(int i = 1; i < 10; i ++){
					topInt.add(i);
				}
			}
		}else{
			for(int i = 1; i < 10; i ++){//显示1-9月
				topInt.add(i);
			}
		}
		return topInt;
	}
	
	@Override
	public List<String> calLeftStrByDay(List<Date> showDates, int daysNum){
		List<String> leftList = new ArrayList<String>();
		if(showDates != null && showDates.size() > 0){
			for (Date date : showDates) {
				leftList.add(DateUtil.formart(date, "yyyy-MM-dd") + "~" + DateUtil.formart(DateUtil.addDays(date, daysNum), "yyyy-MM-dd"));//得到形如2016-1-11~2016-1-17
			}
		}
		return leftList;
	}
	
	@Override
	public List<String> calLeftStrByMonth(List<Date> showDates, int month){
		List<String> leftList = new ArrayList<String>();
		if(showDates != null && showDates.size() > 0){
			for (Date date : showDates) {
				Date tempDate = DateUtil.addMonth(date, month);
				tempDate = DateUtil.addDays(tempDate, -1);
				leftList.add(DateUtil.formart(date, "yyyy-MM-dd") + "~" + DateUtil.formart(tempDate, "yyyy-MM-dd"));//得到形如2016-1-11~2016-1-17
			}
		}
		return leftList;
	}
	
	@Override
	public List<Map<String, Object>> getresultsRemains(List<Date> showDates, List<Map<String, Object>> remains){
  		List<Map<String, Object>> newReamins = null;
		if(showDates != null && showDates.size() > 0){
			if(remains.size() == showDates.size()){//如果记录数相同，则每个日期都有数据
				return remains;
			}else{
				newReamins = new ArrayList<Map<String,Object>>();
				boolean isExsit = true;
				for (Date date : showDates) {
					isExsit = true;
					for (Map<String, Object> remain : remains) {
						int bd = 0;
						try {
							Date date2 = DateUtil.parser(remain.get("date").toString(),"yyyy-MM-dd");
							bd = DateUtil.daysBetween(date2, date);
						} catch (ParseException e) {
							logger.info("RemainByQudaoController-getresultsRemains>>>>>>>>>>>",e);
							e.printStackTrace();
						}
						if(bd == 0){//日期匹配
							isExsit = false;
							newReamins.add(remain);
							break;
						}
					}
					//如果不存在这一天的数据，则新建对象，并设置日期
					if(isExsit){
						Map<String, Object> remain = new HashMap<String, Object>();
						remain.put("date", date);
						newReamins.add(remain);
					}
				}
			}
		}
		
		return newReamins;
	}

}
