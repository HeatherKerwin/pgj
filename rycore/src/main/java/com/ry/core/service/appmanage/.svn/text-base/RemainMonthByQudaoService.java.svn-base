package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ry.core.entity.da.RemainMonthByQudao;

public interface RemainMonthByQudaoService {

	/**
	 * 统计的月留存,如果数据库中已存在数据，则一天一天的更新然后加上今天的统计数据
	 * @date 2016年1月8日
	 * @author lvyanqin
	 */
	void countReaminMonthByQudao(Date executeDate, Date endDate);

	/**
	 * 获取月留存信息
	 * @param qudao
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @date 2016年1月12日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> showMonthRemain(String qudao, Date beginDate,
			Date endDate);
	
}
