package com.ry.core.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.Accountrecord;
import com.ry.core.form.AccountrecordForm;
import com.ry.util.page.PageResults;

public interface AccountrecordService {
	
	public List<Accountrecord> getList(Integer memberId, String day);

	public Integer saveAccountrecord(Accountrecord accountrecord);
	
	public void updateAccountrecord(Accountrecord accountrecord);
	
	public List<Double> allprice(String memberid, String publishtime);
	
	public List<Double> tiexianlixi(String memberid, String publishtime);
	
	Double sallprice(String memberid, String publishtime, List paramList);
	
	Accountrecord getAccountrecord(Integer id);
	
	void delete(Accountrecord accountrecord);
	
	void update(Accountrecord accountrecord);
	
	BigDecimal countPriceByTypeAndMonth(String type);
	
	BigDecimal countPriceByTypeAndWeek(String type);
	
	BigDecimal countPriceByTypeAday(String type);
	
	List<Map<String,Object>> countPriceByDayAndMonth(Integer memberid,Integer belong);
	
	List<Map<String, Object>> countPriceByWeek(Integer memberid);

	List<Accountrecord> findDiscountByWeek(String type,String start,String end);
	
	/**
	 * @author GJJ
	 * @date 2016年1月9日
	 * @param type
	 * @param start
	 * @param end
	 * @return List<Accountrecord>
	 * @time 2016年1月9日
	 * @todo 查询一个月的票据信息
	 */
	List<Accountrecord> findDiscountByMonth(Integer memberid,String start,String end);

	/**
	 * @author GJJ
	 * @date 2016年1月9日
	 * @param type
	 * @param day
	 * @return List<Accountrecord>
	 * @time 2016年1月9日
	 * @todo 查询一天的票据信息
	 */
	List<Accountrecord> findDiscountAday(Integer memberid, String day);

	/**
	 * @author GJJ
	 * @date 2016年1月11日
	 * @param begin
	 * @param end
	 * @param type
	 * @return void
	 * @time 2016年1月11日
	 * @todo 统计票据类型的数量并绘制图表
	 */
	List<Map<String,Object>> countPiaoju(String begin, String end, String type,Integer memberid);

	/**
	 * 查找今天是到期的未贴现记录
	 * @return
	 * @date 2016年1月25日
	 * @author lvyanqin
	 */
	public List<Accountrecord> findAccountrecordByDaoqidate();

	List<Accountrecord> findByCustomizedParam(AccountrecordForm form);

	/**
	 * 获取贴现信息
	 * @param memberId
	 * @param type
	 * @param start
	 * @param end
	 * @return
	 * @date 2016年1月19日
	 * @author lvyanqin
	 */
	public List<Accountrecord> getDiscount(Integer memberId, String type,
			String start, String end);
	
	/**
	 * 根据条件查询记票据[含时间间隔、贴现未贴现状态、用户主键]
	 * @author WKX
	 * @param form
	 * @throws Exception
	 * @since 2016年1月20日 下午2:07:10
	 */
	public List<Accountrecord> getByForm(AccountrecordForm form) throws Exception;

	/**
	 * 查询已贴现的票据[含时间间隔、贴现未贴现状态、用户主键]
	 * @param begin
	 * @param end
	 * @param type
	 * @param memberid
	 * @return
	 * @date 2016年1月22日
	 * @author lvyanqin
	 */
	public List<Map<String, Object>> sumLixi(String begin, String end,
			String type, Integer memberid);

	/**
	 * 根据discountrecordId来查询记录
	 * @param id
	 * @return
	 * @date 2016年1月22日
	 * @author lvyanqin
	 */
	public Accountrecord getAccountrecordByDiscountrecordId(Integer id);

	/**
	 * 根据用户id和时间段来获取总金额
	 * @param memberid
	 * @param begin
	 * @param end
	 * @return
	 * @date 2016年1月25日
	 * @author lvyanqin
	 */
	public List<Map<String, Object>> countPrice(Integer memberid,Integer belong, Date begin, Date end);
	
