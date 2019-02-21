package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.util.page.PageResults;
import com.ry.core.form.DiscountrecordSpForm;

public interface DiscountrecordSpDao {
	
	/**
	 * 根据主键获取对象
	 * @author KHC
	 * @param id
	 * @since 2016年7月28日 下午3:27:17
	 */
	public DiscountrecordSp getById(Integer id);
	
	/**
	 * 根据订单号获取对象
	 * @author KHC
	 * @param ordernumber
	 * @since 
	 */
	public DiscountrecordSp getByOrdernumber(String ordernumber);
	
	/**
	 * 新增
	 * @author KHC
	 * @param discountrecordSp
	 * @since 2016年7月28日 下午3:28:47
	 */
	public void saveModel(DiscountrecordSp discountrecordSp);
	
	/**
	 * 更新
	 * @author KHC
	 * @param discountrecordSp
	 * @since 2016年7月28日 下午3:33:20
	 */
	public void updateModel(DiscountrecordSp discountrecordSp);
	
	/**
	 * 动态分页查询企业商票订单
	 * @author KHC
	 * @param mem
	 * @since 2016年8月10日 下午1:48:03
	 */
	public PageResults<Map<String,Object>> getPageDisRecordSp(MemOrderPageRequest mem);
	
	/**
	 * 根据主键获取商票订单及用户和接单信息
	 * @author KHC
	 * @param id
	 * @since 2016年8月10日 下午6:27:33
	 */
	public Map<String, Object> getInfoById(Integer id);
	
	/**
	 * 根据订单获取对应的报价机构
	 * @author KHC
	 * @param id
	 * @since 2016年8月15日 上午9:22:09
	 */
	public List<Map<String, Object>> getOrgList(DiscountrecordSp discountrecordSp);
	
	/**
	 * 分页显示订单
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @since 2016年8月19日 下午3:13:09
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, DiscountrecordSpForm form);
	
	/**
	 * PC分页显示订单
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @since 
	 */
	public PageResults<Map<String, Object>> getPcPageList(Integer pageIndex,Integer pageSize, DiscountrecordSpForm form);
	
	/**
	 * 根据Id获取订单详情（评论显示）
	 * @author ZY
	 * @param id
	 * @since 2016年8月29日 上午9:29:20
	 */
	public List<Map<String,Object>> getInfoAndOrderById(Integer id);

	/**
	 * 获取需要派单的订单（系统自动派单用）
	 * @author KHC
	 * @since 2016年9月12日 上午10:01:49
	 */
	public List<Map<String, Object>> getNeedPaidan();
	

	/**
	 * 初始化商票和批量的手机号转码
	 * @author ZY
	 * @param id
	 * 2016年9月27日下午1:25:38
	 */
	public List<DiscountrecordSp> getList(String id);
	
	/**
	 * 根据主键获取对象（不解密，最原始对象）
	 * @param id
	 */
	public DiscountrecordSp getModelById(Integer id);
	
	/**
	 * 根据memberId查询订单
	 * @author ZY
	 * @param memberId
	 * 2016年11月3日下午1:25:38
	 */
	public List<DiscountrecordSp> getByMemberId(Integer memberId);
	
	/**
	 * 获取客服未反馈的订单
	 * @author MH
	 * @param form 查询条件
	 */
	public List<DiscountrecordSp> getListByHandleState(DiscountrecordSpForm form);

	/**
	 * 根据多个条件获得导出的商票订单数据
	 * @author ZWD
	 * @param mem
	 * @since 2017年8月1日11:24:15
	 */
	public List<DiscountrecordSp> getByObj(MemOrderPageRequest mem);
	
	/**
	 * 根据客户需求展示商票字段的报表
	 * @author MH
	 * @param mem 参数封装
	 * @return
	 */
	public PageResults<Map<String, Object>> getPageMapList(MemOrderPageRequest mem);
}