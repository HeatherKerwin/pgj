package com.ry.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ry.core.dao.DiscountrecordTaskDao;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;
import com.ry.core.service.DiscountrecordTaskService;

/**
 * 订单流程记录
 * @author WKX
 */
@Service
public class DiscountrecordTaskServiceImpl implements DiscountrecordTaskService{
	
	@Resource
	DiscountrecordTaskDao discountrecordTaskDao;

	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordTaskService#getByDiscountrecordId(java.lang.Integer)
	 */
	public List<DiscountrecordTask> getByDiscountrecordId(Integer discountrecordId){
		return discountrecordTaskDao.getByDiscountrecordId(discountrecordId);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordTaskService#saveModel(com.ry.core.entity.DiscountrecordTask)
	 */
	public void saveModel(DiscountrecordTask task)throws Exception{
		discountrecordTaskDao.saveModel(task);
	}
	
	/* (non-Javadoc)
	 * @see com.ry.core.service.DiscountrecordTaskService#getByDiscountrecordIdAndOperator(java.lang.Integer, com.ry.core.entity.DiscountrecordTask.Operator)
	 */
	public DiscountrecordTask getByDiscountrecordIdAndOperator(Integer discountrecordId,Operator operator){
		List<DiscountrecordTask> list = discountrecordTaskDao.getByDiscountrecordIdAndOperator(discountrecordId, operator);
		if(list!=null && list.size()>0){
			return list.iterator().next();
		}else{
			return null;
		}
	}
}