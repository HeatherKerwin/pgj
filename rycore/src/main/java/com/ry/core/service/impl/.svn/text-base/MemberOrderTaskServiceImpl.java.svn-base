package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.MemberOrderTaskDao;
import com.ry.core.dto.OrderHistoryDto;
import com.ry.core.form.orgOrder.OrgOrderTaskRequest;
import com.ry.core.service.MemberOrderTaskService;
import com.ry.util.page.PageResults;

@Service
public class MemberOrderTaskServiceImpl implements MemberOrderTaskService {

	@Resource
	MemberOrderTaskDao orderTaskDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.DistributeOrderTaskService#getPageList(com.ry.core.form.orgOrder.OrgOrderTaskRequest)
	 */
	public PageResults<OrderHistoryDto> getPageList(OrgOrderTaskRequest req){
		return orderTaskDao.getPageList(req);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.MemberOrderTaskService#getPageList1(com.ry.core.form.orgOrder.OrgOrderTaskRequest)
	 */
	public PageResults<OrderHistoryDto> getPageSpHisList(OrgOrderTaskRequest req) {
		return orderTaskDao.getPageSpHisList(req);
	}

	@Override
	public PageResults<OrderHistoryDto> getPagePlHisList(OrgOrderTaskRequest req) {
		return orderTaskDao.getPagePlHisList(req);
	}
	
}