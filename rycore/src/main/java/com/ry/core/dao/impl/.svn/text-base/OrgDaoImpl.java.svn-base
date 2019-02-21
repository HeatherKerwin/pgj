package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgDao;
import com.ry.core.entity.Org;
import com.ry.core.util.Utility;

@Repository
public class OrgDaoImpl extends BaseDao<Org, Integer> implements OrgDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#saveModel(com.ry.core.entity.Org)
	 */
	public void saveModel(Org org) {
		 if(org.getId()!=null){
			 update(org);
		 }else{
			 save(org);
		 }
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getByMemberId(java.lang.Integer)
	 */
	public Org getByMemberId(Integer memberId) throws Exception{
		StringBuffer hql = new StringBuffer("from org where memberId=?");
		List<Object> param = new ArrayList<Object>();
		param.add(memberId);
		return getByHQL(hql.toString(),param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getById(java.lang.Integer)
	 */
	public Org getById(Integer id) throws Exception{
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getByType(java.lang.Integer)
	 */
	public List<Org> getByType(Integer type) {
		StringBuffer hql = new StringBuffer("from org where type=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(type);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getOrgAndNameByType(java.lang.Integer)
	 */
	public List<Map<String, Object>> getOrgAndNameByType(Integer type) {
		StringBuffer hql = new StringBuffer("select o.*, oi.company from org o left join org_info oi on o.id = oi.org_id where o.type_=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(type);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getOrgAndNameByType1(java.lang.Integer)
	 */
	public List<Map<String, Object>> getOrgAndNameByType1(Integer type) {
		StringBuffer hql = new StringBuffer("select oi.* from  org_info oi where oi.type_=? GROUP BY company ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(type);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#searchCityByOrgId(java.lang.Integer)
	 */
	public List<Map<String, Object>> searchCityByOrgId(Integer orgId) throws Exception {
		StringBuffer sql = new StringBuffer("select oc.*,r.name,r.parent_id from org_city oc left join region r on oc.city_id = r.id where oc.org_id = ?");
		List<Object> param = new ArrayList<Object>();
		param.add(orgId);
		return getListMapBySQL(sql.toString(), param.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getOrgCityInfoById(java.lang.Integer)
	 */
	public List<Map<String, Object>> getOrgCityInfoById(Integer orgCityId) throws Exception {
		StringBuffer sql = new StringBuffer("select oc.*,r.name,r.parent_id from org_city oc left join region r on oc.city_id = r.id where oc.id = ?");
		List<Object> param = new ArrayList<Object>();
		param.add(orgCityId);
		return getListMapBySQL(sql.toString(), param.toArray());
	}

	@Override
	public List<Map<String, Object>> getMemberListByCompany(String company) {
		StringBuffer sql = new StringBuffer("SELECT m.*,oi.member_id FROM org_info oi , member m WHERE oi.member_id = m.id ");
		List<Object> param = new ArrayList<Object>();
		if(company!=null){
			sql.append(" AND oi.company = ? ");
			param.add(company);
		}
		sql.append(" GROUP BY member_id ");
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), param.toArray());
		return Utility.decodeMobile(list);//@WKX EDIT2016-08-17  手机号转码
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#getCityListByMemId(java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> getCityListByMemId(Integer memberId) {
		StringBuffer sql = new StringBuffer("SELECT r.code code,r.name name,oc.id id,oc.org_id orgid,oc.city_id cityid FROM org_city oc,region r,org_info oi WHERE oi.org_id = oc.org_id AND oc.city_id = r.id ");
		List<Object> param = new ArrayList<Object>();
		if(memberId!=null){
			sql.append(" AND oi.member_id = ? ");
			param.add(memberId);
		}
		sql.append(" GROUP BY cityid ");
		return getListMapBySQL(sql.toString(), param.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgDao#updateModel(com.ry.core.entity.Org)
	 */
	public void updateModel(Org org) {
		update(org);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.MemberDao#getOrgByTypeState(java.lang.Integer, java.lang.Integer)
	 */
	public List<Map<String,Object>> getOrgByTypeState(Integer type,Integer state) {
		StringBuffer sql = new StringBuffer("SELECT m.id,m.mobile,o.org_id,o.company,o.name,o.phone FROM (SELECT * FROM org_info oi WHERE oi.type_=? AND oi.state=? GROUP BY oi.member_id)o LEFT JOIN member m ON m.id=o.member_id");
		List<Object> params = new ArrayList<Object>();
		params.add(type);
		params.add(state);
		List<Map<String,Object>> list = getListMapBySQL(sql.toString(), params.toArray());
		return Utility.decodeMobile(list);//手机号转码
	}
}