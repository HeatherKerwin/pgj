package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.DiscussionDao;
import com.ry.core.entity.Discussion;

@Repository
public class DiscussionDaoImpl extends BaseDao<Discussion, Integer> implements DiscussionDao {
	
	public void saveModel(Discussion discussion) {
		save(discussion);
	}
	
	public void updateModel(Discussion discussion) {
		update(discussion);
	}
	
	public Discussion getById(Integer id){
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.DiscussionDao#getByPhone(java.lang.String)
	 */
	public Discussion getByPhone(String phone){
		String hql = "from discussion where phone=?";
		List<Object> paramList = new ArrayList<Object>();		
		paramList.add(phone);
		List<Discussion> list = getListByHQL(hql, paramList.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}