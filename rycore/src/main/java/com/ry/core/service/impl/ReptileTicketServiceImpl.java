/**
 *
 */
package com.ry.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.ReptileTicketDao;
import com.ry.core.entity.ReptileTicket;
import com.ry.core.service.ReptileTicketService;
import com.ry.util.page.PageResults;

@Service
public class ReptileTicketServiceImpl implements ReptileTicketService {
	
	@Resource
	ReptileTicketDao reptileTicketDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.ReptileTicketService#saveModel(java.lang.Integer)
	 */
	@Override
	public ReptileTicket getById(Integer id) throws Exception {
		return reptileTicketDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ReptileTicketService#saveModel(com.ry.core.entity.ReptileTicket)
	 */
	@Override
	public void saveModel(ReptileTicket reptileTicket) {
		reptileTicketDao.saveModel(reptileTicket);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.ReptileTicketService#getPageList(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageResults<ReptileTicket> getPageList(Integer currentPage, Integer pageSize) {
		return reptileTicketDao.getPageList(currentPage, pageSize);
	}

}
