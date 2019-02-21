package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IncreaseUserByTuiGuangService {

	/**
	 * 统计推广新增用户信息
	 * @param flag，用来表示日周月，2日0周1月
	 * @date 2016年2月14日
	 * @author lvyanqin
	 */
	void countIncreaseUser(int flag, Date executeDate, Date endDate);

	/**
	 * 根据条件查询推广新增统计表
	 * @param beginDate
	 * @param endDate
	 * @param flag，2日0周1月
	 * @param style，PC|WAP
	 * @return
	 * @date 2016年2月15日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> findIncreaseUser(Date beginDate, Date endDate,
			Integer flag, String style);

}
