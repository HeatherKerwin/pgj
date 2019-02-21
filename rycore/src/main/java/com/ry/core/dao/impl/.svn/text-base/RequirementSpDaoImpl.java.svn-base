package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.RequirementSpDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.RequirementSp;
@Repository
public class RequirementSpDaoImpl extends BaseDao<RequirementSp, Integer> implements RequirementSpDao {

	@Override
	public RequirementSp getById(Integer id) {
		return get(id);
	}

	@Override
	public void saveModel(RequirementSp requirementsp) {
		save(requirementsp);
	}

	@Override
	public void updateModel(RequirementSp requirementsp) {
		update(requirementsp);
	}
	
	@Override
	public RequirementSp getByAll(Integer orgId,Integer type,String time){
		StringBuffer sql = new StringBuffer("from requirement_sp p where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (orgId != null) {
			sql.append(" and p.orgId =?");
			paramsList.add(orgId);
		}
		if (StringUtils.hasText(time)) {
			sql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') = ? ");
			paramsList.add(time);
		}
		if (type != null) {
			sql.append(" and p.type =?");
			paramsList.add(type);
		}
		List<RequirementSp> list = getListByHQL(sql.toString(), paramsList.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public void saveOrUpdate(RequirementSp requirementSp){
		if(requirementSp!=null && requirementSp.getId()!=null){
			update(requirementSp);
		}else{
		    save(requirementSp);
		}
	}
	
	@Override
	public List<RequirementSp> getByOrgId(Integer orgId,String time){
		StringBuffer hql = new StringBuffer("from requirement_sp where orgId = ?");
		List<Object> paramsList= new ArrayList<Object>();
		paramsList.add(orgId);
		if (StringUtils.hasText(time)) {
			hql.append(" and DATE_FORMAT(createTime,'%Y-%m-%d') = ? ");
			paramsList.add(time);
		}else{
			hql.append(" and DATE_FORMAT(createTime,'%Y-%m-%d') = curdate() ");
		}
		return getListByHQL(hql.toString(), paramsList.toArray());
	}
}
