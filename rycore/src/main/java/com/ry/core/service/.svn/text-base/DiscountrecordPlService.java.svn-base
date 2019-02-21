package com.ry.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.form.MemOrder.UpdateMemRequest;
import com.ry.util.page.PageResults;

public interface DiscountrecordPlService {
	
	/**
	 * 根据主键获取对象
	 * @author KHC
	 * @param id
	 * @since 2016年7月28日 下午4:19:29
	 */
	public DiscountrecordPl getById(Integer id);
	
	/**
	 * 根据订单号获取对象
	 * @param ordernumber
	 * @return
	 */
	public DiscountrecordPl getByOrdernumber(String ordernumber);
	
	/**
	 * 新增
	 * @author KHC
	 * @param discountrecordPl
	 * @since 2016年7月28日 下午4:19:53
	 */
	public void saveModel(DiscountrecordPl discountrecordPl);
	
	/**
	 * 更新
	 * @author KHC
	 * @param discountrecordPl
	 * @since 2016年7月28日 下午4:20:00
	 */
	public void updateModel(DiscountrecordPl discountrecordPl);
	
	/**
	 * 动态分业查询企业批量订单
	 * @author KHC
	 * @param mem
	 * @since 2016年8月10日 下午2:16:34
	 */
	public PageResults<Map<String,Object>> getPageDisRecordPl(MemOrderPageRequest mem);
	
	/**
	 * 根据主键获取批量订单及用户接单信息
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
	 * 根据主键取消订单
	 * @author KHC
	 * @param req
	 * @since 2016年8月14日 上午11:23:26
	 */
	public void updateOrderById(UpdateMemRequest req) throws Exception;
	
	/**
	 * 生成机构批量订单（派单用）
	 * @author KHC
	 * @since 2016年8月13日 上午12:23:26
	 */
	public String createDistributeOrderNo();
	
	/**
	 * 根据用户主键和订单状态获取当前未读消息
	 * @author KHC
	 * @param memberId
	 * @param orderflag
	 * @since 2016年8月19日 上午9:31:48
	 */
	public List<Map<String, Object>> getUnReadByMemberId(Integer memberId,Integer orderflag);
	
	/**
	 * 根据批量订单获取对应的报价机构
	 * @author KHC
	 * @param discountrecordPl
	 * @since 2016年8月15日 上午9:22:09
	 */
	public List<Map<String, Object>> getOrgList(DiscountrecordPl discountrecordPl);
	
	/**
	 * APP分页显示企业批量订单
	 * @author ZY
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @since 2016年8月18日 上午9:36:44
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, DiscountrecordPlForm form);
	
	/**
	 * Pc分页显示企业批量订单
	 * @author ZY
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @since 2016年8月18日 上午9:36:44
	 */
	public PageResults<Map<String, Object>> getPcPageList(Integer pageIndex,Integer pageSize, DiscountrecordPlForm form);
	
	/**
	 * 读最新的task 获取订单信息及 派单信息
	 * @author ZY
	 * @param id
	 * @throws Exception
	 * @since 2016年8月18日 上午9:36:44
	 */
	public Map<String,Object> updateReadTaskAndGetInfoAndOrderById(Integer id) throws Exception;
	
	/**
	 * APP分页显示企业的所有评论
	 * @author ZY
	 * @param pageIndex
	 * @param pageSize
	 * @param id (是comments里的dtbo_id)
	 * @since 2016年8月22日 上午9:23:44
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, Integer id);
	
	/**
	 *  根据Id获取订单详情（评论显示）
	 * @author ZY
	 * @param id
	 * @since 2016年8月29日 上午9:28:18
	 */
	public Map<String,Object> getInfoAndOrderById(Integer id);
	
	/**
	 * 取消订单（并且把有效的接单变为无效）
	 * @author ZY
	 * @param discountrecordPl
	 * @since 2016年9月8日 下午18:22:01
	 */
	public void updateToInvalid(DiscountrecordPl discountrecordPl);
	
	/**
	 * 完成订单（并且把对应的接单-完成）
	 * @param discountrecordSp
	 * @author ZY
	 */
	public void updateToFinish(DiscountrecordPl discountrecordPl);
	
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
	 * 获取超过有效期的订单
	 * @author KHC
	 * @since 2016年9月20日 下午6:36:37
	 */
	public List<DiscountrecordPl> getByEndTime(Date date);
	
	/**
	 * 初始化商票和批量的手机号转码
	 * @author ZY
	 * @param id
	 * 2016年9月27日下午1:25:38
	 */
	public List<DiscountrecordPl> getList(String id);
	
	/**
	 * 获取符合条件的批量订单数量
	 * @author ZY
	 * @since 2016年9月20日 下午6:36:37
	 */
	public List<DiscountrecordPl> getByDiscountrecordPl(DiscountrecordPl dis);
	
	/**
	 * 获得符合条件的需要导出的批量订单数据
	 * @param mem
	 * @author ZWD
	 */
	public List<DiscountrecordPl> getByObj(MemOrderPageRequest mem);
}