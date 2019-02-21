package com.ry.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DiscountrecordTask.Operator;

/**
 * 保存流程记录
 * @author WKX
 */
@Repository
public interface DiscountrecordTaskDao {

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
	 * 根据订单主键及操作获取流程操作的明细
	 * @author WKX
	 * @param discountrecordId
	 * @param operator
	 * @since 2016年1月18日 上午10:39:40
	 */
	public List<DiscountrecordTask> getByDiscountrecordIdAndOperator(Integer discountrecordId,Operator operator);
	
	/**
	 * 根据贴现主键获取当前状态的  task
	 * @author WKX
	 * @param discountrecordId
	 * @since 2016年4月8日 上午10:30:41
	 */
	public List<DiscountrecordTask> getCurrentByDcrdId(Integer discountrecordId,Integer type);
}