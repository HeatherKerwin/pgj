package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.CountIpDao;
import com.ry.core.service.appmanage.CountIpService;

@Service
public class CountIpServiceImpl implements CountIpService {

	@Resource
	private CountIpDao countIpDao;
	
	@Override
	public void countIp(int flag, Date executeDate, Date endDate) {
		countIpDao.countIp(flag, executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> getIp(Date beginDate, Date endDate,
			String flag, String style) {
		return countIpDao.getIp(beginDate, endDate, flag, style);
	}

}
