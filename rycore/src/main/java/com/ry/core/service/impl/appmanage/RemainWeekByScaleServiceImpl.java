package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.RemainWeekByScaleDao;
import com.ry.core.service.appmanage.RemainWeekByScaleService;

@Service
public class RemainWeekByScaleServiceImpl implements RemainWeekByScaleService {

	@Resource
	private RemainWeekByScaleDao remainWeekByScaleDao;
	
	@Override
	public void countReaminWeekByScale(Date executeDate, Date endDate) {
		remainWeekByScaleDao.countReaminWeekByScale(executeDate, endDate);
	}

	@Override
	public List<Map<String, Object>> showWeekRemain(Date beginDate, Date endDate) {
		return remainWeekByScaleDao.showWeekRemain(beginDate, endDate);
	}

}
