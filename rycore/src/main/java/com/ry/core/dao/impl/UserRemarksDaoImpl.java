package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.UserRemarksDao;
import com.ry.core.entity.UserRemarks;
import com.ry.util.datatable.BasePageRequestData;
import com.ry.util.page.PageResults;

@Repository
public class UserRemarksDaoImpl extends BaseDao<UserRemarks, Integer> implements UserRemarksDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#getById(java.lang.Integer)
	 */
	public UserRemarks getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#saveModel(com.ry.core.entity.DistributeOrderSp)
	 */
	public void saveModel(UserRemarks userRemarks) {
		save(userRemarks);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.DistributeOrderSpDao#updateModel(com.ry.core.entity.DistributeOrderSp)
	 */
	public void updateModel(UserRemarks userRemarks){
		update(userRemarks);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.UserRemarksDao#getList(java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<UserRemarks> getPageList(BasePageRequestData req,UserRemarks userRemarks) throws Exception{
		StringBuffer hql = new StringBuffer("FROM user_remarks WHERE member_id = ? AND source = ? order by create_time desc");
		StringBuffer count = new StringBuffer("select count(*) FROM user_remarks WHERE member_id = ? AND source = ? order by create_time desc");
		List<Object> params = new ArrayList<Object>();
		params.add(userRemarks.getMemberId());
		params.add(userRemarks.getSource());
		return findPageByFetchedHql(hql.toString(), count.toString(), req.currentPage().intValue(), req.getLength().intValue(), params.toArray());
	}
}