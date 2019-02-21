package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.DiscountRebate;
import com.ry.core.form.DiscountRebateForm;
import com.ry.util.page.PageResults;

public interface DiscountRebateDao {
	
	/**
	 * 贴现完成（生成返利记录）
	 * @author WKX
	 * @param discountRebate
	 * @since 2016年9月26日 下午8:35:26
	 */
	public void saveModel(DiscountRebate discountRebate);

	/**
	 * 更新返利
	 * @author WKX
	 * @param discountRebate
	 * @since 2016年9月26日 下午8:35:47
	 */
	public void updateModel(DiscountRebate discountRebate);
	
	/**
	 * 获取详情
	 * @author WKX
	 * @param id
	 * @since 2016年9月26日 下午8:35:54
	 */
	public DiscountRebate getById(Integer id);

	/**
	 * 分页显示
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param from
	 * @since 2016年9月26日 下午8:36:02
	 */
	public PageResults<Map<String,Object>> findPageModel(Integer pageIndex,Integer pageSize,DiscountRebate from);
	
	/**
	 * 根据贴现订单主键（获取多条记录）
	 * @author WKX
	 * @param dcrdId 贴现订单
	 * @since 2016年9月26日 下午8:35:20
	 */
	public List<DiscountRebate> getByDcrdId(Integer dcrdId);
	
	/**
	 * 获取对象
	 * @author WKX
	 * @param form
	 * @since 2016年10月11日 下午3:43:37
	 */
	public List<Map<String,Object>> getList(DiscountRebateForm form);
}