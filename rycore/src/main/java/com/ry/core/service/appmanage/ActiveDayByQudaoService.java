package com.ry.core.service.appmanage;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.da.ActiveDayByQudao;

public interface ActiveDayByQudaoService {

	/**
	 * @date 2016-1-5
	 * @author lvyanqin
	 * 日活跃信息入库
	 */
	void countDay(Date executeDate, Date endDate);

	/**
	 * 得到日活跃数据
	 * @date 2016-1-5
	 * @author lvyanqin
	 */
	List<ActiveDayByQudao> activeDays(Date beginDate, Date endDate, String qudao);

	

}
