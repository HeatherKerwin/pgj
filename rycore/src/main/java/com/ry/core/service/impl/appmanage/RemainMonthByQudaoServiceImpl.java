package com.ry.core.service.impl.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.appmanage.RemainMonthByQudaoDao;
import com.ry.core.entity.da.RemainMonthByQudao;
import com.ry.core.service.appmanage.RemainMonthByQudaoService;

@Service
public class RemainMonthByQudaoServiceImpl implements RemainMonthByQudaoService {

	@Resource
	RemainMonthByQudaoDao remainMonthByQudaoDao;
	
	@Override
	public void countReaminMonthByQudao(Date executeDate, Date endDate) {
		
		remainMonthByQudaoDao.countReaminMonthByQudao(executeDate, endDate);
	}

	@Override
	public List<Map<String, Object>> showMonthRemain(String qudao,
			Date beginDate, Date endDate) {
		
		return remainMonthByQudaoDao.showMonthRemain(qudao,
				beginDate, endDate);
	}

}
