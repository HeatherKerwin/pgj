package com.ry.core.service;

import java.util.Map;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.RefundExamine;
import com.ry.core.form.RefundExamineForm;
import com.ry.util.page.PageResults;



public interface RefundExamineService {

	/**
	 * 根获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年9月18日
	 */
	public PageResults<Map<String, Object>> getExamineInfoById(Integer pageIndex,Integer pageSize,RefundExamineForm from);
	
	/**
	 * 付款后 修改状态
	 */
	public void updateExamine(RefundExamine examine);
	
	/**
	 *  查询Examine表的某条数据
	 */
	public RefundExamine getById(Integer id);

	/**
	 * 更新实体
	 * @author WKX
	 * @param refundExamine
	 * @since 2016年9月22日 下午4:09:22
	 */
	public void saveModel(RefundExamine refundExamine);
	
	/**
	 * 跟新实体
	 * @author WKX
	 * @param refundExamine
	 * @since 2016年9月22日 下午4:10:30
	 */
	public void updateModel(RefundExamine refundExamine);
	
	/**
	 * 生成审核记录（更新订单）
	 * @author WKX
	 * @param refundExamine
	 * @param disc
	 * @param dist
	 * @since 2016年9月28日 上午12:46:41
	 */
	public void saveModelAndUpdateDis(RefundExamine refundExamine,Discountrecord disc,DistributeOrder dist);
}