package com.ry.core.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Discountrecord;
import com.ry.core.entity.DistributeOrder;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.form.MemOrder.DispatchOrderInofRequest;
import com.ry.core.form.MemOrder.DispatchOrderInofResponse;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.core.form.MemOrder.UpdateMemRequest;
import com.ry.util.excel.ViewExcel;
import com.ry.util.page.PageResults;

/**
 * @author Administrator
 *
 */
public interface DiscountrecordService {

	void deleteDis(Discountrecord discountrecord);
	
	List<Discountrecord> getList(String id);
	
	List<Discountrecord> getListbyMemberid(String id, String memberid);
	
	void updateDis(Discountrecord discountrecord) throws Exception ;
	
	Long countbyOrderflag(Integer orderflag1, Integer orderflag2);
	
	Long countbyOrdertime(Date begintimed, Date endtimed, Integer orderflag);
	
	List<Discountrecord> getList(Date begintimed, Date endtimed);
		
	PageResults<Discountrecord> findPageModel(int pageNo, int pageSize, Object[] values);
	
	List<Discountrecord> getList(Integer orderflag1, Integer orderflag2,Integer memberid);
	
	List<Discountrecord> getList(Integer orderflag,Integer memberid);
	
	Double allmoney(Date begintimed, Date endtimed);
	
	Double allmoney(Integer salepriceId);
	
	void addDiscountrecord(Discountrecord discountrecord) throws Exception;
	
	List<Discountrecord> getListFlag(String id, String memberid, Integer orderflag1);

	List<Discountrecord> getTopDiscountrecode(int i);
	
	Double countPriceByWeek(Integer memberid);
	
	Double countPriceByWeek1(Integer memberid);
	
	Double countAll(Integer memberid);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年1月7日 上午10:49:36
	 */
	public Discountrecord getById(Integer id);
	
	/**
	 * 根据订单号获取对象
	 * @author WKX
	 * @param ordernumber
	 */
	public Discountrecord getByOrdernumber(String ordernumber);
	
	/**
	 * 根据商户号获取对象
	 * @author WKX
	 * @param ordernumber
	 */
	public Discountrecord getByBnsNoDiscount(String bnsno);
	
	/**
	 * 根据商户号获取对象 不加密
	 * @author MH
	 * @param bnsno
	 * @return
	 */
	public Discountrecord getModelByBnsNoDiscount(String bnsno);

	/**
	 * 分页查询有效订单[含：用户主键、承兑行类型、下单时间间隔、订单状态]
	 * @author WKX
	 * @param nf
	 * @param pageIndex
	 * @param pageSize
	 * @since 2016年1月11日 下午2:18:31
	 */
	public PageResults<Discountrecord> getPageList(DiscountrecordForm nf,Integer pageIndex,Integer pageSize);

	/**
	 * 计算已完成的订单
	 * @param memberid
	 * @return
	 * @date 2016年1月21日
	 * @author lvyanqin
	 */
	Double countFinish(Integer memberid);
	
	/**
	 * 获取上月贴现金额（已完成）
	 * @author WKX
	 * @since 2016年1月21日 下午4:42:01
	 */
	public Double getMoneyLastMonth();
	
	/**
	 * 获取全网总贴现金额（已完成）
	 * @author WKX
	 * @since 2016年1月21日 下午4:42:23
	 */
	public Double getMoneyAllFinish();
	
	/**
	 * 根据多个条件查询
	 * @author WKX
	 * @param df
	 * @since 2016年1月31日 下午3:20:59
	 */
	public List<Discountrecord> getList(DiscountrecordForm df);
	
	/**
	 * 统计贴现订单已完成的活跃用户
	 * @author WKX
	 * @param start 开始时间
	 * @param end 截止时间
	 * @since 2016年2月15日 下午3:19:29
	 */
	public List<Map<String,Object>> getFinishByStartAndEnd(Date start,Date end);
	
	/**
	 * 分页获取企业订单信息
	 * @param mem
	 * @return
	 */
	PageResults<Discountrecord> getPageDisRecord(MemOrderPageRequest mem);
	
	/**
	 * 删除订单
	 * @param req
	 */
	void updateMemById(UpdateMemRequest req);
	
	/**
	 * 获取订单及相应企业信息
	 * @return
	 */
	DispatchOrderInofResponse dispatchOrderInfo(DispatchOrderInofRequest req)throws Exception;
	
	
	/**
	 * 订单主键获取订单及用户信息
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getInfoById(Integer id);
	
	/**
	 * 根据主键取消订单
	 * @param req
	 */
	void updateOrderById(UpdateMemRequest req) throws Exception;
	
	/**
	 * APP分页显示企业订单（含接单机构主键）
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @author WKX
	 */
	public PageResults<Map<String, Object>> getPageList(Integer pageIndex,Integer pageSize, DiscountrecordForm form);
	
