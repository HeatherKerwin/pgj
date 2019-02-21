package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Discountrecord;
import com.ry.core.form.DiscountrecordForm;
import com.ry.core.form.MemOrder.MemOrderPageRequest;
import com.ry.util.page.PageResults;


public interface DiscountrecordDao {
	
	List<Discountrecord> getList(String id);
	
	List<Discountrecord> getList(String id, String memberid);
	
	List<Discountrecord> getListFlag(String id, String memberid, Integer orderflag1);
	
	void delete(Discountrecord discountrecord);
	
	void updateDiscountrecord(Discountrecord discountrecord);
	
	void mergeDiscountrecord(Discountrecord discountrecord);
	
	Long countbyOrderflag(Integer orderflag1, Integer orderflag2);
	
	Long countbyOrdertime(Date ordertime1, Date ordertime2, Integer orderflag);
	
	Long countbyMemberId(Integer memberId);
	
	Double allmoneybyMemberId(Integer memberId);
	
	Double allmoney(Date begintimed, Date endtimed);
	
	Double allmoney(Integer salepriceId);
	
	List<Discountrecord> getList(Date begintimed, Date endtimed);
	
	public PageResults<Discountrecord> findPageModel(String hql, String hqlcount, int pageNo, int pageSize, Object[] values);
	
	List<Discountrecord> getList(Integer orderflag1, Integer orderflag2,Integer memberid);
	
	List<Discountrecord> getList(Integer orderflag, Integer memberid);
	
	 /* 飞  */
	List<Discountrecord> getList1(String id);	
	public void addDiscountrecord(Discountrecord discountrecord);

	List<Discountrecord> getTopDiscountrecode(int i);

	Double countByWeek(Integer memberid);
	
	/**
	 * 计算已完成
	 * @param memberid
	 * @return
	 * @date 2016年1月18日
	 * @author lvyanqin
	 */
	Double countByWeek1(Integer memberid);

	Double countAll(Integer memberid);
	
	/**
	 * 根据主键获取对象
	 * @author WKX
	 * @param id
	 * @since 2016年1月7日 上午10:48:27
	 */
	public Discountrecord getById(Integer id);
	
	/**
	 * 根据订单号获取对象
	 * @author MH
	 * @param ordernumber
	 * @since 
	 */
	public Discountrecord getByOrdernumber(String ordernumber);
	
	/**
	 * 根据商户号获取对象
	 * @author MH
	 * @param ordernumber
	 * @since 
	 */
	public Discountrecord getByBnsNoDiscount(String bnsno);
	
	/**
	 * 根据商户号获取对象 不解密
	 * @author MH
	 * @param ordernumber
	 * @since 
	 */
	public Discountrecord getModelByBnsNoDiscount(String bnsno);

	/**
	 * 分页查询有效订单[含：用户主键、承兑行类型、下单时间间隔、订单状态]
	 * @author WKX
	 * @param nf
	 * @param pageIndex
	 * @param pageSize
	 * @since 2016年1月11日 下午2:05:23
	 */
	public PageResults<Discountrecord> getPageList(DiscountrecordForm nf,Integer pageIndex,Integer pageSize);

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
	 * 根据多个条件查询订单
	 * @author WKX
	 * @param df
	 * @since 2016年1月31日 下午3:19:58
	 */
	public List<Discountrecord> getList(DiscountrecordForm df);
	
	/**
	 * 统计贴现订单已完成的活跃用户
	 * @author WKX
	 * @param start 开始时间
	 * @param end 截止时间
	 * @since 2016年2月15日 下午3:12:52
	 */
	public List<Map<String,Object>> getFinishByStartAndEnd(Date start,Date end);
	
	
	/**
	 * 分页统计企业订单
	 * @author li.xiaofei
	 * @return
	 */
	PageResults<Discountrecord> getPageDisRecord(MemOrderPageRequest mem);
	

	/**
	 * 通过主键查询member实体对象
	 * @param id
	 * @return
	 */
	Discountrecord querybyId(Integer id);
	
	
	/**
	 * 根据主键获取订单及用户信息
	 * @param id
	 */
	List<Map<String, Object>> getInfoById(Integer id);

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
	public List<Map<String,Object>> getInfoAndOrderById(Integer id);
	
	/**
	 * 根据订单主键获取taks及订单号
	 * @param id
	 * @author WKX
	 */
	public List<Map<String,Object>> getTaskAndInfoById(Integer id);
	
	/**
	 * 根据主键获取对象（含机构端上传的付款凭证）
	 * @param id
	 * @author WKX
	 */
	public List<Map<String,Object>> getInfoAndImgById(Integer id);
	
	/**
	 * 图片copy:根据条件查询图片列表
	 * @param orderflag 订单状态
	 * @return
	 */
	public List<Object> disImg(String orderflag,Date start,Date end);
	
	/**
	 * 统计某一用户在某段时间内的已成交金额(时间为空则计算全部)
	 * @param memberId	企业主键
	 * @param start 	开始时间
	 * @param end		结束时间
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
	 * 根据商户订单号获取订单
	 * @author WKX
	 * @param no 商户订单号
	 * @since 2016年5月18日 下午4:50:28
	 */
	public List<Discountrecord> getByBnsNo(String no);
	
	/**
	 * 根据用户主键获取未评论订单（排除掉APP2.2之前的未支付 保证金的 已完成订单）
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
	 * @author GXW
	 * @param memberId
	 */
	public List<Map<String,Object>> getDeposit(String memberId);
	
	/**
	 * PC保证金金额查询（企业）
	 * @author GXW
	 * @param memberId
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
	 * 获取需要派单的订单（系统自动派单用）
	 * @author WKX
	 * @since 2016年6月7日 上午9:37:28
	 */
	public List<Map<String,Object>> getNeedPaidan();
	
	/**
	 * 根据主键获取对象（不解密，最原始对象）
	 * @author WKX
	 * @param id
	 * @since 2016年9月23日 下午4:01:11
	 */
	public Discountrecord getModelById(Integer id);
	
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
	 * @param form 条件
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
	 * @param refundState 快钱退款状态 （0未退款，1已退款）
	 * @since 2017年3月14日 下午5:10:18
	 */
	public List<Discountrecord> getByRefundState(Integer refundState);
	
	/**
	 * 根据多个条件获得导出的银票订单数据
	 * @author ZWD
	 * @param mem
	 * @since 2017年8月1日11:24:15
	 */
	public List<Discountrecord> getByObj(MemOrderPageRequest mem);
	
	/**
	 * 分页订单报表
	 * @author MH
	 * @param mem 参数集合
	 * @param pageNo
	 * @param pageSize
	 */
	public PageResults<Map<String, Object>> getReportPageMapList(MemOrderPageRequest mem,Integer pageNo,Integer pageSize);
}