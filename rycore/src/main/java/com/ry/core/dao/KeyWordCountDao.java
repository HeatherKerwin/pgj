package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.KeyWordCount;

public interface KeyWordCountDao {

	/**
	 * 保存对象
	 * @author WKX
	 * @param keyWordCount
	 * @since 2016年5月5日 下午3:07:00
	 */
	public void saveModel(KeyWordCount keyWordCount);
	
	/**
	 * 根据哪一天和关键字 查询统计的数据
	 * @author WKX
	 * @param day
	 * @param keyword
	 * @since 2016年5月5日 下午3:13:40
	 */
	public List<KeyWordCount> getByDayAndKeyword(String day,String keyword);
	
	/**
	 * 根据起止时间 关键字（去掉重复关键字）统计数量
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param keyword 关键字
	 * @author WKX
	 */
	public List<Map<String,Object>> getByStartAndEndAndKwGroup(String start,String end,String keyword);
	
	/**
	 * 根据起止时间 关键字 图标用
	 * @param start
	 * @param end
	 * @param keyword
	 * @author WKX
	 */
	public List<Map<String,Object>> getByStartAndEndAndKw(String start,String end,String keyword);
}