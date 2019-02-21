package com.ry.core.service.impl.appmanage;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.IncreaseUserByFunctionDao;
import com.ry.core.entity.da.IncreasedUserByFunction;
import com.ry.core.service.appmanage.IncreaseUserByFunctionService;
import com.ry.util.DateUtil;

@Service
public class IncreaseUserByFunctionServiceImpl implements
		IncreaseUserByFunctionService {

	@Resource
	private IncreaseUserByFunctionDao increaseUserByFunctionDao;
	
	@Override
	public void countIncreaseUserByFlag(int flag, Date executeDate, Date endDate) {
		increaseUserByFunctionDao.countIncreaseUserByFlag(flag, executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> findIncreaseUserByFunction(
			Date beginDate, Date endDate, Integer flag, String code) {
		
		return increaseUserByFunctionDao.findIncreaseUserByFunction(beginDate, endDate, flag, code);
	}

	@Override
	public Map<String, Object> handelResult(List<Date> showDates, List<Map<String, Object>> lists, int num){
		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<Integer> amounts = null;
		if(showDates != null && showDates.size() > 0){
			if(lists == null || lists.size() != showDates.size()){//如果记录数不相同，则数据需要处理
				amounts = new ArrayList<Integer>();
				boolean isExsit = true;
				for (Date date : showDates) {
					isExsit = true;
					for (Map<String, Object> map : lists) {
						
						Date date2 = (Date) map.get("date");
						int bd = 0;
						try {
							bd = DateUtil.daysBetween(date2, date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						if(bd == 0){//日期匹配
							isExsit = false;
							if(map.get("amount").getClass().getName() == "java.math.BigDecimal"){
								BigDecimal big = new BigDecimal(map.get("amount")+"");
								amounts.add(big.intValue());
							}else{
								amounts.add((Integer) map.get("amount"));
							}
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
	
}
