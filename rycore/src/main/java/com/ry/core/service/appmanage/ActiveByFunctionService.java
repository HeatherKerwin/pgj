package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ActiveByFunctionService {

	void countActiveByFunction(String flag, Date executeDate, Date endDate);

	/**
	 * 根据开始时间、结束时间、功能字段、日周月、条件，查询功能新增用户数据
	 * @param beginDate
	 * @param endDate
	 * @param flag，0周1月2日
	 * @param code，功能字段，请参考Constant.java
	 * @return
	 * @date 2016年2月2日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> findActiveByFunction(Date beginDate,
			Date endDate, String flag, String code);

}
