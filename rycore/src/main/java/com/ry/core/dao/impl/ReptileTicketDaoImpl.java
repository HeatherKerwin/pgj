package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.ReptileTicketDao;
import com.ry.core.entity.ReptileTicket;
import com.ry.util.page.PageResults;

@Repository
public class ReptileTicketDaoImpl extends BaseDao<ReptileTicket, Integer> implements ReptileTicketDao {

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ReptileTicktDao#getById(java.lang.Integer)
	 */
	@Override
	public ReptileTicket getById(Integer id) throws Exception {
		return get(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ReptileTicktDao#saveModel(com.ry.core.entity.ReptileTicket)
	 */
	@Override
	public void saveModel(ReptileTicket reptileTicket) {
		save(reptileTicket);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ReptileTicktDao#getPageList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<ReptileTicket> getPageList(Integer currentPage, Integer pageSize) {
		StringBuffer hql=new StringBuffer("from reptile_ticket order by create_time asc");
		List<Object> paramList = new ArrayList<Object>();
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, currentPage, pageSize, paramList.toArray());
	}

}
