package com.ry.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.KeyWordCount;

public interface KeyWordCountService {

	/**
	 * 保存对象（同日期和关键字的 不保存）
	 * @author WKX
	 * @param keyWordCount
	 * @since 2016年5月5日 下午3:07:18
	 */
	public void saveModel(KeyWordCount keyWordCount);
	
	/**
	 * 根据起止时间
	 * @param start 开始日期
	 * @param end 结束日期
	 * @param keyword 关键字
	 * @author WKX
	 */
	public List<Map<String,Object>> getByStartAndEndAndKwGroup(String start,String end,String keyword);
	
	/**
	 * 根据起止时间 关键字 图标用
	 * @param type 类型（DAY日、WEEK周、MONTH月）
	 * @param keyword 关键字
	 * @param datas 日期
	 * @author WKX
	 */
	public List<Map<String,Object>> getByStartAndEndAndKw(String type,String keyword,List<Date> datas);
}