package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordPl;
import com.ry.core.form.DiscountrecordPlForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.util.page.PageResults;

public interface DiscountrecordPlDao {

	/**
	 * 根据主键获取对象
	 * @author KHC
	 * @param id
	 * @since 2016年7月28日 下午3:38:31
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
	 * @since 2016年7月28日 下午3:39:12
	 */
	public void saveModel(DiscountrecordPl discountrecordPl);
	
	/**
	 * 更新
	 * @author KHC
	 * @param discountrecordPl
	 * @since 2016年7月28日 下午3:41:04
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
	 * 根据主键获取批量订单及用户和接单信息
	 * @author KHC
	 * @param id
	 * @since 2016年8月10日 下午6:27:33
	 */
	public Map<String, Object> getInfoById(Integer id);
	
	/**
	 * 根据批量订单获取对应的报价机构(匹配银票报价额度)
	 * @author KHC
	 * @param discountrecordPl
	 * @since 2016年8月15日 上午9:22:09
	 */
	public List<Map<String, Object>> getOrgListByPrice(DiscountrecordPl discountrecordPl);
	
	/**
	 * 根据批量订单获取对应的报价机构(匹配银票报价票据类型:纸票或电票)
	 * @author KHC
	 * @param discountrecordPl
	 * @since 2016年9月1日 下午3:26:37
	 */
	public List<Map<String, Object>> getOrgListByType(DiscountrecordPl discountrecordPl);
	
	/**
	 * 根据用户主键和订单状态获取当前未读消息
	 * @author KHC
	 * @param memberId
	 * @param orderflag
	 * @since 2016年8月19日 上午9:31:48
	 */
	public List<Map<String, Object>> getUnReadByMemberId(Integer memberId,Integer orderflag);
	
	/**
	 * APP分页显示企业批量订单（含接单机构主键）
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @author ZY
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, DiscountrecordPlForm form);
	
	/**
	 * PC分页显示企业批量订单（含接单机构主键）
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @author ZY
	 */
	public PageResults<Map<String, Object>> getPcPageList(Integer pageIndex,Integer pageSize, DiscountrecordPlForm form);
	
	/**
	 * 获取批量订单的详情，如果有该订单的机构，也获取该订单机构的部分信息
	 * @author ZY
	 * @param id
	 * @since 2016年8月19日 上午11:11:39
	 */
	public List<Map<String,Object>> getInfoAndOrderById(Integer id);
	
	/**
	 * APP分页显示企业的所有评论
	 * @author ZY
	 * @param pageIndex
	 * @param pageSize
	 * @param id
	 * @since 2016年8月22日 上午9:26:59
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, Integer id );
	
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
	 * 根据主键获取对象（不解密，最原始对象）
	 * @author WKX
	 * @param id
	 * @since 2016年9月27日 下午9:51:01
	 */
	public DiscountrecordPl getModelById(Integer id);
	
	/**
	 * 获取符合条件的批量订单数量
	 * @author ZY
	 * @since 2016年9月20日 下午6:36:37
	 */
	public List<DiscountrecordPl> getByDiscountrecordPl(DiscountrecordPl dis);
	
	/**
	 * 获得需要导出的数据
	 * @param mem
	 * @author ZWD
	 */
	public List<DiscountrecordPl> getByObj(MemOrderPageRequest mem);
}