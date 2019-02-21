package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgPartnerDao;
import com.ry.core.entity.OrgPartner;

@Repository
public class OrgPartnerDaoImpl extends BaseDao<OrgPartner, Integer> implements OrgPartnerDao{

	@Override
	public OrgPartner getById(Integer id) {
		return get(id);
	}
	
	@Override
	public void saveModel(OrgPartner orgPartner) {
		save(orgPartner);
	}
	
	@Override
	public void deleteById(Integer id) {
		delete(id);
	}
	
	@Override
	public void updateModel(OrgPartner orgPartner) {
		update(orgPartner);
	}
	
	@Override
	public List<OrgPartner> getByOrgId(Integer orgId) {
		StringBuffer hql = new StringBuffer("FROM org_partner WHERE orgId=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(orgId);
		return getListByHQL(hql.toString(), params.toArray());
	}
	
	@Override
	public List<Map<String,Object>> getByMemberId(Integer memberId) {
		StringBuffer hql = new StringBuffer("SELECT op.* FROM org_partner op LEFT JOIN org o ON op.org_id=o.id WHERE o.member_id=?"); 
		List<Object> params = new ArrayList<Object>();
		params.add(memberId);
		return getListMapBySQL(hql.toString(), params.toArray());
	}
}