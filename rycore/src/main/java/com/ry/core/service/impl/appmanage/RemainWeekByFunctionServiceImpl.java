package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.RemainWeekByFunctionDao;
import com.ry.core.service.appmanage.RemainWeekByFunctionService;

@Service
public class RemainWeekByFunctionServiceImpl implements RemainWeekByFunctionService {

	@Resource
	private RemainWeekByFunctionDao remainWeekByFunctionDao;
	
	@Override
	public void countRemainWeekByFunction(String field, Date executeDate, Date endDate) {
		remainWeekByFunctionDao.countRemainWeekByFunction(field, executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> showWeekRemain(String code,
			Date beginDate, Date endDate1,String field) {
		return remainWeekByFunctionDao.showWeekRemain(code, beginDate, endDate1, field);
	}

}
