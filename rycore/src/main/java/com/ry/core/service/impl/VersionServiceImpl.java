package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.VersionInfoDao;
import com.ry.core.entity.VersionInfo;
import com.ry.core.form.versioninfo.VersionInfoRequest;
import com.ry.core.service.VersionInfoService;
import com.ry.util.page.PageResults;

@Service
public class VersionServiceImpl implements VersionInfoService {

	@Resource
	private VersionInfoDao versionInfoDao;

	@Override
	public PageResults<VersionInfo> getPage(VersionInfoRequest req) throws Exception {
		return versionInfoDao.getPage(req);
	}

	@Override
	public VersionInfo getNewVersionInfo() {
		return versionInfoDao.getNewVersionInfo();
	}

	@Override
	public VersionInfo getVersionInfo(Integer id) {
		return versionInfoDao.getVersionInfo(id);
	}

	@Override
	public void updateVersionInfo(VersionInfo versionInfo) {
		versionInfoDao.updateVersionInfo(versionInfo);
	}

	@Override
	public Integer addVersionInfo(VersionInfo versionInfo) {
		return versionInfoDao.addVersionInfo(versionInfo);
	}

	@Override
	public List<VersionInfo> getMaxDate() {
		return versionInfoDao.getMaxDate();
	}

}
