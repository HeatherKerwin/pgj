package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.ActiveRatioByScaleDao;
import com.ry.core.entity.da.ActiveRatioByQudao;
import com.ry.core.entity.da.ActiveRatioByScale;
import com.ry.core.service.appmanage.ActiveRatioByScaleService;

@Service
public class ActiveRatioByScaleServiceImpl implements ActiveRatioByScaleService {

	@Resource
	private ActiveRatioByScaleDao activeRatio;
	
	@Override
	public void countActiveRatioWeeks(Date executeDate, Date endDate) {
		activeRatio.countActiveRatioWeeks(executeDate, endDate);
		
	}

	@Override
	public void countActiveRatioMonths(Date executeDate, Date endDate) {
		activeRatio.countActiveRatioMonths(executeDate, endDate);
		
	}

	@Override
	public List<ActiveRatioByScale> activeRatioByScale(Date beginDate, Date endDate,
			String flag) {
		
		return activeRatio.activeRatioByScale(beginDate, endDate, flag);
	}
	
}
