package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.ActiveRatioByQudaoDao;
import com.ry.core.entity.da.ActiveRatioByQudao;
import com.ry.core.service.appmanage.ActiveRatioByQudaoService;

@Service
public class ActiveRatioByQudaoServiceImpl implements ActiveRatioByQudaoService {

	@Resource
	ActiveRatioByQudaoDao activeRatio;
	
	@Override
	public void countActiveRatioWeeks(Date executeDate, Date endDate) {
		activeRatio.countActiveRatioWeeks(executeDate, endDate);
		
	}

	@Override
	public void countActiveRatioMonths(Date executeDate, Date endDate) {
		activeRatio.countActiveRatioMonths(executeDate, endDate);
		
	}

	@Override
	public List<ActiveRatioByQudao> activeRatioByQudao(Date beginDate, Date endDate,
			String flag, String qudao) {
		
		return activeRatio.activeRatioByQudao(beginDate, endDate, flag, qudao);
	}

}
