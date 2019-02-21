package com.ry.core.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.HongbaoAction;
import com.ry.core.entity.HongbaoActivity;
import com.ry.core.entity.HongbaoDetail;
import com.ry.core.entity.HongbaoSend;
import com.ry.core.form.HongbaoActivityForm;
import com.ry.util.page.PageResults;

public interface HongbaoService {
	
	PageResults<HongbaoActivity> getPageResults(HongbaoActivityForm hongbaoActivityForm);
	
	public void addActivity(HongbaoActivity activity);
	
	public void updateActivity(HongbaoActivity activity);
	
	HongbaoActivity getActivity(HongbaoActivity activity);
	
	public List<HongbaoActivity> getActivitys(HongbaoActivity activity);
	
	public List<HongbaoDetail> getList(Integer memberid);
	
	public List<HongbaoDetail> getHistoryList(Integer memberid);
	
	public List<HongbaoDetail> getUsedList(Integer memberid);
	
	HongbaoDetail getHongbaoDetail(Integer id);
	
	public void updateHongbaoDetail(HongbaoDetail hongbaoDetail);
	
	public HongbaoDetail getHongbaoDetail(String orderNumber);
	
	public HongbaoSend getHongbaoSend(Integer id);
	
	public void addHongbaoDetail(HongbaoDetail hongbaoDetail);
	
	public BigInteger countHongbaoDetail(Integer hid);
	
	public void addHongbaoAction(HongbaoAction hongbaoAction);
	
	public List<Object[]> countHongbaoPrice(Integer hid);
	
	public List<HongbaoSend> getHongbaoSends(Integer memberid);
	
	public void addHongbaoSend(HongbaoSend hongbaoSend);
	
	List<HongbaoSend> getList(Integer memberid, Integer hid);
	
	public List<HongbaoDetail> getHongbaoDetail(Integer sid, String phone);
	
	public List<HongbaoAction> getHongbaoActionList(Integer hid, String phone, Integer sid);
	
	public void updateHongbaoAction(HongbaoAction hongbaoAction);
	
	public void deleteHongbaoDetail(HongbaoDetail hongbaoDetail);
	
	public List<HongbaoDetail> getListFlag0();

	Map<String, String> getCount(Integer id);

	List<Integer> getHids();

	HongbaoActivity getCountByBean(Integer integer);

	HongbaoActivity getActivityById(Integer id);
	
	BigInteger countHongbaoAction(Integer hid);
	
	/**
	 * 根据订单编号获取用的红包[一单仅用一个红包]
	 * @author WKX
	 * @param ordernumber
	 * @since 2016年1月13日 上午9:38:23
	 */
	public HongbaoDetail getByOrdernumber(String ordernumber);
	
	/**
	 * 根据开始结束时间查看此方位存在相同的红包活动个数[并且排除当前编辑的红包（可不传参）]
	 * @param start
	 * @param end
	 * @param tagId
	 * @param id
	 * @author WKX
	 */
	public Long countByStartAndEndAndTagIdAndNotId(Date start,Date end,Integer tagId,Integer id);
	
	/**
	 * 根据红包类型获取当前有效活动[含红包总金额和红包个数的判断，如果超出则返回null]
	 * @author WKX
	 * @param tagId
	 * @throws Exception
	 * @since 2016年1月18日 下午5:36:49
	 */
	public HongbaoActivity getActivityByTagId(Integer tagId) throws Exception;
	
	/**
	 * 获取红包列表-List<Map>
	 * @author WKX
	 * @param activity
	 * @since 2016年2月14日 下午3:50:48
	 */
	public List<Map<String,Object>> getMapActivitys(HongbaoActivityForm activity);
	
	/**
	 * 根据时间区间统计发送红包数（一个用户一天内活跃数）
	 * @author WKX
	 * @param type 按时间查询（日、周、月）
	 * @param tagId 活动类型（不同的类型统计方式不同）
	 * @param hid 活动主键
	 * @param datas 时间段
	 * @since 2016年2月17日 上午9:31:35
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(String type,Integer tagId,Integer hid,List<Date> datas);
	
	/**
	 * 统计红包发送率
	 * @author WKX
	 * @param type 按时间查询（日、周、月）
	 * @param tagId 活动类型（不同的类型统计方式不同）
	 * @param hid 活动主键
	 * @param datas 时间段
	 * @throws ParseException
	 * @since 2016年2月17日 上午9:44:50
	 */
	public List<Map<String,Object>> getCountByStartAndEndRatio(String type,Integer tagId,Integer hid,List<Date> datas) throws ParseException;
	
	/**
	 * 获取接受红包而新增的用户
	 * @author WKX
	 * @param type 按时间查询（日、周、月）
	 * @param tagId 活动类型（不同的类型统计方式不同）
	 * @param hid 活动主键
	 * @param datas 时间段
	 * @since 2016年2月17日 上午10:26:38
	 */
	public List<Map<String,Object>> getFromHongbaoCountByStartAndEnd(String type,Integer tagId,Integer hid,List<Date> datas);
	
	/**
	 * 获取接受红包而新增的用户（新增率）
	 * @author WKX
	 * @param type 按时间查询（日、周、月）
	 * @param tagId 活动类型（不同的类型统计方式不同）
	 * @param hid
	 * @param datas
	 * @since 2016年2月17日 上午11:36:30
	 */
	public List<Map<String,Object>> getFromHongbaoCountByStartAndEndRatio(String type,Integer tagId,Integer hid,List<Date> datas);
	
	/**
	 * 红包活动来的用户在时间段内的活跃度
	 * @author WKX
	 * @param type 按时间查询（日、周、月）
	 * @param tagId 活动类型（不同的类型统计方式不同）
	 * @param hid 红包活动的主键
	 * @param datas
	 * @param mStart 用户新增的开始日期（红包活动开始日期）
	 * @param mEnd 用户新增的结束日期（红包活动结束日期）
	 * @since 2016年2月17日 下午5:51:52
	 */
	public List<Map<String,Object>> getCountFromNewByStartAndEnd(String type,Integer tagId,Integer hid,List<Date> datas,Date mStart,Date mEnd);
	
	/**
	 * 获取区间内  新增用户的 留存率（拆解为 时间段内新增，时间段内活跃）周
	 * @author WKX
	 * @param hid
	 * @param otherDays
	 * @param datas
	 * @param xTitle
	 * @param yTitle
	 * @since 2016年2月19日 上午10:54:51
	 */
	public List<List<Object>> getLcWeekByStartAndEnd(Integer hid,String otherDays,List<Date> datas,List<Integer> xTitle,List<String> yTitle) throws ParseException;
	
	/**
	 * 获取区间内  新增用户的 留存率（拆解为 时间段内新增，时间段内活跃）月
	 * @author WKX
	 * @param hid
	 * @param otherDays
	 * @param datas
	 * @param xTitle
	 * @param yTitle
	 * @throws ParseException
	 * @since 2016年2月19日 下午3:15:17
	 */
	public List<List<Object>> getLcMonthByStartAndEnd(Integer hid,String otherDays,List<Date> datas,List<Integer> xTitle,List<String> yTitle) throws ParseException;
}