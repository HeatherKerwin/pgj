package com.ry.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.RemarksDao;
import com.ry.core.entity.Remarks;
import com.ry.core.service.RemarksService;
import com.ry.util.datatable.BasePageRequestData;
import com.ry.util.page.PageResults;

@Service
public class RemarksServiceImpl implements RemarksService{

	@Resource
	RemarksDao remarksDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RemarksService#getById(java.lang.Integer)
	 */
	public Remarks getById(Integer id) {
		return remarksDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RemarksService#saveModel(com.ry.core.entity.Remarks)
	 */
	public void saveModel(Remarks remarks) {
		remarksDao.saveModel(remarks);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RemarksService#getInfoById(java.lang.Integer)
	 */
	public List<Map<String, Object>> getInfoById(Integer id) {
		return remarksDao.getInfoById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RemarksService#getPageInfoById(com.ry.util.datatable.BasePageRequestData, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageInfoById(BasePageRequestData bp, Integer fkId, Integer fkType) {
		return remarksDao.getPageInfoById(bp, fkId, fkType);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ry.core.service.RemarksService#listByTypeAndFkid(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>>  listByTypeAndFkid(Integer currentPage,Integer pageSize,Integer fkId,Integer type){
		return remarksDao.listByTypeAndFkid(currentPage,pageSize,fkId,type);
	}
	/* (non-Javadoc)
	 * @see com.ry.core.service.RemarksService#getListByFkId(java.lang.Integer)
	 */
	public List<Map<String, Object>> getListByFkId(Integer fkid,Integer type) {
		return remarksDao.getListByFkId(fkid,type);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RemarksService#getPageList(com.ry.util.datatable.BasePageRequestData, java.lang.Integer, java.lang.Integer)
	 */
	public PageResults<Map<String, Object>> getPageList(BasePageRequestData bp, Integer fkId, Integer type) {
		return remarksDao.getPageList(bp, fkId, type);
	}
}
