package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.VersionInfo;
import com.ry.core.form.versioninfo.VersionInfoRequest;
import com.ry.util.page.PageResults;

public interface VersionInfoService {
	
	/**
	 * 分页查询版本信息
	 * @author ZWD
	 * @param req
	 * @return PageResults<VersionInfo>
	 * 2017年4月14日15:23:52
	 */
	public PageResults<VersionInfo> getPage(VersionInfoRequest req) throws Exception;
	
	public VersionInfo getNewVersionInfo();
	
	public VersionInfo getVersionInfo(Integer id);
	
	public void updateVersionInfo(VersionInfo versionInfo);
	
	public Integer addVersionInfo(VersionInfo versionInfo);
	
	public List<VersionInfo> getMaxDate();
}
