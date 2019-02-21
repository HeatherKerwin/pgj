package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.PriceLogDao;
import com.ry.core.entity.PriceLog;
import com.ry.core.service.PriceLogService;

@Service
public class PriceLogServiceImpl implements PriceLogService{
	
	@Resource
	PriceLogDao priceLogDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.PriceLogService#saveModel(com.ry.core.entity.PriceLog)
	 */
	@Override
	public void saveModel(PriceLog log) {
		priceLogDao.saveModel(log);
	}
}