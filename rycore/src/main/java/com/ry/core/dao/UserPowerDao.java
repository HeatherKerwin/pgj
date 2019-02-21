package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.da.UserPower;

public interface UserPowerDao {

	public void saveUserPower(UserPower userPower);

	public List<Integer> getListByUserId(Integer id);
	
	public List<UserPower> getPowersByid(Integer id);
	
	public void deleteUserPower(UserPower userPower);

	
}
