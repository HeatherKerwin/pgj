package com.ry.core.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DiscountrecordDao;
import com.ry.core.dao.DistributeOrderDao;
import com.ry.core.dao.RefundExamineDao;
import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.RefundExamine;
import com.ry.core.form.RefundExamineForm;
import com.ry.core.service.RefundExamineService;
import com.ry.util.page.PageResults;

@Service
public class RefundExamineServiceImpl implements RefundExamineService{
	
	@Resource
	RefundExamineDao refundExamineDao;
	
	@Resource
	DiscountrecordDao discountrecordDao;
	
	@Resource
	DistributeOrderDao distributeOrderDao;

	@Override
	public PageResults<Map<String, Object>> getExamineInfoById(Integer pageIndex,Integer pageSize,RefundExamineForm from) {
		return refundExamineDao.getExamineInfoById(pageIndex,pageSize,from);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundExamineService#updateExamine(com.ry.core.entity.RefundExamine)
	 */
	public void updateExamine(RefundExamine examine){
		refundExamineDao.updateExamine(examine);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundExamineService#getById(java.lang.Integer)
	 */
	public RefundExamine getById(Integer id){
		return refundExamineDao.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundExamineService#saveModel(com.ry.core.entity.RefundExamine)
	 */
	public void saveModel(RefundExamine refundExamine) {
		refundExamineDao.saveModel(refundExamine);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundExamineService#updateModel(com.ry.core.entity.RefundExamine)
	 */
	public void updateModel(RefundExamine refundExamine) {
		refundExamineDao.updateModel(refundExamine);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.RefundExamineService#saveModelAndUpdateDis(com.ry.core.entity.RefundExamine, com.ry.core.entity.Discountrecord, com.ry.core.entity.DistributeOrder)
	 */
	public void saveModelAndUpdateDis(RefundExamine refundExamine,Discountrecord disc,DistributeOrder dist) {
		refundExamineDao.saveModel(refundExamine);
		if(disc!=null)discountrecordDao.updateDiscountrecord(disc);
		if(dist!=null)distributeOrderDao.updateDistributeOrder(dist);
	}
}