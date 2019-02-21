package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.CountUvDao;
import com.ry.core.service.appmanage.CountUvService;

@Service
public class CountUvServiceImpl implements CountUvService {

	@Resource
	private CountUvDao countUvDao;
	
	@Override
	public void countUv(int flag, Date executeDate, Date endDate) {
		countUvDao.countUv(flag, executeDate, endDate);
	}

	@Override
	public List<Map<String, Object>> getUv(Date beginDate, Date endDate,
			String flag, String style) {
		return countUvDao.getUv(beginDate, endDate, flag, style);
	}

}
