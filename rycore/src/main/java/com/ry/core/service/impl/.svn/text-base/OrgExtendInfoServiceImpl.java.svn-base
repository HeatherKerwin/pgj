package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.OrgExtendInfoDao;
import com.ry.core.entity.OrgExtendInfo;
import com.ry.core.service.OrgExtendInfoService;

@Service
public class OrgExtendInfoServiceImpl implements OrgExtendInfoService{

	@Resource
	OrgExtendInfoDao orgExtendInfoDao;
	
	@Override
	public OrgExtendInfo getOrgExtendInfoByInfoId(Integer orgInfoId) {
		return orgExtendInfoDao.getOrgExtendInfoByInfoId(orgInfoId);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgExtendInfoService#updateOrgExtendInfo(com.ry.core.entity.OrgExtendInfo)
	 */
	@Override
	public void updateOrgExtendInfo(OrgExtendInfo orgExtendInfo) {
		orgExtendInfoDao.updateOrgExtendInfo(orgExtendInfo);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgExtendInfoService#saveOrgExtendInfo(com.ry.core.entity.OrgExtendInfo)
	 */
	@Override
	public void save(OrgExtendInfo orgExtendInfo) {
		orgExtendInfoDao.saveOrgExtendInfo(orgExtendInfo);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.OrgExtendInfoService#getOrgExtendInfoByInfo()
	 */
	@Override
	public List<OrgExtendInfo> getOrgExtendInfoByInfo() {
		return orgExtendInfoDao.getOrgExtendInfoByInfo();
	}

}
