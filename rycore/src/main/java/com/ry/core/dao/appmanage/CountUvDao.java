package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CountUvDao {

	/**
	 * 日周uv,统计数据入库
	 * @param flag
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	void countUv(int flag, Date executeDate, Date endDate);

	/**
	 * 日周uv，数据获取
	 * @param beginDate
	 * @param endDate
	 * @param flag,2日0周
	 * @param style，PC|WAP
	 * @return
	 * @date 2016年2月18日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> getUv(Date beginDate, Date endDate, String flag,
			String style);

}
