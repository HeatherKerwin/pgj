package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ry.core.dao.ActivecountDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Activecount;
import com.ry.core.form.ActivecountForm;

@Repository
public class ActivecountDaoImpl extends BaseDao<Activecount,Integer > implements ActivecountDao{

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActivecountDao#getCount()
	 */
	@Override
	public Long getAllCount() {
		return countByHql("select count(*) from activecount", null);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.dao.ActivecountDao#getCountByRpAndTime(java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public Long getCountByRpAndTime(String rp, Long beginTime, Long endTime) {
		StringBuffer hql = new StringBuffer("select count(*) from member m,activecount a  where  m.id=a.memberid ");
		List paramList = new ArrayList();
		if(StringUtils.hasText(rp)){
			hql.append("and m.recommendpeople = ?  ");
			paramList.add(rp);
		}
		if(beginTime!=null){
			hql.append("and a.activetime >= ?  ");
			paramList.add(beginTime);
		}
		if(endTime!=null){
			hql.append("and a.activetime <= ?  ");
			paramList.add(endTime);
		}
		return countByHql(hql.toString(), paramList.toArray());
	}
	

	@Override
	public Long getCountActive(String begintimed, String endtimed,
			Integer memberid) {
		List paramList = new ArrayList();
		StringBuilder hql = new StringBuilder("select count(*) from activecount where 1=1 ");
		if (begintimed != null) {
			hql.append("and activetime >= ? ");
			paramList.add(begintimed);
		}
		if (endtimed != null) {
			hql.append("and activetime <= ? ");
			paramList.add(endtimed);
		}
		if (memberid != null) {
			hql.append("and memberid like ? ");
			paramList.add(memberid);
		}
		
		return countByHql(hql.toString(), paramList.toArray());
	}	

	@Override
	public void addActivecount(Activecount activecount) {
		save(activecount);
	}

	@Override
	public Long countActive(Long begintimed, Long endtimed, Integer memberid) {
		List paramList = new ArrayList();
		StringBuilder hql = new StringBuilder("select count(*) from activecount where 1=1 ");
		if (begintimed != null) {
			hql.append("and activetime >= ? ");
			paramList.add(begintimed);
		}
		if (endtimed != null) {
			hql.append("and activetime <= ? ");
			paramList.add(endtimed);
		}
		if (memberid != null) {
			hql.append("and memberid like ? ");
			paramList.add(memberid);
		}
		
		return countByHql(hql.toString(), paramList.toArray());
	}

	@Override
	public List<Activecount> getList(ActivecountForm activecountForm) {
		
		StringBuilder hql = new StringBuilder("from activecount where 1=1 ");
		List paramList = new ArrayList();
		if (activecountForm.getBegintimelong() != null) {
			hql.append(" and activetime >= ? ");
			paramList.add(activecountForm.getBegintimelong());
		}
		
		if (activecountForm.getEndtimelong() != null) {
			hql.append(" and activetime <= ? ");
			paramList.add(activecountForm.getEndtimelong());
		}
		
		if (activecountForm.getMemberid() != null && activecountForm.getMemberid() > 0) {
			hql.append(" and memberid like ? ");
			paramList.add(activecountForm.getMemberid());
		}
		
		return getListByHQL(hql.toString(), paramList.toArray());
	}
	
}
