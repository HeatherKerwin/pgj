package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.AwardDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Award;

@Repository
public class AwardDaoImpl extends BaseDao<Award,Integer> implements AwardDao{
   
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AwardDao#getByMemberId(java.lang.Integer)
	 */
	public List<Award> getByMemberId(Integer memberId) {
		StringBuffer hql = new StringBuffer("from award where memberId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		return queryByHql(hql.toString(), paramList.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.AwardDao#saveAward(com.ry.core.entity.Award)
	 */
	public void saveAward(Award award){
		if(award.getId()!=null){
			update(award);
		}else{
			save(award);
		}
	}
}