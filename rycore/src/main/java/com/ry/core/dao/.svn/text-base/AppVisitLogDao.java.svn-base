package com.ry.core.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.AppVisitLog;

public interface AppVisitLogDao {

	void add(AppVisitLog visit);

	/**
	 * 获取时间范围内（每个用户活跃次数）
	 * @author WKX
	 * @param start
	 * @param end
	 * @since 2016年2月2日 上午10:11:29
	 */
	public List<Map<String,Object>> getGroupByMemberIdAndActionDate(String start,String end);
	
	/**
	 * 统计访问请求
	 * @author WKX
	 * @param code 某个操作编号
	 * @param start
	 * @param end
	 * @since 2016年2月15日 下午1:55:55
	 */
	public List<Map<String,Object>> getCountByStartAndEnd(String code,Date start,Date end);
	
	/**
	 * 查询红包活动来的新增用户（在一段时间内的活跃度）
	 * @author WKX
	 * @param hid 红包编号（来自哪个红包活动）
	 * @param mStart 开始日期（用户新增）
	 * @param mEnd 结束日期（用户新增）
	 * @param vStart 开始日期（活跃区间）
	 * @param vEnd 结束日期（活跃区间）
	 * @since 2016年2月17日 下午4:58:25
	 */
	public List<Map<String,Object>> getCountFromNewByStartAndEnd(Integer hid,Date mStart,Date mEnd,Date vStart,Date vEnd);
	
	/**
	 * 查询红包活动来的新增用户（在一段时间内访问过系统的用户数）
	 * @param hid 红包编号（来自哪个红包活动）
	 * @param mStart 开始日期（用户新增）
	 * @param mEnd 结束日期（用户新增）
	 * @param vStart 开始日期（活跃区间）
	 * @param vEnd 结束日期（活跃区间）
	 * @since 2016年2月19日 下午2:18:02
	 */
	public List<Map<String,Object>> getTotalCountFromNewByStartAndEnd(Integer hid,Date mStart,Date mEnd,Date vStart,Date vEnd);
	
	/**
	 * 获取该段时间内新增用户的活跃度（本月新增本月活跃）
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @since 2017年2月27日 下午4:59:00
	 */
	public List<Map<String, Object>> getByStartAndEnd(Date start,Date end);
	
	/**
	 * 获取用户在该时间内的活跃度
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param memberIds 用户主键
	 * @since 2017年3月28日 下午4:52:38
	 */
	public List<Map<String, Object>> getByStartAndEndInMemberIds(Date start,Date end,List<Integer>memberIds);
	
	/**
	 * 拷贝期间内数据（如果appVisitLogTemp表存在先删除）
	 * @author WKX
	 * @param start 开始时间
	 * @param end 结束时间
	 * @since 2017年3月1日 上午10:56:30
	 */
	public void createTempByStartAndEnd(Date start,Date end);
	
	/**
	 * 获取该时段内的申请认证机构方，且成功的用户的活跃（线上推广渠道注册的用户）
	 * @author WKX
	 * @param start
	 * @param end
	 * @since 2017年3月2日 下午3:52:57
	 */
	public List<Map<String, Object>> getHasOrgInfoByStartAndEnd(Date start,Date end);
	
	/**
	 * 获取该时间段注册用户活跃数（取前多少条，活跃由大到小）（于浩销售数据活跃度专用）
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param index 开始数据
	 * @param size 数量
	 * @since 2017年3月29日 下午2:46:28
	 */
	public List<Map<String, Object>> getByStartAndEnd(Date start,Date end,Integer index,Integer size);
	
	/**
	 * 获取时间段内前多少用户（排除掉指定用户）
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param size 前多少数据
	 * @param memberIds 排除的用户
	 * @since 2017年3月29日 下午5:32:55
	 */
	public List<Map<String, Object>> getByStartAndEndNotinMemberIds(Date start,Date end,Integer size,List<Integer>memberIds);
	
	/**
	 * 获取用户在该时间内的活跃度（与getByStartAndEndInMemberIds不同在于不涉及推荐用户合作等）（于浩销售数据活跃度专用）
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param memberIds 用户主键
	 * @since 2017年3月29日 下午3:08:50
	 */
	public List<Map<String, Object>> getByStartAndEndInMemberIdsNoReple(Date start,Date end,List<Integer>memberIds);
	
	/**
	 * 统计该用户时段内访问量
	 * @author WKX
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param memberId 用户主键
	 * @since 2017年8月15日 下午5:19:51
	 */
	public BigInteger countByStartAndEndAndMemberId(Date start,Date end,Integer memberId);
}