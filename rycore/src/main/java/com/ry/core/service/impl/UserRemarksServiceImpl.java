package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.UserRemarksDao;
import com.ry.core.entity.UserRemarks;
import com.ry.core.service.UserRemarksService;
import com.ry.util.datatable.BasePageRequestData;
import com.ry.util.page.PageResults;

@Service
public class UserRemarksServiceImpl implements UserRemarksService{

	@Resource
	UserRemarksDao userRemarksDao;
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.UserRemarksService#getById(java.lang.Integer)
	 */
	public UserRemarks getById(Integer id) {
		return userRemarksDao.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.UserRemarksService#saveModel(com.ry.core.entity.UserRemarks)
	 */
	public void saveModel(UserRemarks userRemarks) {
		userRemarksDao.saveModel(userRemarks);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.UserRemarksService#updateModel(com.ry.core.entity.UserRemarks)
	 */
	public void updateModel(UserRemarks userRemarks) {
		userRemarksDao.updateModel(userRemarks);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.UserRemarksService#getPageList(com.ry.util.datatable.BasePageRequestData, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<UserRemarks> getPageList(BasePageRequestData req, UserRemarks userRemarks)throws Exception {
		return userRemarksDao.getPageList(req, userRemarks);
	}
}