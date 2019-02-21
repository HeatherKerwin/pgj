package com.ry.core.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.RefundRecordDao;
import com.ry.core.entity.RefundRecord;
import com.ry.core.form.RefundRecordForm;
import com.ry.core.service.RefundRecordService;
import com.ry.util.page.PageResults;

@Service
public class RefundRecordServiceImpl implements RefundRecordService {

	@Resource
	RefundRecordDao refundRecordDao;
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundRecordService#saveModel(com.ry.core.entity.RefundRecord)
	 */
	@Override
	public void saveModel(RefundRecord refundRecord) {
		refundRecordDao.saveModel(refundRecord);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundRecordService#getById(java.lang.Integer)
	 */
	public RefundRecord getById(Integer id){
		return refundRecordDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundRecordService#getList(java.lang.Integer, java.lang.Integer, com.ry.core.form.RefundRecordForm)
	 */
	@Override
	public PageResults<Map<String, Object>> getList(Integer pageIndex, Integer pageSize, RefundRecordForm from) {
		return refundRecordDao.getList(pageIndex, pageSize, from);
	}


	
}