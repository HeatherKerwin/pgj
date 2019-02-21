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

import com.ry.core.dao.appmanage.IncreaseUserByQudaoDao;
import com.ry.core.entity.da.IncreasedUserByQudao;
import com.ry.core.service.appmanage.IncreaseUserByQudaoService;
import com.ry.util.DateUtil;

@Service
public class IncreaseUserByQudaoServiceImpl implements
		IncreaseUserByQudaoService {

	@Resource
	IncreaseUserByQudaoDao increaseUserByQudaoDao;
	
	@Override
	public void countIncreaseUserByQudao(Date executeDate, Date endDate) {
		increaseUserByQudaoDao.countIncreaseUserByQudao(executeDate, endDate);
		
	}

	@Override
	public List<IncreasedUserByQudao> increaseDay(Date beginDate, Date endDate,
			String qudao) {
		
		return increaseUserByQudaoDao.increaseDay(beginDate, endDate, qudao);
	}

	@Override
	public List<IncreasedUserByQudao> increaseWeeks(Date beginDate,
			Date endDate, String qudao) {
		
		return increaseUserByQudaoDao.increaseWeeks(beginDate, endDate, qudao);
	}

	@Override
	public List<IncreasedUserByQudao> increaseMonths(Date beginDate, Date endDate,
			String qudao) {
		return increaseUserByQudaoDao.increaseMonths(beginDate, endDate, qudao);
	}

	
	@Override
	public Map<Object, Object> getResultsIncrease(List<Date> showDates, List<IncreasedUserByQudao> actives, int num){
		Map<Object, Object> returnMap = new HashMap<Object, Object>();
		List<Integer> amounts = null;
		if(actives != null && showDates != null){
			if(actives.size() == showDates.size()){//如果记录数相同，则每个日期都有数据
				
			}else{
				amounts = new ArrayList<Integer>();
				boolean isExsit = true;
				for (Date date : showDates) {
					isExsit = true;
					for (IncreasedUserByQudao active : actives) {
						Date date2 = active.getDate();
						int bd = 0;
						try {
							bd = DateUtil.daysBetween(date2, date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if(bd == 0){//日期匹配
							isExsit = false;
							amounts.add(active.getAmount());
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
		int size1 = showDates.size();
		if(size1 > 0){
			for(int i = 0; i < size1-1; i ++){
				//修改时间格式
				list1.add(DateFormat.getDateInstance().format(showDates.get(i)));
			}
			//修改时间格式
			list1.add(DateFormat.getDateInstance().format(showDates.get(size1-1)));
		}
		//如果amounts不为空，则数据从该集合中获取，如果为空，从activeDays中 获取
		if(amounts != null && amounts.size() > 0){
			int size2 = amounts.size();
			for(int i = 0; i < size2-1; i ++){
				list2.add(amounts.get(i) );
			}
			list2.add(amounts.get(size2-1));
		}else{
			int size2 = actives.size();
			if(size2 > 0){
				for(int i = 0; i < size2-1; i ++){
					list2.add(actives.get(i).getAmount());
				}
				list2.add(actives.get(size2-1).getAmount());
			}
			
		}
		int tickInterval = 0;
		if(num > 8){
			tickInterval = Math.round((num -2)/6);
		}
		returnMap.put("tickInterval", tickInterval);
		returnMap.put("data1", list1);
		returnMap.put("data2", list2);
		return returnMap;
	}

	@Override
	public boolean count1(Date executeDate, Date endDate) {
		return increaseUserByQudaoDao.count1(executeDate, endDate);
		
	}
	
	
}
