package com.ry.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.ClickCount;

public interface ClickCountDao {

	void saveClickCount(ClickCount clickCount);

	int count(String style, String code);

	int countByIp(String style);

	int countByUuid(String style);

	List<String> getAllStyle();

	List<String> getAllCodes();

	/**
	 * 获取所有记录
	 * @author WKX
	 * @since 2016年5月3日 上午11:40:48
	 */
	public List<ClickCount> getList();
	
	/**
	 * 更新记录
	 * @author WKX
	 * @param clickCount
	 * @since 2016年5月3日 上午11:37:53
	 */
	public void updateClickCount(ClickCount clickCount);
	
	/**
	 * 根据创建时间 获取该时段内的 百度推广关键字 访问数据（含日期、关键字、搜索次数、新增人数）根据时间和关键字分别分组 查询
	 * @author WKX
	 * @param start
	 * @param end
	 * @since 2016年5月5日 下午3:24:20
	 */
	public List<Map<String,Object>> getCountByCurrentDate(Date start,Date end);
	
	/**
	 * 获取访问记录（没有关键字的）用于初始化
	 * @param url 请求匹配（过滤渠道用，防止一下处理数据太多）
	 * @author WKX
	 * @since 2016年7月13日 下午6:08:29
	 */
	public List<ClickCount> getNoKeyword(String url);
	
	/**
	 * 根据时间范围获取个渠道的关键字注册数
	 * @author WKX
	 * @param start 开始日期
	 * @param end 结束日期
	 * @since 2017年3月28日 下午2:13:55
	 */
	public List<Map<String,Object>> getSrcCountByDate(Date start,Date end);
	
	/**
	 * 根据时间和来源，获取时间内访问总量和ip访问总数
	 * @author MH
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param source 来源
	 * @return
	 */
	public List<ClickCount> getListCountByDay(Date start,Date end,String source);
}