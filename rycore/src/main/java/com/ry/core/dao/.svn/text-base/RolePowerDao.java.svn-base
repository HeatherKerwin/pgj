package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.RolePower;

public interface RolePowerDao {

	/**
	 * 保存角色权限中间表
	 * @param rolePower
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	void saveRolePower(RolePower rolePower);

	/**
	 * 根据role_id删除数据
	 * @param id
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	void deleteByRoleId(String id);

	/**
	 * 根据roleId查询所有的rolePower信息
	 * @param roleId
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<RolePower> getList(String roleId);

	/**
	 * 根据role_id获取所有power_id
	 * @param id
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<Integer> getRolePowerPowerIds(String id);

}
