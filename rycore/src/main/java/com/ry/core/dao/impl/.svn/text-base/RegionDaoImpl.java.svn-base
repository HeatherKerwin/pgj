package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RegionDao;
import com.ry.core.entity.Region;

@Repository
public class RegionDaoImpl extends BaseDao<Region, Integer> implements RegionDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RegionDao#getById(java.lang.Integer)
	 */
	@Override
	public Region getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RegionDao#getByType(java.lang.String)
	 */
	public List<Region> getByType(String type) {
		StringBuffer hql = new StringBuffer("from region where type=?");
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RegionDao#getByNameAndType(java.lang.String, java.lang.String)
	 */
	public List<Region> getByNameAndType(String name,String type) {
		StringBuffer hql = new StringBuffer("from region where type=? AND (nameEn like ? or name like ?)");
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		params.add(name+"%");
		params.add(name+"%");
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.RegionDao#getByCode(java.lang.String)
	 */
	public Region getByCode(String code){
		StringBuffer hql = new StringBuffer("from region where code=?");
		List<Object> params = new ArrayList<Object>();
		params.add(code);
		List<Region> list = getListByHQL(hql.toString(), params.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RegionDao#getByParent(java.lang.Integer)
	 */
	@Override
	public List<Region> getByParent(Integer parent_id) {
		StringBuffer hql = new StringBuffer("from region where parent_id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(parent_id);
		return getListByHQL(hql.toString(), params.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.RegionDao#getByName(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getByName(Integer parent_id) {
		StringBuffer sql = new StringBuffer("select name as sub from region where parent_id=?");
		List<Object> params = new ArrayList<Object>();
		params.add(parent_id);
		return getListMapBySQL(sql.toString(),params.toArray());
	}
}