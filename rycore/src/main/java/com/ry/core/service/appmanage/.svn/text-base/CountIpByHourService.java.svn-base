package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CountIpByHourService {

	/**
	 * 推广IP,统计入库
	 * 
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	void countIpByHour(Date executeDate, Date endDate);
	
	/**
	 * 求出所有的小时数（0-(hours-1)）
	 * @param hours
	 * @return
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	public List<Integer> getBetweenHours(int hours);
	
	/**
	 * 对查询到的小时数进行处理，如果对应的这个小时没有数据，就给其赋0
	 * @param showHours
	 * @param lists
	 * @return
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	public Map<String, Object> handelResult(List<Integer> showHours, List<Map<String, Object>> lists, int num);

	/**
	 * 获取时ip数据
	 * @param beginDate
	 * @param endDate
	 * @param style
	 * @param hours
	 * @return
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> getIpByHour(Date beginDate, Date endDate,
			String style, int hours);
	
}
