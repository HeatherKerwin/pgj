package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.IncreaseUserByTuiGuangDao;
import com.ry.core.service.appmanage.IncreaseUserByTuiGuangService;

@Service
public class IncreaseUserByTuiGuangServiceImpl implements
		IncreaseUserByTuiGuangService {

	@Resource
	private IncreaseUserByTuiGuangDao increaseUserByTuiGuangDao;
	
	@Override
	public void countIncreaseUser(int flag, Date executeDate, Date endDate) {
		increaseUserByTuiGuangDao.countIncreaseUser(flag, executeDate, endDate);
		
	}

	@Override
	public List<Map<String, Object>> findIncreaseUser(Date beginDate,
			Date endDate, Integer flag, String style) {
		
		return increaseUserByTuiGuangDao.findIncreaseUser(beginDate, endDate, flag, style);
	}

}
