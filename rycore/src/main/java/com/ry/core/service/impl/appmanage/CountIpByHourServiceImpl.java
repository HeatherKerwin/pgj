package com.ry.core.service.impl.appmanage;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.CountIpByHourDao;
import com.ry.core.service.appmanage.CountIpByHourService;
import com.ry.util.DateUtil;

@Service
public class CountIpByHourServiceImpl implements CountIpByHourService {

	@Resource
	private CountIpByHourDao countIpByHourDao;
	
	@Override
	public void countIpByHour(Date executeDate, Date endDate) {
		countIpByHourDao.countIpByHour(executeDate, endDate);
	}

	@Override
	public List<Integer> getBetweenHours(int hours){
		List<Integer> list = null;
		if(hours >= 0){
			list = new ArrayList<Integer>();
		}
		for (int i = 0; i < hours; i++) {
			list.add(i);
		}
		return list;
		
	}
	
	@Override
	public Map<String, Object> handelResult(List<Integer> showHours, List<Map<String, Object>> lists,int num){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Integer> amounts = null;
		if(showHours != null && showHours.size() > 0){
			if(lists == null || lists.size() != showHours.size()){//如果记录数不相同，则数据需要处理
				amounts = new ArrayList<Integer>();
				boolean isExsit = true;
				for (Integer hour : showHours) {
					isExsit = true;
					for (Map<String, Object> map : lists) {
						Integer hour2 = (Integer) map.get("hour");
						if(hour == hour2){//日期匹配
							isExsit = false;
							amounts.add((Integer) map.get("amount"));
							break;
						}
					}
					//如果不存在这一天的数据，则设置这天的数据为0
					if(isExsit){
						amounts.add(0);
					}
				}
			}
		}
		//data1,data2拼接成趋势图需要用到的数据形式
		List<Object> list1 = new ArrayList<Object>();
		List<Object> list2 = new ArrayList<Object>();
		int size1 = showHours.size();
		if(size1 > 0){
			for(int i = 0; i < size1-1; i ++){
				//修改时间格式
				list1.add(showHours.get(i));
			}
			//修改时间格式
			list1.add(showHours.get(size1-1));
		}
		//如果amounts不为空，则数据从该集合中获取，如果为空，从activeDays中 获取
		if(amounts != null && amounts.size() > 0){
			int size2 = amounts.size();
			for(int i = 0; i < size2-1; i ++){
				list2.add(amounts.get(i) );
			}
			list2.add(amounts.get(size2-1));
		}else{
			int size2 = lists.size();
			if(size2 > 0){
				for(int i = 0; i < size2-1; i ++){
					list2.add(lists.get(i).get("amount"));
				}
				list2.add(lists.get(size2-1).get("amount"));
			}
			
		}
		int tickInterval = 0;
		if(num >= 8){//恒左边总的显示数量大于8个点，才会按照这个算法取点
			tickInterval = Math.round((num -2)/6);
		}
		returnMap.put("tickInterval", tickInterval);
		returnMap.put("data1", list1);
		returnMap.put("data2", list2);
		return returnMap;
	}

	@Override
	public List<Map<String, Object>> getIpByHour(Date beginDate, Date endDate,
			String style, int hours) {
		
		return countIpByHourDao.getIpByHour(beginDate, endDate, style, hours);
	}
	
}
