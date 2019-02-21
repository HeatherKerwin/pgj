package com.ry.core.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RoleDao;
import com.ry.core.entity.Accountrecord;
import com.ry.core.entity.Menu;
import com.ry.core.entity.Role;
import com.ry.util.page.PageResults;

@Repository
public class RoleDaoImpl extends BaseDao<Role, Integer> implements RoleDao {

	@Override
	public int saveRole(Role role) {
		return save(role);
	}

	@Override
	public List<Role> getList(String name) {
		List<Role> roles = (List<Role>) getListByHQL("from role where name = ? and (state = 1 or state is null)", new Object[]{name});
		return roles;
	}

	@Override
	public void updateRole(Role role) {
		update(role);
		
	}

	@Override
	public PageResults<Role> getPageRoleResults(Integer pageNo, Integer pageSize) {
		StringBuilder hql = new StringBuilder("select * from role where (state=1 or state is null )  ");	
		StringBuilder hqlcount = new StringBuilder(" select count(*) from role where (state=1 or state is null )"); 
		PageResults< Role> pageResults = findPageByFetchedSql(hql.toString(), hqlcount.toString(), pageNo, pageSize, null);		
		return pageResults;
	}

	@Override
	public Role getRoleById(Integer id) {
		
		return get(id);
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = (List<Role>) getListByHQL("from role where state = 1 or state is null", null);
		return roles;
	}

	@Override
	public List<Map<String, Object>> initRole() {
		String sql = "select id,name from role where state = 1";
		return getListMapBySQL(sql, null);
	}

}
