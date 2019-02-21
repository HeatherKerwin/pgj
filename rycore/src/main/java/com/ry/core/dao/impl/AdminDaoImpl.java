package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.AdminDao;
import com.ry.core.dao.AdminRoleDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.dao.UserPowerDao;
import com.ry.core.entity.Activity;
import com.ry.core.entity.Admin;
import com.ry.core.entity.da.AdminRole;
import com.ry.core.entity.da.UserPower;
import com.ry.core.form.ActivityForm;
import com.ry.util.page.PageResults;

@Repository
public class AdminDaoImpl extends BaseDao<Admin,Integer > implements AdminDao{
	
	@Resource
	UserPowerDao userPowerDao;

	@Resource
	AdminRoleDao adminRoleDao;
	
	@Override
	public Admin getAdmin(Integer id,String username, String password) {
		String hql = "from admin where 1=1 ";
		List paramList = new ArrayList();
		if (id != null) {
			hql += " and id like ? ";
			paramList.add(id);
		}
		if (username != null) {
			hql += " and username like ? ";
			paramList.add(username);
		}
		if (password != null) {
			hql += " and password like ? ";
			paramList.add(password);
		}		
		Admin admin = (Admin)getByHQL(hql,paramList.toArray());		
		return admin;
	}
	
	public Admin getAdminById1(Integer id) {
		StringBuilder hql = new StringBuilder("from admin where 1 = 1 ");
		List paramList = new ArrayList();
		if (id != null) {
			hql.append(" and id = ? ");
			paramList.add(id);
		}	
		return getByHQL(hql.toString(), paramList.toArray());
	}

	@Override
	public List<Admin> getList(Integer id,String username) {	
		String hql = "from admin where 1=1 ";
		List paramList = new ArrayList();
		List<Admin> adminList;
		if (username != null) {
			hql += " and username like ? ";						
			paramList.add(username);
		} 
		if (id != null) {
			hql += " and id like ? ";						
			paramList.add(id);
		}		
		adminList = getListByHQL(hql, paramList.toArray());
		return adminList;
	}

	@Override
	public void addAdmin(Admin admin) {		
		save(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		update(admin);
		
	}
	@Override
	public void updatepwdAdmin(Admin admin) {
		update(admin);
		
	}

	@Override
	public PageResults<Admin> findPageModel(int pageNo, int pageSize, Object[] values) {		
		
		PageResults<Admin> pageResult = findPageByFetchedHql("from admin", "select count(*) from admin", pageNo, pageSize, values);
		
		return pageResult;
	}

	@Override
	public void deleteAdmin(Integer id) {
		delete(id);
	}

	@Override
	public List<Admin> getList(String userName, String pwd) {
		StringBuilder hql = new StringBuilder("from admin where (statue = 1 or statue is null or statue = '')");
		List paramList = new ArrayList();
		if (userName != null) {
			hql.append(" and username = ? ");
			paramList.add(userName);
		}
		if (pwd != null) {
			hql.append(" and password = ? ");
			paramList.add(pwd);
		}			
		List<Admin> admins = getListByHQL(hql.toString(), paramList.toArray());
		return admins;
	}

	@Override
	public List<Admin> getAll() {
		List<Admin> admins = getListByHQL("from admin where statue = 1 order by registDate desc", null);
		return admins;
	}

	@Override
	public void addUser(Admin admin, String[] powerList) {
		int id = save(admin);
		if(powerList != null && powerList.length > 0){
			for(int i = 0; i < powerList.length; i ++){
				UserPower userPower = new UserPower();
				userPower.setPowerId(Integer.valueOf(powerList[i]));
				userPower.setUserId(id);
				userPowerDao.saveUserPower(userPower);
			}
		}
		
		
	}
	
	@Override
	public PageResults<Admin> getPageResults(Integer pageNo, Integer pageSize) {
		StringBuilder hql = new StringBuilder("from admin where (statue = 1 or statue is null or statue = '') ");	
		StringBuilder hqlcount = new StringBuilder(" select count(*) from admin where statue=1 "); 	
		hql.append(" order by registDate desc ");
		PageResults<Admin> pageResults = findPageByFetchedHql(hql.toString(), hqlcount.toString(), pageNo, pageSize, null);		
		
		return pageResults;
	}

	@Override
	public List<Admin> getAdminById(Integer id) {
		StringBuilder hql = new StringBuilder("from admin where statue = 1 ");
		List paramList = new ArrayList();
		if (id != null) {
			hql.append(" and id = ? ");
			paramList.add(id);
		}	
		List<Admin> admins = getListByHQL(hql.toString(), paramList.toArray());
		return admins;
	}

	@Override
	public List<Admin> getList1(Integer id, String username) {
		String hql = "from admin where statue=1 ";
		List paramList = new ArrayList();
		List<Admin> adminList;
		if (username != null) {
			hql += " and username like ? ";						
			paramList.add(username);
		} 
		if (id != null) {
			hql += " and id like ? ";						
			paramList.add(id);
		}		
		adminList = getListByHQL(hql, paramList.toArray());
		return adminList;
	}

	@Override
	public List<Admin> getAdminByName(String userName) {
		String hql = "from admin where statue=1 ";
		List paramList = new ArrayList();
		List<Admin> adminList;
		if (userName != null) {
			hql += " and username = ? ";						
			paramList.add(userName);
		} 
		adminList = getListByHQL(hql, paramList.toArray());
		return adminList;
	}

	@Override
	public void updateAdminPower(String userId, String[] powerList) {
		List<UserPower> powers = userPowerDao.getPowersByid(Integer.valueOf(userId));
		if(powers != null){
			for (UserPower userPower : powers) {
				userPowerDao.deleteUserPower(userPower);
			}
		}
		if(powerList != null && powerList.length > 0){
			for(int i = 0; i < powerList.length; i ++){
				UserPower userPower = new UserPower();
				userPower.setPowerId(Integer.valueOf(powerList[i]));
				userPower.setUserId(Integer.valueOf(userId));
				userPowerDao.saveUserPower(userPower);
			}
		}
	}

	@Override
	public int addAdmin1(Admin admin) {
		return save(admin);
	}

	@Override
	public void updateAdminRole(String userId, String[] roleList) {
		List<AdminRole> roles = adminRoleDao.getRolesById(Integer.valueOf(userId));
		if(roles != null){
			for (AdminRole adminRole : roles) {
				adminRoleDao.deleteModel(adminRole);
			}
		}
		if(roleList != null && roleList.length > 0){
			for(int i = 0; i < roleList.length ; i ++){
				AdminRole adminRole = new AdminRole();
				adminRole.setRoleId(Integer.valueOf(roleList[i]));
				adminRole.setAdminId(Integer.valueOf(userId));
				adminRoleDao.saveModel(adminRole);
			}
		}
	}
		
	
	

}
