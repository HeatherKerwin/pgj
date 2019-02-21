package com.ry.core.dao;

import java.util.Map;

import com.ry.core.entity.RefundRecord;
import com.ry.core.form.RefundRecordForm;
import com.ry.util.page.PageResults;

public interface RefundRecordDao {

	/**
	 * 保存（新增或更新）
	 * @author WKX
	 * @param refundRecord
	 * @since 2016年6月2日 下午2:26:20
	 */
	public void saveModel(RefundRecord refundRecord);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年6月2日 下午2:34:01
	 */
	public RefundRecord getById(Integer id);
	
	/**
	 * 分页查询（转账记录列表）
	 * @author WKX
	 * @param pageIndex 初始页
	 * @param pageSize 一页个数
	 * @param from 查询条件封装
	 * @since 2016年6月2日 下午2:28:10
	 */
	public PageResults<Map<String,Object>> getList(Integer pageIndex,Integer pageSize,RefundRecordForm from);
}