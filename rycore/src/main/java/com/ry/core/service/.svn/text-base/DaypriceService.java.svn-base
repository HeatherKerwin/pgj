package com.ry.core.service;

import java.util.List;

import com.ry.core.entity.Dayprice;

public interface DaypriceService {

	List<Dayprice> getDaypriceList(String day, Integer type4, String time);
	
	public void updateAllDayprice(List<Dayprice> daypriceList) throws Exception;
	
	List<Dayprice> findDaypriceList(String day, Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
	
	List<Dayprice> findDaypriceList(String day1, String day2, String day3, Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
	
	List<Dayprice> findDaypriceList(String day1, String day2, String day3, String day4, String day5, String day6, Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
	
	List<Dayprice> getList(String time, String day, Integer type1, Integer type2, Integer type4);
	
	List<Dayprice> findDaypriceList(Integer type1, Integer type2, Integer type4, String time, Integer start, Integer end);
	
	/**
	 * 批量添加Dayprice
	 * @author BKY
	 */
	public void addAllDayprice(List<Dayprice> daypriceList) throws Exception;
}
