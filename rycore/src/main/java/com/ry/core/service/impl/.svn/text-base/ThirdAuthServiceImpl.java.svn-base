package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ThirdAuthDao;
import com.ry.core.entity.ThirdAuth;
import com.ry.core.service.ThirdAuthService;
@Service
public class ThirdAuthServiceImpl implements ThirdAuthService {

	@Resource
	ThirdAuthDao thirdAuthDao;
	
	@Override
	public List<ThirdAuth> getByAttr(Integer memberId, Integer type, String openId, String token) {
		return thirdAuthDao.getByAttr(memberId, type, openId, token);
	}

	@Override
	public void saveOrUpdate(ThirdAuth t) {
		thirdAuthDao.saveOrUpdate(t);
	}

}
