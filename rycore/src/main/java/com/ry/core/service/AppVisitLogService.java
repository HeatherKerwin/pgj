package com.ry.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.AppVisitLog;

public interface AppVisitLogService {

	void add(AppVisitLog visit);

	/**
	 * 根据起止时间获取每个用户的活跃天数
	 * @author WKX
	 * @param start
	 * @param end
	 * @since 2016年2月2日 上午10:18:17
	 */
	public List<Map<String,Object>> getGroupByMemberIdAndActionDate(String start,String end);
	
	/**
	 * 获取该段时间内新增用户的活跃情况
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @since 2017年2月27日 下午5:01:01
	 */
	public List<Map<String, Object>> getByStartAndEnd(Date start,Date end);
	
	/**
	 * 获取用户在该时间内的活跃度
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param memberIds 用户主键
	 * @since 2017年3月28日 下午5:16:16
	 */
	public List<Map<String, Object>> getByStartAndEndInMemberIds(Date start,Date end,List<Integer> memberIds);
	
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
	 * 获取该时间段注册用户活跃数（取前多少条，活跃由大到小）（于浩活跃数据分析专用）
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
}