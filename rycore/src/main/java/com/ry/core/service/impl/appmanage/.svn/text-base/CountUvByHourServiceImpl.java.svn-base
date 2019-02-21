package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.CountUvByHourDao;
import com.ry.core.service.appmanage.CountUvByHourService;

@Service
public class CountUvByHourServiceImpl implements CountUvByHourService {

	@Resource
	private CountUvByHourDao countUvByHourDao;
	
	@Override
	public void countUvByHour(Date executeDate, Date endDate) {
		countUvByHourDao.countUvByHour(executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> getUvByHour(Date beginDate, Date endDate,
			String style, int hours) {
		return countUvByHourDao.getUvByHour(beginDate, endDate, style, hours);
	}

}
