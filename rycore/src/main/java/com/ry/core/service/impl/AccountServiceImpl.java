package com.ry.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.Enum.AccountLogState;
import com.ry.core.dao.AccountDao;
import com.ry.core.dao.AccountLogDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Account;
import com.ry.core.entity.AccountLog;
import com.ry.core.service.AccountService;

@Service
public class AccountServiceImpl extends BaseDao<Account, Integer> implements AccountService {

	@Resource
	AccountDao accountDao;
	
	@Resource
	AccountLogDao accountLogDao;
	
	public void updateMoneyByLog(AccountLog accountLog) throws Exception{
		if(accountLog==null || accountLog.getAccountLogState()==AccountLogState.SUCCESS)throw new Exception();
		accountDao.updateMoneyByMemberId(accountLog.getMoneyInto(), accountLog.getMemberId());
		
		accountLog.setUpdateTime(new Date());
		accountLog.setAccountLogState(AccountLogState.SUCCESS);//支付成功
		accountLogDao.updateModel(accountLog);
	}
}