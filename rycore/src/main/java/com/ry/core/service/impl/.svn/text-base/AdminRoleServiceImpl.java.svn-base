package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AdminRoleDao;
import com.ry.core.entity.da.AdminRole;
import com.ry.core.service.AdminRoleService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Resource
	AdminRoleDao adminRoleDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.adminRoleService#save(com.ry.core.entity.da.adminRole)
	 */
	public void save(AdminRole adminRole) {
		adminRoleDao.saveModel(adminRole);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.adminRoleService#getListBydUserId(java.lang.Integer)
	 */
	public List<Integer> getListByUserId(Integer id) {
		return adminRoleDao.getListByUserId(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.adminRoleService#getById(java.lang.Integer)
	 */
	public List<AdminRole> getRolesById(Integer id) {
		return adminRoleDao.getRolesById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.adminRoleService#delete(com.ry.core.entity.da.adminRole)
	 */
	public void delete(AdminRole adminRole) {
		adminRoleDao.deleteModel(adminRole);
	}

	public List<AdminRole> getByAdminIdAndRoleId(Integer adminId,Integer roleId){
		return adminRoleDao.getByAdminIdAndRoleId(adminId, roleId);
	}
}