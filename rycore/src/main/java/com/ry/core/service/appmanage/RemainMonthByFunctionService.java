package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RemainMonthByFunctionService {

	/**
	 * 功能模块，分析统计月留存信息并入库
	 * @param field 1功能，2推广PC,3推广WAP
	 * @date 2016年2月3日
	 * @author lvyanqin
	 */
	void countRemainMonthByFunction(String field, Date executeDate, Date endDate);

	/**
	 * 功能模块，根据功能，开始时间，结束时间来获取月留存信息
	 * @param code
	 * @param beginDate
	 * @param endDate1
	 * @param field 1功能，2推广PC,3推广WAP
	 * @return
	 * @date 2016年2月3日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> showMonthRemain(String code, Date beginDate,
			Date endDate1,String field);
	
}