	/**
	 * PC分页显示企业订单（含接单机构主键）
	 * @param pageIndex
	 * @param pageSize
	 * @param form
	 * @author WKX
	 */
	public PageResults<Map<String, Object>> getPcPageList(Integer pageIndex,Integer pageSize, DiscountrecordForm form);
	
	/**
	 * 根据主键获取企业订单（含机构订单）
	 * @param id
	 * @author WKX
	 */
	public Map<String,Object> getInfoAndOrderById(Integer id);
	
	/**
	 * 读最新的task 获取订单信息及 派单信息
	 * @author WKX
	 * @param id
	 * @throws Exception
	 * @since 2016年4月8日 上午10:48:20
	 */
	public Map<String,Object> updateReadTaskAndGetInfoAndOrderById(Integer id) throws Exception;
	
	/**
	 * 更新贴现订单（保存task）此为用户取消订单（如果有派单也更新派单）
	 * @param record
	 * @param order
	 * @param operatorId
	 * @param des
	 * @throws Exception
	 * @author WKX
	 */
	public void updateAndTaskToInvalid(Discountrecord record,DistributeOrder order,Integer operatorId,String des) throws Exception;
	
	/**
	 * 订单已完成（用户确认收到款项）
	 * @param record 企业订单
	 * @param order 机构订单
	 * @param operatorId 操作员主键
	 * @param des 备注
	 * @throws Exception
	 * @author WKX
	 */
	public void updateAndTaskToFinish(Discountrecord record,DistributeOrder order,Integer operatorId,String des) throws Exception;
	
	/**
	 * 根据主键获取task及订单号
	 * @param id
	 * @author WKX
	 */
	public List<Map<String,Object>> getTaskAndInfoById(Integer id);
	
	/**
	 * 根据主键获取对象（含机构端上传的付款凭证）
	 * @param id
	 * @author WKX
	 */
	public Map<String,Object> getInfoAndImgById(Integer id);
	
	/**
	 * 图片copy:根据条件查询图片列表
	 * @param orderflag 订单状态
	 * @return
	 */
	public List<Object> disImg(String orderflag,Date start,Date end) throws Exception;
	
	/**
	 * 统计某一用户在某段时间内的已成交金额(时间为空则计算全部)
	 * @param memberId	企业主键
	 * @param start 	开始时间
	 * @param end		结束时间
	 * @author BKY
	 */
	public Double getMoneyByIdAndTime(Integer memberId, Date start, Date end);
	
	/**
	 * 根据用户主键和订单状态获取当前未读消息
	 * @author WKX
	 * @param memberId
	 * @param orderflag
	 * @since 2016年4月8日 下午2:17:04
	 */
	public List<Map<String,Object>> getUnReadByMemberId(Integer memberId,Integer orderflag);
	
	/**
	 * 根据用户主键获取贴现订单
	 * @author WKX
	 * @param memberId
	 * @since 2016年4月8日 下午7:06:35
	 */
	public List<Discountrecord> getByMemberId(Integer memberId);

	/**
	 * 查询指定的用户在某段时间的某种状态的贴现订单
	 * @param paramList	用户的id列表
	 * @param orderflag	订单状态
	 * @author BKY
	 */
	public List<Map<String, Object>> getCountRecord(List<Object> paramList,Integer orderflag, Date startTime, Date endTime);
	
	/**
	 * @param time	用户注册之日起time（毫秒级）
	 * @param amount 订单数的最低值(包含)
	 */
	public List<Map<String, Object>> getCountAmount(Long time, Integer amount, List<Object> paramList);
	
	/**
	 * 随机生成 商户订单号
	 * @author WKX
	 * @param prefix
	 * @since 2016年5月18日 下午4:52:38
	 */
	public String randomBnsNo(String prefix);
	
	/**
	 * 根据用户主键获取未评论订单
	 * @param memberId
	 * @author WKX
	 */
	public List<Map<String,Object>> getUnCommentsByMemberId(Integer memberId);
	
	/**
	 * 查询所有
	 * @author cx
	 */
	public List<Map<String, Object>> getAllList();
	
	/**
	 * 保证金金额查询（企业）
	 * @author cx
	 * @param memberId
	 * @param orderflag   订单状态
	 */
	public List<Map<String,Object>> getDeposit(String memberId);
	
	/**
	 * PC保证金金额查询（企业）
	 * @author cx
	 * @param memberId
	 * @param orderflag   订单状态
	 */
	public PageResults<Map<String,Object>> getPcBnsDeposit(Integer pageIndex,Integer pageSize,String memberId);
	
	/**
	 * 保证金金额查询（机构）
	 * @author cx
	 * @param orgId
	 */
	public List<Map<String,Object>> baoJinListJG(Integer orgId);
	
