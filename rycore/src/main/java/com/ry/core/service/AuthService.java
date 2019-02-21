package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Menu;
import com.ry.core.entity.Role;
import com.ry.core.entity.RolePower;
import com.ry.util.page.PageResults;

public interface AuthService {

	/**
	 * 得到分页菜单对象数据
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	PageResults<Map<String,Object>> getPageResults1(Integer pageNo, Integer pageSize);

	/**
	 * 根据id获取menu对象
	 * @param id
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	Menu getMenuById(Integer id);

	/**
	 * 更新菜单
	 * @param menu
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	void updateMenu(Menu menu);

	/**
	 * 存菜单
	 * @param menu
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	void saveMenu(Menu menu);

	/**
	 * 存角色
	 * @param name
	 * @param powerList
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	void saveRole(String name, String[] powerList);

	/**
	 * 根据name获取role信息
	 * @param name
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<Role> getList(String name);

	/**
	 * 更新角色权限
	 * @param id
	 * @param powerList
	 * @date 2016年3月7日
	 * @author lvyanqin
	 * @param name 
	 */
	void updateRole(String id, String name, String[] powerList);

	/**
	 * 获取所有角色信息
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
	 * 更新角色
	 * @param role
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	void updateRole(Role role);

	/**
	 * 根据role_id获取rolePower信息
	 * @param id
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<RolePower> getRolePowerList(String id);

	/**
	 * 根据role_id获取所有权利id
	 * @param id
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<Integer> getRolePowerPowerIds(String id);

	/**
	 * 获取所有权限
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	List<Role> getAllRoles();

	/**
	 * 加载所有菜单数据
	 * @return
	 * @date 2016年3月8日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> initMenu();

	/**
	 * 加载所有用户角色
	 * @author KHC
	 * @since 2016年8月2日 下午4:40:16
	 */
	List<Map<String, Object>> initRole();
	
	/**
	 * 根据Id的获取对应的功能菜单
	 * @author KHC
	 * @param id
	 * @since 2016年8月4日 下午4:45:58
	 */
	List<Map<String, Object>> getMenuListById(Integer id);
	
	/**
	 * 根据父节点获取菜单列表
	 * @author KHC
	 * @param parentId
	 * @since 2017年1月12日 下午1:03:28
	 */
	List<Menu> getListByParentId(Integer parentId);
}
