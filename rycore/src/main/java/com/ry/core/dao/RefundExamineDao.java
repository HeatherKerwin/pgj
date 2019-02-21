package com.ry.core.dao;

import java.util.Map;

import com.ry.core.entity.RefundExamine;
import com.ry.core.form.RefundExamineForm;
import com.ry.util.page.PageResults;

public interface RefundExamineDao {

	/**
	 *  查询退款审核
	 */
	public PageResults<Map<String,Object>> getExamineInfoById(Integer pageIndex,Integer pageSize,RefundExamineForm from);
	
	/**
	 * 付款后 修改状态
	 */
	public void updateExamine(RefundExamine examine);
	
	/**
	 *  查询Examine表的某条数据
	 */
	public RefundExamine getById(Integer id);
	/**
	 * 保存实体
	 * @author WKX
	 * @param refundExamine
	 * @since 2016年9月22日 下午4:08:03
	 */
	public void saveModel(RefundExamine refundExamine);
	
	/**
	 * 跟新实体
	 * @author WKX
	 * @param refundExamine
	 * @since 2016年9月22日 下午4:08:43
	 */
	public void updateModel(RefundExamine refundExamine);
}