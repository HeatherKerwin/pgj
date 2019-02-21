package com.ry.core.dao;

import com.ry.core.entity.Account;

public interface AccountDao {
	
	public Account getByMemberId(Integer memberId);
	
	/**
	 * [根据用户主键]更新账户金额
	 * @param money 金额（增加、减少）
	 * @param memberId 用户主键
	 */
	public void updateMoneyByMemberId(Float money,Integer memberId);
}