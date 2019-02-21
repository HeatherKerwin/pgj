package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgCityDao;
import com.ry.core.entity.OrgCity;

@Repository
public class OrgCityDaoImpl extends BaseDao<OrgCity, Integer> implements OrgCityDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getById(java.lang.Integer)
	 */
	@Override
	public OrgCity getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getOrgCity(com.ry.core.entity.OrgCity)
	 */
	public OrgCity getOrgCity(OrgCity orgCity){
		StringBuffer hql = new StringBuffer("FROM org_city WHERE city_id=? AND address=? and org_id=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(orgCity.getCityId());
		params.add(orgCity.getAddress());
		params.add(orgCity.getOrgId());
		List<OrgCity> list = getListByHQL(hql.toString(), params.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getByOrgId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getRegionByOrgId(Integer orgId) {
		StringBuffer sql = new StringBuffer("SELECT * FROM region WHERE id IN (SELECT city_id FROM org_city WHERE org_id=?)"); 
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getInOrgIdAndCityName(java.util.List, java.lang.String)
	 */
	public List<Map<String,Object>> getInOrgIdAndCityName(List<Integer> orgIds,String cityName) {
		if(orgIds==null || orgIds.size()==0)return null;
		StringBuffer sql = new StringBuffer("SELECT oc.org_id orgId,oc.city_id,r.name,r.name_en FROM org_city oc LEFT JOIN region r ON oc.city_id=r.id WHERE r.name LIKE ? "); 
		List<Object> params = new ArrayList<Object>();
		params.add("%"+cityName+"%");
		
		if(orgIds!=null && orgIds.size()>0){
			sql.append(" AND oc.org_id IN ( ");
			String h = "";
			for(Integer id:orgIds){
				if(StringUtils.hasText(h))h += ",";
				h += "?";
				params.add(id);
			}
			sql.append(h+")");
		}
		sql.append(" GROUP BY oc.org_id");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getInOrgIdAndCityId(java.util.List, java.lang.Integer)
	 */
	public List<Map<String,Object>> getInOrgIdAndCityId(List<Integer> orgIds,Integer cityId) {
		if(orgIds==null || orgIds.size()==0)return null;
		StringBuffer sql = new StringBuffer("SELECT oc.org_id orgId,oc.city_id,r.name,r.name_en FROM org_city oc LEFT JOIN region r ON oc.city_id=r.id WHERE r.id = ? "); 
		List<Object> params = new ArrayList<Object>();
		params.add(cityId);
		
		if(orgIds!=null && orgIds.size()>0){
			sql.append(" AND oc.org_id IN ( ");
			String h = "";
			for(Integer id:orgIds){
				if(StringUtils.hasText(h))h += ",";
				h += "?";
				params.add(id);
			}
			sql.append(h+")");
		}
		sql.append(" GROUP BY oc.org_id");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#deleteByOrgId(java.lang.Integer)
	 */
	public void deleteByOrgId(Integer orgId) throws Exception{
		if(orgId==null)throw new Exception("数据异常");
		StringBuffer sql = new StringBuffer("DELETE FROM org_city WHERE org_id=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		executeSql(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#saveModel(com.ry.core.entity.OrgCity)
	 */
	public void saveModel(OrgCity orgCity) {
		save(orgCity);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#deleteById(java.lang.Integer)
	 */
	public void deleteById(Integer id) {
		delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#updateModel(com.ry.core.entity.OrgCity)
	 */
	public void updateModel(OrgCity orgCity) {
		update(orgCity);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getByOrgId(java.lang.Integer)
	 */
	public List<OrgCity> getByOrgId(Integer orgId) {
		StringBuffer hql = new StringBuffer("FROM org_city WHERE orgId=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getCityByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getCityByOrgId(Integer orgId) {
		StringBuffer sql = new StringBuffer("select r.code code,r.name name,oc.id id,oc.org_id orgid,oc.city_id cityid from org_city oc"
				+ " left join region r on oc.city_id = r.id where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (orgId != null) {
			sql.append(" and oc.org_id = ?");
			params.add(orgId);
		}
		sql.append(" group by name");
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	@Override
	public List<Map<String, Object>> getOrgCityList() {
		StringBuffer sql = new StringBuffer(" SELECT r.id id ,r.name name FROM region r WHERE r.id IN( SELECT city_id FROM org_city ) ORDER BY id ");
		List<Object> params = new ArrayList<Object>();
		return getListMapBySQL(sql.toString(), params.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgCityDao#getAndHasPriceByOrgId(java.lang.Integer, java.lang.String)
	 */
	public List<Map<String,Object>> getAndHasPriceByOrgId(Integer orgId,String day) {
		StringBuffer sql = new StringBuffer("SELECT res1.*,res2.name FROM (SELECT r1.*,r2.city_id cityId FROM (SELECT * FROM org_city oc WHERE oc.org_id=?)r1 LEFT JOIN(SELECT * FROM price p WHERE p.org_id=? AND DATE_FORMAT(p.create_time,'%Y-%m-%d')=? GROUP BY p.city_id)r2 ON r1.city_id=r2.city_id"); 
		sql.append(")res1 LEFT JOIN(SELECT * FROM region)res2 ON res1.city_id=res2.id");
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		params.add(orgId);
		params.add(day);
		return getListMapBySQL(sql.toString(), params.toArray());
	}
}