package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.MenuDao;
import com.ry.core.entity.Menu;
import com.ry.util.page.PageResults;

@Repository
public class MenuDaoImpl extends BaseDao<Menu, Integer> implements MenuDao {

	@Override
	public PageResults<Menu> getPageResults(Integer pageNo, Integer pageSize) {
		StringBuilder hql = new StringBuilder("select * from menu where (state=1 or state is null ) ");	
		StringBuilder hqlcount = new StringBuilder(" select count(*) from menu where (state=1 or state is null )"); 	
		hql.append(" order by type_,parent_id ");
		PageResults< Menu> pageResults = findPageByFetchedSql(hql.toString(), hqlcount.toString(), pageNo, pageSize, null);		
		return pageResults;
	}
	
	@Override
	public PageResults<Map<String,Object>> getPageResults1(Integer pageNo, Integer pageSize) {
		StringBuffer sql = new StringBuffer();
		sql.append(" FROM menu m LEFT JOIN menu mm ON m.parent_id=mm.id where (m.state=1 or m.state is null) ");
		sql.append(" ORDER BY m.parent_id,m.sort ");
		String count = "select count(*) "+ sql.toString();
		return findPageMapByFetchedSql("select m.name as name,m.sort as sort,mm.name as parent_name,m.id as id "+sql.toString(),count,pageNo,pageSize,null);
	}
	
	@Override
	public Menu getMenuById(Integer id) {
		return get(id);
	}

	@Override
	public void updateMenu(Menu menu) {
		update(menu);
		
	}

	@Override
	public void saveMenu(Menu menu) {
		save(menu);
		
	}

	@Override
	public List<Map<String, Object>> initMenu() {
		String sql = "select id,parent_id pId,name from menu where state = 1 or state is null";
		return getListMapBySQL(sql, null);
	}

	@Override
	public List<Map<String, Object>> getMenuListById(Integer id) {
		String sql = "SELECT * FROM menu WHERE id IN (SELECT menu_id FROM rolePower WHERE role_id IN(SELECT role_id FROM admin_role WHERE admin_id = ?)) ORDER BY sort ASC";
		return getListMapBySQL(sql, new Object[]{id});
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.MenuDao#getListByParentId(java.lang.Integer)
	 */
	public List<Menu> getListByParentId(Integer parentId) {
		StringBuffer hql =  new StringBuffer("FROM menu WHERE parent_id = ? AND state = 1 ORDER BY sort ");
		List<Object> params = new ArrayList<Object>();
		params.add(parentId);	
		return getListByHQL(hql.toString(), params.toArray());
	}
}