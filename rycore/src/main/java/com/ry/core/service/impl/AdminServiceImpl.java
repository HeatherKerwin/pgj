package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AdminDao;
import com.ry.core.entity.Admin;
import com.ry.core.service.AdminService;
import com.ry.util.page.PageResults;

@Service
public class AdminServiceImpl implements AdminService{
	@Resource
	AdminDao adminDao;

	@Override
	public Admin login(Integer id,String username, String password) {
		Admin admin = adminDao.getAdmin(id,username, password);
		return admin;
	}
	
	public Admin getAdminById1(Integer id) {
		return adminDao.getAdminById1(id);
	}

	@Override
	public List<Admin> getAdminList(Integer id,String username) {
		List<Admin> adminList = adminDao.getList1(id,username);
		return adminList;
	}

	@Override
	public void addAdmin(Admin admin) {
		adminDao.addAdmin(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminDao.updateAdmin(admin);
		
	}
	@Override
	public void updatepwdAdmin(Admin admin) {
		adminDao.updatepwdAdmin(admin);
		
	}
	
	@Override
	public PageResults<Admin> findPageModel(int pageNo, int pageSize,Object[] values) {
		PageResults<Admin> pageResult = adminDao.findPageModel(pageNo, pageSize, values);
		return pageResult;
	}

	@Override
	public void deleteAdmin(Integer id) {
		adminDao.deleteAdmin(id);
	}

	@Override
	public List<Admin> getList(String userName, String pwd) {
		
		return adminDao.getList(userName, pwd);
	}

	@Override
	public List<Admin> getAll() {
		
		return adminDao.getAll();
	}

	@Override
	public void addUser(Admin admin, String[] powerList) {
		
		adminDao.addUser(admin,powerList);
	}

	@Override
	public PageResults<Admin> getPageResults(Integer pageNo, Integer pageSize) {
		return adminDao.getPageResults(pageNo, pageSize);
	}

	@Override
	public List<Admin> getAdminById(Integer id) {
		
		return adminDao.getAdminById(id);
	}

	@Override
	public List<Admin> getAdminByName(String userName) {
		return adminDao.getAdminByName(userName);
	}

	@Override
	public void updateAdminPower(String userName, String[] powerList) {
		adminDao.updateAdminPower(userName, powerList);
		
	}

	@Override
	public int addAdmin1(Admin admin) {
		return adminDao.addAdmin1(admin);
	}

	@Override
	public void updateAdminRole(String userId, String[] roleList) {
		adminDao.updateAdminRole(userId, roleList);
	}

}
