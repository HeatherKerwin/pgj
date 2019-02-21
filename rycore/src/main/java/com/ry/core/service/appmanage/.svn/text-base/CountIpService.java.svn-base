package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CountIpService {

	/**
	 * 推广IP,日周统计入库
	 * @param flag,2日0周
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	void countIp(int flag, Date executeDate, Date endDate);

	/**
	 * 查询日周ip数据
	 * @param beginDate
	 * @param endDate
	 * @param flag,2日0周
	 * @param style
	 * @return
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> getIp(Date beginDate, Date endDate, String flag,
			String style);

}
