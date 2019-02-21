package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ClickNumDao;
import com.ry.core.entity.ClickNum;
import com.ry.core.service.ClickNumService;

@Service
public class ClickNumServiceImpl implements ClickNumService {

	@Resource
	ClickNumDao clickNumDao;
	
	@Override
	public void saveClickNum(ClickNum clickNum) {
		clickNumDao.saveClickNum(clickNum);
	}

}
