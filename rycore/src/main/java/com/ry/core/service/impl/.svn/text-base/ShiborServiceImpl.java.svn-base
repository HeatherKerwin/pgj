package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ShiborDao;
import com.ry.core.entity.Shibor;
import com.ry.core.service.ShiborService;
@Service
public class ShiborServiceImpl implements ShiborService{
	@Resource
	ShiborDao shiborDao;

	@Override
	public void saveShibor(Shibor shibor) {
		shiborDao.insertShibor(shibor);
	}

	@Override
	public List<Shibor> getList(Integer start, Integer end) {
		
		return shiborDao.getList(start, end);
	}

	@Override
	public Shibor getShibor(String day) {
		
		return shiborDao.getShibor(day);
	}

}
