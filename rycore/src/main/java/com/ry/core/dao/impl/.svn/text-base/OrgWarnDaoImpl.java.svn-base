package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.OrgWarnDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.OrgWarn;
import com.ry.util.page.PageResults;
@Repository
public class OrgWarnDaoImpl extends BaseDao<OrgWarn, Integer> implements OrgWarnDao {

	@Override
	public OrgWarn getById(Integer id) {
		return get(id);
	}

	@Override
	public void saveModel(OrgWarn orgWarn) {
		save(orgWarn);
	}

	@Override
	public void updateModel(OrgWarn orgWarn) {
		update(orgWarn);
	}
	
	@Override
	public PageResults<Map<String,Object>> getPageList(Integer currentPage, Integer pageSize,String name)  {
		StringBuffer sql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>();
		sql.append(" SELECT ow.id as id,ow.org_id as orgId,oi.company as name ,oi.phone as phone FROM org_warn ow LEFT JOIN org_info oi On oi.org_id=ow.org_id where ow.state=0 and oi.type_=1 ");
		sql.append(" and date_format(ow.day, '%Y-%m-%d') = curdate() ");
		if(StringUtils.hasText(name)){
			sql.append("and oi.company like ? ");	
			paramList.add("%"+ name +"%");
		}
		sql.append(" group BY ow.id ");
		String count1 = "SELECT COUNT(*)  FROM ( " + sql.toString()+ " )b ";
		return findPageMapByFetchedSql(sql.toString(),count1,currentPage,pageSize,paramList.toArray());
		
	}
	
	@Override
	public OrgWarn getByOrgId(Integer orgId,String day) {
		StringBuffer hql = new StringBuffer("from org_warn ow where ow.orgId = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orgId);
		if(StringUtils.hasText(day)){
			hql.append(" and date_format(ow.day, '%Y-%m-%d') = ? ");
			paramList.add(day);
		}else{
			hql.append(" and date_format(ow.day, '%Y-%m-%d') = curdate() ");
		}
		List<OrgWarn> org = getListByHQL(hql.toString(), paramList.toArray());
		if(org != null && org.size()>0){
			return org.get(0);
		}else{
			return null;
		}
	}
}