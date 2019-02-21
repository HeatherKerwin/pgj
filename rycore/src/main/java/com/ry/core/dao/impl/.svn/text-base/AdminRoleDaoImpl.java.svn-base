package com.ry.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.AdminRoleDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.da.AdminRole;

@Repository
public class AdminRoleDaoImpl extends BaseDao<AdminRole, Integer> implements AdminRoleDao {

	@Override
	public void saveModel(AdminRole adminRole) {
		save(adminRole);
	}

	@Override
	public List<Integer> getListByUserId(Integer id) {
		List<Integer> lists =  getListBySQL("select role_id from admin_role where admin_id = ? order by role_id", new Object[]{id});
		return lists;
	}
	
	@Override
	public List<AdminRole> getRolesById(Integer id) {
		List<AdminRole> lists = getListByHQL("from admin_role where admin_id = ?", new Object[]{id});
		return lists;
	}

	@Override
	public void deleteModel(AdminRole adminRole) {
		deleteObject(adminRole);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AdminRoleDao#getByAdminIdAndRoleId(java.lang.Integer, java.lang.Integer)
	 */
	public List<AdminRole> getByAdminIdAndRoleId(Integer adminId,Integer roleId) {
		List<AdminRole> lists = getListByHQL("from admin_role where admin_id=? AND role_id=?", new Object[]{adminId,roleId});
		return lists;
	}
}