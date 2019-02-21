package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.CurrentpriceDao;
import com.ry.core.entity.Currentprice;
import com.ry.core.service.CurrentpriceService;


@Service
public class CurrentpriceServiceImpl implements CurrentpriceService{
	@Resource
	CurrentpriceDao currentpriceDao;

	@Override
	public List<Currentprice> getList(Currentprice c) {
		return currentpriceDao.queryList(c);
	}

	@Override
	public int updateAllCurrentprice(List<Currentprice> currentpriceList) {		
		return currentpriceDao.updateAll(currentpriceList);				
	}



}
