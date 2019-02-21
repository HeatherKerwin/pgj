package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.ActiveByQudaoDao;
import com.ry.core.entity.da.ActiveByQudao;
import com.ry.core.service.appmanage.ActiveByQudaoService;

@Service
public class ActiveByQudaoServiceImpl implements ActiveByQudaoService {

	@Resource
	ActiveByQudaoDao activeByQudaoDao;
	
	
	@Override
	public List<ActiveByQudao> activeByQudao(Date beginDate, Date endDate,String flag, String qudao) {
		return activeByQudaoDao.activeByQudao(beginDate, endDate, flag, qudao);
		
	}

	
	@Override
	public void countActive(Date executeDate, Date endDate) {
		activeByQudaoDao.countActive(executeDate, endDate);
	}

	
	@Override
	public void countActiveMonths(Date executeDate, Date endDate) {
		activeByQudaoDao.countActiveMonths(executeDate, endDate);
		
	}
	
}
