package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.RemainMonthByScaleDao;
import com.ry.core.service.appmanage.RemainMonthByScaleService;

@Service
public class RemainMonthByScaleServiceImpl implements RemainMonthByScaleService {

	@Resource
	private RemainMonthByScaleDao remainMonthByScaleDao;
	
	@Override
	public void countReaminMonthByScale(Date executeDate, Date endDate) {
		remainMonthByScaleDao.countReaminMonthByScale(executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> showMonthRemain(Date beginDate,
			Date endDate1) {
		
		return remainMonthByScaleDao.showMonthRemain(beginDate, endDate1);
	}

}
