package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RemainMonthByScaleDao {

	/**
	 * 质量与规模，月留存信息入库
	 * 
	 * @date 2016年2月22日
	 * @author lvyanqin
	 */
	void countReaminMonthByScale(Date executeDate, Date endDate);

	/**
	 * 获取月留存信息
	 * @param beginDate
	 * @param endDate1
	 * @return
	 * @date 2016年2月22日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> showMonthRemain(Date beginDate, Date endDate1);

}
