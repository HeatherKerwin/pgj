package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RemainWeekByFunctionDao {

	/**
	 * 功能模块，分析统计周留存信息并入库
	 * @param field 1功能，2推广PC,3推广WAP
	 * @date 2016年2月3日
	 * @author lvyanqin
	 */
	void countRemainWeekByFunction(String field, Date executeDate, Date endDate);

	/**
	 * 根据功能，开始时间，结束时间获取功能周留存信息
	 * @param code
	 * @param beginDate
	 * @param endDate1
	 * @return
	 * @date 2016年2月3日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> showWeekRemain(String code, Date beginDate,
			Date endDate1,String field);

}
