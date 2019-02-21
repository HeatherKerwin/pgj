package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Admin;
import com.ry.util.page.PageResults;


public interface AdminService {
	
	Admin login(Integer id,String username, String password);
	
	public Admin getAdminById1(Integer id);
	
	List<Admin> getAdminList(Integer id, String username);
	
	void addAdmin(Admin admin);
		
	void updateAdmin(Admin admin); 
	void updatepwdAdmin(Admin admin); 
	
	PageResults<Admin> findPageModel(int pageNo, int pageSize, Object[] values);
	
	void deleteAdmin(Integer id);

	List<Admin> getList(String userName, String pwd);

	List<Admin> getAll();

	void addUser(Admin admin, String[] powerList);

	PageResults<Admin> getPageResults(Integer pageNo, Integer pageSize);

	List<Admin> getAdminById(Integer id);

	List<Admin> getAdminByName(String userName);

	void updateAdminPower(String userName, String[] powerList);

	/**
	 * 存admin,返回id
	 * @param admin
	 * @return
	 * @date 2016年3月7日
	 * @author lvyanqin
	 */
	int addAdmin1(Admin admin);
	
	/**
	 * 修改admin用户的角色
	 * @author KHC
	 * @param userId
	 * @param roleList
	 * @since 2016年8月3日 上午10:44:38
	 */
	void updateAdminRole(String userId,String[] roleList);
}
