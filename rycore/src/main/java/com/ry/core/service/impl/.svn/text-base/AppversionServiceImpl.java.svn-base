package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AppversionDao;
import com.ry.core.entity.Appversion;
import com.ry.core.service.AppversionService;


@Service
public class AppversionServiceImpl implements AppversionService {

	@Resource
	AppversionDao appversionDao;
	
	@Override
	public Appversion getAppversion() {
		Appversion appversion = appversionDao.getAppversion();
		return appversion;
	}

	@Override
	public void updateAppversion(Appversion appversion) {
		appversionDao.updateAppersion(appversion);		
	}

	@Override
	public List<Appversion> getList() {
		
		return appversionDao.getList();
	}

}
