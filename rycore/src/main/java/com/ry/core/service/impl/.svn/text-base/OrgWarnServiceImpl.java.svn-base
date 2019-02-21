package com.ry.core.service.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.OrgWarnDao;
import com.ry.core.entity.OrgWarn;
import com.ry.core.service.OrgWarnService;
import com.ry.util.page.PageResults;

@Service
public class OrgWarnServiceImpl extends BaseDao<OrgWarn, Integer> implements OrgWarnService {
	
	@Resource
	OrgWarnDao orgWarnDao;
	
	@Override
	public OrgWarn getById(Integer id) {
		return orgWarnDao.getById(id);
	}
	
	@Override
	public void saveModel(OrgWarn orgWarn) {
		orgWarnDao.saveModel(orgWarn);
	}
	
	@Override
	public void updateModel(OrgWarn orgWarn) {
		orgWarnDao.updateModel(orgWarn);
	}
	
	@Override
	public PageResults<Map<String,Object>> getPageList(Integer currentPage, Integer pageSize,String name) {
		PageResults<Map<String,Object>> pageResults = orgWarnDao.getPageList( currentPage, pageSize, name);
		return pageResults;
	}
}
