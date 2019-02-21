package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.IntegraltradingDetailDao;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.util.page.PageResults;

@Repository
public class IntegraltradingDetailDaoImpl extends BaseDao<IntegraltradingDetail, Integer> implements IntegraltradingDetailDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegraltradingDetailDao#getById(java.lang.Integer)
	 */
	@Override
	public IntegraltradingDetail getById(Integer id) {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegraltradingDetailDao#getList(com.ry.core.entity.IntegraltradingDetail)
	 */
	@Override
	public List<IntegraltradingDetail> getList(IntegraltradingDetail integraltradingDetail) {
		StringBuffer hql = new StringBuffer("FROM integral_trading_detail WHERE memberId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(integraltradingDetail.getMemberId());
		hql.append(" ORDER BY id DESC");
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegraltradingDetailDao#getPageList(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<IntegraltradingDetail> getPageList(Integer pageIndex, Integer pageSize, Integer memberId) {
		StringBuffer hql = new StringBuffer("from integral_trading_detail where 1=1 and memberId=?");
		StringBuffer count = new StringBuffer("select count(*) from integral_trading_detail where 1=1 and memberId=?");
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		
		hql.append(" order by id desc");
		PageResults<IntegraltradingDetail> page = findPageByFetchedHql(hql.toString(),count.toString(),pageIndex,pageSize,paramList.toArray());
		return page;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegraltradingDetailDao#getByMemberId(java.lang.Integer)
	 */
	@Override
	public List<IntegraltradingDetail> getByMemberId(Integer memberId) {
		StringBuffer hql = new StringBuffer("FROM integral_trading_detail WHERE memberId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		hql.append(" ORDER BY id DESC");
		return getListByHQL(hql.toString(), paramList.toArray());
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.IntegraltradingDetailDao#saveModel(com.ry.core.entity.IntegraltradingDetail)
	 */
	@Override
	public void saveModel(IntegraltradingDetail integraltradingDetail) {
		save(integraltradingDetail);
	}

}
