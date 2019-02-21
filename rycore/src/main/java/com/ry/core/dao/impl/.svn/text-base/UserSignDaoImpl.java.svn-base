package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.UserSignDao;
import com.ry.core.entity.UserSign;

@Repository
public class UserSignDaoImpl extends BaseDao<UserSign, Integer> implements UserSignDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.UserSignDao#saveModel()
	 */
	@Override
	public void saveModel(UserSign userSign) {
		if(userSign!=null && userSign.getId()!=null){
			update(userSign);
		}else{
			save(userSign);
		}
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.UserSignDao#getByModel(java.lang.Integer, java.util.Date)
	 */
	@Override
	public UserSign getByModel(Integer MemberId, String day) {
		StringBuffer hql = new StringBuffer(" from user_sign where 1=1 "); 
		List<Object> paramList = new ArrayList<Object>();
		if(MemberId != null){
			hql.append(" and memberId = ?");
			paramList.add(MemberId);
		}
		if(day != null ){
			hql.append(" and DATE_FORMAT(sign_time,'%Y-%m-%d') = ?");
			paramList.add(day);
		}
		List<UserSign> list = getListByHQL(hql.toString(), paramList.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
