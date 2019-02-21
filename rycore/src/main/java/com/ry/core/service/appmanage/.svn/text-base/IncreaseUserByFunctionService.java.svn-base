package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.IncreasedUserByFunction;

public interface IncreaseUserByFunctionService {

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
	List<Map<String, Object>>findIncreaseUserByFunction(Date beginDate,
			Date endDate, Integer flag, String code);

	/**
	 * 处理结果，showDates的集合表示需要显示的数据，如果lists中没有对应的数据，只能将数据赋上0
	 * lists中包含amount，date等键
	 * @param showDates
	 * @param lists
	 * @param num,开始时间和结束时间相隔的星期数或月数或天数
	 * @return
	 * @date 2016年2月2日
	 * @author lvyanqin
	 */
	public Map<String, Object> handelResult(List<Date> showDates, List<Map<String, Object>> lists, int num);
}
