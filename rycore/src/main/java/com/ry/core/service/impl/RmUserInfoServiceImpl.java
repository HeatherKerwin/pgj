package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RmUserInfoDao;
import com.ry.core.entity.RmUserInfo;
import com.ry.core.service.RmUserInfoService;
import com.ry.util.page.PageResults;

@Service
public class RmUserInfoServiceImpl extends BaseDao<RmUserInfo, Integer> implements RmUserInfoService {

	@Resource
	private RmUserInfoDao rmUserInfoDao;
	
	public Integer add(RmUserInfo rui) {
		return rmUserInfoDao.add(rui);
	}

	public List<RmUserInfo> getList(String code, Date startTime, Date endTime) {
		return rmUserInfoDao.getList(code, startTime, endTime);
	}

	public PageResults<RmUserInfo> getPageList(String code, Date startTime, Date endTime,
			Integer currentPage, Integer pageSize) {
		return rmUserInfoDao.getPageList(code, startTime, endTime, currentPage, pageSize);
	}

	public List<RmUserInfo> getListByTime(Date startTime, Date endTime) {
		return rmUserInfoDao.getListByTime(startTime, endTime);
	}
}
