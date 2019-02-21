package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DaylimitDao;
import com.ry.core.entity.Daylimit;
import com.ry.core.service.DaylimitService;

@Service
public class DaylimitServiceImpl implements DaylimitService {

	@Resource
	DaylimitDao daylimitDao;
	
	@Override
	public List<Daylimit> getDaylimitList(String day,String id) {
		List<Daylimit> list = daylimitDao.getList(day,id);
		return list;
	}

	@Override
	public Double countAllmoney(Long endordertime, Long beginordertime) {
		Double count = daylimitDao.countAllmoney(endordertime, beginordertime);
		return count;
	}

	@Override
	public void addDaylimit(Daylimit daylimit) {
		daylimitDao.add(daylimit);
	}

	@Override
	public void updateDaylimit(Daylimit daylimit) {
		daylimitDao.updateDaylimit(daylimit);
	}

}
