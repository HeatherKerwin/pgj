package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.MenuDao;
import com.ry.core.dao.RoleDao;
import com.ry.core.dao.RolePowerDao;
import com.ry.core.entity.Menu;
import com.ry.core.entity.Role;
import com.ry.core.entity.RolePower;
import com.ry.core.service.AuthService;
import com.ry.util.page.PageResults;

@Service
public class AuthServiceImpl implements AuthService{

	@Resource
	private MenuDao menuDao;
	
	@Resource
	private RoleDao roleDao;
	
	@Resource
	private RolePowerDao rolePowerDao;
	
	@Override
	public PageResults<Map<String,Object>> getPageResults1(Integer pageNo, Integer pageSize) {
 		return menuDao.getPageResults1(pageNo, pageSize);
	}
	
	@Override
	public Menu getMenuById(Integer id) {
		return menuDao.getMenuById(id);
	}

	@Override
	public void updateMenu(Menu menu) {
		menuDao.updateMenu(menu);
	}

	@Override
	public void saveMenu(Menu menu) {
		menuDao.saveMenu(menu);
	}

	@Override
	public void saveRole(String name, String[] powerList) {
		Role role = new Role();
		role.setName(name);
		role.setState(1);
		int roleId = roleDao.saveRole(role);
		RolePower rolePower;
		if(powerList!=null&& powerList.length > 0){
		for (String str : powerList) {
			rolePower = new RolePower();
			rolePower.setRole_id(roleId);
			rolePower.setMenu_id(Integer.valueOf(str));
			rolePowerDao.saveRolePower(rolePower);
		}
	} else{
		rolePower = new RolePower();
		rolePower.setRole_id(roleId);
		rolePower.setMenu_id(-1);
		rolePowerDao.saveRolePower(rolePower);
	}
	}

	@Override
	public List<Role> getList(String name) {
		
		return roleDao.getList(name);
	}

	@Override
	public void updateRole(String id, String name, String[] powerList) {
		List<RolePower> powers = rolePowerDao.getList(id);
		if(powers != null){
			for (RolePower rolePower : powers) {
				rolePowerDao.deleteByRoleId(String.valueOf(rolePower.getId()));
			}
		}
		
		RolePower rolePower;
		if(powerList != null && powerList.length > 0){
		for (String str : powerList) {
			rolePower = new RolePower();
			rolePower.setRole_id(Integer.valueOf(id));
			rolePower.setMenu_id(Integer.valueOf(str));
			rolePowerDao.saveRolePower(rolePower);//重新保存
		}
		}
		else{
			rolePower = new RolePower();
			rolePower.setRole_id(Integer.valueOf(id));
			rolePower.setMenu_id(-1);
			rolePowerDao.saveRolePower(rolePower);
		}
	}

	@Override
	public PageResults<Role> getPageRoleResults(Integer pageNo, Integer pageSize) {
		
		return roleDao.getPageRoleResults(pageNo, pageSize);
	}

	@Override
	public Role getRoleById(Integer id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public List<RolePower> getRolePowerList(String roleId) {
		
		return rolePowerDao.getList(roleId);
	}

	@Override
	public List<Integer> getRolePowerPowerIds(String id) {
		
		return rolePowerDao.getRolePowerPowerIds(id);
	}

	@Override
	public List<Role> getAllRoles() {
		
		return roleDao.getAllRoles();
	}

	@Override
	public List<Map<String, Object>> initMenu() {
		
		return menuDao.initMenu();
	}

	@Override
	public List<Map<String, Object>> initRole() {
		return roleDao.initRole();
	}

	@Override
	public List<Map<String, Object>> getMenuListById(Integer id) {
		return menuDao.getMenuListById(id);
	}

	@Override
	public List<Menu> getListByParentId(Integer parentId) {
		return menuDao.getListByParentId(parentId);
	}
}
