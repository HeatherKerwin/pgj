package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.TagDao;
import com.ry.core.entity.Tag;

@Repository
public class TagDaoImpl extends BaseDao<Tag,Integer> implements TagDao{
   
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.TagDao#getByCode(java.lang.String)
	 */
	@Override
	public List<Tag> getByCode(String code) {
		StringBuffer hql = new StringBuffer("from tag where code=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(code);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.TagDao#getByParentId(java.lang.Integer)
	 */
	@Override
	public List<Tag> getByParentId(Integer parentId) {
		StringBuffer hql = new StringBuffer("from tag where parentId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(parentId);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.TagDao#getByParentCode(java.lang.String)
	 */
	public List<Tag> getByParentCode(String code) {
		StringBuffer hql = new StringBuffer("from tag where parentId =(select id from tag where code=?)");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(code);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.TagService#getById(java.lang.Integer)
	 */
	public Tag getById(Integer id){
		return get(id);
	}
}