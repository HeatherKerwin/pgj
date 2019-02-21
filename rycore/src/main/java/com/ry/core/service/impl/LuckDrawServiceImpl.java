package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.LuckDrawDao;
import com.ry.core.entity.LuckDraw;
import com.ry.core.service.LuckDrawService;

@Service
public class LuckDrawServiceImpl implements LuckDrawService{
	
	@Resource
	LuckDrawDao luckDrawDao;

	@Override
	public void saveModel(LuckDraw luckDraw) {
		luckDrawDao.saveModel(luckDraw);
	}

}
