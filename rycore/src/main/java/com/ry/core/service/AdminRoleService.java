package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.da.AdminRole;

public interface AdminRoleService {

	/**
	 * 保存对象
	 * @author KHC
	 * @param adminPower
	 * @since 2016年8月2日 下午3:04:37
	 */
	public void save(AdminRole adminRole);

	/**
	 * 根据用户id查询
	 * @author KHC
	 * @param id
	 * @since 2016年8月2日 下午3:15:18
	 */
	public List<Integer> getListByUserId(Integer id);
	
	/**
	 * 根据用户id获取角色对象
	 * @author KHC
	 * @param id
	 * @since 2016年8月2日 下午3:05:27
	 */
	public List<AdminRole> getRolesById(Integer id);
	
	/**
	 * 删除对象
	 * @author KHC
	 * @param adminPower
	 * @since 2016年8月2日 下午3:05:53
	 */
	public void delete(AdminRole adminRole);

	/**
	 * 根据用户主键和角色主键获取对象（验证是否有该权限）
	 * @author WKX
	 * @param adminId 用户主键
	 * @param roleId 角色主键
	 * @since 2017年6月21日 下午5:05:08
	 */
	public List<AdminRole> getByAdminIdAndRoleId(Integer adminId,Integer roleId);
}