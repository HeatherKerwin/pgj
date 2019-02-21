package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.RemainMonthByFunctionDao;
import com.ry.core.service.appmanage.RemainMonthByFunctionService;

@Service
public class RemainMonthByFunctionServiceImpl implements
		RemainMonthByFunctionService {

	@Resource
	private RemainMonthByFunctionDao remainMonthByFunctionDao;
	
	@Override
	public void countRemainMonthByFunction(String field, Date executeDate, Date endDate) {
		remainMonthByFunctionDao.countRemainMonthByFunction(field, executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> showMonthRemain(String code,
			Date beginDate, Date endDate1,String field) {
		return remainMonthByFunctionDao.showMonthRemain(code, beginDate, endDate1,field);
	}

}
