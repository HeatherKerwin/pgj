package com.ry.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.dao.AccountDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Account;

@Repository
public class AccountDaoImpl extends BaseDao<Account, Integer> implements AccountDao {

	public Account getByMemberId(Integer memberId){
		StringBuffer hql = new StringBuffer("FROM account WHERE memberId=?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(memberId);
		List<Account>list = queryByHql(hql.toString(), paramList.toArray());
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public void updateMoneyByMemberId(Float money,Integer memberId){
		StringBuffer hql = new StringBuffer("UPDATE account SET money=money+?,updateTime=? WHERE memberId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(money);
		params.add(new Date());
		params.add(memberId);
		executeHql(hql.toString(), params.toArray());
	}
}