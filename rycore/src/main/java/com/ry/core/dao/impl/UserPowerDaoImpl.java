package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.UserPowerDao;
import com.ry.core.entity.Admin;
import com.ry.core.entity.da.UserPower;

@Repository
public class UserPowerDaoImpl extends BaseDao<UserPower, Integer> implements UserPowerDao{

	@Override
	public void saveUserPower(UserPower userPower){
		save(userPower);
	}

	@Override
	public List<Integer> getListByUserId(Integer id) {
		List<Integer> lists =  getListBySQL("select powerId from userPower where userId = ? order by powerId", new Object[]{id});
		return lists;
	}
	
	@Override
	public List<UserPower> getPowersByid(Integer id) {
		List<UserPower> lists =  getListByHQL("from userPower where userId = ?", new Object[]{id});
		return lists;
	}
	
	@Override
	public void deleteUserPower(UserPower userPower){
		deleteObject(userPower);
	}

	
}
