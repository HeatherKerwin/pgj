package com.ry.core.dao.appmanage;

import java.util.Date;
import java.util.List;

import com.ry.core.entity.da.ActiveByQudao;

public interface ActiveByQudaoDao {

	/**
	 * 获取周月活跃信息
	 * @param beginDate,开始时间
	 * @param endDate，结束时间
	 * @param flag,0代表周，1代表月
	 * @param qudao
	 * @return
	 * @date 2016年1月11日
	 * @author lvyanqin
	 */
	List<ActiveByQudao> activeByQudao(Date beginDate, Date endDate, String flag, String qudao);

	/**
	 * 一周没满，也有统计数据
	 * 周
	 * @date 2016年1月8日
	 * @author lvyanqin
	 */
	void countActive(Date executeDate, Date endDate);

	/**
	 * 一周没满，也有统计数据
	 * 月
	 * @date 2016年1月8日
	 * @author lvyanqin
	 */
	void countActiveMonths(Date executeDate, Date endDate);
	
}
