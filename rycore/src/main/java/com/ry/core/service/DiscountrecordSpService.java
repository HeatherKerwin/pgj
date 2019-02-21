package com.ry.core.service;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.DiscountrecordSp;
import com.ry.core.form.DiscountrecordSpForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.form.MemOrder.UpdateMemRequest;
import com.ry.util.page.PageResults;

public interface DiscountrecordSpService {
	
	/**
	 * 根据主键获取对象
	 * @author KHC
	 * @param id
	 * @since 2016年7月28日 下午4:14:19
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
	 * @since 2016年7月28日 下午4:14:59
	 */
	public void saveModel(DiscountrecordSp discountrecordSp);
	
	/**
	 * 更新
	 * @author KHC
	 * @param discountrecordSp
	 * @since 2016年7月28日 下午4:15:14
	 */
	public void updateModel(DiscountrecordSp discountrecordSp);
	
	/**
	 * 分页显示订单
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @since 2016年8月19日 下午3:14:46
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
	 * 分页统计企业商票订单
	 * @author KHC
	 * @param mem
	 * @since 2016年8月10日 下午1:48:03
	 */
	public PageResults<Map<String,Object>> getPageDisRecordSp(MemOrderPageRequest mem);
	
	/**
	 * 根据主键获取商票订单及用户接单信息
	 * @author KHC
	 * @param id
	 * @since 2016年8月12日 上午11:23:26
	 */
	public Map<String, Object> getInfoById(Integer id);
	
	/**
	 * 删除订单
	 * @author KHC
	 * @param req
	 * @since 2016年8月13日 上午11:43:26
	 */
	public void updateMemById(UpdateMemRequest req);
	
	/**
	 * 取消订单(派单)
	 * @author KHC
	 * @param req
	 * @since 2016年8月13日 上午11:23:26
	 */
	public void updateOrderById(UpdateMemRequest req) throws Exception;
	
	/**
	 * 生成机构商票订单（派单用）
	 * @author KHC
	 * @since 2016年8月13日 上午12:23:26
	 */
	public String createDistributeOrderNo();
	
	/**
	 * 根据订单id获取对应的报价机构
	 * @author KHC
	 * @param id
	 * @since 2016年8月15日 上午9:22:09
	 */
	public List<Map<String, Object>> getOrgList(DiscountrecordSp discountrecordSp);
	
	/**
	 *  根据Id获取订单详情（评论显示）
	 * @author ZY
	 * @param id
	 * @since 2016年8月29日 上午9:28:18
	 */
	public Map<String,Object> getInfoAndOrderById(Integer id);
	
	/**
	 * 取消订单（并且把有效的接单变为无效）
	 * @author WKX
	 * @param discountrecordSp
	 * @since 2016年9月7日 下午8:22:01
	 */
	public void updateToInvalid(DiscountrecordSp discountrecordSp);
	
	/**
	 * 完成订单（并且把对应的接单-完成）
	 * @param discountrecordSp
	 * @author WKX
	 */
	public void updateToFinish(DiscountrecordSp discountrecordSp);
	
	/**
	 * 客服取消企业订单
	 * @author KHC
	 * @param req
	 * @since 2016年9月11日 上午10:11:22
	 */
	public void updateToInvalidByAdmin(UpdateMemRequest req);
	
	/**
	 * 后台给多家机构派单(手动派单和系统派单)
	 * @author KHC
	 * @param req
	 * @since 2016年9月11日 上午10:39:02
	 */
	public boolean updatePaidan(UpdateMemRequest req);
	
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
	 * 根据memberId查询订单
	 * @author ZY
	 * @param memberId
	 * 2016年11月3日下午1:25:38
	 */
	public List<DiscountrecordSp> getByMemberId(Integer memberId);
	
	/**
	 * 根据id查询订单,不解密
	 * @author MH
	 * @param Id
	 */
	public DiscountrecordSp getModelById(Integer id);
	
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