package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.OperateLogDao;
import com.ry.core.entity.OperateLog;
import com.ry.core.service.OperateLogService;

@Service
public class OperateLogServiceImpl implements OperateLogService{
	
	@Resource
	OperateLogDao operatelogDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.LogService#saveModel(com.ry.core.entity.Log)
	 */
	@Override
	public void saveModel(OperateLog log) {
		operatelogDao.saveModel(log);
	}
}