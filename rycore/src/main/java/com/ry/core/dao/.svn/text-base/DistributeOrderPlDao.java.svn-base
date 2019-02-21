package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.DistributeOrderPl;
import com.ry.core.form.DistributeOrderPlForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.util.page.PageResults;

public interface DistributeOrderPlDao {
	
	/**
	 * 根据主键获取对象
	 * @author KHC
	 * @param id
	 * @since 2016年7月28日 下午3:43:02
	 */
	public DistributeOrderPl getById(Integer id);
	
	/**
	 * 新增
	 * @author KHC
	 * @param distributeOrderPl
	 * @since 2016年7月28日 下午3:43:34
	 */
	public void saveModel(DistributeOrderPl distributeOrderPl);
	
	/**
	 * 更新
	 * @author KHC
	 * @param distributeOrderPl
	 * @since 2016年7月28日 下午3:44:04
	 */
	public void updateModel(DistributeOrderPl distributeOrderPl);
	
	/**
	 * 根据主键删除对象
	 * @author KHC
	 * @param id
	 * @since 2016年9月12日 下午3:06:44
	 */
	public void deleteModel(Integer id);
	
	/**
	 * 根据机构主键获取待接单
	 * @author WKX
	 * @param orgId
	 * @since 2016年8月25日 上午11:06:50
	 */
	public List<Map<String,Object>> getWaitByOrgId(Integer orgId);
	
	/**
	 * APP机构端分页获取批量订单
	 * @author KHC
	 * @param orgId
	 * @since 2016年8月18日 下午2:11:35
	 */
	public PageResults<Map<String, Object>> getPageList(Integer indexPage,Integer pageSize,DistributeOrderPlForm form);
	
	/**
	 * 根据批量贴现订单获取机构派单
	 * @param dcrdId
	 * @author KHC
	 * @since 2016年8月13日 下午5:24:35
	 */
	public List<DistributeOrderPl> getByDcrdId(Integer dcrdId);
	
	/**
	 * 根据机构主键获取派单列表
	 * @param orgId
	 * @author KHC
	 * @since 2016年8月29日 下午4:14:16
	 */
	public List<Map<String, Object>> getListByOrgId(Integer orgId);
	
	/**
	 * 根据主键获取订单信息（含完整贴现信息）
	 * @author WKX
	 * @param id
	 * @since 2016年9月8日 下午2:15:17
	 */
	public Map<String, Object> getInfoById(Integer id);
	
	/**
	 * 根据form查询批量接单列表
	 * @author ZY
	 * @param form
	 * @since 2016年9月8日 下午7:32:22
	 */
	public List<DistributeOrderPl> getList(DistributeOrderPlForm form);
	
	/**
	 * 根据机构id获取派单列表
	 * @author KHC
	 * @param orgId
	 * @since 2016年9月9日 下午3:11:02
	 */
	public List<DistributeOrderPl> getByOrgId(Integer orgId);
	
	/**
	 * 根据订单主键和机构主键获取派单列表
	 * @author KHC
	 * @param discId
	 * @param orgId
	 * @since 2016年9月9日 下午7:13:15
	 */
	public List<DistributeOrderPl> getListByDiscIdAndOrgId(Integer discId,Integer orgId);
	
	/**
	 * 分页获取所有派单列表
	 * @author KHC
	 * @param form
	 * @since 2016年9月10日 上午11:27:03
	 */
	public PageResults<DistributeOrderPl> getOrderPageList(ReviewOrderRequest req);
	
	/**
	 * 分页获取机构订单,注册的手机号码
	 * @author MH
	 * @param req 参数
	 */
	public PageResults<Map<String,Object>> getOrderMemberPageList(ReviewOrderRequest req);
	
	/**
	 * 根据创建时间获取超时订单（定时任务，待接单超时自动无效）
	 * @author KHC
	 * @param date
	 * @since 2016年9月11日 下午7:03:40
	 */
	public List<Map<String, Object>> getOverrunByCreateTime(String date);
	
	/**
	 * 通过订单号获取对象
	 * @author KHC
	 * @param no
	 * @since 2016年11月8日 上午11:02:37
	 */
	public DistributeOrderPl getByNo(String no) throws Exception;
	
	/**
	 * PC获取机构批量列表
	 * @author KHC
	 * @param indexPage
	 * @param pageSize
	 * @param form
	 * @since 2016年11月7日 下午5:18:47
	 */
	public PageResults<Map<String,Object>> getPageListPC(Integer pageIndex,Integer pageSize,DistributeOrderPlForm form) throws Exception;
}