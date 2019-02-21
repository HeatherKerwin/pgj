package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.UserPowerDao;
import com.ry.core.entity.da.UserPower;
import com.ry.core.service.UserPowerService;

@Service
public class UserPowerServiceImpl implements UserPowerService {

	@Resource
	UserPowerDao userPowerDao;
	
	@Override
	public List<Integer> getListByUserId(Integer id) {
		
		return userPowerDao.getListByUserId(id);
	}

	@Override
	public List<UserPower> getPowersByid(Integer id) {
		
		return userPowerDao.getPowersByid(id);
	}

	@Override
	public void saveUserPower(UserPower userPower) {
		userPowerDao.saveUserPower(userPower);
	}

}
