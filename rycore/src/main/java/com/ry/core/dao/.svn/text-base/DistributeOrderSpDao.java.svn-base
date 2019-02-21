package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.DistributeOrderSp;
import com.ry.core.form.DistributeOrderSpForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.util.page.PageResults;

public interface DistributeOrderSpDao {
	
	/**
	 * 根据主键获取对象
	 * @author KHC
	 * @param id
	 * @since 2016年7月28日 下午3:35:24
	 */
	public DistributeOrderSp getById(Integer id);
	
	/**
	 * 新增
	 * @author KHC
	 * @param distributeOrderSp
	 * @since 2016年7月28日 下午3:36:01
	 */
	public void saveModel(DistributeOrderSp distributeOrderSp);
	
	/**
	 * 更新
	 * @author KHC
	 * @param distributeOrderSp
	 * @since 2016年7月28日 下午3:36:47
	 */
	public void updateModel(DistributeOrderSp distributeOrderSp);
	
	/**
	 * 根据下单主键获取(所有报价机构)
	 * @param dcrdSpId
	 * @author WKX
	 */
	public List<Map<String,Object>> getAllByDcrdSpId(Integer dcrdSpId);
	
	/**
	 * 根据下单主键获取(所有报价机构)
	 * @param dcrdSpId
	 * @author WKX
	 */
	public List<Map<String,Object>> getPcAllByDcrdSpId(Integer dcrdSpId);
	
	/**
	 * 根据主键获取-接单（含机构基本信息）
	 * @author WKX
	 * @param id
	 * @since 2016年8月22日 上午10:39:56
	 */
	public Map<String,Object> getInfoById(Integer dtboId);
	
	/**
	 * 根据下单主键获取 当前接单机构
	 * @author WKX
	 * @param dcrdId
	 * @since 2016年8月24日 上午11:08:42
	 */
	public Map<String,Object> getCurrentSelectById(Integer dcrdId);
	
	/**
	 * 根据机构主键获取-待接单
	 * @param orgId
	 * @param way
	 * @author WKX
	 */
	public List<Map<String,Object>> getWaitByOrgId(Integer orgId);
	
	/**
	 * 根据商票贴现订单获取机构派单
	 * @param dcrdId
	 * @author KHC
	 * @since 2016年8月13日 下午5:24:35
	 */
	public List<DistributeOrderSp> getByDcrdId(Integer dcrdId);
	
	/**
	 * 获取商票接单机构派单列表
	 * @author KHC
	 * @param memberId 用户主键
	 * @since 2016年8月17日 下午5:35:52
	 */
	public List<Map<String, Object>> getList(Integer memberId);
	
	/**
	 * 根据机构主键获取派单对象
	 * @param orgId
	 * @since 2016年8月27日 下午5:35:52
	 */
	public List<DistributeOrderSp> getByOrgId(Integer orgId);
	
	/**
	 * 根据派单主键获取派单记录
	 * @author ZY
	 * @param id
	 * @since 2016年8月29日 下午6:44:34
	 */
	public Map<String,Object> getInfoByDtboId(Integer id);
	
	/**
	 * APP机构端分页获取商票订单
	 * @author ZY
	 * @param indexPage
	 * @param pageSize
	 * @since 2016年8月29日 下午4:24:52
	 */
	public PageResults<Map<String,Object>> getPageList(Integer indexPage,Integer pageSize,DistributeOrderSpForm form);
	
	/**
	 * 根据form查询商票接单列表
	 * @author WKX
	 * @param form
	 * @since 2016年9月7日 下午1:48:34
	 */
	public List<DistributeOrderSp> getList(DistributeOrderSpForm form);
	
	/**
	 * 根据订单主键和机构主键获取派单列表
	 * @author KHC
	 * @param discId
	 * @param orgId
	 * @since 2016年9月9日 下午7:05:17
	 */
	public List<DistributeOrderSp> getListByDiscIdAndOrgId(Integer discId,Integer orgId);
	
	/**
	 * 分页获取所有商票派单
	 * @author KHC
	 * @param form
	 * @since 2016年9月10日 上午11:16:47
	 */
	public PageResults<DistributeOrderSp> getOrderPageList(ReviewOrderRequest req);
	
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
	public List<DistributeOrderSp> getOverrunByCreateTime(Date date);
	
	/**
	 * 根据贴现主键获取派单机构的列表（时间早的在前面）
	 * @author WKX
	 * @param dcrdSpId 贴现主键
	 * @since 2016年9月16日 下午5:39:02
	 */
	public List<DistributeOrderSp> getOrderByCreateTime(Integer dcrdSpId);
	
	/**
	 * PC获取机构商票列表
	 * @author KHC
	 * @param indexPage
	 * @param pageSize
	 * @param form
	 * @since 2016年11月7日 下午5:18:47
	 */
	public PageResults<Map<String,Object>> getPageListPC(Integer pageIndex,Integer pageSize,DistributeOrderSpForm form) throws Exception;

	/**
	 * 根据订单号获取对象
	 * @author KHC
	 * @param no
	 * @since 2016年11月7日 下午7:30:40
	 */
	public DistributeOrderSp getByNo(String no) throws Exception;
}