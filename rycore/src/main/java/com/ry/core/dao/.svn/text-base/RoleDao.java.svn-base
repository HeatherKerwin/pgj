package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Role;
import com.ry.util.page.PageResults;

public interface RoleDao {

	/**
	 * 保存角色，返回id
	 * @param role
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	int saveRole(Role role);

	/**
	 * 根据name,获取role信息
	 * @param name
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<Role> getList(String name);

	/**
	 * 更新role
	 * @param role
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	void updateRole(Role role);

	/**
	 * 获取分页角色信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	PageResults<Role> getPageRoleResults(Integer pageNo, Integer pageSize);

	/**
	 * 根据id获取角色
	 * @param id
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	Role getRoleById(Integer id);

	/**
	 * 获取所有角色
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<Role> getAllRoles();

	/**
	 * 加载所有用户角色
	 * @author KHC
	 * @since 2016年8月2日 下午4:40:16
	 */
	List<Map<String, Object>> initRole();
}
