package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RolePowerDao;
import com.ry.core.entity.Role;
import com.ry.core.entity.RolePower;

@Repository
public class RolePowerDaoImpl extends BaseDao<RolePower, Integer> implements RolePowerDao{

	@Override
	public void saveRolePower(RolePower rolePower) {
		saveOrUpdate(rolePower);
	}

	@Override
	public void deleteByRoleId(String id) {
		delete(Integer.valueOf(id));
		
	}

	@Override
	public List<RolePower> getList(String roleId) {
		List<RolePower> rolePowers = (List<RolePower>) getListByHQL("from rolePower where role_id = ?", new Object[]{Integer.valueOf(roleId)});
		return rolePowers;
	}

	@Override
	public List<Integer> getRolePowerPowerIds(String id) {
		List<Integer> lists =  getListBySQL("select menu_id from rolePower where role_id = ?", new Object[]{id});
		return lists;
	}

}