	/**
	 * 统计分析（根据起止时间、用户主键）状态为：贴现
	 * @param memberId
	 * @param belong
	 * @param start
	 * @param end
	 * @throws Exception
	 * @author WKX
	 */
	public List<Map<String, Object>> getSumByMemberId(Integer memberId,Integer belong,String start,String end) throws Exception;
	
	/**
	 * 根据istiexian查询所有
	 * @param memberid
	 * @param istiexian
	 * @date 2016年3月29日
	 * @author cx
	 */
	public List<Accountrecord> alltiexian(Integer memberid,String istiexian);
	
	/**
	 * 自定义搜索票据列表
	 * @param type  是否已贴现
	 * @param end   结束时间
	 * @param start 开始时间
	 * @param chengduitype  承兑行类型
	 * @param allprice  贴现金额
	 * @date 2016年3月31日
	 * @author cx
	 */
	public List<Accountrecord> customcount(Integer memberId, String type,String end,String start,Float allprice,Integer type1);
	
	/**
	 * chaxun
	 * @date 2016年3月31日
	 * @author cx
	 */
	public List<Map<String, Object>> noticeConnect(Integer memberId, String istiexian,Date end,Date start,Float allprice,Integer type1 ,Integer belong,String type,Integer limit);
	
	/**
	 * 查询票据：为提醒用户票据快过期
	 * @param beforeDaoqishijian 用户ID
	 * @param daoqishijian 用户所属类型
	 * @param statue 记录状态（状态 0：删除  1：正常）, 此处需要的是不为0,因为此值可能为空
	 * @param tiexianType 此处为手动
	 * @param istiexian //是否贴现（1为已贴现，0为未贴现）
	 */
	public List<Map<String, Object>> getForRemindExpire(Date beforeDaoqishijian, Date daoqishijian, String statue, String tiexianType, String istiexian);
	
	/**
	 * 根据贴现（承兑）主键和所属查询是否存在相应票据
	 * @author WKX
	 * @param dcrdId
	 * @param belong
	 * @since 2016年4月7日 下午3:44:21
	 */
	public Accountrecord getByDcrdIdAndBelong(Integer dcrdId,Integer belong);
	
	/**
	 * 查看是否有提醒的票据
	 * @author cx
	 * @param memberid
	 * @param belong
	 * @since 2016年5月19日 
	 */
	public List<Map<String,Object>> ifExistTiXin(Integer memberid,Integer belong,String date1,String date2);
	
	/**
	 * 根据用户角色查询 票据管理
	 * @author WKX
	 * @param memberId
	 * @param belong
	 * @param day
	 * @since 2016年7月18日 下午12:49:08
	 */
	public List<Accountrecord> getListByBelong(Integer memberId,Integer belong, String day);
	
	/**
	 * PC分页查询票据管理
	 * @author KHC
	 * @param form
	 * @param pageIndex
	 * @param pageSize
	 * @since 2016年10月27日 下午2:41:04
	 */
	public PageResults<Map<String, Object>> getPageList(AccountrecordForm form,Integer pageIndex,Integer pageSize,Integer memberId);
	
	/**
	 * 获取列表
	 * @author KHC
	 * @param form
	 * @param memberId
	 * @since 2016年11月2日 下午5:44:54
	 */
	public List<Map<String, Object>> getList(AccountrecordForm form,Integer memberId);
	
	/**
	 * 获取用户票据管理提醒（分页）
	 * @author WKX
	 * @param memberId
	 * @param pageIndex
	 * @param pageSize
	 * @throws Exception
	 * @since 2016年11月20日 下午5:46:39
	 */
	public PageResults<Map<String, Object>> getNoticePage(Integer memberId,Integer pageIndex,Integer pageSize) throws Exception;
}