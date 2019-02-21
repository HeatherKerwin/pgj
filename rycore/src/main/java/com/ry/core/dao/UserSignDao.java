package com.ry.core.dao;

import com.ry.core.entity.UserSign;

public interface UserSignDao {
	
	/**
	 * 签到
	 * @author MH
	 */
	public void  saveModel(UserSign userSign);
	
	/**
	 * 根据用户Id，看是否签到
	 * @author MH
	 * @param MemberId 用户Id
	 * @param Day 时间
	 * @return
	 */
	public UserSign getByModel(Integer memberId,String day);

}
