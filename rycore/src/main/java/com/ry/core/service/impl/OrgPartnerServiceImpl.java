package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.OrgPartnerDao;
import com.ry.core.entity.OrgPartner;
import com.ry.core.service.OrgPartnerService;

@Service
public class OrgPartnerServiceImpl implements OrgPartnerService{
	
	@Resource
	OrgPartnerDao orgPartnerDao;

	@Override
	public OrgPartner getById(Integer id) {
		return orgPartnerDao.getById(id);
	}

	@Override
	public void saveModel(OrgPartner orgPartner) {
		orgPartnerDao.saveModel(orgPartner);
	}

	@Override
	public void deleteById(Integer id) {
		orgPartnerDao.deleteById(id);
	}

	@Override
	public void updateModel(OrgPartner orgPartner) {
		orgPartnerDao.updateModel(orgPartner);
	}

	@Override
	public List<OrgPartner> getByOrgId(Integer orgId) {
		return orgPartnerDao.getByOrgId(orgId);
	}
	
	@Override
	public List<Map<String,Object>> getByMemberId(Integer memberId){
		return orgPartnerDao.getByMemberId(memberId);
	}
}