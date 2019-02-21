package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.PreferentialInfoDao;
import com.ry.core.entity.PreferentialInfo;
import com.ry.util.page.PageResults;

@Repository
public class PreferentialInfoDaoImpl extends BaseDao<PreferentialInfo,Integer> implements PreferentialInfoDao{
   
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PreferentialInfoDao#getPageList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<PreferentialInfo> getPageList(Integer currentPage,Integer pageSize) {
		StringBuffer hql = new StringBuffer("from preferential_info order by createTime desc");
		String countHql = "select count(*) "+ hql.toString();
		return findPageByFetchedHql(hql.toString(),countHql,currentPage,pageSize,null);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.PreferentialInfoDao#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer id) {
		delete(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PreferentialInfoDao#getById(java.lang.Integer)
	 */
	@Override
	public PreferentialInfo getById(Integer id) {
		return get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PreferentialInfoDao#saveInfo(com.ry.core.entity.PreferentialInfo)
	 */
	public void saveInfo(PreferentialInfo info)throws Exception {
		if(info!=null && info.getId()!=null)update(info);
		else save(info);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.dao.PreferentialInfoDao#getBetweenCreateTime(java.util.Date, java.util.Date)
	 */
	public List<PreferentialInfo> getBetweenCreateTime(Date start,Date end) {
		StringBuffer hql = new StringBuffer("from preferential_info where createTime BETWEEN ? AND ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(start);
		paramList.add(end);
		return queryByHql(hql.toString(), paramList.toArray());
	}
}