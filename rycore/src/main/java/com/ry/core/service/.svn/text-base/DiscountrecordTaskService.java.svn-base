package com.ry.core.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;

/**
 * 订单流程记录
 * @author WKX
 */
@Repository
public interface DiscountrecordTaskService {

	/**
	 * 根据主表主键获取对象
	 * @author WKX
	 * @param discountrecordId
	 */
	public List<DiscountrecordTask> getByDiscountrecordId(Integer discountrecordId);
	
	/**
	 * 保存对象
	 * @author WKX
	 * @param task
	 */
	public void saveModel(DiscountrecordTask task)throws Exception;
	
	/**
	 * 根据订单主键和操作获取最新的 一条 操作记录
	 * @author WKX
	 * @param discountrecordId
	 * @param operator
	 * @since 2016年1月18日 上午10:41:30
	 */
	public DiscountrecordTask getByDiscountrecordIdAndOperator(Integer discountrecordId,Operator operator);
}