	/**
	 * 企业查看上一月是否有完成订单
	 * @author cx
	 */
	public List<Map<String,Object>> ifComplete(Date datetime1,Date datetime2,Integer memberid);
	
	/**
	 * 企业查看上一月是否有拒绝订单
	 * @author cx
	 */
	public List<Map<String,Object>> ifRefused(Date datetime1,Date datetime2,Integer memberid);
	
	/**
	 * 贴现订单派单保存对应机构报价（派单用）
	 * @author WKX
	 * @param record
	 * @param orgId
	 * @throws ParseException
	 * @since 2016年5月25日 下午3:45:43
	 */
	public Map<String,Object> getPrice(Discountrecord record,Integer orgId) throws ParseException;
	
	/**
	 * 获取调整天数（派单用）
	 * @author WKX
	 * @param type1 纸电票
	 * @param end 到期日期
	 * @throws ParseException
	 * @since 2016年5月25日 下午3:49:20
	 */
	public int getTzts(Integer type1,Date end) throws ParseException;
	
	/**
	 * 获取机构订单（派单用）
	 * @author LXF
	 */
	public String createDistributeOrderNo();
	
	/**
	 * 退款记录（需要人工转账）
	 * @author WKX
	 * @param record 贴现订单
	 * @param order 派单
	 * @param toBelong
	 * @param type 退款类型（1转账、2退款（系统退款失败需要人工转账））
	 * @throws Exception
	 * @since 2016年6月7日 下午9:18:50
	 */
	public void refund(Discountrecord record,DistributeOrder order,Integer toBelong,Integer type) throws Exception;
	
	
	/**
	 * 押金审核
	 * @param record 贴现订单
	 * @param order 派单
	 * @param toBelong
	 * @param type
	 * @throws Exception
	 */
	public void updateAndRefund(Discountrecord record,DistributeOrder order,Integer toBelong,Integer type) throws Exception;
	
	/**
	 * 更新对象（不含业务）
	 * @author WKX
	 * @param discountrecord
	 * @throws Exception
	 * @since 2016年9月6日 上午9:45:09
	 */
	public void updateModel(Discountrecord discountrecord) throws Exception;
	
	/**
	 * 分页查询订单列表
	 * @author KHC
	 * @param from
	 * @param pageNo
	 * @param pageSize
	 * @since 2017年1月5日 下午5:01:03
	 */
	public PageResults<Map<String, Object>> getPageMapList(MemOrderPageRequest mem,Integer pageNo,Integer pageSize);
	
	/**
	 *  分页查询订单列表(专门用于待处理中)
	 * @author ZY
	 * @param mem
	 * @param pageNo
	 * @param pageSize
	 * 2017年2月16日下午6:47:55
	 */
	public PageResults<Map<String, Object>> getPageList(MemOrderPageRequest mem,Integer pageNo,Integer pageSize);

	/**
	 * 分页查询回访的订单
	 * @author KHC
	 * @param mem
	 * @param pageNo
	 * @param pageSize
	 * @since 2017年1月11日 上午10:00:29
	 */
	public PageResults<Map<String, Object>> getPageMapListVisit(MemOrderPageRequest mem,Integer pageNo,Integer pageSize);

	/**
	 * 定时任务获取未处理的订单
	 * @author KHC
	 * @param form 查询条件
	 * @since 2017年1月12日 下午3:39:25
	 */
	public List<Discountrecord> getListByHandleState(DiscountrecordForm form);
	
	/**更新对象
	 * @author KHC
	 * @param discountrecord
	 * @since 2017年1月23日 下午3:58:15
	 */
	public void mergeModel(Discountrecord discountrecord);
	
	/**
	 * 根据退款状态获取订单（订单快钱退款状态，因为存在延迟退款）
	 * @author WKX
	 * @param refundState
	 * @since 2017年3月14日 下午5:10:18
	 */
	public List<Discountrecord> getByRefundState(Integer refundState);
	
	/**
	 * 根据主键获取对象（无解密，和对象复制）
	 * @author MH
	 * @param id 主键
	 */
	public Discountrecord getModelById(Integer id);
	
	/**
	 * 根据多个条件获得导出的银票订单数据
	 * @author ZWD
	 * @param mem
	 * @since 2017年8月1日11:24:15
	 */
	public ViewExcel getByExcelData(MemOrderPageRequest mem,String orderType) throws Exception;
	
	/**
	 * 分页订单报表
	 * @author MH
	 * @param mem 参数集合
	 * @param pageNo
	 * @param pageSize
	 */
	public PageResults<Map<String, Object>> getReportPageMapList(MemOrderPageRequest mem,Integer pageNo,Integer pageSize);

}