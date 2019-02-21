package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ProviceDao;
import com.ry.core.entity.Provice;
import com.ry.core.service.ProviceService;

@Service
public class ProviceServiceImpl extends BaseDao<Provice, Integer> implements
		ProviceService {

	@Resource
	ProviceDao proviceDao;

	@Override
	public List<Provice> getList() {		
		return proviceDao.getList();
	}
	
	

}
