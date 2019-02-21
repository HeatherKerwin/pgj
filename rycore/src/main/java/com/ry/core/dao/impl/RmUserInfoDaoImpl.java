package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RmUserInfoDao;
import com.ry.core.entity.RmUserInfo;
import com.ry.util.page.PageResults;

@Repository
public class RmUserInfoDaoImpl extends BaseDao<RmUserInfo, Integer> implements RmUserInfoDao {
	
	public Integer add(RmUserInfo rui) {
		return save(rui);
	}

	public List<RmUserInfo> getList(String code, Date startTime, Date endTime) {
		StringBuffer hql = new StringBuffer("from rmuserinfo where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (startTime != null && endTime != null) {
			hql.append(" and createTime between ? and ?");
			params.add(startTime);
			params.add(endTime);
		}
		if (!"".equals(code.trim()) && code != null) {
			hql.append(" and code = ? or mycode = ?");
			params.add(code);
			params.add(code);
		}
		return getListByHQL(hql.toString(), params.toArray());
	}

	public PageResults<RmUserInfo> getPageList(String code, Date startTime, Date endTime,
			Integer currentPage, Integer pageSize) {
		StringBuffer hql = new StringBuffer("from rmuserinfo where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (startTime != null && endTime != null) {
			hql.append(" and createTime between ? and ?");
			params.add(startTime);
			params.add(endTime);
		}
		hql.append(" and (code is null or code = '')");
		if (code != null && code != "") {
			hql.append(" and mycode = ?");
			params.add(code);
		}
		String countHql = "select count(*) "+hql.toString();
		return findPageByFetchedHql(hql.toString(), countHql, currentPage, pageSize, params.toArray());
	}
	
	public List<RmUserInfo> getListByTime(Date startTime, Date endTime) {
		StringBuffer hql = new StringBuffer("from rmuserinfo where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (startTime != null && endTime != null) {
			hql.append(" and createTime between ? and ?");
			params.add(startTime);
			params.add(endTime);
		}
		hql.append(" and code is not null and code != ''");
		return getListByHQL(hql.toString(), params.toArray());
	}
}
