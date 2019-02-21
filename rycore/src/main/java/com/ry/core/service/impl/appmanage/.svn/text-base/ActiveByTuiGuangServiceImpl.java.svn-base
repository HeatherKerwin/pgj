package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.ActiveByTuiGuangDao;
import com.ry.core.service.appmanage.ActiveByTuiGuangService;

@Service
public class ActiveByTuiGuangServiceImpl implements ActiveByTuiGuangService {

	@Resource
	private ActiveByTuiGuangDao activeByTuiGuangDao;
	
	@Override
	public List<Map<String, Object>> getActive(Date beginDate, Date endDate,
			String flag, String style) {
		
		return activeByTuiGuangDao.getActive(beginDate, endDate, flag, style);
	}

	@Override
	public void countActive(int flag, Date executeDate, Date endDate) {
		activeByTuiGuangDao.countActive(flag, executeDate, endDate);
	}

}
