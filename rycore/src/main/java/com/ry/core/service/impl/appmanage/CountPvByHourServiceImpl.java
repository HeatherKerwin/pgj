package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.CountPvByHourDao;
import com.ry.core.service.appmanage.CountPvByHourService;

@Service
public class CountPvByHourServiceImpl implements CountPvByHourService {

	@Resource
	private CountPvByHourDao countPvByHourDao;
	
	@Override
	public void countPvByHour(Date executeDate, Date endDate) {
		countPvByHourDao.countPvByHour(executeDate, endDate);
	}

	@Override
	public List<Map<String, Object>> getPv(Date beginDate, Date endDate,
			String flag, String style,  Integer hours) {
		
		return countPvByHourDao.getPv(beginDate, endDate, flag, style, hours);
	}
	
}
