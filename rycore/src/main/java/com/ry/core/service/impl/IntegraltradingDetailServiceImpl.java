package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.IntegraltradingDetailDao;
import com.ry.core.entity.IntegraltradingDetail;
import com.ry.core.service.IntegraltradingDetailService;
import com.ry.util.page.PageResults;

@Service
public class IntegraltradingDetailServiceImpl implements IntegraltradingDetailService {

	@Resource
	IntegraltradingDetailDao integraltradingDetailDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.IntegraltradingDetailService#getById(java.lang.Integer)
	 */
	@Override
	public IntegraltradingDetail getById(Integer id) {
		return integraltradingDetailDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.IntegraltradingDetailService#getList(com.ry.core.entity.IntegraltradingDetail)
	 */
	@Override
	public List<IntegraltradingDetail> getList(IntegraltradingDetail integraltradingDetail) {
		return integraltradingDetailDao.getList(integraltradingDetail);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.IntegraltradingDetailService#getPageList(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<IntegraltradingDetail> getPageList(Integer pageIndex, Integer pageSize, Integer memberId) {
		return integraltradingDetailDao.getPageList(pageIndex, pageSize, memberId);
	}

}
