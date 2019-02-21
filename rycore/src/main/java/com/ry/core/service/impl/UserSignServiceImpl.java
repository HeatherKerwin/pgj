package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.UserSignDao;
import com.ry.core.entity.UserSign;
import com.ry.core.service.UserSignService;

@Service
public class UserSignServiceImpl implements UserSignService {
	
	@Resource
	UserSignDao userSignDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.UserSignService#saveModel(com.ry.core.entity.UserSign)
	 */
	@Override
	public void saveModel(UserSign userSign) {
		userSignDao.saveModel(userSign);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.UserSignService#getByModel(java.lang.Integer, java.util.Date)
	 */
	@Override
	public UserSign getByModel(Integer memberId, String day) {
		return userSignDao.getByModel(memberId, day);
	}

}
