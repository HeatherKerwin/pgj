package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CountPvByHourDao {

	/**
	 * 统计推广,时PV
	 * 日和周的PV信息，在这张表中进行统计分析，实质上是累加
	 * 
	 * @date 2016年2月17日
	 * @author lvyanqin
	 */
	void countPvByHour(Date executeDate, Date endDate);

	/**
	 * 推广，获取PV信息
	 * @param beginDate
	 * @param endDate
	 * @param flag,2日0周  3时
	 * @param style
	 * @return
	 * @date 2016年2月17日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> getPv(Date beginDate, Date endDate, String flag,
			String style, Integer hours);
	
}
