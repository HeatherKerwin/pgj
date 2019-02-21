package com.ry.core.dao;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.RmUserInfo;
import com.ry.util.page.PageResults;

public interface RmUserInfoDao {
	public Integer add(RmUserInfo rui);
	public List<RmUserInfo> getList(String code, Date startTime, Date endTime);
	public PageResults<RmUserInfo> getPageList(String code, Date startTime, Date endTime, Integer currentPage, Integer pageSize);
	public List<RmUserInfo> getListByTime(Date startTime, Date endTime);
}
