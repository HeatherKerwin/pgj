package com.ry.core.service;

import java.text.ParseException;
import java.util.Map;

import com.ry.core.entity.DiscountRebate;
import com.ry.core.entity.Discountrecord;
import com.ry.util.page.PageResults;

public interface DiscountRebateService {
	
	/**
	 * 更新对象（贴现返利）
	 * @author WKX
	 * @param discountRebate
	 * @since 2016年9月26日 下午4:40:44
	 */
	public void updateModel(DiscountRebate discountRebate);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年9月26日 下午4:41:09
	 */
	public DiscountRebate getById(Integer id);

	/**
	 * 分页显示
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param from
	 * @since 2016年9月26日 下午4:41:18
	 */
	public PageResults<Map<String,Object>> findPageModel(Integer pageIndex,Integer pageSize,DiscountRebate from);
	
	/**
	 * 订单完成（返利）
	 * @author WKX
	 * @param discountrecord 银票订单
	 * @throws ParseException
	 * @since 2016年9月26日 下午8:15:01
	 */
	public void saveFromFinish(Discountrecord discountrecord) throws ParseException;
}