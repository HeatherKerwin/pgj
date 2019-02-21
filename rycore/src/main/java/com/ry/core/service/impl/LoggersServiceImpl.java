package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.LoggersDao;
import com.ry.core.entity.Loggers;
import com.ry.core.form.loggers.LoggersRequest;
import com.ry.core.service.LoggersService;
import com.ry.util.page.PageResults;

@Service
public class LoggersServiceImpl implements LoggersService {

	@Resource
	private LoggersDao loggersDao;

	@Override
	public PageResults<Map<String, Object>> getLoggersPage(LoggersRequest logRes) throws Exception {
		return loggersDao.getPage(logRes);
	}

	@Override
	public Integer addLoggers(Loggers loggers) {
		return loggersDao.addLoggers(loggers);
	}

	@Override
	public void updateLoggers(Loggers loggers) {
		loggersDao.updateLoggers(loggers);
	}

	@Override
	public Loggers getLoggers(Integer id) {
		return loggersDao.getLoggers(id);
	}

	@Override
	public List<Map<String,Object>> getAdmin() {
		List<Map<String,Object>> map = loggersDao.getAdmins();
		return map;
	}

	@Override
	public List<Map<String, Object>> getByObj(LoggersRequest logRes) throws Exception {
		return loggersDao.getByObj(logRes);
	}

}
