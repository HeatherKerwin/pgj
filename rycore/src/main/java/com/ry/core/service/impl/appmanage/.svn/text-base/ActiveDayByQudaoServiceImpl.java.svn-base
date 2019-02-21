package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.ActiveDayByQudaoDao;
import com.ry.core.entity.da.ActiveDayByQudao;
import com.ry.core.service.appmanage.ActiveDayByQudaoService;

@Service
public class ActiveDayByQudaoServiceImpl implements ActiveDayByQudaoService {

	@Resource
	ActiveDayByQudaoDao activeByQudaoDao;
	
	@Override
	public void countDay(Date executeDate, Date endDate) {
		activeByQudaoDao.countDay(executeDate, endDate);
	}

	@Override
	public List<ActiveDayByQudao> activeDays(Date beginDate, Date endDate, String qudao) {
		
		return activeByQudaoDao.activeDays(beginDate, endDate, qudao);
	}

	

}
