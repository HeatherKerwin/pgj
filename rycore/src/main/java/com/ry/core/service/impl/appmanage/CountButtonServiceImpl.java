package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.CountButtonDao;
import com.ry.core.service.appmanage.CountButtonService;

@Service
public class CountButtonServiceImpl implements CountButtonService {

	@Resource
	private CountButtonDao countButtonDao;
	
	@Override
	public void countButton(int flag, Date executeDate, Date endDate) {
		countButtonDao.countButton(flag, executeDate, endDate);
	}

	@Override
	public List<Map<String, Object>> getButton(Date beginDate, Date endDate,
			int flag, String style, String code) {
		return countButtonDao.getButton(beginDate, endDate, flag, style, code);
	}

}
