package com.ry.core.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.PreferentialInfoDao;
import com.ry.core.entity.PreferentialInfo;
import com.ry.core.service.PreferentialInfoService;
import com.ry.util.page.PageResults;

@Service
public class PreferentialInfoServiceImpl implements PreferentialInfoService{
	
	@Resource
	PreferentialInfoDao preferentialInfoDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.PreferentialInfoService#getPageList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<PreferentialInfo> getPageList(Integer currentPage,Integer pageSize) {
		
		PageResults<PreferentialInfo> pageResults = preferentialInfoDao.getPageList( currentPage, pageSize);
		return pageResults;
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PreferentialInfoService#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer id) {
		preferentialInfoDao.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.PreferentialInfoService#getById(java.lang.Integer)
	 */
	@Override
	public PreferentialInfo getById(Integer id) {
		return preferentialInfoDao.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PreferentialInfoService#saveInfo(com.ry.core.entity.PreferentialInfo)
	 */
	@Override
	public void saveInfo(PreferentialInfo info) throws Exception{
		preferentialInfoDao.saveInfo(info);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.PreferentialInfoService#getBetweenCreateTime(java.util.Date, java.util.Date)
	 */
	public List<PreferentialInfo> getBetweenCreateTime(Date start,Date end) {
		return preferentialInfoDao.getBetweenCreateTime(start, end);
	}
}