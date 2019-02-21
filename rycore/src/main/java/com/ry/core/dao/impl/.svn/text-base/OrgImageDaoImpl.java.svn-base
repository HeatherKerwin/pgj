package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgImageDao;
import com.ry.core.entity.OrgImage;


@Repository
public class OrgImageDaoImpl extends BaseDao<OrgImage, Integer> implements OrgImageDao{
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgImageDao#getById(java.lang.Integer)
	 */
	@Override
	public OrgImage getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgImageDao#getByOrgId(java.lang.Integer)
	 */
	public List<Map<String,Object>> getByOrgId(Integer orgId){
		StringBuffer hql = new StringBuffer("SELECT * FROM org_image WHERE id= (SELECT MAX(id) FROM org_image WHERE org_id=?)"); 
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(orgId);
		return getListMapBySQL(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.OrgImageDao#saveModel(com.ry.core.entity.OrgImage)
	 */
	public void saveModel(OrgImage orgImage) {
		if(orgImage!=null && orgImage.getId()!=null)update(orgImage);
		else save(orgImage);
	}
}