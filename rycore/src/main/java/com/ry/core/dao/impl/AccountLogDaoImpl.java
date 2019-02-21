package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.AccountLogDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.AccountLog;

@Repository
public class AccountLogDaoImpl extends BaseDao<AccountLog, Integer> implements AccountLogDao {

	@Override
	public void saveModel(AccountLog accountLog) {
		save(accountLog);
	}
	
	@Override
	public void updateModel(AccountLog accountLog) {
		update(accountLog);
	}
	
	@Override
	public List<AccountLog> getByNo(String no){
		StringBuffer hql = new StringBuffer("FROM account_log WHERE no=? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(no);
		return queryByHql(hql.toString(), paramList.toArray());
	}
}