package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.IncreasedUserByFunction;

public interface IncreaseUserByFunctionDao {

	/**
	 * 根据功能来统计新增用户数据
	 * @param flag,0周1月2日
	 * @date 2016年1月28日
	 * @author lvyanqin
	 */
	void countIncreaseUserByFlag(int flag, Date executeDate, Date endDate);

	/**
	 * 获取功能里的新增用户数据
	 * @param beginDate
	 * @param endDate
	 * @param flag，0周1月2日
	 * @param code，功能
	 * @return
	 * @date 2016年1月29日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> findIncreaseUserByFunction(Date beginDate,
			Date endDate, Integer flag, String code);

}
