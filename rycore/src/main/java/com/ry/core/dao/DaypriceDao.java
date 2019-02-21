package com.ry.core.dao;

import java.util.List;

import com.ry.core.entity.Dayprice;

public interface DaypriceDao {
	
	List<Dayprice> getList(String day, Integer type4, String time);
	
	/**
	 * 获取报价
	 * @author RY
	 * @param time 几点的报价
	 * @param day 哪一天的报价
	 * @param type1 承兑行
	 * @param type2 票据金额
	 * @param type4 地域
	 * @EDIT WKX APP2.1 价格已调整（大小票存放字段不同）
	 */
	List<Dayprice> getList(String time, String day, Integer type1, Integer type2, Integer type4);
	
	Dayprice getDayprice(Integer id);
	
	void addDayprice(Dayprice dayprice);
	
	void updateDayprice(Dayprice dayprice);
	
	List<Dayprice> findDaypriceList(String day, Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
	
	List<Dayprice> findDaypriceList(String day1, String day2, String day3, Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
	
	List<Dayprice> findDaypriceList(String day1, String day2, String day3, String day4, String day5, String day6, Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
	
	List<Dayprice> findDaypriceList(Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
}