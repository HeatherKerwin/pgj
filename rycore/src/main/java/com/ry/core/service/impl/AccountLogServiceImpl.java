package com.ry.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.AccountDao;
import com.ry.core.dao.AccountLogDao;
import com.ry.core.dao.BaseDao;
import com.ry.core.entity.Account;
import com.ry.core.entity.AccountLog;
import com.ry.core.service.AccountLogService;

@Service
public class AccountLogServiceImpl extends BaseDao<AccountLog, Integer> implements AccountLogService {

	@Resource
	AccountLogDao accountLogDao;
	
	@Resource
	AccountDao accountDao;
	
	@Override
	public void saveModel(AccountLog accountLog) throws Exception {
		if(accountLog!=null){
			accountLog.setCreateTime(new Date());
			accountLog.setNo(this.randomNo());
			if(accountLog.getAccountId()==null){
				Account account = accountDao.getByMemberId(accountLog.getMemberId());
				if(account==null)throw new Exception();
				accountLog.setAccountId(account.getId());
			}
		}
		accountLogDao.saveModel(accountLog);
	}
	
	@Override
	public AccountLog getByNo(String no){
		List<AccountLog> list = accountLogDao.getByNo(no);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取记录编号
	 * @author WKX
	 */
	private String randomNo(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String str = format.format(new Date());
		for(int i = 0; i < 3; i++){
			str += (int)(Math.random()*10);
		}
		List<AccountLog> list = accountLogDao.getByNo(str);
		if(list!=null && list.size()>0)return this.randomNo();
		else return str;
	}
}