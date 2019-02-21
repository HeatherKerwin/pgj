package com.ry.core.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DiscountrecordTask;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.entity.DistributeOrderTask;
import com.ry.core.entity.Image;
import com.ry.core.form.DistributeOrderForm;
import com.ry.core.form.org.ReviewOrderRequest;
import com.ry.util.page.PageResults;

public interface DistributeOrderService {
	
	/**
	 * 获取机构订单列表（分页）
	 * @author WKX
	 * @param pageIndex
	 * @param pageSize
	 * @param form 查询条件
	 * @since 2016年3月3日 下午4:28:07
	 */
	public PageResults<Map<String,Object>> getPageList(Integer pageIndex,Integer pageSize,DistributeOrderForm form);
	
	/**
	 * 获取机构端未读订单
	 * @author WKX
	 * @param orgId 机构主键
	 * @param state 订单状态
	 * @since 2016年3月3日 下午7:37:36
	 */
	public List<Map<String,Object>> getUnReadByOrgIdAndState(Integer orgId,Integer state);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年3月7日 上午10:40:37
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
	 * 更新订单
	 * @author WKX
	 * @param dis
	 * @since 2016年3月16日 下午1:09:00
	 */
	public void updateDistributeOrder(DistributeOrder dis);
	
	/**
	 * 更新订单（保存操作task）
	 * @author WKX
	 * @param dis 机构订单
	 * @param task 操作记录
	 * @param record 企业订单
	 * @param recordTask 操作记录
	 * @throws Exception
	 * @since 2016年3月17日 上午11:35:54
	 */
	public void updateDisAndSaveTask(DistributeOrder dis,DistributeOrderTask task,Discountrecord record,DiscountrecordTask recordTask) throws Exception;
	
	/**
	 * 根据主键获取 task
	 * @author WKX
	 * @param id
	 * @since 2016年3月17日 上午10:15:08
	 */
	public List<Map<String,Object>> getTaskAndInfoById(Integer id);
	
	/**
	 * 在APP端获取订单详情的同时更新task状态
	 * @author WKX
	 * @param id
	 * @throws Exception
	 * @since 2016年3月17日 上午10:58:28
	 */
	public Map<String,Object> updateReadTaskAndGetInfoById(Integer id) throws Exception;
	
	/**
	 * 根据企业订单主键获取 机构订单
	 * @param dcrdId
	 * @author WKX
	 */
	public DistributeOrder getByDcrdId(Integer dcrdId);
	
	/**
	 * 获取某天 每个机构额度及贴现用掉多少额度
	 * @author WKX
	 * @param start
	 * @param end
	 * @since 2016年3月29日 下午3:16:11
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
	 * 获取这一天指定机构（的额度和已使用额度）
	 * @param orgId
	 * @param day
	 * @throws ParseException
	 */
	public Map<String,Object> getLimitAndMoneyByOrgId(Integer orgId,Date day) throws ParseException;
	
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
	 * 
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
	public PageResults<Map<String,Object>>  getPcOrgDeposit(Integer pageIndex,Integer pageSize,Integer org_id);
	
	/**
	 * 根据orgid 获取本月的完成订单
	 * @author cx
	 * @since 2016年5月24日 
	 */
	public List<Map<String,Object>> getordthism(String date1,String date2,Integer orgid);
	/**
	 * 根据orgId获取机构被分派的订单列表
	 * @param orgId
	 * @author GXW
	 */
	public List<Map<String,Object>> getAssignedByOrgId(Integer org_id,Float version);
	
	/**
	 * 根据贴现订单主键和 机构主键 （查看该机构是否接过单）
	 * @author WKX
	 * @param dcrdId 贴现订单主键
	 * @param orgId 机构主键
	 * @since 2016年5月25日 下午1:16:47
	 */
	public List<DistributeOrder> getByDcrdIdAndOrgId(Integer dcrdId,Integer orgId);
	
	/**
	 * 机构确认验票失败（并保存上传的凭证）
	 * @author WKX
	 * @param dis 接单
	 * @param task 流程
	 * @param images 图片
	 * @param disc 贴现订单先变成无效订单
	 * @throws Exception
	 * @since 2016年5月27日 下午4:47:46
	 * @EDIT 2017年3月21日 下午4:46:01 贴现订单先变成无效，审核后在处理
	 */
	public void updateDisAndImage(DistributeOrder dis,DistributeOrderTask task,List<Image> images,Discountrecord disc) throws Exception;
	
	/**
	 * 支付订单（并保存支付流水）
	 * @author GXW
	 * @param dis 机构端订单
	 * @param state 支付状态
	 * @param des 支付备注（由插件生成）
	 * @param version 版本号
	 * @since 2016年6月1日
	 * @EDIT WKX 2016年9月12日 下午5:45:47 （APP2.3支付成功两边就都变成带交易）
	 */
	public void updateAndPayRecord(DistributeOrder dis,Integer state,String des,Float version);
	
	/**
	 * 保存更新对象
	 * @author WKX
	 * @param distributeOrder
	 * @since 2016年6月7日 下午2:04:44
	 */
	public void saveModel(DistributeOrder distributeOrder);
	
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
	 * 获取机构中银票的接单列表
	 * @author KHC
	 * @param orgId
	 * @since 2016年8月15日 下午5:35:52
	 */
	public List<Map<String, Object>> getListByOrgId(Integer orgId);
	
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
	 * 根据订单号获取详情
	 * @author KHC
	 * @param no
	 * @since 2016年11月4日 下午2:31:56
	 */
	public DistributeOrder getByNo(String no);
	
	/**
	 * 接单（无保证金）
	 * @author WKX
	 * @param dist 机构订单
	 * @since 2017年2月13日 下午5:03:11
	 */
	public void updateAndNoPayRecord(DistributeOrder dist);
	
	/**
	 * 根据退款状态获取订单（订单快钱退款状态，因为存在延迟退款）
	 * @author WKX
	 * @param refundState
	 * @since 2017年3月14日 下午5:10:18
	 */
	public List<DistributeOrder> getByRefundState(Integer refundState);
}