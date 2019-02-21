package com.ry.core.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.DistributeOrder;
import com.ry.core.form.DistributeOrderForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.util.page.PageResults;

public interface DistributeOrderDao {
	
	/**
	 * 获取机构订单列表（分页）
	 * @author WKX
	 * @param indexPage
	 * @param pageSize
	 * @param form 查询条件
	 * @since 2016年3月3日 下午4:24:35
	 */
	public PageResults<Map<String,Object>> getPageList(Integer indexPage,Integer pageSize,DistributeOrderForm form);
	
	/**
	 * 获取机构端未读订单
	 * @author WKX
	 * @param orgId 认证主键（机构）
	 * @param state 订单状态
	 * @since 2016年3月3日 下午7:35:52
	 */
	public List<Map<String,Object>> getUnReadByOrgId(Integer orgId,Integer state);
	
	/**
	 * 获取机构中银票的接单列表
	 * @author KHC
	 * @param orgId
	 * @since 2016年8月15日 下午5:35:52
	 */
	public List<Map<String, Object>> getListByOrgId(Integer orgId);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年3月7日 上午10:39:32
	 */
	public DistributeOrder getById(Integer id);
	
	/**
	 * 根据主键获取对象（及企业贴现订单的明细）
	 * @author WKX
	 * @param id
	 * @since 2016年3月7日 下午4:05:37
	 */
	public Map<String,Object> getInfoById(Integer id);
	
	/**
	 * 派单（新增）
	 * @param dis
	 */
	public void saveDistributeOrder (DistributeOrder dis);
	
	/**
	 * 更新订单
	 * @param dis
	 */
	public void updateDistributeOrder (DistributeOrder dis);
	
	/**
	 * 根据主键获取 task
	 * @author WKX
	 * @param id
	 * @since 2016年3月17日 上午9:57:17
	 */
	public List<Map<String,Object>> getTaskAndInfoById(Integer id);
	
	/**
	 * 根据贴现订单获取机构订单
	 * @param dcrdId
	 * @author WKX
	 */
	public List<DistributeOrder> getByDcrdId(Integer dcrdId);
	
	/**
	 * 获取这一天所有机构（的额度和已使用额度）
	 * @author WKX
	 * @param day
	 * @throws ParseException
	 * @since 2016年3月29日 下午4:27:02
	 */
	public List<Map<String,Object>> getLimitAndMoneyForOrg(Date day) throws ParseException;
	

	/**
	 * 获取某段时间内的机构的订单数
	 * @param state 订单状态
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 某段时间内的机构ID和订单数
	 * @author BKY
	 */
	public List<Map<String, Object>> getListIdAndTotal(Integer state, Date startDate, Date endDate);
	
	/**
	 * 根据org_id，时间，订单状态获取订单数
	 * @author GXW
	 * @param org_id,ordertime1,ordertime2,orderflag
	 * @return long 订单数
	 * @since 2016年5月19日
	 */
	public Long countbyOrdertime(Integer org_id, Long ordertime1, Long ordertime2, Integer orderflag);
	
	/**
	 * 获取本月成交单数
	 * @author cx
	 * @since 2016年5月12日 
	 */
	public List<Map<String,Object>> getSuccessList(String date1,String date2);
	
	/**
	 * 获取本月拒绝单数
	 * @author cx
	 * @since 2016年5月12日 
	 */
	public List<Map<String,Object>> getRefuseList(String date1,String date2);
	
	/**
	 * 获取总共完成交易时间单数
	 * @author cx
	 * @since 2016年5月12日 
	 */
	public List<Map<String,Object>> getSuccessTimeList(String date1,String date2);
	
	/**
	 * 机构保证金列表
	 * @author cx
	 */
	public List<Map<String,Object>> getDeposit(Integer org_id);
	
	/**
	 * 机构保证金列表
	 * @author cx
	 */
	public PageResults<Map<String,Object>> getPcOrgDeposit(Integer pageIndex,Integer pageSize,Integer org_id);
	
	/**
	 * 根据orgid 获取本月的完成订单
	 * @author cx
	 * @since 2016年5月24日 
	 */
	public List<Map<String,Object>> getordthism(String date1,String date2,Integer orgid);
	
	/**
	 * 获取这一天指定机构（的额度和已使用额度）
	 * @param orgId
	 * @param day
	 * @throws ParseException
	 */
	public List<Map<String,Object>> getLimitAndMoneyByOrgId(Integer orgId,Date day) throws ParseException;
	
	/**
	 * 根据orgId获取机构被分派的订单列表
	 * @param orgId
	 * @author GXW
	 */
	public List<Map<String,Object>> getAssignedByOrgId(Integer org_id,Float version);
	
	/**
	 * 根据贴现订单主键和 机构主键 （查看该机构是否接过单）
	 * @author WKX
	 * @param dcrdId
	 * @param orgId
	 * @since 2016年5月25日 下午1:13:30
	 */
	public List<DistributeOrder> getByDcrdIdAndOrgId(Integer dcrdId,Integer orgId);
	
	/**
	 * 根据创建时间获取超时订单（定时任务，待接单超时自动无效）
	 * @author WKX
	 * @param date
	 * @since 2016年6月7日 下午1:42:41
	 */
	public List<DistributeOrder> getOverrunByCreateTime(Date date);
	
	/**
	 * 根据创建时间、机构主键、订单状态获取订单信息(关联表，查订单金额)
	 * @param orgId 机构主键
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param state 状态
	 * @author BKY
	 * @since 2016年6月8日下午14:00:00
	 */
	public List<Map<String, Object>> getByOrgIdAndTime(Integer orgId, String startDate, String endDate, Integer state);
	
	/**
	 * 分页获取机构订单
	 * @author KHC
	 * @param req
	 * @since 2016年9月9日 下午4:41:33
	 */
	public PageResults<DistributeOrder> getOrderPageList(ReviewOrderRequest req);

	/**
	 * 分页获取机构订单,注册的手机号码
	 * @author MH
	 * @param req
	 */
	public PageResults<Map<String,Object>> getOrderMemberPageList(ReviewOrderRequest req);
	
	/**
	 * 根据订单主键分页获取派单列表
	 * @author KHC
	 * @param discId
	 * @since 2016年9月13日 下午3:55:49
	 */
	public PageResults<DistributeOrder> getPageList(Integer discId,Integer pageIndex,Integer pageSize);
	
	/**
	 * 根据商户订单号获取对象
	 * @author ZY
	 * @param bnsNo
	 * @since 2016年10月25日 上午21:40:37
	 */
	public DistributeOrder getByBnsno(String bnsNo);
	
	/**
	 * 获取机构订单列表（分页） 
	 * @author KHC
	 * @param indexPage
	 * @param pageSize
	 * @param form 查询条件
	 * @since 2016年11月3日 下午5:18:14
	 */
	public PageResults<Map<String,Object>> getPageListPC(Integer pageIndex,Integer pageSize,DistributeOrderForm form) throws Exception;
	
	/**
	 * 根据订单号获取对象
	 * @author KHC
	 * @param no
	 * @since 2016年11月4日 下午2:31:56
	 */
	public DistributeOrder getByNo(String no);
	
	/**
	 * 根据退款状态获取订单（订单快钱退款状态，因为存在延迟退款）
	 * @author WKX
	 * @param refundState 快钱退款状态 （0未退款，1已退款）
	 * @since 2017年3月14日 下午5:16:06
	 */
	public List<DistributeOrder> getByRefundState(Integer refundState);
}