package com.ry.core.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.BaseDao;
import com.ry.core.dao.RequirementSpDao;
import com.ry.core.entity.RequirementSp;
import com.ry.core.service.RequirementSpService;

@Service
public class RequirementSpServiceImpl extends BaseDao<RequirementSp, Integer> implements RequirementSpService {
	
	@Resource
	RequirementSpDao requirementSpDao;
	
	@Override
	public RequirementSp getById(Integer id) {
		return requirementSpDao.getById(id);
	}
	
	@Override
	public void saveModel(RequirementSp requirementSp) {
		requirementSpDao.saveModel(requirementSp);
	}
	
	@Override
	public void updateModel(RequirementSp requirementSp) {
		requirementSpDao.updateModel(requirementSp);
	}
	
	@Override
	public RequirementSp getByAll(Integer orgId,Integer type,String time){
		return requirementSpDao.getByAll(orgId,type,time);
	}
	
	@Override
	public void saveOrUpdate(RequirementSp requirementSp){
		requirementSpDao.saveOrUpdate(requirementSp);
	}
	
	@Override
	public List<RequirementSp> getByOrgId(Integer orgId,String time){
		return requirementSpDao.getByOrgId(orgId,time);
	}
}
