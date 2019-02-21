package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.ActiveByFunctionDao;
import com.ry.core.service.appmanage.ActiveByFunctionService;

@Service
public class ActiveByFunctionServiceImpl implements ActiveByFunctionService {

	@Resource
	private ActiveByFunctionDao activeByFunctionDao;
	
	@Override
	public void countActiveByFunction(String flag, Date executeDate, Date endDate) {
		activeByFunctionDao.countActiveByFunction(flag, executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> findActiveByFunction(Date beginDate,
			Date endDate, String flag, String code) {
		return activeByFunctionDao.findActiveByFunction(beginDate, endDate, flag, code);
	}

}
