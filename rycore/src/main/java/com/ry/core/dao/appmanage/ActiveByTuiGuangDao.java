package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ActiveByTuiGuangDao {

	/**
	 * 推广，从推广活跃统计表中获取数据
	 * @param beginDate
	 * @param endDate
	 * @param flag，2日0周1月
	 * @param style，PC|WAP
	 * @return
	 * @date 2016年2月15日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> getActive(Date beginDate, Date endDate,
			String flag, String style);

	/**
	 * 推广，活跃信息统计入库
	 * @param flag，2日0周1月
	 * @date 2016年2月15日
	 * @author lvyanqin
	 */
	void countActive(int flag, Date executeDate, Date endDate);

}
