package com.ry.core.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.KeyWordCountDao;
import com.ry.core.entity.KeyWordCount;
import com.ry.core.service.KeyWordCountService;
import com.ry.util.DateUtil;

@Service
public class KeyWordCountServiceImpl implements KeyWordCountService {

	@Resource
	KeyWordCountDao keyWordCountDao;
	
	@Override
	public void saveModel(KeyWordCount keyWordCount) {
		List<KeyWordCount> list = keyWordCountDao.getByDayAndKeyword(keyWordCount.getDay(), keyWordCount.getKeyword());
		if(list==null || list.size()==0){
			keyWordCountDao.saveModel(keyWordCount);
		}
	}
	
	public List<Map<String,Object>> getByStartAndEndAndKwGroup(String start,String end,String keyword){
		return keyWordCountDao.getByStartAndEndAndKwGroup(start, end,keyword);
	}
	
	public List<Map<String,Object>> getByStartAndEndAndKw(String type,String keyword,List<Date> datas){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		Date start = null,end = null;
		Calendar cal = Calendar.getInstance();
		String s = "";
		String e = "";
		for(int i=0;i<datas.size();i++){
			List<Map<String,Object>> temp = null;
			if("-1".equals(type)){//按天查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				end = cal.getTime();
			}else if("0".equals(type)){//按周查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.WEEK_OF_MONTH, 1);
				end = cal.getTime();
			}else{//按月查询
				start = datas.get(i);
				cal.setTime(start);
				cal.add(Calendar.MONTH, 1);
				end = cal.getTime();
			}
			s = DateUtil.formart(start,DateUtil.FORMART3);
			e = DateUtil.formart(end,DateUtil.FORMART3);
			temp = keyWordCountDao.getByStartAndEndAndKwGroup(s+" 00:00:00",e+" 00:00:00", keyword);
			if(temp!=null && temp.size()>0){
				Map<String,Object> tmp = temp.get(0);
				tmp.put("day", s);
				result.add(tmp);
			}
		}
		return result;
	}
}